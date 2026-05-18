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

Last updated 23 hours ago
Instructions

Using your knowledge of the Minecolonies Wiki my goals is developing a mod in which you will largely influence how the advisor and planner roles are developed, you will strive to understand the relationships between the various mechanics in Minecolonies. You will carefully consider the current colony circumstances and available nearby resources before suggesting a course of action. You will refrain from making wild guesses or invent hallucinations to provide plausible yet incorrect answers. Your personality will be positive and upbeat, you will not engage in "yes man" mentality. If you detect a flaw in my plans, say so, and offer alternative advice.
Files
10% of project capacity used
Indexing

findmodels_v2_4.py

6.70 KB •207 lines•Formatting may be inconsistent from source
import os, re, json, sys, requests, argparse
from datetime import datetime
from dotenv import load_dotenv

load_dotenv()
API_KEY = os.getenv("OPENROUTER_API_KEY")
JSON_FILE = "model_config.json"

# --- HARD GUARDRAILS (all tiers) ---
MIN_CONTEXT = 128000
MIN_PARAMS = 20
LIMIT = 20  # candidates per role section
SIZE_PATTERN = re.compile(r'(\d+)b', re.IGNORECASE)

# --- ROLE TIER DEFINITIONS ---
# Each tier defines:
#   input_weight / output_weight: cost ranking formula
#   exclude_reasoning: whether reasoning models are filtered out
#   require_reasoning: whether non-reasoning models are filtered out
#   description: human-readable label
ROLE_TIERS = {
    "flavor": {
        "description": "Tier 1 — Flavor NPCs (idle chatter, immersion, no colony data)",
        "input_weight": 0.60,
        "output_weight": 0.40,
        "exclude_reasoning": True,
        "require_reasoning": False,
    },
    "advisory": {
        "description": "Tier 2 — Advisory / Planner (colony state, reasoning, instruction-following)",
        "input_weight": 0.80,
        "output_weight": 0.20,
        "exclude_reasoning": False,
        "require_reasoning": True,
    },
    "specialized": {
        "description": "Tier 2 — Specialized roles (Ranch Hand, Scout — procedural, pathfinding-adjacent)",
        "input_weight": 0.75,
        "output_weight": 0.25,
        "exclude_reasoning": False,
        "require_reasoning": True,
    },
    "tactical": {
        "description": "Tier 3 — Tactical roles (Military General, threat assessment, multi-step planning)",
        "input_weight": 0.90,
        "output_weight": 0.10,
        "exclude_reasoning": False,
        "require_reasoning": True,
    },
}


def is_reasoning_model(m_id, description):
    return any(x in m_id or x in description
               for x in ['thinking', 'reasoning', 'r1', 'o1', 'o3'])


def fetch_candidates(headers):
    """Fetch all models from OpenRouter and return the raw list."""
    try:
        response = requests.get("https://openrouter.ai/api/v1/models", headers=headers)
        response.raise_for_status()
        return response.json().get('data', [])
    except Exception as e:
        print(f"Error fetching models: {e}")
        sys.exit(1)


def apply_hard_filters(model):
    """
    Returns a parsed candidate dict if the model passes hard guardrails,
    or None if it should be excluded from all tiers.
    """
    m_id = model.get('id', '').lower()
    context = model.get('context_length', 0)
    pricing = model.get('pricing', {})
    description = model.get('description', '').lower()
    input_mods = model.get('architecture', {}).get('input_modalities', [])

    # Exclude free-tier and router models
    if m_id.endswith(":free") or "router" in m_id:
        return None

    # Exclude models without parseable parameter size
    size_match = SIZE_PATTERN.search(m_id)
    if not size_match or int(size_match.group(1)) < MIN_PARAMS:
        return None

    # Exclude insufficient context windows
    if context < MIN_CONTEXT:
        return None

    try:
        input_cost = float(pricing.get('prompt', 0)) * 1_000_000
        output_cost = float(pricing.get('completion', 0)) * 1_000_000
    except (ValueError, TypeError):
        return None

    return {
        "model_id": model.get('id'),
        "parameter_size": f"{size_match.group(1)}B",
        "context_window": context,
        "input_cost_per_1m": round(input_cost, 4),
        "output_cost_per_1m": round(output_cost, 4),
        "is_reasoning": is_reasoning_model(m_id, description),
        "has_vision": "image" in input_mods,
        # compliance_score is null until test_compliance.py runs
        "compliance_score": None,
        "compliance_tested": False,
    }


def rank_for_tier(candidates, tier_config):
    """
    Filter and rank a candidate list for a specific role tier.
    Returns top LIMIT models sorted by role-weighted cost.
    """
    iw = tier_config["input_weight"]
    ow = tier_config["output_weight"]

    filtered = []
    for c in candidates:
        if tier_config["exclude_reasoning"] and c["is_reasoning"]:
            continue
        if tier_config["require_reasoning"] and not c["is_reasoning"]:
            continue

        role_cost = round(
            (c["input_cost_per_1m"] * iw) + (c["output_cost_per_1m"] * ow), 4
        )

        entry = dict(c)  # copy — don't mutate the master candidate list
        entry["role_weighted_cost_per_1m"] = role_cost
        # Drop the raw reasoning flag from output (implicit in tier)
        del entry["is_reasoning"]
        filtered.append(entry)

    filtered.sort(key=lambda x: x["role_weighted_cost_per_1m"])
    return filtered[:LIMIT]


def main():
    parser = argparse.ArgumentParser(
        description="DragonTweaks model scraper — role-tiered candidate discovery."
    )
    parser.add_argument(
        "--tiers",
        nargs="+",
        choices=list(ROLE_TIERS.keys()) + ["all"],
        default=["all"],
        help="Which role tiers to include in output. Default: all.",
    )
    args = parser.parse_args()

    tiers_to_run = list(ROLE_TIERS.keys()) if "all" in args.tiers else args.tiers

    headers = {
        "Authorization": f"Bearer {API_KEY}",
        "HTTP-Referer": "https://github.com/SenseiDragon/dragontweaks",
    }

    print(f"[{datetime.now().strftime('%H:%M:%S')}] Fetching OpenRouter model list...")
    all_models = fetch_candidates(headers)
    print(f"  {len(all_models)} models returned from API.")

    # Apply hard filters once — shared across all tiers
    candidates = []
    for model in all_models:
        parsed = apply_hard_filters(model)
        if parsed:
            candidates.append(parsed)
    print(f"  {len(candidates)} models passed hard filters.")

    # Build role sections
    output = {
        "generated": datetime.utcnow().isoformat() + "Z",
        "guardrails": {
            "min_context": MIN_CONTEXT,
            "min_params_b": MIN_PARAMS,
            "excludes_free_tier": True,
            "excludes_router_models": True,
        },
        "roles": {}
    }

    for tier_key in tiers_to_run:
        tier_config = ROLE_TIERS[tier_key]
        ranked = rank_for_tier(candidates, tier_config)
        output["roles"][tier_key] = {
            "description": tier_config["description"],
            "input_weight": tier_config["input_weight"],
            "output_weight": tier_config["output_weight"],
            "reasoning_required": tier_config["require_reasoning"],
            "reasoning_excluded": tier_config["exclude_reasoning"],
            "candidates": ranked,
        }
        print(f"  {tier_key}: {len(ranked)} candidates ranked.")

    with open(JSON_FILE, "w") as f:
        json.dump(output, f, indent=4)

    print(f"\nDone. Written to {JSON_FILE}.")


if __name__ == "__main__":
    main()
