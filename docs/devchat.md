# DragonTweaks — Current State Document
*Replaces the original devchat.md as the active reference for Claude Code.*
*Full session history archived in devchat_archive.md — do not delete.*
*Last updated: 2026-05-01*

---

## How To Operate

You execute one instruction at a time. After completing each instruction you STOP and report what you did. You do not proceed to the next step without explicit direction. You do not plan ahead. You do not explore. You do not search for context unless a specific instruction tells you to open a specific file and look for a specific thing. When in doubt, stop and ask.

---

## Project Identity

| Field | Value |
|---|---|
| Mod Name (working) | The Assistant Mod (final name TBD) |
| Repo | https://github.com/Senseidragon/dragontweaks |
| Branch | phase-1 |
| Mod Loader | NeoForge 21.1.226 |
| Minecraft | 1.21.1 |
| Java | 21+ |
| Mod ID | dragontweaks |
| Package | io.github.senseidragon.dragontweaks |

---

## Hard Architectural Rules — Never Violate

1. **Nothing may ever block the main Minecraft game thread.**
2. **All network calls (including OpenRouter HTTP requests) must be async on a separate thread.**
3. **Responses from async operations must be queued back to the main thread via server tick queue before any game interaction.**
4. **Zero interference with MineColonies internals.** All integration via public API and event system only. Citizens are never directly modified.
5. **All MineColonies API dependencies must be verified against stubs in `docs/stubs/` before any dependent code is written.**

---

## LLM Backend — COMPLETE. NO ACTION REQUIRED.

`OllamaClient.java` is gone. It has been fully replaced by `LLMClient.java` using OpenRouter. Do not look for OllamaClient. Do not rename or migrate anything.

| Parameter | Value |
|---|---|
| Provider | OpenRouter |
| Endpoint | https://openrouter.ai/api/v1/chat/completions |
| Model | google/gemma-4-26b-a4b-it |
| Auth | `Authorization: Bearer <key>` — key in `.env` only, never hardcoded, never committed |
| Request format | OpenAI messages array: `[{"role":"system","content":"..."},{"role":"user","content":"..."}]` |
| max_tokens | 100 — always, never omit |
| stream | false — always |
| Thinking mode | `"reasoning": {"effort": "none"}` — disable thinking |

LLM responses are immersion only — flavor text, not functional output. Game logic must never depend on response content. Fallback templates are always acceptable.

---

## What Exists Right Now

All source files are in `src/main/java/io/github/senseidragon/dragontweaks/`.

| File | Status | Notes |
|---|---|---|
| `AssistantCommand.java` | ✅ Complete | `/assistant` command handler |
| `AssistantEntity.java` | ✅ Complete | NPC entity, follow/stop, proximity, role, NBT persistence |
| `AssistantRenderer.java` | ✅ Complete | Placeholder zombie renderer |
| `AssistantRoleRecord.java` | ✅ Complete | Data record: citizenId (int), roleType, assignmentTimestamp, playerUUID, shadowEntityUUID — citizenId added 2026-04-30; shadowEntityUUID is intentional, do not remove |
| `ChatInterceptor.java` | ✅ Complete | Intercepts player chat, routes to LLM, multi-NPC addressing |
| `Config.java` | ✅ Complete | NeoForge ModConfigSpec. See Config section below. |
| `ConversationMemory.java` | ✅ Complete | Per-NPC conversation history |
| `DragonTweaks.java` | ✅ Complete | Main mod class, event bus registration — LevelEvent.Load handler added 2026-05-01 to wire RoleAssignmentData on overworld load |
| `DragonTweaksClient.java` | ✅ Complete | Client-only setup |
| `DragonTweaksClientEvents.java` | ✅ Complete | Client event bus subscriber |
| `EnvLoader.java` | ✅ Complete | Reads `.env` file for API key |
| `FollowPlayerGoal.java` | ✅ Complete | AI goal for follow behavior |
| `LLMClient.java` | ✅ Complete | OpenRouter async HTTP client |
| `ModEntities.java` | ✅ Complete | Entity type registration |
| `ObservationTicker.java` | ✅ Complete | Proactive NPC observations on server tick — 5 bugs fixed 2026-04-30, see Session Notes |
| `RolePersona.java` | ✅ Complete | Role keyword → persona block mapping |
| `RoleAssignmentData.java` | ✅ Complete | SavedData for role assignments — file exists and is correct; updated 2026-04-30 to match AssistantRoleRecord signature |
| `RoleAssignmentScreen.java` | ❌ Does not exist | Client-side role assignment UI |
| `CitizenInteractDetector.java` | ❌ Does not exist | Right-click citizen detection |

---

## Config.java — Known Values

Verify exact field names against source before referencing.

**Currently exists:**
- `LLM_ENDPOINT` — OpenRouter endpoint URL
- `LLM_MODEL` — model string, default `google/gemma-4-26b-a4b-it`
- `NPC_OBSERVATIONS_ENABLED` — boolean, default true
- `NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS` — int, default 15
- `NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS` — int, default 60

**Does not exist yet — to be added:**
- *(none — `COMMAND_RADIUS` was eliminated; detection radius and command radius are the same value, read from existing detection config. Do not add a separate COMMAND_RADIUS entry.)*

---

## Design Decisions — Locked

### Proximity Threshold
- 10 blocks XZ radius, ±5 blocks Y tolerance (cylindrical, not spherical)
- Detection radius and command radius are the same value — one config entry, not two. Never hardcode. All proximity checks read from config at runtime.

### Role Slot Expansion
- Tied to Town Hall level. No separate building.
- Default (TH below level 3): 3 slots / TH Level 3: 4 slots / TH Level 4: 5 slots / TH Level 5: 6 slots
- Hard cap: 6
- No hardcoded `MAX_SLOTS` constant anywhere. Query TH level dynamically at runtime.

### Scout Patrol
- Periodic AABB detection sweep on a timer. No pathfinding. No waypoints. No navigation goal stack.
- Applies to Phase 3 — not current work. Do not implement anything for this now.

---

## What To Build Next

**You will receive one instruction at a time from the player. Do not read ahead and begin implementing future steps. Execute only what you are told.**

The sequence below is for the player's reference. It is not an invitation for you to begin executing all steps at once.

### ~~Step 1~~ ✅ Done — No existing SavedData wiring found
Neither `DragonTweaks.java` nor `AssistantCommand.java` contained any reference to `SavedData` or `dragontweaks_roles`. Confirmed 2026-05-01.

### ~~Step 2~~ ✅ Done — See Step 1

### ~~Step 3~~ ✅ Done — RoleAssignmentData fully wired
`RoleAssignmentData.java` existed and is correct. A `LevelEvent.Load` handler was added to `DragonTweaks.java` that guards for `ServerLevel` and overworld dimension, then calls `RoleAssignmentData.get(serverLevel)` to initialize and attach the SavedData. Player launched the game, loaded a world, confirmed no `SavedData` exceptions and no `dragontweaks_roles` errors in logs. Wiring confirmed good. Confirmed 2026-05-01.

### Pre-Step-4 Tasks — Complete before citizen interaction work

These items are small and targeted. Complete them in order before proceeding to Step 4.

1. **`/assistant locale [code]`** — debug command; injects locale string into session context; unrecognized codes fall back to `en_us` with logged warning; `/assistant locale reset` clears override. See Locale architectural decision in verification checklist.
2. **Observation ticker idle cooldown tuning** — increase passive comment interval. Config value change only — no logic changes.
3. **"Hmm..." refactor** — remove as standard acknowledgment; retain only as timeout fallback.
4. **LLM hard requirement enforcement** — mod fails to start without valid API key.
5. **SavedData persistence smoke test** — write a role assignment via command or console, do a full JVM restart, confirm the record survives. Not yet done. Must pass before proceeding to Step 4.

### Step 4 — Build `CitizenInteractDetector.java`
- Verify correct NeoForge 1.21.1 event for player→entity right-click before writing.
- Verify how to identify a MineColonies citizen entity from the interaction event.
- Verify how to retrieve `ICitizenData` — check null safety.
- On right-click of unemployed citizen with available slot: open `RoleAssignmentScreen`.
- On right-click of already-assigned citizen: pass through to MineColonies normally.

### Step 5 — Network packets
- Server → client: citizen name, citizen ID, slots used, slots max, role list.
- Client → server: citizen ID, selected role name.
- Verify correct NeoForge 1.21.1 packet registration pattern before writing.

### Step 6 — Build `RoleAssignmentScreen.java`
- Client-side only.
- Vanilla Minecraft chrome — `renderBackground()` and standard panel/border textures.
- Reference `AbstractContainerScreen` or equivalent in 1.21.1 sources. Do not invent a look.
- Title: "Assign Role". Citizen name below title.
- Slot counter: "X / Y slots used" — from server packet, not hardcoded.
- Scrollable role list, one button per role. No hardcoded role count.
- Cancel button closes screen. Assign button disabled until role selected, sends confirmation packet on click.

### Step 7 — Add `/assistant revoke <citizenName>` to `AssistantCommand.java`
- Partial case-insensitive name match.
- On match: remove record, release slot, confirm to player.
- No match: inform player clearly.

### Validation
- `./gradlew build` passes
- Right-clicking unemployed citizen opens `RoleAssignmentScreen`
- Screen shows correct citizen name and dynamic slot count
- Assign writes the record
- `/assistant revoke` releases the slot
- Right-clicking assigned citizen passes through to MineColonies normally

---

## MineColonies API Reference

Stubs in `docs/stubs/`. Index at `docs/STUB_INDEX.md`. Load only stubs relevant to the current task.

**Verified API calls:**
- `IMinecoloniesAPI.getInstance().getColonyManager().getIColonyByOwner(world, player)` — get player's colony
- `colony.getServerBuildingManager()` — access building manager
- `ICitizenData.getCitizenHappinessHandler()` — happiness factors
- `CitizenJobChangedModEvent` — role revocation trigger
- `CitizenDiedModEvent` — role cleanup trigger

Town Hall level query — verify against stubs before use.

---

## Session Notes — 2026-04-30

### ObservationTicker.java — 5 bugs found and fixed

All bugs were introduced in commit `d8df91b` ("fix: fix neutralized commentary sequencing and per-mob alert isolation"). The version at `a5ce271` (just before that commit) was clean and correct. The threat subsystem was added in `d8df91b` and broken on arrival.

**Bug 1 — `anyActiveThreats` always false.**
Declared `false`, never set to `true`. The entire threat-detection fire path was dead. Fix: set `anyActiveThreats = true` inside the mob loop when `mob.getTarget() != null`.

**Bug 2 — Tick throttle declared but not wired.**
`tickCounter` and `TICK_INTERVAL = 100` existed as fields but `onServerTick` ran every tick with no guard. Fix: added `if (++tickCounter % TICK_INTERVAL != 0) return;` as the first line of `onServerTick`.

**Bug 3 — Mob tracking loop ran twice.**
The loop updating `lastKnownHealth`, `lastMobTarget`, and `lastMobRef` appeared verbatim at lines 126–133 and again at 166–172. Second occurrence removed entirely.

**Bug 4 — All-clear observe fired for non-threat mobs.**
In the mob-exit cleanup loop, the 30% `LLMClient.observe` call fired whenever any mob left the AABB regardless of whether `npcThreatState` was ever true. Fix: wrapped the observe call with `if (npcThreatState.getOrDefault(npcId, false) && ...)`.

**Bug 5 — `hostilePresentMap` declared and never referenced.**
Field removed entirely.

Build clean after all five fixes.

### Threat state design — confirmed intent

The threat system uses a state-flip model. The NPC comments exactly once per state change:
- Hostiles present when previously clear → one comment, state flips to true
- Hostiles clear when previously present → one comment (30% chance, optional flavor), state flips to false
- Additional mobs arriving during an already-active threat → silence
- Slime splits (new child UUIDs) → silence

Do not add additional tiers or complexity beyond this.

### AssistantRoleRecord.java — citizenId added

The record was missing `int citizenId` as the first field. Added. The full record signature is now:

```java
public record AssistantRoleRecord(int citizenId, String roleType, long assignmentTimestamp, UUID playerUUID, UUID shadowEntityUUID)
```

`shadowEntityUUID` is intentional and correct — do not remove it.

Two call sites in `RoleAssignmentData.java` (`assign()` at line 34, `load()` at line 87) were updated to pass `citizenId` as the first constructor argument. Build clean.

### RoleAssignmentData.java — already existed

The devchat.md incorrectly listed this file as not existing. It was present and correct: proper NeoForge 1.21.1 `SavedData` pattern, all required methods (`assign`, `revoke`, `getRecord`, `isAssigned`, `getAssignedCount`), NBT serialization, static `get(ServerLevel)` factory. Table updated accordingly.

### Audit findings — no action required

- `AssistantEntity.java`: `addAdditionalSaveData` and `readAdditionalSaveData` both correctly implemented against spec.
- `ConversationMemory.java`: `restoreAll` signature is `Map<String, Deque<String>>`, not `Map<String, List<String>>` as the spec text said. The implementation is self-consistent — `AssistantEntity` builds `Deque` objects and passes them in. This is a doc error, not a code error. No fix needed.

---

## Session Notes — 2026-05-01

### Steps 1–3 completed

**Steps 1 & 2 — SavedData wiring check:** Grepped both `DragonTweaks.java` and `AssistantCommand.java` for `SavedData` and `dragontweaks_roles`. Zero matches in both files. No prior wiring existed.

**Step 3 — LevelEvent.Load handler added to DragonTweaks.java:**
Added a `NeoForge.EVENT_BUS.addListener` call in the `DragonTweaks` constructor. The handler casts `LevelEvent.Load.getLevel()` to `ServerLevel`, guards on `serverLevel.dimension() == Level.OVERWORLD`, then calls `RoleAssignmentData.get(serverLevel)`. This initializes the SavedData and attaches it to the level on every overworld load.

**RoleAssignmentData.java corruption fixed:** A prior tool interaction injected raw instruction text into lines 79–80 of the file, breaking the `load()` method. Fixed to `RoleAssignmentData data = new RoleAssignmentData();`. Build clean.

**In-game sanity check passed:** Player launched the game, loaded a world, confirmed no `SavedData` exceptions and no `dragontweaks_roles` errors in logs. Wiring confirmed good.

---

## Known Deferred Items

- NPC cross-awareness — low priority, revisit Phase 4
- Shadow entity multiplayer visibility — test Phase 2
- Ranch Hand max scan radius — test Phase 2
- Mod final name — TBD