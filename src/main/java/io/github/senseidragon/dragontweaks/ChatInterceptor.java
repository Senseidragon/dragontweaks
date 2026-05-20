package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.IColonyManager;
import com.minecolonies.api.colony.IVisitorData;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatInterceptor {

    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        ServerLevel serverLevel = (ServerLevel) player.level();

        double range = Config.COMMAND_PROXIMITY.get();
        AABB searchBox = AABB.ofSize(player.position(), range * 2, range * 2, range * 2);
        List<AssistantEntity> candidates = serverLevel.getEntitiesOfClass(AssistantEntity.class, searchBox);

        BookAdvisorEntity bookAdvisor = null;
        AdvisorState earlyState = null;
        if (!DragonTweaks.LITE_MODE) {
            earlyState = AdvisorStateData.get(serverLevel.getServer().overworld()).getState(player.getUUID());
            DragonTweaks.LOGGER.debug("[ChatInterceptor] player={} advisorState={}", player.getGameProfile().getName(), earlyState);
            if (earlyState == AdvisorState.PRE_COLONY || earlyState == AdvisorState.DORMANT) {
                double bookRange = Config.COMMAND_PROXIMITY.get() * 2;
                AABB bookBox = AABB.ofSize(player.position(), bookRange, bookRange, bookRange);
                for (BookAdvisorEntity ba : serverLevel.getEntitiesOfClass(BookAdvisorEntity.class, bookBox)) {
                    UUID baOwner = ba.getOwnerUUID();
                    if (baOwner != null && player.getUUID().equals(baOwner)) {
                        bookAdvisor = ba;
                        break;
                    }
                }
            }
        }

        if (candidates.isEmpty() && bookAdvisor == null
                && earlyState != AdvisorState.COLONY_NO_CITIZEN
                && earlyState != AdvisorState.COLONY_WITH_CITIZEN
                && !ModList.get().isLoaded("minecolonies")) return;

        String messageLower = event.getRawText().toLowerCase();
        String[] messageWords = messageLower.split("\\s+");
        String rawMessage = event.getRawText();

        // Single pass: name-match across all candidates (for follow/stop) + per-role nearest tracking
        List<AssistantEntity> allMatched = new ArrayList<>();
        List<AssistantEntity> nonAdvisorMatched = new ArrayList<>();
        AssistantEntity nearest = null;
        AssistantEntity nonAdvisorNearest = null;
        double nearestDistSq = Double.MAX_VALUE;
        double nonAdvisorNearestDistSq = Double.MAX_VALUE;

        for (AssistantEntity candidate : candidates) {
            double distSq = player.distanceToSqr(candidate);
            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                nearest = candidate;
            }
            boolean isAdvisor = "Advisor".equals(candidate.getRole());
            if (!isAdvisor && distSq < nonAdvisorNearestDistSq) {
                nonAdvisorNearestDistSq = distSq;
                nonAdvisorNearest = candidate;
            }
            if (candidate.getCustomName() != null) {
                String entityName = candidate.getCustomName().getString();
                for (String word : messageWords) {
                    String stripped = word.replaceAll("[^a-z0-9]", "");
                    if (!stripped.isEmpty() && AssistantCommand.nameMatches(entityName, stripped)) {
                        allMatched.add(candidate);
                        if (!isAdvisor) nonAdvisorMatched.add(candidate);
                        break;
                    }
                }
            }
        }

        // Non-Advisor routing: unchanged logic, scoped to non-Advisor entities
        List<AssistantEntity> nonAdvisorTargets = nonAdvisorMatched.isEmpty()
            ? (nonAdvisorNearest != null ? List.of(nonAdvisorNearest) : List.of())
            : nonAdvisorMatched;

        // Advisor routing: state-aware
        List<AssistantEntity> advisorTargets = new ArrayList<>();
        String advisorReminder = null;
        AdvisorState advisorState = null;

        if (!DragonTweaks.LITE_MODE) {
            AdvisorStateData stateData = AdvisorStateData.get(player.getServer().overworld());
            advisorState = stateData.getState(player.getUUID());

            for (AssistantEntity candidate : candidates) {
                if (!"Advisor".equals(candidate.getRole())) continue;

                switch (advisorState) {
                    case DORMANT:
                        break;
                    case PRE_COLONY:
                        advisorTargets.add(candidate);
                        break;
                    case COLONY_NO_CITIZEN:
                        if (messageLower.startsWith("advisor") && isPlayerInColony(player, serverLevel)) {
                            advisorTargets.add(candidate);
                        }
                        break;
                    case COLONY_WITH_CITIZEN: {
                        String citizenNameLower = candidate.getCustomName() != null
                            ? candidate.getCustomName().getString().toLowerCase()
                            : null;
                        boolean nameMatch = citizenNameLower != null && messageLower.startsWith(citizenNameLower);
                        boolean fallbackMatch = messageLower.startsWith("advisor");
                        if ((nameMatch || fallbackMatch) && isPlayerInColony(player, serverLevel)) {
                            advisorTargets.add(candidate);
                            if (fallbackMatch && !nameMatch && citizenNameLower != null && advisorReminder == null) {
                                String displayName = candidate.getCustomName().getString();
                                advisorReminder = "You know, you can just call me " + displayName + ".";
                            }
                        }
                        break;
                    }
                }
            }
        }

        // COLONY_NO_CITIZEN BookAdvisorEntity search
        BookAdvisorEntity colonyNoBookAdvisor = null;
        IColony colonyNoColony = null;
        if (!DragonTweaks.LITE_MODE && advisorState == AdvisorState.COLONY_NO_CITIZEN
                && messageLower.startsWith("advisor")
                && ModList.get().isLoaded("minecolonies")) {
            for (IColony c : IColonyManager.getInstance().getColonies(serverLevel)) {
                if (player.getUUID().equals(c.getPermissions().getOwner())) {
                    colonyNoColony = c;
                    break;
                }
            }
            if (colonyNoColony != null) {
                for (var e : serverLevel.getAllEntities()) {
                    if (e instanceof BookAdvisorEntity ba && player.getUUID().equals(ba.getOwnerUUID())) {
                        colonyNoBookAdvisor = ba;
                        break;
                    }
                }
            }
        }

        // Auto-correct stale COLONY_NO_CITIZEN when no colony exists for this player
        if (!DragonTweaks.LITE_MODE && advisorState == AdvisorState.COLONY_NO_CITIZEN && colonyNoColony == null) {
            DragonTweaks.LOGGER.warn("[ChatInterceptor] COLONY_NO_CITIZEN state but no colony found — resetting to PRE_COLONY for player={}",
                    player.getGameProfile().getName());
            AdvisorStateData.get(player.getServer().overworld()).setState(player.getUUID(), AdvisorState.PRE_COLONY);
            advisorState = AdvisorState.PRE_COLONY;
            // Re-run BookAdvisor search now that state is corrected
            if (bookAdvisor == null) {
                double bookRange = Config.COMMAND_PROXIMITY.get() * 2;
                AABB bookBox2 = AABB.ofSize(player.position(), bookRange, bookRange, bookRange);
                for (BookAdvisorEntity ba : serverLevel.getEntitiesOfClass(BookAdvisorEntity.class, bookBox2)) {
                    UUID baOwner = ba.getOwnerUUID();
                    if (baOwner != null && player.getUUID().equals(baOwner)) {
                        bookAdvisor = ba;
                        break;
                    }
                }
            }
        }

        // COLONY_WITH_CITIZEN: BookAdvisorEntity search
        BookAdvisorEntity colonyWithBookAdvisor = null;
        IColony colonyWithColony = null;
        String citizenNameForAdvisor = null;
        String citizenFirstName = null;
        boolean cwAdvisorKeyword = false;
        if (!DragonTweaks.LITE_MODE && advisorState == AdvisorState.COLONY_WITH_CITIZEN
                && ModList.get().isLoaded("minecolonies")) {
            AdvisorStateData cwStateData = AdvisorStateData.get(player.getServer().overworld());
            Integer assignedCitizenId = cwStateData.getAssignedCitizenId(player.getUUID());
            for (IColony c : IColonyManager.getInstance().getColonies(serverLevel)) {
                if (player.getUUID().equals(c.getPermissions().getOwner())) {
                    colonyWithColony = c;
                    break;
                }
            }
            if (colonyWithColony != null && assignedCitizenId != null) {
                var citizen = colonyWithColony.getCitizenManager().getCivilian(assignedCitizenId);
                if (citizen != null) {
                    citizenNameForAdvisor = NicknameData.resolve(player.getServer().overworld(), colonyWithColony.getID(), assignedCitizenId, citizen.getName());
                    citizenFirstName = citizenNameForAdvisor.split("\\s+")[0];
                }
            }
            boolean nameTriggered = false;
            if (citizenFirstName != null) {
                String[] cwWords = messageLower.split("\\s+");
                String firstName = citizenFirstName.toLowerCase();
                for (int i = 0; i < Math.min(3, cwWords.length); i++) {
                    String stripped = cwWords[i].replaceAll("[^a-z0-9]", "");
                    if (stripped.equals(firstName)) { nameTriggered = true; break; }
                }
            }
            cwAdvisorKeyword = messageLower.startsWith("advisor");
            DragonTweaks.LOGGER.info("[ChatInterceptor] COLONY_WITH_CITIZEN: assignedCitizenId={}, citizenName={}, citizenFirstName={}, nameTriggered={}, cwAdvisorKeyword={}",
                assignedCitizenId, citizenNameForAdvisor, citizenFirstName, nameTriggered, cwAdvisorKeyword);
            if ((nameTriggered || cwAdvisorKeyword) && colonyWithColony != null) {
                for (var e : serverLevel.getAllEntities()) {
                    if (e instanceof BookAdvisorEntity ba && player.getUUID().equals(ba.getOwnerUUID())) {
                        colonyWithBookAdvisor = ba;
                        break;
                    }
                }
            }
        }

        // Combined LLM targets
        List<AssistantEntity> allTargets = new ArrayList<>(advisorTargets);
        allTargets.addAll(nonAdvisorTargets);

        // Citizen conversation detection
        IColony citizenColony = null;
        ICitizenData matchedCitizen = null;
        String matchedDisplayName = null;
        String matchedJobDesc = null;
        if (ModList.get().isLoaded("minecolonies") && !rawMessage.isEmpty()) {
            for (IColony c : IColonyManager.getInstance().getColonies(serverLevel)) {
                if (player.getUUID().equals(c.getPermissions().getOwner())) {
                    citizenColony = c;
                    break;
                }
            }
            if (citizenColony != null) {
                ServerLevel overworld = player.getServer().overworld();
                RoleAssignmentData roleData = RoleAssignmentData.get(overworld);
                int cId = citizenColony.getID();
                for (ICitizenData citizen : citizenColony.getCitizenManager().getCitizens()) {
                    if (citizen instanceof IVisitorData) continue;
                    if (roleData.isAssigned(citizenColony.getID(), citizen.getId())) continue;
                    if (!CitizenAcknowledgmentData.get(overworld).isAcknowledged(citizenColony.getID(), citizen.getId())) continue;
                    String displayName = NicknameData.resolve(overworld, cId, citizen.getId(), citizen.getName());
                    boolean nicknameMatch = messageLower.contains(displayName.toLowerCase());
                    boolean realNameMatch = !displayName.equalsIgnoreCase(citizen.getName())
                        && messageLower.contains(citizen.getName().toLowerCase());
                    if (!nicknameMatch && !realNameMatch) continue;
                    matchedCitizen = citizen;
                    matchedDisplayName = displayName;
                    String jobDesc = "an unemployed colonist";
                    var job = citizen.getJob();
                    if (job != null) {
                        String jn = job.getNameTagDescription();
                        if (jn != null && !jn.isBlank()) jobDesc = jn;
                    }
                    matchedJobDesc = jobDesc;
                    break;
                }
            }
        }

        if (allTargets.isEmpty() && bookAdvisor == null && colonyNoBookAdvisor == null && colonyWithBookAdvisor == null && matchedCitizen == null) return;

        // Keyword detection — runs before LLM query; state change is immediate
        boolean isFollowIntent = containsAny(messageLower, "follow", "come with", "walk with me", "come along", "come here", "follow me");
        boolean isStopIntent  = containsAny(messageLower, "stop", "stay", "wait here", "stay put", "stand still", "wait for me");
        if (isFollowIntent) {
            for (AssistantEntity target : allTargets) target.setFollowing(true);
        } else if (isStopIntent) {
            List<AssistantEntity> stopTargets = allMatched.isEmpty() ? candidates : allMatched;
            for (AssistantEntity target : stopTargets) target.setFollowing(false);
        }

        player.sendSystemMessage(
                Component.literal("<" + player.getGameProfile().getName() + "> " + rawMessage)
        );
        event.setCanceled(true);

        MinecraftServer server = player.getServer();
        if (server == null) return;

        String timeOfDay = LLMClient.timeOfDay(serverLevel.getDayTime());
        String weather = LLMClient.weather(serverLevel.isRaining(), serverLevel.isThundering());
        String biomeName = player.level().getBiome(player.blockPosition())
                .unwrapKey().map(k -> k.location().toString()).orElse("unknown");

        for (AssistantEntity target : allTargets) {
            Component entityName = target.getCustomName() != null
                ? target.getCustomName()
                : Component.literal("Assistant [PoC]");

            DragonTweaks.LOGGER.info("[ChatInterceptor] {} -> {}: {}",
                player.getGameProfile().getName(),
                entityName.getString(),
                rawMessage);

            String surroundings = LLMClient.scanSurroundings(serverLevel, target);
            if (citizenColony != null) {
                String roster = ColonyContextBuilder.buildRoster(
                        citizenColony, player.getServer().overworld(), serverLevel, player);
                if (!roster.isEmpty()) {
                    surroundings += "\nColony members you know:\n" + roster;
                }
            }
            if (advisorState == AdvisorState.PRE_COLONY && advisorTargets.contains(target)) {
                String npcName = entityName.getString();
                String playerName = player.getGameProfile().getName();
                String scopedPrompt =
                    "You are " + npcName + ", an advisor helping scout a settlement location.\n" +
                    RolePersona.getPersonaBlock("advisor") + "\n" +
                    "You are standing in a " + biomeName + " biome. Time of day: " + timeOfDay + ". Weather: " + weather + ".\n" +
                    "Nearby: " + surroundings + ".\n" +
                    "Speak candidly about this location's suitability as a colony site. Assess terrain, biome, water proximity, elevation, forest coverage, and defensibility. " +
                    "If your conversation history shows you have already warned about a nearby village or hostile encampment, do not repeat that warning unless the player is asking about it directly.\n" +
                    "Never reference \"the game\", \"players\", or anything that breaks immersion.\n" +
                    "The person speaking to you is " + playerName + ".\n" +
                    "Respond in 1-2 sentences. Never break character. Never say you are an AI.";
                LLMClient.query(server, player, entityName, rawMessage, target.getUUID(), scopedPrompt);
            } else {
                LLMClient.query(server, player, entityName, rawMessage, target.getRole(), timeOfDay, weather, surroundings, target.getUUID());
            }
        }

        if (bookAdvisor != null) {
            Component bookName = Component.literal("Advisor");
            String terrainLabels = TerrainScanner.scan(serverLevel, player.blockPosition());
            AABB hostileBox = AABB.ofSize(player.position(), 200, 128, 200);
            List<Mob> nearbyHostiles = serverLevel.getEntitiesOfClass(Mob.class, hostileBox, e -> {
                ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(e.getType());
                if (id == null || !"minecolonies".equals(id.getNamespace())) return false;
                String path = id.getPath();
                return !path.equals("citizen") && !path.equals("visitor") && !path.equals("cavalry_horse");
            });
            String hostileContext = nearbyHostiles.isEmpty() ? "" :
                "DANGER: " + nearbyHostiles.size() + " hostile MineColonies " +
                (nearbyHostiles.size() == 1 ? "entity" : "entities") + " (barbarian or outpost type) " +
                (nearbyHostiles.size() == 1 ? "is" : "are") + " within 100 blocks. " +
                "There is almost certainly a barbarian encampment or outpost nearby. " +
                "Strongly warn " + player.getGameProfile().getName() + " — settling here would invite immediate raids.\n";
            String playerName = player.getGameProfile().getName();
            String scopedPrompt =
                "You are Advisor, an advisor helping scout a settlement location.\n" +
                RolePersona.getPersonaBlock("advisor") + "\n" +
                "You are at depth Y=" + player.getBlockY() + " in a " + biomeName + " biome. Time of day: " + timeOfDay + ". Weather: " + weather + ".\n" +
                "Nearby terrain: " + terrainLabels + ".\n" +
                hostileContext +
                "The terrain labels above are ground truth observed facts. Never contradict them regardless of biome or weather.\n" +
                "Label key: 'structures' means man-made construction (planks, stone bricks, torches, etc.) is nearby — likely ruins or an abandoned build. 'village' means a vanilla village is nearby (bell, smoker, lectern, etc.).\n" +
                "Speak candidly about this location's suitability as a colony site. Assess terrain, biome, water proximity, elevation, forest coverage, and defensibility. " +
                "Never reference \"the game\", \"players\", or anything that breaks immersion.\n" +
                "The person speaking to you is " + playerName + ".\n" +
                "Respond in 1-2 sentences. Never break character. Never say you are an AI.";
            DragonTweaks.LOGGER.debug("[DragonTweaks] PRE_COLONY prompt for {} at {}:\nTERRAIN: {}\nPROMPT: {}",
                playerName, player.blockPosition(), terrainLabels, scopedPrompt);
            UUID advisorMemoryId = UUID.nameUUIDFromBytes(("advisor:" + player.getUUID()).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            LLMClient.query(server, player, bookName, rawMessage, advisorMemoryId, scopedPrompt);
        }

        // COLONY_NO_CITIZEN: BookAdvisorEntity response
        if (colonyNoBookAdvisor != null && colonyNoColony != null) {
            String terrainLabels = TerrainScanner.scan(serverLevel, player.blockPosition());
            int thLevel = colonyNoColony.getServerBuildingManager().hasTownHall()
                ? colonyNoColony.getServerBuildingManager().getTownHall().getBuildingLevel()
                : 0;
            StringBuilder colonySummary = new StringBuilder();
            colonySummary.append("Town Hall level: ").append(thLevel).append("\n");
            colonySummary.append("Buildings:\n");
            for (var b : colonyNoColony.getServerBuildingManager().getBuildings().values()) {
                colonySummary.append("  - ")
                    .append(b.getBuildingType().getRegistryName().getPath())
                    .append(" (level ").append(b.getBuildingLevel()).append(")")
                    .append(" at ").append(b.getPosition())
                    .append("\n");
            }
            int population = colonyNoColony.getCitizenManager().getCurrentCitizenCount();
            int maxPopulation = colonyNoColony.getCitizenManager().getMaxCitizens();
            colonySummary.append("Population: ").append(population).append(" / ").append(maxPopulation);
            String strippedMessage = rawMessage.replaceFirst("(?i)^advisor\\s*", "");
            String playerName = player.getGameProfile().getName();
            String colonyNoPrompt =
                "You are Advisor, a colony advisor. You have full knowledge of the colony structure.\n" +
                "You are at depth Y=" + player.getBlockY() + " in a " + biomeName + " biome. Time of day: " + timeOfDay + ". Weather: " + weather + ".\n" +
                "Nearby terrain: " + terrainLabels + ".\n" +
                "Colony state:\n" + colonySummary + "\n" +
                "The person speaking to you is " + playerName + ".\n" +
                "Respond in 1 short sentence under 100 characters. Never break character. Never say you are an AI.";
            DragonTweaks.LOGGER.info("[ChatInterceptor] COLONY_NO_CITIZEN LLM fire: colonyNoColony={} colonyNoBookAdvisor={} advisorState={}", colonyNoColony != null ? "present" : "null", colonyNoBookAdvisor != null ? "present" : "null", advisorState);
            UUID advisorMemoryIdNo = UUID.nameUUIDFromBytes(("advisor:" + player.getUUID()).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            LLMClient.query(server, player, Component.literal("Advisor"), strippedMessage, advisorMemoryIdNo, colonyNoPrompt);
        }

        // COLONY_WITH_CITIZEN: BookAdvisorEntity response
        if (colonyWithBookAdvisor != null && colonyWithColony != null) {
            String cwTerrainLabels = TerrainScanner.scan(serverLevel, player.blockPosition());
            int cwThLevel = colonyWithColony.getServerBuildingManager().hasTownHall()
                ? colonyWithColony.getServerBuildingManager().getTownHall().getBuildingLevel()
                : 0;
            StringBuilder cwColonySummary = new StringBuilder();
            cwColonySummary.append("Town Hall level: ").append(cwThLevel).append("\n");
            cwColonySummary.append("Buildings:\n");
            for (var b : colonyWithColony.getServerBuildingManager().getBuildings().values()) {
                cwColonySummary.append("  - ")
                    .append(b.getBuildingType().getRegistryName().getPath())
                    .append(" (level ").append(b.getBuildingLevel()).append(")")
                    .append(" at ").append(b.getPosition())
                    .append("\n");
            }
            int cwPopulation = colonyWithColony.getCitizenManager().getCurrentCitizenCount();
            int cwMaxPopulation = colonyWithColony.getCitizenManager().getMaxCitizens();
            cwColonySummary.append("Population: ").append(cwPopulation).append(" / ").append(cwMaxPopulation).append("\n");
            cwColonySummary.append("Citizens:\n");
            for (var citizen : colonyWithColony.getCitizenManager().getCitizens()) {
                double happiness = citizen.getCitizenHappinessHandler().getHappiness(colonyWithColony, citizen);
                cwColonySummary.append("  - ").append(NicknameData.resolve(player.getServer().overworld(), colonyWithColony.getID(), citizen.getId(), citizen.getName()))
                    .append(" (happiness: ").append(String.format("%.2f", happiness)).append(")\n");
            }
            String advisorName = citizenNameForAdvisor != null ? citizenNameForAdvisor : "Advisor";
            String cwStrippedMessage = cwAdvisorKeyword
                ? rawMessage.replaceFirst("(?i)^advisor\\s*", "")
                : citizenFirstName != null
                    ? rawMessage.replaceFirst("(?i)^" + java.util.regex.Pattern.quote(citizenFirstName) + "\\s*", "")
                    : rawMessage;
            String cwPlayerName = player.getGameProfile().getName();
            String colonyWithPrompt =
                "You are " + advisorName + ", the colony Advisor. You have full knowledge of this colony's current state.\n" +
                RolePersona.getPersonaBlock("advisor") + "\n" +
                "Colony state:\n" +
                "- Town Hall level: " + cwThLevel + "\n" +
                "- Buildings: " + cwColonySummary + "\n" +
                "- Population: " + cwPopulation + "/" + cwMaxPopulation + "\n" +
                "The person speaking to you is " + cwPlayerName + ".\n" +
                "RULES:\n" +
                "- Base every recommendation strictly on the colony data above.\n" +
                "- A new colony with no Builder's Hut must be told to build one first — it is required before anything else can be constructed.\n" +
                "- Never give generic advice. Every recommendation must reference specific buildings, citizens, or data from the colony state above.\n" +
                "- Speak as " + advisorName + ". Never break character.\n" +
                "Respond in 1 short sentence under 100 characters.";
            UUID advisorMemoryIdWith = UUID.nameUUIDFromBytes(("advisor:" + player.getUUID()).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            LLMClient.query(server, player, Component.literal(advisorName), cwStrippedMessage, advisorMemoryIdWith, colonyWithPrompt);
            if (cwAdvisorKeyword && citizenNameForAdvisor != null) {
                player.sendSystemMessage(Component.literal("[" + advisorName + "]: You know, you can just call me " + advisorName + "."));
            }
        }

        // Advisor fallback reminder in COLONY_WITH_CITIZEN when "advisor" matched instead of citizen name
        if (advisorReminder != null) {
            AssistantEntity advisorEntity = advisorTargets.get(0);
            Component entityName = advisorEntity.getCustomName() != null
                ? advisorEntity.getCustomName()
                : Component.literal("Advisor");
            player.sendSystemMessage(Component.literal("[" + entityName.getString() + "]: " + advisorReminder));
        }

        // Citizen conversation routing
        if (matchedCitizen != null && citizenColony != null) {
            ServerLevel overworld = player.getServer().overworld();
            CitizenConversationMemory citizenMemory = CitizenConversationMemory.get(overworld);
            final int fColonyId = citizenColony.getID();
            final int fCitizenId = matchedCitizen.getId();
            final String fDisplayName = matchedDisplayName;
            final String fJobDesc = matchedJobDesc;
            final String playerName = player.getGameProfile().getName();
            String roster = ColonyContextBuilder.buildRoster(citizenColony, overworld, serverLevel, player);
            String colonyContext = roster.isEmpty() ? "" : "\nOther colony members you know:\n" + roster;
            String systemPrompt =
                "You are " + fDisplayName + ", a MineColonies citizen working as " + fJobDesc + " in a medieval colony. " +
                "Respond in character. Keep responses to 1-2 sentences.\n" +
                colonyContext +
                "The colony member list above includes a 'last seen' compass direction for each citizen. If asked where someone is, give only that general direction (e.g. 'I think I last saw them to the north'). Never invent specific locations like building names or landmarks.\n" +
                "Output only your spoken reply. No reasoning, no labels, no formatting tokens, no preamble. Never break character. Never say you are an AI.";
            UUID citizenNpcId = UUID.nameUUIDFromBytes(
                ("citizen:" + fColonyId + ":" + fCitizenId).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            LLMClient.query(server, player, Component.literal(fDisplayName), rawMessage,
                citizenNpcId, systemPrompt, LLMClient.MAX_RESPONSE_TOKENS, "flavor",
                reply -> {
                    citizenMemory.appendHistory(fColonyId, fCitizenId, playerName, rawMessage);
                    citizenMemory.appendHistory(fColonyId, fCitizenId, fDisplayName, reply);
                });
        }
    }

    private static boolean isPlayerInColony(ServerPlayer player, ServerLevel level) {
        if (!ModList.get().isLoaded("minecolonies")) return false;
        UUID playerUUID = player.getUUID();
        for (IColony colony : IColonyManager.getInstance().getColonies(level)) {
            if (playerUUID.equals(colony.getPermissions().getOwner())) {
                return colony.isCoordInColony(level, player.blockPosition());
            }
        }
        return false;
    }

    private static boolean containsAny(String message, String... phrases) {
        for (String phrase : phrases) {
            if (message.contains(phrase)) return true;
        }
        return false;
    }
}
