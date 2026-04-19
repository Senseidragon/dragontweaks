# Proactive NPC Observations — Design Spec

**Date:** 2026-04-19
**Status:** Approved
**Estimate:** 2.5–3 hours / 1 session (ObservationTicker.java is the risk item — see implementation notes)

---

## Summary

AssistantEntity NPCs currently respond only when spoken to. This feature adds a periodic awareness tick: each NPC scans its surroundings on a server-side interval, diffs the result against its last scan, and fires an unprompted Ollama observation if something notable appeared and the relevant cooldown has elapsed. A cow standing in the same spot for an hour triggers nothing. A creeper walking into range triggers a reaction within 15 seconds.

---

## Architecture

`ObservationTicker` is a new event handler class, registered in `DragonTweaks.java` alongside `ChatInterceptor`. It listens to `ServerTickEvent` and processes all loaded `AssistantEntity` instances every 100 ticks (5 seconds). It owns all proactive observation state in static maps keyed by NPC UUID.

`OllamaClient` gains two additions: `scanSurroundingsRaw()` (structured scan result used by the ticker) and `observe()` (proactive prompt, distinct from `query()`). The existing `scanSurroundings()` string-formatting method is refactored to call `scanSurroundingsRaw()` internally — no change to its callers.

---

## Config Changes (`Config.java`)

Three new values added to the existing `ModConfigSpec`:

| Key | Type | Default | Range | Description |
|-----|------|---------|-------|-------------|
| `npcObservationsEnabled` | Boolean | `true` | — | Master switch. When false, ObservationTicker skips all entities. |
| `npcObservationHostileCooldownSeconds` | Int | `15` | 5–300 | Min seconds between hostile observations **per NPC**. |
| `npcObservationPassiveCooldownSeconds` | Int | `60` | 15–600 | Min seconds between passive observations **per NPC**. |

Cooldowns are per-entity: NPC A's hostile cooldown is independent of NPC B's. One NPC observing a creeper does not block any other NPC from observing.

---

## `OllamaClient` Changes

### `scanSurroundingsRaw(ServerLevel level, AssistantEntity npc) → Map<String, Boolean>`

New package-private static method. Returns a map of entity display name → isHostile for all entities within `NPC_AWARENESS_RADIUS` matching `NPC_AWARENESS_CATEGORY`, excluding `AssistantEntity`, `Player`, and `ItemEntity`. `isHostile` is true when `MobCategory == MONSTER`.

### `scanSurroundings()` refactor

Becomes a wrapper: calls `scanSurroundingsRaw()`, formats its result into the existing comma-separated string. No change to callers (`ChatInterceptor`).

### `observe(MinecraftServer, ServerPlayer, Component entityName, String role, String timeOfDay, String weather, String surroundings, String whatChanged)`

New public static method. Distinct from `query()` in three ways:
- No "Hmm..." acknowledgment — the NPC initiates, no player message to acknowledge.
- No history injection — an unprompted observation is not a `player: X / npc: Y` exchange.
- Response is NOT written to `ConversationMemory`.

Prompt sent to Ollama:
```
System: [buildSystemPrompt — character, role persona, world context, time, weather, surroundings]
Prompt:  "You just noticed: {whatChanged}. React in character in 1-2 short sentences. Address {playerName} directly."
```

Response is queued back to the game thread via `server.execute()` and sent as a system message to the player, formatted identically to `query()` responses: `[NpcName]: ...`

---

## `ObservationTicker`

### State (static, keyed by NPC UUID)

```java
Map<UUID, Set<String>>  lastSeen         // entity display names seen in previous scan
Map<UUID, Long>         lastHostileMs    // System.currentTimeMillis() of last hostile obs
Map<UUID, Long>         lastPassiveMs    // System.currentTimeMillis() of last passive obs
```

### Tick logic

Fires every 100 ticks (5 seconds). For each `AssistantEntity` in each loaded `ServerLevel`:

1. If `npcObservationsEnabled` is false, skip all.
2. Call `OllamaClient.scanSurroundingsRaw(level, npc)` → `Map<String, Boolean> current`.
3. Retrieve `lastSeen` for this UUID (empty set if first scan).
4. Compute `newTypes = current.keySet() - lastSeen` — entity display names not present in previous scan.
5. **Update `lastSeen` to `current.keySet()` unconditionally** — ensures an entity that stays put never re-triggers.
6. If `newTypes` is empty, stop.
7. Split `newTypes` into `newHostile` (isHostile == true) and `newPassive` (isHostile == false).
8. Hostile takes priority:
   - If `newHostile` non-empty AND `(now - lastHostileMs) >= hostileCooldownMs`: fire hostile observation, update `lastHostileMs`.
   - Else if `newPassive` non-empty AND `(now - lastPassiveMs) >= passiveCooldownMs`: fire passive observation, update `lastPassiveMs`.
   - At most one Ollama query per NPC per scan cycle.

### Broadcast target selection

Before firing, find the target player:
1. Collect all `ServerPlayer` instances in the same `ServerLevel` within `COMMAND_PROXIMITY` of the NPC.
2. Filter to those with a non-empty `ConversationMemory` entry for this NPC's UUID.
3. Pick the nearest. If none qualify, skip the observation silently (no Ollama query fired, cooldown NOT updated — the observation opportunity is lost but the timer doesn't advance).

### "What changed" string format

Built from `newHostile` or `newPassive` display names, whichever triggered:
- Single: `"a creeper appeared nearby"`
- Multiple same type: `"2 skeletons appeared nearby"`
- Multiple types: `"a creeper and a skeleton appeared nearby"` (up to 3 types listed; beyond that: `"several threats appeared nearby"`)
- Passive follows same pattern: `"a horse appeared nearby"`, `"a pig and a cow appeared nearby"`

### Cleanup

When `/assistant delete` is called (both `deleteNearest` and `deleteAll`), clear the deleted entity's UUID from all three maps. Parallel to `ConversationMemory.clearAll()` call already present.

---

## Data Flow

```
ServerTickEvent (every 100 ticks)
  → ObservationTicker.onServerTick()
      → for each AssistantEntity in each loaded ServerLevel:
          → scanSurroundingsRaw() → diff against lastSeen → update lastSeen
          → if new hostile/passive AND cooldown elapsed AND player qualifies:
              → build whatChanged string
              → OllamaClient.observe(server, player, ...)
                  → buildSystemPrompt(...)
                  → async HTTP → Ollama
                  → server.execute() → player.sendSystemMessage(...)
```

---

## Out of Scope

- Observations added to `ConversationMemory` (deferred — format doesn't fit player/NPC exchange pairs)
- NPC-to-NPC observations (deferred)
- Entity departures triggering observations (not implemented — only arrivals trigger)
- Line-of-sight filtering (consistent with existing surroundings awareness)

---

## Implementation Notes

**Checkpoint recommended:** implement and commit `scanSurroundingsRaw()` refactor + config + `OllamaClient.observe()` first, build-verify, then tackle `ObservationTicker.java`. The ticker's change-detection logic is the risk item — verify the diff logic carefully before wiring the Ollama call.

**Threading:** `ObservationTicker.onServerTick()` runs on the server thread. `scanSurroundingsRaw()` is a synchronous scan (same as `scanSurroundings()` in `ChatInterceptor` — established pattern). `observe()` dispatches async, same as `query()`.

**`lastSeen` update timing:** Step 5 above (update `lastSeen` unconditionally before checking cooldowns) is intentional. If the cooldown is not yet elapsed, the new entities are still recorded — they will not re-trigger the next scan unless *additional new* entities appear. This prevents cooldown expiry from causing a stale "new" entity to fire.

---

## Definition of Done

- `./gradlew build` passes.
- Spawn NPC "Johnny" near player (with prior conversation), spawn a zombie nearby — Johnny comments on the zombie within 15 seconds.
- Johnny does not comment again on the same zombie until 15 seconds have elapsed.
- Spawn NPC "Bessie" (no prior conversation with player) — zombie appears, Bessie says nothing.
- Passive: a pig wanders into Johnny's radius — Johnny comments within 60 seconds.
- A passive entity that was already present at spawn does not trigger any observation.
- `/assistant delete Johnny` — no lingering state in ObservationTicker maps.
- Set `npcObservationsEnabled = false` in config — no observations fire.
