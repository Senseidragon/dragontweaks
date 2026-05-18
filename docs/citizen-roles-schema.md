# DragonTweaks — Citizen Role Schema
*Version: 1.0 — 2026-05-15*

This document defines every field used in `citizen-roles.json`. Use it as a reference when creating custom roles. All fields marked **required** must be present. All fields marked **optional** may be omitted; the mod will apply a safe default.

---

## Top-Level Structure

`citizen-roles.json` contains a single JSON object with one key:

```json
{
  "roles": [ ...role objects... ]
}
```

---

## Role Object Fields

---

### `role_id`
**Required**
**Type:** string

Machine-readable identifier. Must be lowercase, no spaces. Used internally by the mod to reference this role.

**Examples:** `"advisor"`, `"scout"`, `"ranch_hand"`, `"planner"`

---

### `display_name`
**Required**
**Type:** string

Human-readable name shown in the role assignment UI.

**Examples:** `"Advisor"`, `"Scout"`, `"Ranch Hand"`, `"Planner"`

---

### `tier`
**Required**
**Type:** integer

Controls which model candidate pool this role draws from and how the mod treats its LLM responses.

| Value | Meaning |
|---|---|
| `1` | Flavor — idle chatter, immersion, no colony data, cheap models only |
| `2` | Functional — colony data access, reasoning expected, structured output |
| `3` | Tactical — adversarial planning, threat assessment, highest reasoning demand |

---

### `scraper_tier`
**Required**
**Type:** string

Maps this role to a section in `model_config.json` produced by `findmodels.py`. The scraper ranks models differently per section based on cost weighting.

**Allowed values:** `"flavor"`, `"advisory"`, `"specialized"`, `"tactical"`

---

### `reasoning_required`
**Required**
**Type:** boolean

Whether the model selected for this role must be a reasoning-capable model. Reasoning models perform internal chain-of-thought before producing output and are better suited for multi-step inference, dependency chains, and threat assessment.

- `true` — only reasoning models are eligible for this role
- `false` — reasoning models are excluded; standard instruction-following models used

**Note:** Reasoning models consume additional tokens on internal chain-of-thought. Roles marked `true` will have higher effective token costs than metadata pricing alone suggests.

---

### `description`
**Required**
**Type:** string

Human-readable explanation of what this role does. Used in the role assignment UI tooltip and as documentation for custom role authors.

---

### `output_token_cap`
**Optional**
**Type:** integer
**Default:** `200`

Maximum tokens the LLM is allowed to produce in a single response for this role. Flavor NPCs use low caps (100–200) to keep responses brief and immersive. Advisory and Tactical roles may need higher caps to accommodate structured reasoning output.

**Suggested ranges:**
- Tier 1 Flavor: `100`–`200`
- Tier 2 Advisory/Specialized: `300`–`600`
- Tier 3 Tactical: `500`–`1000`

---

### `capabilities`
**Optional**
**Type:** object

Declares which environmental scanning and detection capabilities are active for this role. Any capability not listed defaults to `false` or its documented default value. Custom roles may opt into any capability listed here.

---

#### `capabilities.terrain_scan`
**Type:** boolean
**Default:** `false`

Whether this role performs a terrain scan of the surrounding area. Terrain scan samples biome type, surface block categories, terrain relief, and structure proximity.

---

#### `capabilities.underground_scan`
**Type:** boolean
**Default:** `false`

Whether this role samples blocks below the surface in a loose grid pattern. Used to detect cave voids and low-light conditions that indicate hostile mob spawn zones.

---

#### `capabilities.underground_scan_depth`
**Type:** integer
**Default:** `10`
**Requires:** `underground_scan: true`

How many blocks below the surface the underground scan extends. Recommended range: 8–12.

---

#### `capabilities.sound_detection`
**Type:** boolean
**Default:** `false`

Whether this role can report on mob presence inferred from sound signatures. Detection is limited to vanilla Minecraft and MineColonies mobs with known, distinctive audio signatures. Mod-added mobs are excluded.

Detection confidence varies by mob:
- High confidence: Zombies, Skeletons, Spiders (constant audio)
- Low confidence: Witches (proximity-triggered audio)
- Very low / effectively none: Endermen (silent unless aggroed; no player present at scan time means no aggro)

Output uses atmospheric, character-appropriate language. Coordinates, light level values, and direct mob names are avoided in favor of categorical or instinct-based phrasing (e.g. "undead sounds," "something chittering," "I have an uneasy feeling").

Occasional misidentification (5–10% of reports) is intentional and adds immersion.

---

#### `capabilities.explosion_detection`
**Type:** boolean
**Default:** `false`

Whether this role reports on nearby explosion events, including cardinal direction. Intended to alert the player to creeper detonations near citizens or guards. Output suggests follow-up investigation without specifying cause.

---

#### `capabilities.colony_data_access`
**Type:** boolean
**Default:** `false`

Whether this role receives full colony diagnostic data (happiness breakdown, building inventory, citizen records, research state) injected into its LLM prompt context. Required for Advisor and Planner roles. Not appropriate for Tier 1 Flavor roles.

---

#### `capabilities.threat_assessment`
**Type:** boolean
**Default:** `false`

Whether this role reasons about hostile threats — biome spawn table implications, enemy composition, guard readiness, and tactical recommendations. Requires `reasoning_required: true`.

---

#### `capabilities.passive_mob_reporting`
**Type:** boolean
**Default:** `false`

Whether this role reports on passive mob presence (cows, pigs, sheep, chickens, etc.). Defaults to `false` for all current roles — passive mob locations are transient and not tactically useful in Java Edition. Exposed as a capability for custom roles that may have a use for it (e.g. a Ranch Hand locating animals to manage).

---

### `persona_notes`
**Optional**
**Type:** string

Free-text notes for LLM prompt engineering. Describes the character voice, tone, and any hard constraints on output style for this role. Used when drafting system prompts.

**Examples:**
- `"Speaks in medieval idiom. Never references modern technology. Immersion is the primary concern."`
- `"Analytical and direct. Prioritizes actionable recommendations over flavor. Avoids unnecessary embellishment."`

---

## Minimal Custom Role Example

```json
{
  "role_id": "diviner",
  "display_name": "Diviner",
  "tier": 2,
  "scraper_tier": "specialized",
  "reasoning_required": false,
  "description": "A diviner who uses a dowsing rod to sense underground water sources and ore deposits.",
  "output_token_cap": 200,
  "capabilities": {
    "underground_scan": true,
    "underground_scan_depth": 12
  },
  "persona_notes": "Speaks in mystical, vague terms. References tremors, vibrations, and sensations rather than game mechanics."
}
```