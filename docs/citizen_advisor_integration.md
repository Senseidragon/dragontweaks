# DragonTweaks — Citizen & Advisor Integration

*Current-state reference. No session history.*
*Last updated: 2026-05-13 (session 24)*

---

## Role Assignment Identity Model

**Key format:** `"colonyId:citizenId"` (string)

All role assignment data is colony-scoped. There is no old integer-only key format. No migration code exists or is needed.

### RoleAssignmentData.java

- **SavedData key:** `"dragontweaks_roles"` (attached to overworld)
- **Internal map:** `Map<String, AssistantRoleRecord>` — key constructed by private `key(int colonyId, int citizenId)` helper
- **All public APIs require `colonyId`:**
  - `assign(int colonyId, int citizenId, String roleType, UUID playerUUID)`
  - `revoke(int colonyId, int citizenId)`
  - `getRecord(int colonyId, int citizenId)` → `AssistantRoleRecord` (nullable)
  - `isAssigned(int colonyId, int citizenId)` → boolean
  - `getAssignedCount(UUID playerUUID)` → int (iterates values, no colonyId needed)
  - `getAssignments()` → `Iterable<AssistantRoleRecord>`
- **NBT:** string key stored in `"key"` field; `citizenId` int stored separately for record reconstruction

### AssistantRoleRecord.java

```java
public record AssistantRoleRecord(int citizenId, String roleType, long assignmentTimestamp, UUID playerUUID, UUID shadowEntityUUID)
```

`shadowEntityUUID` is intentional — do not remove.

### Available Roles

Hardcoded in `RoleAssignmentPayload.java`:
```java
List.of("Ranch Hand", "Scout", "Advisor", "Planner")
```

Not from config per spec.

### Role Slot Cap Formula

`thLevel < 3 ? 3 : Math.min(thLevel + 1, 6)`

- TH level < 3: 3 slots
- TH level 3: 4 slots
- TH level 4: 5 slots
- TH level 5: 6 slots (hard cap)

---

## Nickname System

### NicknameData.java

- **SavedData key:** `"dragontweaks_nicknames"` (attached to overworld)
- **Internal map:** `Map<String, String>` keyed by `"colonyId:citizenId"`
- **Public API:**
  - `setNickname(int colonyId, int citizenId, String nickname)`
  - `getNickname(int colonyId, int citizenId)` → String (nullable)
  - `removeNickname(int colonyId, int citizenId)`
  - `resolve(int colonyId, int citizenId, String fallback)` → nickname if set, fallback otherwise
  - `static get(ServerLevel)` → NicknameData
- **Cleanup:** `DragonTweaks.java` calls `NicknameData.removeNickname()` in `CitizenDiedModEvent` handler

### /assistant nickname Command

- Subcommand: `/assistant nickname <partialName> <nickname>` (greedyString for multi-word nicknames)
- Finds player's colony across all server levels
- Partial case-insensitive name match against `colony.getCitizenManager().getCitizens()`
- Multiple matches: lists them, asks player to be more specific
- Single match: calls `NicknameData.setNickname()`, confirms with citizenName + id + nickname
- Guards: MineColonies loaded, player has colony

---

## Citizen Conversation Memory

### CitizenConversationMemory.java

- **SavedData key:** `"dragontweaks_citizen_memory"` (attached to overworld)
- **Internal map:** `Map<String, Deque<String>>` keyed by `"colonyId:citizenId"`
- **Max entries per citizen:** 20 (FIFO eviction)
- **Public API:**
  - `appendHistory(int colonyId, int citizenId, String role, String content)`
  - `getHistory(int colonyId, int citizenId)` → `Deque<String>`
  - `clearHistory(int colonyId, int citizenId)`
  - `static get(ServerLevel)` → CitizenConversationMemory
- **Cleanup:** `DragonTweaks.java` calls `clearHistory()` in `CitizenDiedModEvent` handler

---

## Citizen Interaction Flow

### CitizenInteractDetector.java

Registered on `NeoForge.EVENT_BUS` as `PlayerInteractEvent.EntityInteract` listener.

**Flow:**
1. Guard: MineColonies loaded (`ModList.get().isLoaded("minecolonies")`)
2. Guard: entity is `AbstractEntityCitizen`
3. Get `ICitizenData` via `entity.getCitizenData()` — null check
4. Get colonyId via `citizenData.getColony().getID()`
5. Guard: `!RoleAssignmentData.isAssigned(colonyId, citizenId)` — pass through assigned citizens to MineColonies
6. Compute dynamic slot cap from Town Hall level
7. Guard: `slotsUsed < slotsMax`
8. Cancel event, send `RoleAssignmentPayload` to player via `PacketDistributor.sendToPlayer()`

### RoleAssignmentPayload (server→client)

Carries: `citizenName`, `citizenId`, `slotsUsed`, `slotsMax`, `availableRoles`

Registered `playToClient` in `DragonTweaksClient.registerPackets()`.
Handler inline: opens `RoleAssignmentScreen(packet)` via `Minecraft.getInstance().setScreen()`.

### RoleAssignmentScreen.java

- `@OnlyIn(Dist.CLIENT)`, extends `Screen`, panel 210×220
- Scrollable role list (scissor-clipped), slot counter
- Assign button disabled until row selected; sends `RoleSelectionPacket` and closes

### RoleSelectionPacket (client→server)

Carries: `citizenId`, `selectedRole`

**Server handler:**
1. LITE_MODE guard → return
2. Cast `ctx.player()` to `ServerPlayer`
3. MineColonies guard + colony lookup (iterate all server levels by owner UUID)
4. `isAssigned` re-verify with `colony.getID()`
5. `assign(colony.getID(), citizenId, selectedRole, playerUUID)`
6. If selectedRole == "Advisor" (case-insensitive): transition `AdvisorStateData` to `COLONY_WITH_CITIZEN`, set `assignedCitizenId`
7. Confirm to player

### /assistant revoke Command

- Subcommand: `/assistant revoke <citizenName>`
- Partial case-insensitive name match across all role assignments
- On match: `RoleAssignmentData.revoke(colonyId, citizenId)`, handles advisor state transition to `COLONY_NO_CITIZEN` + `BookAdvisorEntity` swap if role was Advisor
- Passes `colony.getID()` to all `isAssigned`, `getRecord`, and `revoke` calls

---

## ChatInterceptor — Citizen Routing

Citizen conversation routing block in `ChatInterceptor.java`:

- Runs on every player chat when MineColonies loaded
- Finds player's colony
- Iterates citizens; skips `instanceof IVisitorData` and `RoleAssignmentData.isAssigned(colonyId, citizenId)`
- Name match: checks nickname first (via `NicknameData.resolve()`), then real name; partial case-insensitive
- On match: builds system prompt with `IJob.getNameTagDescription()` (fallback "an unemployed colonist")
- Fires `LLMClient.query()` with `Consumer<String>` callback
- Callback saves exchange to `CitizenConversationMemory` for both sides

---

## Advisor State Machine

### AdvisorState.java

Enum: `DORMANT`, `PRE_COLONY`, `COLONY_NO_CITIZEN`, `COLONY_WITH_CITIZEN`

### AdvisorStateData.java

- **SavedData key:** `"dragontweaks_advisor_state"` (attached to overworld)
- **Per-player state** keyed by player `UUID`
- **Fields per player:**
  - `advisorState` — `AdvisorState` (default DORMANT)
  - `buildToolTriggerFired` — boolean (default false; never resets once true)
  - `assignedCitizenId` — `Integer` (nullable; non-null only in COLONY_WITH_CITIZEN)
  - `advisorEntityUUID` — `UUID` (nullable; null when entity despawned)
- **Access:** always read/write from `serverLevel.getServer().getLevel(Level.OVERWORLD)` — attached to overworld only

### State Transitions

| Trigger | From | To |
|---|---|---|
| `structurize:sceptergold` enters hotbar | DORMANT | PRE_COLONY |
| `ColonyCreatedModEvent` (player's colony) | PRE_COLONY | COLONY_NO_CITIZEN |
| Player assigns citizen to Advisor role (`RoleSelectionPacket`) | COLONY_NO_CITIZEN | COLONY_WITH_CITIZEN |
| `CitizenJobChangedModEvent` or `CitizenDiedModEvent` (assigned citizen) | COLONY_WITH_CITIZEN | COLONY_NO_CITIZEN |
| `ColonyDeletedModEvent` (player's colony) | COLONY_NO_CITIZEN or COLONY_WITH_CITIZEN | PRE_COLONY |

### State Behaviors Summary

**DORMANT:** No activity. `AdvisorHotbarWatcher` listens on `PlayerTickEvent.Post` only.

**PRE_COLONY:**
- One `BookAdvisorEntity` follows player with yaw-relative offset
- Hotbar visibility toggle every `ADVISOR_HOTBAR_CHECK_TICKS` ticks — entity despawns if build tool leaves hotbar
- Responds to any nearby chat (no keyword required)
- `PreColonyScoutTicker` fires proactive terrain observations at 1200-tick interval

**COLONY_NO_CITIZEN:**
- `BookAdvisorEntity` colony-attached
- Hotbar toggle inactive
- Responds only within colony bounds; keyword "Advisor" required
- One-time greeting fires on entry: "A colony has been established..."

**COLONY_WITH_CITIZEN:**
- `BookAdvisorEntity` renders as `WRITABLE_BOOK` (book-and-quill visual)
- Same movement as COLONY_NO_CITIZEN
- Responds to citizen name or "Advisor" keyword within colony bounds
- Full Observe→Diagnose→Recommend loop active via `AdvisorDiagnosticLoop`

---

## BookAdvisorEntity

- Extends `Entity` (not PathfinderMob — no AI, no combat)
- `setGlowingTag(true)` in constructor
- **NBT:** ownerUUID only
- **Tick behavior (server-side):**
  - PRE_COLONY or DORMANT: follows player with yaw-relative offset (`Config.ADVISOR_ENTITY_OFFSET`)
  - COLONY_NO_CITIZEN or COLONY_WITH_CITIZEN (MineColonies loaded): look up colony via `IColonyManager.getInstance().getIColony()`
    - Player inside colony → follow with offset
    - Player outside colony but within `ADVISOR_BOUNDARY_DETECTION_RANGE` → hold position
    - Player beyond range → snap to Town Hall (`getTownHall().getPosition()`, guarded by `hasTownHall()`)
  - MineColonies not loaded in colony state → fall back to follow

### BookAdvisorRenderer

- `@OnlyIn(Dist.CLIENT)`, extends `EntityRenderer<BookAdvisorEntity>`
- Reads `AdvisorState` from overworld `AdvisorStateData` via `mc.getSingleplayerServer()`
- Renders `Items.WRITABLE_BOOK` in `COLONY_WITH_CITIZEN`, `Items.BOOK` otherwise
- Y-rotation animation: one full rotation per 80 ticks

---

## AdvisorHotbarWatcher

- Registered on `NeoForge.EVENT_BUS` as `PlayerTickEvent.Post`
- Server-side only (guards `!(player.level() instanceof ServerLevel)`)
- **Only active in DORMANT state** — returns immediately in any other state
- Scans hotbar slots 0–8 for `structurize:sceptergold` via `BuiltInRegistries.ITEM.getKey()`
- On first detection:
  - Sets `buildToolTriggerFired = true`
  - Transitions state to PRE_COLONY
  - Spawns one `BookAdvisorEntity` in player's current level
  - Stores entity UUID in `AdvisorStateData`

---

## Lite Mode vs Full Mode

Mode set once at startup based on API key validity. Does not change during a session.

- **Full mode:** API key present, non-blank, non-placeholder
- **Lite mode:** API key absent, blank, or placeholder value

**In lite mode:** No entities. No role assignment UI. No LLM calls. Only `/assistant advisor` and `/assistant planner` panel commands work.

**LITE_MODE flag** is checked at the start of most handlers in `DragonTweaks.java`, `CitizenInteractDetector.java`, and packet handlers.
