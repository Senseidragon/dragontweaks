# DragonTweaks — AI Session Entry Point

*Default startup doc. Read this first. Read only what is listed here for your task.*

---

## Startup Checklist (Every Session)

1. Confirm branch is `phase-1`.
2. Run `./gradlew build` and confirm clean.
3. Read only the docs listed below for your task.
4. Report branch and build status before proceeding.

---

## What To Read For Which Task

| Task area | Read this doc |
|---|---|
| **Any task — always** | `docs/current_state.md` |
| **Any task — always** | `docs/architecture_rules.md` |
| Citizen tracking, role assignment, nicknames, advisor state machine, BookAdvisor movement | `docs/citizen_advisor_integration.md` |
| Advisor/Planner panels, diagnostic report, planner dependency chain | `docs/panels_and_diagnostics.md` |
| MineColonies API calls | `docs/stubs/` (use `docs/STUB_INDEX.md` to find specific stubs) |
| MineColonies game mechanics, building behaviour, worker rules, recipe requirements | `docs/minecolonies_wiki.md` — authoritative 1.21.1 wiki snapshot; will not be updated further |
| Spec for advisor panel UI | `docs/advisor_panel_spec_v0_2.md` |
| Spec for advisor diagnostic logic | `docs/advisor_diagnostic_logic_spec_v0_1.md` |
| Advisor multi-citizen branching, throttle suppression, root cause suppression logic | `docs/advisor_branching_spec_v0_2.md` |
| Advisor LLM prompt wording, response ranking, evaluation harness prompts, or model behavior tests | `docs/advisor_prompt_engineering_spec_v0_2.md` — Do not use as proof that data is currently available in code. Verify current implementation in `docs/panels_and_diagnostics.md` and source first. |
| Advisor role design rationale — why persistent state memory is the core requirement | `docs/advisor_lessons_learned_v4.md` |
| Spec for planner panel UI | `docs/planner_panel_spec_v0_4.md` |
| Spec for planner dependency data | `docs/planner_dependency_data_spec_v0_1.md` |
| Citizen role schema — field definitions and custom role authoring guide for `citizen-roles.json` | `docs/citizen-roles-schema.md` |
| Citizen role definitions — current role configs for Advisor, Planner, Scout, Ranch Hand | `docs/citizen-roles.json` (scraper input/output; not loaded by the mod at runtime) |
| Open design questions | `docs/open_questions_log.md` |
| Full original design intent | `docs/assistantmod_designdoc_v0_3.md` |
| **Why we made a past decision** | `docs/session_history.md` |

---

## Do Not Read Unless Asked

- `docs/devchat.md` — superseded by this doc set; kept for reference only
- `docs/session_history.md` — archaeology only; not part of default startup context

---

## After Completing Any Task

Update the following docs:
- `docs/current_state.md` — update file status table and any newly locked decisions
- `docs/session_log.md` — append a new session entry (most recent at top)
- `docs/dragontweaks_verification_checklist.md` — if `./gradlew build` passed
- `docs/open_questions_log.md` — if any questions were resolved or new ones opened

Do **not** update `docs/devchat.md` — it is archive only.
