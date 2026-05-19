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

CLAUDE.md

7.69 KB •167 lines•Formatting may be inconsistent from source
# CLAUDE.md

Guidance for Claude Code when working in this repository.
Read this file completely before touching any source file. Do not summarize or skip sections.

---

## Session Startup — Required Every Time

1. Read `docs/devchatindex.md` — this is the entry point for all session context.
2. Follow its "What To Read For Which Task" table. Read only the docs listed for your task.
3. Confirm the current branch is `phase-1`.
4. Run `./gradlew build` and confirm it is clean.
5. Report branch and build status before proceeding.

Do not begin any task until all five steps are complete and reported.

---

## Agent Behavior Rules — Never Violate

- **No sub-agents. Ever.** Do not spawn subagents, parallel agents, or multi-agent workflows. All work is done sequentially by this agent alone.
- **Read before writing.** Always read a file before modifying it. Never modify a file you have not read in this session.
- **One task at a time.** Complete the current task, report results, and stop. Do not proceed without explicit direction.
- **Do not read files you have not been directed to read.** Stay on task. `docs/devchatindex.md` tells you exactly what to read for each task type.
- **Do not refactor unrelated code** while fixing a bug or implementing a feature.
- **Typos in instructions:** If a filename or instruction contains an obvious typo, correct it using common sense and proceed.
- **Never guess at root causes.** Trace the full execution path from uploaded source files before writing any fix.
- **Strip punctuation before string comparisons** in any trigger matching code.

---

## Code Investigation & Change Protocol

1. **Inspect files directly in the repo.** Do not ask the user to paste source code.
2. **Make the smallest safe change** that achieves the stated goal. No scope creep.
3. **Do not refactor unrelated code** while fixing a bug or implementing a feature.
4. **Run `./gradlew build` after every change** (or describe why a build is not applicable).
5. **Report changed files and any risks** before closing the task.

---

## Hard Architectural Rules — Never Violate

1. **Nothing may ever block the main Minecraft game thread.** Non-negotiable under any circumstances.
2. **All network calls must be async** using `HttpClient.sendAsync()` + `CompletableFuture` + `orTimeout()`. Blocking `send()` is never acceptable — not in production, not in prototypes.
3. **Async responses must be queued back to the main thread** via `server.execute()` before any game interaction.

   Mandatory async pattern:
   ```java
   httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
       .orTimeout(Config.LLM_TIMEOUT_SECONDS.get(), TimeUnit.SECONDS)
       .thenApply(parseResponse)
       .thenAccept(reply -> server.execute(() -> { /* game thread work */ }))
       .exceptionally(ex -> { LOGGER.warn(...); return null; });
   ```

4. **Zero interference with MineColonies internals.** All integration via public API and event bus only. Citizens are never directly modified.
5. **All MineColonies API calls must be verified against stubs in `docs/stubs/`** before any dependent code is written. Report what you find before writing. Use `docs/STUB_INDEX.md` to locate specific stubs.
6. **Never hardcode config values.** All tunable values live in `Config.java`.
7. **API key lives in `.env` only.** Never in source, never logged, never transmitted to clients.
8. **LLM responses are immersion only.** Game logic must never depend on LLM response content. Fallback templates are always acceptable.

---

## LLM Client

- **Provider:** OpenRouter
- **Endpoint:** `https://openrouter.ai/api/v1/chat/completions`
- **Client class:** `LLMClient.java` — do not rename, do not recreate, do not add a second HTTP client.
- **Model:** `google/gemma-4-26b-a4b-it` (from `Config.java` — do not hardcode)
- **`max_tokens`: 200** for all standard NPC calls. Functional roles (Advisor, Planner) may require higher budgets — check the spec before changing this value.
- **`stream: false` always.**
- **Reasoning block:** Commented out in `LLMClient.java` — intentional. Current model rejects `effort:none`. Do not uncomment without explicit instruction.

---

## MineColonies API Patterns

- **MineColonies guard:** Always wrap calls with `if (!ModList.get().isLoaded("minecolonies")) return;`
- **Event subscription:** `IMinecoloniesAPI.getInstance().getEventBus().subscribe(...)` — not the NeoForge event bus.
- **Colony lookup:** `IColonyManager.getInstance().getColonies(level)` → `List<IColony>`
- **Raid state:** `colony.getRaiderManager().isRaided()` — poll only; no subscribable raid-started event.
- **Colony age:** `IColony.getDay()` → `int`
- **Colony boundary:** `IColony.isCoordInColony(world, pos)`
- **Town Hall level:** `colony.getServerBuildingManager().getTownHall().getBuildingLevel()`
- **Happiness factors:** Ten canonical IDs only — `food`, `slepttonight`, `housing`, `health`, `unemployment`, `idleatjob`, `security`, `school`, `social`, `mystical`
- **No native commute factor** — derive commute distance from building positions manually.
- **`doDaylightCycle` gamerule** — query via Minecraft's native `GameRules` system, not MineColonies API.
- **Warehouse stock:** `AbstractTileEntityWareHouse.getMatchingItemStacksInWarehouse(Predicate<ItemStack>)` — query per material; no full-dump method exists.

---

## NeoForge 1.21.1 — Class Name Traps

These names do not exist in NeoForge 1.21.1. Using them will break the build:

| Wrong (do not use) | Correct |
|---|---|
| `HumanoidMob` | `PathfinderMob` |
| `setHomePosAndDistance(BlockPos, int)` | `restrictTo(BlockPos, int)` on `Mob` |
| `RestrictedWaterAvoidingRandomWalkingGoal` | `WaterAvoidingRandomStrollGoal` + `MoveTowardsRestrictionGoal` |
| `OllamaClient` | Does not exist — LLM client is `LLMClient.java` |

Before using any class name not in the above table, verify it exists in NeoForge 1.21.1 sources. Do not assume 1.20.x names carry over.

---

## NPC Wander Restriction Pattern

- `this.restrictTo(blockPos, radius)` on `Mob` sets home anchor and radius.
- `WaterAvoidingRandomStrollGoal` respects restriction automatically.
- Add `MoveTowardsRestrictionGoal` alongside it.
- Call `restrictTo()` at spawn via `finalizeSpawn()` override.
- Call `restrictTo()` again on stop/stay command using `npc.blockPosition()` as the new anchor.

---

## Files You Must Not Modify Without Explicit Instruction

- `DragonTweaks.java` — restructure only if explicitly directed
- `LLMClient.java`
- `Config.java`
- `RoleAssignmentData.java`
- `ColonyDiagnosticReport.java`
- `PlannerDependencyRegistry.java`

---

## Source of Truth for Current File Status

`docs/current_state.md` is the authoritative list of all source files and their completion status. Check it before assuming any file does or does not exist. Do not rely on memory or this file for that information.

---

## Build Commands

```bash
./gradlew build              # Standard build — run after every change
./gradlew clean              # Clean build outputs
./gradlew --refresh-dependencies
./gradlew runData            # Run data generators
./gradlew runClient          # Launch client with mod loaded
./gradlew runServer          # Launch server with mod loaded
```

Built JAR ends up in `build/libs/`.

---

## Project Identity

| Field | Value |
|---|---|
| Mod | DragonTweaks (Assistant Mod — final name TBD) |
| Mod ID | `dragontweaks` |
| Package | `io.github.senseidragon.dragontweaks` |
| Source root | `src/main/java/io/github/senseidragon/dragontweaks/` |
| Branch | `phase-1` |
| Platform | NeoForge 21.1.226, Java 21, Minecraft 1.21.1 |
| Parchment mappings | `2024.11.17` |
| MineColonies API stubs | `docs/stubs/` — index at `docs/STUB_INDEX.md` |

---

## Session Closeout — Required on Every Task Completion

After completing any task that touches source files, config, or project structure:

1. Update `docs/devchat.md`:
   - Set `Last updated` date at the top.
   - Update the file table (`What Exists Right Now`) for any files added, removed, or changed.
   - Add a session note under `Session Notes`.
2. If `./gradlew build` passed, update `dragontweaks_verification_checklist.md` to reflect newly verified behaviors.

Do not skip this step. Do not mark a task complete without doing it first.
