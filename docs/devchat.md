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
- **System prompt must enforce brevity and character voice.** Example: "You are [name], a [role] in a medieval village. Respond in 1-2 short sentences, in character. Never break character or reference Minecraft."
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

---

## Current Development Phase
**PoC Phase — pre-Phase 1**
Validating core technical risks before committing to full architecture.

### PoC Goal
Spawn a stationary NPC (villager or equivalent) that:
- Does not wander
- Intercepts player chat within proximity radius (~8-10 blocks)
- Fires async Ollama/Gemma4 request with minimal context payload (time of day, weather)
- Delivers LLM response as in-world chat attributed to the NPC
- Handles timeout/fallback gracefully without breaking immersion

### PoC Success Criteria
- Game thread never blocked during Ollama call
- Async round trip works reliably
- Latency is acceptable or masked effectively (~5-10 seconds with think:false)
- Fallback to template on timeout works silently

---

## Decisions Made
- **Async is mandatory from the start** — not a later optimization
- **Immediate NPC acknowledgment** ("Hmm..." or equivalent) while LLM processes, so player knows they were heard
- **60 second timeout** before fallback to template response (per design doc)
- **PoC before Phase 1** — validate unknowns before building framework
- **Short, focused sessions preferred** over long sprawling ones to minimize context drift
- **`think: false` always** — non-negotiable, validated by benchmarking
- **`num_predict: 100` always** — non-negotiable, enforces immersion-appropriate response length

---

## Session Log

### Session 2 — Stationary NPC Entity (PoC)
**Date:** 2026-04-17

#### What was built
- `ModEntities.java` — `DeferredRegister<EntityType<?>>` bound to the mod event bus; registers `dragontweaks:assistant` entity type. Also handles `EntityAttributeCreationEvent` (required — entities without registered attributes crash on first tick).
- `AssistantEntity.java` — Extends `PathfinderMob`. Empty `registerGoals()` override keeps it fully stationary. Constructor sets custom name `"Assistant [PoC]"`, `setCustomNameVisible(true)`, and `setPersistenceRequired()`.
- `AssistantCommand.java` — Registers `/assistant spawn` on the NeoForge game event bus (`@EventBusSubscriber` default). Requires permission level 2. Spawns at the executing player's position on the server thread — no async concerns here.
- `AssistantRenderer.java` — `HumanoidMobRenderer<AssistantEntity, HumanoidModel<AssistantEntity>>` using `ModelLayers.ZOMBIE` and the vanilla zombie texture. Placeholder until Phase 1 model.
- `DragonTweaksClientEvents.java` — Separate `@EventBusSubscriber(bus = MOD, value = CLIENT)` class to register the renderer. Required because `EntityRenderersEvent.RegisterRenderers` fires on the mod bus, not the game bus.
- `DragonTweaks.java` — Added `ModEntities.ENTITY_TYPES.register(modEventBus)` in constructor.

#### Registration pattern confirmed for NeoForge 1.21.1
`DeferredRegister.create(Registries.ENTITY_TYPE, MODID)` → `.register(name, () -> EntityType.Builder.of(...).sized(...).build(rl.toString()))` → returns `DeferredHolder`. The `DeferredRegister` must be bound to the mod event bus in the `@Mod` constructor. Attribute registration via `EntityAttributeCreationEvent` on the mod bus is mandatory.

#### Threading notes
Entity registration and spawn command execution both occur on expected threads (init phase and server thread respectively). The Hard Architectural Rules apply when LLM calls are introduced, not to this registration/spawn code.

### Session 3 — Ollama Performance Benchmarking
**Date:** 2026-04-18

#### Findings
- gemma4:26b with thinking enabled: 1-2 minutes per query. Unacceptable.
- gemma4:26b with `think: false`: ~5 seconds per query. Acceptable for PoC.
- Hardware ceiling: RTX 2060 6GB can only fit 7-8 of 31 model layers on GPU. Remaining layers run on CPU. This is a hardware limitation, not a configuration problem.
- Docker Desktop running on boot steals ~500MB VRAM, reducing GPU layers from 8 to 7. Minor impact.
- Response verbosity is a prompting problem, not a model problem. `num_predict: 100` + tight system prompt resolves it.
- `gemma4:latest` (9.6GB) and `gemma4:26b` (17GB) are different models. Always specify `:26b` explicitly.

#### Impact on integration plan
The `buildRequestBody` method in `OllamaClient.java` (Task 2, Step 1) must be updated to include `think: false` and `num_predict: 100`. The existing plan does not include these parameters. Claude Code must add them before the integration is considered complete.

### Session 3 Addendum — Chat Echo and NPC Identity
**Date:** 2026-04-18

#### What was fixed
- **Chat echo:** Player messages were being swallowed by `event.setCanceled(true)` with no visible record in chat. Fixed by adding `player.sendSystemMessage(Component.literal("<" + player.getGameProfile().getName() + "> " + rawMessage))` immediately before `event.setCanceled(true)` in `ChatInterceptor.java`. Working and validated in-game.

#### Outstanding fix required — NPC identity
- **Problem:** `SYSTEM_PROMPT` in `OllamaClient.java` is a static constant with no NPC name. The LLM invents its own name (tested: NPC named Marcus responded as "Elric"). This is confirmed broken.
- **Required change:** Replace the static `SYSTEM_PROMPT` constant with a `buildSystemPrompt(String npcName)` method:
```java
private static String buildSystemPrompt(String npcName) {
    return "You are " + npcName + ", a farmhand in a medieval village. " +
           "Respond in 1-2 short sentences, in character as " + npcName + ". " +
           "Never say you are an AI. Never break character.";
}
```
- `buildRequestBody` must accept `npcName` as a parameter and call `buildSystemPrompt(npcName)`.
- `OllamaClient.query()` must accept `npcName` as a parameter and pass it to `buildRequestBody`.
- `ChatInterceptor.java` already has `entityName` in scope — pass `entityName.getString()` to `OllamaClient.query()`.
- **Do not hardcode any NPC name.** It must always come from the entity's custom name at runtime.

---

## Concerns Log
### Concern #1 — AI Threading Naivety
**What happened:** Claude suggested synchronous Ollama HTTP call without flagging threading implications. User had to correct this.
**Rule added:** Hard Architectural Rule #1, #2, #3 above.
**Implication:** Do not accept generated code without verifying it respects Minecraft's single-threaded game loop. Explicitly restate threading constraints at the start of any implementation session.

### Concern #2 — Class Existence Not Verified Before Use (1.21.1)
**What happened:** Claude wrote `AssistantEntity extends HumanoidMob` reasoning from 1.20.1 knowledge. `HumanoidMob` does not exist in Minecraft 1.21.1. The class was removed between versions. This would have been a compile error.
**Corrected to:** `AssistantEntity extends PathfinderMob`. `HumanoidMobRenderer`'s actual generic constraint is `T extends Mob` (verified from decompiled source), so `PathfinderMob` satisfies it directly — `HumanoidMob` was never needed for the renderer either.
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

### Session 4 — NPC Personality and Context Expansion
**Date:** 2026-04-18

#### Tasks for Claude Code

##### Task A: Expand spawn command to accept name and role arguments
- Change `/assistant spawn` to `/assistant spawn "<name>" "<role>"` — both arguments quoted strings to support multi-word values (e.g. "Grumpy Joe", "village herbalist").
- `AssistantEntity` needs a `role` String field with getter/setter.
- Role must be persisted to NBT (`addAdditionalSaveData` / `readAdditionalSaveData`) so it survives chunk unloads and server restarts.
- Name is already set via `setCustomName()` — no change needed there.
- If no arguments provided, fall back to defaults: name = "Assistant", role = "villager".

##### Task B: Inject player name into prompt
- `OllamaClient.query()` already receives `ServerPlayer player` — extract `player.getGameProfile().getName()` and pass it into `buildRequestBody()`.
- Add to the prompt context: `"The person speaking to you is named [playerName]."`
- NPC should address the player by name naturally, not every sentence.

##### Task C: Inject role into system prompt
- `OllamaClient.query()` must also accept `String role` parameter (sourced from `AssistantEntity.getRole()`).
- `buildSystemPrompt(String npcName, String role)` replaces `buildSystemPrompt(String npcName)`.
- System prompt should incorporate role naturally, e.g. "You are [name], a [role] in a medieval village."
- `ChatInterceptor` must retrieve role from the nearest `AssistantEntity` and pass it through.

##### Task D: Minecraft world awareness in system prompt
Add the following context to `buildSystemPrompt()` — after the name/role introduction, before the response rules:
```
You live in a world where creepers explode, endermen are unsettling tall 
dark figures that dislike eye contact, zombies and skeletons roam at night, 
and emeralds are common currency. Farming, mining, and crafting are everyday 
activities. Refer to all of these as natural parts of your world — they are 
real to you. Never reference "the game", "players", "code", or anything that 
breaks the sense that this is a real place you live in.
```

##### Full target system prompt shape
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
Respond as [npcName] in 1-2 short sentences. Never break character. Never say you are an AI.
```

##### Method signature changes required
- `buildSystemPrompt(String npcName)` → `buildSystemPrompt(String npcName, String role)`
- `buildRequestBody(String model, String prompt)` → `buildRequestBody(String model, String prompt, String npcName, String role, String playerName, String timeOfDay, String weather)`
- `OllamaClient.query(...)` → add `String role` and `String playerName` parameters
- `ChatInterceptor.onServerChat(...)` → retrieve `nearest.getRole()` and `player.getGameProfile().getName()`, pass both to `OllamaClient.query()`

---

*Last updated: Session 4 — NPC Personality and Context Expansion (2026-04-18)*
