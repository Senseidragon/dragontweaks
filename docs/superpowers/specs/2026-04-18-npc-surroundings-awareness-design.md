# NPC Surroundings Awareness — Design Spec

**Date:** 2026-04-18  
**Status:** Approved

---

## Summary

AssistantEntity NPCs gain awareness of nearby entities (animals, mobs, or both) within a configurable radius. A surroundings summary is always injected into the LLM system prompt — populated with what the NPC can see, or "nothing notable nearby" when the area is empty. This lets the NPC respond naturally when a player asks what's around them, without hallucinating or going silent.

---

## Config Changes (`Config.java`)

Two new values added to the existing `ModConfigSpec`:

| Key | Type | Default | Description |
|-----|------|---------|-------------|
| `npc_awareness_radius` | double | `16.0` | Scan radius around the NPC in blocks |
| `npc_awareness_category` | string enum | `"PASSIVE"` | Which entity categories to include: `PASSIVE`, `HOSTILE`, `ALL` |

`PASSIVE` covers animals and non-hostile creatures (pigs, cows, chickens, horses, sheep, etc.).  
`HOSTILE` covers monsters (zombies, skeletons, creepers, etc.).  
`ALL` includes both.

---

## Entity Scan

Performed in `ChatInterceptor.onServerChat()` at query time, alongside the existing `timeOfDay` and `weather` lookups. The scan runs **on the target AssistantEntity's position**, not the player's.

Steps:
1. Build an `AABB` sphere of radius `NPC_AWARENESS_RADIUS` around the target entity.
2. Call `serverLevel.getEntities(EntitySelector, aabb)` filtering out `AssistantEntity`, `Player`, and `ItemEntity` instances.
3. Apply category filter based on `NPC_AWARENESS_CATEGORY`: check `MobCategory` via `EntityType.getCategory()`.
4. Group results by `EntityType` registry name, count each group.
5. Format as a compact comma-separated string: `"2 pigs, 1 horse, 3 chickens"`.
6. If list is empty, pass the literal string `"nothing notable nearby"`.

The formatted string is passed as a new `surroundings` parameter to `OllamaClient.query()`.

---

## Prompt Injection (`OllamaClient.buildSystemPrompt()`)

`buildSystemPrompt()` gains a `String surroundings` parameter. A new line is always appended:

```
Nearby you can see: {surroundings}.
```

Example populated: `"Nearby you can see: 2 pigs, 1 horse."`  
Example empty: `"Nearby you can see: nothing notable nearby."`

The LLM expresses this in its own character voice — the developer-written text sets the fact, the NPC's role/personality determines how it's said.

---

## Data Flow

```
ChatInterceptor.onServerChat()
  → scan entities around target AssistantEntity
  → format surroundings string
  → OllamaClient.query(..., surroundings)
      → buildRequestBody(..., surroundings)
          → buildSystemPrompt(..., surroundings)
              → appends "Nearby you can see: {surroundings}."
```

`OllamaClient.query()` signature gains one parameter: `String surroundings`. All existing callers (currently only `ChatInterceptor`) must be updated.

---

## Entity Type Display Names

Registry names (`minecraft:pig`) are converted to readable labels (`pig`) by stripping the namespace prefix and replacing underscores with spaces. Examples:

- `minecraft:pig` → `pig`
- `minecraft:chicken` → `chicken`
- `minecraft:cave_spider` → `cave spider`

---

## Out of Scope

- Line-of-sight filtering (not implemented — NPC "awareness" is radius-based)
- Named entities (MineColonies citizens, other AssistantEntities) — deferred
- Distance or directional information — deferred (possible C-style prose upgrade later)

---

## Definition of Done

- `/assistant spawn` + player asks "what's around you?" → NPC responds with visible animals
- Area with no mobs → NPC responds with empty-area flavor, no hallucination
- `npc_awareness_category = HOSTILE` → only hostile mobs listed
- `./gradlew build` passes
