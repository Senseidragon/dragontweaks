package io.github.senseidragon.dragontweaks;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.*;

public class ObservationTicker {

    private static final Map<UUID, Set<String>> lastSeen = new HashMap<>();
    private static final Map<UUID, Long> lastHostileMs = new HashMap<>();
    private static final Map<UUID, Long> lastPassiveMs = new HashMap<>();
    // Tier 1 & 2 threat tracking — keyed by mob UUID
    private static final Map<UUID, UUID> lastMobTarget = new HashMap<>();
    private static final Map<UUID, Float> lastKnownHealth = new HashMap<>();
    private static final Map<UUID, Long> lastThreatAlertMs = new HashMap<>();

    // Per-NPC threat state: true = hostiles currently present, false = clear
    // Used to suppress repeat observations when hostile count increases (e.g. slime splits)
    private static final Map<UUID, Boolean> npcThreatState = new HashMap<>();
    private static final Map<UUID, Long> lastThreatStateChangeMs = new HashMap<>();
    private static final Map<UUID, Mob> lastMobRef = new HashMap<>();

    private static int tickCounter = 0;
    private static final int TICK_INTERVAL = 100;

    public static void onServerTick(ServerTickEvent.Post event) {
        if (++tickCounter % TICK_INTERVAL != 0) return;
        if (!Config.NPC_OBSERVATIONS_ENABLED.get()) return;

        MinecraftServer server = event.getServer();
        double radius = Config.NPC_AWARENESS_RADIUS.get().doubleValue();

        for (ServerLevel level : server.getAllLevels()) {
            Set<AssistantEntity> visited = new HashSet<>();
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.serverLevel() != level) continue;
                AABB box = AABB.ofSize(player.position(), radius * 2, radius * 2, radius * 2);
                for (AssistantEntity npc : level.getEntitiesOfClass(AssistantEntity.class, box)) {
                    if (visited.add(npc)) checkThreats(level, npc);
                }
            }
        }

        long hostileCooldownMs = Config.NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS.get() * 1000L;
        long passiveCooldownMs = Config.NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS.get() * 1000L;

        for (ServerLevel level : server.getAllLevels()) {
            Set<AssistantEntity> npcSet = new HashSet<>();
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.serverLevel() != level) continue;
                AABB box = AABB.ofSize(player.position(), radius * 2, radius * 2, radius * 2);
                npcSet.addAll(level.getEntitiesOfClass(AssistantEntity.class, box));
            }

            for (AssistantEntity npc : npcSet) {
                UUID npcId = npc.getUUID();
                Map<String, Boolean> current = LLMClient.scanSurroundingsRaw(level, npc);
                Set<String> currentNames = current.keySet();

                Set<String> previous = lastSeen.getOrDefault(npcId, Collections.emptySet());
                Set<String> newTypes = new HashSet<>(currentNames);
                newTypes.removeAll(previous);

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
                if (target == null) continue;

                String timeOfDay = LLMClient.timeOfDay(level.getDayTime());
                String weather = LLMClient.weather(level.isRaining(), level.isThundering());
                String surroundings = LLMClient.scanSurroundings(level, npc);
                Component nameComponent = npcNameComponent(npc);

                if (fireHostile) {
                    LLMClient.observe(server, target, nameComponent, npc.getRole(),
                        timeOfDay, weather, surroundings, buildWhatChanged(newHostile), npcId);
                    lastHostileMs.put(npcId, now);
                } else {
                    LLMClient.observe(server, target, nameComponent, npc.getRole(),
                        timeOfDay, weather, surroundings, buildWhatChanged(newPassive), npcId);
                    lastPassiveMs.put(npcId, now);
                }
            }
        }
    }

    private static void checkThreats(ServerLevel level, AssistantEntity npc) {
        double radius = Config.NPC_AWARENESS_RADIUS.get().doubleValue();
        AABB box = AABB.ofSize(npc.position(), radius * 2, radius * 2, radius * 2);

        List<Mob> currentMobs = level.getEntitiesOfClass(Mob.class, box, e -> !(e instanceof AssistantEntity));
        Set<UUID> currentMobIds = new HashSet<>();
        for (Mob mob : currentMobs) currentMobIds.add(mob.getUUID());

        UUID npcId = npc.getUUID();
        long now = System.currentTimeMillis();

        // Determine whether any hostile mob is actively targeting something
        boolean anyActiveThreats = false;

        for (Mob mob : currentMobs) {
            LivingEntity tgt = mob.getTarget();
            if (tgt == null) continue;
            anyActiveThreats = true;
            UUID tgtId = tgt.getUUID();
            lastKnownHealth.put(tgtId, tgt.getHealth());
            lastMobTarget.put(mob.getUUID(), tgtId);
            lastMobRef.put(mob.getUUID(), mob);
        }
        // Threat state change model — fire once on flip, not on every new mob UUID
        boolean wasPresent = npcThreatState.getOrDefault(npcId, false);

       if (anyActiveThreats && !wasPresent) {
            // Threat appeared — find the most dangerous mob to describe
            npcThreatState.put(npcId, true);
            lastThreatStateChangeMs.put(npcId, now);

            Mob representative = null;
            for (Mob mob : currentMobs) {
                if (mob.getTarget() != null) { representative = mob; break; }
            }
            if (representative != null) {
                String aggressorType = entityTypeName(representative);
                String targetDesc = describeTarget(representative.getTarget());
                LLMClient.observe(npc, "a " + aggressorType + " is targeting " + targetDesc + "!", level);
            }

        } else if (!anyActiveThreats && wasPresent) {
            // Threat cleared
            npcThreatState.put(npcId, false);
            lastThreatStateChangeMs.put(npcId, now);

            // 30% chance of all-clear comment to avoid it being too chatty
            if (Math.random() < 0.3) {
                LLMClient.observe(npc, "the threat nearby seems to have passed.", level);
            }
        }
        // If threat state unchanged (still present or still clear), do nothing —
        // this is the fix for slime splits: new child slime UUIDs don't trigger fresh alerts

        // Clean up tracking for mobs that have left the area
        Set<UUID> previouslyTracked = new HashSet<>(lastMobTarget.keySet());
        for (UUID mobId : previouslyTracked) {
            if (!currentMobIds.contains(mobId)) {
                Mob deadMob = lastMobRef.get(mobId);
                String reason = (deadMob != null && deadMob.isDeadOrDying())
                        ? "was dealt with"
                        : "seems to have moved on";
                lastMobTarget.remove(mobId);
                lastMobRef.remove(mobId);
                if (npcThreatState.getOrDefault(npcId, false) && Math.random() < 0.3) {
                    LLMClient.observe(npc, "the threat nearby " + reason + ".", level);
                }
            }
        }
    }

    private static String describeTarget(LivingEntity target) {
        if (target instanceof ServerPlayer player) {
            return player.getGameProfile().getName();
        }
        return "a " + entityTypeName(target);
    }

    private static String entityTypeName(net.minecraft.world.entity.Entity entity) {
        String id = entity.getType().getDescriptionId();
        String[] parts = id.split("\\.");
        return parts[parts.length - 1].replace("_", " ");
    }

    public static void clearState(UUID npcId) {
        lastSeen.remove(npcId);
        lastHostileMs.remove(npcId);
        lastPassiveMs.remove(npcId);
        npcThreatState.remove(npcId);
        lastThreatStateChangeMs.remove(npcId);
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
