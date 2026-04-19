# Proactive NPC Observations Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add a server-tick driven awareness loop so AssistantEntity NPCs proactively comment when new mobs appear in their surroundings, without being spoken to first.

**Architecture:** New `ObservationTicker` class registered on NeoForge's `ServerTickEvent.Post`; runs every 100 ticks, diffs scan results against per-NPC `lastSeen` sets, fires `OllamaClient.observe()` when a new hostile/passive entity type appears and the per-NPC cooldown has elapsed. `OllamaClient` gains `scanSurroundingsRaw()` (structured scan used by ticker) and `observe()` (proactive prompt); `scanSurroundings()` is refactored to call `scanSurroundingsRaw()` internally. Broadcast target: nearest player within `COMMAND_PROXIMITY` who has an existing `ConversationMemory` entry for the NPC.

**Tech Stack:** NeoForge 1.21.1 (`net.neoforged.neoforge.event.tick.ServerTickEvent`), Java 21, existing `Config` / `ConversationMemory` / `OllamaClient` patterns

---

## Files

| File | Change |
|------|--------|
| `src/main/java/io/github/senseidragon/dragontweaks/Config.java` | Add `NPC_OBSERVATIONS_ENABLED`, `NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS`, `NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS` |
| `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java` | Add `scanSurroundingsRaw()`, refactor `scanSurroundings()`, add `observe()` |
| `src/main/java/io/github/senseidragon/dragontweaks/ObservationTicker.java` | Create — tick handler, state maps, diff + fire logic |
| `src/main/java/io/github/senseidragon/dragontweaks/DragonTweaks.java` | Register `ObservationTicker::onServerTick` on NeoForge event bus |
| `src/main/java/io/github/senseidragon/dragontweaks/AssistantCommand.java` | Call `ObservationTicker.clearState(uuid)` in `deleteAll` and `deleteNearest` |

---

### Task 1: Add config values

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/Config.java`

- [ ] **Add three new config entries** after the `NPC_AWARENESS_CATEGORY` block and before `LLM_ENABLED`:

```java
public static final ModConfigSpec.BooleanValue NPC_OBSERVATIONS_ENABLED = BUILDER
        .comment("Enable proactive NPC observations — NPCs comment when new entities appear nearby")
        .define("npcObservationsEnabled", true);

public static final ModConfigSpec.IntValue NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS = BUILDER
        .comment("Minimum seconds between hostile-entity observations per NPC")
        .defineInRange("npcObservationHostileCooldownSeconds", 15, 5, 300);

public static final ModConfigSpec.IntValue NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS = BUILDER
        .comment("Minimum seconds between passive-entity observations per NPC")
        .defineInRange("npcObservationPassiveCooldownSeconds", 60, 15, 600);
```

- [ ] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/Config.java
git commit -m "feat: add proactive observation config values"
```

---

### Task 2: Add `scanSurroundingsRaw()` + refactor `scanSurroundings()`

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java`

- [ ] **Replace the existing `scanSurroundings()` method** (lines 74–101) with `scanSurroundingsRaw()` plus a new `scanSurroundings()` wrapper:

```java
static Map<String, Boolean> scanSurroundingsRaw(ServerLevel level, AssistantEntity npc) {
    double radius = Config.NPC_AWARENESS_RADIUS.get().doubleValue();
    AABB box = AABB.ofSize(npc.position(), radius * 2, radius * 2, radius * 2);
    String category = Config.NPC_AWARENESS_CATEGORY.get();

    Map<String, Boolean> result = new LinkedHashMap<>();
    for (Entity entity : level.getEntitiesOfClass(Entity.class, box,
            e -> !(e instanceof AssistantEntity) && !(e instanceof Player) && !(e instanceof ItemEntity))) {
        MobCategory mob = entity.getType().getCategory();
        boolean include = switch (category) {
            case "HOSTILE" -> mob == MobCategory.MONSTER;
            case "ALL"     -> mob != MobCategory.MISC;
            default        -> mob == MobCategory.CREATURE || mob == MobCategory.AMBIENT
                           || mob == MobCategory.WATER_CREATURE || mob == MobCategory.WATER_AMBIENT;
        };
        if (!include) continue;
        String name = entityDisplayName(entity);
        result.putIfAbsent(name, mob == MobCategory.MONSTER);
    }
    return result;
}

static String scanSurroundings(ServerLevel level, AssistantEntity npc) {
    Map<String, Boolean> raw = scanSurroundingsRaw(level, npc);
    if (raw.isEmpty()) return "nothing notable nearby";
    return String.join(", ", raw.keySet());
}
```

Note: `entityDisplayName()` (lines 103–107) stays unchanged below these methods.

- [ ] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java
git commit -m "refactor: extract scanSurroundingsRaw() from scanSurroundings()"
```

---

### Task 3: Add `observe()` to OllamaClient

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java`

- [ ] **Add `observe()` as a new public static method** after `query()` (at the end of the class, before the closing `}`):

```java
public static void observe(MinecraftServer server, ServerPlayer player, Component entityName,
                           String role, String timeOfDay, String weather, String surroundings,
                           String whatChanged) {
    ensureAlive();
    if (!Config.LLM_ENABLED.get()) return;

    String npcName = entityName.getString();
    String playerName = player.getGameProfile().getName();
    String systemPrompt = buildSystemPrompt(npcName, role, playerName, timeOfDay, weather, surroundings);
    String prompt = "You just noticed: " + whatChanged +
                    ". React in character in 1-2 short sentences. Address " + playerName + " directly.";

    JsonObject obj = new JsonObject();
    obj.addProperty("model", Config.LLM_MODEL.get());
    obj.addProperty("system", systemPrompt);
    obj.addProperty("prompt", prompt);
    obj.addProperty("stream", false);
    obj.addProperty("think", false);
    JsonObject options = new JsonObject();
    options.addProperty("num_predict", 100);
    obj.add("options", options);
    String requestBody = GSON.toJson(obj);

    HttpRequest request;
    try {
        request = HttpRequest.newBuilder()
            .uri(URI.create(Config.LLM_ENDPOINT.get()))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();
    } catch (IllegalArgumentException e) {
        DragonTweaks.LOGGER.error("[OllamaClient] observe() — invalid endpoint URI: {}", e.getMessage());
        return;
    }

    HTTP.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .orTimeout(Config.LLM_TIMEOUT_SECONDS.get(), TimeUnit.SECONDS)
        .thenApply(response -> parseResponse(response.body()))
        .thenAccept(reply -> server.execute(() ->
            player.sendSystemMessage(
                Component.literal("[").append(entityName).append("]: " + reply)
            )
        ))
        .exceptionally(ex -> {
            DragonTweaks.LOGGER.warn("[OllamaClient] observe() failed: {}", ex.getMessage());
            return null;
        });
}
```

- [ ] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java
git commit -m "feat: add OllamaClient.observe() for proactive NPC observations"
```

---

### Task 4: Build checkpoint

- [ ] **Build**

```bash
./gradlew build
```

Expected: `BUILD SUCCESSFUL`

**Do not proceed to Task 5 until this passes.** Tasks 1–3 are the risk items — `observe()` and `scanSurroundingsRaw()` must compile cleanly before tackling the ticker.

---

### Task 5: Create `ObservationTicker.java`

**Files:**
- Create: `src/main/java/io/github/senseidragon/dragontweaks/ObservationTicker.java`

- [ ] **Pre-check:** Confirm `net.neoforged.neoforge.event.tick.ServerTickEvent` exists in NeoForge 21.1.226 decompiled sources at `~/.gradle/caches/`. Verify `ServerTickEvent.Post` is an inner class and that `getServer()` returns `MinecraftServer`. Do not proceed if the class is missing — locate the correct tick event class first.

- [ ] **Create the file**

```java
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
                Map<String, Boolean> current = OllamaClient.scanSurroundingsRaw(level, npc);
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

                String timeOfDay = OllamaClient.timeOfDay(level.getDayTime());
                String weather = OllamaClient.weather(level.isRaining(), level.isThundering());
                String surroundings = OllamaClient.scanSurroundings(level, npc);
                Component nameComponent = npcNameComponent(npc);

                if (fireHostile) {
                    OllamaClient.observe(server, target, nameComponent, npc.getRole(),
                        timeOfDay, weather, surroundings, buildWhatChanged(newHostile));
                    lastHostileMs.put(npcId, now);
                } else {
                    OllamaClient.observe(server, target, nameComponent, npc.getRole(),
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
```

- [ ] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/ObservationTicker.java
git commit -m "feat: add ObservationTicker for proactive NPC surroundings observations"
```

---

### Task 6: Wire up and cleanup

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/DragonTweaks.java`
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/AssistantCommand.java`

- [ ] **Register `ObservationTicker` in `DragonTweaks.java`** — add one line in the constructor after the `ChatInterceptor` listener:

```java
NeoForge.EVENT_BUS.addListener(ObservationTicker::onServerTick);
```

The constructor should look like:

```java
public DragonTweaks(IEventBus modEventBus, ModContainer modContainer) {
    ModEntities.ENTITY_TYPES.register(modEventBus);
    modEventBus.addListener(this::commonSetup);
    modEventBus.addListener(Config::onLoad);
    modEventBus.addListener(ModEntities::onAttributeCreate);
    modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    NeoForge.EVENT_BUS.addListener(ChatInterceptor::onServerChat);
    NeoForge.EVENT_BUS.addListener(ObservationTicker::onServerTick);
    NeoForge.EVENT_BUS.addListener((ServerStartedEvent e) -> OllamaClient.warmup());
    NeoForge.EVENT_BUS.addListener((ServerStoppingEvent e) -> OllamaClient.shutdown());
}
```

- [ ] **Add `ObservationTicker.clearState()` call in `AssistantCommand.deleteAll()`** — inside the entity loop, after `ConversationMemory.clearAll()`:

```java
for (AssistantEntity entity : entities) {
    ConversationMemory.clearAll(entity.getUUID());
    ObservationTicker.clearState(entity.getUUID());
    entity.discard();
    count++;
}
```

- [ ] **Add `ObservationTicker.clearState()` call in `AssistantCommand.deleteNearest()`** — after `ConversationMemory.clearAll(nearest.getUUID())`:

```java
ConversationMemory.clearAll(nearest.getUUID());
ObservationTicker.clearState(nearest.getUUID());
nearest.discard();
```

- [ ] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/DragonTweaks.java
git add src/main/java/io/github/senseidragon/dragontweaks/AssistantCommand.java
git commit -m "feat: register ObservationTicker and wire cleanup on NPC deletion"
```

---

### Task 7: Build and validate

- [ ] **Build**

```bash
./gradlew build
```

Expected: `BUILD SUCCESSFUL`

- [ ] **In-game validation checklist**
  - Spawn Johnny ("scout"), have a conversation (so he has a `ConversationMemory` entry). Spawn a zombie nearby — Johnny should comment within 15 seconds.
  - Wait 14 seconds, spawn another zombie — Johnny should NOT comment yet (cooldown not elapsed). Wait the remaining second, spawn one more — comment fires.
  - Spawn Bessie ("ranch hand") with NO prior conversation. Zombie appears — Bessie says nothing (no qualifying player).
  - Passive: a pig wanders into Johnny's radius (or spawn one) — Johnny comments within 60 seconds.
  - An entity already present when Johnny spawned should NOT trigger any observation on first scan.
  - `/assistant delete Johnny` — no lingering state. Respawn Johnny — fresh entity, no history, no observations.
  - Set `npcObservationsEnabled = false` in config — no observations fire even when mobs appear.
