# Planner Panel — Design Specification v0.4

**DragonTweaks · NeoForge 1.21.1**
*Supersedes v0.3. Do not implement without loading this document.*

---

## IMPLEMENTATION SUMMARY

### Panel Dimensions

- Width: 324px
- Height: 270px
- Line height: 10px

### Mode Logic

- Panel opens in **Snapshot mode** by default
- **Goal Input mode** accessed via text field at top: `"Plan a path to... [___]"`
- **Browse mode** accessed via `[Browse]` button alongside the text field
- All modes read exclusively from `ColonyDiagnosticReport` cache — never trigger a fresh API poll
- **Full mode only:** short LLM-generated summary paragraph displayed above structured data in both Snapshot and Goal Input modes
- **Lite mode:** structured data only — no LLM paragraph

### Pagination

- All modes use pagination — fixed items per page with prev/next controls
- No continuous scroll
- ITEMS_PER_PAGE inherited from existing constant

---

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

---

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
- Appears in goal input mode and browse mode — not in snapshot mode
- Stock check sources: warehouse inventory + player inventory combined
- Deficit = total required − (warehouse stock + player inventory)
- If list exceeds visible area: paginated with prev/next controls

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

---

### Browse Mode — Logic

- Activated by `[Browse]` button alongside the goal input text field
- Browse button toggles between Browse mode and Goal Input mode; text field remains accessible in both
- Content area replaced by a three-level drill-down tree: **Pack → Category → Building**
- Each level is a paginated list of clickable entries
- Selecting a Pack shows its categories; selecting a category shows its buildings; selecting a building loads Goal Input mode for that building (full chain + cost estimate + materials list)
- Back navigation: `[←]` button returns to previous level
- Pack list populated from `config/DragonTweaks/` subdirectories at panel open time
- Category list populated from subdirectories within the selected pack
- Building list populated from JSON filenames within the selected category, level suffix stripped and grouped by base name

### Browse Mode — Layout

```
[← Back]  Browsing: Original > Fundamentals
─────────────────────────────────────────────
  Builder's Hut
  Cook's Hut
  Hospital
  Lumberjack's Hut
  Miner's Hut
  Residence
  Tavern
  Town Hall
─────────────────────────────────────────────
              [< Prev]  Page 1/2  [Next >]
```

- Each entry is a clickable line (full width, 10px line height)
- Highlight on hover
- Selected building transitions directly to Goal Input mode result view

### Browse Mode — Level Selection

- When a building is selected from the browse list, if multiple levels exist (e.g. builder1–builder5), a level selector is shown before the materials list:
  `Level: [1] [2] [3] [4] [5]`
- Selected level highlighted; defaults to current in-colony level + 1 if building exists, else level 1
- Changing level reloads materials list for that level

---

### Input Area Layout (Top of Panel)

```
[Plan a path to: ________________] [Browse]
```

- Text field width: ~260px
- Browse button width: ~55px
- Single row, consistent with existing panel header height

---

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
| Materials list in snapshot mode | Goal input and browse mode only |

### Data Sources

- Colony state: `ColonyDiagnosticReport` cache (TTL 30s, configurable)
- Cache invalidation: `BuildingConstructionModEvent`, `CitizenJobChangedModEvent`, `CitizenAddedModEvent`, `CitizenDiedModEvent`
- Materials data: `BlueprintMaterialsLoader.getMaterials(structurePack, blueprintPath, level)` — cold file read per request
- Warehouse stock: `getMatchingItemStacksInWarehouse(Predicate<ItemStack>)`
- Player inventory: `player.getInventory()`
- Style/path per building: `ISchematicProvider.getStructurePack()` + `getBlueprintPath()`

---

## DESIGN RECORD

### Browse Mode Rationale

Players who do not know a building's exact name have no entry point into Goal Input mode beyond guessing. Browse mode provides a structured alternative: drill down by pack, category, and building name. Both modes coexist — the text field remains active during browse. Players who know the name type it; players who don't, browse.

### Level Selection Rationale

A building's materials list changes significantly between levels. Defaulting to current level + 1 reflects the most common use case: the player wants to know what they need for the next upgrade. Explicit level selection covers the planning-ahead case.

### Materials Pagination Rationale

Large buildings at higher levels can have 50+ distinct material types. Pagination at the existing ITEMS_PER_PAGE rate keeps the list navigable without requiring a scroll mechanism inconsistent with the rest of the panel UI.

### Time Estimate Rationale

Steps remaining only — no day range estimate. Builder speed varies with materials, pathfinding, and interruptions. A wrong estimate damages player trust more than no estimate.

### Pagination Rationale

Paginate over scroll throughout. Minecraft panel UIs are fixed-size. Pagination gives the player explicit control and avoids visual noise in a small panel frame.

---

*v0.4 — Adds Browse mode, level selector, materials pagination, BlueprintMaterialsLoader integration.*
*Supersedes v0.3 (2026-05-07).*
