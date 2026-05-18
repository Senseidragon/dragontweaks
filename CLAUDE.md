# CLAUDE.md

Guidance for Claude Code when working in this repository.
Read this file completely before touching any source file. Do not summarize or skip sections.

---

## Session Startup — Required Every Time

1. Read `docs/devchatindex.md` — this is the entry point for all session context.
2. Follow its "What To Read For Which Task" table. Read only the docs listed for your task.
3. Confirm the current branch is `phase-1`.
4. Run `./gradlew build` and confirm it is clean.
5. Report branch and build status before proceeding.

Do not begin any task until all five steps are complete and reported.

---

## Agent Behavior Rules — Never Violate

- **No sub-agents. Ever.** Do not spawn subagents, parallel agents, or multi-agent workflows. All work is done sequentially by this agent alone.
- **Read before writing.** Always read a file before modifying it. Never modify a file you have not read in this session.
- **One task at a time.** Complete the current task, report results, and stop. Do not proceed without explicit direction.
- **Do not read files you have not been directed to read.** Stay on task. `docs/devchatindex.md` tells you exactly what to read for each task type.
- **Do not refactor unrelated code** while fixing a bug or implementing a feature.
- **Typos in instructions:** If a filename or instruction contains an obvious typo, correct it using common sense and proceed.
- **Never guess at root causes.** Trace the full execution path from uploaded source files before writing any fix.
- **Strip punctuation before string comparisons** in any trigger matching code.

---

## Hard Architectural Rules — Never Violate

1. **Nothing may ever block the main Minecraft game thread.** Non-negotiable under any circumstances.
2. **All network calls must be async** using `HttpClient.sendAsync()` + `CompletableFuture` + `orTimeout()`. Blocking `send()` is never acceptable — not in production, not in prototypes.
3. **Async responses must be queued back to the main thread** via `server.execute()` before any game interaction.

   Mandatory async pattern:
   ```java
   httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
       .orTimeout(Config.LLM_TIMEOUT_SECONDS.get(), TimeUnit.SECONDS)
       .thenApply(parseResponse)
       .thenAccept(reply -> server.execute(() -> { /* game thread work */ }))
       .exceptionally(ex -> { LOGGER.warn(...); return null; });
   ```

4. **Zero interference with MineColonies internals.** All integration via public API and event bus only. Citizens are never directly modified.
5. **All MineColonies API calls must be verified against stubs in `docs/stubs/`** before any dependent code is written. Report what you find before writing. Use `docs/STUB_INDEX.md` to locate specific stubs.
6. **Never hardcode config values.** All tunable values live in `Config.java`.
7. **API key lives in `.env` only.** Never in source, never logged, never transmitted to clients.
8. **LLM responses are immersion only.** Game logic must never depend on LLM response content. Fallback templates are always acceptable.

---

## LLM Client

- **Provider:** OpenRouter
- **Endpoint:** `https://openrouter.ai/api/v1/chat/completions`
- **Client class:** `LLMClient.java` — do not rename, do not recreate, do not add a second HTTP client.
- **Model:** `google/gemma-4-26b-a4b-it` (from `Config.java` — do not hardcode)
- **`max_tokens`: 200** for all standard NPC calls. Functional roles (Advisor, Planner) may require higher budgets — check the spec before changing this value.
- **`stream: false` always.**
- **Reasoning block:** Commented out in `LLMClient.java` — intentional. Current model rejects `effort:none`. Do not uncomment without explicit instruction.

---

## MineColonies API Patterns

- **MineColonies guard:** Always wrap calls with `if (!ModList.get().isLoaded("minecolonies")) return;`
- **Event subscription:** `IMinecoloniesAPI.getInstance().getEventBus().subscribe(...)` — not the NeoForge event bus.
- **Colony lookup:** `IColonyManager.getInstance().getColonies(level)` → `List<IColony>`
- **Raid state:** `colony.getRaiderManager().isRaided()` — poll only; no subscribable raid-started event.
- **Colony age:** `IColony.getDay()` → `int`
- **Colony boundary:** `IColony.isCoordInColony(world, pos)`
- **Town Hall level:** `colony.getServerBuildingManager().getTownHall().getBuildingLevel()`
- **Happiness factors:** Ten canonical IDs only — `food`, `slepttonight`, `housing`, `health`, `unemployment`, `idleatjob`, `security`, `school`, `social`, `mystical`
- **No native commute factor** — derive commute distance from building positions manually.
- **`doDaylightCycle` gamerule** — query via Minecraft's native `GameRules` system, not MineColonies API.
- **Warehouse stock:** `AbstractTileEntityWareHouse.getMatchingItemStacksInWarehouse(Predicate<ItemStack>)` — query per material; no full-dump method exists.

---

## NeoForge 1.21.1 — Class Name Traps

These names do not exist in NeoForge 1.21.1. Using them will break the build:

| Wrong (do not use) | Correct |
|---|---|
| `HumanoidMob` | `PathfinderMob` |
| `setHomePosAndDistance(BlockPos, int)` | `restrictTo(BlockPos, int)` on `Mob` |
| `RestrictedWaterAvoidingRandomWalkingGoal` | `WaterAvoidingRandomStrollGoal` + `MoveTowardsRestrictionGoal` |
| `OllamaClient` | Does not exist — LLM client is `LLMClient.java` |

Before using any class name not in the above table, verify it exists in NeoForge 1.21.1 sources. Do not assume 1.20.x names carry over.

---

## NPC Wander Restriction Pattern

- `this.restrictTo(blockPos, radius)` on `Mob` sets home anchor and radius.
- `WaterAvoidingRandomStrollGoal` respects restriction automatically.
- Add `MoveTowardsRestrictionGoal` alongside it.
- Call `restrictTo()` at spawn via `finalizeSpawn()` override.
- Call `restrictTo()` again on stop/stay command using `npc.blockPosition()` as the new anchor.

---

## Files You Must Not Modify Without Explicit Instruction

- `DragonTweaks.java` — restructure only if explicitly directed
- `LLMClient.java`
- `Config.java`
- `RoleAssignmentData.java`
- `ColonyDiagnosticReport.java`
- `PlannerDependencyRegistry.java`

---

## Source of Truth for Current File Status

`docs/current_state.md` is the authoritative list of all source files and their completion status. Check it before assuming any file does or does not exist. Do not rely on memory or this file for that information.

---

## Build Commands

```bash
./gradlew build              # Standard build — run after every change
./gradlew clean              # Clean build outputs
./gradlew --refresh-dependencies
./gradlew runData            # Run data generators
./gradlew runClient          # Launch client with mod loaded
./gradlew runServer          # Launch server with mod loaded
```

Built JAR ends up in `build/libs/`.

---

## Project Identity

| Field | Value |
|---|---|
| Mod | DragonTweaks (Assistant Mod — final name TBD) |
| Mod ID | `dragontweaks` |
| Package | `io.github.senseidragon.dragontweaks` |
| Source root | `src/main/java/io/github/senseidragon/dragontweaks/` |
| Branch | `phase-1` |
| Platform | NeoForge 21.1.226, Java 21, Minecraft 1.21.1 |
| Parchment mappings | `2024.11.17` |
| MineColonies API stubs | `docs/stubs/` — index at `docs/STUB_INDEX.md` |

---

## Session Closeout — Required on Every Task Completion

After completing any task that touches source files, config, or project structure:

1. Update `docs/devchat.md`:
   - Set `Last updated` date at the top.
   - Update the file table (`What Exists Right Now`) for any files added, removed, or changed.
   - Add a session note under `Session Notes`.
2. If `./gradlew build` passed, update `dragontweaks_verification_checklist.md` to reflect newly verified behaviors.

Do not skip this step. Do not mark a task complete without doing it first.
