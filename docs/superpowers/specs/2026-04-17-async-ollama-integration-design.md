# Async Ollama Integration — Design Spec
**Date:** 2026-04-17  
**Session:** 4 — Async Ollama Integration  
**Status:** Approved

---

## Overview

Wire the existing `ChatInterceptor` pipeline to a real Ollama LLM endpoint using a fully non-blocking async pattern. No thread ever blocks waiting for Ollama. This PoC validates the exact CompletableFuture + `server.execute()` pattern that will survive into Phase 1 production.

---

## Hard Constraints

1. Nothing blocks the main game thread. Ever.
2. All network I/O is async via `HttpClient.sendAsync()`.
3. Results queue back to the server thread via `server.execute()` before any game interaction.
4. No new Gradle dependencies. Java 21 stdlib + Gson (bundled with Minecraft) only.
5. No conversation history, no streaming, no retry logic.

---

## Architecture

### Components

**`OllamaClient.java`** (new)  
Owns the async executor, `HttpClient`, and the full request/response chain.

**`ChatInterceptor.java`** (modified)  
After sending "Hmm...", captures `server`, `timeOfDay`, `weather`, then calls `OllamaClient.query()`.

**`DragonTweaks.java`** (modified)  
Registers `ServerStoppingEvent` listener to call `OllamaClient.shutdown()`.

---

## Data Flow

```
Game thread (ServerChatEvent)
  │
  ├─ Cancel event
  ├─ Send "Hmm..." to player          ← synchronous, preserved from prior session
  ├─ Capture: server, player, entityName, rawMessage, timeOfDay, weather
  └─ OllamaClient.query(...)
        │
        └─ httpClient.sendAsync(request, BodyHandlers.ofString())
              .orTimeout(llmTimeoutSeconds, SECONDS)
              .thenApply(response -> parseResponse(response.body()))
              .thenAccept(reply ->
                  server.execute(() -> player.sendSystemMessage(...))  ← back on game thread
              )
              .exceptionally(ex ->
                  server.execute(() -> sendFallback(...))              ← back on game thread
              )

dragontweaks-llm thread (single-thread executor, bound to HttpClient)
  └─ Runs CompletableFuture stages (thenApply, thenAccept)
  └─ Timeout fires on JDK timer thread → exceptionally() → server.execute()
```

---

## `OllamaClient` Internals

### Executor

```java
private static final ExecutorService EXECUTOR =
    Executors.newSingleThreadExecutor(r -> new Thread(r, "dragontweaks-llm"));
```

Single-thread: serializes requests, bounds concurrency, named for thread-dump visibility.

### HttpClient

```java
private static final HttpClient HTTP = HttpClient.newBuilder()
    .executor(EXECUTOR)
    .build();
```

Executor is bound at construction — NOT passed to `sendAsync()` (no such overload exists). Effect: callback stages run on `dragontweaks-llm`.

### Timeout

`CompletableFuture.orTimeout(llmTimeoutSeconds, TimeUnit.SECONDS)` only. No redundant request-level timeout. On expiry, `TimeoutException` flows into `exceptionally()`.

### Request JSON

```json
{
  "model": "<Config.LLM_MODEL>",
  "system": "You are a helpful assistant NPC in a Minecraft colony. You are aware of the time of day and weather. Respond in character, briefly (2-3 sentences).",
  "prompt": "Player said: <rawMessage>\nTime: <MORNING|AFTERNOON|EVENING|NIGHT>\nWeather: <CLEAR|RAINING|THUNDERSTORM>",
  "stream": false
}
```

Built with `Gson` (bundled). Player message is property-escaped by Gson — no injection risk.

### Response JSON

```json
{ "response": "...", "done": true, ... }
```

Parse: `JsonParser.parseString(body).getAsJsonObject().get("response").getAsString()`

### Fallback

On any exception (timeout, network error, parse failure, bad URI):
```
"[<entityName>]: My thoughts escape me for the moment."
```
Sent via `server.execute()` on the game thread. No exception is re-thrown.

---

## Context Payload

Computed on the game thread before handing off:

| Field | Source | Values |
|---|---|---|
| `timeOfDay` | `serverLevel.getDayTime() % 24000` | MORNING (0–5999), AFTERNOON (6000–11999), EVENING (12000–17999), NIGHT (18000–23999) |
| `weather` | `serverLevel.isThundering()` / `isRaining()` | THUNDERSTORM, RAINING, CLEAR |

---

## Server Reference

`MinecraftServer server = player.getServer()` — called on the game thread, null-checked defensively. Passed into the async lambda via closure. `server.execute(Runnable)` is thread-safe.

---

## Lifecycle

`OllamaClient.shutdown()` calls `EXECUTOR.shutdownNow()`.  
Triggered by `ServerStoppingEvent` registered on `NeoForge.EVENT_BUS` in `DragonTweaks` constructor.

**Fallback import if `ServerStoppingEvent` not found:** `net.neoforged.fml.event.lifecycle.FMLServerStoppingEvent` (build-time check only).

---

## Config Values Used (all pre-existing)

| Key | Type | Default |
|---|---|---|
| `llmEnabled` | `BooleanValue` | `true` |
| `llmEndpoint` | `ConfigValue<String>` | `http://SENSEI:11434/api/generate` |
| `llmModel` | `ConfigValue<String>` | `gemma4:26b` |
| `llmTimeoutSeconds` | `IntValue` | `60` |

If `llmEnabled` is false, `query()` immediately queues the fallback on the game thread and returns — no HTTP call is made.

---

## External Classes Verified

| Class | Source | Status |
|---|---|---|
| `java.net.http.HttpClient` | JDK 11+ | Confirmed |
| `java.net.http.HttpRequest` | JDK 11+ | Confirmed |
| `java.net.http.HttpResponse.BodyHandlers` | JDK 11+ | Confirmed |
| `java.util.concurrent.CompletableFuture.orTimeout()` | Java 9+ | Confirmed |
| `com.google.gson.Gson` / `JsonObject` / `JsonParser` | MC bundled | Confirmed |
| `MinecraftServer.execute(Runnable)` | MC 1.21.1 | Confirmed |
| `ServerPlayer.getServer()` | MC 1.21.1 | Confirmed — `@Nullable` |
| `ServerLevel.getDayTime()` | MC 1.21.1 | Confirmed — returns `long` |
| `ServerLevel.isRaining()` / `isThundering()` | MC 1.21.1 | Confirmed |
| `net.neoforged.neoforge.event.server.ServerStoppingEvent` | NeoForge 21.x | High confidence — flag if build fails |
