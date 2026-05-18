# DragonTweaks — Session History

*Archaeology only. Not part of default startup context.*
*For current state, read `docs/current_state.md`, `docs/citizen_advisor_integration.md`, and `docs/panels_and_diagnostics.md`.*

---

## Session 28 — 2026-05-17 — Advisor multi-citizen branching + root cause suppression

Implemented `advisor_branching_spec_v0_2.md`.

**AdvisorThrottleData:** `Set<String>` replaced with two `Map<String, Integer>` maps. Old NBT format silently discarded. New API: `hasFiredToday`, `markFiredToday`, `getSuppressedSinceDay`, `recordSuppression`, `clearSuppression`. Stale daily entries pruned on `markFiredToday`.

**AdvisorDiagnosticLoop:** `runCycleAsync` rewritten. Systemic no longer suppresses per-citizen. Pre-scan: up to 5 flagged citizens sorted red-first/alpha; top 2 selected. Per-citizen: root cause suppression check → daily throttle check on main thread. `buildCitizenPrompt(report, CitizenRecord)` — worst factor from per-citizen `HappinessFactor` list. Throttle keys use citizen name (no numeric ID in `CitizenRecord`). Non-targetCitizen root cause defaults to `RootCause.UNKNOWN`.

**Config:** `ADVISOR_ROOTCAUSE_SUPPRESS_DAYS` added (int, default 2).

**DragonTweaks:** TODO comment for `RaidStartedEvent` — stub not found in `docs/stubs/`. Implementation deferred.

Build clean.

---

## Session 24 — 2026-05-13 — RoleAssignmentData colony-scoped

Breaking change: `RoleAssignmentData` internal map changed from `Map<Integer, AssistantRoleRecord>` to `Map<String, AssistantRoleRecord>`. Key format: `colonyId + ":" + citizenId` via private `key()` helper. All four public APIs now require `colonyId`. NBT stores string key in `"key"` field. No migration from old integer-only save data.

Call sites updated: `RoleSelectionPacket` (added colony lookup), `AssistantCommand.revokeByName` (passes `colony.getID()`), `ChatInterceptor` (`citizenColony.getID()`), `CitizenInteractDetector` (`citizenData.getColony().getID()`).

Build clean.

---

## Session 23 — 2026-05-13 — Citizen conversation routing, verified in-game

**LLMClient.java:** `{"reasoning":{"effort":"none"}}` commented out. Model switched to `openai/gpt-oss-120b` which rejects `effort:none` with 400. TODO: re-enable when switching back to `google/gemma-4-26b-a4b-it`. New `query()` overload added: `Consumer<String> onReply` callback, fires after response delivered on main thread.

**CitizenConversationMemory.java:** SavedData on overworld key `"dragontweaks_citizen_memory"`. Map keyed by `"colonyId:citizenId"`. Max 20 entries/citizen, FIFO eviction. Full NBT serialization.

**ChatInterceptor.java:** Added citizen routing block before combined early-return guard. Iterates colony citizens, skips visitors (`instanceof IVisitorData`) and role-assigned citizens, partial case-insensitive name match with nickname priority. Fires `LLMClient.query()` with `Consumer<String>` callback that saves exchange to `CitizenConversationMemory`.

**DragonTweaks.java:** `CitizenConversationMemory.clearHistory()` added to `CitizenDiedModEvent` handler.

**AssistantCommand.java:** `/assistant nickname` success message now shows citizenName, citizenId, and nickname.

Verified in-game: nickname match, citizen routing, role-assigned block, responses delivered as `[nickname]: [text]`. Build clean.

---

## Session 22 — 2026-05-12 — Nickname system, village proximity warnings, advisor activation wiring

**NicknameData.java:** SavedData on overworld `"dragontweaks_nicknames"`. Map keyed by `"colonyId:citizenId"`. API: setNickname, getNickname (nullable), removeNickname, resolve(fallback). Death cleanup in DragonTweaks.java.

**AssistantCommand.java:** `/assistant nickname <partialName> <nickname>` subcommand added. Partial case-insensitive match, multiple-match handling.

**ChatInterceptor.java:** COLONY_WITH_CITIZEN trigger now uses `NicknameData.resolve()` for citizen name lookup.

**TerrainScanner.java:** Replaced block-scan village heuristic with `ServerLevel.findNearestMapStructure(StructureTags.VILLAGE, center, 19, false)`. Label includes compass direction + distance. `compassDir(dx, dz)` private helper (8-point).

**PreColonyScoutTicker.java:** Village proximity warning via `findNearestMapStructure` at 150-block radius. Distance rounded to nearest 50 blocks. Iron golem + pillager raid warning injected into LLM context. `lastObservedPos.put()` moved before LLM fire.

**RoleAssignmentData.java:** `getAssignments()` added returning `Iterable<AssistantRoleRecord>`.

**RoleSelectionPacket.java:** On selectedRole == "Advisor": transitions `AdvisorStateData` to `COLONY_WITH_CITIZEN`, sets `assignedCitizenId`. This wires the advisor activation.

Build clean.

---

## Session 21 — 2026-05-10 — COLONY_NO_CITIZEN chat routing

Added `COLONY_NO_CITIZEN` routing block to `ChatInterceptor.java`. Guards: `!LITE_MODE`, state == `COLONY_NO_CITIZEN`, `messageLower.startsWith("advisor")`, MineColonies loaded. Finds `BookAdvisorEntity` by owner UUID within 64-block AABB. Extended combined early-return guard.

LLM context: biome, time, weather, Y, terrain labels from `TerrainScanner.scan()`, building count, Town Hall level (guarded by `hasTownHall()`). Strips "advisor" prefix before passing to LLM. Persona: "You are Advisor, a colony advisor. You have full knowledge of the colony structure." Delivered as `[Advisor]: [response]`.

Build clean.

---

## Session 20 — 2026-05-10 — PreColonyScoutTicker and TerrainScanner extraction

Extracted `scanTerrainLabels` private method from `ChatInterceptor.java` into new `TerrainScanner.java` with `public static String scan(ServerLevel, BlockPos)`.

Created `PreColonyScoutTicker.java`: `ServerTickEvent.Post` at 1200-tick interval. PRE_COLONY state, not flying, moved ≥64 blocks, BookAdvisorEntity within 64 blocks. 1-in-7 dry humor directive. Registered in `DragonTweaks.java`. Build clean.

---

## Sessions 16–19 — 2026-05-10 — TerrainScanner iterative improvements

- **Session 16:** Initial `scanTerrainLabels` in `ChatInterceptor`: 8 categories (ice, snow, water, lava, sand, gravel, farmland, forest), 32×8×32 radius step 2.
- **Session 17:** Removed `canSeeSky` early return. Added stone and ore categories.
- **Session 18:** Added structures category.
- **Session 19:** Added village category via block heuristic (BELL, HAY_BLOCK, etc.); village takes priority over structures. (Later replaced in session 22 with `findNearestMapStructure`.)

Build clean each session.

---

## Session 15 — 2026-05-08 — Role assignment packets and screen

Created: `RoleAssignmentPayload.java`, `RoleSelectionPacket.java`, `RoleAssignmentScreen.java`

Replaced TODO stub in `CitizenInteractDetector.java` with `PacketDistributor.sendToPlayer(serverPlayer, new RoleAssignmentPayload(...))`.

Updated `DragonTweaksClient.java`: added `RoleAssignmentPayload` handler (opens `RoleAssignmentScreen`).

Updated `DragonTweaks.java`: added `registerServerPackets()` registering `RoleSelectionPacket` `playToServer`.

Build clean.

---

## Session 14 — 2026-05-08 — BookAdvisorEntity state-aware movement

Modified `BookAdvisorEntity.java` tick to be state-aware: PRE_COLONY/DORMANT follows player; colony states use `IColonyManager.getInstance().getIColony()` to look up colony and branch between follow/hold/snap-to-TH. Extracted `followPlayer(Player)` private helper. Build clean.

---

## Session 13 — 2026-05-08 — Colony event handlers and advisor citizen-lost logic

Added to `DragonTweaks.java`:
- `ColonyCreatedModEvent` handler: PRE_COLONY → COLONY_NO_CITIZEN transition, one-time greeting message
- `ColonyDeletedModEvent` handler: any colony state → PRE_COLONY, despawn/respawn BookAdvisorEntity
- Shared `handleAdvisorCitizenLost()` private helper: COLONY_WITH_CITIZEN → COLONY_NO_CITIZEN on citizen death or job change, despawns/respawns at Town Hall

New field: `colonyGreetedPlayers` Set<UUID> (session-only). Build clean.

---

## Session 12 — 2026-05-08 — AdvisorHotbarWatcher

Created `AdvisorHotbarWatcher.java`. Detects `structurize:sceptergold` in hotbar slots 0–8 via `BuiltInRegistries.ITEM.getKey()`. DORMANT state only. Transitions to PRE_COLONY, spawns BookAdvisorEntity. Build clean.

---

## Session 11 — 2026-05-08 — BookAdvisorEntity, ModEntities registration

Created `BookAdvisorEntity.java` (extends `Entity`, not PathfinderMob). Glow via `setGlowingTag(true)`. Follow logic in `tick()` via yaw-relative vector math.

Updated `ModEntities.java`: added `BOOK_ADVISOR` DeferredHolder. No attribute registration (Entity subclass). Build clean.

---

## Session 10 — 2026-05-08 — AdvisorState enum, AdvisorStateData, Config advisor values

Created `AdvisorState.java` (enum) and `AdvisorStateData.java` (SavedData).

Added five config values to `Config.java`: `ADVISOR_ENTITY_OFFSET`, `ADVISOR_HOTBAR_CHECK_TICKS`, `ADVISOR_BOUNDARY_DETECTION_RANGE`, `ADVISOR_WHISPER_THRESHOLD`, `ADVISOR_FORCE_PRIVATE`. Build clean.

---

## Session 9 — 2026-05-07 — Packet refactor, bed count fix, planner correctness, townhall auto-satisfied

**ClientPanelHandler:** Removed `handleOnClient` from packet classes. Created `ClientPanelHandler.java` (`@OnlyIn(Dist.CLIENT)`). Moved `registerPackets()` from `DragonTweaks` to `DragonTweaksClient`.

**housingCap fix:** `getMaxCitizens()` was wrong (research-gated capacity). Replaced with `countBedCapacity()`: +2 Residence, +4 Tavern via translation key substring.

**Planner already-built skip:** `generateRecommendations()` skips recs for already-built buildings with `stepsRemaining == 0`. Exceptions: `residence`, `guard_tower`.

**townhall auto_satisfied:** `"auto_satisfied": true` in `planner_dependencies.json` for townhall. `PlannerDependencyRegistry` parses into `autoSatisfiedIds`. `isStepComplete()` checks this first for BUILDING steps.

Build clean.

---

## Session 8 — 2026-05-07 — PlannerPanelScreen

Created `PlannerPanelScreen.java`. 324×270. Snapshot mode with recommendation cards. Goal input mode with EditBox, Enter handler (KEY_RETURN=257), chain view, materials collapsible, no-match suggestions. Build clean.

---

## Session 7 — 2026-05-07 — AdvisorPanelScreen

Created `AdvisorPanelScreen.java`. 304×250. Environmental banner, systemic pattern banner, paginated citizen list with expand/collapse, severity dots, commute flags. Build clean.

---

## Session 6 — 2026-05-07 — PlannerPanelPayload

Created `PlannerPanelPayload.java`. Snapshot mode (rec list from red factors + housing). Goal input mode (chain from `PlannerDependencyRegistry`). BUILDING_HOLDERS map (20 entries). Build clean.

---

## Session 5 — 2026-05-07 — AdvisorPanelPayload

Created `AdvisorPanelPayload.java`. Calls `ColonyDiagnosticCache.getOrGenerate()`. Citizens sorted red→yellow→healthy. `CitizenEntry` with collapsed + expanded fields. Threshold values from config at call-time. Build clean.

---

## Session 4 — 2026-05-07 — AdvisorDiagnosticLoop

Created `AdvisorThrottleData.java` (SavedData, Set<String> throttle keys) and `AdvisorDiagnosticLoop.java` (600-tick + dirty-flag loop, async via CompletableFuture, throttle + LLM on main thread).

Updated `DragonTweaks.java`: registered loop, added `markDirty()` alongside cache invalidation in all 4 MineColonies handlers. Build clean.

---

## Session 3 — 2026-05-07 — ColonyDiagnosticCache

Created `ColonyDiagnosticCache.java`. ConcurrentHashMap, 30s TTL, `getOrGenerate()` + `invalidate()`. Registered four invalidation handlers in `DragonTweaks.java`. Build clean.

---

## Session 2 — 2026-05-07 — PlannerDependencyRegistry

Created `planner_dependencies.json` with full 20-building seed data. Created `PlannerDependencyRegistry.java` as singleton. DFS post-order chain resolution, cycle-safe. `findMatches()` exact + substring. Registered `load()` as first call in `commonSetup()`. Build clean.

---

## Session 1 — 2026-05-07 — Config additions and LLMClient reasoning disable

Added: `ADVISOR_COMMUTE_THRESHOLD`, `ADVISOR_HAPPINESS_THRESHOLD_RED`, `ADVISOR_HAPPINESS_THRESHOLD_YELLOW` to `Config.java`.

Added `{"reasoning":{"effort":"none"}}` to `LLMClient.java` `buildRequestBody()`. (Later commented out in session 23.)

Build clean.

---

## Session — 2026-05-06 — CitizenInteractDetector

Built `CitizenInteractDetector.java`. Verified API chain against stubs before writing. Citizen identity, isAssigned guard, dynamic TH-level slot cap, event cancel, debug log. RoleAssignmentScreen call was TODO stub (replaced session 15). Updated CLAUDE.md: fixed devchat.md path references.

Build clean.

---

## Session — 2026-05-05 — Flavor NPC Behavior Design Session

No code written. Decisions locked:
- Flavor NPCs: ambient/immersive only, wander 3–5 block radius, `LookAtPlayerGoal` on proximity
- Greeting: per-NPC per-player cooldown, configurable chance (default 7%), configurable cooldown (default 12000 ticks)
- LLM calls only when player within detection range
- Colony event reactions: raid started, citizen death, building construction complete

---

## Session — 2026-05-05 — MineColonies Handlers and Greeting/Raid Systems

Added to `DragonTweaks.java`: `CitizenDiedModEvent` and `BuildingConstructionModEvent` handlers, guarded by `ModList.isLoaded("minecolonies")`.

Added to `ObservationTicker.java`: per-player greeting loop, raid state-flip poll (`IRaiderManager.isRaided()`), `fireColonyEventObservation()` helper.

Added to `AssistantEntity.java`: idle wander (`WaterAvoidingRandomStrollGoal`), `LookAtPlayerGoal`, per-player greeting system (`Map<UUID, Long>`).

Build clean.

---

## Session — 2026-05-02 — Design Ponder Session

No code written. Decisions locked:
- Follow/stop mechanism archived (no production use case currently)
- Flavor NPC visual representation: floating non-humanoid objects (book, map, etc.)
- Shadow entity system for Advisor/Planner: follows player within colony, holds at boundary, snaps to Town Hall
- Ranch Hand design: operates beyond colony bounds, sighting memory (dual TTL+cap eviction), passive collection, guided wandering
- Quest system: parking lot (future only)

---

## Session — 2026-05-01 — Steps 1–3

No SavedData wiring found in `DragonTweaks.java` or `AssistantCommand.java`.

Added `LevelEvent.Load` handler to `DragonTweaks.java` to initialize `RoleAssignmentData` on overworld load.

Fixed `RoleAssignmentData.java` corruption (prior tool interaction injected raw text into load() method).

In-game sanity check passed. Build clean.

---

## Session — 2026-04-30 — ObservationTicker Bug Fixes

Fixed 5 bugs in `ObservationTicker.java` introduced by commit `d8df91b`:
1. `anyActiveThreats` always false — never set to true
2. Tick throttle declared but not wired — `if (++tickCounter % TICK_INTERVAL != 0) return;` missing
3. Mob tracking loop ran twice — duplicate removed
4. All-clear observe fired for non-threat mobs — wrapped with `npcThreatState` check
5. `hostilePresentMap` declared and never referenced — removed

Added `int citizenId` as first field to `AssistantRoleRecord`. Updated call sites in `RoleAssignmentData.java`. Build clean.
