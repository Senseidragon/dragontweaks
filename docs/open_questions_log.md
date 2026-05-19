# DragonTweaks — Master Open Questions Log
*Last updated: 2026-05-19 (sessions 26 + 27 + 28 + 29 + 30 + 31)*
*Purpose: Single source of truth for all unresolved questions. Check this before starting any design or implementation session.*

---

## Status Key

| Symbol | Meaning |
|---|---|
| 🔲 | Unresolved — blocks implementation |
| ✅ | Resolved — answer recorded below |
| 🔁 | Deferred by design — not a blocker |

---

## Source Verification Items
*Resolvable by Claude Code reading stubs — no design session required.*

| # | Status | Question | Blocks |
|---|---|---|---|
| B1 | ✅ | `RaidStartedEvent` — verify package path against `docs/stubs/` — **Answer:** Not present in stubs. Confirmed TODO only — invalidation comment remains in DragonTweaks.java. Not a blocker. | `DragonTweaks.java` invalidation trigger (branching spec Section 5) |
| B2 | ✅ | `CitizenRecord` field names for red/yellow flags, commute flag, and root cause ordinal — verify against current `ColonyDiagnosticReport` implementation — **Answer:** Red/yellow flags on `HappinessFactor.isRedFlag()`/`isYellowFlag()`. Root cause on report-level `RootCause` enum. `AdvisorDiagnosticLoop.java` confirmed clean — no invalid field access. | Pre-scan and suppression key logic (branching spec Sections 1 and 4.2) |
| B3 | ✅ | `PlannerDependencyRegistry.java` BUILDING_HOLDERS map — verify ModBuildings DeferredHolder field names for new buildings: Smeltery, Stonemason, Stone Smeltery, Crusher, Sifter — **Answer:** Resolved this session — `PlannerDependencyRegistry.java` is JSON-driven. No BUILDING_HOLDERS map exists or is needed. 5 new buildings already covered by `planner_dependencies.json` v2. | Planner goal input chain resolution for new buildings |

---

## Design Sessions Required
*These require a design session before implementation can begin. Do not implement without them.*

| # | Status | Question | Blocks |
|---|---|---|---|
| D1 | ✅ | Advisor happiness diagnostic branching logic — `Observe → Diagnose → Recommend` as implementable code spec | Resolved 2026-05-17 — see `docs/advisor_branching_spec_v0_2.md` |
| D2 | ✅ | Dependency data spec — enumerated building list with full chains for Planner goal input mode | Resolved 2026-05-17 — see `src/main/resources/data/dragontweaks/planner_dependencies.json` (v2) |

---

## Deferred Items
*Intentionally parked. Not blockers.*

| # | Status | Question | Notes |
|---|---|---|---|
| R1 | 🔁 | Mod final name | TBD |
| R2 | 🔁 | Flavor NPC spawn cap value | Design session needed before implementation |
| R3 | 🔁 | Auto-detect client locale via packet | NeoForge 1.21.1 API path verification needed |
| R4 | 🔁 | LLM intent classification for command parser (FOLLOW/STOP/NONE) | Architectural decision pending |
| R5 | 🔁 | Full mode LLM summary paragraph — prompt design for panel context | Panel structure resolved; LLM prompt wording TBD at implementation |
| R6 | 🔁 | NPC cross-awareness | Revisit Phase 4 |
| R7 | 🔁 | Shadow entity multiplayer visibility | Test Phase 2 |
| R8 | 🔁 | Quest system | Parking lot — no timeline |
| OQ-26-1 | 🔁 | Reasoning token billing rate on OpenRouter | Solve empirically in Phase 2 — fire identical prompt at reasoning vs non-reasoning model |
| OQ-26-2 | 🔁 | Scout `underground_scan_depth` — finalize value | Currently 10, range 8–12; adjust based on playtest |
| OQ-26-3 | 🔁 | Phase 2 compliance prompt engineering for Scout | Sound detection, threat inference, atmospheric language prompts not yet drafted |
| OQ-26-4 | ✅ | `model_config.json` Java reader interval | **Answer:** 15-minute cache implemented in `ModelConfigLoader.java` (session 31). Fallback paths not cached — next call retries. |
| OQ-27-1 | 🔁 | Non-target citizens share `rc14` suppression key — root cause per-citizen requires generator redesign | Deferred — known limitation, revisit after D1 in-game testing |
| OQ-30-1 | 🔲 | `model_config.json` first candidate (`openai/gpt-oss-120b`) is a reasoning model — spends token budget on reasoning, returns `content: null`. Should `ModelConfigLoader` filter out reasoning models (e.g. by `reasoning_excluded` flag or model ID suffix), or must the candidates list be manually curated to put a non-reasoning model first? | Advisory LLM responses in all states |

---

## Resolved Items

| # | Question | Answer | Date |
|---|---|---|---|
| D2 | Full building list with dependency chains for Planner goal input mode | 45 buildings in `planner_dependencies.json` v2. Source: MineColonies `civilian.json` + `technology.json`. Reference: `docs/research_unlocks.md` | 2026-05-17 |
| D1 | Advisor branching logic — `Observe → Diagnose → Recommend` as implementable spec | Pre-scan 5 citizens, output top 2, per-day throttle, root cause suppression window. Full spec: `docs/advisor_branching_spec_v0_2.md` | 2026-05-17 |
| V1 | `greatfood` modifier ID | Omit — ten confirmed canonical IDs only | 2026-05-07 |
| V2 | Red/yellow happiness thresholds | red < 0.5 / yellow 0.5–0.9 / healthy ≥ 0.9 | 2026-05-07 |
| V3 | Colony age API | `IColony.getDay()` returns int | 2026-05-07 |
| V4 | Warehouse inventory API | `getMatchingItemStacksInWarehouse(Predicate<ItemStack>)` — predicate per item | 2026-05-07 |
| V5 | OpenRouter reasoning disable | `{"reasoning": {"effort": "none"}}` | 2026-05-07 |
| — | Panel scroll vs paginate | Paginate — fixed items per page, next/previous | 2026-05-07 |
| — | Planner goal input matching | Exact string match + "did you mean" suggestions | 2026-05-07 |
| — | Lite vs full mode panel differences | Full mode adds LLM summary paragraph above structured data | 2026-05-07 |
| — | Commute distance threshold | 80 blocks — config key `ADVISOR_COMMUTE_THRESHOLD` | 2026-05-07 |
| — | Expand/collapse persistence | Persists while panel open, resets on close | 2026-05-07 |
| — | Time estimate in goal input mode | Steps remaining only — no day estimate | 2026-05-07 |
| — | Materials list location | Goal input mode only, collapsed by default | 2026-05-07 |
| — | Materials stock check sources | Warehouse + player inventory combined | 2026-05-07 |
| — | Advisor panel recommendations | None — diagnostic only | 2026-05-07 |
| — | Systemic pattern banner | Explicit banner above citizen list | 2026-05-07 |
| — | Citizen list ordering | Flagged-first: red → yellow → healthy, alphabetical within tier | 2026-05-07 |
| — | Colony summary header | Overall happiness + citizen count + housing cap only | 2026-05-07 |
| — | Snapshot mode priority | Hybrid: crisis first, then shortest chain | 2026-05-07 |
| — | Research prereq visibility | Inline per recommendation only | 2026-05-07 |
| — | Worker/bed visibility | Persistent header strip, always visible | 2026-05-07 |
| B1 | `RaidStartedEvent` stub verification | Not present in stubs. Confirmed TODO only — invalidation comment remains in `DragonTweaks.java`. Not a blocker. | 2026-05-18 |
| B2 | `CitizenRecord` field names for red/yellow flags and root cause ordinal | Red/yellow flags on `HappinessFactor.isRedFlag()`/`isYellowFlag()`. Root cause on report-level `RootCause` enum. `AdvisorDiagnosticLoop.java` confirmed clean — no invalid field access. | 2026-05-18 |
| B3 | `PlannerDependencyRegistry.java` BUILDING_HOLDERS map for 5 new buildings | Registry is JSON-driven. No BUILDING_HOLDERS map exists or is needed. 5 new buildings already covered by `planner_dependencies.json` v2. | 2026-05-18 |
| OQ-26-4 | `model_config.json` Java reader interval | 15-minute cache in `ModelConfigLoader.java`. Fallback paths not cached. | 2026-05-19 |

---

## Live Testing Observations

| # | Status | Observation | Action Required |
|---|---|---|---|
| OQ-28-1 | 🔲 | Happiness threshold values vs. actual MineColonies scale — `ADVISOR_HAPPINESS_THRESHOLD_RED` (0.5) and `ADVISOR_HAPPINESS_THRESHOLD_YELLOW` (0.9) were defined assuming a 0–1 factor scale. `IHappinessModifier.getFactor()` uses a scale where 1.0 is neutral, values can go below 0, and the upper bound is unbounded. `social: -1.00` observed in live testing on a fresh colony — confirmed expected behavior for `ExpirationBasedHappinessModifier` with no interaction history. Threshold values need design review before Advisor LLM recommendations go live. Do not adjust thresholds without consulting the MineColonies source for what constitutes a "bad" factor value on this scale. | Design session required — review MineColonies source to establish meaningful threshold values on the actual scale |

---

*Update this log at the end of every design session and after every Claude Code verification result.*
