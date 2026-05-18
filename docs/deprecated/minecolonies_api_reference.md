# MineColonies API Reference
*Merged from MinecoloniesAPI-summary.md and Answers_regarding_research_and_happiness*

---

## Entry Point

Everything starts from one singleton:

```java
IMinecoloniesAPI api = IMinecoloniesAPI.getInstance();
```

From there you branch into managers and subsystems.

---

## Major Systems

### Colony Manager

```java
IColonyManager manager = api.getColonyManager();

// Find colonies
IColony colony = manager.getColonyByWorld(id, world);
IColony colony = manager.getColonyByPosFromWorld(world, blockPos);
List<IColony> all = manager.getColonies(world);
IColony nearest = manager.getClosestColony(world, pos);
IColony mine = manager.getIColonyByOwner(world, player);

// Create / delete
manager.createColony(world, pos, player, name, pack);
manager.deleteColonyByDimension(id, canDestroy, dimension);

// Validation
manager.isFarEnoughFromColonies(world, pos);
```

### IColony (server-side, mutable)

```java
colony.getID();
colony.getName() / colony.setName(String);
colony.getCenter();
colony.isCoordInColony(world, pos);
colony.getPermissions();         // IPermissions
colony.getCitizenManager();      // ICitizenManager
colony.getServerBuildingManager();
colony.getResearchManager();
colony.getStatisticsManager();
colony.getQuestManager();
colony.getEventManager();
colony.getRequestManager();
```

### IColonyView (client-side, read-only)

```java
view.getCitizens();              // Map<Integer, ICitizenDataView>
view.getCitizenCount() / getCitizenCountLimit();
view.getOverallHappiness();
view.getWorkOrders();
view.getPlayers();               // players + ranks
```

---

## Citizens

| Interface | Side | Use |
|---|---|---|
| `ICitizen` | Both | Lightweight: ID, name, inventory, saturation |
| `ICitizenData` | Server | Full data: job, home, bed, skills, family, health |
| `ICitizenDataView` | Client | Read-only view of above |

### Key `ICitizenData` Access

```java
data.getJob();
data.getWorkBuilding();
data.getHomeBuilding();
data.getCitizenSkillHandler();
data.getCitizenHappinessHandler();
data.getEntity();                // Optional<AbstractEntityCitizen>
data.getPartner();
data.getChildren();
```

---

## Buildings

```java
IBuilding building = colony.getServerBuildingManager().getBuilding(pos);

building.getLevel() / building.getMaxLevel();
building.isBuilt() / building.isPendingConstruction();
building.requestUpgrade(player, pos);
building.requestRepair(pos);
building.getCustomName() / building.setCustomBuildingName(name);
building.createRequest(citizenData, requestable, async);
```

---

## Permissions

```java
IPermissions perms = colony.getPermissions();

perms.hasPermission(player, Action.BUILD);
perms.getRank(player);
perms.setPlayerRank(uuid, rank, world);
perms.addPlayer(uuid, name, rank);
perms.getRankOwner() / getRankOfficer() / getRankFriend();
// Standard ranks: Owner(0), Officer(1), Friend(2), Neutral(3), Hostile(4)
```

---

## Events

```java
api.getEventBus().subscribe(CitizenAddedModEvent.class, event -> { ... });
api.getEventBus().subscribe(ColonyCreatedModEvent.class, event -> { ... });
```

### Available Events

**Colony**
- `ColonyCreatedModEvent`
- `ColonyDeletedModEvent`
- `ColonyNameChangedModEvent`
- `ColonyFlagChangedModEvent`
- `ColonyViewUpdatedModEvent`

**Buildings**
- `BuildingAddedModEvent`
- `BuildingRemovedModEvent`
- `BuildingConstructionModEvent`

**Citizens**
- `CitizenAddedModEvent`
- `CitizenRemovedModEvent`
- `CitizenDiedModEvent`
- `CitizenJobChangedModEvent`

**Players**
- `PlayerEnteringModEvent`
- `PlayerLeavingModEvent`

---

## Registries (for Addon Authors)

Access via `IMinecoloniesAPI.getInstance()`:

| Registry | What it's for |
|---|---|
| `getBuildingRegistry()` | Custom building types |
| `getJobRegistry()` | Custom citizen jobs |
| `getBuildingExtensionRegistry()` | Building modules |
| `getGuardTypeRegistry()` | Guard variants |
| `getResearchEffectRegistry()` | Custom research rewards |
| `getResearchRequirementRegistry()` | Custom research prerequisites |
| `getRecipeTypeRegistry()` | Custom recipe types |
| `getCraftingTypeRegistry()` | Custom crafting methods |
| `getQuestRewardRegistry()` | Custom quest rewards |
| `getColonyEventRegistry()` | Custom in-game colony events |
| `getModelTypeRegistry()` *(client)* | Custom citizen render models |

---

## Recipe / Request System

```java
// Check / fulfill a recipe
IRecipeStorage recipe = ...;
recipe.getInput();               // List<ItemStorage>
recipe.getPrimaryOutput();       // ItemStack
recipe.canFullFillRecipe(qty, existingItems, handlers...);
recipe.fullfillRecipe(world, handlers);

// Create a work request from a building
IToken<?> token = building.createRequest(citizenData, requestable, false);
```

---

## Research System

### Call Chain

```java
ILocalResearchTree tree = colony.getResearchManager().getResearchTree();
```

### Querying Completion

```java
// Check a single research by ResourceLocation ID
boolean done = tree.hasCompletedResearch(new ResourceLocation("minecolonies", "technology/some_research"));
boolean done = tree.isComplete(researchId);   // same thing, two methods

// Get the full completed list
List<ResourceLocation> completed = tree.getCompletedList();

// Check if a branch has finished its highest-tier (level 6) research
boolean maxed = tree.branchFinishedHighestLevel(new ResourceLocation("minecolonies", "warfare"));
```

### Querying In-Progress Research

```java
List<ILocalResearch> inProgress = tree.getResearchInProgress();

for (ILocalResearch r : inProgress) {
    r.getId();          // ResourceLocation — the research ID
    r.getBranch();      // ResourceLocation — branch it belongs to
    r.getDepth();       // int — tier/depth in the tree (1–6)
    r.getProgress();    // int — progress in ticks
    r.getState();       // ResearchState enum: NOT_STARTED, IN_PROGRESS, FINISHED
}
```

### Fetching a Specific Research by ID

```java
ILocalResearch r = tree.getResearch(branchId, researchId);
// Returns null if not started at all — only returns an object if it was started or finished
```

### Research Effects (separate from the tree)

```java
IResearchEffectManager effects = colony.getResearchManager().getResearchEffects();
// Used for checking numeric/boolean effects a research unlocks (e.g. bonus values)
```

### Key Limitation

`getResearch()` and `getResearchInProgress()` only know about researches that have been started. Researches the colony has never touched don't appear — check for those with `hasCompletedResearch()` returning `false` and absence from `getResearchInProgress()`.

---

## Happiness System

### Call Chain

```java
ICitizenHappinessHandler h = citizenData.getCitizenHappinessHandler();

// Get overall happiness (0.0–∞, where 1.0 is neutral)
double overall = h.getHappiness(colony, citizenData);

// Get all modifier names currently tracked on this citizen
List<String> names = h.getModifiers();

// Get a specific modifier
IHappinessModifier mod = h.getModifier("food");
double factor = mod.getFactor(citizenData);  // <1.0 = negative, >1.0 = positive
double weight  = mod.getWeight();
```

### Canonical Happiness Factor IDs

These are the registered string IDs from `HappinessRegistry`. Use these with `getModifier()`:

| ID string | What it measures |
|---|---|
| `"food"` | Whether the citizen is being fed adequately |
| `"slepttonight"` | Whether the citizen slept last night |
| `"housing"` | Whether the citizen has a home building assigned |
| `"health"` | Injury / disease state |
| `"unemployment"` | Whether the citizen has a job |
| `"idleatjob"` | Whether the citizen is stuck idle at their work building |
| `"security"` | Proximity to a guard building |
| `"school"` | Access to a school (for children) |
| `"social"` | Social interactions with other citizens |
| `"mystical"` | Proximity to a Mystical Site |

> **Note:** There is also a `greatfood` function registered but no canonical string ID constant — it appears to be a bonus tier of the food factor.

> **Notable absence:** There is no `"commute"` modifier in the registered set. Distance-to-work is not tracked as a happiness factor directly. The closest proxies are `idleatjob` (citizen is stuck/not working) and `housing` (no home assigned). Bed distance is not a separate factor — it is folded into `slepttonight`.

### Modifier Types

All modifier types implement `IHappinessModifier`:

| Type | Behavior |
|---|---|
| `StaticHappinessModifier` | Fixed factor, doesn't decay |
| `ExpirationBasedHappinessModifier` | Factor that expires after N ticks |
| `TimeBasedHappinessModifier` | Factor tied to a time window (e.g. "slept last night") |

### Pattern: Reading a Citizen's Full Happiness Breakdown

```java
ICitizenHappinessHandler h = data.getCitizenHappinessHandler();
for (String id : h.getModifiers()) {
    IHappinessModifier mod = h.getModifier(id);
    System.out.printf("%s: factor=%.2f weight=%.2f%n", id, mod.getFactor(data), mod.getWeight());
}
double total = h.getHappiness(colony, data);
```

---

## Side Note: Server vs. Client

`IColony` is **server-side** (mutable). On the client you get `IColonyView` instead — mostly read-only. The colony manager returns the appropriate type automatically depending on which logical side you're on.

---

## Minimal Addon Quickstart

```java
// 1. Get API on server startup / world load
IMinecoloniesAPI api = IMinecoloniesAPI.getInstance();

// 2. Subscribe to events
api.getEventBus().subscribe(CitizenDiedModEvent.class, event -> {
    IColony colony = event.getColony();
    // react to citizen death
});

// 3. Query colonies in a command or event
IColony colony = api.getColonyManager().getColonyByPosFromWorld(world, pos);
if (colony != null) {
    int pop = colony.getCitizenManager().getCurrentCitizenCount();
    boolean canBuild = colony.getPermissions().hasPermission(player, Action.BUILD);
}

// 4. Register a custom building type (during mod init)
api.getBuildingRegistry().register(
    new BuildingEntry.Builder()
        .setBuildingProducer(MyBuilding::new)
        .setBuildingViewProducer(MyBuildingView::new)
        .setRegistryName(new ResourceLocation("myaddon", "mybuilding"))
        .build()
);
```
