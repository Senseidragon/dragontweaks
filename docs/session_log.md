# DragonTweaks — Session Log

*Append-only. One entry per session. Most recent at the top.*
*Purpose: Capture what was done, why, and any decisions made. Not a file status table — that lives in `current_state.md`.*

---

## 2026-05-19 — Session 36

**Focus:** Replace positional hazard suppression with BookAdvisor conversation memory.

**Work completed:**
- **Backed out positional suppression (`PreColonyScoutTicker.java`):** Removed `villageWarnedPos`, `raiderWarnedPos`, `HAZARD_SUPPRESS_RADIUS` fields and all associated suppression logic. Village and raider context now always injected when present — suppression is delegated to the LLM via conversation history.
- **Stable per-player advisor memory key (`PreColonyScoutTicker.java`, `ChatInterceptor.java`):** All four BookAdvisor `LLMClient.query()` calls now use `UUID.nameUUIDFromBytes("advisor:" + playerUUID)` as the memory key instead of the entity UUID. This makes memory persist across entity respawns and across all three advisor states (PRE_COLONY → COLONY_NO_CITIZEN → COLONY_WITH_CITIZEN) within a session.
- **Prompt suppression instruction (`PreColonyScoutTicker.java`, `ChatInterceptor.java`):** PRE_COLONY proactive prompt and PRE_COLONY direct-chat prompt both now instruct the advisor: "If your conversation history shows you have already warned about a nearby village or hostile encampment, do not repeat that warning." The LLM sees prior observations and can apply this rule with actual context.

**Decisions made:**
- Positional suppression (Java-side) is architecturally wrong: the LLM is stateless and has no awareness of what it previously said. Conversation memory is the correct mechanism — the model sees its own prior output and can decide not to repeat it.
- Stable memory key: `UUID.nameUUIDFromBytes("advisor:" + playerUUID)` — deterministic, consistent with the citizen NPC pattern at `ChatInterceptor.java:473`, survives entity respawns.
- Memory persists within a server session. Does not persist across server restarts (ConversationMemory is in-memory only). Acceptable — full cross-restart persistence would require SavedData, deferred.

**Deferred / carry-forward:**
- Cross-restart persistence for BookAdvisor memory — would require SavedData. Not requested.

**Build status:** PASS

---

## 2026-05-19 — Session 35

**Focus:** Post-run log scan bug fixes — renderer log spam and scout hazard repetition.

**Work completed:**
- **BookAdvisorRenderer log spam removed (`BookAdvisorRenderer.java`):** `LOGGER.info()` call inside `render()` was firing every frame (~60/sec). Removed entirely.
- **PRE_COLONY scout hazard suppression (`PreColonyScoutTicker.java`):** Village and raider context is now suppressed after first mention. Added `villageWarnedPos` and `raiderWarnedPos` maps (keyed by player UUID). Each hazard is injected into the prompt only once per 512-block radius. Moving 512+ blocks resets the suppression and allows re-warning in a genuinely new area.

**Player notes from this session's in-game test (Claude Code messages):**
- Feature request logged: `/assistant mow [radius]` — clears tall/short grass, bushes, flowers, vines in radius centered on player (default 64). Not implemented this session.
- TEST PASSED: Spontaneous response
- TEST PASSED: Responds without keyword appropriately
- TEST PASSED: Responds appropriately to weather queries
- TEST FAILED: Scout constantly mentions villages and golems every prompt — fixed this session.

**Decisions made:**
- Hazard suppression is Java-side (omit context block), not purely prompt-side. LLM is stateless; prompt-only instructions cannot suppress content the model never saw was mentioned before.
- Suppress radius: 512 blocks. Player must travel 512+ blocks before a hazard type is re-warned.

**Deferred / carry-forward:**
- `/assistant mow [radius]` feature request — not yet scoped or implemented.

**Build status:** PASS

---

## Session Template

```
## YYYY-MM-DD — Session N

**Focus:** [One-line summary of what this session tackled]

**Work completed:**
- 

**Decisions made:**
- 

**Deferred / carry-forward:**
- 

**Build status:** PASS / FAIL
```

---

## 2026-05-19 — Session 34

**Focus:** Two live-test bugs — LLM warmup delay on first call, and no spontaneous PRE_COLONY commentary.

**Work completed:**
- **LLM warmup ping (`LLMClient.java`, `DragonTweaks.java`):** Added `LLMClient.warmup()` — fires a minimal async 1-token request on first player login to seed the HTTP connection. Fires once per server session (tracked via `warmupFired` flag). Response is discarded. Gated on `!LITE_MODE` and `Config.LLM_ENABLED`.
- **PRE_COLONY spontaneous commentary fix (`PreColonyScoutTicker.java`):** Removed `player.isCreative()` gate. Creative mode was silently blocking all proactive scout observations during testing. The position guard (64-block movement threshold) already prevents over-firing; no replacement gate needed.

**Decisions made:**
- Creative mode does not suppress PRE_COLONY scout observations. The position guard is the sole throttle for that system.
- Warmup fires on first player login per server session, routes to `"advisory"` model tier.

**Deferred / carry-forward:**
- None new.

**Build status:** PASS

---

## 2026-05-19 — Session 33

**Focus:** Bug fixes and enhancements from live testing — model leak, model routing, village radius, citizen location hallucination, `/assistant list`.

**Work completed:**
- **Model token leak fix (`LLMClient.java`):** Added `cleanResponse()` — strips model-internal special tokens (`<|channel|>`, `<|message|>` and any `<|...|>` pattern) from all LLM responses before delivery. If stripping leaves an empty string, fallback fires instead.
- **Model routing fix (`LLMClient.java`):** Removed hardcoded `"advisory"` from 8-arg `query()` callback overload. Added `modelRole` parameter — callers now declare their tier explicitly.
- **Citizen conversation routing (`ChatInterceptor.java`):** Citizen conversation (non-assigned citizens like Mav) now passes `"flavor"` model role. Was incorrectly routing to `"advisory"` (reasoning model) because it reused the advisory callback overload.
- **PRE_COLONY scout routing (`PreColonyScoutTicker.java`):** Now routes to `"specialized"` model with `SPECIALIZED_MAX_TOKENS`. Was routing to `"advisory"` — scouting is a Scout/specialized function, not an advisory one.
- **Creative mode gate fix (`PreColonyScoutTicker.java`):** Replaced `player.getAbilities().flying` with `player.isCreative()`. Survival elytra flight no longer suppresses scout observations.
- **Village radius bug fix (`PreColonyScoutTicker.java`, `TerrainScanner.java`):** `findNearestMapStructure` radius parameter is in chunks, not blocks. Was passing hardcoded `150` (chunks = 2,400 blocks) and `19` (chunks = ~300 blocks). Both now use `SCOUT_VILLAGE_REPORT_RADIUS` config value in blocks, converted to chunks via `(blocks + 15) / 16`. Post-find exact block-distance guard added — rejects villages beyond the config threshold.
- **New config value (`Config.java`):** `SCOUT_VILLAGE_REPORT_RADIUS` — max distance in blocks within which a village is reported during scouting. Default 256, range 16–1024.
- **Citizen location hallucination fix (`ChatInterceptor.java`, `ColonyContextBuilder.java`):** Citizens were inventing location names ("market square") when asked where someone is. `ColonyContextBuilder` now computes compass direction from player to each citizen's work building and injects `"last seen to the north"` style labels into the roster. Citizen system prompt updated to use that direction data and forbid invented landmarks.
- **Citizen prompt hardening (`ChatInterceptor.java`):** Added explicit output constraint: "Output only your spoken reply. No reasoning, no labels, no formatting tokens, no preamble."
- **`/assistant list` command (`AssistantCommand.java`):** New subcommand. Lists all assigned citizens for the player's colony — name, citizen ID, role, and nickname if set.

**Decisions made:**
- Village proximity is measured in blocks everywhere. Config value is always in blocks; conversion to chunks happens at the API call site only.
- Citizens should give compass-direction hints for other citizens' locations based on work building position — not refuse, and not fabricate named landmarks.
- Non-assigned citizens (flavor-tier) always route to `"flavor"` model regardless of query content.
- PRE_COLONY scouting routes to `"specialized"` — Scout is a specialized role, not an advisory one.
- Player in-game chat containing "Claude Code" (not in LLM responses) is a direct note to the AI. Saved to Claude Code memory for future log scans.

**Deferred / carry-forward:**
- OQ-27-1: Non-target citizens sharing `rc0` suppression key — deferred indefinitely.
- Tests 3, 4, 5 from this session's test list (PRE_COLONY village threshold, elytra flight gate, direct chat village distance) — require a fresh PRE_COLONY world state to verify.

**Build status:** PASS

---

## 2026-05-19 — Session 32

**Focus:** Role-aware model routing and token budget increase for advisory and specialized tiers.

**Work completed:**
- Rewrote `ModelConfigLoader` to cache the full `roles` JsonObject (all tiers) on a single 15-minute cache. Added `getModel(String role)` that navigates `roles → {role} → candidates[0] → model_id`. `getModel()` delegates to `getModel("advisory")` for backward compatibility.
- Threaded `modelRole` parameter through `queryWithPrompt()` in `LLMClient`. Flavor NPC `query()` passes `"flavor"`, advisory no-callback `query()` passes `"advisory"`, 7-arg advisory `query()` with callback uses `getModel("advisory")` directly, `observe()` uses `getModel("flavor")` directly.
- Raised `ADVISORY_MAX_TOKENS` from 750 to 2000. Added `SPECIALIZED_MAX_TOKENS = 2000`.
- Closed OQ-30-1: `gpt-oss-120b` as advisory candidates[0] is intentional (cheapest qualifying reasoning model). The `content: null` failure was caused by insufficient token budget, not wrong model selection. 2000 tokens is the fix.
- Closed OQ-28-1: Happiness threshold scale mismatch — decision is to leave thresholds as-is. Advisor observes raw `getFactor()` values; LLM reasons about them. Config values retained for future tuning.

**Decisions made:**
- `ModelConfigLoader` caches full role table, not a single model string. All 4 tiers (flavor, advisory, specialized, tactical) served from one file read.
- Token budgets: flavor = 200, advisory = 2000, specialized = 2000. Tactical TBD.
- MineColonies happiness thresholds will not be adjusted — raw API values passed to LLM for interpretation.

**Deferred / carry-forward:**
- `SPECIALIZED_MAX_TOKENS` defined but no specialized role callers wired yet — will be threaded in when Planner/Forester roles are implemented.
- Tactical tier exists in `model_config.json` but has no callers — future Phase.
- WARN logs in `PreColonyScoutTicker` should be downgraded to DEBUG once pipeline is confirmed stable.

**Build status:** PASS

---

## 2026-05-19 — Session 31

**Focus:** ModelConfigLoader cache, hostile entity detection, stale state auto-correction.

**Work completed:**
- Completed `ModelConfigLoader` 15-minute cache — `cachedModel` and `cacheExpiryMs` now populated after successful parse. Fallback paths intentionally not cached.
- Added MineColonies hostile entity scan to `PreColonyScoutTicker` — namespace-based (`"minecolonies"` namespace, excluding citizen/visitor/cavalry_horse). Confirmed 41 camp barbarians detected at ~50 blocks in live test. Previous tag-based approach (`ModTags.raiders`, `ModTags.hostile`) failed — camp entity variants are in neither tag.
- Added same hostile entity scan to `ChatInterceptor` bookAdvisor PRE_COLONY prompt path — replaced hardcoded `"nothing notable nearby"` with live 200-block scan. DANGER context now injected into direct chat responses when hostiles are present.
- Added COLONY_NO_CITIZEN auto-correction to `ChatInterceptor` — if state is COLONY_NO_CITIZEN but no colony exists for the player, resets to PRE_COLONY on the spot and re-runs BookAdvisor search. Root cause: `ColonyCreatedModEvent` fires on world load for restored colonies, causing stale transition.
- Fixed `entityies` typo in plural hostile count string in both files.

**Decisions made:**
- Camp barbarian detection must use MineColonies namespace scan, not `ModTags.raiders` or `ModTags.hostile` — neither tag covers camp-dwelling variants. Locked.
- COLONY_NO_CITIZEN auto-correction is the canonical fix for stale state. No startup scan needed — correction fires lazily on first chat.
- ModelConfigLoader cache interval: 15 minutes. Fallbacks not cached.

**Deferred / carry-forward:**
- OQ-30-1: `openai/gpt-oss-120b` is still first candidate in `model_config.json` — reasoning model may still exhaust token budget. Fix requires manually reordering candidates or adding `reasoning_excluded` filtering.
- WARN logs in `PreColonyScoutTicker` should be downgraded to DEBUG once pipeline is confirmed stable.
- Flying guard (`player.getAbilities().flying`) blocks PRE_COLONY observations in creative mode — noted, not yet addressed.

**Build status:** PASS

---

## 2026-05-19 — Session 30

**Focus:** PRE_COLONY advisor LLM connectivity — diagnosis, fixes, model config overhaul.

**Work completed:**
- Diagnosed silent LLM failure chain: `BookAdvisorEntity.getOwnerUUID()` null after reload → ChatInterceptor early-exit → no query fired. Added null guard on `ba.getOwnerUUID()` in ChatInterceptor BookAdvisor search.
- Unified BookAdvisor search radius to `Config.COMMAND_PROXIMITY` (was hardcoded 64 blocks, now consistent with AssistantEntity candidate radius).
- Removed `handleAdvisorCitizenLost()` call from `CitizenJobChangedModEvent` handler in `DragonTweaks.java`. Job changes must not trigger COLONY_WITH_CITIZEN → COLONY_NO_CITIZEN — only `CitizenDiedModEvent` and revoke command may do so.
- Added WARN-level logs in `PreColonyScoutTicker` before LLM query and in callback to expose whether the query fires and whether the response arrives.
- Improved `LLMClient` exception logging to include exception class name alongside message (TimeoutException previously logged as "null").
- Added `isJsonNull()` guards in `parseResponse()` for `choices`, `message`, and `content` fields. Diagnosed root cause: `openai/gpt-oss-120b` (a reasoning model) was being selected from `model_config.json`, spending all tokens on reasoning and returning `content: null`.
- Rewrote `ModelConfigLoader.java` to parse the roles-sectioned `model_config.json` format (`roles → first role → candidates[0] → model_id`) using Gson with full null guards. Old string-scan parser discarded.
- Moved `model_config.json` from project root to `run/` (where `ModelConfigLoader` reads it at runtime).
- Split `max_tokens`: advisory roles → `ADVISORY_MAX_TOKENS = 750`; flavor NPCs → `MAX_RESPONSE_TOKENS = 200`. Threaded `maxTokens` through `buildRequestBody` and `queryWithPrompt`.
- Corrected PRE_COLONY advisor prompts in `PreColonyScoutTicker` and `ChatInterceptor` — removed contradictory "never mention colonies" restriction. Advisor is explicitly scouting colony sites; this is the role's core domain.

**Decisions made:**
- Advisory token budget is 750. Flavor NPC budget stays 200. Split is permanent — any advisory role that can't get good advice within 750 tokens needs the budget raised, not capped.
- `CitizenJobChangedModEvent` explicitly must not trigger state regression. Job reassignment is not citizen loss.
- PRE_COLONY advisor has full permission to discuss colony site suitability, village proximity risk, and settlement defensibility.
- `model_config.json` lives in `run/` and is in the scraper's roles-sectioned format. `ModelConfigLoader` now understands this format.

**Deferred / carry-forward:**
- `model_config.json` first candidate (`openai/gpt-oss-120b`) is a reasoning model. Even at 750 tokens it may exhaust budget on reasoning before producing content. Need to either: (a) move a non-reasoning model to top of candidates list, or (b) add reasoning-model filtering to `ModelConfigLoader`. See OQ-30-1.
- WARN log added to `PreColonyScoutTicker` should be downgraded to DEBUG once the pipeline is confirmed stable.
- Flying guard in `PreColonyScoutTicker` (`player.getAbilities().flying`) blocks all PRE_COLONY observations in creative mode. May need revisiting for test convenience.

**Build status:** PASS

---

## 2026-05-18 — Session 29

**Focus:** Browse mode for Planner panel — `BlueprintMaterialsLoader` + Planner panel drill-down UI.

**Work completed:**
- Created `BlueprintMaterialsLoader.java` — static `getMaterials(structurePack, blueprintPath, level)` reads `config/DragonTweaks/{pack}/{path}{level}.json` via Gson, returns `Map<String, Integer>`, logs warning and returns empty map on missing file or parse failure.
- Added Browse mode to `PlannerPanelScreen.java` per `planner_panel_spec_v0_4.md`:
  - `[Browse]` button alongside goal input; goal input narrowed to accommodate it.
  - `[← Back]` button, visible only in browse mode, navigates up one drill level.
  - Four browse states: `PACK_LIST → CATEGORY_LIST → BUILDING_LIST → LEVEL_SELECTOR`.
  - Filesystem population from `config/DragonTweaks/` at each level (packs = subdirs, categories = subdirs, buildings = JSON filenames with level suffix stripped, levels = numeric suffix extracted).
  - `ITEMS_PER_PAGE = 8` constant added; browse uses separate `browsePage` counter distinct from `currentPage`.
  - Level selector shows clickable `[1] [2] ...` buttons; selecting a level calls `BlueprintMaterialsLoader.getMaterials()` and renders materials list in-panel.
  - Prev/Next buttons dispatched through `prevPage()`/`nextPage()` to handle both browse and non-browse modes.
- Snapshot mode logic untouched throughout.

**Decisions made:**
- Browse result (materials) shown in-panel within the LEVEL_SELECTOR browse state, not by transitioning to Goal Input mode — avoids needing to construct a `PlannerPanelPayload.GoalResult` client-side.
- `blueprintPath` passed to `BlueprintMaterialsLoader` as `category + "/" + buildingBase` to match nested directory structure.

**Deferred / carry-forward:**
- Browse mode untested against real JSON files (no pack data exists in repo yet).
- Hover highlight on browse entries not implemented (spec mentions it; deprioritized).

**Build status:** PASS

---

## 2026-05-17 — Session 28

**Focus:** D2 blocker — Planner dependency data, full building list with verified research chains.

**Work completed:**
- Identified ~13 buildings in prior seed data had incorrect or missing research gates
- Directed Claude Code to extract full research unlock data from MineColonies source repo (`civilian.json` + `technology.json`) into `docs/research_unlocks.md` (145 entries)
- Rebuilt `planner_dependencies.json` v2 from verified source data — 45 buildings total
- Confirmed Enchanter is freely buildable (it is a prereq for research, not unlocked by research)

**Decisions made:**
- School: gated behind `civilian/higherlearning` (Uni L1, Residence total lvl 3, 3x Book)
- Library: gated behind `civilian/keen` (Uni L1, Residence total lvl 3, 3x Book)
- Hospital: gated behind `civilian/stamina` (Uni L1, no building prereq, 1x Carrot)
- Graveyard: gated behind `civilian/remembrance` (Uni L1, Town Hall lvl 2, 8x Bone)
- Mystical Site: gated behind `civilian/ambition` (Uni L1, no building prereq, 1x Diamond)
- Composter: gated behind `technology/biodegradable` (Uni L1, Farmer total lvl 3, 64x Bone Meal)
- Florist: gated behind `technology/flowerpower` (Uni L2, Composter lvl 3)
- Plantation: gated behind `technology/letitgrow` (Uni L2, Farmer lvl 3)
- Dyer: gated behind `technology/rainbowheaven` (Uni L2, Composter lvl 3)
- Glassblower: gated behind `technology/thoselungs` (Uni L2, Smeltery lvl 3)
- Mechanic: gated behind `technology/whatyaneed` (Uni L2, Blacksmith lvl 3)
- Concrete Mixer: gated behind `technology/pavetheroad` (Uni L3, Crusher lvl 1)
- New buildings added: Smeltery, Stonemason, Stone Smeltery, Crusher, Sifter
- `research_unlocks.md` added to project as permanent reference document

**Deferred / carry-forward:**
- `PlannerDependencyRegistry.java` BUILDING_HOLDERS map needs entries for new buildings (Smeltery, Stonemason, Stone Smeltery, Crusher, Sifter) — verify ModBuildings DeferredHolder field names against stubs before implementing
- D2 design complete — implementation session pending

**Build status:** N/A (design session)

---

## 2026-05-17 — Session 27

**Focus:** D1 blocker — Advisor branching logic design + implementation.

**Work completed:**
- Designed and documented `advisor_branching_spec_v0_1.md` and `advisor_branching_spec_v0_2.md`
- Resolved pre-scan (max 5 citizens), output selection (top 2), throttle rules, root cause suppression
- `AdvisorThrottleData.java` — replaced `Set<String>` with two `Map<String, Integer>` maps; new API per spec Section 8
- `AdvisorDiagnosticLoop.java` — lines 85–101 replaced with pre-scan + top-2 selection + per-citizen suppression/throttle loop; `buildCitizenPrompt()` signature updated to accept `CitizenRecord`
- `Config.java` — added `ADVISOR_ROOTCAUSE_SUPPRESS_DAYS` (int, default 2)
- `DragonTweaks.java` — TODO comment added for `RaidStartedEvent` invalidation trigger (stub not found; deferred)
- Updated `current_state.md`, `open_questions_log.md` to reflect session 27 state
- Documentation cleanup pass performed by Claude Code

**Decisions made:**
- Pre-scan: up to 5 flagged citizens per cycle; sort red → yellow → alpha within tier
- Output: top 2 from candidate list; no tier special-casing
- Throttle: per-citizen per-day; key `"{colonyId}:{citizenId}:{colonyDay}"`; no mid-day re-fire
- Systemic pattern fires first but does NOT suppress per-citizen output
- Root cause suppression key: `"{colonyId}:{citizenId}:rc{ordinal}"`; window = `ADVISOR_ROOTCAUSE_SUPPRESS_DAYS` (default 2)
- `RaidStartedEvent` added to invalidation triggers (TODO pending stub verification)
- Non-target citizens use `RootCause.UNKNOWN` (ordinal 14) as suppression discriminator — known limitation, deferred

**Deferred / carry-forward:**
- B1: `RaidStartedEvent` package path — Claude Code to verify against stubs
- B2: `CitizenRecord` field names for flags and root cause — Claude Code to verify against stubs
- Known limitation: non-target citizens share `rc14` suppression key — root cause per-citizen requires generator redesign; deferred
- D1 needs in-game testing before refactor of `AdvisorDiagnosticLoop` is appropriate

**Build status:** PASS

---

## 2026-05-15 — Session 26

**Focus:** Model scraper evolution + Scout role design.

**Work completed:**
- Adapted Eigent-style cost weighting into role-tiered cost weighting for DragonTweaks; locked three-phase scraper pipeline (findmodels → test_compliance → rank_models)
- Produced `findmodels_v2_4.py` — role-tiered cost weighting, single sectioned JSON output, Eigent weighting removed
- Produced `citizen-roles-schema.md` — field definitions, allowed values, custom role authoring guide
- Produced `citizen-roles.json` — four current role definitions (Advisor, Planner, Scout, Ranch Hand)
- Locked Scout role design — terrain scan, underground scan, biome threat inference, sound detection, flavor framing rules

**Decisions made:**
- Scout reasoning: `reasoning_required: true` — biome-to-threat inference requires it
- Underground scan depth: ~10 blocks (8–12 configurable via `underground_scan_depth`); no upward extension
- Passive mob locations never reported — spawn data, not tactically useful
- Sound detection: vanilla + MineColonies mobs only; mod-added mobs excluded until explicitly added
- Output hard rules: no Y coordinates, no light level numbers, instinct/sense language only; occasional specificity (5–10%) intentional
- Any capability granted to any role must be defined as optional field in schema

**Deferred / carry-forward:**
- OQ-26-1: Reasoning token billing rate on OpenRouter — solve empirically in Phase 2
- OQ-26-2: Scout `underground_scan_depth` — finalize value in playtest
- OQ-26-3: Phase 2 compliance prompt engineering for Scout — not yet drafted
- OQ-26-4: `model_config.json` Java reader interval — not yet specified

**Build status:** N/A (design session)

---

## 2026-05-14 — Session 25

**Focus:** Documentation refactor — split devchat.md into structured doc set, rewrote CLAUDE.md, established new session closeout process.

**Work completed:**
- Refactored `devchat.md` into dedicated doc files (`current_state.md`, `architecture_rules.md`, `devchatindex.md`, `open_questions_log.md`, `session_history.md`, etc.)
- Rewrote `CLAUDE.md` — removed stale file references, corrected startup flow to point at `devchatindex.md`, updated closeout instructions
- Created this session log as the new living record of session work
- Updated `devchatindex.md` startup checklist to reflect new doc structure

**Decisions made:**
- `devchat.md` is now archive/safety net only — Claude Code no longer directed to update it
- Session closeout now targets: `current_state.md` (file status + locked decisions) + `dragontweaks_verification_checklist.md` (if build passed) + this log
- Living session log is append-only, most recent entry at top

**Deferred / carry-forward:**
- D1: Advisor `Observe → Diagnose → Recommend` branching logic spec — still open
- D2: Full building enumeration with dependency chains for Planner goal input mode — still open

**Build status:** N/A (documentation session)
