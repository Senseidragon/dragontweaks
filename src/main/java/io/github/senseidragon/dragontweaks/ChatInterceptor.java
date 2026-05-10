package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.IColonyManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
        if (!DragonTweaks.LITE_MODE) {
            AdvisorState earlyState = AdvisorStateData.get(serverLevel.getServer().overworld()).getState(player.getUUID());
            DragonTweaks.LOGGER.debug("[ChatInterceptor] player={} advisorState={}", player.getGameProfile().getName(), earlyState);
            if (earlyState == AdvisorState.PRE_COLONY || earlyState == AdvisorState.DORMANT) {
                AABB bookBox = AABB.ofSize(player.position(), 64, 64, 64);
                for (BookAdvisorEntity ba : serverLevel.getEntitiesOfClass(BookAdvisorEntity.class, bookBox)) {
                    if (player.getUUID().equals(ba.getOwnerUUID())) {
                        bookAdvisor = ba;
                        break;
                    }
                }
            }
        }

        if (candidates.isEmpty() && bookAdvisor == null) return;

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

        // Combined LLM targets
        List<AssistantEntity> allTargets = new ArrayList<>(advisorTargets);
        allTargets.addAll(nonAdvisorTargets);

        if (allTargets.isEmpty() && bookAdvisor == null) return;

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
            if (advisorState == AdvisorState.PRE_COLONY && advisorTargets.contains(target)) {
                String npcName = entityName.getString();
                String playerName = player.getGameProfile().getName();
                String scopedPrompt =
                    "You are " + npcName + ", an advisor helping scout a settlement location.\n" +
                    RolePersona.getPersonaBlock("advisor") + "\n" +
                    "You are standing in a " + biomeName + " biome. Time of day: " + timeOfDay + ". Weather: " + weather + ".\n" +
                    "Nearby: " + surroundings + ".\n" +
                    "Speak ONLY about terrain, biome, water proximity, elevation, forest coverage, and defensibility. " +
                    "Never mention colonies, citizens, buildings, happiness, or workers. " +
                    "Never reference \"the game\", \"players\", or anything that breaks immersion.\n" +
                    "The person speaking to you is " + playerName + ".\n" +
                    "Respond in 1 short sentence under 100 characters. Never break character. Never say you are an AI.";
                LLMClient.query(server, player, entityName, rawMessage, target.getUUID(), scopedPrompt);
            } else {
                LLMClient.query(server, player, entityName, rawMessage, target.getRole(), timeOfDay, weather, surroundings, target.getUUID());
            }
        }

        if (bookAdvisor != null) {
            Component bookName = Component.literal("Advisor");
            String surroundings = "nothing notable nearby";
            String terrainLabels = TerrainScanner.scan(serverLevel, player.blockPosition());
            String playerName = player.getGameProfile().getName();
            String scopedPrompt =
                "You are Advisor, an advisor helping scout a settlement location.\n" +
                RolePersona.getPersonaBlock("advisor") + "\n" +
                "You are at depth Y=" + player.getBlockY() + " in a " + biomeName + " biome. Time of day: " + timeOfDay + ". Weather: " + weather + ".\n" +
                "Nearby: " + surroundings + ".\n" +
                "Nearby terrain: " + terrainLabels + ".\n" +
                "The terrain labels above are ground truth observed facts. Never contradict them regardless of biome or weather.\n" +
                "Label key: 'structures' means man-made construction (planks, stone bricks, torches, etc.) is nearby — likely ruins or an abandoned build. 'village' means a vanilla village is nearby (bell, smoker, lectern, etc.).\n" +
                "Speak ONLY about terrain, biome, water proximity, elevation, forest coverage, and defensibility. " +
                "Never mention colonies, citizens, buildings, happiness, or workers. " +
                "Never reference \"the game\", \"players\", or anything that breaks immersion.\n" +
                "The person speaking to you is " + playerName + ".\n" +
                "Respond in 1 short sentence under 100 characters. Never break character. Never say you are an AI.";
            DragonTweaks.LOGGER.debug("[DragonTweaks] PRE_COLONY prompt for {} at {}:\nTERRAIN: {}\nPROMPT: {}",
                playerName, player.blockPosition(), terrainLabels, scopedPrompt);
            LLMClient.query(server, player, bookName, rawMessage, bookAdvisor.getUUID(), scopedPrompt);
        }

        // Advisor fallback reminder in COLONY_WITH_CITIZEN when "advisor" matched instead of citizen name
        if (advisorReminder != null) {
            AssistantEntity advisorEntity = advisorTargets.get(0);
            Component entityName = advisorEntity.getCustomName() != null
                ? advisorEntity.getCustomName()
                : Component.literal("Advisor");
            player.sendSystemMessage(Component.literal("[" + entityName.getString() + "]: " + advisorReminder));
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
