# DragonTweaks — Current Project State

*Ground-truth reference. No session history. Facts only.*
*Last updated: 2026-05-17 (session 28)*

---

## Project Identity

| Field | Value |
|---|---|
| Mod Name | The Assistant Mod (final name TBD) |
| Branch | `phase-1` |
| Mod Loader | NeoForge 21.1.226 |
| Minecraft | 1.21.1 |
| Java | 21+ |
| Mod ID | `dragontweaks` |
| Package | `io.github.senseidragon.dragontweaks` |
| Source root | `src/main/java/io/github/senseidragon/dragontweaks/` |

---

## LLM Backend

| Parameter | Value |
|---|---|
| Provider | OpenRouter |
| Endpoint | `https://openrouter.ai/api/v1/chat/completions` |
| Client class | `LLMClient.java` — do not rename, do not recreate |
| Model | `google/gemma-4-26b-a4b-it` |
| max_tokens | 200 — always |
| stream | false — always |
| reasoning | Commented out (incompatible with current model). Not a bug. Re-enable when switching back to gemma. |

Auth: `Authorization: Bearer <key>` — API key in `.env` only. Never hardcoded. Never committed.

---

## Source Files — Current Status

All in `src/main/java/io/github/senseidragon/dragontweaks/`.

| File | Status | Purpose |
|---|---|---|
| `DragonTweaks.java` | ✅ Complete | Main mod class. Event bus registration, MineColonies handlers, LITE_MODE flag. TODO comment added for `RaidStartedEvent` invalidation (stub not found in `docs/stubs/`). |
| `DragonTweaksClient.java` | ✅ Complete | Client-only setup. Packet registration via `registerPackets()`. |
| `DragonTweaksClientEvents.java` | ✅ Complete | Client event bus subscriber. |
| `AssistantEntity.java` | ✅ Complete | Flavor NPC entity. Follow/stop, proximity, role, NBT. Idle wander + LookAtPlayer + greeting system. Wander restriction via `restrictTo()`. |
| `AssistantCommand.java` | ✅ Complete | `/assistant` command. Subcommands: `revoke <citizenName>`, `nickname <partial> <nickname>`, `advisor`, `planner`. |
| `AssistantRenderer.java` | ✅ Complete | Placeholder zombie renderer. |
| `AssistantRoleRecord.java` | ✅ Complete | Record: `citizenId (int)`, `roleType`, `assignmentTimestamp`, `playerUUID`, `shadowEntityUUID`. |
| `AssistantPanelCommand.java` | ✅ Complete | Handles `/assistant advisor` and `/assistant planner`. Sends panel packets. |
| `BookAdvisorEntity.java` | ✅ Complete | Lightweight floating entity. Extends `Entity`. State-aware tick. Glows. NBT: ownerUUID only. |
| `BookAdvisorRenderer.java` | ✅ Complete | `@OnlyIn(Dist.CLIENT)`. Renders WRITABLE_BOOK in COLONY_WITH_CITIZEN, BOOK otherwise. Y-rotation animation. |
| `ChatInterceptor.java` | ✅ Complete | Routes player chat to LLM. Multi-NPC addressing, PRE_COLONY and COLONY_NO_CITIZEN advisor routing, citizen conversation routing (non-assigned citizens only). |
| `CitizenConversationMemory.java` | ✅ Complete | SavedData on overworld (`"dragontweaks_citizen_memory"`). Map keyed by `"colonyId:citizenId"`. Max 20 entries/citizen FIFO. Cleared on death. |
| `CitizenInteractDetector.java` | ✅ Complete | `PlayerInteractEvent.EntityInteract` handler. Opens `RoleAssignmentPayload` flow. Passes `citizenData.getColony().getID()` to `isAssigned`. |
| `ClientPanelHandler.java` | ✅ Complete | `@OnlyIn(Dist.CLIENT)`. Handles `OpenAdvisorPanelPacket` and `OpenPlannerPanelPacket`. |
| `ColonyDiagnosticCache.java` | ✅ Complete | TTL 30s. `getOrGenerate(IColony)` + `invalidate(int colonyId)`. |
| `ColonyDiagnosticReportGenerator.java` | ✅ Complete | `generate(IColony)` → `ColonyDiagnosticReport`. All diagnostic phases including systemic pattern detection and per-citizen root cause. |
| `Config.java` | ✅ Complete | All config values. `ADVISOR_ROOTCAUSE_SUPPRESS_DAYS` added (int, default 2, range 0–30). See Config Values section. |
| `ConversationMemory.java` | ✅ Complete | Per-NPC conversation history. |
| `EnvLoader.java` | ✅ Complete | Reads `.env` for API key. |
| `FollowPlayerGoal.java` | ✅ Complete | AI goal for follow behavior. |
| `LLMClient.java` | ✅ Complete | OpenRouter async HTTP client. `query()` overload accepts `Consumer<String> onReply`. Reasoning block commented out. |
| `ModEntities.java` | ✅ Complete | `ASSISTANT` and `BOOK_ADVISOR` DeferredHolder registrations. |
| `NicknameData.java` | ✅ Complete | SavedData on overworld (`"dragontweaks_nicknames"`). Map keyed by `"colonyId:citizenId"`. |
| `ObservationTicker.java` | ✅ Complete | Proactive NPC observations. 100-tick interval. Greeting trigger, raid poll, event reactions. |
| `OpenAdvisorPanelPacket.java` | ✅ Complete | Server→client packet carrying `AdvisorPanelPayload`. |
| `OpenPlannerPanelPacket.java` | ✅ Complete | Server→client packet carrying `PlannerPanelPayload`. |
| `PlannerDependencyRegistry.java` | ✅ Complete | Singleton. Loads `planner_dependencies.json` at startup. DFS chain resolution. `getChain()`, `findMatches()`, `isAutoSatisfied()`. |
| `PreColonyScoutTicker.java` | ✅ Complete | 1200-tick proactive scouting in PRE_COLONY state. Village proximity warning injected into LLM context. |
| `RoleAssignmentData.java` | ✅ Complete | SavedData. Colony-scoped: `Map<String, AssistantRoleRecord>` keyed by `"colonyId:citizenId"`. All APIs require colonyId. No old integer-key migration. |
| `RoleAssignmentPayload.java` | ✅ Complete | Server→client packet. Carries citizenName, citizenId, slotsUsed, slotsMax, availableRoles. |
| `RoleAssignmentScreen.java` | ✅ Complete | `@OnlyIn(Dist.CLIENT)`. Scrollable role list, slot counter, Assign/Cancel. |
| `RolePersona.java` | ✅ Complete | Role keyword → persona block mapping. |
| `RoleSelectionPacket.java` | ✅ Complete | Client→server. Sends citizenId + selectedRole. On "Advisor": transitions AdvisorStateData to COLONY_WITH_CITIZEN. Passes `colony.getID()` to RoleAssignmentData APIs. |
| `TerrainScanner.java` | ✅ Complete | `public static String scan(ServerLevel, BlockPos)`. Village detection via `findNearestMapStructure`. Compass direction + distance in output. |
| `AdvisorDiagnosticLoop.java` | ✅ Complete | Multi-citizen branching loop per `advisor_branching_spec_v0_2.md`. Pre-scan: up to 5 flagged citizens sorted red-first then alpha; top 2 selected per cycle. Root cause suppression check + daily throttle check per citizen, each in its own `server.execute()` block. Systemic fires independently and does not suppress per-citizen output. `buildCitizenPrompt(report, CitizenRecord)` — worst factor derived from per-citizen `HappinessFactor` list; commute derived from `getCommuteDistance()`. RaidStartedEvent invalidation is a TODO (stub not found). Implemented 2026-05-17. |
| `AdvisorHotbarWatcher.java` | ✅ Complete | `PlayerTickEvent.Post`. DORMANT state only. Detects `structurize:sceptergold`. Transitions to PRE_COLONY, spawns BookAdvisorEntity. |
| `AdvisorPanelPayload.java` | ✅ Complete | Server-side data class. Assembles from ColonyDiagnosticCache. Citizen list sorted red→yellow→healthy. |
| `AdvisorPanelScreen.java` | ✅ Complete | `@OnlyIn(Dist.CLIENT)`. 304×250 panel. Environmental banner, systemic pattern banner, paginated citizen list. |
| `AdvisorState.java` | ✅ Complete | Enum: `DORMANT`, `PRE_COLONY`, `COLONY_NO_CITIZEN`, `COLONY_WITH_CITIZEN`. |
| `AdvisorStateData.java` | ✅ Complete | SavedData on overworld (`"dragontweaks_advisor_state"`). Per-player: advisorState, buildToolTriggerFired, assignedCitizenId (nullable), advisorEntityUUID (nullable). |
| `AdvisorThrottleData.java` | ✅ Complete | Two `Map<String, Integer>` maps: `firedKeys` (daily throttle, key→colonyDay) and `suppressedKeys` (root cause suppression, suppressionKey→colonyDay). API: `hasFiredToday(key, colonyDay)`, `markFiredToday(key, colonyDay)`, `getSuppressedSinceDay(key)`, `recordSuppression(key, colonyDay)`, `clearSuppression(key)`. Old `Set<String>` format detected on load and discarded silently. Stale `firedKeys` entries pruned on `markFiredToday`. Implemented 2026-05-17. |
| `PlannerPanelPayload.java` | ✅ Complete | Server-side data class. Snapshot + goal-input modes. Recommendation list, cost estimate, chain steps. |
| `PlannerPanelScreen.java` | ✅ Complete | `@OnlyIn(Dist.CLIENT)`. 324×270 panel. EditBox, snapshot rec list, goal-input chain view. |

---

## Config Values

All in `Config.java`. Verify exact field names against source before referencing.

| Key | Type | Default | Notes |
|---|---|---|---|
| `LLM_ENDPOINT` | String | OpenRouter URL | |
| `LLM_MODEL` | String | `google/gemma-4-26b-a4b-it` | |
| `LLM_ENABLED` | boolean | true | |
| `LLM_TIMEOUT_SECONDS` | int | 90 | |
| `NPC_OBSERVATIONS_ENABLED` | boolean | true | |
| `NPC_OBSERVATION_HOSTILE_COOLDOWN_SECONDS` | int | 5 | |
| `NPC_OBSERVATION_PASSIVE_COOLDOWN_SECONDS` | int | 180 | |
| `ROLE_SLOTS` | int | 3 | |
| `COMMAND_PROXIMITY` | int | 10 | Detection radius = command radius (same value) |
| `NPC_AWARENESS_RADIUS` | int | 16 | |
| `NPC_AWARENESS_CATEGORY` | String | "PASSIVE" | |
| `FLAVOR_NPC_GREETING_CHANCE` | double | 0.07 | |
| `FLAVOR_NPC_GREETING_COOLDOWN_TICKS` | int | 12000 | |
| `FLAVOR_NPC_WANDER_RADIUS` | int | 5 | |
| `ADVISOR_COMMUTE_THRESHOLD` | int | 80 | Blocks |
| `ADVISOR_HAPPINESS_THRESHOLD_RED` | double | 0.5 | Factor below this = red |
| `ADVISOR_HAPPINESS_THRESHOLD_YELLOW` | double | 0.9 | Factor below this (≥ red) = yellow |
| `ADVISOR_ENTITY_OFFSET` | double | 1.8 | Offset from player in blocks |
| `ADVISOR_HOTBAR_CHECK_TICKS` | int | 40 | PRE_COLONY only |
| `ADVISOR_BOUNDARY_DETECTION_RANGE` | int | 40 | Range before snap to Town Hall |
| `ADVISOR_WHISPER_THRESHOLD` | int | 120 | Characters — above triggers whisper pattern |
| `ADVISOR_FORCE_PRIVATE` | boolean | false | Force all responses private |
| `ADVISOR_ROOTCAUSE_SUPPRESS_DAYS` | int | 2 | Colony days before same root cause re-fires for a citizen |

---

## Locked Design Decisions

### Proximity Threshold
- 10 blocks XZ, ±5 Y tolerance. Detection radius = command radius. One config entry.

### Role Slot Expansion
- TH level < 3: 3 slots. TH level 3: 4. TH level 4: 5. TH level 5: 6. Hard cap: 6.
- Query TH level dynamically. No hardcoded MAX_SLOTS.

### Role Assignment Identity
- Colony-scoped. Key format: `"colonyId:citizenId"`. No old integer-only compatibility.

### Happiness Thresholds
- Red: factor < 0.5. Yellow: factor 0.5–0.9. Healthy: ≥ 0.9.

### Ten Canonical Happiness Factor IDs
`food`, `slepttonight`, `housing`, `health`, `unemployment`, `idleatjob`, `security`, `school`, `social`, `mystical`

### LLM Reasoning Block
- Commented out in `LLMClient.java` as of 2026-05-13. Intentional — current model rejects `effort:none`.
- Re-enable when switching back to `google/gemma-4-26b-a4b-it`.

### AVAILABLE_ROLES constant
- Hardcoded in `RoleAssignmentPayload.java`: `["Ranch Hand", "Scout", "Advisor", "Planner"]`. Not from config per spec.

### Bed Capacity Calculation
- `countBedCapacity()` in `ColonyDiagnosticReportGenerator`: +2 per Residence (translation key contains "residence" or ".home"), +4 per Tavern. TODO: verify substrings against 1.21.1.

### townhall auto_satisfied
- `planner_dependencies.json` has `"auto_satisfied": true` on townhall entry. Always treated as complete in step chains.

### Advisor Branching Logic (locked 2026-05-17)
- Pre-scan: up to 5 flagged citizens per cycle. Sort: red → yellow → alpha within tier.
- Output: top 2 from candidate list. No tier special-casing.
- Throttle: per-citizen per-day. Key: `"{colonyId}:{citizenName}:{colonyDay}"`. No mid-day re-fire.
  - **Note:** `CitizenRecord` has no numeric ID — citizen name is used as identifier in throttle keys.
- Systemic pattern fires first but does NOT suppress per-citizen output.
- Root cause suppression key: `"{colonyId}:{citizenName}:rc{ordinal}"`. Suppressed for `ADVISOR_ROOTCAUSE_SUPPRESS_DAYS` days.
  - Root cause is only available at report level (for `targetCitizen`). Non-target citizens get `RootCause.UNKNOWN` ordinal.
- `RaidStartedEvent` invalidation: **TODO only** — stub not found in `docs/stubs/`. Comment added in `DragonTweaks.java`. Implementation pending stub verification.
- Full spec: `docs/advisor_branching_spec_v0_2.md`.

---

## Known Landmines

| Wrong | Correct |
|---|---|
| `HumanoidMob` | Use `PathfinderMob` (NeoForge 1.21.1) |
| `setHomePosAndDistance()` | Use `restrictTo(BlockPos, int)` on `Mob` |
| Any `OllamaClient` reference | Does not exist. Use `LLMClient.java` |

---

## Resources

| Item | Location |
|---|---|
| MineColonies API stubs | `docs/stubs/` (index: `docs/STUB_INDEX.md`) |
| Planner dependency data | `src/main/resources/data/dragontweaks/planner_dependencies.json` |
| Verification checklist | `dragontweaks_verification_checklist.md` |
| Advisor branching spec | `docs/advisor_branching_spec_v0_2.md` |
