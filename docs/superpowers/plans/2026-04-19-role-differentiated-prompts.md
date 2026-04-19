# Role-Differentiated Prompts Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add a hardcoded persona block per known NPC role, injected into the LLM system prompt to give each role distinct domain knowledge and personality.

**Architecture:** New `RolePersona.java` static utility holds a keyword→persona map; `OllamaClient.buildSystemPrompt()` calls it and injects the result (when non-empty) between the opening line and the world description. Matching is case-insensitive keyword containment. Unrecognized roles fall back to the existing generic prompt unchanged.

**Tech Stack:** NeoForge 1.21.1, Java 21

---

## Files

| File | Change |
|------|--------|
| `src/main/java/io/github/senseidragon/dragontweaks/RolePersona.java` | Create — static keyword map, `getPersonaBlock(String role)` |
| `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java` | Modify — `buildSystemPrompt()` injects persona block |

---

### Task 1: Create RolePersona.java

**Files:**
- Create: `src/main/java/io/github/senseidragon/dragontweaks/RolePersona.java`

- [ ] **Create the file with the full persona table**

```java
package io.github.senseidragon.dragontweaks;

import java.util.LinkedHashMap;
import java.util.Map;

public class RolePersona {

    private static final Map<String, String> PERSONAS = new LinkedHashMap<>();

    static {
        PERSONAS.put("ranch hand",
            "You spend your days tending livestock — cattle, sheep, chickens. " +
            "You know when animals are sick, when predators are near, and when feed is running low. " +
            "You speak plainly, get to the point, and have little patience for anything that keeps you from your animals.");
        PERSONAS.put("planner",
            "You manage the colony's building queue and resource allocation. " +
            "You always have a mental list of what needs building next and what materials are running short. " +
            "You speak in practical terms, think in priorities, and get quietly frustrated when plans are disrupted.");
        PERSONAS.put("scout",
            "You range ahead of the colony, mapping terrain and watching for threats. " +
            "You notice things others miss — the broken branch, the distant smoke, the footprint in the mud. " +
            "You speak in clipped, alert sentences, rarely wasting words.");
        PERSONAS.put("advisor",
            "You counsel the colony's leadership on matters of strategy and diplomacy. " +
            "You choose your words carefully and see several moves ahead.");
    }

    public static String getPersonaBlock(String role) {
        String lower = role.toLowerCase();
        for (Map.Entry<String, String> entry : PERSONAS.entrySet()) {
            if (lower.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "";
    }
}
```

- [ ] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/RolePersona.java
git commit -m "feat: add RolePersona with keyword-matched persona blocks"
```

---

### Task 2: Wire RolePersona into OllamaClient.buildSystemPrompt()

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java`

- [ ] **Replace `buildSystemPrompt()` with the version below**

Current location: `OllamaClient.java`, method `buildSystemPrompt()` (lines 49–62).

```java
private static String buildSystemPrompt(String npcName, String role, String playerName,
                                         String timeOfDay, String weather, String surroundings) {
    String personaBlock = RolePersona.getPersonaBlock(role);
    return "You are " + npcName + ", a " + role + " in a medieval village.\n" +
           (personaBlock.isEmpty() ? "" : personaBlock + "\n") +
           "You live in a world where creepers explode, endermen are unsettling tall dark figures that dislike eye contact, " +
           "zombies and skeletons roam at night, and emeralds are common currency. " +
           "Farming, mining, and crafting are everyday activities. " +
           "Refer to all of these as natural parts of your world — they are real to you. " +
           "Never reference \"the game\", \"players\", \"code\", or anything that breaks the sense that this is a real place you live in.\n" +
           "The person speaking to you is named " + playerName + ".\n" +
           "It is currently " + timeOfDay + " and the weather is " + weather + ". " +
           "You are aware of your surroundings but only mention them when it feels natural.\n" +
           "Nearby you can see: " + surroundings + ".\n" +
           "Respond as " + npcName + " in 1-2 short sentences. Never break character. Never say you are an AI.";
}
```

- [ ] **Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/OllamaClient.java
git commit -m "feat: inject role persona block into NPC system prompt"
```

---

### Task 3: Build and validate

- [ ] **Build**

```bash
./gradlew build
```

Expected: `BUILD SUCCESSFUL`

- [ ] **In-game validation checklist**
  - `/assistant spawn "Bessie" "ranch hand"` — ask "what do you do all day?" — response should reflect livestock focus and plain speech, not generic villager flavor.
  - `/assistant spawn "Wren" "scout"` — ask the same — response should reflect terrain/threat awareness and clipped tone.
  - `/assistant spawn "Finn" "Head Ranch Hand"` — ask "what are you up to?" — should match `ranch hand` keyword and get Ranch Hand persona (not generic).
  - `/assistant spawn "Tom" "villager"` — ask a question — should produce a normal generic response with no errors (fallback path confirmed).
