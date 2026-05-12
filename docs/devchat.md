# DragonTweaks — Current State Document
*Replaces the original devchat.md as the active reference for Claude Code.*
*Full session history archived in devchat_archive.md — do not delete.*
*Last updated: 2026-05-12 (session 22)*

---

## How To Operate

You execute one instruction at a time. After completing each instruction you STOP and report what you did. You do not proceed to the next step without explicit direction. You do not plan ahead. You do not explore. You do not search for context unless a specific instruction tells you to open a specific file and look for a specific thing. When in doubt, stop and ask.

---

## Project Identity

| Field | Value |
|---|---|
| Mod Name (working) | The Assistant Mod (final name TBD) |
| Repo | https://github.com/Senseidragon/dragontweaks |
| Branch | phase-1 |
| Mod Loader | NeoForge 21.1.226 |
| Minecraft | 1.21.1 |
| Java | 21+ |
| Mod ID | dragontweaks |
| Package | io.github.senseidragon.dragontweaks |

---

## Hard Architectural Rules — Never Violate

1. **Nothing may ever block the main Minecraft game thread.**
2. **All network calls (including OpenRouter HTTP requests) must be async on a separate thread.**
3. **Responses from async operations must be queued back to the main thread via server tick queue before any game interaction.**
4. **Zero interference with MineColonies internals.** All integration via public API and event system only. Citizens are never directly modified.
5. **All MineColonies API dependencies must be verified against stubs in `docs/stubs/` before any dependent code is written.**

---

## LLM Backend — COMPLETE. NO ACTION REQUIRED.

`OllamaClient.java` is gone. It has been fully replaced by `LLMClient.java` using OpenRouter. Do not look for OllamaClient. Do not rename or migrate anything.

| Parameter | Value |
|---|---|
| Provider | OpenRouter |
| Endpoint | https://openrouter.ai/api/v1/chat/completions |
| Model | google/gemma-4-26b-a4b-it |
| Auth | `Authorization: Bearer <key>` — key in `.env` only, never hardcoded, never committed |
| Request format | OpenAI messages array: `[{"role":"system","content":"..."},{"role":"user","content":"..."}]` |
| max_tokens | 200 — always, never omit |
| stream | false — always |

LLM responses are immersion only — flavor text, not functional output. Game logic must never depend on response content. Fallback templates are always acceptable.

---

## What Exists Right Now

All source files are in `src/main/java/io/github/senseidragon/dragontweaks/`.

| File | Status | Notes |
|---|---|---|
| `AssistantCommand.java` | ✅ Complete | `/assistant` command handler — stop command now calls setHomePosition() to anchor wander radius at current position (commit 1335c95); `/assistant revoke <citizenName>` subcommand: searches all levels for an assigned citizen by name, revokes role, handles advisor state transition to COLONY_NO_CITIZEN + BookAdvisorEntity swap if role was advisor; `/assistant nickname <partial> <nickname>` subcommand added 2026-05-12 |
| `AssistantEntity.java` | ✅ Complete | NPC entity, follow/stop, proximity, role, NBT persistence; idle wander (WaterAvoidingRandomStrollGoal), LookAtPlayerGoal, and per-player greeting system added 2026-05-05; wander radius constraint via restrictTo() and MoveTowardsRestrictionGoal added 2026-05-05 (commit 1335c95) |
| `AssistantRenderer.java` | ✅ Complete | Placeholder zombie renderer |
| `AssistantRoleRecord.java` | ✅ Complete | Data record: citizenId (int), roleType, assignmentTimestamp, playerUUID, shadowEntityUUID — citizenId added 2026-04-30; shadowEntityUUID is intentional, do not remove |
| `ChatInterceptor.java` | ✅ Complete | Intercepts player chat, routes to LLM, multi-NPC addressing; PRE_COLONY BookAdvisorEntity prompt now includes block scan terrain labels (32×8×32 radius, step 2) added 2026-05-10; scanTerrainLabels extracted to TerrainScanner 2026-05-10; COLONY_NO_CITIZEN BookAdvisorEntity routing block added 2026-05-10 (session 21); citizen name lookups now resolve nickname via NicknameData.resolve() 2026-05-12 |
| `TerrainScanner.java` | ✅ Complete | Shared terrain scan utility. `public static String scan(ServerLevel, BlockPos)`. Extracted from ChatInterceptor. Used by both ChatInterceptor and PreColonyScoutTicker. Added 2026-05-10. Village detection updated 2026-05-12: replaced block-scan heuristic (BELL/HAY_BLOCK/etc.) with `ServerLevel.findNearestMapStructure(StructureTags.VILLAGE, center, 19, false)` (~300-block radius); label now includes compass direction and approximate distance e.g. "village to the northeast (~84 blocks)" via private `compassDir(dx, dz)` helper (8-point). Structures label suppressed when village found. |
| `PreColonyScoutTicker.java` | ✅ Complete | Proactive PRE_COLONY scouting observations. 1200-tick interval. Guards: PRE_COLONY state, not flying, moved ≥64 blocks in X or Z, BookAdvisorEntity within 64 blocks. Calls TerrainScanner.scan(), builds custom system prompt, fires async LLM via LLMClient.query(). 1-in-7 dry humor directive. Updates lastObservedPos per player. Added 2026-05-10. Village proximity check added 2026-05-12: independent `findNearestMapStructure` call (150-block radius), distance rounded to nearest 50 blocks, 8-point compass direction — injects iron golem hostility and pillager raid escalation warning into LLM context. System prompt updated to include "village proximity and colony placement risk". `lastObservedPos.put()` moved earlier (before LLM fire) to prevent double-fire on slow responses. |
| `Config.java` | ✅ Complete | NeoForge ModConfigSpec. See Config section below. ADVISOR_COMMUTE_THRESHOLD, ADVISOR_HAPPINESS_THRESHOLD_RED, ADVISOR_HAPPINESS_THRESHOLD_YELLOW added 2026-05-07. |
| `ConversationMemory.java` | ✅ Complete | Per-NPC conversation history |
| `DragonTweaks.java` | ✅ Complete | Main mod class, event bus registration — LevelEvent.Load handler added 2026-05-01; MineColonies CitizenDiedModEvent and BuildingConstructionModEvent handlers added 2026-05-05, guarded by ModList.isLoaded check; CitizenInteractDetector registered 2026-05-06; ColonyCreatedModEvent, ColonyDeletedModEvent, advisor citizen-lost logic added 2026-05-08; registerServerPackets() added 2026-05-08 to register RoleSelectionPacket playToServer handler; CitizenDiedModEvent now calls NicknameData.removeNickname() 2026-05-12 |
| `NicknameData.java` | ✅ Complete | SavedData on overworld, key "dragontweaks_nicknames". Map<String,String> keyed by "colonyId:citizenId". setNickname/getNickname/removeNickname/resolve(). Added 2026-05-12 |
| `DragonTweaksClient.java` | ✅ Complete | Client-only setup. Packet handler registration moved here (session 9): `registerPackets()` added to constructor, registers both panel packet handlers via `ClientPanelHandler`; RoleAssignmentPayload handler added inline (session 15). |
| `DragonTweaksClientEvents.java` | ✅ Complete | Client event bus subscriber |
| `EnvLoader.java` | ✅ Complete | Reads `.env` file for API key |
| `FollowPlayerGoal.java` | ✅ Complete | AI goal for follow behavior |
| `LLMClient.java` | ✅ Complete | OpenRouter async HTTP client — `{"reasoning":{"effort":"none"}}` added to request body 2026-05-07 |
| `ModEntities.java` | ✅ Complete | Entity type registration — `BOOK_ADVISOR` DeferredHolder added 2026-05-08; no attribute registration (Entity subclass, not Mob) |
| `ObservationTicker.java` | ✅ Complete | Proactive NPC observations on server tick — 5 bugs fixed 2026-04-30; greeting trigger loop, raid state-flip poll (IRaiderManager.isRaided()), and fireColonyEventObservation helper added 2026-05-05 |
| `RolePersona.java` | ✅ Complete | Role keyword → persona block mapping |
| `RoleAssignmentData.java` | ✅ Complete | SavedData for role assignments — file exists and is correct; updated 2026-04-30 to match AssistantRoleRecord signature; `getAssignments()` returning `Iterable<AssistantRoleRecord>` added 2026-05-12 |
| `RoleAssignmentScreen.java` | ✅ Complete | Client-side role assignment UI. @OnlyIn(Dist.CLIENT). Extends Screen. Scrollable role list, slot counter, Assign/Cancel buttons. Added 2026-05-08. |
| `RoleAssignmentPayload.java` | ✅ Complete | Server→client packet. Carries citizenName, citizenId, slotsUsed, slotsMax, availableRoles. AVAILABLE_ROLES constant hardcoded to ["Ranch Hand", "Scout", "Advisor", "Planner"]. Added 2026-05-08. |
| `RoleSelectionPacket.java` | ✅ Complete | Client→server packet. Carries citizenId, selectedRole. handleOnServer: LITE_MODE guard, isAssigned re-verify, calls RoleAssignmentData.assign(), confirms to player. Added 2026-05-08. Updated 2026-05-12: when selectedRole is "Advisor", transitions AdvisorStateData to COLONY_WITH_CITIZEN and sets assignedCitizenId — this is the wiring that activates full advisor capability on role assignment. |
| `CitizenInteractDetector.java` | ✅ Complete | PlayerInteractEvent.EntityInteract handler — citizen check, null-safe ICitizenData retrieval, isAssigned guard, dynamic TH-level slot cap, event cancel + debug log; sends RoleAssignmentPayload to player via PacketDistributor.sendToPlayer(). Added 2026-05-06; TODO stub replaced 2026-05-08. |
| `PlannerDependencyRegistry.java` | ✅ Complete | Singleton. Loads `planner_dependencies.json` from classpath at commonSetup. Resolves dependency graph DFS into pre-computed per-building chains. Public: `getChain(String)`, `findMatches(String)` returning `MatchResult` with exact and substring suggestion lists. Added 2026-05-07. Updated session 9: parses `auto_satisfied` field from JSON into `autoSatisfiedIds`; exposes `isAutoSatisfied(String)`. |
| `OpenAdvisorPanelPacket.java` | ✅ Complete | Network packet (server→client). Carries `AdvisorPanelPayload`. `handleOnClient` removed session 9 — handler lives in `ClientPanelHandler`. Serialization/deserialization unchanged. Added 2026-05-07. |
| `OpenPlannerPanelPacket.java` | ✅ Complete | Network packet (server→client). Carries `PlannerPanelPayload`. `handleOnClient` removed session 9 — handler lives in `ClientPanelHandler`. Serialization/deserialization unchanged. Added 2026-05-07. |
| `ClientPanelHandler.java` | ✅ Complete | `@OnlyIn(Dist.CLIENT)`. Two static handler methods: `handleAdvisorPanel` and `handlePlannerPanel`. Each calls `Minecraft.getInstance().setScreen()` via `ctx.enqueueWork()`. Registered in `DragonTweaksClient`. Added session 9. |
| `AssistantPanelCommand.java` | ✅ Complete | Handles `/assistant advisor` and `/assistant planner` subcommands. Sends `OpenAdvisorPanelPacket` / `OpenPlannerPanelPacket` to the executing player. Added 2026-05-07. |
| `ColonyDiagnosticCache.java` | ✅ Complete | Per-colony cache wrapping ColonyDiagnosticReport. TTL 30s. Static `getOrGenerate(IColony)` + `invalidate(int colonyId)`. Added 2026-05-07. |
| `ColonyDiagnosticReportGenerator.java` | ✅ Complete | Single public method: `generate(IColony)` → `ColonyDiagnosticReport`. Four private helpers: `buildCitizenRecords()` — iterates all citizens, resolves name/job/work pos/home pos/commute distance (XZ Euclidean)/happiness factors (10 canonical via HappinessConstants) with red+yellow flags; `buildBuildingRecords()` — type (translation key), level, built, pending, first assigned worker name; `collectEnvironmentalFlags()` — DAYLIGHT_CYCLE_DISABLED (GameRules), RAID_ACTIVE (isRaided()), THUNDERSTORM; `countBedCapacity()` — sums beds from built Residence (×2) and Tavern (×4) by translation key substring match (TODO: verify substrings against 1.21.1 sources); `determineRootCause()` — maps worstFactor + home/work buildings + commute + redFactors list to `RootCause` enum (food always returns FOOD_SUPPLY_CHAIN pending Cook/Restaurant key verification TODO). **Systemic pattern detection IS here**: Phase 3 in `generate()` — NEWLY_FOUNDED_ALL_RED (colonyDay < 3 AND all citizens have ≥1 red factor), ALL_CITIZENS_RED (all have ≥1 red), HOUSING_SLEEP_COMMUTE_CLUSTER (≥2 citizens with commute > threshold AND housing red AND sleep red); first match wins. **Per-citizen root cause analysis IS here**: Phase 4 in `generate()` — picks lowest-happiness citizen (tiebreak by name), computes commuteDistance, finds worstFactor and redFactors list, calls determineRootCause(). Phase 4 is skipped entirely when a systemic pattern is detected. All diagnostic output fields (targetCitizen, worstFactor, redFactors, rootCause, commuteDistance, commuteFlagged, systemicDetected, systemicPattern) are populated here and stored directly on `ColonyDiagnosticReport`. |
| `BookAdvisorEntity.java` | ✅ Complete | Lightweight floating entity. Extends `Entity` (not PathfinderMob). No AI goals. No combat. State-aware tick: PRE_COLONY follows player with yaw-relative offset; COLONY_NO_CITIZEN and COLONY_WITH_CITIZEN follow within colony bounds, hold position within ADVISOR_BOUNDARY_DETECTION_RANGE of entity, snap to Town Hall if beyond that range. Colony looked up via IColonyManager.getInstance().getIColony(). MineColonies guard via ModList.isLoaded(). Glow via `setGlowingTag(true)`. NBT persists ownerUUID only. Rendered by BookAdvisorRenderer. Updated 2026-05-08. |
| `BookAdvisorRenderer.java` | ✅ Complete | `@OnlyIn(Dist.CLIENT)`. Extends `EntityRenderer<BookAdvisorEntity>`. Reads `AdvisorState` from overworld `AdvisorStateData` client-side via `mc.getSingleplayerServer()`. Renders `Items.WRITABLE_BOOK` when state is `COLONY_WITH_CITIZEN`, `Items.BOOK` for all other states — this is the book-to-book-and-quill visual swap. Y-rotation animation: one full rotation per 80 ticks. Verified working in-game 2026-05-12. |
| `AdvisorState.java` | ✅ Complete | Enum: DORMANT, PRE_COLONY, COLONY_NO_CITIZEN, COLONY_WITH_CITIZEN. Added 2026-05-08. |
| `AdvisorStateData.java` | ✅ Complete | SavedData. Per-player map keyed by UUID. Fields: advisorState, buildToolTriggerFired, assignedCitizenId (nullable), advisorEntityUUID (nullable). Attached to overworld, key "dragontweaks_advisor_state". Added 2026-05-08. |
| `AdvisorHotbarWatcher.java` | ✅ Complete | `PlayerTickEvent.Post` listener on NeoForge.EVENT_BUS. Server-side only. DORMANT state only — returns immediately in any other state. Scans hotbar slots 0–8 for `structurize:sceptergold` via `BuiltInRegistries.ITEM.getKey()`. On first detection: sets buildToolTriggerFired, transitions state to PRE_COLONY, spawns one BookAdvisorEntity in player's current level, stores entity UUID in AdvisorStateData. AdvisorStateData read/written from overworld SavedData. Added 2026-05-08. |
| `AdvisorThrottleData.java` | ✅ Complete | SavedData. Stores fired throttle keys as Set<String>. Keys: `"{colonyId}:{citizenId}:{colonyDay}"` (citizen) or `"{colonyId}:systemic:{pattern}:{colonyDay}"`. Attached to overworld level. Added 2026-05-07. |
| `AdvisorDiagnosticLoop.java` | ✅ Complete | Tick-driven Observe→Diagnose loop. 600-tick interval + dirty flag per colony. Async via CompletableFuture. Throttle check + player delivery on main thread via server.execute(). LLM fired via LLMClient.observe(). Added 2026-05-07. |
| `AdvisorPanelPayload.java` | ✅ Complete | Server-side data preparation class. Static `build(IColony)` calls `ColonyDiagnosticCache.getOrGenerate()` and assembles payload: environmental flags, systemic pattern (nullable), colony summary header, citizen list ordered red→yellow→healthy with alpha within tiers. Per-citizen collapsed fields (name, worst factor ID+value, tier severity, additional complaints badge, commute flag) and expanded fields (all 10 canonical factors with value+modifier label, commute distance+threshold). Reads three threshold config keys at runtime. Added 2026-05-07. |
| `PlannerPanelPayload.java` | ✅ Complete | Server-side data preparation class. Static `build(IColony, String goalInput)`. Snapshot mode (goalInput null): calls ColonyDiagnosticCache, computes WorkerHeader and BedHeader, generates Recommendation list ordered crisis-first then shortest-chain. Goal input mode: calls PlannerDependencyRegistry.findMatches()+getChain(), annotates each ChainStep completed/firstIncomplete, builds CostEstimate (stepsRemaining + research names). Per-step completion verified via BuildingEntry.getRegistryName() matching against BUILDING_HOLDERS map (DeferredHolder.getId()), and ILocalResearchTree.isComplete() for RESEARCH steps. In-progress annotation via claimed BUILD/UPGRADE work orders matched to building types. Materials list empty pending per-building material API verification; getMatchingItemStacksInWarehouse() call pattern documented in TODO comment. Added 2026-05-07. Updated session 9: `isStepComplete()` checks `isAutoSatisfied()` first for BUILDING steps; `generateRecommendations()` skips recommendations for already-built buildings with 0 stepsRemaining (exempt: `residence`, `guard_tower`). |
| `AdvisorPanelScreen.java` | ✅ Complete | Client-side GUI (`@OnlyIn(Dist.CLIENT)`). Extends `Screen`. Constructor takes `AdvisorPanelPayload`. Renders: environmental warnings banner (conditional, amber), systemic pattern banner (conditional, dark red), colony summary header (happiness + citizen count/housing cap), paginated citizen list (5 items/page, Prev/Close/Next nav buttons). Per-citizen collapsed row: severity dot, expand arrow, name, worst factor+value, +N additional complaints badge, [far] commute flag. Per-citizen expanded section: all 10 canonical factors with value+severity dot+modifier type label, commute line with threshold comparison. Expand/collapse state persists per citizen index until panel closes (cleared in onClose). mouseClicked() tracks accumulated y through expanded rows to correctly hit-test header rows. renderables iterated directly (public final field) after custom content — renderBackground() called once, super.render() not called. All class names verified against NeoForge 1.21.1 sources. Build clean. Added 2026-05-07. |
| `PlannerPanelScreen.java` | ✅ Complete | Client-side GUI (`@OnlyIn(Dist.CLIENT)`). Extends `Screen`. Constructor takes `PlannerPanelPayload` + nullable `Consumer<String> goalCallback`. `updatePayload()` for packet-driven refresh. Snapshot mode: worker/bed header strip, paginated recommendations (3/page) each showing crisis badge `[!]/[ ]`, target name, `[In Progress]` annotation, immediate blocker, inline research prereq, worker/bed constraint lines, steps remaining. Goal input mode: EditBox at top, Enter intercepted in `keyPressed()` (InputConstants.KEY_RETURN=257), calls `goalCallback`. On exact match: cost estimate block (steps remaining + research names), collapsible materials list (defaulted collapsed, `matsToggleY` field tracks toggle row for mouseClicked), paginated dependency chain (8/page) with ✓ greyed completed steps, ► highlighted first incomplete, plain white remaining. On no match: "Did you mean" suggestions or "No suggestions found." — never blank. All class names (Screen, Button, EditBox, GuiGraphics, InputConstants) verified against NeoForge 1.21.1 sources. Build clean. Added 2026-05-07. |

---

## Config.java — Known Values

Verify exact field names against source before referencing.

**Currently exists:**
- `LLM_ENDPOINT` — OpenRouter endpoint URL
- `LLM_MODEL` — model string, default `google/gemma-4-26b-a4b-it`
- `NPC_OBSERVATIONS_ENABLED` — boolean, default true
- `NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS` — int, default 5
- `NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS` — int, default 180
- `ROLE_SLOTS` — int, default 3, range 1–8
- `COMMAND_PROXIMITY` — int, default 10, range 4–32
- `NPC_AWARENESS_RADIUS` — int, default 16, range 4–64
- `NPC_AWARENESS_CATEGORY` — String, default "PASSIVE"
- `LLM_ENABLED` — boolean, default true
- `LLM_TIMEOUT_SECONDS` — int, default 90, range 5–180
- `FLAVOR_NPC_GREETING_CHANCE` — double, default 0.07, range 0.0–1.0
- `FLAVOR_NPC_GREETING_COOLDOWN_TICKS` — int, default 12000, range 1200–144000
- `FLAVOR_NPC_WANDER_RADIUS` — int, default 5, range 2–20
- `ADVISOR_COMMUTE_THRESHOLD` — int, default 80, range 10–500
- `ADVISOR_HAPPINESS_THRESHOLD_RED` — double, default 0.5, range 0.0–1.0
- `ADVISOR_HAPPINESS_THRESHOLD_YELLOW` — double, default 0.9, range 0.0–1.0
- `ADVISOR_ENTITY_OFFSET` — double, default 1.8, range 0.5–5.0
- `ADVISOR_HOTBAR_CHECK_TICKS` — int, default 40, range 10–200
- `ADVISOR_BOUNDARY_DETECTION_RANGE` — int, default 40, range 10–200
- `ADVISOR_WHISPER_THRESHOLD` — int, default 120, range 40–500
- `ADVISOR_FORCE_PRIVATE` — boolean, default false

**Does not exist yet — to be added:**
- *(none — `COMMAND_RADIUS` was eliminated; detection radius and command radius are the same value, read from existing detection config. Do not add a separate COMMAND_RADIUS entry.)*

---

## Design Decisions — Locked

### Proximity Threshold
- 10 blocks XZ radius, ±5 blocks Y tolerance (cylindrical, not spherical)
- Detection radius and command radius are the same value — one config entry, not two. Never hardcode. All proximity checks read from config at runtime.

### Role Slot Expansion
- Tied to Town Hall level. No separate building.
- Default (TH below level 3): 3 slots / TH Level 3: 4 slots / TH Level 4: 5 slots / TH Level 5: 6 slots
- Hard cap: 6
- No hardcoded `MAX_SLOTS` constant anywhere. Query TH level dynamically at runtime.

### Scout Patrol
- Periodic AABB detection sweep on a timer. No pathfinding. No waypoints. No navigation goal stack.
- Applies to Phase 3 — not current work. Do not implement anything for this now.

### Pre-Colony Advisor — State Machine

The Advisor exists in one of four named states. State must be persisted in
SavedData attached to the player, not in entity NBT alone. Entity NBT does not
survive despawn. SavedData survives server restarts.

State enum: DORMANT, PRE_COLONY, COLONY_NO_CITIZEN, COLONY_WITH_CITIZEN.
Degraded states are re-entries into existing enum values, not separate values.
One Advisor entity per player maximum — enforce on spawn.
Per-player state — multiple players on the same server have fully independent
Advisor state.

---

#### State 0 — DORMANT
- No build tool has ever entered this player's hotbar.
- Zero background activity. No ticks. No listeners of any kind except the one
  passive inventory change listener waiting for a build tool hotbar event.
- No entity exists. Nothing is allocated. Completely silent.

---

#### State 1 — PRE_COLONY (Simple Book, Player-Attached)
**Entry trigger:** Build tool enters the player's hotbar for the first time
ever. One-time per-player trigger. Does not re-fire on subsequent hotbar
additions. Player record is created in SavedData at this moment.

**Entity:** Simple floating book. Follows the player at all times.
- Maintains a positional offset from the player: approximately 1.5–2 blocks
  to one side and slightly behind. Never at player coordinates. Offset value
  must be a config entry. Not hardcoded.
- Offset repositions gracefully when the player turns — not a rigid fixed
  vector, not a snap.
- Entity has a visual glow effect (Minecraft native glowing effect, equivalent
  to spectral arrow rendering). Visible in the dark. Not a world light source.
  No dependency on dynamic lighting mods. Verify glow application against
  NeoForge 1.21.1 before implementing — do not assume 1.20.x method carries
  over.
- Visible to all nearby players at all times.

**Visibility toggle (hotbar check):**
- Book is visible only while a build tool is present anywhere in hotbar slots
  1–9. If build tool leaves the hotbar entirely (main inventory, chest,
  container, backpack), book despawns silently. Respawns when build tool
  returns to hotbar.
- Hotbar check runs every 40 ticks (2 seconds). Config value. Not hardcoded.
- This check is ONLY active in PRE_COLONY state and in the degraded PRE_COLONY
  re-entry after colony destruction. Completely inactive in COLONY_NO_CITIZEN
  and COLONY_WITH_CITIZEN. Do not run it during colony states.

**Communication:** Responds to any nearby player chat within proximity
threshold. No keyword required in this state.
- No follow or stop commands. These do not apply to the Advisor at any state.

**Capability — two awareness tiers:**
- Tier 1 (Sensory): Immediate surroundings at player position. Nearby terrain,
  water, forest coverage, elevation, hostile mobs within detection radius.
  Position-dependent — updates as player moves.
- Tier 2 (Colony knowledge): Empty in PRE_COLONY — no colony exists.
- Biome-aware: Advisor adjusts advice tone and content to current biome.
  Queried from World.getBiome() at player position. A Jagged Peaks biome
  warrants materially different site advice than a Plains or River biome.
- May comment on terrain, water proximity, forest coverage, defensibility,
  elevation, and biome suitability for a colony. Must not reference colony
  data, citizens, buildings, or happiness — none of these exist yet.
- LLM system prompt must explicitly scope responses to pre-colony context only.

**Build tool item ID** must be verified against the MineColonies item registry
before implementation. Do not assume or hardcode an item name or ID.

---

#### State 2 — COLONY_NO_CITIZEN (Simple Book, Colony-Attached)
**Entry trigger:** ColonyCreatedModEvent fires for this player's colony.

**On transition:** Advisor delivers one unprompted message (LLM or template):
"A colony has been established. I'll be staying close from now on. If you need
me, say 'Advisor' followed by your question — but only while you're within the
colony bounds."
This message fires once and is not repeated.

**Entity:** Simple floating book. Now colony-attached.
- Always follows the player while the player is within colony bounds.
  No follow or stop commands.
- If player exits colony bounds: Advisor holds its last valid in-bounds
  position as long as the player remains within detection range of that
  position. Detection range for this check is a separate config value from
  COMMAND_PROXIMITY — suggested default 32–48 blocks.
- If player moves beyond that detection range: entity snaps instantly to Town
  Hall block and waits. Snap is immediate, not a pathfind.
- When player returns within colony bounds and within detection range, entity
  resumes following.
- Hotbar visibility toggle is fully inactive in this state. Book is always
  visible within colony bounds regardless of hotbar contents.
- Visible to all nearby players at all times.

**Communication:** Responds only within colony bounds. Keyword "Advisor"
(case-insensitive) required at the start of a chat message.

**Capability:**
- Tier 1 (Sensory): Immediate surroundings at player's current position.
  Same as PRE_COLONY sensory tier.
- Tier 2 (Colony knowledge): Full colony structural data — all buildings,
  positions relative to Town Hall, levels, assigned workers — queried from
  MineColonies API. Available regardless of where within the colony the player
  is standing. Advisor may reference buildings not immediately visible:
  "The Forester's Hut is about 80 blocks northeast of the Town Hall."
  This is colony knowledge, not sensory. LLM prompt must label these
  distinctly so the model does not conflate them.
- Cannot run full Observe → Diagnose → Recommend loop. No citizen anchor.
- Proactively announces when first colonists arrive and prompts player to
  assign one of the arriving citizens to the Advisor role.

---

#### State 3 — COLONY_WITH_CITIZEN (Book and Quill, Full Capability)
**Entry trigger:** Player assigns an unemployed citizen to the Advisor role
via the role assignment UI.

**On transition:** Simple book despawns. Book-and-quill spawns in its place.
The visual change is the passive signal that full capability is unlocked.
No popup or notification beyond the entity change itself.

**Entity:** Floating book-and-quill. Colony-attached. Same movement rules as
COLONY_NO_CITIZEN — follows player within colony bounds, holds position at
boundary while player is within detection range, snaps instantly to Town Hall
if player goes beyond detection range. Visible to all nearby players.

**The shadow entity follows the player, not the citizen.** The citizen is the
name and personality anchor only. No part of the entity movement or
pathfinding system tracks the citizen's position. The citizen continues their
MineColonies work completely unmodified and untracked.

**Communication:**
- Primary trigger: citizen's name (case-insensitive). Example: "Joe, what
  should I prioritize?"
- Fallback trigger: keyword "Advisor". When used, Advisor responds and reminds
  player of citizen name in character: "You know, you can just call me Joe."
- Colony bounds restriction applies. No response outside colony bounds.

**Capability:**
- Tier 1 (Sensory): Immediate surroundings at player position.
- Tier 2 (Colony knowledge): Full colony structural data plus full per-citizen
  data. Available anywhere within colony bounds.
- Full Observe → Diagnose → Recommend loop active.
- All ten happiness factors, commute distance calculation, research tree
  queries, dependency chain analysis — all active.
- Colony boundary check: colony.isCoordInColony(world, pos). Verify method
  signature against stubs before implementation.

---

#### Degraded State — COLONY_WITH_CITIZEN → COLONY_NO_CITIZEN
**Entry trigger:** CitizenJobChangedModEvent (citizen received a real job
assignment) or CitizenDiedModEvent (citizen died).

- Book-and-quill despawns. Simple book respawns at Town Hall.
- Capability degrades to COLONY_NO_CITIZEN level.
- Citizen name no longer a valid trigger. Keyword "Advisor" required again.
- Advisor delivers one unprompted notification that the role is vacant and
  prompts player to assign a new citizen.
- This is a return to State 2, not a new state.

---

#### Degraded State — Any Colony State → PRE_COLONY
**Entry trigger:** ColonyDeletedModEvent fires for this player's colony.

- All Advisor entities despawn. Simple book respawns at player's current
  position.
- PRE_COLONY behavior reinstates fully: follows player freely, hotbar
  visibility toggle reactivates at 40-tick interval, keyword restriction
  lifted, responds to any nearby chat.
- Capability resets to pre-colony sensory and biome awareness only.
  Tier 2 colony knowledge clears entirely.
- Player's one-time build tool trigger flag is preserved — Advisor does not
  re-trigger from scratch. It resumes PRE_COLONY behavior from current
  player position immediately.

---

### Advisor Response Delivery

Applies at all states. Governs how Advisor responses reach players.

**Short responses** (response length below threshold):
- Delivered to public chat.
- Format: "[CitizenName]: [response text]"
- In PRE_COLONY: "Advisor: [response text]"
- In COLONY_NO_CITIZEN: "Advisor: [response text]"

**Long responses** (response length at or above threshold):
- Public message drawn from a small template pool (2–3 variants). Never
  LLM-generated — must be instant.
- Private message: full response text delivered only to the triggering player.

Public whisper template pools by state:

COLONY_WITH_CITIZEN (citizen name known):
  - "[CitizenName] whispers something to [PlayerName]."
  - "[CitizenName] leans over and murmurs to [PlayerName]."
  - "[CitizenName] speaks quietly with [PlayerName]."

COLONY_NO_CITIZEN:
  - "The advisor whispers something to [PlayerName]."
  - "The advisor murmurs quietly to [PlayerName]."

PRE_COLONY:
  - "Your advisor murmurs something to you."
  - "The book rustles quietly near [PlayerName]."

**Threshold:** Config value. Suggested default: 120 characters.

**Server operator override:** Config toggle to force all responses to private
delivery regardless of length. When active, public whisper message still fires
so nearby players have a visual cue — only the full response text is suppressed
from public chat.

---

### Advisor — Implementation Verification Required Before Coding

These items must be verified against sources or stubs before any dependent
code is written. Do not assume. Do not hardcode.

| Item | Verification Target |
|---|---|
| Build tool item ID | MineColonies item registry |
| Glow effect application | NeoForge 1.21.1 entity rendering |
| colony.isCoordInColony() signature | MineColonies API stubs |
| ColonyCreatedModEvent | Already confirmed in API reference — use it |
| ColonyDeletedModEvent | Already confirmed in API reference — use it |

---

### Advisor — SavedData Fields (Per Player)

| Field | Type | Notes |
|---|---|---|
| advisorState | AdvisorState enum | DORMANT / PRE_COLONY / COLONY_NO_CITIZEN / COLONY_WITH_CITIZEN |
| buildToolTriggerFired | boolean | True once build tool has ever entered hotbar. Never resets. |
| assignedCitizenId | Integer (nullable) | Null unless COLONY_WITH_CITIZEN |
| advisorEntityUUID | UUID (nullable) | Null when entity is despawned |

---

### Advisor — Config Values Required

| Config Key | Type | Default | Notes |
|---|---|---|---|
| ADVISOR_ENTITY_OFFSET | double | 1.8 | Blocks offset from player. Not hardcoded. |
| ADVISOR_HOTBAR_CHECK_TICKS | int | 40 | Hotbar poll interval. PRE_COLONY only. |
| ADVISOR_BOUNDARY_DETECTION_RANGE | int | 40 | Blocks — range before snap to Town Hall. |
| ADVISOR_WHISPER_THRESHOLD | int | 120 | Characters — above this triggers whisper pattern. |
| ADVISOR_FORCE_PRIVATE | boolean | false | Server operator override. Forces all responses private. |

### Lite Mode vs Full Mode

The mod detects its operating mode at startup based on API key validity.
Mode is set once at startup and does not change during a session.

**Mode detection logic:**
- API key present, non-blank, non-placeholder → Full mode
- API key absent, blank, or placeholder value → Lite mode
- No crash. No fail-to-start. Mode flag is set and respected throughout.
- The existing IllegalStateException in DragonTweaks.java commonSetup() must
  be replaced with a mode flag assignment. Do not throw on missing key.

**One-time lite mode notification:**
On first world load in lite mode, deliver a single chat message to the player:
"Assistant Mod is running in Lite mode. Add an OpenRouter API key in config
to unlock full AI companion features."
Fires once per player. Does not repeat on subsequent loads.

---

#### Full Mode
Everything designed in this document applies. No restrictions beyond those
already specified per feature.

---

#### Lite Mode

**What exists in lite mode:**
- Colony diagnostic data layer (ColonyDiagnosticReport — see Dashboard section)
- `/assistant advisor` command — opens Advisor diagnostic panel
- `/assistant planner` command — opens Planner dependency chain panel
- Both panels read from cached ColonyDiagnosticReport

**What does not exist in lite mode:**
- No entities of any kind. No floating books, no book-and-quill, no flavor
  NPCs, no shadow entities. Entity presence without LLM response capability
  is visual noise with no gameplay value.
- No citizen role assignment. The role assignment UI does not open in lite
  mode. Right-clicking a citizen does nothing mod-related. CitizenInteractDetector
  must check mode flag and return early if lite mode is active.
- No Ranch Hand role. Physical world interaction without personality is
  meaningless.
- No Scout role. Patrol reporting without conversational output is meaningless.
- No Advisor or Planner entities following the player. Panel access is
  command-driven only.
- No pre-colony floating book. No build tool hotbar trigger.
- No whisper pattern. No LLM acknowledgment messages. No template responses.

**Lite mode commands:**
- `/assistant advisor` — opens Advisor diagnostic panel
- `/assistant planner` — opens Planner dependency chain panel
- `/assistant` with no subcommand — prints available commands to player

These commands are available in lite mode only. In full mode, interaction is
through the Advisor entity and citizen name triggers, not commands. The panel
can still be opened in full mode via command as a supplementary view — it is
not lite-mode exclusive, but it is lite-mode's primary interface.

---

### Colony Diagnostic Report (ColonyDiagnosticReport)

The central data object produced by the diagnostic layer. Generated by the
same code regardless of mode. Both the LLM context injection (full mode) and
the dashboard panels (both modes) read from this object.

**Generation:**
- Async. Never on the main game thread.
- Cached with a short TTL. Suggested default: 30 seconds. Config value.
- Cache invalidated early on relevant colony events:
  - BuildingConstructionModEvent
  - CitizenJobChangedModEvent
  - CitizenAddedModEvent
  - CitizenDiedModEvent
- Both panels and LLM context always read from cache. Never re-poll on demand.

**Contents:**
- Colony metadata: name, citizen count, housing cap, Town Hall level,
  overall happiness score
- Per-citizen records: name, job, work building position, home building
  position, commute distance (derived), full happiness factor breakdown
  (all ten canonical factor IDs with factor value and weight), red/yellow
  flag per factor
- Flagged issues list: ranked by severity, each issue citing the specific
  API value that triggered it
- Actionable recommendations list: each recommendation includes full
  dependency chain, blocking dependencies surfaced first
- Research snapshot: completed list, in-progress list with progress in ticks,
  not-started status for known prerequisite researches
- Building inventory: all buildings with level, built status, pending
  construction status, assigned worker
- Environmental context: doDaylightCycle state, time of day, weather,
  ThreatLevel

**Usage in full mode:**
Serialized into structured context block injected into LLM system prompt.
LLM expresses findings conversationally in character.

**Usage in lite mode (and as supplementary view in full mode):**
Rendered directly as dashboard panel UI. No language generation.

---

### Dashboard Panels

Two panels. Both read from ColonyDiagnosticReport. Opened via command in
lite mode; also accessible via command in full mode as supplementary view.

#### Advisor Panel
Displays citizen-level happiness diagnostics and colony health overview.

Content (detail design session required before implementation):
- Colony health summary header
- Per-citizen happiness breakdown — all ten factors, red/yellow flagged
- Commute distance per citizen, flagged if over threshold
- Prioritized issue list with severity indicators
- Environmental warnings (doDaylightCycle false, active raid, etc.)

#### Planner Panel
Displays dependency chain analysis and build recommendations.

Content (DETAIL DESIGN SESSION REQUIRED — do not implement panel content
until this session has been completed and decisions locked here):
- Build recommendations with full dependency chains
- Research prerequisite status
- Worker and bed availability
- Current work order queue
- Blocking dependency surfaced first for each recommendation chain

> **NOTE:** Planner panel content requires a dedicated design session to
> specify exactly what is displayed, in what order, and how dependency
> chains are visualized. This is flagged as incomplete. Do not implement
> Planner panel content until that session occurs and this section is
> updated with locked decisions.

---

### Development Workflow Note

In lite mode during development, the dashboard panels serve as the primary
integration test harness for the diagnostic data layer. Before wiring LLM
responses to ColonyDiagnosticReport output, verify panel data is correct
first. A correct panel in lite mode means the data layer is trustworthy.
LLM integration then becomes a presentation concern only, not a data concern.

---

## What To Build Next

**You will receive one instruction at a time from the player. Do not read ahead and begin implementing future steps. Execute only what you are told.**

The sequence below is for the player's reference. It is not an invitation for you to begin executing all steps at once.

### ~~Step 1~~ ✅ Done — No existing SavedData wiring found
Neither `DragonTweaks.java` nor `AssistantCommand.java` contained any reference to `SavedData` or `dragontweaks_roles`. Confirmed 2026-05-01.

### ~~Step 2~~ ✅ Done — See Step 1

### ~~Step 3~~ ✅ Done — RoleAssignmentData fully wired
`RoleAssignmentData.java` existed and is correct. A `LevelEvent.Load` handler was added to `DragonTweaks.java` that guards for `ServerLevel` and overworld dimension, then calls `RoleAssignmentData.get(serverLevel)` to initialize and attach the SavedData. Player launched the game, loaded a world, confirmed no `SavedData` exceptions and no `dragontweaks_roles` errors in logs. Wiring confirmed good. Confirmed 2026-05-01.

### Pre-Step-4 Tasks — Complete before citizen interaction work

These items are small and targeted. Complete them in order before proceeding to Step 4.

1. ~~**`/assistant locale [code]`**~~ ✅ Done — confirmed working in-game. Locale override fires correctly, Maxine switched to Spanish on `es_es`. `/assistant locale reset` clears override correctly.
2. ~~**Observation ticker idle cooldown tuning**~~ ✅ Code change done — config value only, no logic changes. Needs in-game test to confirm feel is correct.
3. ~~**"Hmm..." refactor**~~ ✅ Done — confirmed absent from all source files via search. Fallback path already in place: `sendFallback()` in `LLMClient.java` lines 163–168, wired to `exceptionally` block at lines 296–300.
4. ~~**LLM hard requirement enforcement**~~ ✅ Done — startup check exists in `DragonTweaks.java` `commonSetup()` at lines 40–44. Checks null, blank, and placeholder key value. Throws `IllegalStateException` with clear message on failure.
5. ~~**SavedData persistence smoke test**~~ — Moot. No command currently writes to `RoleAssignmentData`. Write path does not exist until Steps 4–7 are complete. Not a blocker for Step 4.

### ~~Step 4~~ ✅ Done — `CitizenInteractDetector.java` built and registered 2026-05-06
Subscribes to `PlayerInteractEvent.EntityInteract`. Guards client-side and MineColonies not loaded. Casts entity to `AbstractEntityCitizen`, retrieves `ICitizenData` with null check, reads name/id/job. Checks `isAssigned()` to pass through already-assigned citizens. Computes dynamic slot cap from Town Hall level. Cancels event and logs debug line if unassigned and slot available. RoleAssignmentScreen call is a TODO stub. Build clean.

### ~~Step 5~~ ✅ Done — Network packets (2026-05-08)
- `RoleAssignmentPayload.java` — server→client CustomPacketPayload. Carries citizenName, citizenId, slotsUsed, slotsMax, availableRoles.
- `RoleSelectionPacket.java` — client→server CustomPacketPayload. Carries citizenId, selectedRole. Server handler guards LITE_MODE, re-verifies isAssigned, calls RoleAssignmentData.assign(citizenId, roleType, playerUUID).
- Registered: `playToClient` in DragonTweaksClient.registerPackets(); `playToServer` in DragonTweaks.registerServerPackets() on mod event bus.

### ~~Step 6~~ ✅ Done — `RoleAssignmentScreen.java` (2026-05-08)
- @OnlyIn(Dist.CLIENT), extends Screen. Panel 210×220.
- Title "Assign Role", citizen name, slot counter (slotsUsed / slotsMax).
- Scrollable role list: scissor-clipped, mouseScrolled() wired, rows hit-tested in mouseClicked().
- Cancel closes. Assign button disabled until row selected; on click sends RoleSelectionPacket and closes.

### Step 7 — Add `/assistant revoke <citizenName>` to `AssistantCommand.java`
- Partial case-insensitive name match.
- On match: remove record, release slot, confirm to player.
- No match: inform player clearly.

### Validation
- `./gradlew build` passes
- Right-clicking unemployed citizen opens `RoleAssignmentScreen`
- Screen shows correct citizen name and dynamic slot count
- Assign writes the record
- `/assistant revoke` releases the slot
- Right-clicking assigned citizen passes through to MineColonies normally

---

## MineColonies API Reference

Stubs in `docs/stubs/`. Index at `docs/STUB_INDEX.md`. Load only stubs relevant to the current task.

**Verified API calls:**
- `IMinecoloniesAPI.getInstance().getColonyManager().getIColonyByOwner(world, player)` — get player's colony
- `colony.getServerBuildingManager()` — access building manager
- `ICitizenData.getCitizenHappinessHandler()` — happiness factors
- `CitizenJobChangedModEvent` — role revocation trigger
- `CitizenDiedModEvent` — role cleanup trigger

Town Hall level query — verify against stubs before use.

---

## Session Notes — 2026-04-30

### ObservationTicker.java — 5 bugs found and fixed

All bugs were introduced in commit `d8df91b` ("fix: fix neutralized commentary sequencing and per-mob alert isolation"). The version at `a5ce271` (just before that commit) was clean and correct. The threat subsystem was added in `d8df91b` and broken on arrival.

**Bug 1 — `anyActiveThreats` always false.**
Declared `false`, never set to `true`. The entire threat-detection fire path was dead. Fix: set `anyActiveThreats = true` inside the mob loop when `mob.getTarget() != null`.

**Bug 2 — Tick throttle declared but not wired.**
`tickCounter` and `TICK_INTERVAL = 100` existed as fields but `onServerTick` ran every tick with no guard. Fix: added `if (++tickCounter % TICK_INTERVAL != 0) return;` as the first line of `onServerTick`.

**Bug 3 — Mob tracking loop ran twice.**
The loop updating `lastKnownHealth`, `lastMobTarget`, and `lastMobRef` appeared verbatim at lines 126–133 and again at 166–172. Second occurrence removed entirely.

**Bug 4 — All-clear observe fired for non-threat mobs.**
In the mob-exit cleanup loop, the 30% `LLMClient.observe` call fired whenever any mob left the AABB regardless of whether `npcThreatState` was ever true. Fix: wrapped the observe call with `if (npcThreatState.getOrDefault(npcId, false) && ...)`.

**Bug 5 — `hostilePresentMap` declared and never referenced.**
Field removed entirely.

Build clean after all five fixes.

### Threat state design — confirmed intent

The threat system uses a state-flip model. The NPC comments exactly once per state change:
- Hostiles present when previously clear → one comment, state flips to true
- Hostiles clear when previously present → one comment (30% chance, optional flavor), state flips to false
- Additional mobs arriving during an already-active threat → silence
- Slime splits (new child UUIDs) → silence

Do not add additional tiers or complexity beyond this.

### AssistantRoleRecord.java — citizenId added

The record was missing `int citizenId` as the first field. Added. The full record signature is now:

```java
public record AssistantRoleRecord(int citizenId, String roleType, long assignmentTimestamp, UUID playerUUID, UUID shadowEntityUUID)
```

`shadowEntityUUID` is intentional and correct — do not remove it.

Two call sites in `RoleAssignmentData.java` (`assign()` at line 34, `load()` at line 87) were updated to pass `citizenId` as the first constructor argument. Build clean.

### RoleAssignmentData.java — already existed

The devchat.md incorrectly listed this file as not existing. It was present and correct: proper NeoForge 1.21.1 `SavedData` pattern, all required methods (`assign`, `revoke`, `getRecord`, `isAssigned`, `getAssignedCount`), NBT serialization, static `get(ServerLevel)` factory. Table updated accordingly.

### Audit findings — no action required

- `AssistantEntity.java`: `addAdditionalSaveData` and `readAdditionalSaveData` both correctly implemented against spec.
- `ConversationMemory.java`: `restoreAll` signature is `Map<String, Deque<String>>`, not `Map<String, List<String>>` as the spec text said. The implementation is self-consistent — `AssistantEntity` builds `Deque` objects and passes them in. This is a doc error, not a code error. No fix needed.

---

## Session Notes — 2026-05-01

### Steps 1–3 completed

**Steps 1 & 2 — SavedData wiring check:** Grepped both `DragonTweaks.java` and `AssistantCommand.java` for `SavedData` and `dragontweaks_roles`. Zero matches in both files. No prior wiring existed.

**Step 3 — LevelEvent.Load handler added to DragonTweaks.java:**
Added a `NeoForge.EVENT_BUS.addListener` call in the `DragonTweaks` constructor. The handler casts `LevelEvent.Load.getLevel()` to `ServerLevel`, guards on `serverLevel.dimension() == Level.OVERWORLD`, then calls `RoleAssignmentData.get(serverLevel)`. This initializes the SavedData and attaches it to the level on every overworld load.

**RoleAssignmentData.java corruption fixed:** A prior tool interaction injected raw instruction text into lines 79–80 of the file, breaking the `load()` method. Fixed to `RoleAssignmentData data = new RoleAssignmentData();`. Build clean.

**In-game sanity check passed:** Player launched the game, loaded a world, confirmed no `SavedData` exceptions and no `dragontweaks_roles` errors in logs. Wiring confirmed good.

---

## Session Notes — 2026-05-02 — Design Ponder Session

No code was written this session. This was a design review and decision session covering entity architecture, shadow entity behavior, and flavor NPC representation. All decisions below are locked unless explicitly revisited.

### Follow/Stop mechanism — archived

The follow/stop prototype in `AssistantEntity.java` has proven its concept but has no current production use case. Functional roles (Ranch Hand, Planner, Scout, Architect) are attached to MineColonies citizens whose movement must not be interfered with. The mechanism is not needed for stationary flavor NPCs either.

**Decision:** Move follow/stop code to a test or archive folder. Do not delete — it has future value if a quest-style mechanic is ever built. Document clearly why it exists so it is not mistaken for dead code.

### Citizen role tracking — confirmed approach

Functional roles (Advisor, Planner, Ranch Hand, Scout) do not tag or modify MineColonies citizen entities in any way. We maintain our own UUID lists and write down citizen UUIDs internally. MineColonies citizens go about their work unmodified.

### Flavor NPC visual representation — decided

Flavor-only NPCs (e.g. Cranky Joe) will not use humanoid models. Reasons: no skin decision required, avoids uncanny villager face, visually legible without explanation.

**Decision:** Use non-humanoid floating object entities. Examples: floating book and quill for Advisor, map or compass for Scout, agriculture-adjacent object for Ranch Hand. Exact model per role TBD at implementation time. The point is the object reads as intentional and characterful, not like a glitched item drop.

Rendering must look intentional. Entity must have no default hostile or pathfinding behavior. Must not be an existing mob type that carries combat or physics baggage.

### Flavor NPC movement — decided

Stationary is easier but insufficient for immersion. Flavor NPCs should exhibit limited-area idle wandering within a small radius (approximately 3–5 blocks). This gives them a "living" quality without letting them wander somewhere stupid or get lost.

When a player is speaking to a flavor NPC, it should use `LookAtPlayerGoal` with a proximity threshold so it faces the player during dialogue. An NPC staring at a fence post while being addressed breaks immersion.

### Shadow entity system — Advisor and Planner roles

The Advisor and Planner roles make no sense as stationary entities — their value is in active observation and answering questions. These roles will manifest as shadow entities: visible floating objects that auto-follow the player within colony bounds while the real MineColonies citizen goes about their normal work unaffected.

**Visual:** Shadow entities are visible to the player. Their presence or absence is a passive signal — if your book disappears, you notice, and you know that role slot has opened back up. This is intentional passive UI feedback requiring no popup or notification.

**Follow behavior:** Modeled on wolf follow behavior. Shadow entity pathfinds to follow the player at a respectful distance (approximately 3–4 blocks behind and slightly to the side, not directly behind). If blocked and unable to reach the player within a configurable timeout, it teleports to the player's location — same mechanic as Allays.

**Sky visibility constraint:** Shadow entities may only occupy positions with unobstructed sky access. A raycast straight up from the entity's position must pass through only air, leaves, or other transparent natural blocks. Any crafted block (planks, stone bricks, glass, etc.) terminates the path and the entity stops there and waits. This naturally prevents shadow entities from entering buildings, going underground, or descending staircases. Natural overhangs and jungle canopy are permitted.

**Timeout and retry:** If sky is blocked, retry on a short timer (10–20 seconds) before deciding the position is untenable and falling back. This prevents stranding on legitimate natural terrain features like cliff overhangs.

**Colony boundary behavior:**
- Inside colony bounds, player enters building → entity waits at last valid sky-access position (reads as "waiting at the door"). Reacquires player when player is detected outside.
- Inside colony bounds, player moves far enough away that entity is stuck → entity teleports to player's current location (wolf teleport trigger).
- Outside colony bounds, entity cannot reach player → entity returns to Town Hall and waits.

**Colony boundary is the only MineColonies API dependency for shadow entity movement.** Sky visibility (`level.canSeeSky(BlockPos)`), pathfinding, and teleport fallback are all vanilla Minecraft. The boundary check — detecting when the player has crossed outside colony territory — is the one place the MineColonies API is required. Verify the correct `IColony` boundary query against stubs before writing that code. Advisor and Planner shadow entities must not exit the colony boundary; if the player exits, the shadow entity stops at the boundary and returns to Town Hall.

**Town Hall as default anchor:** When not following, shadow entities return to Town Hall. This is the administrative center and the logical waiting location for an advisor or planner between active sessions with the player.

**Pathfinding scope:** Pathfinding attempts are always constrained to sky-valid positions. The entity will attempt to walk around obstacles (e.g. walk around a building to reach a player who exited the back door) before resorting to teleport. Teleport is a last resort, not a primary movement mechanism.

**Configuration:** Follow timeout before teleport must be a config value. Do not hardcode.

### Quest system — parking lot

A crude quest mechanic (NPC reports lost child → player finds child → escort back → reward) was identified as a theoretically valid use case for the follow/stop mechanism. This is noted for future consideration only. It is so far out of current scope that virtually any other feature takes priority. Do not plan or implement anything for this.

### Ranch Hand — role design decisions

The Ranch Hand is the only shadow entity role with real world-state side effects. It physically manipulates the world — applies leads, moves animals, attaches them to fence posts. This is not flavor text. All other shadow entities are advisory only.

**Visual representation:** Floating lead or lasso. Immediately readable as "this thing catches animals." Fits the role without explanation.

**Boundary behavior:** The Ranch Hand operates beyond colony bounds. Animals do not respect property lines. Operational radius extends a configurable distance beyond the colony boundary. Sky visibility constraint still applies — no cave diving.

**Role as active utility:** The Ranch Hand does not report animal sightings to the player unprompted. It silently accumulates a sighting list as it wanders. When a player issues a fetch request, it consults the list to determine confidence ("I think I saw a cow recently, let me look" vs "Haven't seen one lately but I'll try").

**Sighting memory — data structure:** A short TTL list of entries. Each entry contains: animal type, approximate XZ coordinates, timestamp in game ticks (`level.getGameTime()`). `getGameTime()` is a monotonic tick counter independent of day/night cycle, daylight gamerule, and any time-scaling mods. It is the correct clock for this purpose.

**Sighting memory — dual eviction:**
- **TTL eviction:** entries older than TTL_TICKS are stale and dropped on next list access. TTL target is approximately 10 real-time minutes expressed in ticks (approximately 12,000 ticks). Must be a config value.
- **Cap eviction:** if the list exceeds the maximum entry count after adding a new sighting, the oldest entry is dropped (FIFO). Cap must be a config value. Suggested default: 8–10 entries.

TTL handles quiet colonies where sightings are infrequent. Cap handles busy colonies and modded time environments where TTL alone may not bound list growth. Both mechanisms together ensure the list stays bounded under any server configuration.

**Sighting memory — lazy cleanup:** No background cleanup pass. No scheduled task. Cleanup runs on write: when a new sighting is added, stale entries are swept first, then the new entry is appended, then cap eviction is applied if needed. One pass, all three jobs done.

**Fetch decision logic:** When a player requests an animal type, the Ranch Hand checks its sighting list. If a fresh entry exists for that type, it expresses confidence and goes to look. If no fresh entry exists, it expresses uncertainty but goes anyway. If no relevant facility exists in the colony for that animal type (no cowherder hut for cows, no swineherd hut for pigs, etc.), the Ranch Hand will not attempt to catch that animal type at all — though it still records sightings of it in case a facility is built later.

**Passive collection behavior:** The Ranch Hand acts as a "dog catcher" — it wanders the colony and extended buffer, and when it spots a stray animal for which a facility exists, it leashes it and delivers it to the nearest appropriate facility. It does not always route to the same facility — it routes to the nearest appropriate one relative to where the animal was found. Multiple cowherder huts split the load naturally.

**Multi-animal handling:** When the Ranch Hand has an animal on a lead and spots another stray en route to a facility, it ignores the second animal for now and completes the current delivery first. No chain-leading. One animal at a time.

**Facility loss mid-transit:** If the destination facility is destroyed or removed while the Ranch Hand is en route with a leashed animal, it releases the lead and resumes wandering. No stranded animal situation.

**Wandering behavior:** The Ranch Hand meanders physically through the colony and extended buffer — it does not teleport between locations. This is intentional. Watching it walk an animal back across the colony reinforces that it is doing real work. It also means it surveys the colony organically as a byproduct of movement, feeding its sighting list without a dedicated patrol system.

Wandering is guided, not dumb. The Ranch Hand biases its movement toward areas where it has previously seen animals and toward known facility locations (cowherder hut, swineherd hut, chicken coop, etc.), since animals tend to congregate near their assigned buildings. If it has been in a dead zone for a configurable period with no sightings, it selects a new destination weighted toward productive areas. It does not repeatedly wander the same empty corner hoping something shows up.

---

## Session Notes — 2026-05-05 — Flavor NPC Behavior Design Session

No code was written this session. This was a dedicated design session for Tier 2 flavor NPC behavior. All decisions below are locked unless explicitly revisited.

### What flavor NPCs are

Flavor NPCs are purely ambient and immersive. They do not functionally interact with the world in any way. Their purpose is to make the colony feel alive — through wandering, reacting to events, and responding to player chat. Cranky Joe is the reference implementation and is a complete, correct example of this tier.

### Movement

- Idle wandering within a ~3–5 block radius is always active regardless of player proximity
- `LookAtPlayerGoal` activates when a player enters detection range, so the NPC faces the player during interaction
- No patrol routes, no waypoint navigation — idle wandering is sufficient and intentional

### Greeting Behavior

- When a player enters detection range, the NPC makes a single roll at a configurable chance (default 5–10%, config value) to fire a greeting
- Greeting is generated via LLM and must be appropriate to current time of day and weather
- Cooldown is **per-NPC, per-player** — each player has an independent cooldown tracked on the NPC
- Cooldown duration is a config value (suggested default: ~12,000 ticks, approximately 10 real minutes)
- Player may leave and re-enter detection range freely during cooldown — no re-roll until cooldown expires for that player
- Global cooldown (shared across all players) is explicitly rejected — it makes the NPC appear to ignore other players for no reason

### LLM Call Rule — Universal

LLM calls of any kind (greeting, event reaction, chat response) only fire when at least one player is within detection range of the NPC. No player nearby = no LLM calls. Idle wandering continues regardless. This is consistent with the existing ObservationTicker silent-drop pattern.

### Colony Event Reactions

Flavor NPCs may react to MineColonies colony events for immersive commentary. Reactions are LLM-generated and subject to the universal LLM call rule above.

**Events flavor NPCs react to (initial set):**
- Raid started
- Citizen death
- Building construction complete

All other events are deferred. No functional response to any event — commentary only.

### Config Values Required

| Config Key | Type | Suggested Default | Notes |
|---|---|---|---|
| `FLAVOR_NPC_GREETING_CHANCE` | float | 0.07 (7%) | Roll on player entering detection range |
| `FLAVOR_NPC_GREETING_COOLDOWN_TICKS` | int | 12000 | Per-NPC, per-player cooldown |

Both values must be in `Config.java`. Never hardcode.

---

## Session Notes — 2026-05-12 (session 22) — Nickname system, village proximity warnings, advisor activation wiring

### NicknameData.java — new SavedData

Created `NicknameData.java`. NeoForge SavedData attached to overworld at key `"dragontweaks_nicknames"`. Internal store: `Map<String, String>` keyed by `"colonyId:citizenId"` strings. Public API: `setNickname(int colonyId, int citizenId, String nickname)`, `getNickname(int colonyId, int citizenId)` (nullable), `removeNickname(int colonyId, int citizenId)`, `resolve(int colonyId, int citizenId, String fallback)` — returns nickname if set, fallback otherwise. Static `get(ServerLevel)` factory. Full NBT serialization.

### AssistantCommand.java — /assistant nickname subcommand

Added `/assistant nickname <partialName> <nickname>` (greedyString for nickname to allow spaces). Finds player's colony across all server levels. Partial case-insensitive name match against `colony.getCitizenManager().getCitizens()`. Multiple matches: lists them and asks player to be more specific. Single match: calls `NicknameData.setNickname()` and confirms. No match: informs player. Guards: MineColonies loaded, player has colony.

### ChatInterceptor.java — nickname resolution

Citizen name lookups in COLONY_WITH_CITIZEN trigger detection now resolve via `NicknameData.resolve(colonyId, citizenId, citizenName)`. Players can address their advisor by nickname instead of MineColonies-assigned name.

### DragonTweaks.java — nickname cleanup on death

`CitizenDiedModEvent` handler now calls `NicknameData.removeNickname(colonyId, citizenId)` to clean up any stored nickname when a citizen dies.

### TerrainScanner.java — village detection overhaul

Replaced block-scan village heuristic (BELL, HAY_BLOCK, etc.) with `ServerLevel.findNearestMapStructure(StructureTags.VILLAGE, center, 19, false)` (~300-block scan radius). Label now includes compass direction and approximate distance: e.g. `"village to the northeast (~84 blocks)"`. Compass direction via private `compassDir(dx, dz)` helper (8-point). Structures label suppressed when village is found — the two are mutually exclusive in output.

### PreColonyScoutTicker.java — village proximity warning

Added independent village proximity check using `findNearestMapStructure` at 150-block radius (separate from TerrainScanner's scan). Distance rounded to nearest 50 blocks. 8-point compass direction. When a village is detected, injects a warning into the LLM context advising the player about iron golem hostility and pillager raid escalation risk from placing a colony near a village. System prompt updated to include "village proximity and colony placement risk" in the Speak ONLY about line. `lastObservedPos.put()` moved to before the LLM call to prevent double-fire if LLM response is slow.

### RoleAssignmentData.java — getAssignments()

Added `getAssignments()` returning `Iterable<AssistantRoleRecord>` — exposes all current role assignments for iteration.

### RoleSelectionPacket.java — advisor state activation

`handleOnServer()` now includes an Advisor-specific block: when `selectedRole` equals `"Advisor"` (case-insensitive), reads `AdvisorStateData` from overworld SavedData, transitions state to `COLONY_WITH_CITIZEN`, and sets `assignedCitizenId` to the assigned citizen's id. This is the wiring that upgrades the floating book to book-and-quill and activates full diagnostic capability.

Build clean.

---

## Session Notes — 2026-05-10 (session 21) — COLONY_NO_CITIZEN chat routing

Added COLONY_NO_CITIZEN routing block to `ChatInterceptor.java`.

New `colonyNoBookAdvisor` and `colonyNoColony` variables declared before the combined-targets block. COLONY_NO_CITIZEN search: guards on `!LITE_MODE`, state == COLONY_NO_CITIZEN, `messageLower.startsWith("advisor")`, and MineColonies loaded. Finds player colony via the existing safe loop pattern (matching `isPlayerInColony`). If player is within colony bounds, finds `BookAdvisorEntity` by owner UUID within 64-block AABB. The `if (allTargets.isEmpty() && bookAdvisor == null) return;` guard extended to also include `colonyNoBookAdvisor == null` so the event is not returned early when only a COLONY_NO_CITIZEN entity is present.

LLM block fires after the PRE_COLONY bookAdvisor block. Context: biome, time of day, weather, Y, terrain labels from `TerrainScanner.scan()`, building count from `getServerBuildingManager().getBuildings().size()`, Town Hall level guarded by `hasTownHall()`. Strips "advisor" prefix (case-insensitive) from message before passing to LLM. Persona: "You are Advisor, a colony advisor. You have full knowledge of the colony structure." Response delivered as `[Advisor]: [response]` via `LLMClient.query()` with scoped system prompt.

Build clean.

---

## Session Notes — 2026-05-10 (session 20) — PreColonyScoutTicker and TerrainScanner extraction

Extracted `scanTerrainLabels` private method from `ChatInterceptor.java` into new shared utility `TerrainScanner.java` with `public static String scan(ServerLevel, BlockPos)`. Removed the private method and its unused `BlockPos` and `Blocks` imports from `ChatInterceptor`. Updated the single call site to use `TerrainScanner.scan()`.

Created `PreColonyScoutTicker.java`: `ServerTickEvent.Post` listener at 1200-tick interval. Per-player guards: PRE_COLONY state, not flying, moved ≥64 blocks in X or Z since last observation, BookAdvisorEntity owned by the player within 64-block AABB. Fires `TerrainScanner.scan()`, builds unsolicited scouting system prompt with terrain/biome/Y/time/weather context. 1-in-7 chance appends dry humor directive via `random.nextInt(7) == 0`. Updates `lastObservedPos` before firing LLM call to prevent double-fire if LLM is slow. Delivers via `LLMClient.query()` with custom system prompt — response arrives as `[Advisor]: [text]`.

Registered `PreColonyScoutTicker::onServerTick` in `DragonTweaks.java` following existing pattern. Build clean.

---

## Session Notes — 2026-05-10 (session 19) — scanTerrainLabels: add village category, refine structures block list

Added `village` boolean to `scanTerrainLabels()` in `ChatInterceptor.java`. Triggered by: BELL, HAY_BLOCK, LECTERN, BLAST_FURNACE, SMOKER, COMPOSTER, FLETCHING_TABLE, CARTOGRAPHY_TABLE. Updated `structures` block list (removed OAK_DOOR, SPRUCE_DOOR, GLASS_PANE). Village takes priority in label output — if both true, only "village" is emitted; "structures" only emitted when village is false. Updated early-exit condition to 12 flags. Build clean.

---

## Session Notes — 2026-05-10 (session 18) — scanTerrainLabels: add structures category

Added `structures` boolean to `scanTerrainLabels()` in `ChatInterceptor.java`. Triggered by: OAK_PLANKS, SPRUCE_PLANKS, COBBLESTONE, STONE_BRICKS, COBBLESTONE_WALL, OAK_DOOR, SPRUCE_DOOR, GLASS_PANE, TORCH, LANTERN. Added as `else if` branch after `ore`. Updated early-exit condition to include `structures` (now 11 flags). Added "structures" to the labels list. Build clean.

---

## Session Notes — 2026-05-10 (session 17) — scanTerrainLabels: remove canSeeSky early return, add stone/ore categories

Removed the `canSeeSky` early-return guard from `scanTerrainLabels()` in `ChatInterceptor.java`. Scan now always runs regardless of sky access. Added two new block categories: `stone` (STONE, DEEPSLATE, COBBLESTONE) and `ore` (COAL_ORE, IRON_ORE, GOLD_ORE, DEEPSLATE_IRON_ORE, DEEPSLATE_GOLD_ORE). Updated boolean declarations, scan loop, early-exit condition (now 10 flags), and labels list. Build clean.

---

## Session Notes — 2026-05-10 (session 16) — PRE_COLONY block scan terrain labels

Added `scanTerrainLabels(ServerLevel, BlockPos)` private method to `ChatInterceptor.java`. Scans 32-block X/Z radius, 4 up/4 down Y, step 2. Tracks presence (boolean) of 8 categories: ice, snow, water, lava, sand, gravel, farmland, forest. Builds comma-separated label string; returns "none notable" if nothing detected. Early-exits once all 8 flags set. Added `"Nearby terrain: " + terrainLabels + ".\n"` line to the PRE_COLONY `bookAdvisor` scoped prompt, after the existing `"Nearby: "` line. Added `BlockPos` and `Blocks` imports. Build clean.

---

## Session Notes — 2026-05-08 (session 15) — Role assignment packets and screen

Created three new files and updated three existing files. Build clean.

**RoleAssignmentPayload.java** — implements `CustomPacketPayload`. Record with 5 fields. `AVAILABLE_ROLES` constant `List.of("Ranch Hand", "Scout", "Advisor", "Planner")` — hardcoded, not from config per spec. Encode/decode via `FriendlyByteBuf`. Registered `playToClient` in `DragonTweaksClient.registerPackets()`.

**RoleSelectionPacket.java** — implements `CustomPacketPayload`. Record: `citizenId`, `selectedRole`. `handleOnServer()`: LITE_MODE early return → cast `ctx.player()` to `ServerPlayer` → get `RoleAssignmentData` from overworld SavedData → `isAssigned` re-verify → `assign(citizenId, selectedRole, playerUUID)` → confirmation message. `assign()` confirmed as 3-param: `(int citizenId, String roleType, UUID playerUUID)` — no shadowEntityUUID parameter (RoleAssignmentData stores null internally). Registered `playToServer` in new `DragonTweaks.registerServerPackets()` on mod event bus.

**RoleAssignmentScreen.java** — @OnlyIn(Dist.CLIENT). Panel 210×220, scissor-clipped role list, scroll offset tracked via `mouseScrolled()`. Role rows rendered as fill+drawString (not Button widgets) to support scrollable click hit-testing cleanly. `mouseClicked()` hit-tests rows after button delegation. Assign button `active = false` until a row is selected; on click calls `PacketDistributor.sendToServer(new RoleSelectionPacket(...))` and closes.

**CitizenInteractDetector.java** — replaced TODO stub. After slot check passes: casts player to `ServerPlayer`, calls `PacketDistributor.sendToPlayer(serverPlayer, new RoleAssignmentPayload(name, citizenId, slotsUsed, maxSlots, AVAILABLE_ROLES))`.

**DragonTweaksClient.java** — added `Minecraft` import; added `RoleAssignmentPayload` handler inline in `registerPackets()` as lambda: `(packet, ctx) -> ctx.enqueueWork(() -> Minecraft.getInstance().setScreen(new RoleAssignmentScreen(packet)))`.

**DragonTweaks.java** — added `RegisterPayloadHandlersEvent` import; added `modEventBus.addListener(this::registerServerPackets)` in constructor; added `registerServerPackets()` method calling `event.registrar(MODID).playToServer(...)`.

---

## Session Notes — 2026-05-08 (session 14) — BookAdvisorEntity state-aware movement

Modified `BookAdvisorEntity.java` tick logic to be state-aware.

**Stub verifications performed:**
- `IColony.isCoordInColony(Level, BlockPos)` — confirmed at IColony.java line 12
- `IColonyManager.getInstance().getIColony(Level, BlockPos)` — confirmed at IColonyManager.java line 19
- `colony.getServerBuildingManager().getTownHall().getPosition()` — already verified in session 13; `hasTownHall()` guard pattern reused from same session

**Behavior:**
- PRE_COLONY or DORMANT: existing follow behavior (yaw-relative offset).
- COLONY_NO_CITIZEN or COLONY_WITH_CITIZEN, MineColonies loaded: look up colony via `getIColony(level, entityBlockPos)`. If null, fall back to follow. If player is inside colony (`isCoordInColony`): follow with offset. If player is outside colony but within `ADVISOR_BOUNDARY_DETECTION_RANGE` of entity: hold position (no setPos). If beyond that range: snap to Town Hall via `getServerBuildingManager().getTownHall().getPosition()`, guarded by `hasTownHall()`.
- MineColonies not loaded in colony state: fall back to follow.

**Refactor:** Extracted `followPlayer(Player)` private helper to eliminate duplicate offset logic.

Build clean.

---

## Session Notes — 2026-05-08 (session 13) — Colony event handlers and advisor citizen-lost logic

Added three new MineColonies event handlers to `DragonTweaks.java` inside the existing `ModList.isLoaded("minecolonies")` guard.

**Stub verifications performed:**
- `IPermissions.getOwner()` → `UUID` — confirmed in `IPermissions.java` stub
- `ICitizen.getId()` → `int` — confirmed in `ICitizen.java` stub via `AbstractCitizenModEvent.getCitizen()`
- `colony.getServerBuildingManager().getTownHall().getPosition()` → `BlockPos` — confirmed via `ICommonBuilding.getPosition()` stub
- `ColonyCreatedModEvent` / `ColonyDeletedModEvent` → both extend `AbstractColonyModEvent` with `getColony()` only; player UUID derived via `getColony().getPermissions().getOwner()`

**ColonyCreatedModEvent handler:** LITE_MODE guard → get owner UUID via `colony.getPermissions().getOwner()` → check state is PRE_COLONY → transition to COLONY_NO_CITIZEN → send one-time greeting message guarded by session-only `colonyGreetedPlayers` Set<UUID>.

**ColonyDeletedModEvent handler:** LITE_MODE guard → check state is COLONY_NO_CITIZEN or COLONY_WITH_CITIZEN → despawn existing entity (UUID lookup across all server levels) → spawn new BookAdvisorEntity at player's current position in player's current level → transition to PRE_COLONY → clear assignedCitizenId → update advisorEntityUUID.

**CitizenDiedModEvent and CitizenJobChangedModEvent:** Added `if (!LITE_MODE)` advisor logic call at end of each existing handler. Cache invalidation unchanged. Shared logic extracted into `private static handleAdvisorCitizenLost(IColony, int, ServerLevel)`: checks COLONY_WITH_CITIZEN state and assignedCitizenId match → despawns entity → spawns BookAdvisorEntity at Town Hall position (guarded by `hasTownHall()`) → transitions to COLONY_NO_CITIZEN → clears assignedCitizenId → sends "Your advisor's role is now vacant." message.

**New field:** `private static final Set<UUID> colonyGreetedPlayers` — session-only, same pattern as `liteModeNotified`.

Build clean.

---

## Session Notes — 2026-05-08 (session 12) — AdvisorHotbarWatcher

Created `AdvisorHotbarWatcher.java`. Registered on `NeoForge.EVENT_BUS` as `PlayerTickEvent.Post` listener.

**Event:** `PlayerTickEvent.Post` — confirmed against stub at `net.neoforged.neoforge.event.tick.PlayerTickEvent`. Inner class `.Post` pattern matches existing `ServerTickEvent.Post` usage in ObservationTicker/AdvisorDiagnosticLoop.

**Server guard:** `!(player.level() instanceof ServerLevel)` early return — no client execution.

**State guard:** Returns immediately unless state is DORMANT and `buildToolTriggerFired` is false — completely inactive in PRE_COLONY, COLONY_NO_CITIZEN, COLONY_WITH_CITIZEN.

**Hotbar scan:** `player.getInventory().getItem(slot)` for slots 0–8. Item identity via `BuiltInRegistries.ITEM.getKey(stack.getItem())` compared to `ResourceLocation.fromNamespaceAndPath("structurize", "sceptergold")`. No vanilla/NeoForge stubs exist for Player or Inventory — APIs confirmed from vanilla 1.21.1 knowledge. `ResourceLocation.fromNamespaceAndPath()` form confirmed from existing code in AssistantRenderer, ModEntities.

**Spawn:** `ModEntities.BOOK_ADVISOR.get().create(serverLevel)`. `entity.setOwner(player)`. `entity.moveTo(x, y+1.0, z, 0f, 0f)`. `serverLevel.addFreshEntity(entity)`. Entity spawned in player's current level (any dimension). Entity UUID stored in AdvisorStateData via overworld SavedData.

**AdvisorStateData access:** Always reads/writes from `serverLevel.getServer().getLevel(Level.OVERWORLD)` — SavedData is attached to overworld only.

Updated `DragonTweaks.java`: added `NeoForge.EVENT_BUS.addListener(AdvisorHotbarWatcher::onPlayerTick)` following existing pattern.

Build clean.

---

## Session Notes — 2026-05-08 (session 11) — BookAdvisorEntity, ModEntities registration

Created `BookAdvisorEntity.java`. Extends `Entity` (vanilla root, not PathfinderMob). No AI, no combat, no role assignment.

**Base class:** `Entity` — vanilla entity class stubs are not present in docs/stubs (stubs cover only MineColonies and NeoForge APIs). Base class selection is confirmed from 1.21.1 knowledge: Entity is the correct lightest non-mob base.

**Glow:** `setGlowingTag(true)` called in constructor — vanilla Entity method, present since 1.17+, confirmed correct for 1.21.1. Does not require LivingEntity.

**Follow logic:** `tick()` runs server-side only. Reads `Config.ADVISOR_ENTITY_OFFSET` each tick. Computes right-perpendicular vector from player yaw (`cos(yawRad)`, `sin(yawRad)`), adds 0.5-block behind component (`sin(yawRad)`, `-cos(yawRad)`). Sets entity Y to player.getY()+1.0.

**Required abstract:** `defineSynchedData(SynchedEntityData.Builder)` — implemented empty (no custom synced data).

**NBT:** ownerUUID only. Written if non-null, read back safely.

Updated `ModEntities.java`: added `BOOK_ADVISOR` DeferredHolder using same `.of()/.sized()/.clientTrackingRange()/.build()` chain as ASSISTANT. No entry in `onAttributeCreate` — Entity subclasses do not register attributes.

Build clean.

---

## Session Notes — 2026-05-08 (session 10) — AdvisorState enum, AdvisorStateData SavedData, Config advisor values

Created `AdvisorState.java` — four-value enum: DORMANT, PRE_COLONY, COLONY_NO_CITIZEN, COLONY_WITH_CITIZEN.

Created `AdvisorStateData.java` — NeoForge SavedData following RoleAssignmentData pattern exactly. Per-player state stored in `Map<UUID, PlayerAdvisorState>`. `PlayerAdvisorState` inner class holds: `advisorState` (default DORMANT), `buildToolTriggerFired` (default false), `assignedCitizenId` (nullable Integer), `advisorEntityUUID` (nullable UUID). Full NBT serialization — optional fields written only when non-null, graceful enum fallback to DORMANT on unknown value. Static `get(ServerLevel)` attached to overworld at key "dragontweaks_advisor_state". Public API: `getOrCreate`, `setState`, `getState`, `setBuildToolTriggerFired`, `hasBuildToolTriggerFired`, `setAssignedCitizenId`, `getAssignedCitizenId`, `setAdvisorEntityUUID`, `getAdvisorEntityUUID`.

Added five config values to `Config.java`: `ADVISOR_ENTITY_OFFSET` (double, 1.8, 0.5–5.0), `ADVISOR_HOTBAR_CHECK_TICKS` (int, 40, 10–200), `ADVISOR_BOUNDARY_DETECTION_RANGE` (int, 40, 10–200), `ADVISOR_WHISPER_THRESHOLD` (int, 120, 40–500), `ADVISOR_FORCE_PRIVATE` (boolean, false).

Build clean.

---

## Session Notes — 2026-05-07 (session 9) — Packet refactor, bed count fix, planner correctness, townhall auto-satisfied

### ClientPanelHandler — packet handler isolation
Removed `handleOnClient` static methods from `OpenAdvisorPanelPacket` and `OpenPlannerPanelPacket`. Both methods referenced `net.minecraft.client.Minecraft` which must never be classloaded on a dedicated server. Created `ClientPanelHandler.java` (`@OnlyIn(Dist.CLIENT)`) with `handleAdvisorPanel` and `handlePlannerPanel`. Moved `registerPackets()` from `DragonTweaks` into `DragonTweaksClient` (client-dist-only class). Packet handler registration now happens in a class that is never loaded on dedicated server, making the isolation explicit and guaranteed. Build clean.

### housingCap — actual bed count
`ColonyDiagnosticReportGenerator.generate()` was using `colony.getCitizenManager().getMaxCitizens()` as the housing cap, which is a research-gated citizen capacity, not a raw bed count. Replaced with `countBedCapacity()`: iterates all built colony buildings, adds 2 for each Residence (translation key contains "residence" or ".home"), adds 4 for each Tavern. TODO comment marks translation key substrings as needing in-game verification against MineColonies 1.21.1. Build clean.

### Planner — skip already-built recommendations
`generateRecommendations()` was adding buildings to the recommendation list based solely on red happiness factors, without checking whether the building already exists in the colony. A hospital that already existed (but was understaffed) would appear as "Build Hospital — Ready to build", which is wrong. Added guard after computing `stepsRemaining`: if the target building is in `builtTypes` and `stepsRemaining == 0`, the recommendation is skipped. Exception: `CAPACITY_DRIVEN = {"residence", "guard_tower"}` — multiple instances are valid for these. Build clean.

### planner_dependencies.json / PlannerDependencyRegistry — townhall auto_satisfied
Added `"auto_satisfied": true` to the townhall entry in `planner_dependencies.json`. `PlannerDependencyRegistry` now parses this field into `autoSatisfiedIds` and exposes `isAutoSatisfied(String)`. `PlannerPanelPayload.isStepComplete()` checks `isAutoSatisfied()` first for BUILDING steps — if true, returns true without consulting `builtTypes`. Since every chain passes through townhall and every colony has one, all townhall prerequisite steps are now always marked complete. Build clean.

---

## Session Notes — 2026-05-07 (session 8) — PlannerPanelScreen

Created `PlannerPanelScreen.java` — client-side GUI extending `Screen`. Constructor takes `PlannerPanelPayload` (initial) and nullable `Consumer<String> goalCallback`. `updatePayload()` is a public method for the eventual packet handler to push new data without reopening the screen.

**Panel: 324×270.** Layout (top to bottom): title → "Goal:" label + EditBox (full-width minus label) → worker/bed header strip (snapshot only) → scissor-clipped content area → Prev/Close/Next nav row.

**Snapshot mode:** worker/bed header strip always visible when mode=SNAPSHOT. Recommendations paginated 3/page. Each rec card: separator line, `[!]`/`[ ]` priority badge, target name, `[In Progress]` annotation (blue, right-aligned). Conditional lines: immediate blocker (yellow, ▸ prefix), research prereq (blue), worker slot constraint (red), bed delta constraint (red), steps remaining (grey). Crisis badge is red; non-crisis is grey.

**Goal input mode — Enter handling:** `keyPressed()` checks `keyCode == InputConstants.KEY_RETURN` (verified value 257 against decompiled `InputConstants.java`) and `goalInput.isFocused()`. EditBox does not handle Enter itself. `goalCallback.accept(text)` fires if callback non-null. Safe pattern — works regardless of whether EditBox internally consumes Enter.

**Goal input mode — exact match:** Cost estimate block (steps remaining + research names, inline). Materials collapsible toggle row defaulted collapsed. `matsToggleY` field set during render() each frame; `mouseClicked()` reads it to detect toggle clicks. Dependency chain paginated 8/page: completed steps grey+✓, first incomplete yellow+►, remaining white+indent.

**Goal input mode — no match:** "No match: <input>" in red, then "Did you mean:" with suggestion list or "No suggestions found." — never blank per spec.

**All class names verified against NeoForge 1.21.1 decompiled gradle cache:** `Screen`, `Button` (builder pattern), `EditBox` (6-param constructor confirmed), `GuiGraphics` (fill/drawString/enableScissor confirmed), `InputConstants` (KEY_RETURN=257). Build clean.

---

## Session Notes — 2026-05-07 (session 7) — AdvisorPanelScreen

Created `AdvisorPanelScreen.java` — client-side GUI extending `Screen`. Constructor takes `AdvisorPanelPayload`. No server calls, no network: pure display of pre-built payload data.

**Panel layout (304×250, centered):** title → environmental banner (conditional) → systemic pattern banner (conditional) → colony summary header → scissor-clipped citizen list → Prev/Close/Next nav row.

**Environmental banner:** amber `0xEE663300`, shown when `payload.getEnvironmentalFlags()` is non-empty. Lists flag names inline, appends "data may be unreliable".

**Systemic pattern banner:** dark red `0xEE550011`, shown when `payload.getSystemicPattern()` is non-null. One banner maximum (payload already resolved highest-severity winner). Three pattern labels mapped from `ColonyDiagnosticReport.SystemicPattern` enum.

**Colony summary header:** overall happiness (color-coded red/yellow/green) and citizen count / housing cap on one line.

**Citizen list:** `enableScissor()`/`disableScissor()` on `[listTop, listBottom]`. 5 items per page (ITEMS_PER_PAGE constant). Per-citizen collapsed row (16px): severity dot, `>` or `v` arrow, name (truncated at 14 chars), worst factor id+value, `+N` badge, `[far]` commute flag. Per-citizen expanded section: 10 factor lines (10px each) each with severity dot+id+value+modifier label, then commute line (threshold comparison with color). Expand state stored in `Set<Integer>` keyed by citizen list index; cleared in `onClose()`.

**mouseClicked():** calls `super.mouseClicked()` first (button delegation), then walks accumulated y through current page's citizen rows (accounting for expanded sections) to hit-test header rows only. Click on header row toggles expand.

**Render pattern:** `renderBackground()` called once → custom content drawn → `this.renderables` iterated directly (field is `public final` on `Screen`) to render buttons on top. `super.render()` not called to avoid double `renderBackground()`.

**NeoForge 1.21.1 class names verified against gradle-cached decompiled sources:**
- `Screen` → `net.minecraft.client.gui.screens.Screen`
- `Button` → `net.minecraft.client.gui.components.Button` with `Button.builder().bounds().build()` pattern
- `GuiGraphics` → `net.minecraft.client.gui.GuiGraphics` — `fill()`, `drawString()`, `drawCenteredString()`, `enableScissor()`, `disableScissor()` all confirmed
- `renderBackground(GuiGraphics, int, int, float)` — 4-param signature confirmed
- `renderables` — `public final List<Renderable>` on `Screen`

Build clean.

---

## Session Notes — 2026-05-07 (session 6) — PlannerPanelPayload

Created `PlannerPanelPayload.java` — pure server-side data preparation class. Static `build(IColony colony, String goalInput)`.

**Snapshot mode** (`goalInput == null`): calls `ColonyDiagnosticCache.getOrGenerate()`. Builds `WorkerHeader` (workersAssigned = built buildings with non-null assignedWorker; totalSlots = all built buildings — approximate, no verified API for exact worker-slot capacity). Builds `BedHeader` from `report.getCitizenCount()` and `report.getHousingCap()`. Generates `List<Recommendation>` from global red factors and housing shortage check. Ordered crisis-first then by `stepsRemaining` ascending.

**Recommendation fields**: `targetId`, `targetDisplayName`, `priority` (CRISIS/NON_CRISIS), `immediateBlocker` (first incomplete step description), `researchPrereqName` (null unless research is the blocker), `workerSlotsNeeded` (1 if worker slots full), `bedDelta` (1 if beds full and recommendation is for residence), `stepsRemaining`, `inProgress` (claimed BUILD/UPGRADE work order for that building type).

**Goal input mode** (`goalInput != null`): calls `PlannerDependencyRegistry.findMatches()` then `getChain()`. Annotates each `ChainStep` with `completed` and `firstIncomplete` flags. Builds `CostEstimate` (count of incomplete steps + names of incomplete RESEARCH steps). `GoalResult` carries exact matches, suggestions, chain, cost estimate, materials, `hasWarehouse`.

**Step completion verified APIs**:
- BUILDING: `IBuilding.getBuildingType().getRegistryName()` matched against `BUILDING_HOLDERS` map (DeferredHolder.getId() — confirmed in DeferredHolder stub)
- BUILDING_PREREQ: total level sum from `getBuildingTotalLevels()` vs step.minTotalLevel
- RESEARCH: `ILocalResearchTree.isComplete(ResourceLocation)` — confirmed in ILocalResearchTree stub

**In-progress check**: `colony.getWorkManager().getWorkOrders()` → filter claimed BUILD/UPGRADE → `colony.getServerBuildingManager().getBuildings().get(wo.getLocation())` → match registry name.

**BUILDING_HOLDERS map**: 20 entries mapping JSON node IDs to ModBuildings DeferredHolder fields. Fields verified against ModBuildings stub (confirmed: townHall, builder, home, wareHouse, deliveryman, guardTower, tavern, university, lumberjack, sawmill, fletcher, miner, blacksmith, farmer, fisherman, cook, school, library, hospital, mysticalSite). TODO: verify ResourceLocation paths in-game.

**Materials list**: empty pending per-building material requirements API verification and player parameter. Call pattern for `getMatchingItemStacksInWarehouse(Predicate<ItemStack>)` documented in TODO comment in `buildMaterialsList()`.

Build clean.

---

## Session Notes — 2026-05-07 (session 5) — AdvisorPanelPayload

Created `AdvisorPanelPayload.java` — pure server-side data preparation class. No rendering, no GUI, no client code. Single static `build(IColony colony)` method. Calls `ColonyDiagnosticCache.getOrGenerate(colony)` then assembles the payload.

Payload contents: `List<EnvironmentalFlag>` from report, `SystemicPattern` (null if none detected), colony summary (overall happiness, citizen count, housing cap), `List<CitizenEntry>` ordered red tier first, then yellow, then healthy, alphabetical by name within each tier.

`CitizenEntry` carries: `tier` (Severity enum driving order), `name`, `worstFactorId`, `worstFactorValue`, `additionalComplaintsCount` (flagged factors minus worst already shown), `commuteFlagged` (distance > `ADVISOR_COMMUTE_THRESHOLD`), plus expanded: `List<FactorDetail>` in canonical 10-factor order, `commuteDistance`, `commuteThreshold`.

`FactorDetail` carries: `factorId`, `value`, `severity`, `modifierTypeLabel` from static map (Static/TimeBased/ExpirationBased per spec table).

Citizen tier logic per spec: RED if any factor red or commute flagged; YELLOW if any factor yellow (no red, no commute flag); HEALTHY otherwise.

Threshold reads at call-time from `Config.ADVISOR_HAPPINESS_THRESHOLD_RED.get()`, `Config.ADVISOR_HAPPINESS_THRESHOLD_YELLOW.get()`, `Config.ADVISOR_COMMUTE_THRESHOLD.get()`. No hardcoded values.

Build clean.

---

## Session Notes — 2026-05-07 (session 4) — AdvisorDiagnosticLoop

Created `AdvisorThrottleData.java` — NeoForge SavedData attached to the overworld level. Stores a `Set<String>` of fired throttle keys persisted across sessions. Keys: `"{colonyId}:{citizenId}:{colonyDay}"` for per-citizen, `"{colonyId}:systemic:{patternType}:{colonyDay}"` for systemic patterns. `hasFired(key)` / `markFired(key)` are the public API. Pattern follows `RoleAssignmentData` exactly.

Created `AdvisorDiagnosticLoop.java` — server-side tick listener registered on `NeoForge.EVENT_BUS`. Checks each colony every 600 ticks (30s) OR when `markDirty(colonyId)` is called. Guards with `ModList.isLoaded("minecolonies")`. Player-in-colony check (via `isCoordInColony`) on main thread before going async. Async path: `CompletableFuture.runAsync()` calls `ColonyDiagnosticCache.getOrGenerate()`, evaluates systemic pattern vs per-citizen diagnosis, builds LLM prompt. Returns to main thread via `server.execute()` for throttle check (`AdvisorThrottleData`), player re-lookup, throttle marking, and `LLMClient.observe()` call. Advisor identity: deterministic UUID per colony from `UUID.nameUUIDFromBytes`. No Advisor entity needed — colony-level proxy.

Updated `DragonTweaks.java`: registered `AdvisorDiagnosticLoop::onServerTick`. Added `AdvisorDiagnosticLoop.markDirty()` alongside existing `ColonyDiagnosticCache.invalidate()` in all four MineColonies event handlers. Extracted `colonyId` local variable in each handler to avoid double-calling `getColony().getID()`.

Build clean.

---

## Session Notes — 2026-05-07 (session 3) — ColonyDiagnosticCache

Created `ColonyDiagnosticCache.java`. Static class with `ConcurrentHashMap<Integer, CacheEntry>` keyed by colony ID. TTL is 30 seconds (hardcoded constant — no config key specified). `getOrGenerate(IColony)` checks staleness and calls `ColonyDiagnosticReportGenerator.generate(colony)` on miss. `invalidate(int colonyId)` removes the entry.

Registered four invalidation handlers in `DragonTweaks.java` inside the existing `enqueueWork` block (already guarded by `ModList.isLoaded("minecolonies")`):
- `CitizenDiedModEvent` — merged with existing LLM observation handler (one subscriber, invalidation runs before the world-cast guard)
- `BuildingConstructionModEvent` — merged with existing LLM observation handler
- `CitizenJobChangedModEvent` — new subscriber, invalidation only
- `CitizenAddedModEvent` — new subscriber, invalidation only

Verified `CitizenJobChangedModEvent` and `CitizenAddedModEvent` package paths against stubs: both at `com.minecolonies.api.eventbus.events.colony.citizens.*`. `getColony()` confirmed on `AbstractColonyModEvent` base class. Build clean.

---

## Session Notes — 2026-05-07 (session 2) — PlannerDependencyRegistry

Created `src/main/resources/data/dragontweaks/planner_dependencies.json` with the full 20-building seed data from `planner_dependency_data_spec_v0_1.md`. Created `PlannerDependencyRegistry.java` as a singleton that loads the JSON at mod startup, resolves the dependency graph depth-first into pre-computed per-building chains, and exposes `getChain(String buildingId)` and `findMatches(String input)`. Registered `PlannerDependencyRegistry.load()` as the first call in `DragonTweaks.commonSetup()`. Build clean.

Chain resolution algorithm: DFS post-order with a visited set (cycle-safe). For each building with `building_prereqs`, the prereq building's chain is resolved first, then a `BUILDING_PREREQ` annotation step (level requirement) is inserted, then the `RESEARCH` step, then the building itself. All chains pre-cached at load time.

`findMatches`: exact match via normalized alias map; if no exact match, substring scan over all ids and aliases for "did you mean" suggestions.

---

## Session Notes — 2026-05-07 — Config additions and LLMClient reasoning disable

Added three config values to `Config.java`:
- `ADVISOR_COMMUTE_THRESHOLD` — int, default 80, range 10–500. Drives commute flag in Advisor panel.
- `ADVISOR_HAPPINESS_THRESHOLD_RED` — double, default 0.5, range 0.0–1.0. Factor below this = red.
- `ADVISOR_HAPPINESS_THRESHOLD_YELLOW` — double, default 0.9, range 0.0–1.0. Factor below this (but ≥ red) = yellow.

Threshold defaults from V2 resolution in open_questions_log.md: red < 0.5, yellow 0.5–0.9, healthy ≥ 0.9.

Added `{"reasoning": {"effort": "none"}}` to `buildRequestBody()` in `LLMClient.java` after the `stream` property. Confirmed syntax from OpenRouter docs (V5 resolution). `JsonObject reasoning` constructed inline using existing Gson `JsonObject` pattern already in the file.

Build clean.

---

## Session Notes — 2026-05-06 — CitizenInteractDetector

Built `CitizenInteractDetector.java` as a new file, registered on `NeoForge.EVENT_BUS` in `DragonTweaks.java`.

**API chain verified against stubs before writing:**
- Event: `PlayerInteractEvent.EntityInteract` (confirmed in NeoForge stubs)
- Citizen identity: `instanceof AbstractEntityCitizen` — guarded by `ModList.isLoaded("minecolonies")` to avoid class-not-found on vanilla servers
- `ICitizenData` retrieval: `AbstractEntityCitizen.getCitizenData()` — null-checked
- Name/ID: `ICitizen.getName()` and `ICitizen.getId()` — confirmed in `ICitizen.java` stub
- Job name: `IJob.getNameTagDescription()` — confirmed in `IJob.java` stub; falls back to "unemployed" if null or blank
- TH level: `colony.getServerBuildingManager().getTownHall().getBuildingLevel()` — chain confirmed across `ICitizen`, `IColony`, `IRegisteredStructureManager`, `ICommonRegisteredStructureManager`, and `ICommonBuilding` stubs; null-checked with fallback level 1
- Slot count: `RoleAssignmentData.getAssignedCount(playerUUID)` — per-player, matches design intent
- Slot cap formula: `thLevel < 3 ? 3 : Math.min(thLevel + 1, 6)` — matches locked design decision in devchat.md

**CLAUDE.md path fix:** Updated both session-startup and session-closeout references from `devchat.md` to `docs/devchat.md`.

Build clean after changes.

---

## Known Deferred Items

- NPC cross-awareness — low priority, revisit Phase 4
- Shadow entity multiplayer visibility — test Phase 2
- Ranch Hand max scan radius — resolved; Ranch Hand operates beyond colony bounds at a configurable buffer distance
- Mod final name — TBD
- Quest system — parking lot, no timeline