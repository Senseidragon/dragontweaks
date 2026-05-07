# The Assistant Mod — Design Document v0.3

**Minecraft 1.21.1 · NeoForge · MineColonies Integration · LLM-Backed AI Roles**

| | |
|---|---|
| Status | Phase 1 — Active Development |
| Repo | https://github.com/Senseidragon/dragontweaks |
| Branch | phase-1 |
| Mod ID | dragontweaks |
| Package | io.github.senseidragon.dragontweaks |

**v0.3 Change Summary:** Converted from .docx to .md. Updated LLM backend from Ollama to OpenRouter. Corrected phase ordering to reflect actual build state. Replaced Steward's Office slot expansion with Town Hall level system. Added two-tier NPC architecture. Removed all Ollama-specific config. Cleaned stale open questions.

---

## 1. Overview

The Assistant Mod introduces a system of role-based AI entities that serve the player in Minecraft 1.21.1 with deep integration into the MineColonies mod ecosystem. Unemployed MineColonies citizens can be assigned one of several specialized roles, each with its own capabilities, personality, and scope.

The design prioritizes non-interference with MineColonies internals, meaningful gameplay choices through resource constraints, and immersion through environmental awareness and in-character communication.

---

## 2. Core Design Philosophy

### 2.1 Non-Interference Principle

The mod must not modify, override, or risk breaking MineColonies' existing systems. All integration is achieved through MineColonies' public API and event system only. Citizens are never directly controlled or modified.

### 2.2 Role as Overlay

A role is an overlay applied to an existing unemployed citizen. The citizen continues to exist entirely within MineColonies' management. When MineColonies assigns the citizen a real job, the role is automatically and gracefully revoked.

### 2.3 Intangible Execution Model

Physical task execution is handled by invisible, intangible proxy entities (shadow entities) rather than the citizen themselves. This avoids chunk loading issues, entity death complications, and citizen pathfinding conflicts. The citizen is the personality and the name; the shadow entity is the worker.

### 2.4 Smart LLM Usage

LLM calls are only made where response latency is acceptable: general chatter, task assignment parsing, colony event commentary, and farewell messages. Time-critical execution paths (pathfinding, entity interaction, threat detection) are entirely deterministic code. The LLM backend is OpenRouter. A valid API key enables Full mode; without it, the mod runs in Lite mode (diagnostic dashboard only, no entities, no LLM features). See Section 8.

### 2.5 Environmental Presence

Every interaction is colored by world context. The assistant is aware of time of day, weather, recent colony events, and biome. This context is injected into all LLM calls to create the impression of a living, present companion.

### 2.6 Advisor Behavior Contract

The Advisor role is bound by a strict behavioral contract derived from live colony testing. Violations are treated as implementation bugs, not acceptable behavior:

- **Observe → Diagnose → Recommend** — never skip Observe. Poll API state before forming any opinion.
- Never offer explanations before seeing the relevant data. Honest uncertainty beats false confidence.
- Never re-investigate player-confirmed facts. Confirmed state is immutable until the player explicitly changes it.
- Check environmental preconditions (`doDaylightCycle`, weather, time) before diagnosing citizen complaints.
- Every recommendation must cite the specific API value or calculation that triggered it.
- Commute distance is a derived metric, not a native happiness factor — surface it as Advisor analysis, not colony complaint data.

---

## 3. Architecture

### 3.1 Hard Architectural Rules

These rules are non-negotiable. Any generated code that violates them must be rejected and rewritten.

| Rule | Requirement |
|---|---|
| Game thread | Nothing may ever block the main Minecraft game thread. Absolute. |
| Async | All network calls (including OpenRouter HTTP requests) must be async on a separate thread. |
| Re-queue | Responses from async operations must be queued back to the main thread via server tick queue before any game interaction. |
| MineColonies | Zero interference with MineColonies internals. All integration via public API and event system only. Citizens are never directly modified. |
| API verification | All MineColonies API dependencies must be verified against stubs in `docs/stubs/` before any dependent code is written. |

### 3.2 Two-Tier NPC Architecture

The mod operates two entirely separate NPC tiers that share the LLM backend but are otherwise independent systems.

**Tier 1 — Functional Role NPCs (MineColonies Citizens)**

- No spawned entity — the citizen already exists, managed entirely by MineColonies
- Mod attaches a record to the citizen via `RoleAssignmentData` (citizen is never modified)
- LLM provides advisory and observational output tied to the citizen's actual job data
- No follow, no stop, no direct movement — MineColonies owns the citizen's behavior entirely
- Assignment via GUI triggered by right-clicking an unemployed citizen
- Consumes a role slot (capped by Town Hall level — see Section 3.4)
- Valid roles are configured archetypes only: Ranch Hand, Scout, Advisor, Planner, or server-operator additions
- Freeform descriptions are not valid roles

**Tier 2 — Flavor / Immersion NPCs**

- Real entity spawned into the world — the mod owns this entity entirely
- Not attached to any MineColonies citizen
- Supports follow, stop, direct interaction, idle routines, patrol behaviors
- Created via `/assistant spawn [persona]`
- Does not consume a role slot
- No functional MineColonies integration — purely emergent and immersive
- Separate spawn cap needed to prevent server performance issues (value TBD)

### 3.3 Class Hierarchy

```
AbstractAssistant
  — shared: follow player, idle, chatter, environmental awareness
  — shared: capability matrix, deflection logic, event queue, LLM interface
  — shared: role slot registration and revocation

RanchHandAssistant  extends AbstractAssistant
ScoutAssistant      extends AbstractAssistant
AdvisorAssistant    extends AbstractAssistant
PlannerAssistant    extends AbstractAssistant
```

### 3.4 Capability Matrix

| Capability | Ranch Hand | Scout | Advisor | Planner |
|---|---|---|---|---|
| `HERD_ANIMALS` | ✅ | ❌ | ❌ | ❌ |
| `ATTACH_LEADS` | ✅ | ❌ | ❌ | ❌ |
| `PERIMETER_PATROL` | ❌ | ✅ | ❌ | ❌ |
| `MOB_DETECTION` | ❌ | ✅ | ❌ | ❌ |
| `THREAT_REPORTING` | ❌ | ✅ | ⚠️ Advisory | ❌ |
| `COLONY_ANALYSIS` | ❌ | ❌ | ✅ | ✅ |
| `CITIZEN_MGMT` | ❌ | ❌ | ✅ | ✅ |
| `DEPENDENCY_PLANNING` | ❌ | ❌ | ⚠️ Advisory | ✅ |
| `GENERAL_CHAT` | ✅ | ✅ | ✅ | ✅ |

✅ Supported · ❌ Deflected · ⚠️ Partial / Advisory only

### 3.5 Role Slot System

- Slots are a shared pool per player (not per-role limits)
- Cap is tied dynamically to Town Hall level — queried at runtime, never hardcoded

| Town Hall Level | Role Slots |
|---|---|
| Below 3 | 3 |
| 3 | 4 |
| 4 | 5 |
| 5 | 6 |

- Hard cap: 6 slots regardless of colony size
- No `MAX_SLOTS` constant anywhere in source — always query TH level dynamically

### 3.6 Shadow Entity Model

- Shadow entity spawns when a physical role (Ranch Hand, Scout) is assigned
- Has its own AI goal stack independent of the citizen
- Executes pathfinding, mob interaction, lead attachment, patrol routes
- All actions attributed to the citizen by name in player-facing messages
- Shadow entity despawns when role is revoked
- Analytical roles (Advisor, Planner) use no shadow entity — purely background processes

### 3.7 Environmental Context Payload

| Context Field | Description | Source |
|---|---|---|
| `TimeOfDay` | MORNING / AFTERNOON / EVENING / NIGHT | `World.getDayTime()` |
| `Weather` | CLEAR / RAINING / THUNDERSTORM | World weather state |
| `RecentWeather` | Was raining in last 10 minutes | Rolling weather log |
| `Biome` | Current biome at player position | `World.getBiome()` |
| `MoonPhase` | Current moon phase (0–7) | `World.getMoonPhase()` |
| `RecentEvents` | Queue of last 5–10 colony events | Colony event listener |
| `ColonySize` | Current citizen count | MineColonies API |
| `ThreatLevel` | CALM / ELEVATED / UNDER_ATTACK | Colony raid state |
| `FoodStatus` | HEALTHY / LOW / CRITICAL | Colony food stores (API access unverified — Phase 4) |
| `DaylightCycle` | `doDaylightCycle` gamerule state | Minecraft `GameRules` system (not MineColonies API) |

> **Note:** `doDaylightCycle` must be checked via Minecraft's native `GameRules` system. If false, all happiness diagnostics are suppressed until it is restored.

---

## 4. MineColonies API — Verified Capabilities

All items marked ✅ have been confirmed through direct API source inspection. Items marked ⚠️ require additional mod-side logic or are unconfirmed.

### 4.1 API Entry Point

```java
IMinecoloniesAPI api    = IMinecoloniesAPI.getInstance();
IColonyManager   mgr    = api.getColonyManager();
IColony          colony = mgr.getIColonyByOwner(world, player);  // server-side
IColonyView      view   = ...;                                    // client-side, read-only
```

### 4.2 Colony-Level Queries

| API Call | Returns | Status |
|---|---|---|
| `colony.getCitizenManager().getCurrentCitizenCount()` | `int` | ✅ Verified |
| `view.getCitizenCountLimit()` | `int` — housing cap | ✅ Verified |
| `view.getOverallHappiness()` | `double` — colony average | ✅ Verified |
| `view.getWorkOrders()` | Active work orders list | ✅ Verified |
| `colony.getResearchManager().getResearchTree()` | `ILocalResearchTree` | ✅ Verified |
| `colony.getServerBuildingManager().getBuilding(pos)` | `IBuilding` | ✅ Verified |

### 4.3 Per-Citizen Queries (Server-Side, `ICitizenData`)

| API Call | Returns | Status |
|---|---|---|
| `data.getJob()` | Job assignment or null | ✅ Verified |
| `data.getWorkBuilding()` | Building position | ✅ Verified |
| `data.getHomeBuilding()` | Home building position | ✅ Verified |
| `data.getCitizenSkillHandler()` | Skill values | ✅ Verified |
| `data.getCitizenHappinessHandler()` | `ICitizenHappinessHandler` | ✅ Verified |
| `data.getEntity()` | `Optional<AbstractEntityCitizen>` | ✅ Verified |
| `data.getPartner()` / `data.getChildren()` | Family relationships | ✅ Verified |
| Commute distance | Work pos − Home pos (Advisor derives) | ⚠️ Derived |

### 4.4 Happiness System — Verified Factor IDs

The happiness handler exposes a named modifier list. Iterate and query by string ID. Overall happiness ranges from 0.0–∞ where 1.0 is neutral; below 1.0 is negative, above 1.0 is positive.

```java
ICitizenHappinessHandler h = data.getCitizenHappinessHandler();
double overall = h.getHappiness(colony, data);

for (String id : h.getModifiers()) {
    IHappinessModifier mod = h.getModifier(id);
    double factor = mod.getFactor(data);  // < 1.0 = negative
    double weight = mod.getWeight();
}
```

| Factor ID | What It Measures | Modifier Type |
|---|---|---|
| `"food"` | Whether citizen is being fed adequately | `StaticHappinessModifier` |
| `"slepttonight"` | Whether citizen slept last night (bed distance folded in) | `TimeBasedHappinessModifier` |
| `"housing"` | Whether citizen has a home building assigned | `StaticHappinessModifier` |
| `"health"` | Injury / disease state | `StaticHappinessModifier` |
| `"unemployment"` | Whether citizen has a job | `StaticHappinessModifier` |
| `"idleatjob"` | Whether citizen is stuck idle at their work building | `ExpirationBasedHappinessModifier` |
| `"security"` | Proximity to a guard building | `StaticHappinessModifier` |
| `"school"` | Access to a school (children only) | `StaticHappinessModifier` |
| `"social"` | Social interactions with other citizens | `ExpirationBasedHappinessModifier` |
| `"mystical"` | Proximity to a Mystical Site | `StaticHappinessModifier` |
| `"greatfood"` (no constant) | Bonus tier of food factor — ID unconfirmed | ⚠️ Unverified |

| Modifier Type | Behavior |
|---|---|
| `StaticHappinessModifier` | Fixed factor, does not decay over time |
| `ExpirationBasedHappinessModifier` | Factor that expires after N ticks |
| `TimeBasedHappinessModifier` | Factor tied to a time window (e.g. slept last night) |

> **IMPORTANT:** There is no `"commute"` happiness factor. Distance-to-work is not tracked by MineColonies. The Advisor must calculate commute distance independently using `getWorkBuilding()` and `getHomeBuilding()` positions and apply its own threshold logic. This is Advisor-derived analysis, not a colony complaint.

### 4.5 Research System — Verified Call Chain

```java
ILocalResearchTree tree = colony.getResearchManager().getResearchTree();

// Check a single research
boolean done = tree.hasCompletedResearch(
    new ResourceLocation("minecolonies", "technology/some_research"));

// Full completed list
List<ResourceLocation> completed = tree.getCompletedList();

// In-progress researches
List<ILocalResearch> inProgress = tree.getResearchInProgress();
for (ILocalResearch r : inProgress) {
    r.getId();        // ResourceLocation
    r.getBranch();    // ResourceLocation
    r.getDepth();     // int (tier 1–6)
    r.getProgress();  // int (ticks)
    r.getState();     // ResearchState: NOT_STARTED, IN_PROGRESS, FINISHED
}

// Check branch max (level 6) completion
boolean maxed = tree.branchFinishedHighestLevel(
    new ResourceLocation("minecolonies", "warfare"));
```

> **Key limitation:** `getResearchInProgress()` only knows about researches that have been started. Research not yet touched does not appear. The three-state model is: not started (absent from tree + `hasCompletedResearch()` = false) / in progress / complete.

### 4.6 Event System — Available Events

| Event | Category | Advisor/Planner Use |
|---|---|---|
| `CitizenAddedModEvent` | Citizens | Population growth trigger — re-evaluate housing/employment |
| `CitizenRemovedModEvent` | Citizens | Clean up role assignments if citizen removed |
| `CitizenDiedModEvent` | Citizens | Trauma response; clean up shadow entity and role slot |
| `CitizenJobChangedModEvent` | Citizens | **Critical** — triggers role revocation flow |
| `BuildingAddedModEvent` | Buildings | Planner can track new build availability |
| `BuildingRemovedModEvent` | Buildings | Invalidate cached building state |
| `BuildingConstructionModEvent` | Buildings | Track build completion for dependency chain progress |
| `ColonyCreatedModEvent` | Colony | Initialize Advisor state on new colony |
| `ColonyDeletedModEvent` | Colony | Full cleanup of all role state |
| `ColonyViewUpdatedModEvent` | Colony | Client-side state refresh trigger |
| `PlayerEnteringModEvent` | Players | Proximity and greeting trigger |

### 4.7 Building Queries

```java
IBuilding b = colony.getServerBuildingManager().getBuilding(pos);
b.getLevel();               // current level
b.getMaxLevel();            // maximum level
b.isBuilt();                // true if construction complete
b.isPendingConstruction();  // true if queued or in progress
b.getCustomName();          // player-set name if any
```

---

## 5. Role Definitions

### 5.1 Ranch Hand

**Personality:** Earthy, practical, humble. Loves animals, hates paperwork. Speaks simply.

**Primary capabilities:** herding animals, attaching leads, returning livestock to designated locations.

**Behavior loop:**
1. Receive task (e.g. "round up 3 cows")
2. Parse target mob type and quantity via LLM intent parsing
3. Shadow entity scans world for nearest qualifying mobs (AABB search)
4. Path to first target mob; programmatically attach lead
5. Repeat until quantity met or scan radius exhausted
6. Path to designated return point; attach leads to nearest fence post
7. Report completion to player in character

**Sighting Memory:**

The Ranch Hand passively accumulates animal sighting data as it wanders. Each entry contains animal type, approximate XZ coordinates, and a game-tick timestamp (`level.getGameTime()`).

Dual eviction keeps the list bounded under any server configuration:
- **TTL eviction:** entries older than TTL_TICKS are stale and dropped on next list access. Default ~12,000 ticks (~10 real minutes). Config value.
- **Cap eviction:** if entry count exceeds the cap after adding a new sighting, the oldest entry is dropped (FIFO). Suggested cap: 8–10 entries. Config value.

Cleanup is lazy — runs on write only. One pass per write: sweep stale entries → append new entry → apply cap eviction.

**Fetch decision logic:** Fresh sighting exists for requested animal type → Ranch Hand expresses confidence and goes. No fresh sighting → expresses uncertainty but goes anyway. No relevant colony facility for that animal type (no cowherder hut for cows, no swineherd hut for pigs) → Ranch Hand will not attempt to catch it, but still records sightings.

**Passive collection:** When Ranch Hand spots a stray animal for which a colony facility exists, it leashes it and delivers it to the nearest appropriate facility. Multiple facilities of the same type split the load naturally.

**Multi-animal handling:** One animal at a time. Ignore additional strays spotted mid-transit; complete current delivery first. If the destination facility is destroyed while en route, release the lead and resume wandering.

**Wandering:** Biases toward areas with previous sightings and known facility locations. If no sightings occur within a configurable period, selects a new destination weighted toward productive areas. Does not repeatedly wander the same empty areas.

**Operational boundary:** Operates beyond colony bounds at a configurable buffer distance. Sky visibility constraint still applies — no underground movement.

**Deflection:** *"Wouldn't a Planner be better suited to that? I'm good with critters but terrible at math."*

**Environmental flavor examples:**
- Clear morning: *"Right away! Good morning for it — they'll be easy to spot."*
- Thunderstorm: *"I'll get to it, but they'll be skittish in this weather. Might take longer."*
- After a citizen death: *"...Aye. I'll get your cows. Tom would've wanted us to keep going."*

---

### 5.2 Scout

**Personality:** Wiry, alert, restless. Economical with words. Trusts instinct over analysis.

**Primary capabilities:** perimeter patrol, hostile mob detection, threat location reporting.

**Behavior loop:**
1. Receive patrol assignment or begin default perimeter route
2. Shadow entity walks patrol path (dynamic or player-defined)
3. Continuously scan for hostile mobs within detection radius
4. On threat detection: record mob type, count, and coordinates
5. Report: *"Zombie spotted 40 blocks northeast, near the wheat farm"*
6. Continue patrol or investigate based on threat level
7. Periodic check-ins: *"Perimeter clear so far, continuing patrol"*

**Deflection:** *"I'd rather be moving than crunching numbers. Get the Advisor for that."*

> **Scout patrol implementation note:** Periodic AABB detection sweep on a timer. No pathfinding, no waypoints, no navigation goal stack. Player-defined vs auto-generated patrol path — default to auto with player override (design TBD for Phase 3).

---

### 5.3 Advisor

**Personality:** Measured, thoughtful, slightly pompous. Has opinions. Offers them unsolicited.

**Primary capabilities:** colony happiness analysis, citizen management recommendations, resource gap identification.

**Behavior loop (Observe → Diagnose → Recommend):**
1. Check environmental preconditions (`doDaylightCycle`, weather, time) before any diagnosis
2. Poll MineColonies API for full colony state
3. Iterate all citizens: read happiness handler, calculate derived metrics (commute distance)
4. Distinguish red (active, compounding) vs yellow (mild, stable) factors before escalating
5. Surface prioritized recommendation citing specific API values that triggered it
6. Respond to direct queries; never re-ask confirmed information

**Example output:** *"Tom and Alice score 0.72 on slepttonight. Their home building is 87 blocks from their work site. Moving them to Residence 4 would reduce that to 31 blocks and bring them within range of Guard Tower 2. Shall I formalize that recommendation?"*

**Deflection:** *"Rounding up livestock? That's rather beneath my analytical capabilities. A Ranch Hand would serve you better there."*

**State Machine:**

The Advisor exists in one of four named states. State is persisted in player-attached `SavedData` — not entity NBT alone, which does not survive despawn. One Advisor entity per player maximum. Per-player state — multiple players on the same server have fully independent Advisor state.

| State | Entity | Entry Trigger |
|---|---|---|
| `DORMANT` | None | Initial — no build tool has ever entered this player's hotbar |
| `PRE_COLONY` | Floating book, player-attached | Build tool enters hotbar for the first time (one-time per-player trigger) |
| `COLONY_NO_CITIZEN` | Floating book, colony-attached | `ColonyCreatedModEvent` fires for this player's colony |
| `COLONY_WITH_CITIZEN` | Floating book-and-quill, colony-attached | Player assigns unemployed citizen to Advisor role via assignment UI |

**DORMANT:** Zero background activity. No ticks. Only one listener: passive inventory change waiting for build tool hotbar event.

**PRE_COLONY:** Floating book follows player at all times at a configurable positional offset (~1.5–2 blocks to one side and slightly behind — never at player coordinates). Entity has native glow effect (verify against NeoForge 1.21.1 before implementing — do not assume 1.20.x method carries over). Hotbar visibility toggle: book despawns if build tool leaves hotbar slots 1–9; check runs every 40 ticks (config value; ONLY active in PRE_COLONY). Responds to any nearby player chat — no keyword required. Colony knowledge: empty.

**COLONY_NO_CITIZEN:** Book is now colony-attached. Follows player while within colony bounds. If player exits colony bounds: holds last valid in-bounds position while player remains within detection range (separate config value from `COMMAND_PROXIMITY`, suggested 32–48 blocks); snaps instantly to Town Hall if player goes beyond detection range. Hotbar toggle fully inactive. Keyword "Advisor" (case-insensitive) required at start of chat message. Colony knowledge: full structural data — all buildings, positions, levels, assigned workers.

**COLONY_WITH_CITIZEN:** Simple book despawns; book-and-quill spawns in its place — the visual change is the passive signal that full capability is unlocked. Same movement rules as `COLONY_NO_CITIZEN`. The shadow entity follows the player, not the citizen — the citizen is the name and personality anchor only; the citizen's MineColonies work continues completely unmodified. Primary trigger: citizen's name (case-insensitive). Fallback trigger: "Advisor" keyword — Advisor reminds player of citizen name in character. Full `Observe → Diagnose → Recommend` loop active. Colony knowledge: full structural + per-citizen data.

**Degraded transitions:**
- `COLONY_WITH_CITIZEN` → `COLONY_NO_CITIZEN`: triggered by `CitizenJobChangedModEvent` (citizen received a real job) or `CitizenDiedModEvent`. Book-and-quill despawns; simple book respawns at Town Hall. Advisor delivers one unprompted notification that the role is vacant. Citizen name no longer a valid trigger.
- Any colony state → `PRE_COLONY`: triggered by `ColonyDeletedModEvent`. All Advisor entities despawn; simple book respawns at player's current position. Hotbar toggle reactivates. Build tool trigger flag preserved — Advisor does not re-fire from scratch.

**SavedData fields (per player):**

| Field | Type | Notes |
|---|---|---|
| `advisorState` | `AdvisorState` enum | `DORMANT` / `PRE_COLONY` / `COLONY_NO_CITIZEN` / `COLONY_WITH_CITIZEN` |
| `buildToolTriggerFired` | boolean | True once build tool has ever entered hotbar. Never resets. |
| `assignedCitizenId` | Integer (nullable) | Null unless `COLONY_WITH_CITIZEN` |
| `advisorEntityUUID` | UUID (nullable) | Null when entity is despawned |

**Config values:**

| Config Key | Type | Default | Notes |
|---|---|---|---|
| `ADVISOR_ENTITY_OFFSET` | double | 1.8 | Blocks offset from player. Not hardcoded. |
| `ADVISOR_HOTBAR_CHECK_TICKS` | int | 40 | Hotbar poll interval. PRE_COLONY only. |
| `ADVISOR_BOUNDARY_DETECTION_RANGE` | int | 40 | Blocks — range before snap to Town Hall. |
| `ADVISOR_WHISPER_THRESHOLD` | int | 120 | Characters — above this triggers whisper pattern. |
| `ADVISOR_FORCE_PRIVATE` | boolean | false | Server operator override — forces all responses private. |

**Implementation verification required before coding:**

| Item | Verification Target |
|---|---|
| Build tool item ID | MineColonies item registry — do not assume or hardcode |
| Glow effect application | NeoForge 1.21.1 entity rendering — do not assume 1.20.x method |
| `colony.isCoordInColony()` signature | MineColonies API stubs |
| `ColonyCreatedModEvent` | Already confirmed in API reference — use it |
| `ColonyDeletedModEvent` | Already confirmed in API reference — use it |

---

### 5.4 Planner

**Personality:** Systematic, dry, slightly condescending. Thinks in dependency chains. Dislikes improvisation.

**Primary capabilities:** dependency chain planning, build order optimization, crisis response sequencing, resource forecasting.

**Dependency chain validation rules:**
- Check research prerequisites first — if a building is research-locked, the chain starts at the University
- Validate worker availability: is there an unemployed citizen to staff this building?
- Validate bed availability: if no worker, is there a free bed for a new citizen?
- Validate University level AND building level prerequisites for each research (both conditions must be satisfied simultaneously)
- Surface the longest blocking dependency first
- Never recommend a building that requires research not yet complete

**Example output:** *"Food crisis detected. Dependency chain: cattle operation → cow herder citizen → wheat supply → wheat farm → farmer + farmland. Current blocker: no wheat farm exists. Recommended build order: (1) Place farm, (2) Assign farmer, (3) Build herder hut, (4) Stock with wheat, buckets, axe. Estimated stabilization: 3 in-game days."*

**Deflection:** *"Herding animals. I'll add it to the task queue... right after I reorganize the task queue. Perhaps a Ranch Hand?"*

---

## 6. MineColonies Integration

### 6.1 Role Assignment Flow

1. Player right-clicks an unemployed MineColonies citizen
2. Custom UI opens showing available roles and current slot usage
3. Player selects a role
4. System verifies: citizen is unemployed, player has available slot
5. Role assignment recorded in `RoleAssignmentData` (citizen is not modified)
6. Shadow entity spawned if role requires physical execution
7. Confirmation message delivered in-character

### 6.2 Role Revocation on Job Assignment

When MineColonies assigns a real job to a citizen with an active role:

1. MineColonies fires `CitizenJobChangedModEvent`
2. Mod listener checks if citizen has an active role assignment
3. If yes: generate in-character farewell message (LLM or template)
4. Deliver message to player
5. Remove role assignment from `RoleAssignmentData`
6. Despawn shadow entity if present
7. Release role slot back to player pool

| Role | Example Farewell Message |
|---|---|
| Ranch Hand | *"Boss, they're putting me to work in the mines. Gonna miss them cows. Good luck with the herd."* |
| Scout | *"Got my marching orders — proper ones this time. Someone else'll have to watch the perimeter."* |
| Advisor | *"It appears my talents are required elsewhere. I trust you'll manage without my counsel... somehow."* |
| Planner | *"Reassignment logged. Transferring outstanding task queue to... nowhere. Good luck."* |

### 6.3 Data Storage

- Stored in mod-owned `SavedData` attached to the world/level
- Key: `Map<CitizenID, AssistantRoleRecord>`
- `AssistantRoleRecord` fields: `citizenId` (int), `roleType` (String), `assignmentTimestamp` (long), `playerUUID` (UUID), `shadowEntityUUID` (UUID — intentional, do not remove)
- Persists across server restarts via NBT
- Automatically cleaned on citizen death or colony dissolution

### 6.4 Citizen Death Handling

`CitizenDiedModEvent` triggers the following sequence:

1. Check if deceased citizen has an active role assignment
2. If yes: generate in-character notification to player
3. Remove role assignment from `RoleAssignmentData`
4. Despawn shadow entity if present
5. Release role slot back to player pool
6. Log event to colony event queue for context payload

---

## 7. LLM Integration

### 7.1 Backend Configuration

| Parameter | Value |
|---|---|
| Provider | OpenRouter |
| Endpoint | `https://openrouter.ai/api/v1/chat/completions` |
| Model | `google/gemma-4-26b-a4b-it` |
| Auth | `Authorization: Bearer <key>` — key in `.env` only, never hardcoded, never committed |
| Request format | OpenAI messages array: `[{"role":"system","content":"..."},{"role":"user","content":"..."}]` |
| `max_tokens` | 100 — always, never omit |
| `stream` | `false` — always |
| API key | Mode flag — absent/blank/placeholder key → Lite mode (no crash, no fail-to-start). See Section 8. |

### 7.2 LLM vs. Template Routing

| Interaction Type | LLM? | Rationale |
|---|---|---|
| General chatter | Yes | Dynamic language needed, latency acceptable |
| Task assignment parsing | Yes | Natural language intent extraction |
| Colony event commentary | Yes | Context-rich, latency acceptable |
| Farewell messages on revocation | Yes | One-time, latency acceptable |
| Advisor narrative explanations | Yes | Colony state context needed for nuance |
| Greeting (time of day) | No | Template pool, must be instant |
| Role-specific commands | No | Deterministic execution, latency unacceptable |
| Deflection responses | No | Template pool, must feel snappy |
| Pathfinding decisions | No | Real-time, deterministic required |
| Threat detection | No | Real-time, deterministic required |
| Happiness factor lookup | No | Direct API query, no language needed |
| Research prerequisite check | No | Direct API query, deterministic |
| Intent classification (follow/stop) | Yes | Language-agnostic FOLLOW / STOP / NONE classification needed for i18n |

### 7.3 System Prompt Structure

- Role identity, speech style, and knowledge scope
- Current environmental context payload (Section 3.7)
- Relevant recent colony events (filtered to most contextually appropriate, last 5–10)
- Current task status if applicable
- Instruction to stay in character and deflect out-of-scope requests warmly
- Locale directive: always respond in the player's configured Minecraft client locale

### 7.4 Immediate Acknowledgment Pattern

Because LLM response latency creates dead silence, every interaction triggers an immediate template acknowledgment before the async LLM call resolves:

| Role | Acknowledgment |
|---|---|
| Ranch Hand | *"On it, boss. Give me a moment."* |
| Scout | *"Checking that now."* |
| Advisor | *"Let me review the colony data."* |
| Planner | *"Analyzing. Stand by."* |

> **Note:** *"Hmm..."* has been removed from the standard acknowledgment path. It is retained only as a timeout fallback — if the actual timeout threshold is exceeded, the NPC fires a brief in-character response. Otherwise silence until the LLM responds.

**Response Delivery — Short vs Long:**

Short and long responses are routed differently based on a configurable character threshold. This applies to the Advisor at all states.

- **Short** (below threshold): delivered to public chat as `[CitizenName]: [response text]`. In `PRE_COLONY` and `COLONY_NO_CITIZEN` states: `Advisor: [response text]`.
- **Long** (at or above threshold): public whisper template fires instantly (never LLM-generated); full response text delivered privately to the triggering player only.

| State | Public Whisper Template Pool |
|---|---|
| `COLONY_WITH_CITIZEN` | "[CitizenName] whispers something to [PlayerName]." / "[CitizenName] leans over and murmurs to [PlayerName]." / "[CitizenName] speaks quietly with [PlayerName]." |
| `COLONY_NO_CITIZEN` | "The advisor whispers something to [PlayerName]." / "The advisor murmurs quietly to [PlayerName]." |
| `PRE_COLONY` | "Your advisor murmurs something to you." / "The book rustles quietly near [PlayerName]." |

**Threshold:** `ADVISOR_WHISPER_THRESHOLD` config value. Default: 120 characters.

**Server operator override:** `ADVISOR_FORCE_PRIVATE` forces all responses to private delivery regardless of length. Public whisper still fires so nearby players have a visual cue — only the full response text is suppressed from public chat.

### 7.5 Fallback Behavior

- If OpenRouter is unreachable or times out: fall back to template pool responses silently
- Do not surface errors to the player in immersion-breaking ways
- Log connection failures for server operator review

---

## 8. Operating Modes, Diagnostic Data & Dashboard

### 8.1 Lite Mode vs Full Mode

The mod detects its operating mode at startup based on API key validity. Mode is set once and does not change during a session.

**Mode detection:**
- API key present, non-blank, non-placeholder → Full mode
- API key absent, blank, or placeholder value → Lite mode
- No crash. No fail-to-start. Mode flag is set and respected throughout.
- The `IllegalStateException` in `DragonTweaks.java` `commonSetup()` must be replaced with a mode flag assignment. Do not throw on missing key.

**One-time lite mode notification:** On first world load in lite mode, deliver a single chat message to the player: *"Assistant Mod is running in Lite mode. Add an OpenRouter API key in config to unlock full AI companion features."* Fires once per player. Does not repeat on subsequent loads.

**Full mode:** All features described in this document apply without restriction.

**Lite mode — what exists:**
- Colony diagnostic data layer (`ColonyDiagnosticReport` — see Section 8.2)
- `/assistant advisor` command — opens Advisor diagnostic panel
- `/assistant planner` command — opens Planner dependency chain panel
- Both panels read from cached `ColonyDiagnosticReport`

**Lite mode — what does not exist:**
- No entities of any kind. No floating books, no book-and-quill, no flavor NPCs, no shadow entities. Entity presence without LLM response capability is visual noise with no gameplay value.
- No citizen role assignment. The role assignment UI does not open in lite mode. `CitizenInteractDetector` must check mode flag and return early if lite mode is active.
- No Ranch Hand or Scout roles. Physical world interaction without personality is meaningless.
- No Advisor or Planner shadow entities following the player. Panel access is command-driven only.
- No pre-colony floating book. No build tool hotbar trigger.
- No whisper pattern. No LLM acknowledgment messages. No template responses.

**Lite mode commands:**
- `/assistant advisor` — opens Advisor diagnostic panel
- `/assistant planner` — opens Planner dependency chain panel
- `/assistant` with no subcommand — prints available commands to player

Both panel commands are also available in full mode as supplementary views. They are not lite-mode exclusive, but they are lite mode's primary interface.

---

### 8.2 Colony Diagnostic Report (`ColonyDiagnosticReport`)

The central diagnostic data object. Generated by the same code regardless of mode. Both the LLM context injection (full mode) and the dashboard panels (both modes) read from this object.

**Generation:**
- Async. Never on the main game thread.
- Cached with a short TTL. Suggested default: 30 seconds. Config value.
- Cache invalidated early on relevant colony events: `BuildingConstructionModEvent`, `CitizenJobChangedModEvent`, `CitizenAddedModEvent`, `CitizenDiedModEvent`
- Both panels and LLM context always read from cache. Never re-poll on demand.

**Contents:**
- Colony metadata: name, citizen count, housing cap, Town Hall level, overall happiness score
- Per-citizen records: name, job, work building position, home building position, commute distance (derived), full happiness factor breakdown (all ten canonical factor IDs with factor value and weight), red/yellow flag per factor
- Flagged issues list: ranked by severity, each issue citing the specific API value that triggered it
- Actionable recommendations list: each includes full dependency chain; blocking dependencies surfaced first
- Research snapshot: completed list, in-progress list with progress in ticks, not-started status for known prerequisite researches
- Building inventory: all buildings with level, built status, pending construction status, assigned worker
- Environmental context: `doDaylightCycle` state, time of day, weather, `ThreatLevel`

**Full mode use:** Serialized into structured context block injected into LLM system prompt. LLM expresses findings conversationally in character.

**Lite mode use:** Rendered directly as dashboard panel UI. No language generation.

---

### 8.3 Dashboard Panels

Two panels. Both read from `ColonyDiagnosticReport`. Opened via command in lite mode; also accessible via command in full mode as a supplementary view.

#### Advisor Panel
Displays citizen-level happiness diagnostics and colony health overview.

Content (detail design session required before implementation):
- Colony health summary header
- Per-citizen happiness breakdown — all ten factors, red/yellow flagged
- Commute distance per citizen, flagged if over threshold
- Prioritized issue list with severity indicators
- Environmental warnings (`doDaylightCycle` false, active raid, etc.)

#### Planner Panel
Displays dependency chain analysis and build recommendations.

> **NOTE:** Planner panel content requires a dedicated design session before implementation. Do not implement panel content until that session has occurred and this section is updated with locked decisions.

Content (pending design session):
- Build recommendations with full dependency chains
- Research prerequisite status
- Worker and bed availability
- Current work order queue
- Blocking dependency surfaced first for each recommendation chain

---

### 8.4 Development Workflow Note

In lite mode during development, the dashboard panels serve as the primary integration test harness for the diagnostic data layer. Verify panel data is correct before wiring LLM responses to `ColonyDiagnosticReport` output. A correct panel in lite mode means the data layer is trustworthy. LLM integration then becomes a presentation concern only, not a data concern.

---

## 9. Interaction Model

### 9.1 Command Detection

- Proximity threshold: 10 blocks XZ radius, ±5 blocks Y tolerance (cylindrical, not spherical)
- Detection radius and command radius are the same value — one config entry, not two
- Never hardcoded — all proximity checks read from config at runtime
- If multiple role-assigned citizens are in range, nearest one responds
- Commands parsed for intent before execution or deflection
- Unrecognized commands fall through to general chatter / LLM

### 9.2 Response Pipeline

1. Player chat message detected within proximity
2. Build `EnvironmentalContext` payload (including `doDaylightCycle` state)
3. Classify interaction: role command / out-of-role / general chatter / greeting
4. Role command → capability check → execute or deflect
5. Execute: send immediate acknowledgment, begin async execution
6. General chatter → send immediate acknowledgment, fire async LLM call
7. LLM response queued back to main thread via server tick queue, delivered via in-world chat

### 9.3 Greeting System (Template-Based, No LLM)

| Time | Ranch Hand | Scout | Advisor | Planner |
|---|---|---|---|---|
| Morning | *"Mornin' boss, critters are already up."* | *"Early start. Good. Been up since third watch."* | *"Good morning. I've prepared a summary of overnight developments."* | *"Morning. I've updated the task queue."* |
| Afternoon | *"Afternoon. Hot one today."* | *"Quiet so far. Almost too quiet."* | *"Have you reviewed my recommendations?"* | *"Three tasks pending your approval."* |
| Evening | *"Sun's going down. Heading in soon."* | *"Getting dark. I'll stay out a bit longer."* | *"The colony's numbers look better today."* | *"Tomorrow's schedule is prepared."* |
| Night | *"Shouldn't you be sleeping, boss?"* | *"You're up late. Something wrong?"* | *"Burning the midnight oil? So am I."* | *"Night work is inefficient. But here we are."* |

### 9.4 Locale System

- NPC response language is driven by the Minecraft client locale setting (`en_us`, `ja_jp`, `ru_ru`, `zh_cn`, etc.)
- NPC understands any language; always responds in the configured locale
- `/assistant locale [code]` — debug command; injects locale string into session context; unrecognized codes fall back to `en_us` with logged warning; `/assistant locale reset` clears override
- Auto-detect via `Minecraft.getInstance().getLanguageManager().getSelected()` transmitted server-side via packet on session init (deferred — NeoForge 1.21.1 API path needs verification)

---

## 10. Development Phases

The PoC is complete. The mod has a working LLM backend (OpenRouter), flavor NPC entity with follow/stop behavior, conversation memory with NBT persistence, proactive environmental observations, role-differentiated personas, and `SavedData` role assignment storage wired and confirmed on world load.

Current work is Phase 1 — building the citizen interaction and role assignment system.

### PoC — Complete ✅

- Stationary NPC intercepts nearby player chat
- Async OpenRouter request fires, response delivered in-character
- Timeout fallback fires silently — no immersion break
- Game thread never blocks
- Flavor NPC entity with follow/stop behavior
- Conversation memory with NBT persistence across sessions
- Proactive environmental observations (threat state flip model)
- Role-differentiated LLM personas

### Phase 1 — Foundation (Active)

- ~~LLM backend (OpenRouter)~~ ✅ Complete
- ~~Flavor NPC entity~~ ✅ Complete
- ~~Conversation memory~~ ✅ Complete
- ~~Proactive observations (`ObservationTicker`)~~ ✅ Complete
- ~~`RoleAssignmentData` SavedData~~ ✅ Complete — wired and confirmed in-game
- `/assistant locale [code]` debug command
- "Hmm..." acknowledgment refactor (retain as timeout fallback only)
- LLM hard requirement enforcement (fail to start without valid API key)
- SavedData persistence smoke test (write + JVM restart + confirm survival)
- `CitizenInteractDetector` — right-click citizen detection
- Network packets (server→client: citizen info + slots; client→server: role selection)
- `RoleAssignmentScreen` — role selection UI
- `/assistant revoke <name>` command
- `CitizenJobChangedModEvent` listener — role revocation on job assignment
- `CitizenDiedModEvent` listener — role slot release on citizen death

### Phase 2 — Ranch Hand (Planned)

- Shadow entity framework (intangible, invisible)
- Mob scanning (AABB search)
- Pathfinding to target mob
- Programmatic lead attachment
- Return-to-point behavior
- Fence post lead attachment
- Completion reporting in character
- Shadow entity multiplayer visibility test

### Phase 3 — Scout (Planned)

- Perimeter patrol path generation (auto-generated default, player override TBD)
- Hostile mob detection within radius
- Threat location calculation and reporting
- Periodic check-in system
- Threat level assessment

### Phase 4 — MineColonies Deep Integration (Planned)

- Colony state polling using verified API (Section 4)
- Per-citizen happiness factor iteration (all 10 canonical factor IDs)
- Commute distance calculation (derived metric, work pos − home pos)
- Red vs. yellow factor distinction before escalating recommendations
- `doDaylightCycle` precondition check before any happiness diagnosis
- Research tree queries for Planner dependency chain validation
- Colony event queue integration (births, deaths, raids, completions)
- Colony food store API access (currently unverified — audit before Phase 4)

### Phase 5 — Planner & Dependency Chains (Planned)

- Research prerequisite validation before any build recommendation
- Full dependency chain modeling including University level + building level prereqs
- Worker and bed availability checks before staffing recommendations
- Build order optimization
- Crisis response sequencing
- Resource forecasting
- LLM-assisted plan generation with colony state context

### Phase 6 — NPC Cross-Awareness & Polish (Planned)

- NPC cross-awareness (NPCs aware of each other's state)
- Flavor NPC patrol routes and idle routines (design session needed before implementation)
- Flavor NPC spawn cap (value TBD)
- Mod final name (working title: The Assistant Mod)

---

## 11. Open Questions & Decisions Pending

| Question | Status | Notes |
|---|---|---|
| `CitizenJobChangedModEvent` — confirmed public? | ✅ Confirmed | Use for role revocation trigger |
| Citizen happiness values queryable? | ✅ Confirmed | Full named modifier system verified; all 10 factor IDs documented |
| Is commute a happiness factor? | ❌ Not a factor | No native commute modifier. Advisor derives from building positions. |
| `greatfood` modifier — canonical ID? | ⚠️ Unverified | Appears in registry but no string constant confirmed. Low priority. |
| Research tree exposed via API? | ✅ Confirmed | Full `ILocalResearchTree` API verified; three-state model documented |
| Proximity threshold for command detection? | ✅ Decided | 10 blocks XZ, ±5 Y (cylindrical) |
| Role slots expandable via building? | ✅ Decided | Tied to Town Hall level — no separate building |
| Citizen death — role handling? | ✅ Designed | `CitizenDiedModEvent` available; revoke and notify flow defined |
| Shadow entity visible to other players? | TBD | Test in Phase 2 |
| Maximum scan radius for Ranch Hand? | TBD | Performance vs. utility tradeoff; test in Phase 2 |
| Scout patrol: player-defined vs auto-generated? | TBD | Default to auto with player override; design in Phase 3 |
| Colony food store — public API access? | ⚠️ Unverified | Not yet confirmed; needed for `FoodStatus` context field in Phase 4 |
| Flavor NPC spawn cap value? | TBD | Needed before Phase 2 to prevent server performance issues |
| Mod final name? | TBD | Working title: The Assistant Mod |
| Locale auto-detect API path (NeoForge 1.21.1)? | TBD | Verify `LanguageManager` path before implementing |

---

## 12. Technical Notes

### 12.1 Target Environment

| Parameter | Value |
|---|---|
| Minecraft | 1.21.1 |
| Mod Loader | NeoForge 21.1.226 |
| MineColonies | Latest compatible with 1.21.1 |
| Java | 21+ |
| LLM Backend | OpenRouter — `google/gemma-4-26b-a4b-it` |

### 12.2 Key Risks

| Risk | Severity | Mitigation |
|---|---|---|
| Game thread blocking from OpenRouter HTTP call | Critical | Mandatory async from day 1; hard architectural rule |
| MineColonies API coverage gaps (e.g. food stores) | High | Audit each API dependency before writing dependent code |
| LLM latency degrading immersion | Medium | Immediate acknowledgment pattern masks latency; template fallback on timeout |
| Shadow entity pathfinding conflicting with MC world modifications | Medium | Test shadow entity behavior early in Phase 2 |
| MineColonies version updates changing API | Medium | Abstract all MC/colony calls behind interfaces |
| `greatfood` modifier ID unconfirmed | Low | Omit until ID confirmed; does not block any critical path |
| NeoForge 1.21.1 class regressions from 1.20.x | High | Verify all vanilla/NeoForge class names against 1.21.1 sources before use — `HumanoidMob` does not exist in 1.21.1; `PathfinderMob` is correct |

### 12.3 Advisor State Contract

The following are implementation requirements, not guidelines. An Advisor that violates them will lose player trust and become unused.

| Requirement | Priority |
|---|---|
| Persistent memory of player decisions across sessions | Critical |
| Observe → Diagnose → Recommend sequence enforced in code, not convention | Critical |
| Full dependency chain validation before any Planner recommendation | Critical |
| Validate worker and bed availability before any build recommendation | Critical |
| Research prerequisites as first-class dependencies in every build chain | Critical |
| Never re-ask confirmed information within or across sessions | Critical |
| Never doubt or re-investigate player-confirmed facts | Critical |
| Precise distance calculations from API coordinates (not estimates) | High |
| Per-citizen happiness factor breakdown, not colony averages | High |
| Distinguish red vs. yellow happiness factors before escalating | High |
| Check `doDaylightCycle` before any happiness diagnosis | High |
| Explicit uncertainty when API data unavailable (never guess) | High |
| Pattern recognition: uniform red happiness → check systemic causes first | High |
| Surface longest blocking dependency first in build path recommendations | High |
| Log colony state at time of each recommendation | Medium |

### 12.4 Session Continuity

Cognitive entropy is real in extended AI-assisted development sessions. Recommended practices:

- Keep Claude Code sessions short and focused — one deliverable per session
- Restate hard architectural rules at the start of every implementation session
- Verify all generated code respects the single-threaded game loop before accepting it
- Update `devchat.md` with decisions made at the end of each session
- Verify all NeoForge 1.21.1 class names against decompiled sources before use — do not assume 1.20.x names carry over

---

*End of Design Document v0.3 · Converted from v0.2 docx · Updated for OpenRouter backend, two-tier NPC architecture, Town Hall slot system, and actual build state as of 2026-05-01 · v0.3.1 additions: Advisor state machine, Lite/Full mode, ColonyDiagnosticReport, Dashboard Panels, Ranch Hand sighting memory — 2026-05-07*
