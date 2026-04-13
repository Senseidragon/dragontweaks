# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

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