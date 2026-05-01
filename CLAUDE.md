# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

If a filename or instruction contains an obvious typo, correct it using
common sense and proceed. Do not stop to investigate the typo. Do not
search for the misspelled filename. Assume the most reasonable
interpretation and execute it.

---
Do not think before writing code. Write the code directly.


## Hard Architectural Rules — Never Violate

1. **Nothing may ever block the main Minecraft game thread.** This is non-negotiable in the NeoForge/Minecraft ecosystem.
2. **All network calls (including OpenRouter HTTP requests) must be async on a separate thread.**
3. **Responses from async operations must be queued back to the main thread via server tick queue before any game interaction.**
4. **Zero interference with MineColonies internals.** All MineColonies integration via public API and event system only. Citizens are never directly modified.
5. **All MineColonies API dependencies must be verified against stubs in `docs/stubs/` before any dependent code is written.**

## Agent Behavior Rules — Never Violate

- **Do not perform exploratory reads before acting on unambiguous tasks.** Execute directly.
- **Do not read files you have not been explicitly directed to read.**
- **Specify exact file paths before making any changes.** No vague references.
- **Do not use class names from 1.20.x without verifying they exist in 1.21.1 sources.**
- **One concern at a time.** Do not refactor unrelated code while fixing a bug.

## Superpowers Integration

- **Always invoke the brainstorming skill before writing any new feature code.** Do not skip to implementation.
- **Do not skip the design approval step.** Wait for explicit approval before proceeding.
- **Use subagent-driven-development for any change touching more than one file.**
- **Plans must include exact file paths and complete code before execution begins.**

## Stub Library

API stubs for MineColonies and NeoForge are in `docs/stubs/`. The index is at `docs/STUB_INDEX.md`.
- Load only stubs directly relevant to the current task.
- Do not bulk-load entire categories.
- Verify API surface against stubs before writing any integration code.

## Build Commands

```bash
# Build the mod JAR
./gradlew build

# Refresh dependencies (if IDE or build is broken)
./gradlew --refresh-dependencies

# Clean build outputs (does not affect source)
./gradlew clean

# Run data generators (outputs to src/generated/resources/)
./gradlew runData

# Launch Minecraft client with the mod loaded
./gradlew runClient

# Launch dedicated server with the mod loaded
./gradlew runServer

# Run game tests
./gradlew runGameTestServer
```

The built JAR ends up in `build/libs/`. Mod metadata (version, id, etc.) is injected at build time from `gradle.properties` into `src/main/templates/META-INF/neoforge.mods.toml` via the `generateModMetadata` task.

## Architecture

This is a **NeoForge mod for Minecraft 1.21.1**, built with Java 21 and the NeoForge ModDevGradle plugin.

**Entry points:**
- `DragonTweaks.java` — Main mod class annotated with `@Mod`. Bootstraps all `DeferredRegister`s (blocks, items, creative tabs), registers event listeners on both the mod event bus and the NeoForge game event bus, and loads the config spec.
- `DragonTweaksClient.java` — Client-only counterpart annotated with `@Mod(dist = Dist.CLIENT)`. Registers the in-game config screen and handles client-side setup events. Code here is safe from server-side classloading.

**Registration pattern:**  
All game objects (blocks, items, creative tabs) use NeoForge's `DeferredRegister` pattern declared as `static final` fields on `DragonTweaks`. Registers must be bound to the mod event bus in the constructor before they take effect.

**Config:**  
`Config.java` uses `ModConfigSpec` (NeoForge's config API). The spec is registered in `DragonTweaks`'s constructor. Config values are accessed statically. Translations for config screen labels live in `src/main/resources/assets/dragontweaks/lang/en_us.json`.

**Resource/data generation:**  
Generated resources go to `src/generated/resources/` (via `runData`). This directory is already on the resource source set. BlockBench `.bbmodel` files and datagen cache files are excluded from the final JAR.

**Key version properties** (all in `gradle.properties`):
- Minecraft: `1.21.1`, NeoForge: `21.1.226`
- Parchment mappings: `2024.11.17` (better parameter names)
- Mod ID: `dragontweaks`, group: `io.github.senseidragon.dragontweaks`

## LLM Backend

- **Provider:** OpenRouter (server-side only)
- **API key:** Stored in `.env` on the server. Never transmitted to clients. Never logged.
- **Client architecture:** Clients never call OpenRouter directly. All LLM calls go through the server-side `OpenRouterClient`.
- **`max_tokens: 100` always** — NPC responses must be short. Never omit this.
- **`stream: false` always** — No streaming.
- **LLM responses are immersion only.** Flavor text, not functional output. Game logic must never depend on response content. Fallback templates are always acceptable.
