# Async Ollama Integration Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Wire the existing ChatInterceptor pipeline to Ollama via a fully non-blocking `sendAsync()` + `CompletableFuture` chain, with results queued back to the server thread via `server.execute()`.

**Architecture:** A new `OllamaClient` class owns the single-thread executor, the `HttpClient` (bound to that executor), and the async call chain. `ChatInterceptor` computes context (time of day, weather) on the game thread then hands off to `OllamaClient.query()`. `DragonTweaks` registers a `ServerStoppingEvent` listener to call `OllamaClient.shutdown()`.

**Tech Stack:** Java 21 (`java.net.http.HttpClient`, `CompletableFuture`), Gson (bundled with Minecraft), NeoForge 21.1.226, JUnit 5 (test scope only).

---

## File Map

| Action | Path | Responsibility |
|--------|------|---------------|
| Create | `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java` | Executor, HttpClient, async query chain, pure helpers |
| Create | `src/test/java/io/github/senseidragon/dragontweaks/OllamaClientTest.java` | Unit tests for pure helpers |
| Modify | `src/main/java/io/github/senseidragon/dragontweaks/ChatInterceptor.java` | Capture context, delegate to OllamaClient |
| Modify | `src/main/java/io/github/senseidragon/dragontweaks/DragonTweaks.java` | Register ServerStoppingEvent → OllamaClient.shutdown() |
| Modify | `build.gradle` | Add JUnit 5 to testImplementation |

---

## Task 1: Add JUnit 5 and write failing unit tests

**Files:**
- Modify: `build.gradle`
- Create: `src/test/java/io/github/senseidragon/dragontweaks/OllamaClientTest.java`

- [ ] **Step 1: Add JUnit 5 to build.gradle**

Open `build.gradle`. Inside the existing `dependencies { }` block, add after the last existing dependency line:

```groovy
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.3'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
```

Also add this block anywhere after the `dependencies { }` block (e.g., before the `publishing { }` block):

```groovy
test {
    useJUnitPlatform()
}
```

- [ ] **Step 2: Create the test directory and write failing tests**

Create `src/test/java/io/github/senseidragon/dragontweaks/OllamaClientTest.java`:

```java
package io.github.senseidragon.dragontweaks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OllamaClientTest {

    @Test
    void timeOfDay_morning_boundaries() {
        assertEquals("MORNING", OllamaClient.timeOfDay(0));
        assertEquals("MORNING", OllamaClient.timeOfDay(5999));
    }

    @Test
    void timeOfDay_afternoon_boundaries() {
        assertEquals("AFTERNOON", OllamaClient.timeOfDay(6000));
        assertEquals("AFTERNOON", OllamaClient.timeOfDay(11999));
    }

    @Test
    void timeOfDay_evening_boundaries() {
        assertEquals("EVENING", OllamaClient.timeOfDay(12000));
        assertEquals("EVENING", OllamaClient.timeOfDay(17999));
    }

    @Test
    void timeOfDay_night_boundaries() {
        assertEquals("NIGHT", OllamaClient.timeOfDay(18000));
        assertEquals("NIGHT", OllamaClient.timeOfDay(23999));
    }

    @Test
    void timeOfDay_wraps_at_24000() {
        assertEquals("MORNING", OllamaClient.timeOfDay(24000));
        assertEquals("MORNING", OllamaClient.timeOfDay(48000));
    }

    @Test
    void weather_clear() {
        assertEquals("CLEAR", OllamaClient.weather(false, false));
    }

    @Test
    void weather_raining() {
        assertEquals("RAINING", OllamaClient.weather(true, false));
    }

    @Test
    void weather_thunderstorm_takes_priority() {
        assertEquals("THUNDERSTORM", OllamaClient.weather(true, true));
        assertEquals("THUNDERSTORM", OllamaClient.weather(false, true));
    }

    @Test
    void parseResponse_extracts_response_field() {
        String json = "{\"model\":\"gemma4:26b\",\"response\":\"Hello there!\",\"done\":true}";
        assertEquals("Hello there!", OllamaClient.parseResponse(json));
    }

    @Test
    void parseResponse_handles_embedded_quotes() {
        String json = "{\"response\":\"He said \\\"hi\\\".\",\"done\":true}";
        assertEquals("He said \"hi\".", OllamaClient.parseResponse(json));
    }
}
```

- [ ] **Step 3: Run tests — confirm they fail to compile (class not found)**

```
./gradlew test
```

Expected: compilation error — `cannot find symbol: OllamaClient`. This is correct TDD state.

> **If tests fail with MC/NeoForge classes not found** (e.g., `cannot find symbol: Component`), NeoForge ModDevGradle isn't putting MC on the test classpath. Fix: add this block inside the existing `neoForge { }` block in `build.gradle`:
> ```groovy
> unitTest {
>     enable()
>     testedMod = mods."${mod_id}"
> }
> ```
> Then re-run. This tells ModDevGradle to include the game dependencies in the `test` source set.

---

## Task 2: Create OllamaClient with pure helpers (make tests pass)

**Files:**
- Create: `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java`

- [ ] **Step 1: Create OllamaClient.java with helpers and infrastructure (no query() yet)**

```java
package io.github.senseidragon.dragontweaks;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OllamaClient {

    private static final ExecutorService EXECUTOR =
        Executors.newSingleThreadExecutor(r -> new Thread(r, "dragontweaks-llm"));

    private static final HttpClient HTTP = HttpClient.newBuilder()
        .executor(EXECUTOR)
        .build();

    private static final Gson GSON = new Gson();

    private static final String SYSTEM_PROMPT =
        "You are a helpful assistant NPC in a Minecraft colony. " +
        "You are aware of the time of day and weather. " +
        "Respond in character, briefly (2-3 sentences).";

    static String timeOfDay(long dayTime) {
        long t = dayTime % 24000;
        if (t < 6000) return "MORNING";
        if (t < 12000) return "AFTERNOON";
        if (t < 18000) return "EVENING";
        return "NIGHT";
    }

    static String weather(boolean raining, boolean thundering) {
        if (thundering) return "THUNDERSTORM";
        if (raining) return "RAINING";
        return "CLEAR";
    }

    static String parseResponse(String json) {
        return JsonParser.parseString(json)
            .getAsJsonObject()
            .get("response")
            .getAsString();
    }

    private static String buildRequestBody(String model, String prompt) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", model);
        obj.addProperty("system", SYSTEM_PROMPT);
        obj.addProperty("prompt", prompt);
        obj.addProperty("stream", false);
        return GSON.toJson(obj);
    }

    static void sendFallback(ServerPlayer player, Component entityName) {
        player.sendSystemMessage(
            Component.literal("[").append(entityName)
                .append("]: My thoughts escape me for the moment.")
        );
    }

    public static void shutdown() {
        EXECUTOR.shutdownNow();
    }
}
```

- [ ] **Step 2: Run the unit tests — confirm they pass**

```
./gradlew test
```

Expected output (all 10 tests):
```
OllamaClientTest > timeOfDay_morning_boundaries() PASSED
OllamaClientTest > timeOfDay_afternoon_boundaries() PASSED
OllamaClientTest > timeOfDay_evening_boundaries() PASSED
OllamaClientTest > timeOfDay_night_boundaries() PASSED
OllamaClientTest > timeOfDay_wraps_at_24000() PASSED
OllamaClientTest > weather_clear() PASSED
OllamaClientTest > weather_raining() PASSED
OllamaClientTest > weather_thunderstorm_takes_priority() PASSED
OllamaClientTest > parseResponse_extracts_response_field() PASSED
OllamaClientTest > parseResponse_handles_embedded_quotes() PASSED
```

If `parseResponse` tests fail due to Gson not on the test classpath, add to `build.gradle` `testImplementation`:
```groovy
testImplementation 'com.google.code.gson:gson:2.10.1'
```
Then re-run.

- [ ] **Step 3: Commit**

```bash
git add build.gradle \
    src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java \
    src/test/java/io/github/senseidragon/dragontweaks/OllamaClientTest.java
git commit -m "feat: add OllamaClient with pure helpers and unit tests"
```

---

## Task 3: Implement async query() method

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java`

No automated test is possible for the full async chain without mocking `HttpClient`. Correctness is verified in Task 6 via integration smoke test.

- [ ] **Step 1: Add imports and the query() method to OllamaClient**

Add these imports to the top of `OllamaClient.java` (merge with existing imports):

```java
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
```

Add the `query()` method to `OllamaClient` after `shutdown()`:

```java
public static void query(MinecraftServer server, ServerPlayer player,
                         Component entityName, String message,
                         String timeOfDay, String weather) {
    if (!Config.LLM_ENABLED.get()) {
        server.execute(() -> sendFallback(player, entityName));
        return;
    }

    String prompt = "Player said: " + message + "\nTime: " + timeOfDay + "\nWeather: " + weather;
    String requestBody = buildRequestBody(Config.LLM_MODEL.get(), prompt);

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
}
```

Also add `MinecraftServer` to the imports:
```java
import net.minecraft.server.MinecraftServer;
```

- [ ] **Step 2: Confirm it compiles**

```
./gradlew compileJava
```

Expected: `BUILD SUCCESSFUL`. Fix any import errors if they appear.

- [ ] **Step 3: Run unit tests to confirm nothing regressed**

```
./gradlew test
```

Expected: all 10 tests PASSED.

- [ ] **Step 4: Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java
git commit -m "feat: implement async query() with CompletableFuture chain"
```

---

## Task 4: Modify ChatInterceptor to call OllamaClient.query()

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/ChatInterceptor.java`

- [ ] **Step 1: Replace ChatInterceptor.java entirely**

```java
package io.github.senseidragon.dragontweaks;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.List;

public class ChatInterceptor {

    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        ServerLevel serverLevel = (ServerLevel) player.level();

        double range = Config.COMMAND_PROXIMITY.get();
        AABB searchBox = AABB.ofSize(player.position(), range * 2, range * 2, range * 2);
        List<AssistantEntity> candidates = serverLevel.getEntitiesOfClass(AssistantEntity.class, searchBox);

        if (candidates.isEmpty()) return;

        AssistantEntity nearest = null;
        double nearestDistSq = range * range;
        for (AssistantEntity candidate : candidates) {
            double distSq = player.distanceToSqr(candidate);
            if (distSq <= nearestDistSq) {
                nearestDistSq = distSq;
                nearest = candidate;
            }
        }

        if (nearest == null) return;

        String rawMessage = event.getRawText();
        event.setCanceled(true);

        Component entityName = nearest.getCustomName() != null
            ? nearest.getCustomName()
            : Component.literal("Assistant [PoC]");

        player.sendSystemMessage(
            Component.literal("[").append(entityName).append("]: Hmm...")
        );

        DragonTweaks.LOGGER.info("[ChatInterceptor] {} -> {}: {}",
            player.getGameProfile().getName(),
            entityName.getString(),
            rawMessage);

        MinecraftServer server = player.getServer();
        if (server == null) return;

        String timeOfDay = OllamaClient.timeOfDay(serverLevel.getDayTime());
        String weather = OllamaClient.weather(serverLevel.isRaining(), serverLevel.isThundering());

        OllamaClient.query(server, player, entityName, rawMessage, timeOfDay, weather);
    }
}
```

- [ ] **Step 2: Compile**

```
./gradlew compileJava
```

Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 3: Run unit tests**

```
./gradlew test
```

Expected: all 10 tests PASSED.

- [ ] **Step 4: Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/ChatInterceptor.java
git commit -m "feat: wire ChatInterceptor to OllamaClient async query"
```

---

## Task 5: Register shutdown hook in DragonTweaks

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/DragonTweaks.java`

- [ ] **Step 1: Replace DragonTweaks.java entirely**

```java
package io.github.senseidragon.dragontweaks;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import org.slf4j.Logger;

@Mod(DragonTweaks.MODID)
public class DragonTweaks {
    public static final String MODID = "dragontweaks";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DragonTweaks(IEventBus modEventBus, ModContainer modContainer) {
        ModEntities.ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(Config::onLoad);
        modEventBus.addListener(ModEntities::onAttributeCreate);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.addListener(ChatInterceptor::onServerChat);
        NeoForge.EVENT_BUS.addListener((ServerStoppingEvent e) -> OllamaClient.shutdown());
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("DragonTweaks loaded — LLM endpoint: {}", Config.LLM_ENDPOINT.get());
    }
}
```

> **If `ServerStoppingEvent` import fails:** Replace the import and listener with:
> ```java
> import net.neoforged.fml.event.lifecycle.FMLServerStoppingEvent;
> // in constructor:
> modEventBus.addListener((FMLServerStoppingEvent e) -> OllamaClient.shutdown());
> ```

- [ ] **Step 2: Compile**

```
./gradlew compileJava
```

Expected: `BUILD SUCCESSFUL`. If `ServerStoppingEvent` not found, apply the fallback import above.

- [ ] **Step 3: Full build including tests**

```
./gradlew build
```

Expected: `BUILD SUCCESSFUL`, all 10 unit tests pass, JAR produced in `build/libs/`.

- [ ] **Step 4: Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/DragonTweaks.java
git commit -m "feat: register OllamaClient shutdown on ServerStoppingEvent"
```

---

## Task 6: Integration smoke test

No automated game test. Verify manually using the running server.

- [ ] **Step 1: Start the server**

```
./gradlew runServer
```

Watch for this line in logs confirming LLM endpoint is loaded:
```
DragonTweaks loaded — LLM endpoint: http://SENSEI:11434/api/generate
```

- [ ] **Step 2: Spawn an AssistantEntity and stand within range**

In-game: `/summon dragontweaks:assistant` then stand within 10 blocks.

- [ ] **Step 3: Chat and verify the happy path**

Type any message in chat. Expected sequence in chat:
1. `[Assistant [PoC]]: Hmm...` — immediate, on game thread
2. `[Assistant [PoC]]: <Ollama response>` — arrives after LLM latency, 2-3 sentences

Expected in server logs:
```
[ChatInterceptor] <player> -> Assistant [PoC]: <your message>
```
No `[OllamaClient] LLM request failed` log line.

- [ ] **Step 4: Verify the fallback path**

Set `llmEnabled = false` in `config/dragontweaks-common.toml`, restart the server, chat near the entity.

Expected sequence:
1. `[Assistant [PoC]]: Hmm...`
2. `[Assistant [PoC]]: My thoughts escape me for the moment.`

- [ ] **Step 5: Verify timeout fallback**

Set `llmTimeoutSeconds = 1` in config, restart, chat. Ollama at `gemma4:26b` will not respond in 1 second.

Expected sequence:
1. `[Assistant [PoC]]: Hmm...`
2. `[Assistant [PoC]]: My thoughts escape me for the moment.`

Expected in logs:
```
[OllamaClient] LLM request failed: ...
```

- [ ] **Step 6: Restore config to defaults and do final commit**

Restore `llmEnabled = true`, `llmTimeoutSeconds = 60`.

```bash
git add -A
git commit -m "feat: async Ollama integration complete — Session 4 PoC"
```
