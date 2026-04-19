# NPC Surroundings Awareness Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Inject a surroundings summary (nearby mobs/animals) into the NPC's LLM system prompt so it can answer questions like "what's around you?"

**Architecture:** Entity scan runs per-target NPC at query time in ChatInterceptor, results formatted as a compact string, passed through to OllamaClient.buildSystemPrompt() as a new parameter. Always present — populated or "nothing notable nearby".

**Tech Stack:** NeoForge 1.21.1, Java 21, ModConfigSpec, Minecraft server-side entity API

---

## Files

| File | Change |
|------|--------|
| `src/main/java/io/github/senseidragon/dragontweaks/Config.java` | Add `NPC_AWARENESS_RADIUS` (double) and `NPC_AWARENESS_CATEGORY` (string enum) |
| `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java` | Add `scanSurroundings()`, `entityDisplayName()`, update `buildSystemPrompt()` and `query()` |
| `src/main/java/io/github/senseidragon/dragontweaks/ChatInterceptor.java` | Call `scanSurroundings()` per target, pass result to `query()` |

---

### Task 1: Add config values

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/Config.java`

- [x] **Add NPC_AWARENESS_RADIUS and NPC_AWARENESS_CATEGORY** after the existing `COMMAND_PROXIMITY` entry:

```java
import java.util.List;  // add to imports if not present

public static final ModConfigSpec.DoubleValue NPC_AWARENESS_RADIUS = BUILDER
        .comment("Radius in blocks around an NPC within which it can perceive nearby entities")
        .defineInRange("npcAwarenessRadius", 16.0, 4.0, 64.0);

public static final ModConfigSpec.ConfigValue<String> NPC_AWARENESS_CATEGORY = BUILDER
        .comment("Which entity categories the NPC is aware of: PASSIVE, HOSTILE, or ALL")
        .defineInList("npcAwarenessCategory", "PASSIVE", List.of("PASSIVE", "HOSTILE", "ALL"));
```

- [x] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/Config.java
git commit -m "feat: add NPC awareness radius and category config"
```

---

### Task 2: Add surroundings scan and prompt injection to OllamaClient

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java`

- [x] **Add imports** at the top of OllamaClient.java (after existing imports):

```java
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import java.util.LinkedHashMap;
import java.util.Map;
```

- [x] **Add `scanSurroundings()` and `entityDisplayName()`** as static methods after the existing `weather()` method:

```java
static String scanSurroundings(ServerLevel level, AssistantEntity npc) {
    double radius = Config.NPC_AWARENESS_RADIUS.get();
    AABB box = AABB.ofSize(npc.position(), radius * 2, radius * 2, radius * 2);
    String category = Config.NPC_AWARENESS_CATEGORY.get();

    Map<String, Integer> counts = new LinkedHashMap<>();
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
        counts.merge(entityDisplayName(entity), 1, Integer::sum);
    }

    if (counts.isEmpty()) return "nothing notable nearby";

    StringBuilder sb = new StringBuilder();
    counts.forEach((name, count) -> {
        if (!sb.isEmpty()) sb.append(", ");
        sb.append(count).append(" ").append(name);
    });
    return sb.toString();
}

private static String entityDisplayName(Entity entity) {
    String id = entity.getType().getDescriptionId(); // e.g. "entity.minecraft.pig"
    String[] parts = id.split("\\.");
    return parts[parts.length - 1].replace("_", " ");
}
```

- [x] **Update `buildSystemPrompt()`** — add `String surroundings` parameter and append the line:

Replace the existing method signature and last line:

```java
private static String buildSystemPrompt(String npcName, String role, String playerName,
                                         String timeOfDay, String weather, String surroundings) {
    return "You are " + npcName + ", a " + role + " in a medieval village.\n" +
           "You live in a world where creepers explode, endermen are unsettling tall dark figures that dislike eye contact, " +
           "zombies and skeletons roam at night, and emeralds are common currency. " +
           "Farming, mining, and crafting are everyday activities. " +
           "Refer to all of these as natural parts of your world — they are real to you. " +
           "Never reference \"the game\", \"players\", \"code\", or anything that breaks the sense that this is a real place you live in.\n" +
           "The person speaking to you is named " + playerName + ".\n" +
           "It is currently " + timeOfDay + " and the weather is " + weather + ". " +
           "You are aware of your surroundings but only mention them when it feels natural.\n" +
           "Nearby you can see: " + surroundings + ".\n" +
           "Respond as " + npcName + " in 1-2 short sentences. Never break character. Never say you are an AI.";
}
```

- [x] **Update `buildRequestBody()`** — add `String surroundings` parameter and pass it through:

```java
private static String buildRequestBody(String model, String message, String npcName, String role,
                                        String playerName, String timeOfDay, String weather,
                                        String surroundings) {
    JsonObject obj = new JsonObject();
    obj.addProperty("model", model);
    obj.addProperty("system", buildSystemPrompt(npcName, role, playerName, timeOfDay, weather, surroundings));
    obj.addProperty("prompt", message);
    obj.addProperty("stream", false);
    obj.addProperty("think", false);
    JsonObject options = new JsonObject();
    options.addProperty("num_predict", 100);
    obj.add("options", options);
    return GSON.toJson(obj);
}
```

- [x] **Update `query()`** — add `String surroundings` parameter and pass to `buildRequestBody()`:

```java
public static void query(MinecraftServer server, ServerPlayer player,
                         Component entityName, String message, String role,
                         String timeOfDay, String weather, String surroundings) {
    if (!Config.LLM_ENABLED.get()) {
        server.execute(() -> sendFallback(player, entityName));
        return;
    }

    String npcName = entityName.getString();
    String playerName = player.getGameProfile().getName();
    String requestBody = buildRequestBody(Config.LLM_MODEL.get(), message, npcName, role,
                                          playerName, timeOfDay, weather, surroundings);

    HttpRequest request;
    try {
        request = HttpRequest.newBuilder()
            .uri(URI.create(Config.LLM_ENDPOINT.get()))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();
    } catch (IllegalArgumentException e) {
        DragonTweaks.LOGGER.error("[OllamaClient] Invalid endpoint URI: {}", e.getMessage());
        server.execute(() -> sendFallback(player, entityName));
        return;
    }

    try {
        HTTP.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .orTimeout(Config.LLM_TIMEOUT_SECONDS.get(), TimeUnit.SECONDS)
            .thenApply(response -> parseResponse(response.body()))
            .thenAccept(reply -> server.execute(() ->
                player.sendSystemMessage(
                    Component.literal("[").append(entityName).append("]: " + reply)
                )
            ))
            .exceptionally(ex -> {
                DragonTweaks.LOGGER.warn("[OllamaClient] LLM request failed: {}", ex.getMessage());
                server.execute(() -> sendFallback(player, entityName));
                return null;
            });
    } catch (Exception e) {
        DragonTweaks.LOGGER.error("[OllamaClient] Failed to dispatch async request: {}", e.getMessage());
        server.execute(() -> sendFallback(player, entityName));
    }
}
```

- [x] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java
git commit -m "feat: add surroundings scan and prompt injection to OllamaClient"
```

---

### Task 3: Wire surroundings scan into ChatInterceptor

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/ChatInterceptor.java`

- [x] **Inside the `for (AssistantEntity target : targets)` loop**, add the scan call and pass `surroundings` to `query()`. Replace the existing loop body:

```java
for (AssistantEntity target : targets) {
    Component entityName = target.getCustomName() != null
        ? target.getCustomName()
        : Component.literal("Assistant [PoC]");

    player.sendSystemMessage(
        Component.literal("[").append(entityName).append("]: Hmm...")
    );

    DragonTweaks.LOGGER.info("[ChatInterceptor] {} -> {}: {}",
        player.getGameProfile().getName(),
        entityName.getString(),
        rawMessage);

    String surroundings = OllamaClient.scanSurroundings(serverLevel, target);
    OllamaClient.query(server, player, entityName, rawMessage, target.getRole(), timeOfDay, weather, surroundings);
}
```

- [x] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/ChatInterceptor.java
git commit -m "feat: pass surroundings context to OllamaClient per NPC"
```

---

### Task 4: Build and verify

- [x] **Build**

```bash
./gradlew build
```

Expected: `BUILD SUCCESSFUL`

- [x] **In-game validation checklist**
  - Spawn an NPC near some pigs/chickens. Ask "what can you see around you?" — NPC should mention the animals.
  - Clear the area. Ask again — NPC should respond with empty-area flavor, no hallucinated animals.
  - Set `npcAwarenessCategory = HOSTILE` in config. Spawn a zombie nearby. Ask — NPC should mention the zombie, not the pigs.
  - Confirm prompts not forced — ask unrelated question; NPC should not gratuitously list surroundings every response.

---

**Status: COMPLETE (2026-04-18, Session 7)**  
Note: `NPC_AWARENESS_RADIUS` was corrected from `DoubleValue` to `IntValue` during implementation (NeoForge config validation error). The `query()` signature shown above is outdated — Sessions 9–10 added `npcId`/`playerName` parameters for conversation memory, but surroundings is fully wired through.
