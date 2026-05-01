# Stub Library Index

This file serves as a high-level directory for the read-only stub files located in the `docs/stubs/` directory.

Because of the large volume of files (1,574 stubs), these are not automatically loaded into context. Use this index to identify the correct file, then load only what is directly relevant to the current task.

## Usage Instructions for Claude Code

1. **Search:** Find the relevant category below for the API you need.
2. **Load:** Use the path shown to read the specific stub file(s) needed.
3. **Context discipline:** Only load stubs directly relevant to the current task. Do not bulk-load categories.

---

## MineColonies API — `docs/stubs/com/minecolonies/api/`

### Top-Level Entry Points
- `IMinecoloniesAPI.java` — Main API interface; colony access, citizen queries, building lookups
- `MinecoloniesAPIProxy.java` — Static proxy to get the active `IMinecoloniesAPI` instance

### Advancements & Triggers — `advancements/`
- `AdvancementTriggers.java` — Registry of all advancement triggers
- `AllTowersTrigger.java`, `ArmyPopulationTrigger.java`, `ColonyPopulationTrigger.java` — Population/army triggers
- `BuildingAddRecipeTrigger.java`, `CompleteBuildRequestTrigger.java`, `CreateBuildRequestTrigger.java` — Building triggers
- `CitizenBuryTrigger.java`, `CitizenEatFoodTrigger.java`, `CitizenResurrectTrigger.java` — Citizen lifecycle triggers
- `ClickGuiButtonTrigger.java`, `OpenGuiWindowTrigger.java` — UI interaction triggers
- `DeepMineTrigger.java`, `MaxFieldsTrigger.java`, `PlaceStructureTrigger.java`, `PlaceSupplyTrigger.java`, `UndertakerTotemTrigger.java` — Misc triggers

### Blocks & Tile Entities — `blocks/`, `tileentities/`
- `blocks/AbstractBlockHut.java` — Base class for all hut blocks
- `blocks/AbstractBlockBarrel.java`, `AbstractBlockMinecoloniesRack.java` — Storage blocks
- `blocks/AbstractBlockMinecolonies*.java` — Base block variants (container, directional, falling, grave, horizontal)
- `blocks/AbstractColonyBlock.java` — Colony-owned block base
- `blocks/ModBlocks.java` — Registry of all MineColonies blocks
- `blocks/decorative/` — Gate, construction tape, colony flag banner
- `blocks/huts/AbstractBlockMinecoloniesDefault.java` — Default hut block
- `blocks/interfaces/` — `IBlockMinecolonies`, `IBuildingBrowsableBlock`, `IRSComponentBlock`, `ITickableBlockMinecolonies`
- `blocks/types/` — `BarrelType`, `GraveType`, `RackType` enums
- `tileentities/` — Barrel, grave, plantation, rack, scaffold, warehouse tile entities

### Client & Rendering — `client/`
- `client/ModKeyMappings.java` — Key bindings
- `client/render/modeltype/` — `AmazonModel`, `CitizenModel`, `EgyptianModel`, `IModelType`, `ISimpleModelType`

### Colony & Building Management — `colony/`, `colony/buildings/`
- `colony/` — Colony state, permissions, claims, connections, progress, chunk claims
- `colony/buildings/` — Building interfaces, modules, worker buildings, views, registry
- `colony/buildingextensions/` — Extension modules and registries
- `colony/requestsystem/` — Request/delivery system, tokens, factories, resolvers

### Citizens & Happiness — `entity/citizen/`, `entity/citizen/happiness/`
- `entity/citizen/` — `ICitizenData`, citizen skills, status, attributes
- `entity/citizen/happiness/` — Happiness modifiers and modifier registries

### Entity AI & Mobs — `entity/ai/`, `entity/mobs/`, `entity/pathfinding/`
- `entity/ai/` — State machines, AI targets, worker AI utility
- `entity/mobs/` — Raider types, mob AI registries, specific mob types
- `entity/pathfinding/` — Navigation interfaces, path job types

### Events — `eventbus/`
- `eventbus/` — MineColonies mod event bus, colony events, citizen events

### Inventory & Items — `inventory/`, `items/`
- `inventory/` — Container and inventory interfaces
- `items/` — Component data, item interfaces, `ModItems`, item tags

### Quests & Research — `quests/`, `research/`
- `quests/` — Quest objectives, dialogue trees, rewards, registries
- `research/` — Research providers, effects, requirements, registry *(key for Planner role)*

### Utilities & Others
- `compatibility/` — Third-party mod compatibility interfaces
- `configuration/` — Config value interfaces
- `crafting/` — Crafting recipe interfaces and registries
- `creativetab/` — Creative tab registration
- `enchants/` — Custom enchantment interfaces
- `equipment/` — Equipment type interfaces
- `loot/` — Loot table interfaces
- `sounds/` — Sound events and managers
- `util/` — BlockPos utilities, NBT helpers, math/world utilities

---

## NeoForge API — `docs/stubs/net/neoforged/neoforge/`

### Common API — `common/`
- `common/` — Attributes, brewing, commands, conditions, crafting, enums, extensions, item abilities, loot, utilities
- `common/extensions/` — Block, entity, item, level, player extension interfaces
- `common/crafting/` — Custom crafting conditions and ingredients

### Events — `event/`
- `event/` — Top-level event classes
- `event/entity/` — Entity lifecycle, living entity, player events
- `event/entity/player/` — Player-specific events (interact, attack, respawn, etc.)
- `event/level/` — Level load/save, chunk, block events
- `event/server/` — Server lifecycle events

### Network — `network/`
- `network/` — Packet payloads, registration, channel negotiation
- `network/event/` — Network-related events
- `network/filters/` — Packet filtering

### Registries — `registries/`
- `registries/` — `DeferredRegister`, `DeferredHolder`, registry callbacks
- `registries/datamaps/` — Data map types and registries
- `registries/datamaps/builtin/` — Built-in data maps (furnace fuel, compostables, etc.)
- `registries/holdersets/` — Custom holder set types

### Attachments — `attachment/`
- `attachment/` — Data attachment types for entities, chunks, levels

### Capabilities — `capabilities/`
- `capabilities/` — Fluid handler, energy storage, item handler interfaces

### Server — `server/`
- `server/` — Server lifecycle hooks, language hooks
- `server/command/` — Command helpers and built-in NeoForge commands
- `server/permission/` — Permission API, nodes, handler interfaces

### Resources — `resource/`
- `resource/` — Resource pack loader, reload listener interfaces

---

## Maintenance
When new stubs are generated, update this index to reflect any new or changed packages. Keep this file in `docs/` (not inside `docs/stubs/`) so it is never accidentally deleted with the stubs folder.
