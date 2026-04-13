# DragonTweaks / The Assistant Mod — Session Continuity Document

## Project Identity
- **Mod Name (working):** The Assistant Mod (final name TBD)
- **Repo:** https://github.com/Senseidragon/dragontweaks
- **Mod Loader:** NeoForge
- **Minecraft Version:** 1.21.1
- **Design Doc:** AssistantMod_DesignDoc_v0.1.docx (in repo)
- **LLM Backend:** Ollama @ http://SENSEI:11434/api/generate — Model: gemma4:26b
- **Java:** 21+

---

## Hard Architectural Rules — Never Violate
1. **Nothing may ever block the main Minecraft game thread.** This is non-negotiable in the NeoForge/Minecraft modpack ecosystem.
2. **All network calls, including Ollama HTTP requests, must be async on a separate thread.**
3. **Responses from async operations must be queued back to the main thread via server tick queue before any game interaction.**
4. **Zero interference with MineColonies internals.** All MineColonies integration via public API and event system only. Citizens are never directly modified.
5. **All MineColonies API dependencies must be verified before any dependent code is written.** See design doc section 5.4.

---

## Current Development Phase
**PoC Phase — pre-Phase 1**
Validating core technical risks before committing to full architecture.

### PoC Goal
Spawn a stationary NPC (villager or equivalent) that:
- Does not wander
- Intercepts player chat within proximity radius (~8-10 blocks)
- Fires async Ollama/Gemma4 request with minimal context payload (time of day, weather)
- Delivers LLM response as in-world chat attributed to the NPC
- Handles timeout/fallback gracefully without breaking immersion

### PoC Success Criteria
- Game thread never blocked during Ollama call
- Async round trip works reliably
- Latency is acceptable or masked effectively
- Fallback to template on timeout works silently

---

## Decisions Made
- **Async is mandatory from the start** — not a later optimization
- **Immediate NPC acknowledgment** ("Hmm..." or equivalent) while LLM processes, so player knows they were heard
- **60 second timeout** before fallback to template response (per design doc)
- **PoC before Phase 1** — validate unknowns before building framework
- **Short, focused sessions preferred** over long sprawling ones to minimize context drift

---

## Concerns Log
### Concern #1 — AI Threading Naivety
**What happened:** Claude suggested synchronous Ollama HTTP call without flagging threading implications. User had to correct this.
**Rule added:** Hard Architectural Rule #1, #2, #3 above.
**Implication:** Do not accept generated code without verifying it respects Minecraft's single-threaded game loop. Explicitly restate threading constraints at the start of any implementation session.

---

## Known Limitations of This Document
- **Cognitive entropy is real.** Constraints loaded at session start are not guaranteed to remain equally weighted as context grows. Keep sessions short and focused.
- **Summarization is lossy.** This document is already a compressed representation. Do not allow it to grow large — precision over completeness.
- **AI adherence is not guaranteed.** This document reduces error probability but does not eliminate it. Critical architectural decisions should still be verified by the user.

---

## Open Questions (from design doc)
- Does MineColonies fire a public `CitizenJobAssignedEvent`?
- Are citizen happiness values queryable via public API?
- Are colony food stores queryable?
- Are building registry and citizen assignments queryable?
- Right proximity threshold for command detection (suggest 8-10 blocks as start)
- Should role slots be expandable via a new building (Steward's Office concept)?
- What happens to role if citizen dies (not job assignment)?
- Should shadow entity be visible to other players in multiplayer?
- Maximum scan radius for Ranch Hand mob search?
- Should Scout patrol path be player-defined or auto-generated?
- Final mod name?

---

*Last updated: Session 1 — Pre-PoC*
