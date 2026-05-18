# Advisor Branching Spec — v0.2
*Design session: 2026-05-17*
*v0.1 created: 2026-05-17 — v0.2 updated: 2026-05-17*
*Changes from v0.1: Added root cause suppression logic (Section 4.2), updated AdvisorThrottleData storage model (Section 8), updated Section 6 flow, corrected Section 9 unchanged-list.*

---

## Document Scope

This document specifies the implementable branching logic for `AdvisorDiagnosticLoop.runCycleAsync()`. It defines how the loop selects citizens, fires LLM calls, and throttles output. It does **not** specify LLM prompt wording — see `docs/advisor_prompt_engineering_spec_v0_2.md` for that.

**Relationship to existing code:**
- `ColonyDiagnosticReportGenerator` — unchanged.
- `AdvisorDiagnosticLoop.runCycleAsync()` — lines 85–101 replaced per Section 6.
- `AdvisorThrottleData` — **storage model changes** — see Section 8.
- `DragonTweaks.java` — one new invalidation trigger added (see Section 5).

---

## 1. Pre-Scan Pass

Before any LLM call is made, the async block performs a **pre-scan** over the colony citizen list.

**Algorithm:**

1. Collect all citizens from `report.getCitizenRecords()`.
2. Filter to flagged citizens only — those with at least one red or yellow factor, OR commute flagged.
3. Sort:
   - Red citizens first (any factor below `ADVISOR_HAPPINESS_THRESHOLD_RED`, or `commuteFlagged == true`)
   - Yellow citizens second (any factor below `ADVISOR_HAPPINESS_THRESHOLD_YELLOW`, none red, commute not flagged)
   - Alphabetical by name within each tier
4. Truncate to a maximum of **5 citizens**.

This produces the **candidate list**. No LLM calls are made during this pass.

**Source fields:**
- Red/yellow flags: already computed per-citizen in `ColonyDiagnosticReport` citizen records
- Commute flag: `citizenRecord.isCommuteFlagged()`
- Thresholds: `Config.ADVISOR_HAPPINESS_THRESHOLD_RED.get()` / `Config.ADVISOR_HAPPINESS_THRESHOLD_YELLOW.get()`

---

## 2. Output Selection

From the candidate list, take the **top 2** entries. No special-casing by tier — whatever the sort order produces is the output set.

| Candidate list state | Output |
|---|---|
| 2+ reds at top | Top 2 reds. No yellows processed this cycle. |
| 1 red, 1+ yellows | Top red + top yellow. |
| 0 reds, 2+ yellows | Top 2 yellows. |
| 0 reds, 1 yellow | That yellow only. |
| Empty | No per-citizen LLM calls this cycle. |

Each selected citizen produces **one LLM call** via `buildCitizenPrompt(report, citizenRecord)`. The existing single-target `buildCitizenPrompt(report)` signature must be updated — see Section 7.

---

## 3. Systemic Pattern Handling

Systemic detection logic is **unchanged**. If `report.isSystemicPatternDetected()` is true:

1. Build and fire the systemic LLM call first (existing behavior).
2. Proceed to the pre-scan pass and output selection as normal — systemic detection does **not** suppress per-citizen output.
3. The systemic throttle key (`{colonyId}:systemic:{patternType}:{colonyDay}`) is independent of per-citizen throttle keys.

**Rationale:** Systemic patterns explain colony-wide conditions. Per-citizen output addresses individual situations. Both are useful; suppressing one wastes diagnostic capacity.

---

## 4. Throttle and Suppression Rules

### 4.1 Per-Citizen Daily Throttle

**Key:** `"{colonyId}:{citizenId}:{colonyDay}"`

Each selected citizen is checked against `AdvisorThrottleData.hasFiredToday(key)` before any LLM call. If already fired today, that citizen is skipped. The next candidate is **not** promoted — the slot is unused that cycle.

**Systemic throttle:** unchanged — `"{colonyId}:systemic:{patternType}:{colonyDay}"`.

### 4.2 Root Cause Suppression

A citizen who fires repeatedly with the **same root cause** is suppressed after the first observation, for a configurable number of colony days. This prevents a citizen with a chronic issue from consuming a daily output slot indefinitely at the expense of citizens with new or different problems.

**Suppression key:** `"{colonyId}:{citizenId}:rc{rootCauseOrdinal}"`

- `rootCauseOrdinal` is the integer ordinal of the `RootCause` enum value for that citizen's current diagnosis.
- Example: if `RootCause.FOOD` has ordinal 1, the key is `"3:7:rc1"` for citizen 7 in colony 3.

**Suppression check (performed before the daily throttle check):**
1. Build the suppression key for this citizen's current root cause.
2. Look up the day this key was first fired via `AdvisorThrottleData.getSuppressedSinceDay(key)`.
3. If `colonyDay - suppressedSinceDay < ADVISOR_ROOTCAUSE_SUPPRESS_DAYS` → citizen is suppressed. Skip. Do not consume a throttle slot.
4. If suppression has expired (`>= ADVISOR_ROOTCAUSE_SUPPRESS_DAYS`) → clear the suppression entry and allow through.
5. If no suppression entry exists → allow through. After firing, record `suppressedSinceDay = colonyDay`.

**Config key:** `ADVISOR_ROOTCAUSE_SUPPRESS_DAYS`, default **2**, in `dragontweaks-common.toml`.

**Suppression expiry behaviour:** When suppression expires, the citizen is eligible again. If they fire with the same root cause again, a new suppression window starts from that new day.

**Root cause change:** If a citizen's root cause changes (different ordinal), the old suppression key is irrelevant. The new key has no entry — citizen fires immediately.

**Rationale:** Keeps the Advisor from repeating the same diagnosis until the player has had a reasonable window to act. Two days is enough for a player to notice and respond; if they haven't, another reminder at day 3 is warranted.

---

## 5. New Invalidation Trigger — RaidStartedEvent

Add `RaidStartedEvent` as a cache invalidation and `markDirty()` trigger in `DragonTweaks.java`, alongside the existing four event handlers.

**Why:** A raid start is the only realistic mid-day event that can flip a citizen's security factor to red instantly.

**Implementation pattern:** Follow the existing handler pattern exactly — call `ColonyDiagnosticCache.invalidate(colonyId)` and `AdvisorDiagnosticLoop.markDirty(colonyId)` together.

**MineColonies event class:** Verify `RaidStartedEvent` package path against `docs/stubs/` before implementing. Do not assume package location.

---

## 6. Updated runCycleAsync() Flow

Replace lines 85–101 with the following. Lines before 85 and after 101 are unchanged.

```
1. [Existing] Check report.isSystemicPatternDetected()
   → If true: build systemic throttle key, call buildSystemicPrompt(pattern)
   → Queue systemic result to main thread (existing server.execute() block)

2. [New] Pre-scan pass
   → Collect flagged citizens from report.getCitizenRecords()
   → Sort: red-first, then yellow, alpha within tier
   → Truncate to max 5 → candidate list

3. [New] Output selection
   → Take top 2 from candidate list

4. [New] Per-citizen loop (max 2 iterations)
   For each selected citizen:
     a. Build suppression key: "{colonyId}:{citizenId}:rc{rootCauseOrdinal}"
     b. Check AdvisorThrottleData.getSuppressedSinceDay(suppressionKey)
        → If suppressed and within window: skip citizen entirely
        → If expired or absent: continue
     c. Build daily throttle key: "{colonyId}:{citizenId}:{colonyDay}"
     d. Queue to main thread via server.execute():
         · hasFiredToday(dailyKey) check → skip if true
         · If not fired:
             - buildCitizenPrompt(report, citizenRecord) → LLMClient.observe()
             - markFiredToday(dailyKey)
             - recordSuppression(suppressionKey, colonyDay)
```

**Note:** Steps 4a–4b run on the async thread (read-only throttle state access). Steps 4d run on the main thread via `server.execute()`. Each citizen gets its own `server.execute()` block — do not batch.

**Note:** `getSuppressedSinceDay()` and `recordSuppression()` are new methods on `AdvisorThrottleData` — see Section 8.

---

## 7. Signature Change Required

**New signature:**
```java
private String buildCitizenPrompt(ColonyDiagnosticReport report, CitizenRecord citizen)
```

The caller passes the specific citizen record. `report.getTargetCitizen()` is no longer the loop's source of truth and is no longer called from the loop.

---

## 8. AdvisorThrottleData — Storage Model Change

`AdvisorThrottleData` currently stores a `Set<String>` of fired keys. This must change to support suppression day tracking.

**New storage model:**
```java
// Replaces Set<String> firedKeys
Map<String, Integer> firedKeys;   // key → colonyDay fired (daily throttle)
Map<String, Integer> suppressedKeys; // suppressionKey → colonyDay first fired
```

Both maps persist to NBT on the overworld SavedData as before.

**Updated public API:**

| Method | Description |
|---|---|
| `hasFiredToday(String key, int colonyDay)` | Returns true if key exists in `firedKeys` and stored day == colonyDay |
| `markFiredToday(String key, int colonyDay)` | Puts key → colonyDay into `firedKeys` |
| `getSuppressedSinceDay(String key)` | Returns day from `suppressedKeys`, or -1 if absent |
| `recordSuppression(String key, int colonyDay)` | Puts key → colonyDay into `suppressedKeys` |
| `clearSuppression(String key)` | Removes key from `suppressedKeys` (called when suppression window expires) |

**Old methods `hasFired(String)` and `markFired(String)` are replaced.** Update all call sites.

**NBT keys:** Use distinct NBT tag names for the two maps — e.g. `"firedKeys"` and `"suppressedKeys"` — to avoid collision.

**Stale entry cleanup:** `firedKeys` entries for days older than `colonyDay - 2` can be pruned on load or periodically to prevent unbounded growth. `suppressedKeys` entries are cleared explicitly via `clearSuppression()` when the window expires.

---

## 9. What This Spec Does Not Change

- `ColonyDiagnosticReportGenerator` — no changes.
- `ColonyDiagnosticCache` — no changes to cache logic or TTL.
- LLM prompt content — see `docs/advisor_prompt_engineering_spec_v0_2.md`.
- Panel payload (`AdvisorPanelPayload`) — no changes.

---

## 10. Open Questions

| # | Question | Blocks |
|---|---|---|
| B1 | `RaidStartedEvent` — verify package path against stubs before implementing | Section 5 implementation |
| B2 | `CitizenRecord` field names for red/yellow flags, commute flag, and root cause — verify against current `ColonyDiagnosticReport` implementation before writing pre-scan and suppression key logic | Sections 1 and 4.2 implementation |

Both B1 and B2 are resolvable by Claude Code reading stubs — no design session required.

---

*End of Advisor Branching Spec v0.2*
