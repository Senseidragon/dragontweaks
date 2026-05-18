# DragonTweaks — Session Log

*Append-only. One entry per session. Most recent at the top.*
*Purpose: Capture what was done, why, and any decisions made. Not a file status table — that lives in `current_state.md`.*

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
- D2: Full building enumeration with dependency chains for Planner goal input mode — still open

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
- Any capability granted to any role must be defined as optional field in schema — custom role authors must be able to discover all capabilities

**Deferred / carry-forward:**
- OQ-26-1: Reasoning token billing rate on OpenRouter — solve empirically in Phase 2
- OQ-26-2: Scout `underground_scan_depth` — finalize value in playtest
- OQ-26-3: Phase 2 compliance prompt engineering for Scout — not yet drafted
- OQ-26-4: `model_config.json` Java reader interval — not yet specified
- D1: Advisor branching logic spec — still open (resolved session 27)
- D2: Planner dependency chains — still open

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
