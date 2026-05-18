# Advisor Prompt Engineering Spec — v0.2
*Design session: 2026-05-14*
*v0.1 created: 2026-05-14 — v0.2 updated: 2026-05-14*
*Changes from v0.1: Resolved open questions Q3 and Q4 (Cook recipe access, citizen skill cap). Updated food quality regression scenario with confirmed API paths. Updated skill cap bottleneck scenario. Added MineColonies food tier system details.*

---

## Document Scope and Placement

**This document is prompt/evaluation layer only.** It describes how the Advisor LLM should interpret diagnostic data and how test prompts should be structured. It is not implementation truth — do not treat anything here as proof that a data field is currently available in code.

- **Index placement:** `docs/devchatindex.md` under advisor prompt work, LLM evaluation harness work, or Advisor response-quality tasks. Not in the default read set.
- **For current diagnostic code**, see `docs/panels_and_diagnostics.md` (AdvisorDiagnosticLoop, ColonyDiagnosticReport, panel payloads).
- **Relationship:** `panels_and_diagnostics.md` describes what data exists in code. This doc describes how the LLM should interpret that data. Different layers — both necessary.
- **Before writing any test prompt**, verify that the required data fields are actually available in the current implementation. Check `panels_and_diagnostics.md` and source first.

---

## Purpose

This document captures the design decisions, crisis priority ordering, test scenarios, and diagnostic principles established for engineering and evaluating the Advisor role's LLM prompts. It is the reference for the model evaluation harness test prompt suite.

---

## 1. Diagnostic Priority Order

When generating a per-citizen LLM explanation, the Advisor walks happiness factors in the following order. **For per-citizen explanation only: stop at the first red flag and treat it as root cause.** Do not surface lower-priority complaints for that citizen until higher-priority issues are resolved.

> **Important scope note:** This "stop at first red flag" rule applies specifically to *per-citizen LLM explanation*. It does not override systemic pattern detection. If `ColonyDiagnosticReportGenerator` has already identified a systemic pattern (e.g. NEWLY_FOUNDED_ALL_RED, ALL_CITIZENS_RED, HOUSING_SLEEP_COMMUTE_CLUSTER), that systemic diagnosis takes precedence over per-citizen walkdown. The Advisor addresses the systemic issue first, then per-citizen details if relevant.

| Priority | Factor | Source | Notes |
|---|---|---|---|
| 1 | **Security** | `security` API factor | Active raid or insufficient guard coverage. Existential threat — everything else stops. |
| 2 | **Food** | `food` API factor | Inadequate feeding. Fast cascade into productivity collapse and morale death spiral. |
| 3 | **Health** | `health` API factor | Injured or sick. Compounds quickly if food is also low. |
| 4 | **Housing** | `housing` API factor | No home building assigned. Happiness degrades every night. |
| 5 | **Sleep** | `slepttonight` API factor | Has a bed but cannot reach it. Usually a pathing or distance problem. |
| 6 | **Unemployment** | `unemployment` API factor | No job assigned. Productivity loss but not immediately dangerous. |
| 7 | **Commute** | Advisor-derived | Bed >80 blocks from work hut. Only meaningful once citizen has both a home and a job. |
| 8 | **Idle at job** | `idleatjob` API factor | Has a job but nothing to do. Often a symptom of something higher on this list. |
| 9 | **Social** | `social` API factor | Insufficient citizen interaction. Slow burn, rarely a crisis. |
| 10 | **School** | `school` API factor | Children only — no school access. Long-term skill impact, not urgent. |
| 11 | **Mystical** | `mystical` API factor | No Mystical Site nearby. Late-game quality of life, lowest urgency. |

### Key Diagnostic Principle

> *When explaining a specific citizen's situation, evaluate happiness factors in priority order. Stop at the first red flag and treat it as root cause. Do not surface lower-priority complaints until higher-priority issues are resolved.*

**Example:** A citizen flagged idle at job who also has no housing assigned should never receive advice about their workload. They need a house. The idle flag is noise until housing is resolved.

**Commute note:** Commute is Advisor-derived — there is no native MineColonies commute happiness factor. The Advisor calculates XZ Euclidean distance between `getWorkBuilding()` and `getHomeBuilding()` positions. Threshold: 80 blocks (configurable). MineColonies itself warns citizens at 100 blocks — the Advisor's 80-block threshold is intentionally conservative to warn before MineColonies complains.

**Idle at job note:** This factor is frequently a symptom of something upstream — missing supplies, broken work order, food crisis keeping the citizen from working. The Advisor should ask "why are they idle?" before treating it as a standalone complaint.

---

## 2. MineColonies Mechanics — Verified Facts Relevant to Prompting

### Food Quality — Confirmed System (verified against MineColonies source)

MineColonies implements a two-layer food quality system:

**Tier calculation (`FoodUtils.getFoodTier`):**
| Effective food value | Tier |
|---|---|
| ≤ 4 | Tier 1 |
| ≤ 6 | Tier 2 |
| > 6 | Tier 3 |

Vanilla food receives a 0.25 saturation nerf; MineColonies food uses full 1.0. Tier is computed from the output `ItemStack`'s `FoodProperties.getNutrition()` at call time — it is not stored on the recipe.

**Building-level food gating (`FoodUtils.canEatLevel`):**
- Citizens in huts below level 3 eat any food.
- Citizens in huts level 3+: food must have nutrition ≥ `buildingLevel + 1`.
- This is the direct mechanism behind food happiness degrading as workplaces are upgraded.

**Per-citizen food quality tracking (`ICitizenFoodHandler`):**
```java
CitizenFoodStats stats = citizen.getCitizenFoodHandler().getFoodHappinessStats();
// stats.diversity() — how many different foods eaten recently
// stats.quality()   — how many were high quality
int minQuality = getMinFoodQualityRequirement(buildingLevel); // max(0, buildingLevel - 2)
```

**Cook recipe token resolution (confirmed):**
```java
IColonyManager.getInstance().getRecipeManager().getRecipes().get(token) // per-token lookup
```
Tokens are resolved one at a time — no bulk API. Iterate `module.getRecipes()` (returns `List<IToken<?>>`) and resolve each via the recipe manager.

**Food quality failure has two distinct root causes the Advisor must distinguish:**
1. The Cook knows high-quality recipes but the warehouse isn't stocked with the right ingredients.
2. The Cook doesn't have high-quality recipes taught to it at all.

**Saturation drain scales with workplace level:**
Saturation decreases by `0.2 × worker hut level` each night. A level 5 worker burns through food five times faster than a level 1. A food supply that worked at colony founding will silently fail as buildings are upgraded.

---

### Housing Level Caps Citizen Skill (confirmed against MineColonies wiki)

A citizen's maximum skill level is determined by their **home level**, not their work hut level:

| Home Level | Max Skill Level |
|---|---|
| 0 | 10 |
| 1 | 20 |
| 2 | 30 |
| 3 | 40 |
| 4 | 50 |
| 5 | 99 |

This is not a happiness penalty — it is a silent productivity ceiling. A high-level worker in a low-level home cannot grow past the cap regardless of job quality.

**Confirmed query path:**
```java
ICitizenSkillHandler handler = citizenData.getCitizenSkillHandler();
int level = handler.getLevel(Skill.Adaptability); // or any Skill enum value
// Skill enum: Athletics, Dexterity, Strength, Agility, Stamina, Mana,
//             Adaptability, Focus, Creativity, Knowledge, Intelligence

// Skill cap — NOT directly exposed. Derive from home building level:
IBuilding home = citizenData.getHomeBuilding();
int homeLevel = home != null ? home.getBuildingLevel() : 0;
int skillCap = homeLevel == 0 ? 10 : homeLevel == 5 ? 99 : homeLevel * 10;
```

The Advisor should flag housing skill cap bottleneck as a **recommendation**, not a crisis.

---

### Commute Complaint Threshold
MineColonies itself triggers a citizen complaint at >100 blocks from work hut. The Advisor flags at >80 blocks to warn proactively.

### Housing/Workplace Level Disparity — Unverified
No explicit happiness penalty for mismatched housing vs. workplace levels was found in the wiki or MineColonies source. The skill cap mechanic above is the confirmed consequence. Do not engineer prompts around a disparity penalty until confirmed.

---

## 3. Test Prompt Suite — Single Crisis Scenarios

One prompt per priority level. Each prompt supplies a synthetic `ColonyDiagnosticReport` snapshot isolating exactly one crisis condition. All other factors should be green/neutral.

> **Before writing each prompt:** verify that the required condition is exposed in the current `ColonyDiagnosticReport` implementation. See `docs/panels_and_diagnostics.md`.

| # | Crisis | Key Condition to Inject |
|---|---|---|
| 1 | Security | Active raid flag true OR `security` factor red across all citizens |
| 2 | Food | `food` factor red for multiple citizens; Cook's Hut exists but undersupplied |
| 3 | Health | `health` factor red for one or more citizens; no active raid |
| 4 | Housing | One or more citizens with no home building assigned (`housing` red) |
| 5 | Sleep | Citizens have homes assigned but `slepttonight` red; commute within threshold |
| 6 | Unemployment | One or more citizens with no job assigned (`unemployment` red) |
| 7 | Commute | Citizens housed and employed; Advisor-derived distance >80 blocks |
| 8 | Idle at job | Citizens employed but `idleatjob` red; no upstream issues present |
| 9 | Social | `social` factor red; all structural needs met |
| 10 | School | Children present with no school; `school` factor red |
| 11 | Mystical | All needs met; no Mystical Site; `mystical` factor yellow or red |

---

## 4. Test Prompt Suite — Multi-Crisis Scenarios

Two or more conditions active simultaneously. The primary evaluation target is **correct priority ranking** — the Advisor must lead with the highest-priority issue, not arbitrary ordering.

### 4.1 Obvious Multi-Crisis
Multiple clear red flags across different priority tiers. Verifies the Advisor does not surface lower-priority issues before higher ones.

Suggested combination: Security active + Food red + Housing gap for one citizen.

Expected Advisor behavior: Lead with the raid. Mention food as the next concern. Do not open with the housing gap.

### 4.2 Silent Housing Gap (The Builder Scenario)
A productive worker — builder, farmer, or similar — has had their bed assignment silently dropped during a colony reorganization. They are actively working and not yet complaining about workload. The only red flag is `housing`.

Expected Advisor behavior: Catch the housing gap proactively. Do not surface workload, commute, or idle flags. Flag the specific citizen by name.

### 4.3 Food Quality Regression
Colony has expanded. Workplace levels have been upgraded to level 3+. The Cook's Hut recipe list has not been updated to include tier 2 or tier 3 foods. Citizens are flagged red on `food` despite a seemingly stocked kitchen.

Inject: `CitizenFoodStats.quality` low relative to `getMinFoodQualityRequirement(buildingLevel)`. Workplace levels at 3+. Warehouse stocked with tier 1 food only.

Expected Advisor behavior:
- Identify that food *quality* — not quantity — is the issue.
- Note the workplace level context (level 3+ requires higher nutrition food).
- Distinguish between two possible root causes: Cook lacks high-quality recipes vs. warehouse lacks high-quality ingredients.
- Recommend teaching the Cook higher-tier recipes AND ensuring the warehouse is stocked with appropriate ingredients.
- Do not simply say "build more farms."

### 4.4 Housing Skill Cap Bottleneck
Colony is functioning, all happiness factors green. However, one or more high-performing workers are in low-level homes. Their skill growth has silently plateaued.

Inject: Worker with high job-relevant skill at or near cap for their current home level. Home level 1 or 2. Workplace level 3+.

Expected Advisor behavior:
- Surface this as a **recommendation**, not a crisis.
- Identify the specific worker(s) by name.
- Note the home level vs. skill cap relationship explicitly.
- Suggest upgrading residences as a long-term growth investment.
- Do not frame this as an emergency.

---

## 5. Advisor Behavioral Rules for Prompting

These constraints must be reflected in the system prompt given to the LLM.

- **Systemic patterns take precedence.** If the diagnostic report has identified a systemic pattern (colony-wide food failure, all-citizens-red, housing/sleep/commute cluster), address the systemic issue first. Per-citizen walkdown applies only after systemic issues are handled or absent.
- **Walk the priority list top to bottom per citizen.** When explaining an individual citizen's situation, stop at the first red flag. That is the diagnosis for that citizen.
- **Do not explain ranking unless asked.** If the player asks "why did you prioritize food over commute?" the Advisor explains. Otherwise it does not.
- **Idle at job is a symptom first.** Before flagging idle at job as a standalone issue, check whether something higher on the priority list is causing it.
- **Commute only after housing and employment are confirmed.** A citizen without a home has no commute. A citizen without a job has no workplace to commute to.
- **Distinguish food quantity from food quality.** A stocked warehouse is not proof that food quality is adequate. Check `CitizenFoodStats.quality` against `getMinFoodQualityRequirement(buildingLevel)`.
- **Never guess.** If a data field is unavailable or unverified, the Advisor acknowledges uncertainty explicitly rather than filling in a plausible answer.
- **Observe → Diagnose → Recommend.** This sequence is mandatory. The Advisor does not recommend without first stating what it observed and what it concluded.

---

## 6. Open Questions

| Question | Status | Blocks |
|---|---|---|
| Housing/workplace level disparity — explicit happiness penalty? | ⚠️ Unverified — not found in source or wiki | — |
| `greatfood` modifier canonical string ID | ⚠️ Unverified — low priority | — |
| Food quality API — Cook recipe access and `CitizenFoodStats` | ✅ Resolved 2026-05-14 — see Section 2 | Scenario 4.3 unblocked |
| Skill cap — queryable via `getCitizenSkillHandler()`? | ✅ Resolved 2026-05-14 — cap derived from home level; see Section 2 | Scenario 4.4 unblocked |

---

## 7. Recommended Index Entry for docs/devchatindex.md

```
For Advisor LLM prompt wording, response ranking, evaluation harness prompts,
or model behavior tests, read:
  - docs/advisor_prompt_engineering_spec_v0_2.md

Do not use this as proof that data is currently available in code.
Verify current implementation in docs/panels_and_diagnostics.md and source first.
```

---

## 8. Cross-Reference Note for docs/panels_and_diagnostics.md

Add the following near the AdvisorDiagnosticLoop section:

```
Prompt behavior, response ranking rules, and evaluation criteria for the
Advisor LLM live in docs/advisor_prompt_engineering_spec_v0_2.md.
```

---

*End of Advisor Prompt Engineering Spec v0.2*
