# Accessibility Navigation Mod — Design Notes

## Working Concept

A Minecraft 1.21.1 NeoForge client-side accessibility navigation mod designed to help visually impaired players stay oriented, return to known locations, and safely traverse confusing terrain.

The goal is not to create a cheat mod or full Baritone replacement. The goal is to provide route visibility, orientation, recovery assistance, and limited traversal help when terrain or visual impairment creates unnecessary navigation difficulty.

Core principle:

```text
Help the player understand and safely follow a route.
Do not play the game, mine resources, fight, loot, or reveal hidden information.
```

---

## Primary Use Cases

### 1. Return to Start / Marker

The player marks a starting point before entering a cave, swamp, forest, dungeon, ravine, or other confusing area.

The mod can later show a route back to that marker.

Possible marker types:

- Manual keybind marker
- Named waypoint
- Death point
- Bed/spawn point if available
- Physical anchor such as torch-on-fencepost, though the mod should not depend on detecting this pattern

Recommended first version:

```text
Press key: Start trail / Set marker
Press key: Toggle return overlay
Follow rendered trail or route back
```

---

### 2. Breadcrumb Trail

Instead of solving full pathfinding immediately, the mod can record where the player has already walked.

When return mode is enabled, it renders the trail backward.

This is highly reliable because it does not need to infer a path through unknown terrain.

Breadcrumb behavior:

- Record position every few blocks or every few seconds
- Store dimension + coordinates + order
- Render dots/line backward toward the start
- Show distance to next breadcrumb and Y-level difference
- Optional audio cue when off trail

This is likely the best initial accessibility feature.

---

### 3. Route Overlay / Debug Line

The visible line is initially an accessibility aid, but architecturally it should be treated as a debug/rendering consumer of a reusable navigation core.

Potential overlay types:

- Thick high-contrast world-space line
- Large glowing breadcrumb dots
- HUD arrow toward next route point
- Distance indicator
- Y-level indicator
- Optional beam at final marker

Example HUD:

```text
Return marker: 84m NW, +12Y
Next point: 6m ahead, +1Y
```

---

## Long-Term Architecture

The mod should be built as a reusable navigation/pathfinding core with multiple target types.

```text
Navigator Core
├─ TargetResolver
│  ├─ marker target
│  ├─ waypoint target
│  ├─ death point target
│  ├─ biome target
│  ├─ village/structure target
│  └─ coordinate target
│
├─ WorldScanner
│  ├─ loaded chunk cache
│  ├─ block passability
│  ├─ danger detection
│  └─ terrain cost map
│
├─ PathPlanner
│  ├─ A* pathfinding
│  ├─ Dijkstra fallback
│  ├─ hierarchical/chunk pathing later
│  └─ path smoothing
│
├─ SafetyRules
│  ├─ avoid lava
│  ├─ avoid void/deep falls
│  ├─ avoid fire
│  ├─ avoid drowning
│  ├─ avoid hostile mobs if possible
│  └─ avoid forbidden blocks
│
├─ RouteRenderer
│  ├─ debug line
│  ├─ breadcrumb dots
│  ├─ HUD arrow
│  └─ distance/Y indicator
│
└─ Executor
   ├─ display-only mode
   ├─ assisted navigation mode
   └─ limited traversal assist later
```

---

## Target Abstraction

The pathfinder should not care whether the destination is a marker, waypoint, biome, village, or death point.

Suggested abstraction:

```java
interface PathTarget {
    Optional<BlockPos> resolve(ClientLevel level, LocalPlayer player);
    String displayName();
}
```

Possible implementations:

```text
MarkerTarget
DeathPointTarget
WaypointTarget
CoordinateTarget
SpawnTarget
VillageTarget
BiomeTarget
StructureTarget
EntityTarget
```

Recommended target rollout order:

1. Manual marker
2. Death point
3. Named waypoint
4. Last bed/spawn if available
5. Breadcrumb trail return
6. Nearby known village/structure
7. Biome/structure search later

Biome and structure targets should be delayed because they may require server-side locate data, permissions, or chunk scanning.

---

## Capability Levels

### Level 0 — Direction Only

No real pathfinding.

Features:

- Compass arrow
- Distance
- Y-level difference
- Marker beam

Useful for:

- Waypoints
- Death points
- General orientation

---

### Level 1 — Walkable Route Through Known/Loaded Terrain

Basic pathfinding through existing terrain.

Allowed:

- Walking
- Step up one block
- Safe drops
- Existing stairs/slabs

Not allowed:

- Digging
- Placing blocks
- Bridging
- Mining
- Swimming/ladders initially, unless explicitly implemented

This is the realistic first pathfinding MVP.

---

### Level 2 — Vertical Navigation

Adds support for:

- Stairs
- Ladders
- Vines
- Water elevators
- Safe drops
- Short climbs

Important for caves, ravines, mineshafts, and vertical terrain.

---

### Level 3 — Limited Traversal Modification

Adds limited block breaking and placement for accessibility traversal only.

Allowed examples:

- Break grass/leaves/vines
- Break dirt/sand/gravel/clay/snow
- Break/chop wood/logs/planks/fences if necessary
- Place dirt/planks/cobble-style blocks as step assists
- Short bridges only under strict rules
- Pillar recovery after falls

Forbidden examples:

- Ore targeting
- Strip mining
- Hidden resource search
- Combat automation
- Auto-looting
- Breaking stone-tier-or-higher blocks
- Breaking machines, containers, storage, utility blocks

---

### Level 4 — Full Automation

Baritone-like behavior.

Includes:

- Full auto-movement
- Mining
- Bridging
- Pillaring
- Tool choice
- Inventory management
- Dynamic recovery

This should not be an early goal. It is complex and risks moving away from accessibility assistance.

---

## Accessibility Versus Cheat Boundary

Clear design boundary:

```text
The mod helps the player navigate.
It does not seek resources, reveal hidden information, fight, loot, or optimize gameplay rewards.
```

Allowed:

- Mark location
- Show compass arrow
- Show distance and Y difference
- Render breadcrumb trail already walked
- Render path through known/loaded terrain
- Suggest safe route
- Suggest minor terrain corrections
- Assist recovery from falls

Avoid or forbid:

- Ore finding
- X-ray behavior
- Hidden chest detection
- Combat automation
- Auto-looting
- Auto-mining resources
- Long-distance tunneling
- Resource-targeted pathing

---

## Block Breaking Policy

Block breaking is allowed only as a traversal fallback, not as resource acquisition.

Core rule:

```text
The pathfinder may break blocks only to restore traversal, never to seek resources.
```

Hard gate:

```text
Only blocks breakable by hand or with wood-tier tools are eligible.
Anything requiring stone-tier or better is forbidden.
```

Allowed block categories:

- Grass
- Tall grass
- Leaves
- Vines
- Snow
- Dirt
- Sand
- Gravel
- Clay
- Logs
- Planks
- Fences, possibly

Forbidden block categories:

- Stone
- Deepslate
- Ores
- Metal blocks
- Machines
- Containers
- Storage blocks
- Utility blocks
- Claimed/protected blocks if detectable
- Anything valuable or structure-critical

Example logic:

```java
boolean canBreakForTraversal(BlockState state, ToolProfile toolProfile) {
    return !state.isAir()
        && isNonValuableObstacle(state)
        && requiresAtMostWoodTool(state)
        && !isContainerOrMachineOrClaimSensitive(state)
        && !isOreOrResourceBlock(state);
}
```

---

## Movement and Action Cost Model

Costs should make the mod prefer normal movement, then minor corrections, then construction only when useful.

Suggested base costs:

```text
Walk 1 block:                         1
Step up/down existing terrain:         2
Safe drop 1–2 blocks:                  3
Break grass/leaves/vines/snow:         4–8
Place block as step-assist:            4–6
Place block for short stair/pillar:    6–10
Break dirt/sand/gravel/clay:           8–15
Break wood/log/planks/fence:           15–25
Place horizontal bridge block:         18–35
Stone/deepslate/ore breaking:          forbidden
Lava/fire/void/deep fall:              forbidden
```

Important distinction:

```text
Placing a dirt block to climb a steep slope is not the same as building a bridge.
```

Step-assist placement should be relatively cheap. Horizontal bridging should be much more expensive and tightly constrained.

---

## Route-Efficiency Gate for Major Construction

Major constructive traversal, especially bridging, should only be considered when the passive route is disproportionately inefficient.

Rule:

```text
Only consider major construction if the passive route cost is more than about 4x the direct distance.
```

Example:

```text
Direct distance A→B: 40 blocks
Best walk-only route: 190 movement-cost
Ratio: 4.75x
Result: evaluate bridging/pillaring/soft modification options
```

Pseudo-logic:

```java
Route passive = findRoute(profile.withNoConstruction());

double ratio = passive.cost() / directDistance(start, target);

Profile effectiveProfile = profile;

if (ratio > 4.0) {
    effectiveProfile = profile.withMajorConstructionEnabled();
} else {
    effectiveProfile = profile.withOnlyMinorAssistEnabled();
}

Route finalRoute = findRoute(effectiveProfile);
```

Final validation:

```java
if (finalRoute.bridgeBlocksPlaced() > maxBridgeBlocks) reject;
if (finalRoute.longestBridgeSpan() > maxBridgeSpan) reject;
if (finalRoute.breaksForbiddenBlocks()) reject;
if (finalRoute.crossesLavaOrVoid()) reject;
```

---

## Minor Assist Versus Major Assist

Two separate budgets should exist.

### Minor Assist

Allowed even without route inefficiency threshold.

Examples:

- Place 1–3 step blocks
- Clear grass/leaves
- Break one or two soft blocks
- Step-assist up steep terrain

### Major Assist

Only allowed when passive route is inefficient, such as more than 4x direct distance.

Examples:

- Horizontal bridging
- Repeated pillaring
- Cutting through trees
- Multiple block placements across a gap

---

## Bridging Policy

Bridging is possible but should be treated as a high-control traversal primitive.

Human-follow mode:

```text
Show bridge suggestion, but do not place blocks.
```

Assist mode:

```text
Place limited bridge blocks only while navigating to a known destination and only while the assist key is held.
```

Recommended bridge constraints:

- Only toward known non-resource target
- Only when passive route is inefficient
- Max bridge span, e.g. 5 blocks by default
- Max total bridge blocks per route, e.g. 8 blocks by default
- Use only disposable blocks
- Never use valuable blocks
- Avoid gravity blocks such as sand/gravel for bridging unless explicitly enabled
- Bridge over lava disabled by default
- Bridge over void disabled by default
- Stop on damage, mob threat, manual override, or key release

Suggested bridge action cost:

```text
Place horizontal bridge block: 18–35
Bridge length penalty: nonlinear
```

Example bridge cost curve:

```text
1 block bridge: 20
2 block bridge: 45
3 block bridge: 75
4 block bridge: 110
5 block bridge: 150
```

---

## Pillaring and Step Placement

Pillaring and step placement are usually less egregious than bridging.

Examples:

- Placing dirt blocks to climb a steep ravine wall
- Placing blocks underfoot to return to a previous ledge
- Stair-stepping up terrain that is otherwise awkward but nearby

These should be cheaper than bridging.

Suggested costs:

```text
Place block as step-assist:          4–6
Place block for short stair/pillar:  6–10
Horizontal bridge block:             18–35
```

Default constraints:

```text
Max step-assist placements per route: 12
Max normal pillar height: 4
Longer recovery pillar allowed only under fall recovery rules
```

---

## Fall Recovery / Displacement Recovery

This should be a distinct behavior from normal pathfinding.

Use case:

- Player falls into a hole
- Creeper blows out the floor
- Gravel/sand collapses
- Player drops into ravine/cavern/mineshaft
- Fall is survivable, e.g. around 10 blocks
- The safest return is often to pillar back up to the previous known-safe position

Principle:

```text
Prefer returning to a recent known-safe state over exploring unknown terrain.
```

Detection logic:

```text
Track recent safe positions every 1–2 seconds.
If player drops more than 4 blocks suddenly:
  mark last stable position as RecoveryAnchor.
If current position is below RecoveryAnchor:
  evaluate vertical recovery.
If height difference <= maxRecoveryPillarHeight:
  suggest or execute pillar-up recovery.
Else:
  fall back to normal pathfinding.
```

Fall recovery should not require the 4x route inefficiency test.

It is triggered by sudden displacement, not by route optimization.

Allowed when:

- Target is a recent safe anchor
- Vertical distance is within configured max
- Current area is not lava/fire/void
- Player has allowed disposable blocks
- No immediate hostile threat, or player confirms
- Player holds assist key in assisted mode

Suggested costs:

```text
Fall recovery pillar: 3–5 per vertical block
Normal walk:          1 per block
Bridge:               18–35 per horizontal gap block
```

So a 10-block recovery pillar may cost around 40, which is usually preferable to a long unsafe cave reroute.

Suggested action type:

```java
enum AssistAction {
    ROUTE_FOLLOW,
    STEP_ASSIST,
    SOFT_BLOCK_CLEAR,
    BRIDGE_GAP,
    FALL_RECOVERY_PILLAR
}
```

---

## Fall Damage Safety Assumptions

Normal player fall damage:

```text
0–3 blocks: no damage
4 blocks: 1 HP / half heart
Each additional block: +1 HP / half heart
22 blocks: normally survivable at full health, leaves about half a heart
23+ blocks: normally lethal without mitigation
```

Pathfinding safety thresholds should be stricter than theoretical survival.

Suggested defaults:

```text
0–3 block drop: safe
4–6 block drop: acceptable but not preferred
7–10 block drop: high cost
11+ block drop: avoid unless emergency/recovery
20+ block drop: forbidden
```

Mitigating factors that may change this:

- Feather Falling
- Protection
- Slow Falling
- Resistance
- Water
- Ladders/vines
- Slime/hay/honey-like landing blocks
- Modded effects or armor

The mod should initially assume no mitigation unless it explicitly detects it.

---

## Assist Modes

### Passive Mode

```text
Render route only.
No block breaking.
No block placing.
No movement control.
```

### Gentle Assist

```text
Suggest soft-block clearing and minor step placement.
No automatic block placement or movement.
```

### Hands-On Assist

```text
While assist key is held:
  may walk/jump
  may place minor step-assist blocks
  may perform short recovery pillar
  may place limited bridge blocks if route-efficiency gate allows it
Release key:
  stop immediately
```

### Emergency Assist

```text
Higher limits for recovery.
Still forbids stone-tier mining, ore targeting, lava bridging, void bridging, combat, looting, and resource search.
```

---

## Dead-Man Switch / Safety Controls

Any automated movement or placement should require active player consent.

Recommended controls:

- Hold key to assist
- Release key to stop instantly
- Manual movement input pauses assist
- Mouse movement/manual camera override pauses assist
- Damage taken stops assist
- Hostile mob nearby pauses or warns
- Lava/fire/void nearby pauses or warns
- Inventory lacks safe block: stop and warn
- Lost target/path invalid: stop and warn

This keeps the player in control.

---

## Default Configuration Proposal

```text
Route inefficiency threshold for major construction: 4.0x
Minor step-assist: enabled
Max step-assist placements per route: 12
Max normal pillar height: 4
Max fall recovery pillar height: 15
Max bridge span: 5
Max total bridge blocks: 8
Bridge over lava: disabled
Bridge over void: disabled
Break blocks harder than wood-tier: forbidden
Ore/resource targeting: forbidden
Auto-looting: forbidden
Combat automation: forbidden
```

---

## Suggested Commands / Keybinds

Commands:

```text
/nav mark <name>
/nav to <name>
/nav to death
/nav to x y z
/nav clear
/nav trail start
/nav trail stop
/nav trail return
/nav mode passive|gentle|assist|emergency
```

Keybinds:

```text
F8  Start/stop breadcrumb recording
F9  Toggle return overlay
F10 Clear current route/trail
Hold key: Hands-on assist / dead-man switch
```

Final keys should be configurable.

---

## MVP Recommendation

Do not start with biome/village/path-to-anything.

Start with the smallest useful version:

```text
Client-side NeoForge 1.21.1 mod
Manual marker
Breadcrumb recording
Route/trail overlay
HUD arrow + distance + Y difference
No movement automation
No block breaking
No block placing
```

Second version:

```text
Pathfinding to known BlockPos inside loaded/known terrain
Display-only route overlay
Basic safety filtering
```

Third version:

```text
Minor assist suggestions
Soft block clearing suggestions
Step-assist suggestions
Fall recovery detection
```

Fourth version:

```text
Hands-on assist mode with dead-man switch
Limited step placement
Limited recovery pillaring
Strict bridge rules
```

---

## Key Design Insight

The first useful mod does not need to solve all pathfinding.

For the core accessibility problem:

```text
You do not need to calculate the way out if you record the way in.
```

Breadcrumb trails solve the immediate “I forgot how to get back” problem with far less complexity than full pathfinding.

The long-term design should still keep a reusable navigation core so the mod can later support markers, death points, villages, biomes, structures, and assisted traversal.
