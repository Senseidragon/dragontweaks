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

AssistantMod_DesignDoc_v0.1.docx

18.42 KB •526 lines•Formatting may be inconsistent from source
**The Assistant Mod**

Design Document v0.1

*Minecraft 1.21.1 • MineColonies Integration • LLM-Backed AI Roles*

Status: Brainstorm / Pre-Architecture

# 1. Overview

The Assistant Mod introduces a system of role-based AI entities that serve the player in Minecraft 1.21.1, with deep integration into the MineColonies mod ecosystem. The core concept is a personal assistant framework where unemployed MineColonies citizens can be assigned one of several specialized roles, each with its own capabilities, personality, and scope of commands.

The design prioritizes non-interference with MineColonies' internal systems, meaningful gameplay choices through resource constraints, and a sense of immersion through environmental awareness and in-character communication.

# 2. Core Design Philosophy

## 2.1 Non-Interference Principle

The mod must not modify, override, or risk breaking MineColonies' existing systems. All integration is achieved through MineColonies' public API and event system only. Citizens are never directly controlled or modified.

## 2.2 Role as Overlay

A role is an overlay applied to an existing unemployed citizen. The citizen continues to exist entirely within MineColonies' management. When MineColonies assigns the citizen a real job, the role is automatically and gracefully revoked.

## 2.3 Intangible Execution Model

Physical task execution is handled by invisible, intangible proxy entities (shadow entities) rather than the citizen themselves. This avoids chunk loading issues, entity death complications, and citizen pathfinding conflicts. The citizen is the personality and the name; the shadow entity is the worker.

## 2.4 Smart LLM Usage

LLM calls (to a local Ollama instance) are only made where response latency is acceptable: general chatter, task assignment parsing, colony event commentary, and citizen farewell messages. Time-critical execution paths (pathfinding, entity interaction, combat detection) are entirely deterministic code. This ensures the mod remains playable regardless of LLM response time.

## 2.5 Environmental Presence

Every interaction is colored by world context. The assistant is aware of time of day, weather, recent colony events (births, deaths, attacks, food crises), and biome. This context is injected into all responses to create the impression of a living, present companion rather than a utility tool.

# 3. Architecture

## 3.1 Class Hierarchy

The mod is built on a single abstract base class with concrete role implementations:

- AbstractAssistant

- Shared behaviors: follow player, idle, general chatter, environmental awareness

- Shared systems: capability matrix, deflection logic, event queue, LLM interface

- Role slot registration and revocation

- RanchHandAssistant extends AbstractAssistant

- ScoutAssistant extends AbstractAssistant

- AdvisorAssistant extends AbstractAssistant

- PlannerAssistant extends AbstractAssistant

## 3.2 Capability Matrix

Each role declares a set of capabilities it supports. Commands are matched against this matrix before execution or deflection:

| **Capability** | **Ranch Hand** | **Scout** | **Advisor** | **Planner** |
| --- | --- | --- | --- | --- |
| HERD_ANIMALS | ✅ | ❌ | ❌ | ❌ |
| ATTACH_LEADS | ✅ | ❌ | ❌ | ❌ |
| PERIMETER_PATROL | ❌ | ✅ | ❌ | ❌ |
| MOB_DETECTION | ❌ | ✅ | ❌ | ❌ |
| THREAT_REPORTING | ❌ | ✅ | ⚠️ | ❌ |
| COLONY_ANALYSIS | ❌ | ❌ | ✅ | ✅ |
| CITIZEN_MGMT | ❌ | ❌ | ✅ | ✅ |
| DEPENDENCY_PLANNING | ❌ | ❌ | ⚠️ | ✅ |
| GENERAL_CHAT | ✅ | ✅ | ✅ | ✅ |

*✅ = Supported   ❌ = Deflected   ⚠️ = Partial/Advisory only*

## 3.3 Role Slot System

Each player has a finite pool of role slots to prevent colony-scale abuse and maintain meaningful gameplay choices:

- Default: 3 total role slots per player

- Slots are a shared pool (not per-role limits) — the player decides the mix

- Slots can be expanded by constructing a Steward's Office or Command Post (MineColonies building, TBD)

- Maximum cap: 6–8 slots regardless of colony size

## 3.4 Shadow Entity Model

Physical task roles (Ranch Hand, Scout) use an invisible intangible proxy entity to perform world interactions:

- Shadow entity spawns when a physical role is assigned

- Has its own AI goal stack independent of the citizen

- Executes pathfinding, mob interaction, lead attachment, patrol routes

- All actions are attributed to the citizen by name in player-facing messages

- Shadow entity despawns when the role is revoked

- Analytical roles (Advisor, Planner) use no shadow entity — purely background processes

## 3.5 Environmental Context Payload

Before any interaction or LLM call, a context object is assembled and injected:

| **Context Field** | **Description** | **Source** |
| --- | --- | --- |
| TimeOfDay | MORNING / AFTERNOON / EVENING / NIGHT | World.getDayTime() |
| Weather | CLEAR / RAINING / THUNDERSTORM | World weather state |
| RecentWeather | Was raining in last 10 minutes | Rolling weather log |
| Biome | Current biome at player position | World.getBiome() |
| MoonPhase | Current moon phase (0–7) | World.getMoonPhase() |
| RecentEvents | Queue of last 5–10 colony events | Colony event listener |
| ColonySize | Current citizen count | MineColonies API |
| ThreatLevel | CALM / ELEVATED / UNDER_ATTACK | Colony raid state |
| FoodStatus | HEALTHY / LOW / CRITICAL | Colony food stores |

# 4. Role Definitions

## 4.1 Ranch Hand

**Personality: **Earthy, practical, humble. Loves animals, hates paperwork. Speaks simply.

Primary capabilities: herding animals, attaching leads, returning livestock to designated locations.

### Behavior Loop

- Receive task (e.g. 'round up 3 cows')

- Parse target mob type and quantity from command (or LLM intent parsing)

- Shadow entity scans world for nearest qualifying mobs

- Path to first target mob

- Programmatically attach lead

- Repeat until quantity met or scan radius exhausted

- Path to designated return point

- Attach leads to nearest fence post

- Report completion to player in character

### Deflection Example

*"**Wouldn**'**t a Planner be better suited to that? I**'**m good with critters but terrible at math.**"*

### Environmental Flavor Examples

- Clear morning: "Right away! Good morning for it — they'll be easy to spot."

- Thunderstorm: "I'll get to it, but they'll be skittish in this weather. Might take longer."

- After a death: "...Aye. I'll get your cows. Tom would've wanted us to keep going."

## 4.2 Scout

**Personality: **Wiry, alert, restless. Economical with words. Trusts instinct over analysis.

Primary capabilities: perimeter patrol, hostile mob detection, threat location reporting.

### Behavior Loop

- Receive patrol assignment or begin default perimeter route

- Shadow entity walks patrol path (dynamic or player-defined)

- Continuously scan for hostile mobs within detection radius

- On threat detection: record mob type, count, and coordinates

- Report to player: "Zombie spotted 40 blocks northeast, near the wheat farm"

- Continue patrol or investigate based on threat level

- Periodic check-ins: "Perimeter clear so far, continuing patrol"

### Deflection Example

*"**I**'**d rather be moving than crunching numbers. Get the Advisor for that — I**'**ll stay sharp out here.**"*

## 4.3 Advisor

**Personality: **Measured, thoughtful, slightly pompous. Has opinions. Offers them unsolicited.

Primary capabilities: colony happiness analysis, citizen management recommendations, resource gap identification.

### Behavior Loop

- Periodically poll MineColonies API for colony state

- Analyze citizen happiness, building coverage, guard proximity

- Surface prioritized recommendations to player proactively

- Respond to direct queries about specific citizens or buildings

- Flag inefficiencies (e.g. guard tower too far from residential area)

### Housing Optimization Example

*"**Tom and Alice are both unhappy with their commute. Moving them to Residence 4 would halve their travel time and place them within range of Guard Tower 2. Shall I recommend this to you formally?**"*

### Deflection Example

*"**Rounding up livestock? That**'**s rather beneath my analytical capabilities. A Ranch Hand would serve you better there.**"*

## 4.4 Planner

**Personality: **Systematic, dry, slightly condescending. Thinks in dependency chains. Dislikes improvisation.

Primary capabilities: dependency chain planning, build order optimization, crisis response sequencing, resource forecasting.

### Food Crisis Example Output

*"**Food crisis detected. Sustainable resolution requires a cattle operation. Dependency chain: cattle operation → cow herder citizen → wheat supply → wheat farm → farmer citizen + farmland. Current blocker: no wheat farm exists. Recommended build order: (1) Place farm, (2) Assign farmer, (3) Build cow herder hut adjacent to pasture, (4) Stock herder with wheat, buckets, axe. Estimated stabilization: 3 in-game days.**"*

### Deflection Example

*"**Herding animals. I**'**ll add it to the task queue... right after I reorganize the task queue. Perhaps a Ranch Hand?**"*

# 5. MineColonies Integration

## 5.1 Role Assignment Flow

- Player right-clicks an unemployed MineColonies citizen

- Custom UI opens showing available roles and current slot usage

- Player selects a role

- System verifies: citizen is unemployed, player has available slot

- Role assignment recorded in separate mod data structure (citizen is not modified)

- Shadow entity spawned if role requires physical execution

- Confirmation message delivered in-character

## 5.2 Role Revocation on Job Assignment

When MineColonies assigns a real job to a citizen with an active role:

- MineColonies fires CitizenJobAssignedEvent (requires API verification)

- Mod listener checks if citizen has an active role assignment

- If yes: generate in-character farewell message (LLM or template)

- Deliver message to player

- Remove role assignment from mod data

- Despawn shadow entity if present

- Release role slot back to player pool

### Farewell Message Examples by Role

| **Role** | **Example Farewell** |
| --- | --- |
| Ranch Hand | "Boss, they're putting me to work in the mines. Gonna miss them cows. Good luck with the herd." |
| Scout | "Got my marching orders — proper ones this time. Someone else'll have to watch the perimeter." |
| Advisor | "It appears my talents are required elsewhere. I trust you'll manage without my counsel... somehow." |
| Planner | "Reassignment logged. Transferring outstanding task queue to... nowhere. Good luck." |

## 5.3 Data Storage

Role assignments are stored outside of citizen or colony data to ensure zero interference:

- Stored in mod-owned SavedData attached to the world/level

- Key: Map<CitizenID, AssistantRoleRecord>

- AssistantRoleRecord contains: role type, assignment timestamp, player UUID, shadow entity UUID if applicable

- Persists across server restarts

- Automatically cleaned on citizen death or colony dissolution

## 5.4 API Dependencies to Verify

The following MineColonies API capabilities must be confirmed before implementation:

- CitizenJobAssignedEvent — does it fire publicly on job assignment?

- Citizen employment status query — is there a public isUnemployed() or similar?

- Colony food store access — can we query current food levels?

- Citizen happiness values — are individual happiness metrics accessible?

- Building registry — can we enumerate buildings and their positions?

- Citizen residence/workplace assignments — are these queryable?

# 6. LLM Integration

## 6.1 Architecture

The mod communicates with a locally hosted Ollama instance over HTTP. The recommended model is Gemma 4 26B running on a dedicated server (SENSEI configuration: Ryzen 7 3800X, 64GB RAM, RTX 2060 6GB VRAM).

Endpoint: http://SENSEI:11434/api/generate

## 6.2 When LLM is Used vs Not Used

| **Interaction Type** | **LLM?** | **Rationale** |
| --- | --- | --- |
| General chatter | Yes | Dynamic language needed, latency acceptable |
| Task assignment parsing | Yes | Natural language intent extraction |
| Colony event commentary | Yes | Context-rich, latency acceptable |
| Farewell messages on revocation | Yes | One-time, latency acceptable |
| Greeting (time of day) | No | Template pool, must be instant |
| Role-specific commands | No | Deterministic execution, latency unacceptable |
| Deflection responses | No | Template pool, must feel snappy |
| Pathfinding decisions | No | Real-time, deterministic required |
| Threat detection | No | Real-time, deterministic required |

## 6.3 System Prompt Structure

Each role is given a personality system prompt prepended to all LLM calls:

- Establishes role identity, speech style, and knowledge scope

- Includes current environmental context payload

- Includes relevant recent colony events (filtered to most contextually appropriate)

- Includes current task status if applicable

- Instructs model to stay in character and deflect out-of-scope requests warmly

## 6.4 Fallback Behavior

If the Ollama instance is unreachable or times out:

- Fall back to template pool responses silently

- Do not surface errors to player in immersion-breaking ways

- Log connection failures for server operator review

- Retry connection periodically rather than failing permanently

# 7. Interaction Model

## 7.1 Command Detection

The mod intercepts player chat messages when the player is within proximity of a citizen with an active role assignment. No special prefix is required — the entity "overhears" nearby chat naturally.

- Proximity threshold: TBD (suggest 8–10 blocks as starting point)

- If multiple role-assigned citizens are in range, nearest one responds

- Commands are parsed for intent before execution or deflection

- Unrecognized commands fall through to general chatter / LLM

## 7.2 Response Pipeline

- Player chat message detected within proximity

- Build EnvironmentalContext payload

- Classify interaction: role command / out-of-role / general chatter / greeting

- Role command → capability check → execute or deflect

- General chatter → LLM call with context + system prompt

- Response delivered via in-world chat, attributed to citizen by name

## 7.3 Greeting System

Time-of-day greetings are template-based (no LLM) for instant response:

| **Time** | **Ranch Hand** | **Scout** | **Advisor** | **Planner** |
| --- | --- | --- | --- | --- |
| Morning | "Mornin' boss, critters are already up." | "Early start. Good. Been up since third watch." | "Good morning. I've prepared a summary of overnight developments." | "Morning. I've updated the task queue." |
| Afternoon | "Afternoon. Hot one today." | "Quiet so far. Almost too quiet." | "Have you reviewed my recommendations?" | "Three tasks pending your approval." |
| Evening | "Sun's going down. Heading in soon." | "Getting dark. I'll stay out a bit longer." | "The colony's numbers look better today." | "Tomorrow's schedule is prepared." |
| Night | "Shouldn't you be sleeping, boss?" | "You're up late. Something wrong?" | "Burning the midnight oil? So am I." | "Night work is inefficient. But here we are." |

# 8. Development Phases

## Phase 1 — Foundation (Moderate)

- AbstractAssistant entity class

- Role assignment UI (right-click unemployed citizen)

- Role slot system (3 default slots)

- MineColonies event listener (job assignment detection)

- Basic in-character revocation messaging (template)

- Environmental context payload builder

- Template-based greeting system

## Phase 2 — Ranch Hand (Moderate)

- Shadow entity framework (intangible, invisible)

- Mob scanning (AABB search)

- Pathfinding to target mob

- Programmatic lead attachment

- Return-to-point behavior

- Fence post lead attachment

- Completion reporting in character

## Phase 3 — Scout (Moderate)

- Perimeter patrol path generation

- Hostile mob detection within radius

- Threat location calculation and reporting

- Periodic check-in system

- Threat level assessment

## Phase 4 — MineColonies Deep Integration (Hard)

- Colony state polling (food, happiness, buildings)

- Citizen happiness analysis

- Housing/workplace proximity optimization

- Advisor recommendation engine

- Colony event queue (births, deaths, raids, completions)

## Phase 5 — LLM Integration (Hard)

- Ollama HTTP client in mod

- Role system prompt templates

- Natural language task intent parsing

- General chatter routing to LLM

- Colony event commentary generation

- Fallback/timeout handling

## Phase 6 — Planner & Dependency Chains (Very Hard)

- Dependency chain modeling for colony systems

- Build order optimization

- Crisis response sequencing

- Resource forecasting

- LLM-assisted plan generation with colony state context

# 9. Open Questions & Decisions Pending

| **Question** | **Status** | **Notes** |
| --- | --- | --- |
| Does MineColonies fire a public CitizenJobAssignedEvent? | Unverified | Check MineColonies GitHub API |
| Are citizen happiness values queryable via public API? | Unverified | May require source spelunking |
| What is the right proximity threshold for command detection? | TBD | 8-10 blocks suggested as start |
| Should role slots be expandable via a new building? | TBD | Steward's Office concept proposed |
| What happens to role if citizen dies (not job assignment)? | TBD | Likely: revoke and notify |
| Should shadow entity be visible to other players on multiplayer? | TBD | Intangible but possibly visible? |
| Maximum scan radius for Ranch Hand mob search? | TBD | Performance vs utility tradeoff |
| Should Scout patrol path be player-defined or auto-generated? | TBD | Both options have merit |
| Mod name? | TBD | Working title: The Assistant Mod |
| Mod loader target: Fabric or NeoForge? | TBD | Depends on MineColonies target version |

# 10. Technical Notes

## 10.1 Target Environment

- Minecraft: 1.21.1

- MineColonies: latest version compatible with 1.21.1

- Mod Loader: TBD (Fabric or NeoForge)

- Java: 21+

## 10.2 LLM Server Configuration

- Host: SENSEI (local network)

- Ollama version: 0.20.6

- Model: gemma4:26b

- Endpoint: http://SENSEI:11434/api/generate

- Expected cold response: ~36 seconds (first query after boot)

- Expected warm response: ~10-13 seconds

- Timeout recommendation: 60 seconds before fallback to template

## 10.3 Key Risks

- MineColonies API coverage may not expose all needed data (mitigation: audit API early in Phase 1)

- LLM latency may feel too slow for some interaction types (mitigation: strict LLM/template routing)

- Shadow entity pathfinding may conflict with MineColonies world modifications (mitigation: test early)

- MineColonies version updates may change API (mitigation: abstract all MC/colony calls behind interfaces)

*End of Design Document v0.1*
