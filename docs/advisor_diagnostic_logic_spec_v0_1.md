# Advisor Diagnostic Branching Logic — Specification v0.1

**DragonTweaks · NeoForge 1.21.1**
*Locked 2026-05-07. Do not implement without loading this document.*

---

## IMPLEMENTATION SUMMARY

### Trigger Conditions

The Advisor runs its `Observe → Diagnose → Recommend` cycle when:
- `ColonyDiagnosticReport` cache is invalidated by any of: `BuildingConstructionModEvent`, `CitizenJobChangedModEvent`, `CitizenAddedModEvent`, `CitizenDiedModEvent`
- Fixed interval fallback: every 30s (matches cache TTL) regardless of events

Cycle always reads from cache — never triggers a fresh API poll.

### Phase 1 — Observe (Environmental)

Runs in parallel with all other phases — never blocks diagnosis.

| Check | Source | Flag If |
|---|---|---|
| `doDaylightCycle` | `GameRules` | false |
| Active raid | MineColonies `ThreatLevel` | raid active |
| Thunderstorm | Vanilla weather state | thunderstorm active |

- Environmental flags are attached to the `DiagnosisResult` object
- Diagnosis continues regardless of flag state
- Panel environmental banner and LLM output both read from these flags

### Phase 2 — Observe (Colony State)

Read from `ColonyDiagnosticReport` cache:
- All citizen happiness factor values (ten canonical IDs only — no `greatfood`)
- Overall happiness score per citizen (`h.getHappiness(colony, data)`)
- Commute distance per citizen (derived: work building pos vs home building pos)
- Colony day count (`IColony.getDay()`)

### Phase 3 — Diagnose (Systemic Pattern Check)

Runs before per-citizen analysis. Evaluated in priority order — first match wins.

| Priority | Pattern | Trigger Condition |
|---|---|---|
| 1 | Newly founded, all red | `colony.getDay() < 3` AND all citizens have at least one red factor |
| 2 | All citizens red simultaneously | All citizens have at least one red factor (any colony age) |
| 3 | Housing/sleep/commute cluster | `housing` red AND `slepttonight` red AND commute flagged, affecting ≥ 2 citizens |

**If a systemic pattern is matched:**
- Set `systemicPatternDetected = true` in `DiagnosisResult`
- Record which pattern matched
- **Skip per-citizen LLM output** — Advisor verbal output addresses the pattern only
- **Do not skip panel citizen list** — panel always shows full citizen list regardless

**If no systemic pattern:**
- Proceed to per-citizen diagnosis

### Phase 4 — Diagnose (Per-Citizen)

Only runs when no systemic pattern detected.

**Target citizen selection:**
- Find citizen with lowest overall happiness score (`h.getHappiness(colony, data)`)
- If tie: alphabetical by name
- Only this citizen receives full diagnosis this cycle

**Per-citizen diagnosis steps:**
1. Identify worst factor: lowest `mod.getFactor(data)` across all ten canonical IDs
2. Collect all red factors: any factor where `mod.getFactor(data) < ADVISOR_HAPPINESS_THRESHOLD_RED`
3. Apply root cause rules (see Root Cause Table below)
4. Check proactive throttle before firing LLM output

### Root Cause Table

Rule-based logic determines cause. LLM narrates it in character.

| Factor ID | Rule | Root Cause Hypothesis |
|---|---|---|
| `slepttonight` | commute > `ADVISOR_COMMUTE_THRESHOLD` | Bed too far from work building |
| `slepttonight` | `housing` also red | No home building assigned |
| `slepttonight` | neither above | Unknown — flag for player inspection |
| `housing` | no home building assigned | No Residence available or assigned |
| `food` | no Cook/Restaurant building exists | No food production building |
| `food` | building exists | Supply chain issue — flag for player inspection |
| `unemployment` | citizen has no job | No matching work building available |
| `idleatjob` | citizen has job | Pathfinding or supply blockage at work building |
| `security` | no Guard Tower within range | No guard coverage at citizen location |
| `health` | — | Injury or disease — no automated fix available |
| `social` | — | Low citizen count or isolation — flag for player |
| `school` | — | No School building or child citizen unassigned |
| `mystical` | — | No Mystical Site within range |
| commute | distance > `ADVISOR_COMMUTE_THRESHOLD` | Home building too far from work building |

- If a factor is red but no rule matches: hypothesis = "unknown cause — player inspection recommended"
- Root cause is passed to LLM as structured context, not free text
- LLM narrates the hypothesis in Advisor character voice

### Phase 5 — Proactive Output (Throttled)

**Throttle rule:** Advisor delivers unprompted diagnosis at most once per in-game day per citizen.

| Field | Notes |
|---|---|
| Throttle key | `citizenId + colonyDay` |
| Storage | `SavedData` — persists across sessions |
| Reset | New in-game day (`IColony.getDay()` increments) |
| Scope | Per-citizen — one citizen firing does not block others |

**Output fires when:**
- Worst citizen overall happiness < `ADVISOR_HAPPINESS_THRESHOLD_RED`
- Throttle key not already fired this in-game day
- At least one player is within Advisor detection range (consistent with existing ObservationTicker pattern)

**Output suppressed when:**
- Throttle already fired for this citizen today
- No player in range
- Systemic pattern detected (systemic pattern output fires instead, subject to its own throttle — see below)

**Systemic pattern proactive output:**
- Same throttle mechanism — key is `patternType + colonyDay`
- Fires at most once per pattern type per in-game day

### DiagnosisResult Object

Passed from Diagnose phase to LLM context builder.

| Field | Type | Notes |
|---|---|---|
| `environmentalFlags` | `List<EnvironmentalFlag>` | Active environmental conditions |
| `systemicPatternDetected` | boolean | |
| `systemicPattern` | `SystemicPattern` enum (nullable) | Which pattern matched |
| `targetCitizen` | `ICitizenData` (nullable) | Null if systemic pattern detected |
| `worstFactor` | `String` (nullable) | Factor ID of lowest value |
| `redFactors` | `List<String>` | All factor IDs below red threshold |
| `rootCause` | `RootCause` enum (nullable) | Rule-matched cause |
| `commuteDistance` | int | Blocks — always populated |
| `commuteFlagged` | boolean | `commuteDistance > ADVISOR_COMMUTE_THRESHOLD` |

### Config Values Required

| Config Key | Type | Default | Notes |
|---|---|---|---|
| `ADVISOR_HAPPINESS_THRESHOLD_RED` | DoubleValue | 0.5 | Already added to Config.java |
| `ADVISOR_HAPPINESS_THRESHOLD_YELLOW` | DoubleValue | 0.9 | Already added to Config.java |
| `ADVISOR_COMMUTE_THRESHOLD` | IntValue | 80 | Already added to Config.java |

### Thread Safety

- All diagnosis logic runs async — never on main game thread
- `DiagnosisResult` is immutable once constructed
- LLM call follows existing `LLMClient` async pattern
- Proactive output queued back to main thread via server tick queue before delivery

---

## DESIGN RECORD

### Environmental Parallel Rationale

Environmental checks run in parallel rather than blocking. Even when `doDaylightCycle` is false, the player still benefits from seeing the full diagnosis — they need to know what's wrong so they can fix it once the game rule is corrected. The environmental banner in the panel already signals unreliability; blocking diagnosis entirely would just leave the player with less information.

### Systemic Pattern Priority Rationale

Systemic patterns suppress per-citizen LLM output only — not the panel citizen list. The Advisor's verbal output is most useful when focused: if a systemic cause exists, individual citizen diagnosis is noise. But the panel is a reference tool — the player may be checking it independently of what the Advisor is saying, and hiding the citizen list removes useful data.

### Worst Citizen by Overall Score Rationale

Overall happiness score (weighted aggregate) chosen over worst single factor or most complaints. A citizen with one severely red factor may need more attention than a citizen with three mild yellow factors. The weighted aggregate surfaces the citizen most in distress, not just the most complained-about.

### Hybrid Root Cause Rationale

Rule-based logic determines the cause; LLM narrates in character. Pure LLM root cause generation is unpredictable and may hallucinate causes that don't exist in the colony. Pure rule-based output is dry and breaks immersion. Hybrid gives deterministic accuracy with characterful delivery.

### Proactive Throttle Rationale

Once per in-game day per citizen. Too frequent and the Advisor becomes noise the player learns to ignore. Too infrequent and genuine problems go unreported. One per day mirrors the natural rhythm of MineColonies colony management — players check in roughly once per day cycle. Throttle key includes citizen ID so multiple citizens can each fire once per day independently.

---

*v0.1 — Generated from design session 2026-05-07.*
*Next: D2 — Dependency data spec for Planner goal input mode.*
