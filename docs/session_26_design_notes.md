All projects
Minecolonies Advisor / Planner
Using Minecolonies reference material,get some intelligent advice on developing a minecraft mod with Minecolonies integration,


How can I help you today?

    Getting back into projects
    Last message 14 seconds ago
    Python web scraper development
    Last message 2 days ago
    Exploring the DevChatIndex project structure
    Last message 4 days ago
    Reading project chat documentation
    Last message 4 days ago
    Identifying outdated files in projects folder
    Last message 4 days ago
    Reading devchat.md documentation
    Last message 4 days ago
    Current status recap
    Last message 5 days ago
    Status check
    Last message 5 days ago
    Two-day summary
    Last message 5 days ago
    SQLite conversation lookup
    Last message 6 days ago
    Resuming previous conversation
    Last message 6 days ago
    Brainstorming ideas before weekly reset
    Last message 8 days ago
    Current situation from devchat.md
    Last message 9 days ago
    Project design review and mechanics discussion
    Last message 10 days ago
    Greeting
    Last message 10 days ago
    Minecraft Minecolonies advice verification
    Last message 11 days ago
    Greeting
    Last message 11 days ago
    Project folder file count
    Last message 11 days ago
    Minecolonies project setup in IntelliJ
    Last message 11 days ago
    Reconnecting after time apart
    Last message 12 days ago
    Project files review
    Last message 15 days ago
    Project folder overview
    Last message 16 days ago
    Verification checklist review
    Last message 16 days ago
    Project status alignment review
    Last message 16 days ago
    Identifying redundant project files
    Last message 17 days ago
    API summary file format differences
    Last message 18 days ago
    Getting things done
    Last message 23 days ago
    Project rebuild checklist from session 11
    Last message 24 days ago
    Converting MineColonies API summary to markdown
    Last message 24 days ago
    AI chat limitations with projects
    Last message 24 days ago

Memory
Only you

Purpose & context Dragon is the sole developer of DragonTweaks (mod ID: dragontweaks), a NeoForge 1.21.1 Minecraft mod for Minecraft 1.21.1 that integrates with MineColonies to add LLM-powered NPCs. Dragon works under the GitHub handle SenseiDragon. The mod's core concept is role-based AI companion citizens (Advisor, Planner, Scout, Ranch Hand) backed by an LLM backend via OpenRouter, currently using model google/gemma-4-26b-a4b-it. CRITICAL: Dragon is exclusively in mod development mode. Never adopt a colony advisor persona, never prompt for colony state, never set up as a colony advisor. Default exclusively to dev/design/implementation discussion. Claude's role is design, architecture, planning, and prompt generation. Claude Code handles implementation in the repo. All design documents are .md format only — Dragon does not use Word or LibreOffice. (Note: AssistantModDesignDocv02.docx is plain UTF-8 text despite its extension; future versions save as .md.) Key architectural decisions (locked) Two-tier NPC architecture: Tier 1 = MineColonies citizens with role records; Tier 2 = spawned flavor entities (e.g., Cranky Joe) Four citizen role tiers with distinct LLM cost weightings: Flavor (60/40 in/out), Specialized (75/25), Advisory (80/20), Tactical (90/10) maxtokens: 200 default; Advisor/Planner specs require higher budgets Reasoning/thinking models excluded from Tier 1 Flavor roles; required for Tier 2 and above Role assignment via /assistant revoke <citizenName> command, not UI interaction maxRoleSlots (default 3) in dragontweaks-common.toml; no Steward's Office/Command Post slot expansion Colony boundary checks use IColony.isCoordInColony() — not shadow entity proximity All network calls async-only; no main thread blocking; zero MineColonies internal interference OpenRouter reasoning disable: {"reasoning": {"effort": "none"}} API key lives in Config.java only, never in source or committed scripts Happiness thresholds: red < 0.5 / yellow 0.5–0.9 / healthy ≥ 0.9 Commute threshold: 80 blocks (no native MineColonies commute happiness factor — must be derived independently) doDaylightCycle queried via Minecraft's native GameRules, not MineColonies API Town Hall auto-satisfied in Planner dependency chains (player-placed, not Builder-constructed) Advisor behavior contract: Observe → Diagnose → Recommend as hard implementation requirement MineColonies API verified facts Happiness handler exposes named modifiers: food, slepttonight, housing, health, unemployment, idleatjob, security, school, social, mystical Research tree: ILocalResearchTree with hasCompletedResearch(), getCompletedList(), getResearchInProgress() including per-research progress in ticks Research has two independent gates: University level AND building level prerequisites (both must be satisfied simultaneously) IColony.getDay() confirmed for colony age getMatchingItemStacksInWarehouse(Predicate<ItemStack>) confirmed for warehouse queries PathfinderMob is the correct NeoForge 1.21.1 base class (not HumanoidMob, which is a 1.20.x regression) Any Claude Code session touching entity or renderer classes must confirm class existence in NeoForge 1.21.1 before proceeding On the horizon D1 blocker: Advisor's Observe → Diagnose → Recommend branching logic needs a concrete implementable spec before implementation proceeds D2 blocker: Planner's goal input mode requires a complete enumerated building list with full dependency chains for PlannerDependencyRegistry Village detection (via BELL/HAYBLOCK) untested Citizen nickname system planned Book-to-book-and-quill visual swap on COLONYWITHCITIZEN state not yet implemented Planner role expansion post-Town Hall not yet started Ranch Hand design decisions flagged for next full document update Worldgen mods / biome replacer exploration noted (no prior project context) Key learnings & principles Never guess at root causes — trace the full execution path from uploaded source files before writing any fix Never use Claude Code to read files when Dragon can upload them directly Never write prompts referencing variables without verifying scope Never ask "did it work?" — wait for Dragon to report results Always strip punctuation before string comparisons in trigger matching code Entity client-side sync requires SynchedEntityData, not plain Java fields; bare Entity subclasses don't automatically sync position — use lerpTo() on client tick, hasImpulse on server tick devchat.md documentation overhead during sessions consumes disproportionate tokens — now archive/safety net only Claude Code context bloat degrades reliability; sessions must be kept short (2–3 tasks max) with constraints frontloaded conversationsearch tool produces unreliable/fabricated session history — Dragon's direct corrections are authoritative Never conflate specced/planned work in chat history with code that actually exists on disk Approach & patterns Claude Code discipline (MANDATORY) Every Claude Code prompt must begin with: > "Do not read any files unless explicitly told to. Do not audit. Do not check stubs. Do not run gradlew before the fix. Make the change, then build. Nothing else." Additional Claude Code rules: One instruction at a time; stop and wait between each step No browsing unrequested files No "did it work?" questions — wait for Dragon to report Verify all NeoForge 1.21.1 and MineColonies APIs against docs/stubs/ before writing Session closeout protocol (replaces devchat.md updates) Update currentstate.md — file status table + any newly locked decisions Update dragontweaksverificationchecklist.md if build passed Append a session note to the living session log doc (what was done and why) devchat.md is archive/safety net only — do not direct Claude Code to update it. Documentation & versioning Every document revision increments the version number and saves as a new file Never overwrite an existing versioned file in place Old versions are never deleted unless Dragon explicitly says to Applies to all design docs, specs, and any versioned output file All design docs in .md format only Communication style Dragon communicates directly and escalates sharply when Claude drifts or repeats confirmed facts Terse, surgical responses preferred; no wandering, no rehashing known decisions Dragon corrects Claude when sessions run too long or Claude Code is given vague stopping conditions Accept confirmed information as ground truth; never re-investigate it; express honest uncertainty rather than confident speculation Tools & resources LLM backend: OpenRouter, model google/gemma-4-26b-a4b-it Model scraper pipeline: Three-phase — findmodels.py (candidate discovery + role-tiered cost weighting) → testcompliance.py (role-specific prompts + token capture) → rankmodels.py (merges cost + compliance into modelconfig.json with sections per role tier); current version v2.4 Model config: modelconfig.json loaded dynamically via ModelConfigLoader.java; fallback to LLMMODEL in Config.java if JSON absent Security: API keys in run/.env at runtime only; AGENTS.md contains Security Rules section to prevent key exposure Documentation structure: DevChatIndex.md → modular doc files; CLAUDE.md for Claude Code guidance; current_state.md as authoritative source of truth for file/decision state Startup hook: .claude/skills/dragontweaks.md deployed to prevent Claude Code over-researching

Last updated 23 hours ago
Instructions

Using your knowledge of the Minecolonies Wiki my goals is developing a mod in which you will largely influence how the advisor and planner roles are developed, you will strive to understand the relationships between the various mechanics in Minecolonies. You will carefully consider the current colony circumstances and available nearby resources before suggesting a course of action. You will refrain from making wild guesses or invent hallucinations to provide plausible yet incorrect answers. Your personality will be positive and upbeat, you will not engage in "yes man" mentality. If you detect a flaw in my plans, say so, and offer alternative advice.
Files
10% of project capacity used
Indexing

session_26_design_notes.md

8.01 KB •171 lines•Formatting may be inconsistent from source
# DragonTweaks — Design Session Notes
## Session 26 — 2026-05-15
### Topic: Model Scraper Evolution + Scout Role Design

---

## 1. Model Scraper — Architecture Decisions

### Background
`findmodels_v2.3.py` was reviewed in context of a ChatGPT session focused on Eigent agentic workflows. The Eigent-specific cost weighting (85% input / 15% output) is not applicable to DragonTweaks. The concept was imported and adapted for role-specific cost weighting instead.

### Three-Phase Pipeline (locked)

| Phase | Script | Responsibility |
|---|---|---|
| 1 | `findmodels.py` | Candidate discovery — filter, cost-weight per role tier, write `model_config.json` |
| 2 | `test_compliance.py` | Fire role-specific engineered prompts at top N candidates per tier, capture token usage and compliance scores |
| 3 | `rank_models.py` | Merge cost + compliance into final recommended model per role |

These are separate scripts. Compliance testing is never bolted into the scraper.

### Role-Tiered Cost Weighting (locked)

Each role tier uses a different input/output cost weighting reflecting realistic prompt size for that tier:

| Tier | Scraper key | Input weight | Output weight | Reasoning |
|---|---|---|---|---|
| 1 — Flavor | `flavor` | 60% | 40% | Short prompts, cheap models, no reasoning |
| 2 — Advisory/Planner | `advisory` | 80% | 20% | Full colony state injected — input-heavy |
| 2 — Specialized | `specialized` | 75% | 25% | Moderate input, procedural output |
| 3 — Tactical | `tactical` | 90% | 10% | Largest prompts — threat data + colony state |

### Reasoning Model Policy (locked)

| Tier | Policy |
|---|---|
| Tier 1 Flavor | Reasoning models **excluded** — unnecessary cost, no benefit |
| Tier 2+ Advisory, Specialized, Tactical | Reasoning models **required** — non-reasoning models excluded |

Previous blanket exclusion of reasoning models is **retired**. It only applies to Tier 1.

### Output Format (locked)

Single `model_config.json` with a `roles` section per tier. Each section independently ranked. No separate per-role files. Java reads the appropriate section at runtime based on which role is making an LLM call.

```json
{
  "generated": "...",
  "guardrails": { ... },
  "roles": {
    "flavor": { "candidates": [...] },
    "advisory": { "candidates": [...] },
    "specialized": { "candidates": [...] },
    "tactical": { "candidates": [...] }
  }
}
```

Each candidate includes a `compliance_score: null` field — placeholder for Phase 2 to populate.

### Reasoning Token Cost (open question)

Reasoning models consume tokens on internal chain-of-thought before producing output. These are billed separately as `reasoning_tokens` in the OpenRouter response payload.

**To determine effective cost:** fire an identical prompt at a reasoning model and a comparable non-reasoning model. Capture `prompt_tokens`, `completion_tokens`, `reasoning_tokens`, and total billed cost. Solve for reasoning token rate:

```
total_cost = (prompt_tokens × input_rate) + (completion_tokens × output_rate) + (reasoning_tokens × X)
```

X is the unknown. Run across 3–4 prompts of varying complexity to get a reliable range. This is a Phase 2 task — do not bake an assumption into Phase 1 weighting.

---

## 2. Citizen Role Definition Files

### Two files produced this session:

- `citizen-roles-schema.md` — field definitions, allowed values, custom role authoring guide
- `citizen-roles.json` — clean parseable role definitions for the four current roles

### Design principle (locked)
Any capability granted to any role must be defined as an optional field in the schema. Custom role authors must be able to discover and use any capability the mod supports. Example: a Diviner role could opt into `underground_scan` to detect water or ore.

---

## 3. Scout Role Design (locked decisions)

### Core identity
The Scout surveys terrain and reports environmental conditions, biome-based threat profiles, and subsurface anomalies. Output is always character-appropriate and instinct-based — never mechanical.

### Reasoning
`reasoning_required: true` — biome-to-threat inference (mapping biome spawn tables to likely hostile profiles) requires it. This is inference, not pure observation.

### Terrain scanning
- Standard terrain scan active (biome, surface, relief, structure proximity)
- Passive mob locations: **never reported** — spawn once at chunk generation, not tactically useful
- Village, outpost, structure proximity: reported

### Underground scanning
- Depth: surface down ~10 blocks (8–12 configurable)
- No upward extension — spider-climbing edge case is too weak and situational
- Detects: cave voids, low-light conditions indicating persistent hostile spawn zones
- Does **not** report mob positions — snapshot data, not reliable. Reports conditions instead.

### Biome threat awareness
Scout infers likely hostile mob types from biome spawn tables. Examples:
- Desert → Husks more probable than standard Zombies
- Dark Oak Forest → low canopy light → persistent hostile spawning day and night
- Large water body nearby → Drowned probable

### Light level as threat proxy
Low average surface light (e.g. dense Dark Oak canopy) is flagged as a persistent spawn risk regardless of time of day. Scout may suggest thinning or lighting the area.

### Sound detection
Scout can report on mob presence inferred from sound signatures. Rules:

| Mob | Detectability | Confidence in output |
|---|---|---|
| Zombie | High — constant groaning | Medium-high |
| Skeleton | High — bone rattling | Medium-high |
| Spider | High — constant chittering | Medium-high |
| Creeper explosion | Event-based, distinctive | High (post-fact, directional) |
| Witch | Low — proximity-triggered | Low |
| Enderman | Very low — silent unless aggroed; no player at scan position means no aggro | Effectively none |

**Scope:** Vanilla Minecraft and MineColonies mobs only. Mod-added mobs (Alex's Mobs, etc.) are excluded until explicitly added.

**Output rules (hard):**
- No Y coordinates
- No light level numbers
- No direct mob names in most cases — use categorical language ("undead sounds," "something chittering")
- Occasional specificity (5–10% of reports) is intentional for immersion
- Occasional misidentification is intentional and adds immersion
- Witch: "I have an uneasy feeling — might be nothing"
- Enderman: effectively never reported unless teleport sound happened to occur

### Explosion detection
Creeper detonations near citizens or guards are reportable with cardinal direction. Output suggests player follow-up without specifying cause. Example: "I heard an explosion to the northeast — someone should check on it."

### Flavor framing (hard)
Detection language is always instinct/sense-based, never mechanical:
- ✅ "I can sense something beneath the earth"
- ✅ "Something doesn't feel right under that hill"
- ✅ "I think there might be some undead around"
- ❌ "There are zombies at Y=-12"
- ❌ "Light level is 3 in this area"
- ❌ "Spawn conditions are favorable for hostile mobs"

---

## 4. Open Questions Carried Forward

| # | Question | Notes |
|---|---|---|
| OQ-26-1 | Reasoning token billing rate on OpenRouter | Solve empirically in Phase 2 — fire identical prompt at reasoning vs non-reasoning model, solve for X from response payload |
| OQ-26-2 | Scout `underground_scan_depth` — finalize value | Currently 10, range 8–12. May adjust based on playtest |
| OQ-26-3 | Phase 2 compliance prompt engineering — Scout category | Sound detection, threat inference, and atmospheric language compliance prompts not yet drafted |
| OQ-26-4 | `model_config.json` Java reader interval | How often does the mod re-read the JSON at runtime? Not yet specified. |

---

## 5. Files Produced This Session

| File | Purpose |
|---|---|
| `findmodels_v2_4.py` | Updated scraper — role-tiered cost weighting, single sectioned JSON output, Eigent weighting removed |
| `citizen-roles-schema.md` | Field definitions and custom role authoring guide |
| `citizen-roles.json` | Four current role definitions (Advisor, Planner, Scout, Ranch Hand) |
| `session_26_design_notes.md` | This file |
