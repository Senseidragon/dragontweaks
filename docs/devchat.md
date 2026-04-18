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
- **Prompt refinement:** "You are aware of your surroundings but only mention them when it feels natural." already added to validated prompt shape above. Ensure `buildSystemPrompt()` matches exactly.
- **Remove command partial name match:** `/assistant remove` currently does exact name match. "Jack" fails to match "Jack Dawson". Fix to support partial/case-insensitive name matching, or show a clear usage error stating full name is required.

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

## Open Questions (from design doc)
- Does MineColonies fire a public `CitizenJobAssignedEvent`?
- Are citizen happiness values queryable via public API?
- Are colony food stores queryable?
- Are building registry and citizen assignments queryable?
- Right proximity threshold for command detection (suggest 8-10 blocks as start)
- Should role slots be expandable via a new building (Steward's Office concept)?
- What happens to role if citizen dies (not job assignment)?
- Should shadow entity be visible to other players in multiplayer?
- Maximum scan radius for Ranch Hand mob search?
- Should Scout patrol path be player-defined or auto-generated?
- Final mod name?

---

*Last updated: Session 4 — PoC Complete, Phase 1 begins (2026-04-18)*
