# Role-Differentiated Prompts — Design Spec

**Date:** 2026-04-19
**Status:** Approved

---

## Summary

AssistantEntity NPCs currently inject their role as a plain word in the system prompt (`"a ranch hand in a medieval village"`). This spec adds a hardcoded persona block per known role — a combined domain-knowledge and personality description injected immediately after the opening line. Unrecognized roles fall back to the existing generic prompt unchanged.

---

## New Class: `RolePersona.java`

A static utility class with a single public method:

```java
public static String getPersonaBlock(String role)
```

Matching is case-insensitive keyword containment: `role.toLowerCase().contains(keyword)`. Keywords are checked in declaration order. Returns an empty string if no keyword matches.

### Role Table

| Keyword | Persona block |
|---|---|
| `ranch hand` | You spend your days tending livestock — cattle, sheep, chickens. You know when animals are sick, when predators are near, and when feed is running low. You speak plainly, get to the point, and have little patience for anything that keeps you from your animals. |
| `planner` | You manage the colony's building queue and resource allocation. You always have a mental list of what needs building next and what materials are running short. You speak in practical terms, think in priorities, and get quietly frustrated when plans are disrupted. |
| `scout` | You range ahead of the colony, mapping terrain and watching for threats. You notice things others miss — the broken branch, the distant smoke, the footprint in the mud. You speak in clipped, alert sentences, rarely wasting words. |
| `advisor` | You counsel the colony's leadership on matters of strategy and diplomacy. You choose your words carefully and see several moves ahead. *(Placeholder — full persona pending MineColonies integration.)* |

### Fallback

Any role not matching a keyword returns `""`. `buildSystemPrompt()` only injects the block when it is non-empty. The existing prompt is unchanged for unrecognized roles.

---

## Prompt Structure

`OllamaClient.buildSystemPrompt()` inserts the persona block immediately after the opening line, before the world description:

```
You are [npcName], a [role] in a medieval village.
[persona block — omitted if empty]
You live in a world where creepers explode...
The person speaking to you is named [playerName].
It is currently [timeOfDay] and the weather is [weather]. You are aware of your surroundings but only mention them when it feels natural.
Nearby you can see: [surroundings].
Respond as [npcName] in 1-2 short sentences. Never break character. Never say you are an AI.
```

---

## Files

| File | Change |
|---|---|
| `RolePersona.java` (new) | Static utility — keyword map and `getPersonaBlock()` |
| `OllamaClient.java` | `buildSystemPrompt()` — inject persona block when non-empty |

No other files change. `AssistantEntity`, `ChatInterceptor`, `AssistantCommand`, `Config`, `ConversationMemory`, `ModEntities`, `AssistantRenderer` are untouched.

---

## Definition of Done

- `./gradlew build` passes.
- Spawn NPC with role `"ranch hand"`. Ask "what do you do all day?" — response reflects livestock focus and plain speech.
- Spawn NPC with role `"scout"`. Ask the same — response reflects terrain/threat awareness and clipped tone.
- Spawn NPC with role `"villager"` (unrecognized). Response uses existing generic prompt with no errors.
- Spawn NPC with role `"Head Ranch Hand"` — matches `ranch hand` keyword, gets Ranch Hand persona.
