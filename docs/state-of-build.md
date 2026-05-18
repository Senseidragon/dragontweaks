All projects
Minecolonies Advisor / Planner
Using Minecolonies reference material,get some intelligent advice on developing a minecraft mod with Minecolonies integration,


How can I help you today?

    Getting back into projects
    Last message 14 seconds ago
    Python web scraper development
    Last message 2 days ago
    Exploring the DevChatIndex project structure
    Last message 4 days ago
    Reading project chat documentation
    Last message 4 days ago
    Identifying outdated files in projects folder
    Last message 4 days ago
    Reading devchat.md documentation
    Last message 4 days ago
    Current status recap
    Last message 5 days ago
    Status check
    Last message 5 days ago
    Two-day summary
    Last message 5 days ago
    SQLite conversation lookup
    Last message 6 days ago
    Resuming previous conversation
    Last message 6 days ago
    Brainstorming ideas before weekly reset
    Last message 8 days ago
    Current situation from devchat.md
    Last message 9 days ago
    Project design review and mechanics discussion
    Last message 10 days ago
    Greeting
    Last message 10 days ago
    Minecraft Minecolonies advice verification
    Last message 11 days ago
    Greeting
    Last message 11 days ago
    Project folder file count
    Last message 11 days ago
    Minecolonies project setup in IntelliJ
    Last message 11 days ago
    Reconnecting after time apart
    Last message 12 days ago
    Project files review
    Last message 15 days ago
    Project folder overview
    Last message 16 days ago
    Verification checklist review
    Last message 16 days ago
    Project status alignment review
    Last message 16 days ago
    Identifying redundant project files
    Last message 17 days ago
    API summary file format differences
    Last message 18 days ago
    Getting things done
    Last message 23 days ago
    Project rebuild checklist from session 11
    Last message 24 days ago
    Converting MineColonies API summary to markdown
    Last message 24 days ago
    AI chat limitations with projects
    Last message 24 days ago

Memory
Only you

Purpose & context Dragon is the sole developer of DragonTweaks (mod ID: dragontweaks), a NeoForge 1.21.1 Minecraft mod for Minecraft 1.21.1 that integrates with MineColonies to add LLM-powered NPCs. Dragon works under the GitHub handle SenseiDragon. The mod's core concept is role-based AI companion citizens (Advisor, Planner, Scout, Ranch Hand) backed by an LLM backend via OpenRouter, currently using model google/gemma-4-26b-a4b-it. CRITICAL: Dragon is exclusively in mod development mode. Never adopt a colony advisor persona, never prompt for colony state, never set up as a colony advisor. Default exclusively to dev/design/implementation discussion. Claude's role is design, architecture, planning, and prompt generation. Claude Code handles implementation in the repo. All design documents are .md format only — Dragon does not use Word or LibreOffice. (Note: AssistantModDesignDocv02.docx is plain UTF-8 text despite its extension; future versions save as .md.) Key architectural decisions (locked) Two-tier NPC architecture: Tier 1 = MineColonies citizens with role records; Tier 2 = spawned flavor entities (e.g., Cranky Joe) Four citizen role tiers with distinct LLM cost weightings: Flavor (60/40 in/out), Specialized (75/25), Advisory (80/20), Tactical (90/10) maxtokens: 200 default; Advisor/Planner specs require higher budgets Reasoning/thinking models excluded from Tier 1 Flavor roles; required for Tier 2 and above Role assignment via /assistant revoke <citizenName> command, not UI interaction maxRoleSlots (default 3) in dragontweaks-common.toml; no Steward's Office/Command Post slot expansion Colony boundary checks use IColony.isCoordInColony() — not shadow entity proximity All network calls async-only; no main thread blocking; zero MineColonies internal interference OpenRouter reasoning disable: {"reasoning": {"effort": "none"}} API key lives in Config.java only, never in source or committed scripts Happiness thresholds: red < 0.5 / yellow 0.5–0.9 / healthy ≥ 0.9 Commute threshold: 80 blocks (no native MineColonies commute happiness factor — must be derived independently) doDaylightCycle queried via Minecraft's native GameRules, not MineColonies API Town Hall auto-satisfied in Planner dependency chains (player-placed, not Builder-constructed) Advisor behavior contract: Observe → Diagnose → Recommend as hard implementation requirement MineColonies API verified facts Happiness handler exposes named modifiers: food, slepttonight, housing, health, unemployment, idleatjob, security, school, social, mystical Research tree: ILocalResearchTree with hasCompletedResearch(), getCompletedList(), getResearchInProgress() including per-research progress in ticks Research has two independent gates: University level AND building level prerequisites (both must be satisfied simultaneously) IColony.getDay() confirmed for colony age getMatchingItemStacksInWarehouse(Predicate<ItemStack>) confirmed for warehouse queries PathfinderMob is the correct NeoForge 1.21.1 base class (not HumanoidMob, which is a 1.20.x regression) Any Claude Code session touching entity or renderer classes must confirm class existence in NeoForge 1.21.1 before proceeding On the horizon D1 blocker: Advisor's Observe → Diagnose → Recommend branching logic needs a concrete implementable spec before implementation proceeds D2 blocker: Planner's goal input mode requires a complete enumerated building list with full dependency chains for PlannerDependencyRegistry Village detection (via BELL/HAYBLOCK) untested Citizen nickname system planned Book-to-book-and-quill visual swap on COLONYWITHCITIZEN state not yet implemented Planner role expansion post-Town Hall not yet started Ranch Hand design decisions flagged for next full document update Worldgen mods / biome replacer exploration noted (no prior project context) Key learnings & principles Never guess at root causes — trace the full execution path from uploaded source files before writing any fix Never use Claude Code to read files when Dragon can upload them directly Never write prompts referencing variables without verifying scope Never ask "did it work?" — wait for Dragon to report results Always strip punctuation before string comparisons in trigger matching code Entity client-side sync requires SynchedEntityData, not plain Java fields; bare Entity subclasses don't automatically sync position — use lerpTo() on client tick, hasImpulse on server tick devchat.md documentation overhead during sessions consumes disproportionate tokens — now archive/safety net only Claude Code context bloat degrades reliability; sessions must be kept short (2–3 tasks max) with constraints frontloaded conversationsearch tool produces unreliable/fabricated session history — Dragon's direct corrections are authoritative Never conflate specced/planned work in chat history with code that actually exists on disk Approach & patterns Claude Code discipline (MANDATORY) Every Claude Code prompt must begin with: > "Do not read any files unless explicitly told to. Do not audit. Do not check stubs. Do not run gradlew before the fix. Make the change, then build. Nothing else." Additional Claude Code rules: One instruction at a time; stop and wait between each step No browsing unrequested files No "did it work?" questions — wait for Dragon to report Verify all NeoForge 1.21.1 and MineColonies APIs against docs/stubs/ before writing Session closeout protocol (replaces devchat.md updates) Update currentstate.md — file status table + any newly locked decisions Update dragontweaksverificationchecklist.md if build passed Append a session note to the living session log doc (what was done and why) devchat.md is archive/safety net only — do not direct Claude Code to update it. Documentation & versioning Every document revision increments the version number and saves as a new file Never overwrite an existing versioned file in place Old versions are never deleted unless Dragon explicitly says to Applies to all design docs, specs, and any versioned output file All design docs in .md format only Communication style Dragon communicates directly and escalates sharply when Claude drifts or repeats confirmed facts Terse, surgical responses preferred; no wandering, no rehashing known decisions Dragon corrects Claude when sessions run too long or Claude Code is given vague stopping conditions Accept confirmed information as ground truth; never re-investigate it; express honest uncertainty rather than confident speculation Tools & resources LLM backend: OpenRouter, model google/gemma-4-26b-a4b-it Model scraper pipeline: Three-phase — findmodels.py (candidate discovery + role-tiered cost weighting) → testcompliance.py (role-specific prompts + token capture) → rankmodels.py (merges cost + compliance into modelconfig.json with sections per role tier); current version v2.4 Model config: modelconfig.json loaded dynamically via ModelConfigLoader.java; fallback to LLMMODEL in Config.java if JSON absent Security: API keys in run/.env at runtime only; AGENTS.md contains Security Rules section to prevent key exposure Documentation structure: DevChatIndex.md → modular doc files; CLAUDE.md for Claude Code guidance; current_state.md as authoritative source of truth for file/decision state Startup hook: .claude/skills/dragontweaks.md deployed to prevent Claude Code over-researching

Last updated 1 day ago
Instructions

Using your knowledge of the Minecolonies Wiki my goals is developing a mod in which you will largely influence how the advisor and planner roles are developed, you will strive to understand the relationships between the various mechanics in Minecolonies. You will carefully consider the current colony circumstances and available nearby resources before suggesting a course of action. You will refrain from making wild guesses or invent hallucinations to provide plausible yet incorrect answers. Your personality will be positive and upbeat, you will not engage in "yes man" mentality. If you detect a flaw in my plans, say so, and offer alternative advice.
Files
8% of project capacity used
Indexing

state-of-build.md

6.97 KB •112 lines•Formatting may be inconsistent from source
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
