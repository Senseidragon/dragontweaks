# DragonTweaks — Architecture Rules

*Non-negotiable. These rules apply to every line of code in this project.*

---

## Threading Rules

1. **Nothing may ever block the main Minecraft game thread.** Non-negotiable under any circumstances.
2. **All network calls must be async** using `HttpClient.sendAsync()` + `CompletableFuture` + `orTimeout()`. Blocking `send()` is never acceptable — not in production, not in prototypes.
3. **Async responses must return to the main thread** via `server.execute()` before any game interaction. The mandatory async pattern:

```java
httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .orTimeout(Config.LLM_TIMEOUT_SECONDS.get(), TimeUnit.SECONDS)
    .thenApply(parseResponse)
    .thenAccept(reply -> server.execute(() -> { /* game thread work here */ }))
    .exceptionally(ex -> { LOGGER.warn(...); return null; });
```

---

## MineColonies Compatibility Rules

4. **Zero interference with MineColonies internals.** All integration via public API and event bus only. Citizens are never directly modified.
5. **All MineColonies API calls must be verified against stubs in `docs/stubs/`** before any dependent code is written. Report what you find before writing.
6. **Always guard MineColonies calls** with `if (!ModList.get().isLoaded("minecolonies")) return;`
7. **MineColonies event bus** for colony events: `IMinecoloniesAPI.getInstance().getEventBus().subscribe(...)` — not the NeoForge event bus.

### Verified MineColonies API Facts

| Fact | API |
|---|---|
| Colony happiness overall | `ICitizenHappinessHandler.getHappiness(colony, data)` |
| All modifier names | `ICitizenHappinessHandler.getModifiers()` — `List<String>` |
| Single modifier | `ICitizenHappinessHandler.getModifier(String id)` |
| Factor value | `IHappinessModifier.getFactor(ICitizenData)` — 1.0 neutral, <1.0 negative |
| Factor weight | `IHappinessModifier.getWeight()` |
| Colony age in days | `IColony.getDay()` — returns int |
| Warehouse stock | `AbstractTileEntityWareHouse.getMatchingItemStacksInWarehouse(Predicate<ItemStack>)` |
| Research tree | `IColony.getResearchManager().getResearchTree()` |
| Research completed | `ILocalResearchTree.hasCompletedResearch(ResourceLocation)` |
| Research in progress | `ILocalResearchTree.getResearchInProgress()` |
| Work building position | `ICitizenData.getWorkBuilding()` |
| Home building position | `ICitizenData.getHomeBuilding()` |
| Citizen job | `ICitizenData.getJob()` |
| Citizen happiness handler | `ICitizenData.getCitizenHappinessHandler()` |
| Building level | `IBuilding.getLevel()` |
| Building built status | `IBuilding.isBuilt()` |
| Building pending | `IBuilding.isPendingConstruction()` |
| Town Hall level | `colony.getServerBuildingManager().getTownHall().getBuildingLevel()` |
| Colony in coord check | `IColony.isCoordInColony(world, pos)` |
| Raid state | `colony.getRaiderManager().isRaided()` |
| Colony owner | `IColony.getPermissions().getOwner()` — returns `UUID` |
| Citizen ID | `ICitizen.getId()` — returns `int` |
| Town Hall position | `colony.getServerBuildingManager().getTownHall().getPosition()` — `BlockPos` |
| Colony lookup by pos | `IColonyManager.getInstance().getIColony(Level, BlockPos)` |
| Colonies by level | `IColonyManager.getInstance().getColonies(level)` — `List<IColony>` |

### MineColonies Events

| Event | Access |
|---|---|
| `CitizenDiedModEvent` | `e.getColony().getWorld()` cast to `ServerLevel` |
| `BuildingConstructionModEvent` | `e.getBuilding().getBuildingType().getTranslationKey()` |
| `ColonyCreatedModEvent` | `getColony().getPermissions().getOwner()` for owner UUID |
| `ColonyDeletedModEvent` | Same as above |
| `CitizenJobChangedModEvent` | `getColony()` — package: `com.minecolonies.api.eventbus.events.colony.citizens.*` |
| `CitizenAddedModEvent` | Same package |

---

## Security Rules

8. **API key lives in `.env` only.** Never in source code. Never logged. Never transmitted to clients.
9. **LLM responses are immersion only.** Game logic must never depend on LLM response content. Fallback templates are always acceptable.

---

## Code Quality Rules

10. **Never hardcode config values.** All tunable values live in `Config.java`.
11. **Read before writing.** Always read a file before modifying it.
12. **Do not modify these files unless explicitly instructed:** `DragonTweaks.java` (restructure only), `LLMClient.java`, `Config.java`, `RoleAssignmentData.java`, `ColonyDiagnosticReport.java`, `PlannerDependencyRegistry.java`.
13. **Do not refactor unrelated code** while fixing a bug or implementing a feature.

---

## NeoForge 1.21.1 Class Name Traps

| Wrong (1.20.x) | Correct (1.21.1) |
|---|---|
| `HumanoidMob` | `PathfinderMob` |
| `setHomePosAndDistance(BlockPos, int)` | `restrictTo(BlockPos, int)` on `Mob` |
| `RestrictedWaterAvoidingRandomWalkingGoal` | `WaterAvoidingRandomStrollGoal` + `MoveTowardsRestrictionGoal` |

Verify all class names against NeoForge 1.21.1 sources before use.

---

## NPC Wander Restriction Pattern

- `this.restrictTo(blockPos, radius)` on `Mob` sets home anchor + radius.
- `WaterAvoidingRandomStrollGoal` respects restriction automatically.
- Add `MoveTowardsRestrictionGoal` alongside it.
- Call `restrictTo()` at spawn via `finalizeSpawn()` override.
- Call `restrictTo()` again on stop/stay command using `npc.blockPosition()`.
