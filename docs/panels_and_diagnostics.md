# DragonTweaks — Panels & Diagnostics

*Current-state reference. No session history.*
*Last updated: 2026-05-17 (session 28)*

---

## ColonyDiagnosticReport

Central data object produced by the diagnostic layer. Both LLM context injection (full mode) and dashboard panels (both modes) read from this object.

**Contents:**
- Colony metadata: name, citizen count, housing cap, Town Hall level, overall happiness
- Per-citizen records: name, job, work/home building positions, commute distance (XZ Euclidean), all 10 happiness factors with value/weight/red+yellow flags
- Building records: type (translation key), level, built, pending, first assigned worker name
- Environmental flags: DAYLIGHT_CYCLE_DISABLED, RAID_ACTIVE, THUNDERSTORM
- Systemic pattern: nullable `SystemicPattern` enum (NEWLY_FOUNDED_ALL_RED, ALL_CITIZENS_RED, HOUSING_SLEEP_COMMUTE_CLUSTER)
- Per-citizen root cause: `RootCause` enum, worstFactor, redFactors list, commute info
- Research snapshot (not yet wired to panels)

**Generation:** Always async. Cached with 30s TTL. Never on main thread.

---

## ColonyDiagnosticReportGenerator

Single public method: `generate(IColony)` → `ColonyDiagnosticReport`

**Phase 1 — Per-citizen records** (`buildCitizenRecords()`):
- Iterates all citizens, resolves name/job/work pos/home pos
- Commute distance: XZ Euclidean from work to home building positions
- Happiness: 10 canonical factor IDs via `ICitizenHappinessHandler`, values from `IHappinessModifier.getFactor()`
- Red flag: factor < `ADVISOR_HAPPINESS_THRESHOLD_RED`; Yellow flag: factor < `ADVISOR_HAPPINESS_THRESHOLD_YELLOW`

**Phase 2 — Building records** (`buildBuildingRecords()`):
- All colony buildings via `getServerBuildingManager().getBuildings()`
- Type via translation key, level, built/pending status, first assigned worker

**Phase 3 — Systemic pattern detection:**
- `NEWLY_FOUNDED_ALL_RED`: colonyDay < 3 AND all citizens have ≥1 red factor
- `ALL_CITIZENS_RED`: all citizens have ≥1 red factor
- `HOUSING_SLEEP_COMMUTE_CLUSTER`: ≥2 citizens with commute > threshold AND housing red AND sleep red
- First match wins. Skips Phase 4 if systemic detected.

**Phase 4 — Per-citizen root cause** (`determineRootCause()`):
- Skipped when systemic pattern detected
- Picks lowest-happiness citizen (tiebreak by name)
- Maps worstFactor + home/work buildings + commute + redFactors to `RootCause` enum

**Environmental flags** (`collectEnvironmentalFlags()`):
- `DAYLIGHT_CYCLE_DISABLED`: Minecraft `GameRules` (not MineColonies API)
- `RAID_ACTIVE`: `colony.getRaiderManager().isRaided()`
- `THUNDERSTORM`: `level.isThundering()`

**Bed capacity** (`countBedCapacity()`):
- +2 per built Residence (translation key contains "residence" or ".home")
- +4 per built Tavern (translation key contains "tavern")
- TODO: verify these substring matches against MineColonies 1.21.1 translation keys in-game

---

## ColonyDiagnosticCache

- Static class with `ConcurrentHashMap<Integer, CacheEntry>` keyed by colony ID
- TTL: 30 seconds (hardcoded constant — no config key)
- `getOrGenerate(IColony)` → checks staleness, calls generator on miss
- `invalidate(int colonyId)` → removes entry

**Invalidation triggers** (in `DragonTweaks.java`):
- `CitizenDiedModEvent`
- `BuildingConstructionModEvent`
- `CitizenJobChangedModEvent`
- `CitizenAddedModEvent`

---

## AdvisorDiagnosticLoop

> Prompt behavior, response ranking rules, and evaluation criteria for the Advisor LLM live in `docs/advisor_prompt_engineering_spec_v0_2.md`.
> Multi-citizen branching, throttle suppression, and root cause suppression logic: `docs/advisor_branching_spec_v0_2.md`.

- Registered on `NeoForge.EVENT_BUS` as `ServerTickEvent.Post`
- Checks each colony every 600 ticks (30s) OR when `markDirty(colonyId)` is called
- `markDirty()` is called alongside `ColonyDiagnosticCache.invalidate()` in all 4 event handlers in `DragonTweaks.java`
- `RaidStartedEvent` invalidation: TODO — stub not found in `docs/stubs/`; comment added in `DragonTweaks.java`

**Flow (`runCycleAsync`):**
1. Main thread: player-in-colony check via `isCoordInColony`
2. Async: `CompletableFuture.runAsync()` → `ColonyDiagnosticCache.getOrGenerate()`
3. Async: Systemic check — if `isSystemicPatternDetected()`, build systemic prompt, dispatch `server.execute()` block
4. Async: Pre-scan pass — filter `getCitizens()` to flagged citizens (any `isRedFlag()`, `isYellowFlag()`, or `commuteDistance > threshold`); sort red-first then alpha; cap at 5 → candidate list
5. Async: Take top 2 from candidate list
6. Async (per citizen): build suppression key + daily throttle key + LLM prompt string
7. Main thread via `server.execute()` (one block per citizen): root cause suppression check → daily throttle check → player lookup → `markFiredToday` + `recordSuppression` → `LLMClient.observe()`

**Throttle keys:**
- Per-citizen daily: `"{colonyId}:{citizenName}:{colonyDay}"` — **name used, not numeric ID** (`CitizenRecord` has no numeric ID field)
- Systemic: `"{colonyId}:systemic:{patternType}:{colonyDay}"`
- Root cause suppression: `"{colonyId}:{citizenName}:rc{rootCauseOrdinal}"` — ordinal from `RootCause` enum; non-targetCitizen citizens get `RootCause.UNKNOWN` ordinal

**Root cause availability:** Report-level `getRootCause()` is only valid for the single `targetCitizen`. Citizens other than `targetCitizen` in the top-2 get `RootCause.UNKNOWN` for suppression key purposes.

**Advisor identity:** Deterministic UUID per colony from `UUID.nameUUIDFromBytes`. No Advisor entity needed — colony-level proxy.

### AdvisorThrottleData.java

- SavedData on overworld, persisted across sessions
- Two `Map<String, Integer>` maps:
  - `firedKeys`: daily throttle — key → colonyDay it was fired
  - `suppressedKeys`: root cause suppression — suppressionKey → colonyDay first fired
- Old `Set<String>` NBT format detected on load and discarded silently (no migration)
- Stale `firedKeys` entries (day > colonyDay − 2) pruned automatically on `markFiredToday`

**Public API:**

| Method | Description |
|---|---|
| `hasFiredToday(String key, int colonyDay)` | True if key in `firedKeys` and stored day equals `colonyDay` |
| `markFiredToday(String key, int colonyDay)` | Stores key→colonyDay; prunes stale entries |
| `getSuppressedSinceDay(String key)` | Returns stored day from `suppressedKeys`, or -1 if absent |
| `recordSuppression(String key, int colonyDay)` | Stores suppressionKey→colonyDay |
| `clearSuppression(String key)` | Removes key from `suppressedKeys` (called when suppression window expires) |

---

## Advisor Panel

### AdvisorPanelPayload.java

Server-side data preparation. Static `build(IColony colony)`.

1. Calls `ColonyDiagnosticCache.getOrGenerate(colony)`
2. Assembles payload from report

**Payload contents:**
- `List<EnvironmentalFlag>` from report
- `SystemicPattern` (null if none detected)
- Colony summary: overall happiness, citizen count, housing cap
- `List<CitizenEntry>` ordered red → yellow → healthy, alphabetical within each tier

**CitizenEntry fields:**
- Collapsed: tier (Severity), name, worstFactorId, worstFactorValue, additionalComplaintsCount, commuteFlagged
- Expanded: `List<FactorDetail>` (all 10 canonical factors in canonical order, with value + modifier type label), commuteDistance, commuteThreshold

**FactorDetail modifier type labels** (from static map):
- Static / TimeBased / ExpirationBased per spec table

Threshold values read from config at call-time: `ADVISOR_HAPPINESS_THRESHOLD_RED`, `ADVISOR_HAPPINESS_THRESHOLD_YELLOW`, `ADVISOR_COMMUTE_THRESHOLD`.

### AdvisorPanelScreen.java

- `@OnlyIn(Dist.CLIENT)`, extends `Screen`, panel 304×250
- Constructor takes `AdvisorPanelPayload`
- `renderBackground()` called once; `super.render()` NOT called; `renderables` iterated directly (public final field on Screen)

**Layout (top to bottom):**
1. Title
2. Environmental banner (conditional, amber `0xEE663300`) — shown when `getEnvironmentalFlags()` non-empty
3. Systemic pattern banner (conditional, dark red `0xEE550011`) — one banner maximum
4. Colony summary header: happiness (color-coded) + citizen count / housing cap
5. Scissor-clipped citizen list
6. Prev / Close / Next nav row (5 items per page)

**Per-citizen collapsed row (16px):** severity dot, `>` or `v` arrow, name (truncated at 14 chars), worst factor id+value, `+N` badge, `[far]` commute flag

**Per-citizen expanded section:** 10 factor lines (10px each) each with severity dot + id + value + modifier label, then commute line with threshold comparison

**mouseClicked():** calls `super.mouseClicked()` first, then walks accumulated y through current page citizen rows (accounting for expanded sections) to hit-test header rows only.

**Expand state:** `Set<Integer>` keyed by citizen list index; cleared in `onClose()`.

### Packet Flow

`/assistant advisor` → `AssistantPanelCommand` → `OpenAdvisorPanelPacket` (server→client)

`ClientPanelHandler.handleAdvisorPanel()` (`@OnlyIn(Dist.CLIENT)`) → `Minecraft.getInstance().setScreen(new AdvisorPanelScreen(packet.payload()))` via `ctx.enqueueWork()`

Registered in `DragonTweaksClient.registerPackets()`.

---

## Planner Panel

### PlannerDependencyRegistry.java

Singleton. Loaded at `DragonTweaks.commonSetup()` — first call.

- Reads `src/main/resources/data/dragontweaks/planner_dependencies.json`
- DFS post-order chain resolution (cycle-safe via visited set)
- All chains pre-cached at load time
- `autoSatisfiedIds`: buildings with `"auto_satisfied": true` in JSON (currently: townhall)

**Public API:**
- `getChain(String buildingId)` → `List<ChainStep>`
- `findMatches(String input)` → `MatchResult` with exact matches + substring suggestions
- `isAutoSatisfied(String buildingId)` → boolean

**Chain step types:** `BUILDING`, `BUILDING_PREREQ` (level requirement), `RESEARCH`

**townhall is always auto-satisfied** — every colony has one, so all chains that pass through townhall always have it marked complete.

### PlannerPanelPayload.java

Server-side data preparation. Static `build(IColony colony, String goalInput)`.

**Snapshot mode** (`goalInput == null`):
- Calls `ColonyDiagnosticCache.getOrGenerate()`
- `WorkerHeader`: workersAssigned (built buildings with assigned worker), totalSlots (approx)
- `BedHeader`: from report citizen count + housing cap
- `List<Recommendation>` ordered crisis-first then by `stepsRemaining` ascending

**Recommendation fields:** targetId, targetDisplayName, priority (CRISIS/NON_CRISIS), immediateBlocker, researchPrereqName, workerSlotsNeeded, bedDelta, stepsRemaining, inProgress

**Already-built skip rule:** If target building is in `builtTypes` and `stepsRemaining == 0`, recommendation is skipped. Exceptions (multiple valid): `residence`, `guard_tower`.

**Goal input mode** (`goalInput != null`):
- `PlannerDependencyRegistry.findMatches()` + `getChain()`
- Annotates each `ChainStep` with `completed` and `firstIncomplete` flags
- `CostEstimate`: count of incomplete steps + names of incomplete RESEARCH steps
- `GoalResult`: exact matches, suggestions, chain, cost estimate, materials, hasWarehouse

**Step completion logic:**
- `BUILDING`: `IBuilding.getBuildingType().getRegistryName()` matched against `BUILDING_HOLDERS` map; `isAutoSatisfied()` checked first
- `BUILDING_PREREQ`: total level sum from `getBuildingTotalLevels()` vs step.minTotalLevel
- `RESEARCH`: `ILocalResearchTree.isComplete(ResourceLocation)`

**In-progress check:** `colony.getWorkManager().getWorkOrders()` → filter claimed BUILD/UPGRADE → match registry name

**Materials:** Empty — pending per-building material requirements API verification. `getMatchingItemStacksInWarehouse()` pattern documented in TODO comment.

**BUILDING_HOLDERS map:** 20 entries (townHall, builder, home, wareHouse, deliveryman, guardTower, tavern, university, lumberjack, sawmill, fletcher, miner, blacksmith, farmer, fisherman, cook, school, library, hospital, mysticalSite). TODO: verify ResourceLocation paths in-game.

### PlannerPanelScreen.java

- `@OnlyIn(Dist.CLIENT)`, extends `Screen`, panel 324×270
- Constructor: `PlannerPanelPayload` + nullable `Consumer<String> goalCallback`
- `updatePayload()`: public method for packet-driven refresh without reopening

**Layout:**
1. Title
2. "Goal:" label + `EditBox` (full-width minus label)
3. Worker/bed header strip (snapshot mode always visible)
4. Scissor-clipped content area
5. Prev / Close / Next nav row

**Snapshot mode:** Recommendations paginated 3/page. Per-rec card: `[!]/[ ]` priority badge, target name, `[In Progress]` annotation, immediate blocker (yellow), research prereq (blue), worker/bed constraint (red), steps remaining (grey).

**Goal input mode — exact match:** Cost estimate block, collapsible materials list (defaulted collapsed; `matsToggleY` field set each render, used in mouseClicked). Dependency chain paginated 8/page: completed steps grey + ✓, first incomplete yellow + ►, remaining white + indent.

**Goal input mode — no match:** "No match: <input>" in red, then "Did you mean:" suggestions or "No suggestions found." — never blank.

**Enter handling:** `keyPressed()` checks `keyCode == InputConstants.KEY_RETURN` (257) and `goalInput.isFocused()`. Calls `goalCallback.accept(text)` if non-null.

### Packet Flow

`/assistant planner` → `AssistantPanelCommand` → `OpenPlannerPanelPacket` (server→client)

`ClientPanelHandler.handlePlannerPanel()` (`@OnlyIn(Dist.CLIENT)`) → `Minecraft.getInstance().setScreen(new PlannerPanelScreen(packet.payload(), null))` via `ctx.enqueueWork()`

Registered in `DragonTweaksClient.registerPackets()`.

---

## TerrainScanner

`public static String scan(ServerLevel level, BlockPos center)`

- Scans 32-block X/Z radius, 4 up / 4 down Y, step 2
- Tracks boolean presence of categories: ice, snow, water, lava, sand, gravel, farmland, forest, stone, ore, structures, village
- Village detection via `ServerLevel.findNearestMapStructure(StructureTags.VILLAGE, center, 19, false)` (~300-block radius)
- Village label includes compass direction + approximate distance: e.g. `"village to the northeast (~84 blocks)"`
- Structures label suppressed when village found (mutually exclusive in output)
- 8-point compass via private `compassDir(dx, dz)` helper
- Returns "none notable" if no categories detected
- Early-exit once all flags set

Used by: `ChatInterceptor` (PRE_COLONY prompt) and `PreColonyScoutTicker`.

---

## PreColonyScoutTicker

- Registered on `NeoForge.EVENT_BUS` as `ServerTickEvent.Post`
- 1200-tick interval

**Guards per player:**
1. PRE_COLONY state
2. Not flying
3. Moved ≥64 blocks in X or Z since `lastObservedPos`
4. `BookAdvisorEntity` owned by player within 64-block AABB

**`lastObservedPos` updated before LLM fire** — prevents double-fire if response is slow.

**Prompt context:** terrain labels from `TerrainScanner.scan()`, biome, Y, time, weather. 1-in-7 chance adds dry humor directive.

**Village proximity warning** (independent `findNearestMapStructure` at 150-block radius): distance rounded to nearest 50 blocks, 8-point compass. Injects iron golem hostility and pillager raid escalation warning into LLM context when village detected.
