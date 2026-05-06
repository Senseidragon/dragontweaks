**# CLAUDE.md

This file provides guidance to Claude Code when working with code in this repository.
Read this file completely before touching any source file. Do not summarize or skip sections.

---

## Session Startup — Required Every Time

Before beginning any task:
1. Read `devchat.md` in full
2. Confirm the current branch is `phase-1`
3. Run `./gradlew build` and confirm it is clean
4. Report branch and build status before proceeding

Do not begin any task until these three steps are complete and reported.

---

## Agent Behavior Rules — Never Violate

- **No sub-agents. Ever.** Do not spawn subagents, parallel agents, or multi-agent workflows under any circumstances. They burn tokens and produce unreliable results. All work is done sequentially by this agent alone.
- **No Superpowers plugin features.** Do not invoke brainstorming skills, design approval flows, or any Superpowers-specific workflow. Execute tasks directly.
- **Read before writing.** Always read a file before modifying it. Never modify a file you have not read in this session.
- **One task at a time.** Complete the current task, report results, and stop. Do not proceed to the next task without explicit direction.
- **Do not read files you have not been directed to read.** Stay on task.
- **Do not refactor unrelated code** while fixing a bug or implementing a feature. One concern at a time.
- **Typos in instructions:** If a filename or instruction contains an obvious typo, correct it using common sense and proceed. Do not stop to investigate.

---

## Hard Architectural Rules — Never Violate

1. **Nothing may ever block the main Minecraft game thread.** Non-negotiable.
2. **All network calls (including OpenRouter HTTP requests) must be async** using `HttpClient.sendAsync()` + `CompletableFuture` + `orTimeout()`.
3. **Async responses must be queued back to the main thread** via `server.execute()` before any game interaction.
4. **Zero interference with MineColonies internals.** All MineColonies integration via public API and event bus only. Citizens are never directly modified.
5. **All MineColonies API calls must be verified against stubs in `docs/stubs/`** before any dependent code is written. Report what you find before writing.
6. **Never hardcode config values.** All tunable values must live in `Config.java`.
7. **API key lives in `.env` only.** Never in source, never logged, never transmitted to clients.

---

## NeoForge 1.21.1 — Known Class Name Traps

These are confirmed wrong vs right for NeoForge 1.21.1. Do not use the wrong names under any circumstances:

| Wrong (1.20.x) | Correct (1.21.1) |
|---|---|
| `HumanoidMob` | Does not exist — use `PathfinderMob` |
| `setHomePosAndDistance(BlockPos, int)` | Does not exist — use `restrictTo(BlockPos, int)` on `Mob` |
| `RestrictedWaterAvoidingRandomWalkingGoal` | Does not exist — use `WaterAvoidingRandomStrollGoal` + `MoveTowardsRestrictionGoal` |
| `OllamaClient` | Does not exist in this project — LLM client is `LLMClient.java` |

Before using any class name from memory, verify it exists in the NeoForge 1.21.1 decompiled sources. Report what you find. Do not assume 1.20.x names carry over.

---

## LLM Client Architecture

- **Provider:** OpenRouter (server-side only)
- **Client class:** `LLMClient.java` — instantiable class with explicit lifecycle management. Not a static utility class.
- **Async pattern (mandatory):**
  ```java
  httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
      .orTimeout(Config.LLM_TIMEOUT_SECONDS.get(), TimeUnit.SECONDS)
      .thenApply(parseResponse)
      .thenAccept(reply -> server.execute(() -> { /* game thread work */ }))
      .exceptionally(ex -> { LOGGER.warn(...); return null; });
  ```
- **Blocking `send()` is never acceptable** — not in production, not in prototypes.
- **`max_tokens: 100` always** — NPC responses must be short.
- **`stream: false` always** — No streaming.
- **LLM responses are immersion only.** Game logic must never depend on response content. Fallback templates are always acceptable.

---

## MineColonies API Patterns

- **Event subscription:** `IMinecoloniesAPI.getInstance().getEventBus().subscribe(EventClass.class, handler)` — not the NeoForge event bus.
- **Colony lookup by level:** `IColonyManager.getInstance().getColonies(level)` — returns `List<IColony>` for all colonies in a level.
- **Raid state:** `colony.getRaiderManager().isRaided()` — poll for state flip; no subscribable raid-started event exists.
- **Citizen death event:** `CitizenDiedModEvent` — accessor: `e.getColony().getWorld()` cast to `ServerLevel`
- **Building construction event:** `BuildingConstructionModEvent` — accessor: `e.getBuilding().getBuildingType().getTranslationKey()`
- **MineColonies guard:** Always wrap MineColonies API calls with `if (!ModList.get().isLoaded("minecolonies")) return;`
- **Happiness factors:** Ten canonical factor IDs — food, slepttonight, housing, health, unemployment, idleatjob, security, school, social, mystical
- **No native commute happiness factor** — derive commute distance from building positions manually
- **`doDaylightCycle` gamerule** — query via Minecraft's native `GameRules` system, not MineColonies API

---

## NPC Wander Restriction

- Call `this.restrictTo(blockPos, radius)` on `Mob` to set a home anchor and radius
- `WaterAvoidingRandomStrollGoal` respects the restriction automatically via `GoalUtils.isRestricted()`
- Add `MoveTowardsRestrictionGoal` alongside it to pull the NPC back if it strays
- Always call `restrictTo()` at spawn time via `finalizeSpawn()` override
- Always call `restrictTo()` again when a stop/stay command is issued, using `npc.blockPosition()` as the new anchor

---

## What Does Not Exist Yet

These files do not exist in the codebase. Do not reference them, do not assume they exist, do not attempt to import them:

- `CitizenInteractDetector.java` — right-click citizen detection, not built
- `RoleAssignmentScreen.java` — client-side role assignment UI, not built

The write path for `RoleAssignmentData` also does not exist yet. Nothing currently writes to it.

---

## Build Commands

```bash
# Standard build — run after every change
./gradlew build

# Clean build outputs
./gradlew clean

# Refresh dependencies
./gradlew --refresh-dependencies

# Run data generators
./gradlew runData

# Launch client with mod loaded
./gradlew runClient

# Launch server with mod loaded
./gradlew runServer
```

Built JAR ends up in `build/libs/`. Mod metadata is injected at build time from `gradle.properties`.

---

## Project Identity

- **Mod:** DragonTweaks (Assistant Mod)
- **Platform:** NeoForge 1.21.1, Java 21
- **Mod ID:** `dragontweaks`
- **Package:** `io.github.senseidragon.dragontweaks`
- **Source root:** `src/main/java/io/github/senseidragon/dragontweaks/`
- **Branch:** `phase-1`
- **NeoForge version:** `21.1.226`
- **Parchment mappings:** `2024.11.17`
- **MineColonies API stubs:** `docs/stubs/` — index at `docs/STUB_INDEX.md`

---

## Key Architecture Notes

**Entry points:**
- `DragonTweaks.java` — Main mod class (`@Mod`). Bootstraps DeferredRegisters, registers event listeners on both mod event bus and NeoForge game event bus, loads config spec, validates API key on startup.
- `DragonTweaksClient.java` — Client-only (`@Mod(dist = Dist.CLIENT)`). Safe from server-side classloading.

**Registration pattern:** All game objects use NeoForge `DeferredRegister` pattern, declared as `static final` fields, bound to the mod event bus in the constructor.

**Config:** `Config.java` uses `ModConfigSpec`. Values accessed statically. All new config values follow the existing pattern exactly.

****ObservationTicker:** Fires every 100 ticks (`TICK_INTERVAL = 100`). Guarded by `NPC_OBSERVATIONS_ENABLED`. Silent-drop pattern: if no qualifying player is in range, no LLM call is made and no error is logged. Greeting trigger and raid poll both run on this same interval.

**Greeting system:** Per-player cooldown tracked as `Map<UUID, Long>` on `AssistantEntity`. Roll fires on player entering detection range. Only fires if player is within `COMMAND_PROXIMITY`. Uses plain AABB check — not `findTarget()` — because greeting fires before ConversationMemory history exists.