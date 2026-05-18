# DragonTweaks — Master Open Questions Log
*Last updated: 2026-05-17 (sessions 26 + 27)*
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
| B1 | 🔲 | `RaidStartedEvent` — verify package path against `docs/stubs/` | `DragonTweaks.java` invalidation trigger (branching spec Section 5) |
| B2 | 🔲 | `CitizenRecord` field names for red/yellow flags, commute flag, and root cause ordinal — verify against current `ColonyDiagnosticReport` implementation | Pre-scan and suppression key logic (branching spec Sections 1 and 4.2) |

---

## Design Sessions Required
*These require a design session before implementation can begin. Do not implement without them.*

| # | Status | Question | Blocks |
|---|---|---|---|
| D1 | ✅ | Advisor happiness diagnostic branching logic — `Observe → Diagnose → Recommend` as implementable code spec | Resolved 2026-05-17 — see `docs/advisor_branching_spec_v0_2.md` |
| D2 | 🔲 | Dependency data spec — enumerated building list with full chains for Planner goal input mode | Planner goal input mode |

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
| OQ-26-1 | 🔁 | Reasoning token billing rate on OpenRouter | Solve empirically in Phase 2 — fire identical prompt at reasoning vs non-reasoning model, solve for X from response payload |
| OQ-26-2 | 🔁 | Scout `underground_scan_depth` — finalize value | Currently 10, range 8–12; adjust based on playtest |
| OQ-26-3 | 🔁 | Phase 2 compliance prompt engineering for Scout | Sound detection, threat inference, and atmospheric language compliance prompts not yet drafted |
| OQ-26-4 | 🔁 | `model_config.json` Java reader interval | How often does the mod re-read the JSON at runtime? Not yet specified |

---

## Resolved Items

| # | Question | Answer | Date |
|---|---|---|---|
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

---

*Update this log at the end of every design session and after every Claude Code verification result.*
