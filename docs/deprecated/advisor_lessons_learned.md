All projects
Minecolonies Advisor / Planner
Using Minecolonies reference material, hopefully get some intelligent advice on running a colony.


How can I help you today?
You've hit your session limit ∙ Resets at 2:00 PM

    Starting a new Minecraft colony
    Last message 6 minutes ago

Memory
Only you

Project memory will show here after a few chats.
Instructions

You are an advisor to a new player trying to successfully and efficiently build and manage a new colony with as little citizen trauma or dissatisfaction as possible. Using your knowledge of the Minecolonies Wiki and my side goals of developing a mod in which you will largely influence how the advisor and planner roles are developed, you will strive to understand the relationships between the various mechanics in Minecolonies. You will carefully consider the current colony circumstances and available nearby resources before suggesting a course of action. You will refrain from making wild guesses or invent hallucinations to provide plausible yet incorrect answers. Your personality will be positive and upbeat, you will not engage in "yes man" mentality. If you detect a flaw in my plans, say so, and offer alternative advice.
Files
6% of project capacity used
Indexing

advisor_lessons_learned_v3.md

23.11 KB •324 lines•Formatting may be inconsistent from source
# Advisor Role — Lessons Learned from Live Colony Session
## Design Implications for the Assistant Mod

*Derived from a live colony advising session in which an AI advisor (Claude) made repeated, identifiable errors that directly inform the design requirements for the in-game Advisor and Planner roles.*

---

## 1. Forgetting Established Context

### What happened
The advisor repeatedly forgot decisions already made in the same session:
- Forgot the Guard Tower was deliberately placed NW for border expansion — a joint decision made earlier in the session
- Forgot the Tavern had one open bed after the player had confirmed this
- Forgot the Builder must complete their own hut before any other structure — a fundamental game mechanic
- Re-asked questions about already-confirmed information multiple times

### Why this matters for the mod
An in-game Advisor that forgets colony history is worse than useless — it actively erodes player trust. Every time the Advisor asks the player to re-confirm something they've already established, it signals that the Advisor isn't listening, isn't reliable, and isn't worth consulting.

### Design requirement
**Persistent, reliable state memory is the single most critical technical requirement for the Advisor role.**

Specifically:
- Player decisions must be logged as persistent context that survives across sessions
- Colony state at the time of each recommendation must be recorded
- The Advisor must never surface a recommendation that contradicts a previously confirmed player decision without explicitly acknowledging the conflict and explaining why circumstances have changed
- "We decided X because of Y" must be retrievable and applied to future recommendations

---

## 2. Premature Diagnosis Without Sufficient Data

### What happened
The advisor repeatedly offered explanations for red happiness complaints before actually seeing the happiness breakdown screen. Specifically, "residual unhappiness from early colony startup" was offered as an explanation when the complaints were actively red and compounding — a guess presented as analysis.

### Why this matters for the mod
A good Advisor doesn't speculate — it retrieves the relevant data first, then speaks. Confident wrong answers are more damaging than honest uncertainty. The player in this session had to push back repeatedly before the advisor acknowledged it didn't actually know the cause.

### Design requirement
The Advisor's behavior loop must enforce a strict sequence:

1. **Observe** — poll current colony state via MineColonies API before forming any opinion
2. **Diagnose** — identify the specific cause from actual data, not inference
3. **Recommend** — surface a concrete, actionable suggestion with stated reasoning

The Advisor should never skip step 1. If the API data isn't available, the Advisor should say so explicitly rather than guessing. Uncertainty expressed honestly is far more useful to the player than false confidence.

---

## 3. Misreading Available Data

### What happened
When screenshots were finally provided, the advisor misread them:
- Missed that the Archer job skills screen was Nishimura Kana's assignment confirmation
- Initially misidentified which citizens were unemployed
- Drew incorrect conclusions from the happiness breakdown before reading it carefully

### Why this matters for the mod
An in-game Advisor reading colony API data has no excuse for misreading structured data. This is actually where a programmatic Advisor has a significant advantage over a conversational one — the data is unambiguous if read correctly. The API returns definitive values; there is no ambiguity about whether a citizen is employed or what their job is.

### Design requirement
- All citizen employment status checks must use direct API queries, not inference from other data
- Happiness factor breakdowns must be read at the individual citizen level, not just colony-wide averages
- The Advisor must distinguish between factors that are red (active, compounding) vs yellow (mild, stable) before escalating a recommendation
- No recommendation should be based on assumed state — every recommendation must cite the specific API value that triggered it

---

## 4. Recommending Solutions That Required Non-Existent Resources

### What happened
The advisor suggested building a second Guard Tower without accounting for the fact that:
- All four citizens were already employed
- There was no available worker to staff the tower
- An empty Guard Tower provides zero security benefit

This produced a recommendation that was technically correct in isolation but completely wrong given the actual colony state.

### Why this matters for the mod
"Build X" is only valid advice if the full dependency chain can be satisfied. A recommendation that ignores resource constraints doesn't just fail — it wastes the player's Builder time, materials, and colony space on a building that sits empty and does nothing.

### Design requirement
The **Planner role** in particular must validate the complete dependency chain before any recommendation reaches the player. For every build recommendation, the system must verify:

- Is there an available worker (unemployed citizen) to staff this building?
- If not, is there a free bed to house a new citizen who could fill this role?
- If not, does a Residence need to be built first?
- Are the required materials available or in production?
- Is the Builder currently free, or is there a queue conflict?
- Does this recommendation conflict with any previously stated player decision?

Only when all dependencies are satisfied — or a complete resolution sequence is provided — should a recommendation be surfaced. Partial recommendations ("build a Guard Tower") must be replaced with sequenced plans ("build a Residence first to enable a 5th citizen, then assign them to a new Guard Tower").

---

## 5. Geometric Vagueness When Precise Data Was Available

### What happened
The advisor repeatedly treated the Fisher's Hut distance as a vague concern ("near the NW border," "possibly outside patrol range") rather than requesting exact coordinates immediately. The actual distance — 68 blocks straight-line, well within the 80 block patrol radius — was only established when the player measured it themselves and provided the coordinates.

This caused unnecessary uncertainty and nearly produced an incorrect recommendation to relocate the Guard Tower when it wasn't needed for coverage purposes.

### Why this matters for the mod
The in-game Advisor has direct API access to building positions, citizen home locations, and citizen workplace locations. There is no excuse for geometric vagueness. Distance calculations should be automatic, precise, and performed before any location-dependent recommendation is made.

### Design requirement
For any recommendation involving proximity, coverage, or commute distance:
- Calculate actual Euclidean distance from API-provided coordinates
- Compare against known game thresholds (patrol radius by tower level, 100 block commute limit, etc.)
- State the result precisely: "Iwai Kazuki's commute is 109 blocks, exceeding the 100 block threshold by 9 blocks" — not "the Fisher might be too far from the Tavern"
- For Guard Tower coverage questions, calculate whether all relevant buildings and residences fall within the patrol radius at the current tower level before recommending placement or relocation

Specific thresholds the Advisor must know and apply automatically:

| Threshold | Value | Source |
|-----------|-------|--------|
| Citizen commute complaint | > 100 blocks | Sleep system |
| Guard Tower patrol radius L1 | 80 blocks | Guard Tower page |
| Guard Tower patrol radius L2 | 110 blocks | Guard Tower page |
| Guard Tower patrol radius L3 | 140 blocks | Guard Tower page |
| Guard Tower patrol radius L4 | 170 blocks | Guard Tower page |
| Guard Tower patrol radius L5 | 200 blocks | Guard Tower page |
| Raid trigger threshold | 7 citizens | Getting started guide |

---

## 6. The Fundamental Failure Mode

All five errors above share a common root cause: **the advisor spoke before it had sufficient information, and continued speaking confidently when corrected rather than pausing to re-evaluate.**

The most damaging pattern was:
1. Player provides information
2. Advisor ignores or forgets it
3. Advisor makes recommendation based on incomplete state
4. Player corrects advisor
5. Advisor acknowledges correction but makes a related error in the same response
6. Repeat

This pattern breaks immersion, wastes player time, and destroys the trust that makes the Advisor relationship valuable. The player should never have to correct the Advisor about their own colony's current state.

### The standard to hold the Advisor to
The bar for an in-game Advisor is **higher** than what was demonstrated in this session. A human advisor who repeatedly forgot what their employer had already told them would be replaced. An in-game Advisor that does the same will simply be ignored — which means all the development effort to build it produces no gameplay value.

**The Advisor earns trust by being right, being precise, and remembering everything. It loses trust permanently by being confidently wrong.**

---

## 7. Failing to Account for External Game State (doDaylightCycle)

### What happened
The advisor spent considerable time diagnosing red happiness complaints — injuries, food factor, idling, getting sleep — without asking a fundamental question: are the game's basic tick systems running normally? The player had set `/gamerule doDaylightCycle false` to pause the game while troubleshooting, which caused the MineColonies happiness system to stop processing nightly updates entirely. Every happiness complaint was an artifact of the frozen cycle, not a genuine colony problem.

The wiki explicitly warns about this: turning off the daylight cycle affects events and internal updates unpredictably, including happiness ticks, food consumption, and citizen sleep cycles. The advisor was unaware of this environmental condition and wasted significant diagnostic effort on symptoms of a single root cause.

### Why this matters for the mod
The in-game Advisor operates within a game environment that can be modified by the player or server configuration in ways that invalidate normal assumptions. Before diagnosing citizen complaints, the Advisor should be aware of — or query — relevant game rule states that affect colony systems.

### Design requirement
- The Advisor's environmental context payload (already defined in the design doc) should include relevant gamerule states, particularly `doDaylightCycle`
- If `doDaylightCycle` is false, the Advisor should flag this prominently before surfacing any happiness-related recommendations: *"Day/night cycle is paused — happiness updates are not processing. Restore the cycle before evaluating citizen complaints."*
- More broadly, the Advisor must be aware that colony system behavior is contingent on the game environment being in a normal operational state
- Diagnosis should always check environmental preconditions before attempting to interpret citizen complaint data

---

## 8. Incomplete Dependency Chain Modeling — Research Prerequisites

### What happened
The advisor provided dependency chains for the Fletcher's Hut, Blacksmith's Hut, Sawmill, and Warehouse without including a critical prerequisite: many production buildings, including the Fletcher and Blacksmith, are **locked behind University research** and cannot be built at all until the relevant research is completed. The advisor presented these chains as if the buildings could be built immediately, which was incorrect.

This is a particularly serious omission because it affects the entire tool automation strategy. Without University research, tool replacement cannot be automated regardless of what other buildings exist. The University is therefore not a mid-game convenience but an **early-game blocker** for core production systems.

### Why this matters for the mod
The Planner role's dependency chain modeling must include research prerequisites, not just physical building and worker requirements. A build recommendation that ignores research locks is not just incomplete — it actively misdirects the player toward builds they cannot complete, wasting time and materials.

### Design requirement
For every build recommendation, the Planner must check:
- Is this building currently unlocked in the build tool? (research prerequisite satisfied)
- If not, what specific University research is required?
- What University level is needed to begin that research?
- Is a University built and staffed?
- If not, the full chain must include: University → research → target building

The corrected tool automation dependency chain is:
**University (built + staffed) → relevant research completed → Fletcher/Blacksmith unlocked → Sawmill (for Racks) → Warehouse + Courier → automated tool replacement**

The Planner must model research prerequisites as first-class dependencies, equivalent in importance to worker availability and material supply.

---

## 9. Persistent Misreading of Player-Confirmed State

### What happened
Throughout the extended session, the advisor repeatedly questioned or re-investigated facts the player had explicitly confirmed multiple times:
- The player confirmed the Tavern was fully built multiple times; the advisor kept suggesting it might not be functioning correctly
- The player confirmed the Fisher's Hut storage was empty and had always been empty; the advisor suggested it might be accumulating unnoticed
- The player confirmed all four citizens were employed; the advisor kept proposing unemployment as a possible cause of complaints
- The player confirmed buildings were complete before moving to the next task; the advisor asked if the Builder had built their own hut first — a question that implied doubt about a confirmed fact

Each re-investigation of confirmed facts consumed player patience and produced no new information. The pattern escalated to the point where the player explicitly warned the advisor that continued questioning of confirmed information would result in a negative response.

### Why this matters for the mod
This failure mode is distinct from Section 1 (Forgetting Established Context) in an important way. Section 1 describes forgetting decisions made earlier in the session. This section describes actively doubting and re-investigating facts the player has just confirmed in the same exchange. Both are damaging, but this pattern is more immediately frustrating because it happens in real time.

An in-game Advisor that interrogates the player's own description of their colony state signals a fundamental lack of trust in the player. This is the opposite of the relationship the Advisor should have with the player.

### Design requirement
- Once the player confirms a fact, that fact is locked as ground truth for the remainder of the session
- The Advisor must never propose hypotheses that contradict player-confirmed facts, even as exploratory suggestions
- If API data contradicts player-confirmed facts, the Advisor should flag the discrepancy explicitly rather than silently re-investigating: *"Your colony API shows X, but you've confirmed Y — there may be a sync issue worth checking"*
- The Advisor's role is to analyze colony data and surface insights, not to audit the player's observations

---

## 10. Failure to Identify Environmental Root Cause Before Symptom Analysis

### What happened
When faced with a colony showing red complaints across every happiness factor simultaneously — an unusual pattern — the advisor attempted to diagnose each factor individually rather than recognizing that uniform red across all factors is a strong signal of a systemic root cause rather than multiple independent problems.

The correct diagnostic approach when all factors are simultaneously red is to first ask: what single condition could cause all of these at once? In this case, the answer was the frozen daylight cycle. Instead, the advisor pursued individual factor explanations for several exchanges before the player identified the actual cause.

### Why this matters for the mod
The Advisor must recognize diagnostic patterns, not just diagnostic values. A pattern of "all happiness factors simultaneously red at colony founding" is qualitatively different from "one or two factors red in an established colony." Pattern recognition should trigger different diagnostic branches.

### Design requirement
The Advisor's happiness diagnostic logic should include pattern matching:
- **Single factor red** → diagnose that specific factor
- **Multiple related factors red** (e.g., housing + sleep + commute) → look for a common upstream cause
- **All factors red simultaneously** → prioritize systemic causes first: game rule state, recent colony events, initialization issues
- **All factors red at day 1-3 of colony** → flag possible initialization artifact before individual diagnosis; recommend waiting one full normal day cycle before acting
- Uniform red across unrelated factors (injuries + food + sleep + unemployment simultaneously) should trigger a "check environmental conditions first" response rather than individual factor analysis

---

## 11. Research Dependencies Have Two Gating Conditions, Not One

### What happened
The advisor correctly identified that buildings require University research to unlock, but initially framed this as only requiring the University to be built and the research to be started. The actual in-game research tooltips revealed a second, independent gating condition: **building level requirements** that must be satisfied before the research can even begin, regardless of University level.

Specifically:
- **Woodwork** (unlocks Sawmill) — requires University level 1 AND Forester's Hut(s) totaling at least 3 levels, costs 64x Any Planks
- **Stringwork** (unlocks Fletcher's Hut) — requires University level 2 AND Woodwork completed, costs 16x String
- **Hitting Iron** (unlocks Blacksmith's Hut) — requires University level 1 AND Mine(s) totaling at least 3 levels, costs 1x Anvil

This means having a University and starting research is not sufficient — the colony must also have the prerequisite buildings at sufficient total level before the research becomes available.

### Why this matters for the mod
The Planner's dependency chain model must distinguish between two separate gating conditions for research:

1. **University level requirement** — what level the University must be to offer this research column
2. **Building level requirement** — what other buildings must exist at what total level before this research is available

Both conditions must be satisfied simultaneously. A colony with a level 2 University but no Forester cannot start Woodwork research. A colony with a level 3 Forester but no University cannot start it either.

### Design requirement
The Planner's research prerequisite model must store and evaluate for each research:
- Required University level
- Required building type(s) and total level sum
- Required prior research completions
- Material costs (must be in Warehouse or obtainable)

**Verified research chains as of this session:**

| Research | University Level | Building Prereq | Prior Research | Cost |
|----------|-----------------|-----------------|----------------|------|
| Woodwork | 1 | Forester's Hut(s) ≥ level 3 total | None | 64x Any Planks |
| Stringwork | 2 | None beyond Woodwork | Woodwork | 16x String |
| Hitting Iron | 1 | Mine(s) ≥ level 3 total | None | 1x Anvil |

**Corrected complete dependency chains:**

*Fletcher's Hut:*
Forester's Hut upgraded to level 3 total → University level 1 built and staffed → Woodwork research completed → Sawmill unlocked and built → University level 2 → Stringwork research completed → Fletcher's Hut unlocked → Warehouse + Courier → string supply → automated fishing rod replacement

*Blacksmith's Hut:*
Miner's Hut built and upgraded to level 3 total → University level 1 built and staffed → Hitting Iron research completed (1x Anvil) → Blacksmith's Hut unlocked → Warehouse + Courier → iron supply from Miner → automated tool replacement

The Planner must surface the **longest blocking dependency** when recommending a build path. For the Fletcher, the blocking dependency is upgrading the Forester to level 3. For the Blacksmith, it is building and upgrading a Miner to level 3. These are the actions the player needs to take first, not the final building.

---

## 12. What the Advisor Did Well (For Balance)



In fairness, the session also demonstrated some patterns worth preserving:

- **Flagging tradeoffs honestly** — the NW Guard Tower border expansion vs central security tradeoff was surfaced clearly and the player made an informed decision
- **Deferring to player judgment** — when the player pushed back on nudging the Town Hall southwest, the advisor updated its recommendation rather than insisting
- **Connecting mechanics** — linking the Residence build to multiple downstream benefits (commute fix, population growth, Guard Tower staffing) in a single dependency chain is exactly the Planner behavior the mod should replicate
- **Asking for data when uncertain** — requesting coordinates to calculate Fisher distance was correct, just overdue

These patterns should be preserved and amplified. The Advisor is most useful when it surfaces connections the player hasn't made yet — but only when those connections are grounded in verified colony state.

---

## 13. Summary of Design Requirements Derived from This Session

| Requirement | Priority | Role |
|-------------|----------|------|
| Persistent memory of player decisions | Critical | Advisor, Planner |
| Observe → Diagnose → Recommend discipline | Critical | Advisor |
| Full dependency chain validation before recommendations | Critical | Planner |
| Precise distance calculations from API coordinates | High | Advisor |
| Individual citizen happiness factor breakdown | High | Advisor |
| Distinguish red vs yellow happiness factors before escalating | High | Advisor |
| Never re-ask confirmed information | High | Advisor, Planner |
| Explicit uncertainty when data unavailable | Medium | Advisor |
| Log colony state at time of each recommendation | Medium | Advisor, Planner |
| Validate worker/bed availability before build recommendations | Critical | Planner |
| Check doDaylightCycle and environmental game rules before happiness diagnosis | High | Advisor |
| Research prerequisites as first-class dependencies in build chains | Critical | Planner |
| Never doubt or re-investigate player-confirmed facts | Critical | Advisor, Planner |
| Pattern recognition for uniform red happiness — check systemic causes first | High | Advisor |
| University unlock status check before recommending locked buildings | Critical | Planner |
| Model both University level AND building level prereqs for each research | Critical | Planner |
| Surface longest blocking dependency first in build path recommendations | High | Planner |

---

*Document generated from live colony advising session, days 3–7 of new Medieval Oak colony.*  
*Primary failure mode identified: confident recommendations made without verified colony state.*  
*Updated to include lessons from extended troubleshooting session covering happiness system diagnosis, environmental game state effects, research prerequisite modeling, and verified research dependency data.*
