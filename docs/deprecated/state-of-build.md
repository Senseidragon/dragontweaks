# State of Build — DragonTweaks / The Assistant Mod

**Timestamp:** 2026-04-17  
**Build status:** `BUILD SUCCESSFUL` — 0 errors, 0 warnings  
**Phase:** PoC (pre-Phase 1) — interception pipeline proven, Ollama not yet wired

---

## Source Files

| File | Status | Purpose |
|---|---|---|
| `DragonTweaks.java` | Modified | Main `@Mod` entry point. Registers all listeners explicitly via `modEventBus.addListener()` and `NeoForge.EVENT_BUS.addListener()`. |
| `DragonTweaksClient.java` | Modified | Client-only `@Mod`. Registers config screen and renderer events via injected `IEventBus`. |
| `DragonTweaksClientEvents.java` | Modified | Registers `AssistantRenderer` via `EntityRenderersEvent.RegisterRenderers`. Plain class — no annotation. |
| `Config.java` | Modified | `ModConfigSpec` for all config values. Plain class — listener registered via `addListener`. |
| `ModEntities.java` | Modified | `DeferredRegister<EntityType<?>>` + `EntityAttributeCreationEvent` handler. Plain class — no annotation. |
| `AssistantEntity.java` | Stable | `PathfinderMob` subclass. Stationary, persistent, named "Assistant [PoC]". Empty `registerGoals()`. |
| `AssistantCommand.java` | Stable | Registers `/assistant spawn` (permission level 2). Spawns entity at player position. Uses `@EventBusSubscriber` default (no `bus` param — no warning). |
| `AssistantRenderer.java` | Stable | Placeholder `HumanoidMobRenderer` using vanilla zombie texture and `ModelLayers.ZOMBIE`. |
| `ChatInterceptor.java` | **New** | Server-side chat interception. Proximity check → cancel chat → send acknowledgment → log. |

---

## Config Values (all in `gradle.properties` and `Config.java`)

| Key | Default | Range | Purpose |
|---|---|---|---|
| `roleSlots` | 3 | 1–8 | Role slots per player |
| `commandProximity` | 10 | 4–32 | Blocks radius for chat interception |
| `llmEnabled` | true | — | Enable Ollama-backed responses |
| `llmEndpoint` | `http://SENSEI:11434/api/generate` | — | Ollama endpoint |
| `llmModel` | `gemma4:26b` | — | Model name |
| `llmTimeoutSeconds` | 60 | 5–120 | Timeout before template fallback |

---

## ChatInterceptor Pipeline (PoC milestone)

**Flow:**
1. `ServerChatEvent` fires on game thread
2. Build `AABB.ofSize(player.position(), range×2, range×2, range×2)` as a coarse pre-filter
3. Query `serverLevel.getEntitiesOfClass(AssistantEntity.class, aabb)` — returns only entities in cube
4. Walk list, find nearest via `distanceToSqr` — only accepts winner if within `range²` (sphere check)
5. If no winner: event passes through unmodified
6. If winner: `event.setCanceled(true)` → `player.sendSystemMessage("[Assistant [PoC]]: Hmm...")` → `LOGGER.info`

**Threading:** All work is in-event on the game thread. Total cost is entity iteration + distance math — sub-microsecond. Hard architectural rules are satisfied. Ollama will be introduced as a `CompletableFuture` on a worker thread with `server.execute()` for the reply queue-back.

---

## Design Decisions

### Event registration: explicit `addListener()` over `@EventBusSubscriber`
`EventBusSubscriber.bus()` and `EventBusSubscriber.Bus` are both `@Deprecated(forRemoval = true)` in NeoForge 21.1.226. The annotation is used internally by NeoForge itself, but producing `[removal]` warnings in mod code is unacceptable. All event registration is now done explicitly:
- Mod bus events → `modEventBus.addListener(ClassName::method)` in `DragonTweaks` or `DragonTweaksClient` constructor
- Game bus events → `NeoForge.EVENT_BUS.addListener(ClassName::method)` in `DragonTweaks` constructor

`DragonTweaksClient` constructor signature updated from `(ModContainer)` to `(IEventBus, ModEventBus)` to receive the injected mod bus. `AssistantCommand` retains its `@EventBusSubscriber` default (no `bus` param) because it produces no warning — the default resolves to the game bus and the `RegisterCommandsEvent` fires there.

### AABB cube pre-filter + sphere threshold
The entity query uses a cube AABB (edge = diameter) as an efficient first-pass filter. The true proximity check uses `distanceToSqr <= range²`, which is a sphere. The cube over-selects (corners beyond range pass the AABB), but the distance check immediately rejects them. `nearestDistSq` is initialized to `range²` rather than `Double.MAX_VALUE` — this means the loop only ever produces a non-null winner if a candidate is actually in range, eliminating a redundant range check after the loop.

### AssistantEntity extends PathfinderMob (not HumanoidMob)
`HumanoidMob` does not exist in Minecraft 1.21.1 — it was removed between versions. `PathfinderMob` satisfies `HumanoidMobRenderer`'s generic constraint (`T extends Mob`) directly. Verified from decompiled sources.

### Direct cast `(ServerLevel) player.level()`
`ServerChatEvent` is documented to fire only on the logical server. The cast is guaranteed safe within this event handler. No `instanceof` guard needed.

### Placeholder renderer uses zombie model/texture
A `HumanoidMobRenderer` with `ModelLayers.ZOMBIE` and the vanilla zombie texture is the lowest-friction visible humanoid placeholder that compiles and renders correctly in 1.21.1. To be replaced with a custom model in Phase 1.

---

## API Surface Verified from Decompiled Sources (NeoForge 21.1.226)

All of the following were confirmed by reading actual source files in `~/.gradle/caches/ng_execute/`, not from memory.

| API | Confirmed form |
|---|---|
| Chat interception event | `net.neoforged.neoforge.event.ServerChatEvent` |
| Bus | `NeoForge.EVENT_BUS` (game bus) |
| Cancellable | Implements `ICancellableEvent`; call `event.setCanceled(true)` |
| Cancel effect | Message not delivered to any client |
| Entity level accessor | `Entity.level()` — not `getLevel()` |
| Entity distance | `Entity.distanceToSqr(Entity other)` |
| Entity query | `EntityGetter.getEntitiesOfClass(Class<T>, AABB, Predicate<T>)` |
| AABB factory | `AABB.ofSize(Vec3 center, double xSize, double ySize, double zSize)` |
| Player message | `ServerPlayer.sendSystemMessage(Component)` |

**1.20.x regression note:** The only observed difference in this area is `@Cancelable` annotation replaced by `ICancellableEvent` interface. Package, bus, and all method signatures are the same.

---

## What Is Not Yet Built

- Ollama HTTP client and async call
- `CompletableFuture` → `server.execute()` response pipeline
- Environmental context payload (time of day, weather, biome, moon phase)
- Template fallback on timeout
- Any Phase 1–6 architecture (see design doc)

---

## Hard Architectural Rules (standing)

1. Nothing blocks the main game thread. Ever.
2. All async work (Ollama, HTTP) runs on a separate thread.
3. Async results queue back to the main thread via `server.execute()` before any game interaction.
4. Zero interference with MineColonies internals — public API and event system only.
5. Verify any vanilla or NeoForge class exists in decompiled sources before use. Do not rely on memory of 1.20.x class names.
