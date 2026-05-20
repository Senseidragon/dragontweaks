package io.github.senseidragon.dragontweaks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class PreColonyScoutTicker {

    private static final int TICK_INTERVAL = 1200;
    private static int tickCounter = 0;
    private static final Map<UUID, BlockPos> lastObservedPos = new HashMap<>();
    private static final Random random = new Random();

    public static void onServerTick(ServerTickEvent.Post event) {
        if (++tickCounter % TICK_INTERVAL != 0) return;
        if (DragonTweaks.LITE_MODE) return;

        MinecraftServer server = event.getServer();
        for (ServerLevel level : server.getAllLevels()) {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.serverLevel() != level) continue;
                tryFireScoutObservation(server, level, player);
            }
        }
    }

    private static void tryFireScoutObservation(MinecraftServer server, ServerLevel level, ServerPlayer player) {
        UUID playerUUID = player.getUUID();

        AdvisorState state = AdvisorStateData.get(server.overworld()).getState(playerUUID);
        if (state != AdvisorState.PRE_COLONY) return;

        BlockPos pos = player.blockPosition();
        BlockPos lastPos = lastObservedPos.get(playerUUID);
        if (lastPos != null && (
                Math.abs(pos.getX() - lastPos.getX()) < 64 &&
                Math.abs(pos.getZ() - lastPos.getZ()) < 64)) return;
        lastObservedPos.put(playerUUID, pos);

        AABB searchBox = AABB.ofSize(player.position(), 64, 64, 64);
        BookAdvisorEntity bookAdvisor = null;
        for (BookAdvisorEntity ba : level.getEntitiesOfClass(BookAdvisorEntity.class, searchBox)) {
            if (playerUUID.equals(ba.getOwnerUUID())) {
                bookAdvisor = ba;
                break;
            }
        }
        if (bookAdvisor == null) return;

        String terrainLabels = TerrainScanner.scan(level, pos);

        AABB raiderBox = AABB.ofSize(player.position(), 200, 128, 200);
        List<Mob> nearbyRaiders = level.getEntitiesOfClass(Mob.class, raiderBox, e -> {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(e.getType());
            if (id == null || !"minecolonies".equals(id.getNamespace())) return false;
            String path = id.getPath();
            return !path.equals("citizen") && !path.equals("visitor") && !path.equals("cavalry_horse");
        });
        String raiderContext = "";
        if (!nearbyRaiders.isEmpty()) {
            raiderContext =
                "DANGER: " + nearbyRaiders.size() + " hostile MineColonies " +
                (nearbyRaiders.size() == 1 ? "entity" : "entities") + " (barbarian or outpost type) " +
                (nearbyRaiders.size() == 1 ? "is" : "are") + " within 100 blocks. " +
                "There is almost certainly a barbarian encampment or outpost nearby. " +
                "Strongly warn " + player.getGameProfile().getName() + " — settling here would invite immediate raids.\n";
        }

        int villageRadiusBlocks = Config.SCOUT_VILLAGE_REPORT_RADIUS.get();
        int villageRadiusChunks = Math.max(1, villageRadiusBlocks / 16);
        BlockPos villagePos = level.findNearestMapStructure(
                net.minecraft.tags.StructureTags.VILLAGE, pos, villageRadiusChunks, false);
        String villageContext = "";
        if (villagePos != null) {
            int dx = villagePos.getX() - pos.getX();
            int dz = villagePos.getZ() - pos.getZ();
            int distance = (int) Math.sqrt(dx * dx + dz * dz);
            if (distance > villageRadiusBlocks) villagePos = null;
        }
        if (villagePos != null) {
            int dx = villagePos.getX() - pos.getX();
            int dz = villagePos.getZ() - pos.getZ();
            int distance = (int)(Math.round(Math.sqrt(dx*dx + dz*dz) / 50.0) * 50);
            double angle = Math.atan2(dz, dx);
            String direction;
            if (angle < -7 * Math.PI / 8 || angle >= 7 * Math.PI / 8) direction = "W";
            else if (angle < -5 * Math.PI / 8) direction = "NW";
            else if (angle < -3 * Math.PI / 8) direction = "N";
            else if (angle < -Math.PI / 8) direction = "NE";
            else if (angle < Math.PI / 8) direction = "E";
            else if (angle < 3 * Math.PI / 8) direction = "SE";
            else if (angle < 5 * Math.PI / 8) direction = "S";
            else direction = "SW";
            villageContext =
                "A village is approximately " + distance + " blocks to the " + direction + ".\n" +
                "Warn " + player.getGameProfile().getName() + " that placing a colony near a village risks iron golem hostility and pillager raid escalation.\n";
        }

        String biomeName = level.getBiome(pos).unwrapKey()
                .map(k -> k.location().toString()).orElse("unknown");
        String timeOfDay = LLMClient.timeOfDay(level.getDayTime());
        String weather = LLMClient.weather(level.isRaining(), level.isThundering());
        String playerName = player.getGameProfile().getName();

        String scopedPrompt =
            "You are Advisor, an advisor helping scout a settlement location.\n" +
            RolePersona.getPersonaBlock("advisor") + "\n" +
            "You are at depth Y=" + pos.getY() + " in a " + biomeName + " biome. Time of day: " + timeOfDay + ". Weather: " + weather + ".\n" +
            "Nearby terrain: " + terrainLabels + ".\n" +
            villageContext +
            raiderContext +
            "The terrain labels above are ground truth observed facts. Never contradict them.\n" +
            "Label key: 'structures' means man-made construction (planks, stone bricks, torches, etc.) is nearby — likely ruins or an abandoned build. 'village' means a vanilla village is nearby (bell, smoker, lectern, etc.).\n" +
            "You are making an unsolicited observation — the player has not asked you anything. " +
            "Make exactly one specific, opinionated observation about this location's suitability as a colony site. Address " + playerName + " directly.\n" +
            "Assess terrain, biome, water proximity, elevation, forest coverage, defensibility, and any nearby village risk. " +
            "If your conversation history shows you have already warned about a nearby village or hostile encampment, do not repeat that warning — focus on something new.\n" +
            "Never reference \"the game\", \"players\", or anything that breaks immersion.\n" +
            "Respond in 1 short sentence. Never break character. Never say you are an AI." +
            (random.nextInt(7) == 0 ? "\nYou may use dry wit if the terrain has an obvious problem." : "");

        UUID advisorMemoryId = UUID.nameUUIDFromBytes(("advisor:" + playerUUID).getBytes(StandardCharsets.UTF_8));

        DragonTweaks.LOGGER.debug("[PreColonyScoutTicker] Firing LLM query for player={} terrain={}", playerName, terrainLabels);
        LLMClient.query(server, player, Component.literal("Advisor"),
                "terrain seen: " + terrainLabels, advisorMemoryId, scopedPrompt, LLMClient.SPECIALIZED_MAX_TOKENS, "specialized",
                reply -> DragonTweaks.LOGGER.debug("[PreColonyScoutTicker] LLM callback fired for player={} reply={}", playerName, reply));
    }
}
