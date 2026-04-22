package io.github.senseidragon.dragontweaks;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.*;

public class ObservationTicker {

    private static final Map<UUID, Set<String>> lastSeen = new HashMap<>();
    private static final Map<UUID, Long> lastHostileMs = new HashMap<>();
    private static final Map<UUID, Long> lastPassiveMs = new HashMap<>();

    private static int tickCounter = 0;
    private static final int TICK_INTERVAL = 100;

    public static void onServerTick(ServerTickEvent.Post event) {
        if (++tickCounter < TICK_INTERVAL) return;
        tickCounter = 0;

        if (!Config.NPC_OBSERVATIONS_ENABLED.get()) return;

        MinecraftServer server = event.getServer();
        long hostileCooldownMs = Config.NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS.get() * 1000L;
        long passiveCooldownMs = Config.NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS.get() * 1000L;

        for (ServerLevel level : server.getAllLevels()) {
            List<AssistantEntity> npcs = level.getEntitiesOfClass(
                AssistantEntity.class, new AABB(-3e7, -3e7, -3e7, 3e7, 3e7, 3e7));

            for (AssistantEntity npc : npcs) {
                UUID npcId = npc.getUUID();
                Map<String, Boolean> current = LLMClient.scanSurroundingsRaw(level, npc);
                Set<String> currentNames = current.keySet();

                Set<String> previous = lastSeen.getOrDefault(npcId, Collections.emptySet());
                Set<String> newTypes = new HashSet<>(currentNames);
                newTypes.removeAll(previous);

                // Update lastSeen unconditionally — prevents cooldown expiry from re-firing stale entries
                lastSeen.put(npcId, new HashSet<>(currentNames));

                if (newTypes.isEmpty()) continue;

                Set<String> newHostile = new LinkedHashSet<>();
                Set<String> newPassive = new LinkedHashSet<>();
                for (String name : newTypes) {
                    if (Boolean.TRUE.equals(current.get(name))) newHostile.add(name);
                    else newPassive.add(name);
                }

                long now = System.currentTimeMillis();
                boolean fireHostile = !newHostile.isEmpty()
                    && (now - lastHostileMs.getOrDefault(npcId, 0L)) >= hostileCooldownMs;
                boolean firePassive = !newPassive.isEmpty()
                    && (now - lastPassiveMs.getOrDefault(npcId, 0L)) >= passiveCooldownMs;

                if (!fireHostile && !firePassive) continue;

                ServerPlayer target = findTarget(level, npc, npcId);
                if (target == null) continue;  // cooldown NOT updated — opportunity lost, timer doesn't advance

                String timeOfDay = LLMClient.timeOfDay(level.getDayTime());
                String weather = LLMClient.weather(level.isRaining(), level.isThundering());
                String surroundings = LLMClient.scanSurroundings(level, npc);
                Component nameComponent = npcNameComponent(npc);

                if (fireHostile) {
                    LLMClient.observe(server, target, nameComponent, npc.getRole(),
                        timeOfDay, weather, surroundings, buildWhatChanged(newHostile));
                    lastHostileMs.put(npcId, now);
                } else {
                    LLMClient.observe(server, target, nameComponent, npc.getRole(),
                        timeOfDay, weather, surroundings, buildWhatChanged(newPassive));
                    lastPassiveMs.put(npcId, now);
                }
            }
        }
    }

    public static void clearState(UUID npcId) {
        lastSeen.remove(npcId);
        lastHostileMs.remove(npcId);
        lastPassiveMs.remove(npcId);
    }

    private static ServerPlayer findTarget(ServerLevel level, AssistantEntity npc, UUID npcId) {
        double proximity = Config.COMMAND_PROXIMITY.get();
        AABB box = AABB.ofSize(npc.position(), proximity * 2, proximity * 2, proximity * 2);
        List<ServerPlayer> candidates = level.getEntitiesOfClass(ServerPlayer.class, box);

        ServerPlayer nearest = null;
        double nearestDistSq = Double.MAX_VALUE;
        for (ServerPlayer player : candidates) {
            String playerName = player.getGameProfile().getName();
            if (!ConversationMemory.getHistory(npcId, playerName).isEmpty()) {
                double distSq = npc.distanceToSqr(player);
                if (distSq < nearestDistSq) {
                    nearestDistSq = distSq;
                    nearest = player;
                }
            }
        }
        return nearest;
    }

    private static Component npcNameComponent(AssistantEntity npc) {
        return npc.getCustomName() != null
            ? npc.getCustomName()
            : Component.literal("Assistant");
    }

    private static String buildWhatChanged(Set<String> types) {
        List<String> names = new ArrayList<>(types);
        if (names.size() > 3) return "several creatures appeared nearby";
        if (names.size() == 1) return "a " + names.get(0) + " appeared nearby";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            if (i > 0) sb.append(i == names.size() - 1 ? " and " : ", ");
            sb.append("a ").append(names.get(i));
        }
        sb.append(" appeared nearby");
        return sb.toString();
    }
}
