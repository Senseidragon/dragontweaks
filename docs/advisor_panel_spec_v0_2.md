# Advisor Panel — Design Specification v0.2

**DragonTweaks · NeoForge 1.21.1**
*Locked 2026-05-07. Do not implement without loading this document.*

---

## IMPLEMENTATION SUMMARY

### Panel Structure (Top to Bottom)

1. Environmental warnings banner (conditional)
2. Systemic pattern banner (conditional)
3. Colony summary header (always visible)
4. LLM summary paragraph — full mode only (conditional on mode)
5. Citizen list (paginated)

### Pagination

- Fixed items per page, next/previous controls
- No continuous scroll

### Lite vs Full Mode

- **Lite mode:** structured data only
- **Full mode:** short LLM-generated summary paragraph inserted between colony summary header and citizen list — Advisor NPC voices a brief framing of what the panel is showing

### Environmental Warnings Banner

- Top of panel, above everything
- Shown only when one or more conditions active

| Condition | Source |
|---|---|
| `doDaylightCycle` is false | `GameRules` query |
| Active raid in progress | MineColonies API `ThreatLevel` |
| Thunderstorm active | Vanilla weather state |

- When active: happiness diagnostics below marked as potentially unreliable
- Not dismissible — clears automatically when condition resolves

### Systemic Pattern Banner

- Below environmental banner, above colony summary header
- Shown only when pattern-matching logic detects a systemic pattern
- At most one banner shown — highest severity pattern wins

| Pattern | Trigger Condition |
|---|---|
| All citizens red simultaneously | All citizens have at least one red factor |
| Newly founded colony, all red | Colony age < 3 days AND all citizens red |
| Housing + sleep + commute cluster | housing + slepttonight red AND commute flagged, affecting 2+ citizens |

- Pattern logic runs on `ColonyDiagnosticReport` — no additional API calls
- Colony age detection: verify API availability before implementing — see verification checklist

### Colony Summary Header

| Field | Notes |
|---|---|
| Overall happiness score | Colony-wide aggregate, 0.0–∞, neutral at 1.0 |
| Citizen count | Total citizens |
| Housing cap | Total bed capacity |

Nothing else. No complaint counts, no breakdowns.

### Citizen List — Ordering

Three tiers, alphabetical within each tier:

| Tier | Criteria |
|---|---|
| Red | One or more factors below red threshold OR commute flagged |
| Yellow | One or more factors in yellow range, none red |
| Healthy | All factors green, commute within threshold |

### Citizen List — Per-Row (Collapsed Default)

| Field | Notes |
|---|---|
| Citizen name | |
| Worst factor label | Name of single lowest-scoring factor |
| Worst factor value | Numeric score |
| Severity indicator | Red / yellow / green |
| Additional complaints badge | Count of other flagged factors, e.g. `+2` |
| Commute flag | Shown inline only when commute exceeds 80-block threshold |

- Expand/collapse state persists while panel is open
- State resets only on panel close — not on cache refresh

### Citizen List — Per-Row (Expanded)

- All ten canonical happiness factors: factor ID, value, severity indicator
- Factor modifier type shown (Static / TimeBased / ExpirationBased)
- Commute distance with threshold comparison: e.g. `94 blocks — exceeds 80-block threshold`
- Commute flagged visually identical to a red happiness factor

### Commute Threshold

| Parameter | Value |
|---|---|
| Default threshold | 80 blocks |
| Config key | `ADVISOR_COMMUTE_THRESHOLD` (to be added to Config.java) |
| Distance calculation | Derived from `getWorkBuilding()` and `getHomeBuilding()` positions |
| API source | Not a MineColonies happiness factor — Advisor-derived only |

### Happiness Factor Reference

| Factor ID | Modifier Type | Notes |
|---|---|---|
| `food` | StaticHappinessModifier | |
| `slepttonight` | TimeBasedHappinessModifier | |
| `housing` | StaticHappinessModifier | |
| `health` | StaticHappinessModifier | |
| `unemployment` | StaticHappinessModifier | |
| `idleatjob` | ExpirationBasedHappinessModifier | |
| `security` | StaticHappinessModifier | |
| `school` | StaticHappinessModifier | |
| `social` | ExpirationBasedHappinessModifier | |
| `mystical` | StaticHappinessModifier | |
| `greatfood` | Unverified | ⚠️ Do not implement until ID confirmed against MineColonies source |

### Happiness Thresholds

⚠️ Red and yellow threshold values must be verified against MineColonies source before implementing tier logic. Do not hardcode assumptions. See verification checklist.

### Recommendations

None. Advisor panel is purely diagnostic. Recommendations are Planner scope.

### Data Source

- All content from `ColonyDiagnosticReport` cache
- Cache TTL: 30s (configurable)
- Early invalidation on: `BuildingConstructionModEvent`, `CitizenJobChangedModEvent`, `CitizenAddedModEvent`, `CitizenDiedModEvent`
- Panel never triggers a fresh API poll

---

## DESIGN RECORD

### Commute Threshold Rationale

80 blocks chosen as default. MineColonies building placement norms in a functioning colony keep most workers within 40–60 blocks of their home. 80 blocks gives reasonable headroom before flagging without being so generous that genuine commute problems go unreported. Config value — operators can tune for their colony layout.

### Expand/Collapse Persistence Rationale

State persists while panel is open, resets on close. Resetting on every 30-second cache refresh would be disruptive — the panel would collapse expanded citizens mid-inspection. Persisting until close respects the player's intent without requiring explicit save/restore of UI state across sessions.

### Diagnostic-Only Rationale

No recommendations in the Advisor panel. Role separation is intentional: Advisor observes and diagnoses, Planner recommends and sequences. Mixing recommendation output into the Advisor panel blurs this boundary and duplicates logic already in the Planner panel. In full mode the LLM Advisor NPC can bridge between the two verbally — the panels stay separated.

### Systemic Pattern Banner Rationale

Explicit in the panel, not silent. The Advisor knowing something meaningful and withholding it is inconsistent with its role. Banner appears at panel scope above the citizen list because systemic patterns are colony-wide findings, not per-citizen findings. One banner maximum — highest severity wins to prevent stacking.

### Verification Prerequisites — Do Not Implement Until Resolved

| Item | Verification Target | Blocks |
|---|---|---|
| `greatfood` factor ID | MineColonies source — confirm ID exists and spelling | Happiness iteration code |
| Red/yellow threshold values | MineColonies source — confirm numeric cutoffs | Citizen tier logic |
| Colony age detection | MineColonies API — confirm colony age is queryable | Newly-founded pattern banner |

---

*v0.2 — All design decisions resolved 2026-05-07. Three source verification items remain — tracked on rebuild checklist.*
*Supersedes v0.1.*
