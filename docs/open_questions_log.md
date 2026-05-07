# DragonTweaks — Master Open Questions Log
*Last updated: 2026-05-07*
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
*All resolved 2026-05-07.*

| # | Status | Question | Answer |
|---|---|---|---|
| V1 | ✅ | `greatfood` happiness modifier ID | **Omit entirely.** Runtime string not visible from stubs. Implement only the ten confirmed canonical IDs. Constant name `HADGREATFOOD` noted for reference only. |
| V2 | ✅ | Red/yellow happiness threshold values | **Design decision — no API tiers exist.** `factor < 0.5` = red, `0.5 ≤ factor < 0.9` = yellow, `factor ≥ 0.9` = healthy. Make these config values. |
| V3 | ✅ | Colony age in days queryable? | **Confirmed.** `IColony.getDay()` returns `int`. Source: `IColony.java` line 81. |
| V4 | ✅ | Warehouse inventory API | **Partial — sufficient.** `getMatchingItemStacksInWarehouse(Predicate<ItemStack>)` returns `List<Tuple<ItemStack, BlockPos>>`. Source: `AbstractTileEntityWareHouse.java` line 8. No full-dump method — query per material using predicate. |
| V5 | ✅ | OpenRouter reasoning disable syntax | **Confirmed.** `{"reasoning": {"effort": "none"}}`. Add to `LLMClient.java` request body. |

---

## Design Sessions Required
*These require a design session before implementation can begin. Do not implement without them.*

| # | Status | Question | Blocks |
|---|---|---|---|
| D1 | 🔲 | Advisor happiness diagnostic branching logic — `Observe → Diagnose → Recommend` as implementable code spec | Advisor implementation |
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

---

## Resolved Items

| # | Question | Answer | Date |
|---|---|---|---|
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
