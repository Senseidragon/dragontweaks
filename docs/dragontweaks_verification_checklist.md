# DragonTweaks — In-Game Verification Checklist & Decision Log
*Last updated: 2026-05-19 (session 31)*

Legend: ✅ Verified | ❌ Failed/Bug | ⚠️ Flagged | 🔲 Untested/Assumed | 🚫 Not built yet | 🔁 Deferred

---

## NPC Entity — Spawn & Persistence

| Status | Test | Notes |
|--------|------|-------|
| ✅ | NPC spawns via `/assistant spawn` command | Session 1 |
| ✅ | NPC persists across server restart (NBT save/load) | Session 1 — full JVM exit and relog |
| ✅ | NPC role and playerUUID survive relog | Session 1 — persona intact after relog |
| ✅ | NPC initiates unprompted environmental commentary on spawn | Session 2 — salmon comment before player spoke |
| 🔲 | Shadow entity UUID stored and retrieved correctly | Requires physical role test — not yet tested |
| 🚫 | DragonTweaks.java mod initializes cleanly with all handlers registered | Not yet tested |
| 🚫 | DragonTweaksClient.java client setup and packet registration complete | Not yet tested |
| 🚫 | DragonTweaksClientEvents.java client event handlers fire correctly | Not yet tested |
| 🚫 | AssistantRenderer renders entity correctly in-game | Not yet tested |
| 🚫 | ModEntities registers ASSISTANT and BOOK_ADVISOR entity types without errors | Not yet tested |
| 🚫 | Config values load correctly from dragontweaks-common.toml | Not yet tested |

---

## Follow / Stop Behavior (Flavor NPCs only)

| Status | Test | Notes |
|--------|------|-------|
| ✅ | NPC physically follows player when directed explicitly | "follow me, dummy" / "follow me, goober" both worked |
| ✅ | NPC physically stops on 'stop' command | Fired correctly both times in session 2 |
| ✅ | Re-follow after stop works | Confirmed session 2 |
| ⚠️ | Command parser — natural/implicit phrasing | ARCHITECTURAL DECISION PENDING — see Command Parser note |
| 🔲 | Implicit stop phrasing ("wait here", "stay put" etc.) | Not yet tested |

### Command Parser — Architectural Note
Current parser reads player raw text, matches keywords, fires movement goal immediately. Intentional low-latency design — LLM response is async and purely for immersion.

Hardcoding keyword expansion is **explicitly rejected** due to i18n — Japanese, Spanish, Russian etc. would all fail.

**Agreed direction:** LLM-based intent classification returning `FOLLOW` / `STOP` / `NONE`. Language-agnostic. Implementation TBD.

---

## Chat & LLM Integration

| Status | Test | Notes |
|--------|------|-------|
| ✅ | Player chat message routes to correct NPC | Sessions 1 and 2 |
| 🔲 | LLM response arrives asynchronously (no game freeze) | Not stress tested |
| 🔲 | Fallback fires on LLM timeout | Requires deliberate network disruption test |
| 🔲 | OpenRouter API key never appears in logs | Code review only — not in-game confirmed |
| ✅ | Conversation memory persists across sessions | Session 2 — "Bubba" remembered after full JVM exit and relog |
| ✅ | "Hmm..." acknowledgment — see architectural note below | Refactor complete — confirmed absent from all source files 2026-05-05. Fallback path wired via `sendFallback()` in `LLMClient.java`. |
| 🔁 | NPC uses correct player name consistently | Deferred — see Player Nickname note |
| ✅ | NPC handles unknown/nonsense words gracefully | Session 1 — "fraggle" deflected in character |
| ✅ | NPC generates contextually aware environmental commentary | Session 2 — squid/salmon observations fit surroundings |
| 🚫 | EnvLoader reads API key from .env without errors | Not yet tested |
| 🚫 | CitizenConversationMemory saves and retrieves entries per citizen | Not yet tested |
| 🚫 | NicknameData saves and retrieves nicknames per citizen | Not yet tested |

### LLM as Hard Requirement
The LLM key is a **hard requirement** for the mod. No key = mod does not run. This eliminates the need for "Hmm..." as a standard acknowledgment.

"Hmm..." is retained **only** as a timeout fallback — if the actual timeout threshold is exceeded, Joe fires a brief in-character response. Otherwise silence until the LLM responds.

~~**Near-term build target:** enforce hard failure on missing API key, and refactor "Hmm..." out of standard acknowledgment path.~~ ✅ Done — startup check in `DragonTweaks.java` `commonSetup()`. Throws `IllegalStateException` on null, blank, or placeholder key.

### Player Nickname / Name Drift
Current behavior: Joe remembers a nickname for as long as it stays in context, then drifts back to the Minecraft username. Honest to how conversational memory works.

**Deferred** — persistent nicknames require explicit NBT storage. Not essential now. Downgraded from bug to known limitation.

---

## ObservationTicker — Threat & Environment

| Status | Test | Notes |
|--------|------|-------|
| ⚠️ | Passive commentary rate during idle periods | 5 comments in ~3 minutes. Throttle works but rate too high. Increase idle cooldown — config value change only. Near-term build target. |
| ✅ | NPC comments once when threat appears (state flip) | Session 1 — one wolf warning, no repeat |
| ✅ | All-clear fires on mob exit from AABB (not mob death) | Session 1 — correct behavior confirmed |
| ✅ | Passive event throttle suppresses redundant spawn commentary | Session 1 — second sheep correctly ignored |
| 🔲 | No comment on slime splits | Not yet tested |
| ✅ | anyActiveThreats flips correctly when mob targets entity | Session 1 wolf test — bug #1 fix holds |

---

## Role Assignment System
**Deferred until GUI implementation.**

### Architectural Decision — Two Distinct NPC Tiers

Two tiers share the LLM backend but are otherwise entirely separate systems.

#### Tier 1: Functional Role NPCs (MineColonies Citizens)
- **No spawned entity** — citizen already exists, managed entirely by MineColonies
- Mod attaches a tag/record to the citizen via RoleAssignmentData
- LLM provides advisory/observational output tied to the citizen's actual job data
- **No follow, no stop, no direct movement** — MineColonies owns the citizen's behavior entirely
- Assignment via GUI when interacting with a real citizen
- Consumes a role slot (capped by Town Hall level: L1-2=3, L3=4, L4=5, L5=6)
- Valid roles are **only** configured archetypes: Ranch Hand, Scout, Advisor, Planner, or server-operator additions
- Freeform descriptions are **not** valid roles — outside configured archetypes = not a functional assignment

#### Tier 2: Flavor / Immersion NPCs (e.g. Cranky Joe)
- **Real entity spawned** into the world — mod owns this entity entirely
- Not attached to any MineColonies citizen
- Supports follow, stop, direct interaction, patrol behaviors, idle routines
- Created via `/assistant spawn [persona]`
- **Does not consume a role slot**
- No functional MineColonies integration — purely emergent/immersive
- Separate configurable cap needed to prevent server performance issues — deferred

**Key insight:** Cranky Joe as tested is not an incomplete functional NPC — he is a complete, correct implementation of the Flavor NPC tier.

**Flavor NPC behavior space (to be designed):** Beyond follow/stop, could include patrol routes, idle routines, colony event reactions. Needs dedicated design session before implementation.

| Status | Test | Notes |
|--------|------|-------|
| ✅ | RoleAssignmentData initializes from SavedData on world open | LevelEvent.Load handler confirmed in-game 2026-05-01 — no exceptions on world load |
| 🔲 | RoleAssignmentData record survives full JVM restart (write + reload test) | Not yet done — required before proceeding to Step 4 |
| 🚫 | Role record written on assignment | CitizenInteractDetector built 2026-05-06 — detects interaction and guards correctly. Write path still absent; requires RoleAssignmentScreen (Step 6) to complete. |
| 🚫 | Slot count enforced per TH level | Requires role assignment GUI |
| 🔲 | CitizenJobChangedModEvent does NOT revoke advisor role | Architectural decision locked 2026-05-19 — only death and revoke command may trigger state regression |
| 🔲 | Role slot released on CitizenDiedModEvent | Not yet tested |
| 🚫 | AssistantRoleRecord stores all fields correctly | Not yet tested |
| 🚫 | RoleAssignmentPayload carries correct citizen data to client | Not yet tested |
| 🚫 | RoleAssignmentScreen opens and displays role list correctly | Not yet tested |
| 🚫 | RolePersona maps role keywords to correct persona blocks | Not yet tested |
| 🚫 | RoleSelectionPacket transmits role selection to server correctly | Not yet tested |

---

## Commands

| Status | Test | Notes |
|--------|------|-------|
| ✅ | `/assistant spawn [persona]` creates flavor NPC with correct persona | Session 1 |
| ✅ | `/assistant delete` removes NPC | Works — confirmed in testing |
| 🚫 | `/assistant revoke <name>` releases citizen role slot | Not yet built — tied to role assignment system, deferred |
| ✅ | `/assistant locale [code]` manual locale override | Confirmed working in-game 2026-05-05. Locale override fires correctly; `/assistant locale reset` clears override correctly. |
| 🚫 | `/assistant advisor` opens Advisor panel packet | Not yet tested |
| 🚫 | `/assistant planner` opens Planner panel packet | Not yet tested |

---

## Locale / Language System

### Architectural Decision
Joe's response language is driven by the **Minecraft client locale setting** (`en_us`, `ja_jp`, `ru_ru`, `zh_cn` etc.), not by what language the player types in. Joe understands any language; he always responds in the configured locale.

~~**Immediate build target:** `/assistant locale [code]`
- Manually injects locale string into session context for testing
- Unrecognized codes fall back to `en_us` with a logged warning
- `/assistant locale reset` clears the override
- Examples: `/assistant locale ru_ru`, `/assistant locale ja_jp`~~ ✅ Done — confirmed in-game 2026-05-05.

**Deferred:** Auto-detect via `Minecraft.getInstance().getLanguageManager().getSelected()`, transmitted server-side via packet on session init. Needs NeoForge 1.21.1 API path verification first.

**Easter egg note (out of scope, door left ajar in comments):** `/assistant locale Klingon` → graceful fallback to `en_us`. 🖖

---

## Immediate Build Priorities (in order)

These are to be completed before the citizen interaction work (Steps 4–7 in devchat.md).

1. ~~**`/assistant locale [code]`** — debug command, locale injection, fallback to en_us~~ ✅ Done
2. **Observation ticker idle cooldown tuning** — increase passive comment interval; config value change only — code change done, needs in-game feel test
3. ~~**"Hmm..." refactor** — remove as standard acknowledgment, retain as timeout fallback only~~ ✅ Done
4. ~~**LLM hard requirement enforcement** — mod fails to start without valid API key~~ ✅ Done
5. ~~**SavedData persistence smoke test** — write a role assignment, do a full JVM restart, confirm record survives. Must pass before proceeding to Step 4.~~ ~~Moot — no write path exists until Steps 5–6 complete.~~
6. **Flavor NPC behavior space design** — patrol, idle routines, event reactions (design session needed before implementation)

> **Note:** Items involving MineColonies citizens (`CitizenJobChangedModEvent`, `CitizenDiedModEvent`, role revocation, slot enforcement) cannot be meaningfully tested until MineColonies integration work begins. These are tracked above for completeness but are not actionable until then.

---

---

## Advisor System

| Status | Test | Notes |
|--------|------|-------|
| ⚠️ | BookAdvisorEntity spawns, persists, and state ticks correctly | Spawn confirmed (renderer active, ownerUUID valid in log, session 30). NBT save/load verified correct in source. Full persist-across-reload test not yet run. |
| 🚫 | BookAdvisorRenderer displays correct book type per advisor state | Not yet tested |
| 🚫 | AdvisorState enum transitions fire in correct order | Not yet tested |
| ⚠️ | AdvisorStateData persists per-player state across sessions | Stale COLONY_NO_CITIZEN state observed (session 31) — caused by ColonyCreatedModEvent firing on world load for restored colonies. Auto-correction added to ChatInterceptor; root state persistence itself not re-verified. |
| 🚫 | AdvisorHotbarWatcher detects scepter and transitions to PRE_COLONY | Not yet tested |
| ✅ | PreColonyScoutTicker fires proactive scouting commentary in PRE_COLONY state | Confirmed firing, LLM query dispatched, response received (session 31). Village proximity warning and barbarian camp DANGER context both confirmed in live test. |
| ✅ | TerrainScanner returns village direction and distance correctly | Confirmed: "village to the west (~632 blocks)" reported correctly in PRE_COLONY scout observation (session 31). |
| ✅ | MineColonies hostile entity scan detects camp barbarians in PRE_COLONY | Confirmed: 41 camp entities detected at ~50 blocks (session 31). Namespace scan (`"minecolonies"` namespace, excluding citizen/visitor/cavalry_horse) required — `ModTags.raiders` and `ModTags.hostile` do not tag camp variants. |
| 🚫 | ColonyDiagnosticCache generates and invalidates reports within TTL | Not yet tested |
| 🚫 | ColonyDiagnosticReportGenerator produces correct per-citizen and systemic data | Not yet tested |
| 🚫 | AdvisorDiagnosticLoop selects top 2 citizens and fires per-citizen LLM prompts | Not yet tested |
| 🚫 | AdvisorThrottleData throttles per-citizen per-day and suppresses repeated root causes | Not yet tested |
| 🚫 | AdvisorPanelPayload assembles from diagnostic cache with correct citizen sort order | Not yet tested |
| 🚫 | AdvisorPanelScreen opens with correct panel layout and citizen list | Not yet tested |
| 🚫 | OpenAdvisorPanelPacket received and dispatched by ClientPanelHandler | Not yet tested |

---

## Planner System

| Status | Test | Notes |
|--------|------|-------|
| 🚫 | PlannerDependencyRegistry loads planner_dependencies.json at startup without errors | Not yet tested |
| 🚫 | PlannerPanelPayload assembles snapshot and goal-input modes correctly | Not yet tested |
| 🚫 | PlannerPanelScreen opens with EditBox and chain view rendering correctly | Not yet tested |
| 🚫 | PlannerPanelScreen [Browse] button activates browse mode and replaces content area | Not yet tested |
| 🚫 | Browse mode: pack list populated from config/DragonTweaks/ subdirectories | Not yet tested — requires pack data on disk |
| 🚫 | Browse mode: drill-down Pack → Category → Building → Level selector navigates correctly | Not yet tested |
| 🚫 | Browse mode: [← Back] returns to previous level at each drill depth | Not yet tested |
| 🚫 | Browse mode: level selector calls BlueprintMaterialsLoader and renders materials list | Not yet tested |
| 🚫 | BlueprintMaterialsLoader.getMaterials() reads correct JSON path and returns correct map | Not yet tested — requires JSON files on disk |
| 🚫 | BlueprintMaterialsLoader returns empty map and logs warning on missing file | Not yet tested |
| 🚫 | Browse mode pagination uses ITEMS_PER_PAGE and prev/next controls work independently of snapshot/goal-input pages | Not yet tested |
| 🚫 | OpenPlannerPanelPacket received and dispatched by ClientPanelHandler | Not yet tested |

---

## Design Decisions Locked — 2026-05-07

- **Advisor state machine** — Four states: DORMANT, PRE_COLONY, COLONY_NO_CITIZEN, COLONY_WITH_CITIZEN. Full spec in `docs/devchat.md` under Design Decisions — Locked. Covers entity behavior, visibility toggle, communication triggers, capability tiers, degraded state transitions, response delivery (short/long/whisper), SavedData fields, and required config values. Not yet implemented — design only.

---

*Checklist is a living document — update after each test session or design decision.*