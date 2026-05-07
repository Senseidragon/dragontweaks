# Planner Panel — Design Specification v0.3

**DragonTweaks · NeoForge 1.21.1**
*Locked 2026-05-07. Do not implement without loading this document.*

---

## IMPLEMENTATION SUMMARY

### Mode Logic

- Panel opens in **Snapshot mode** by default
- **Goal Input mode** accessed via text field at top: `"Plan a path to... [___]"`
- Both modes read exclusively from `ColonyDiagnosticReport` cache — never trigger a fresh API poll
- **Full mode only:** short LLM-generated summary paragraph displayed above structured data in both modes
- **Lite mode:** structured data only — no LLM paragraph

### Pagination

- Both panels use pagination — fixed items per page with next/previous controls
- No continuous scroll

### Snapshot Mode — Logic

- **Priority order:** Crisis items first → then non-crisis ordered by shortest dependency chain (fewest steps)
- Crisis definition: active blocker causing compounding harm — no beds, unemployed citizens, stalled food, active raid damage
- Each recommendation shows medium detail by default
- If recommendation target is already assigned to Builder: annotate `[In Progress]` — do not suppress
- Silently check work order queue for annotation — do not expose full queue in panel

### Snapshot Mode — Per-Recommendation Fields

| Field | Notes |
|---|---|
| Target name | Building or research name |
| Priority badge | `[!]` crisis / `[ ]` non-crisis |
| Immediate blocker | Single next action required |
| Research prereq | Shown inline only when research is the blocker or a required step |
| Worker slots needed | Shown only when worker availability is the active constraint |
| Bed capacity delta | Shown only when bed availability is the active constraint |
| Steps remaining | Integer count — no time estimate |
| In-progress annotation | `[In Progress — Builder assigned]` if applicable |

### Snapshot Mode — Persistent Header

| Field | Always Visible |
|---|---|
| Worker slot fill | e.g. `Workers: 4 / 6 slots filled` |
| Bed occupancy | e.g. `Beds: 6 / 8 occupied` |

### Goal Input Mode — Logic

- Input: building or goal name, case-insensitive
- Matching: exact string match only — on near-miss, show "did you mean" suggestions
- On recognized input: show full dependency chain + cost estimate block + collapsible materials list
- Chain ordered longest blocker first (not chronological)
- Completed steps shown greyed/struck-through; first incomplete step highlighted
- On partial match: show known chain, flag unresolved portion with suggestions
- On no match: show known building list or "did you mean" suggestion — never silent failure, never blank

### Goal Input Mode — Cost Estimate Fields

| Field | Notes |
|---|---|
| Steps remaining | Integer count of incomplete steps in chain |
| Research needed | Names only, inline |

**No time estimate.** Builder speed is unpredictable — steps remaining is the honest measure.

### Goal Input Mode — Materials List

- Collapsible, collapsed by default
- Label: `"Materials needed"` or similar
- Appears in goal input mode only — not in snapshot mode
- Stock check sources: warehouse inventory + player inventory combined
- Deficit = total required − (warehouse stock + player inventory)

| Condition | Behavior |
|---|---|
| Warehouse exists | Show deficit after subtracting warehouse stock + player inventory |
| No warehouse exists | Show full material requirement, note that no warehouse was found |
| Material fully covered | Show item as satisfied (greyed or checked) |

**Example display:**
```
▶ Materials needed
  Oak Planks     need 100  (300 required, 200 in warehouse)
  String         need 16   (16 required, 0 found)
  Anvil          ✓ satisfied (1 found in inventory)
```

### Goal Input Mode — Unrecognized Input

| Condition | Behavior |
|---|---|
| Exact match found | Show full chain + cost estimate + materials list |
| No exact match, partial match exists | Show known chain, flag unresolved portion, show "did you mean" suggestions |
| No match at all | Show known building list or "did you mean" suggestions — never blank |

### Silent Background Checks (Not Displayed)

- Work order queue — drives `[In Progress]` annotation only
- Research gate — both University level AND building level prereqs evaluated simultaneously
- Worker availability — drives worker slot constraint display
- `doDaylightCycle` state — checked as environmental precondition; does not suppress steps remaining

### Explicit Exclusions

| Excluded | Reason |
|---|---|
| Full work order queue | Builder status, not Planner scope |
| Dedicated research section | Research shown inline per recommendation only |
| Per-citizen happiness data | Advisor panel scope |
| Mob threat / raid status | Scout scope |
| Full building inventory | Too broad |
| Time estimates | Builder speed is unpredictable — steps remaining only |
| Materials list in snapshot mode | Goal input only |

### Data Source

- All content from `ColonyDiagnosticReport` cache
- Cache TTL: 30s (configurable)
- Early invalidation on: `BuildingConstructionModEvent`, `CitizenJobChangedModEvent`, `CitizenAddedModEvent`, `CitizenDiedModEvent`
- Materials list additionally requires: warehouse inventory API + player inventory — verify API availability before implementing

---

## DESIGN RECORD

### Time Estimate Rationale

Steps remaining only — no day range estimate. Builder speed varies with materials, pathfinding, and interruptions. A wrong estimate damages player trust more than no estimate. Honest and simple wins.

### Materials List Rationale

Goal input mode only — not snapshot mode. Snapshot recommendations are prioritized suggestions; attaching a full materials breakdown to every snapshot item would create noise. Goal input is player-driven and goal-specific, so a materials deficit list is directly actionable in that context.

Warehouse + player inventory combined before calculating deficit. Player may be carrying materials that haven't been deposited yet — ignoring inventory would overstate the deficit and send the player farming resources they already have.

No warehouse present: show full requirement with an explicit note. Never silently assume zero stock — the note prompts the player to build a warehouse, which is itself a dependency.

### Pagination Rationale

Paginate over scroll. Minecraft panel UIs are fixed-size. Pagination gives the player explicit control over navigation and avoids the visual noise of a continuous scrolling list in a small panel frame.

### Goal Input Matching Rationale

Exact match + "did you mean" over fuzzy match. Fuzzy matching adds implementation complexity and produces unpredictable results — "Fletchr" might match "Fletcher" or might match something else entirely depending on the algorithm. Exact match is deterministic; "did you mean" suggestions cover the common typo case without ambiguity.

### Lite vs Full Mode Rationale

Full mode adds a short LLM-generated summary paragraph above the structured data. This gives the Planner NPC a voice in the panel — the structured data is the same in both modes, but in full mode the Planner character frames it conversationally. Lite mode is structured data only.

### Open Questions — ALL RESOLVED

No open questions remain for this spec.

---

*v0.3 — All open questions resolved 2026-05-07.*
*Supersedes v0.1 and v0.2.*
