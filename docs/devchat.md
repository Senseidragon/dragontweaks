# DragonTweaks / The Assistant Mod — Session Continuity Document

## Project Identity
- **Mod Name (working):** The Assistant Mod (final name TBD)
- **Repo:** https://github.com/Senseidragon/dragontweaks
- **Mod Loader:** NeoForge
- **Minecraft Version:** 1.21.1
- **Design Doc:** AssistantMod_DesignDoc_v0.1.docx (in repo)
- **LLM Backend:** Ollama @ http://SENSEI:11434/api/generate — Model: gemma4:26b
- **Java:** 21+

---

## Hard Architectural Rules — Never Violate
1. **Nothing may ever block the main Minecraft game thread.** This is non-negotiable in the NeoForge/Minecraft modpack ecosystem.
2. **All network calls, including Ollama HTTP requests, must be async on a separate thread.**
3. **Responses from async operations must be queued back to the main thread via server tick queue before any game interaction.**
4. **Zero interference with MineColonies internals.** All MineColonies integration via public API and event system only. Citizens are never directly modified.
5. **All MineColonies API dependencies must be verified before any dependent code is written.** See design doc section 5.4.

---

## Ollama / LLM Configuration — Non-Negotiable
These were validated by direct benchmarking on SENSEI hardware (RTX 2060 6GB, gemma4:26b Q4_K_M).

- **`"think": false` is mandatory in every API call.** Thinking mode adds ~55 seconds of chain-of-thought overhead per query. With thinking disabled, gemma4:26b responds in ~5 seconds. Never omit this parameter.
- **`"num_predict": 100` must be set in every API call.** The model is verbose by default and will produce multi-paragraph responses without this cap. NPC responses must be short.
- **`"stream": false` required.** Streaming complicates async handling with no benefit for this use case.
- **LLM responses are immersion only.** They are flavor text, not functional output. The mod must never depend on response content for game logic. Fallback templates are always acceptable and expected.
- **System prompt must enforce brevity and character voice.** See validated prompt shape below.
- **Model tag must be explicit:** `gemma4:26b` — not `gemma4` or `gemma4:latest` (different model, different size).

### Validated buildRequestBody parameters
```json
{
  "model": "gemma4:26b",
  "system": "<character prompt>",
  "prompt": "<player message with context>",
  "stream": false,
  "think": false,
  "options": {
    "num_predict": 100
  }
}
```

### Validated system prompt shape
```
You are [npcName], a [role] in a medieval village.
You live in a world where creepers explode, endermen are unsettling tall dark
figures that dislike eye contact, zombies and skeletons roam at night, and
emeralds are common currency. Farming, mining, and crafting are everyday
activities. Refer to all of these as natural parts of your world — they are
real to you. Never reference "the game", "players", "code", or anything that
breaks the sense that this is a real place you live in.
The person speaking to you is named [playerName].
It is currently [timeOfDay] and the weather is [weather].
You are aware of your surroundings but only mention them when it feels natural.
Respond as [npcName] in 1-2 short sentences. Never break character. Never say you are an AI.
```

---

## Current Development Phase
**Phase 1 — Core Framework**
PoC complete and validated. All core technical risks cleared.

### PoC — COMPLETE (2026-04-18)
All success criteria met and validated in-game:
- Game thread never blocked ✓
- Async round trip reliable ✓
- Latency acceptable (~5 seconds with think:false) ✓
- Fallback to template on timeout works silently ✓
- Proximity cutoff working correctly ✓
- NPC identity, player name, role, and Minecraft world awareness all functioning ✓

### Remaining cleanup tasks before Phase 1 code begins
~~**Prompt refinement:** Completed in Session 6. `buildSystemPrompt()` matches validated shape exactly.~~
~~**Remove command partial name match:** Completed in Session 6. `nameMatches()` helper handles partial/case-insensitive matching.~~

All cleanup tasks complete. Phase 1 may begin.

---

## Decisions Made
- **Async is mandatory from the start** — not a later optimization
- **Immediate NPC acknowledgment** ("Hmm..." or equivalent) while LLM processes, so player knows they were heard
- **60 second timeout** before fallback to template response (per design doc)
- **PoC before Phase 1** — validate unknowns before building framework
- **Short, focused sessions preferred** over long sprawling ones to minimize context drift
- **`think: false` always** — non-negotiable, validated by benchmarking
- **`num_predict: 100` always** — non-negotiable, enforces immersion-appropriate response length
- **Environmental context is available but not forced** — NPCs mention time/weather when natural, not every response

---

## Known Limitations — Deferred
- **NPC cross-awareness:** NPCs have no knowledge of other NPCs' actual roles. Hega knows Jack is her husband (from role description) but invents his activities (said he was mining; he's a farmer). Acceptable at PoC stage. Will be resolved when MineColonies citizen data provides proper relationship and role context.

---

## Session Log

### Session 2 — Stationary NPC Entity (PoC)
**Date:** 2026-04-17

#### What was built
- `ModEntities.java` — `DeferredRegister<EntityType<?>>` bound to the mod event bus; registers `dragontweaks:assistant` entity type. Also handles `EntityAttributeCreationEvent` (required — entities without registered attributes crash on first tick).
- `AssistantEntity.java` — Extends `PathfinderMob`. Empty `registerGoals()` override keeps it fully stationary. Constructor sets custom name `"Assistant [PoC]"`, `setCustomNameVisible(true)`, and `setPersistenceRequired()`.
- `AssistantCommand.java` — Registers `/assistant spawn "<name>" "<role>"` on the NeoForge game event bus. Requires permission level 2. Spawns at the executing player's position on the server thread.
- `AssistantRenderer.java` — `HumanoidMobRenderer<AssistantEntity, HumanoidModel<AssistantEntity>>` using `ModelLayers.ZOMBIE` and the vanilla zombie texture. Placeholder until Phase 1 model.
- `DragonTweaksClientEvents.java` — Separate `@EventBusSubscriber(bus = MOD, value = CLIENT)` class to register the renderer. Required because `EntityRenderersEvent.RegisterRenderers` fires on the mod bus, not the game bus.
- `DragonTweaks.java` — Added `ModEntities.ENTITY_TYPES.register(modEventBus)` in constructor.
- `OllamaClient.java` — Async HTTP client on dedicated single-thread executor. Handles query(), buildSystemPrompt(), buildRequestBody(), parseResponse(), sendFallback(), shutdown().
- `ChatInterceptor.java` — Intercepts ServerChatEvent, finds nearest AssistantEntity within range, echoes player message, sends "Hmm...", fires async OllamaClient.query().

#### Registration pattern confirmed for NeoForge 1.21.1
`DeferredRegister.create(Registries.ENTITY_TYPE, MODID)` → `.register(name, () -> EntityType.Builder.of(...).sized(...).build(rl.toString()))` → returns `DeferredHolder`. The `DeferredRegister` must be bound to the mod event bus in the `@Mod` constructor. Attribute registration via `EntityAttributeCreationEvent` on the mod bus is mandatory.

### Session 3 — Ollama Performance Benchmarking
**Date:** 2026-04-18

#### Findings
- gemma4:26b with thinking enabled: 1-2 minutes per query. Unacceptable.
- gemma4:26b with `think: false`: ~5 seconds per query. Acceptable.
- Hardware ceiling: RTX 2060 6GB fits 7-8 of 31 layers on GPU. Hardware limitation, not config.
- Docker Desktop on boot steals ~500MB VRAM. Minor but worth noting.
- `gemma4:latest` (9.6GB) and `gemma4:26b` (17GB) are different models. Always specify `:26b`.

### Session 4 — NPC Personality, Context, PoC Validation
**Date:** 2026-04-18

#### What was completed
- Chat echo implemented in ChatInterceptor — player messages visible in chat before cancellation.
- NPC identity fixed — `buildSystemPrompt(String npcName, String role)` replaces static constant.
- Player name injected into prompt via `player.getGameProfile().getName()`.
- Role injected via `AssistantEntity.getRole()`, persisted to NBT.
- Minecraft world awareness added to system prompt — validated in-game (Endermen, Creepers, emeralds referenced naturally without immersion breaks).
- Spawn command expanded to `/assistant spawn "<name>" "<role>"` supporting multi-word values.
- PoC fully validated. NPC-to-player conversation working correctly across weather, time of day, and topic changes.

---

## Concerns Log
### Concern #1 — AI Threading Naivety
**What happened:** Claude suggested synchronous Ollama HTTP call without flagging threading implications. User had to correct this.
**Rule added:** Hard Architectural Rule #1, #2, #3 above.
**Implication:** Do not accept generated code without verifying it respects Minecraft's single-threaded game loop. Explicitly restate threading constraints at the start of any implementation session.

### Concern #2 — Class Existence Not Verified Before Use (1.21.1)
**What happened:** Claude wrote `AssistantEntity extends HumanoidMob` reasoning from 1.20.1 knowledge. `HumanoidMob` does not exist in Minecraft 1.21.1.
**Corrected to:** `AssistantEntity extends PathfinderMob`.
**Standing rule:** Before using any vanilla or NeoForge class in 1.21.1 code, verify it exists in the decompiled sources at `~/.gradle/caches/ng_execute/.../transformed/`. Do not rely on memory of class names from 1.20.x or earlier. When uncertain, grep the sources first.

---

## Known Limitations of This Document
- **Cognitive entropy is real.** Constraints loaded at session start are not guaranteed to remain equally weighted as context grows. Keep sessions short and focused.
- **Summarization is lossy.** This document is already a compressed representation. Do not allow it to grow large — precision over completeness.
- **AI adherence is not guaranteed.** This document reduces error probability but does not eliminate it. Critical architectural decisions should still be verified by the user.

---

## MineColonies API Reference Documents

| Document | What it covers |
|---|---|
| `docs/MinecoloniesAPI-summary.txt` | **Primary API reference.** Entry point, colony manager, citizens, buildings, permissions, events, registries, recipe/request system, building inventories, warehouse inventory, food store query. Decompiled from `minecolonies-1.0.180-ALPHA-api.jar`. |
| `docs/minecolonies_wiki.md` | Gameplay mechanics reference — building tiers, citizen behaviors, colony management. Not API-focused; useful for understanding what data is meaningful. |
| `docs/MinecoloniesWiki/` | Raw wiki source (git submodule from ldtteam/MinecoloniesWiki). Do not edit. |

---

## Open Questions (from design doc)

**Answered (2026-04-19):**
- ~~Does MineColonies fire a public `CitizenJobAssignedEvent`?~~ → **Yes:** `CitizenJobChangedModEvent` via `api.getEventBus().subscribe()`
- ~~Are citizen happiness values queryable via public API?~~ → **Yes:** `ICitizenData.getCitizenHappinessHandler()`
- ~~Are colony food stores queryable?~~ → **Partial:** No dedicated method. Iterate warehouse via `getMatchingItemStacksInWarehouse(stack -> stack.getItem().getFoodProperties() != null)`
- ~~Are building registry and citizen assignments queryable?~~ → **Yes:** `colony.getServerBuildingManager().getBuilding(pos)`, `building.getAllAssignedCitizen()`, `building.getHandlers()` for inventory

**Still open:**
- Right proximity threshold for command detection (suggest 8-10 blocks as start)
- Should role slots be expandable via a new building (Steward's Office concept)?
- What happens to role if citizen dies (not job assignment)?
- Should shadow entity be visible to other players in multiplayer?
- Maximum scan radius for Ranch Hand mob search?
- Should Scout patrol path be player-defined or auto-generated?
- Final mod name?

---

---

### Session 5 — Multi-NPC Addressing
**Date:** 2026-04-18

#### Decision — Multi-NPC response behavior
- If a player message contains the name of one or more AssistantEntities within range, **all named entities respond independently** with their own async query.
- If no entity name is mentioned, **nearest entity responds** (current behavior).
- Name matching must be **case-insensitive and partial** — "jack" matches "Jack Dawson".
- Multiple async queries fire independently. Responses arrive in LLM return order — acceptable and natural.
- AssistantEntities do **not** need explicit awareness of other AssistantEntities. They respond because they were addressed, not because they sense each other.

#### Task for Claude Code — ChatInterceptor multi-NPC update
- Replace single nearest-entity logic with two-pass scan:
  1. Collect all AssistantEntities within range.
  2. Check each entity's name (case-insensitive, partial) against the raw message.
  3. If any name matches, fire async query for each matched entity.
  4. If no name matches, fall back to nearest-entity behavior.
- Name extraction: use `entity.getCustomName().getString()` — check if any word/token of that name appears in the lowercased message.
- Each matched entity fires its own independent `OllamaClient.query()` call. No coordination between them.

*Last updated: Session 5 — Multi-NPC Addressing (2026-04-18)*

---

### Session 6 — PoC Cleanup (Final Session Before Phase 1)
**Date:** 2026-04-18

#### Scope — three tasks, one session, in this order

**Task 1: Verify prompt matches validated shape**
- Open `OllamaClient.java`, find `buildSystemPrompt()`.
- Confirm it matches the validated system prompt shape in the LLM Configuration section above exactly, including the line "You are aware of your surroundings but only mention them when it feels natural."
- If it matches, no change needed. If it differs, update it to match. Do not improvise.

**Task 2: Fix remove command partial name match**
- `/assistant remove <name>` currently does exact name match. "Jack" fails to remove "Jack Dawson".
- Fix to case-insensitive partial match: entity name contains the provided token, or provided token contains entity name.
- Same matching logic that will be used in Task 3 — extract it into a shared private helper method `nameMatches(String entityName, String token)` so it is only written once.

**Task 3: Multi-NPC addressing in ChatInterceptor**
- Replace single nearest-entity logic with two-pass scan as documented in Session 5 above.
- Use the `nameMatches()` helper from Task 2.
- If any entity name matches: fire async query for each matched entity independently.
- If no name matches: fall back to nearest entity (current behavior).

#### Definition of done
All three tasks complete, `./gradlew build` passes, validated in-game:
- Prompt naturalness confirmed (no forced weather reference in every response)
- `/assistant remove Jack` successfully removes "Jack Dawson"
- Saying "Hi Jack and Hega" within range of both triggers both responses independently

#### What was completed (2026-04-18)
- **Task 1:** `buildSystemPrompt()` in `OllamaClient.java` confirmed to match validated shape exactly, including "You are aware of your surroundings but only mention them when it feels natural." No change needed.
- **Task 2:** `AssistantCommand.nameMatches(String entityName, String token)` static helper added — case-insensitive, partial in either direction. `/assistant remove Jack` now removes "Jack Dawson".
- **Task 3:** `ChatInterceptor` two-pass scan implemented. Named entities within range each fire an independent async query. Falls back to nearest if no name mentioned. Uses `AssistantCommand.nameMatches()`.

*Last updated: Session 6 — PoC cleanup complete (2026-04-18)*

---

### Session 7 — NPC Surroundings Awareness
**Date:** 2026-04-18

#### What was built
- **`Config.java`** — Two new config values:
  - `NPC_AWARENESS_RADIUS` (`IntValue`, default 16, range 4–64): radius in blocks around each NPC for entity scanning.
  - `NPC_AWARENESS_CATEGORY` (`ConfigValue<String>`, default `"PASSIVE"`, valid: `"PASSIVE"` / `"HOSTILE"` / `"ALL"`): which mob categories the NPC perceives.
- **`OllamaClient.scanSurroundings(ServerLevel, AssistantEntity)`** — Queries entities within an AABB of `NPC_AWARENESS_RADIUS` centered on the NPC. Excludes `AssistantEntity`, `Player`, and `ItemEntity`. Filters by `NPC_AWARENESS_CATEGORY`. Groups remaining entities by display name (derived from `getType().getDescriptionId()`, e.g. `"entity.minecraft.pig"` → `"pig"`), counts them, returns a formatted string like `"2 pig, 1 cow"` or `"nothing notable nearby"`.
- **`OllamaClient.buildSystemPrompt()`** — Now takes a `surroundings` parameter. Injects `"Nearby you can see: {surroundings}."` as a new line in the system prompt, after the time/weather line.
- **`OllamaClient.query()`** — Signature extended to accept `surroundings` string, passed through to `buildRequestBody()` and `buildSystemPrompt()`.
- **`ChatInterceptor`** — Calls `OllamaClient.scanSurroundings(serverLevel, target)` once per target NPC immediately before firing the async query.

#### Fix applied
`NPC_AWARENESS_RADIUS` initially defined as `DoubleValue` (`defineInRange` with double bounds). NeoForge config threw a validation error at load. Corrected to `IntValue` (`defineInRange` with int bounds) and `Config.NPC_AWARENESS_RADIUS.get().doubleValue()` used at the call site.

#### Design notes
- Scan runs on the game thread (inside `ChatInterceptor.onServerChat()`), synchronously, before the async query fires. This is intentional — the scan result must be captured at the moment of conversation, not later.
- NPCs do not perceive other `AssistantEntity` instances or players — excluded from scan by design.
- Surroundings are injected into the system prompt, not the user prompt, so the model can weave them naturally into character voice rather than feeling obligated to address them.

*Last updated: Session 7 — NPC surroundings awareness complete (2026-04-18)*

---

### Session 8 — MineColonies API Research
**Date:** 2026-04-19

#### What was researched
Decompiled `minecolonies-1.0.180-ALPHA-api.jar` from the Gradle cache to verify what the public API actually exposes. Key findings added to `docs/MinecoloniesAPI-summary.txt`.

#### Building inventories
- `IBuilding.getHandlers()` returns `List<IItemHandler>` — full Forge inventory access for any building.
- Building tile entities extend `TileEntityRack`, exposing `getAllContent()`, `hasItemStack()`, `getCount()`, `getFreeSlots()`.
- `IBuilding.forceTransferStack(ItemStack, World)` allows inserting items into any building.
- Applies to all worker buildings: Builder's Hut, Mine, Sawmill, Farm, etc.

#### Warehouse inventory
- `IWareHouse` extends `IBuilding` — all building methods apply.
- `AbstractTileEntityWareHouse` adds warehouse-wide query methods spanning all linked racks:
  - `hasMatchingItemStackInWarehouse(Predicate, int)` — presence check
  - `getMatchingItemStacksInWarehouse(Predicate)` → `List<Tuple<ItemStack, BlockPos>>` — items + locations
- No external write path into the warehouse. `dumpInventoryIntoWareHouse()` requires `InventoryCitizen`.

#### Food stores
No dedicated food query. Must use: `te.getMatchingItemStacksInWarehouse(s -> s.getItem().getFoodProperties() != null)`.

#### Open questions resolved
`CitizenJobChangedModEvent` confirmed public. Happiness via `getCitizenHappinessHandler()` confirmed. Building/citizen assignment queries confirmed.

*Last updated: Session 8 — MineColonies API research (2026-04-19)*

---

### Session 9 — Persistent NPC Memory
**Date:** 2026-04-19

#### Goal
Each AssistantEntity remembers recent conversation history with each player. History is injected into the Ollama prompt so the NPC can reference prior exchanges naturally. No MineColonies dependency. Fully testable in superflat testbed.

#### Architecture context — OllamaClient is static
`OllamaClient` is a static utility class — no constructor, no instance. The executor, HTTP client, and Gson are all static fields initialized at classload. This is correct for shared infrastructure (one thread pool across all NPCs is intentional). Do NOT refactor OllamaClient to be per-instance. Memory is handled separately via a static `ConversationMemory` class keyed by NPC UUID.

#### Design decisions

**Storage:** Static `ConversationMemory` class holding `Map<UUID, Map<String, Deque<String>>>`. Outer key is NPC entity UUID. Inner key is player name (lowercase). Values are deques of alternating exchange lines. No NBT persistence — history lost on server restart. Acceptable at this stage.

**Why UUID not NPC name:** Names can collide (two NPCs named "Jack"). UUID is guaranteed unique per entity instance.

**History format:** Each exchange is stored as two lines — player line then NPC line:
```
Senseidragon: How are the crops?
Hega: The rye is coming in well, though I worry about rain tonight.
Senseidragon: Should I bring extra torches?
Hega: It wouldn't hurt — the path to the mill gets dark quickly.
```

**Injection point:** History is injected into the **user prompt** (not the system prompt), immediately before the current player message. System prompt remains character/role/world definition only.

**History cap:** Last **10 exchanges** (10 player lines + 10 NPC lines = 20 lines total) per player per NPC. Oldest pair dropped from front of deque when cap exceeded.

**Threading:** History read on game thread before async query fires. History written on game thread after response is queued back. No concurrent access, no locking needed.

#### ConversationMemory API
```java
// Static utility class — no instances
ConversationMemory.addExchange(UUID npcId, String playerName, String playerLine, String npcLine)
ConversationMemory.getHistory(UUID npcId, String playerName) → String  // empty string if none
ConversationMemory.clear(UUID npcId, String playerName)                // for testing/future use
ConversationMemory.clearAll(UUID npcId)                                // on NPC removal
```

#### User prompt shape with history
```
[Prior conversation:]
Senseidragon: How are the crops?
Hega: The rye is coming in well, though I worry about rain tonight.

Senseidragon says: What's the weather like today?
```
If no history exists for this player+NPC pair yet, omit the `[Prior conversation:]` block entirely — do not inject an empty block.

#### Files to create/modify
- **New:** `ConversationMemory.java` — static utility class as described above.
- **Modify:** `OllamaClient.java` — update `query()` to accept NPC UUID and player name; prepend history in user prompt construction; call `ConversationMemory.addExchange()` after response is sent back to game thread.
- **Modify:** `ChatInterceptor.java` — pass NPC UUID (from `AssistantEntity`) and player name through to `OllamaClient.query()`. Player name already available via event; UUID via `entity.getUUID()`.
- **Modify:** `AssistantCommand.java` — call `ConversationMemory.clearAll(npcId)` when an NPC is removed via `/assistant remove`, so stale history doesn't accumulate.
- **No changes** to `AssistantEntity`, `AssistantRenderer`, `Config`, `ModEntities`.

#### Definition of done
- `./gradlew build` passes.
- Validated in-game: tell an NPC your name or a detail, end the conversation, start a new one — NPC references the earlier exchange without being re-prompted.
- Validated: two different players maintain independent histories with the same NPC.
- Validated: Jack's memory of Senseidragon is separate from Hega's memory of Senseidragon.
- Validated: `/assistant remove Jack` clears Jack's history map entirely.

#### Constraints — do not violate
- **Do not refactor OllamaClient to per-instance.** Static is correct. Memory lives in ConversationMemory, not OllamaClient.
- **In-memory only.** No NBT persistence this session.
- **`num_predict` stays at 100.** History injection lengthens the prompt; response cap does not change.
- **Hard Architectural Rules 1–3 still apply.** History read/write on game thread; Ollama HTTP on async thread; responses queued back to game thread before any chat output.

*Last updated: Session 11 complete (2026-04-19)*

---

### Session 10 — NBT Persistence for Conversation Memory
**Date:** 2026-04-19

#### Goal
Conversation history survives full game exit and JVM restart. History is written to NBT on the `AssistantEntity` itself and read back on entity load. No new dependencies. Fully testable in superflat testbed by exiting to desktop and relaunching.

#### Architecture context
`ConversationMemory` is currently a static map keyed by `UUID → playerName → Deque<String>`. It survives log out/in within the same JVM session but is wiped on full game exit. NBT persistence means writing the history map to the entity's NBT data on save and restoring it on load. The entity's own NBT is the correct location — history belongs to the entity, travels with it, and is discarded automatically when the entity is removed from the world.

#### Pre-check for Claude Code — verify entity removal behavior
Before writing any NBT code, verify how NeoForge 1.21.1 handles entity NBT on removal. Confirm that when an entity is removed via `entity.discard()` or equivalent, its NBT data does not persist anywhere independently. If there is any doubt, add an explicit NBT clear step in `AssistantCommand` on `/assistant remove` before the entity is discarded. Do not assume — check the sources.

#### Design decisions

**NBT structure:** History is stored as a compound tag on the entity under key `"dragontweaks:conversation_history"`. Structure:
```
dragontweaks:conversation_history (CompoundTag)
  └── "Senseidragon" (ListTag of StringTag)
        ├── "Senseidragon: How are the crops?"
        ├── "Hega: The rye is coming in well."
        └── ...
  └── "OtherPlayer" (ListTag of StringTag)
        └── ...
```

**Write timing:** Override `addAdditionalSaveData(CompoundTag)` in `AssistantEntity` to serialize the history map. This is called automatically by Minecraft on chunk save and world save.

**Read timing:** Override `readAdditionalSaveData(CompoundTag)` in `AssistantEntity` to deserialize and restore the history map into `ConversationMemory` keyed by the entity's UUID. This is called automatically on entity load.

**Cleanup on removal:** When `/assistant remove` is called:
1. Call `ConversationMemory.clearAll(entity.getUUID())` — clears in-memory map.
2. Call `entity.getPersistentData().remove("dragontweaks:conversation_history")` — explicitly clears NBT before discard.
3. Then discard the entity.
No digital litter — both in-memory and on-disk state are explicitly cleared.

**New entity, clean slate:** A new entity spawned with the same name as a removed entity gets a new UUID. `ConversationMemory` is keyed by UUID. No history inheritance is possible by design.

#### Files to create/modify
- **Modify:** `AssistantEntity.java` — override `addAdditionalSaveData()` to write history to NBT; override `readAdditionalSaveData()` to read history from NBT and restore into `ConversationMemory`.
- **Modify:** `AssistantCommand.java` — on `/assistant remove`, explicitly clear NBT data before entity discard, in addition to existing `ConversationMemory.clearAll()` call.
- **Modify:** `ConversationMemory.java` — add `getAll(UUID npcId) → Map<String, Deque<String>>` and `restoreAll(UUID npcId, Map<String, Deque<String>>)` methods to support serialization/deserialization from `AssistantEntity`.
- **No changes** to `OllamaClient`, `ChatInterceptor`, `Config`, `ModEntities`, `AssistantRenderer`.

#### Definition of done
- `./gradlew build` passes.
- Validated in-game: tell NPC a named detail (e.g. your nickname, a family member's name), exit to desktop fully, relaunch, re-enter world, ask NPC about the detail — NPC recalls it correctly.
- Validated: `/assistant remove` followed by `/assistant spawn` with the same name produces an NPC with no memory of prior conversations.
- Validated: no NBT data remains after removal — check entity NBT in-game via F3+I or equivalent if available.

#### Constraints — do not violate
- History is stored on the entity's own NBT — not in a separate file, not in world saved data, not in a static config.
- Do not increase history cap. Still 10 exchanges per player per NPC.
- `num_predict` stays at 100.
- Hard Architectural Rules 1–3 still apply.
- In-memory map remains the live working store. NBT is purely persistence (write on save, read on load). Do not read from NBT on every query.

*Last updated: Session 10 complete (2026-04-19)*

---

### Session 11 — Follow Behavior
**Date:** 2026-04-19

#### Goal
Player can ask an AssistantEntity to follow them via natural chat, and ask them to stop via natural chat. Follow behavior enables mobile testing of surroundings awareness and makes NPCs fundamentally more useful than stationary entities.

#### Design decisions

**Trigger mechanism:** Keyword detection in `ChatInterceptor`, evaluated before the Ollama query fires. If a follow/stop intent is detected, the behavior state changes immediately on the game thread. The normal Ollama query still fires and Johnny responds in character — the behavior change and the conversational response are not mutually exclusive.

**Follow keywords:** "follow", "come with", "walk with me", "come along", "come here", "follow me"

**Stop keywords:** "stop", "stay", "wait here", "stay put", "stand still", "wait for me"

**Targeting:** If no NPC name is mentioned in the message, the nearest AssistantEntity within range follows/stops. If a name is mentioned, use existing `AssistantCommand.nameMatches()` logic to target the correct entity.

**Follow behavior:**
- NPC attempts to pathfind to within 3-4 blocks of the player continuously.
- If the player becomes undetectable (teleport, goes invisible, falls out of range, enters a space the NPC cannot pathfind to) the NPC stops moving and stays put. Does not despawn, does not error.
- Follow state is in-memory only — lost on server restart or entity removal. No NBT persistence needed.

**Goal implementation:** Add a follow goal to `AssistantEntity` via `registerGoals()`. Pre-check what follow/move-to-player goal classes exist in NeoForge 1.21.1 decompiled sources before writing anything. Do not assume class names from 1.20.x exist. A custom goal extending `Goal` that targets the player and uses the entity's navigator may be required. Verify first.

**State flag:** Add a `boolean following` field to `AssistantEntity` with `setFollowing(boolean)` and `isFollowing()` accessors. The follow goal checks `isFollowing()` on each tick — if false, goal is inactive and entity stays stationary (existing behavior). Persisted to NBT alongside name and role so it survives chunk unload/reload within a session, but intentionally reset to `false` on world load — NPCs always start stationary.

**Removal cleanup:** Follow state is on the entity itself — no separate cleanup needed beyond the existing entity discard on `/assistant remove`.

#### Files to create/modify
- **Modify:** `AssistantEntity.java` — add `following` boolean field with accessors; add follow goal in `registerGoals()`; write/read `following` to NBT in `addAdditionalSaveData()`/`readAdditionalSaveData()` (reset to false on read — intentional).
- **Modify:** `ChatInterceptor.java` — add keyword detection before Ollama query; call `entity.setFollowing(true/false)` on detection; still fire Ollama query for natural conversational response.
- **New (if needed):** Custom follow goal class if no suitable vanilla/NeoForge goal exists in 1.21.1.
- **No changes** to `OllamaClient`, `ConversationMemory`, `AssistantCommand`, `AssistantRenderer`, `Config`, `ModEntities`.

#### Definition of done
- `./gradlew build` passes.
- Validated in-game: say "follow me" to Johnny — he begins pathfinding to within 3-4 blocks and maintains distance as player moves.
- Validated: say a stop phrase — Johnny stops moving and stays put.
- Validated: Johnny responds in character to both triggers (not silent, not a system message).
- Validated: if player teleports away, Johnny stops and stays put rather than erroring.
- Validated: nearest NPC follows when no name mentioned; named NPC follows when name is included.
- Validated: follow state does not survive world exit — Johnny is stationary on relaunch.

#### Constraints — do not violate
- Keyword detection must feel like natural conversation, not slash commands.
- Follow goal must not block the game thread — goal logic runs on server tick, which is acceptable for pathfinding goals. Ollama query still async.
- Do not use class names from 1.20.x without verifying they exist in 1.21.1 sources (Concern #2).
- Hard Architectural Rules 1–3 still apply.
- `num_predict` stays at 100.

#### What was completed (2026-04-19)
- **Step A:** `following` boolean field + `isFollowing()`/`setFollowing()` added to `AssistantEntity`. Written to NBT on save; intentionally reset to `false` on load — NPCs always start stationary. `MOVEMENT_SPEED` bumped from `0.0` to `0.3` (required for navigator to move).
- **Step B:** `FollowPlayerGoal.java` — custom `Goal` (no suitable vanilla goal exists for `PathfinderMob` → `Player`). Checks `isFollowing()` each tick. Pathfinds to within 3 blocks, recalculates every 10 ticks, stops navigator cleanly if player unreachable. Validated in-game.
- **Step C:** `/assistant follow [name]` and `/assistant stop [name]` added to `AssistantCommand`. Name argument optional — falls back to nearest NPC within 64 blocks if omitted.
- **Step D:** Keyword detection added to `ChatInterceptor` before Ollama query fires. Follow/stop state changes immediately on game thread; Ollama query still fires for in-character response.
- **Bug fix:** Chat tokens were not stripped of punctuation before `nameMatches()`. "freddy," failed to match "Freddy Follower". Fixed by stripping non-alphanumeric characters from each token before matching — both NPCs now respond to "freddy, this is millie".

*Last updated: Session 11 complete (2026-04-19)*

---

### Session 12 — Role-Differentiated Prompts
**Date:** 2026-04-19

#### Goal
Make NPC roles feel real by injecting a role-specific persona block (domain knowledge + personality) into the LLM system prompt. Roles were previously just a word in the opening line — a Ranch Hand and a Scout sounded identical.

#### Design decisions

**Approach:** Hardcoded keyword→persona map in a new `RolePersona` static utility class. Matching is case-insensitive keyword containment (`role.toLowerCase().contains(keyword)`), so `"Head Ranch Hand"` matches `"ranch hand"`. Unrecognized roles return an empty string — existing generic prompt unchanged.

**Injection point:** Immediately after the opening line (`"You are X, a Y in a medieval village."`), before the world description. Omitted entirely when empty — no blank lines for unrecognized roles.

**Persona content:** Each entry combines domain knowledge (what the NPC knows and cares about) with personality/speech flavor (how they talk). Both are needed — domain alone produces a knowledgeable generic NPC; personality alone produces a flavored generic NPC.

#### Initial role table

| Keyword | Domain + personality |
|---|---|
| `ranch hand` | Livestock focus (cattle, sheep, chickens), knows animal health/predator signs/feed levels. Plain speech, no patience for distractions. |
| `planner` | Building queue and resource allocation. Always mentally tracking priorities. Practical speech, quietly frustrated by disruptions. |
| `scout` | Terrain mapping and threat detection. Notices subtle signs. Clipped, alert sentences, rarely wastes words. |
| `advisor` | Colony strategy and diplomacy. Careful word choice, thinks several moves ahead. *(Placeholder — full persona pending MineColonies integration.)* |

#### Files created/modified
- **New:** `RolePersona.java` — static utility, `LinkedHashMap<String, String>` keyword→persona, `getPersonaBlock(String role)` public method.
- **Modify:** `OllamaClient.buildSystemPrompt()` — two-line change: call `RolePersona.getPersonaBlock(role)`, conditionally prepend to world description.

#### Definition of done
- `./gradlew build` passes. ✓
- In-game: Ranch Hand and Scout give distinctly different responses to "what do you do all day?"
- `"Head Ranch Hand"` keyword-matches and gets Ranch Hand persona.
- `"villager"` (unrecognized) produces generic response with no errors.

*Last updated: Session 12 complete (2026-04-19)*

---

### Session 13 — Proactive NPC Observations
**Date:** 2026-04-19

#### Goal
NPCs proactively comment when new mobs appear in their surroundings, without being spoken to first. Tick-driven awareness loop, independent of player chat.

#### Architecture
- `ObservationTicker` registered on `ServerTickEvent.Post`. Runs every 100 ticks, diffs current scan results against per-NPC `lastSeen` sets. When a new hostile or passive entity type appears and the per-NPC cooldown has elapsed, fires `OllamaClient.observe()`.
- `OllamaClient.scanSurroundingsRaw()` — structured scan returning `Map<String, Integer>` (entity type → count). Extracted from `scanSurroundings()`, which now wraps it.
- `OllamaClient.observe()` — proactive prompt path. Builds a short observation prompt; broadcasts response to nearest player within `COMMAND_PROXIMITY` who has an existing `ConversationMemory` entry for the NPC.
- Broadcast target selection: nearest player within range who has history with the NPC. If no such player exists, observation is silently dropped.

#### Config values added
- `NPC_OBSERVATIONS_ENABLED` (BooleanValue, default `true`)
- `NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS` (IntValue, default 15, range 5–300)
- `NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS` (IntValue, default 60, range 15–600)

#### Files created/modified
- **New:** `ObservationTicker.java` — tick handler, per-NPC `lastSeen` maps, per-NPC cooldown tracking, diff + fire logic.
- **Modify:** `OllamaClient.java` — extracted `scanSurroundingsRaw()`; `scanSurroundings()` now wraps it; added `observe()`.
- **Modify:** `DragonTweaks.java` — registered `ObservationTicker::onServerTick` on the NeoForge event bus.
- **Modify:** `AssistantCommand.java` — `deleteAll` and `deleteNearest` call `ObservationTicker.clearState(uuid)` on NPC removal.
- **Modify:** `Config.java` — three new proactive observation config entries.

*Last updated: Session 13 complete (2026-04-19)*

---

### Session 14 — Follow/Stop Bug Fixes
**Date:** 2026-04-19

#### Bug 1 — Stop intent with no name only stopped nearest NPC
`ChatInterceptor` stop-intent path used `targets` (which fell back to `List.of(nearest)` when `matched` was empty) rather than all candidates in range. When multiple NPCs were following, only the nearest stopped.

**Fix:** When `isStopIntent` is true, the stop list is `candidates` if `matched` is empty, otherwise `matched`. Follow intent continues to use `targets` (nearest fallback) unchanged.

#### Bug 2 — NPC kept walking briefly after stop intent
`setFollowing(false)` set the flag but left the navigator running. The goal would eventually terminate on the next tick check, but the NPC visually walked a step or two further.

**Fix:** `AssistantEntity.setFollowing(boolean)` now calls `this.getNavigation().stop()` immediately when the value is set to `false`. The navigator halts at the moment of the flag change, not on the next goal tick.

Note: `FollowPlayerGoal.canContinueToUse()` already checked `entity.isFollowing()` as its first condition — no change needed there.

#### Files modified
- `ChatInterceptor.java` — stop intent uses `candidates` (all in range) when no name matched.
- `AssistantEntity.java` — `setFollowing(false)` calls `this.getNavigation().stop()` immediately.

*Last updated: Session 14 complete (2026-04-19)*

---

### Note — Model Configurability (2026-04-19)

**Concern raised:** `gemma4:26b` on an RTX 2060 6GB works for 1-2 players but will introduce latency under higher load. Waiting on a 5070 server to come back online. Need to be able to swap models (e.g. `gemma4:26b` ↔ `gemma3:12b`) to evaluate quality/latency tradeoffs without rebuilding the mod.

**Status: Already implemented.** `Config.LLM_MODEL` (`ConfigValue<String>`, default `"gemma4:26b"`) exists in `Config.java`. All three call sites in `OllamaClient` (`buildRequestBody`, `ensureAlive`, and the `query()` dispatch) read from `Config.LLM_MODEL.get()`. No hardcoded model strings remain in the codebase.

**To switch models:** Edit `run/config/dragontweaks-common.toml`, change `llmModel = "gemma4:26b"` to the desired model tag. Change takes effect on the next config reload — no rebuild required.
