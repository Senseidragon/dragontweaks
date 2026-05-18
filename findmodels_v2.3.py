import os, re, json, sys, requests, argparse
from datetime import datetime
from dotenv import load_dotenv

load_dotenv()
API_KEY = os.getenv("OPENROUTER_API_KEY")
JSON_FILE = "model_config.json"

# --- RESTORED CORE GUARDRAILS ---
MIN_CONTEXT = 128000
MIN_PARAMS = 20  
LIMIT = 25
SIZE_PATTERN = re.compile(r'(\d+)b', re.IGNORECASE)

def fetch_and_rank_models(filters):
    print(f"[{datetime.now().strftime('%H:%M:%S')}] Auditing with active filters: {filters}...")

    headers = {
        "Authorization": f"Bearer {API_KEY}",
        "HTTP-Referer": "https://github.com/jesse-patterson/model-scanner",
    }

    try:
        response = requests.get("https://openrouter.ai/api/v1/models", headers=headers)
        response.raise_for_status()
        all_models = response.json().get('data', [])
    except Exception as e:
        print(f"Error fetching data: {e}")
        return

    eligible_models = []

    for model in all_models:
        m_id = model.get('id', '').lower()
        context = model.get('context_length', 0)
        pricing = model.get('pricing', {})
        description = model.get('description', '').lower()
        input_mods = model.get('architecture', {}).get('input_modalities', [])

        # 1. Base Filters
        if m_id.endswith(":free") or "router" in m_id: continue
        size_match = SIZE_PATTERN.search(m_id)
        if not size_match or int(size_match.group(1)) < MIN_PARAMS: continue
        if context < MIN_CONTEXT: continue

        # 2. STACKABLE Logic (The fix for Strike 2.5)
        is_thinking = any(x in m_id or x in description for x in ['thinking', 'reasoning', 'r1', 'o1', 'o3'])
        has_vision = "image" in input_mods
        is_coder = any(x in m_id or x in description for x in ['coder', 'code', 'instruct'])

        # Apply filters as logical ANDs
        if filters.get('ppress') and not (has_vision and is_thinking): continue
        if filters.get('thinking') and not is_thinking: continue
        if filters.get('vision') and not has_vision: continue
        if filters.get('coding') and not is_coder: continue
        if filters.get('flavor') and context < 128000: continue # Redundant but kept for mode logic

        try:
            input_cost = float(pricing.get('prompt', 0)) * 1_000_000
            output_cost = float(pricing.get('completion', 0)) * 1_000_000

            # Eigent-style agent workloads appear input/context heavy during learning runs.
            # Keep the old blended metric for comparison, but rank by this estimate.
            blended_cost = (input_cost + output_cost) / 2
            agent_learning_cost = (input_cost * 0.85) + (output_cost * 0.15)

            eligible_models.append({
                "model_id": model.get('id'),
                "input_cost_per_1m": round(input_cost, 4),
                "output_cost_per_1m": round(output_cost, 4),
                "blended_cost_per_1m": round(blended_cost, 4),
                "agent_learning_cost_per_1m": round(agent_learning_cost, 4),
                "context_window": context,
                "parameter_size": f"{size_match.group(1)}B"
            })
        except: continue

    eligible_models.sort(key=lambda x: x["agent_learning_cost_per_1m"])
    top_models = eligible_models[:LIMIT]

    with open(JSON_FILE, "w") as f:
        json.dump(top_models, f, indent=4)
    print(f"Success: {len(top_models)} models written to {JSON_FILE}.")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Multi-stack model scanner.")
    parser.add_argument("--flavor", action="store_true")
    parser.add_argument("--ppress", action="store_true")
    parser.add_argument("--thinking", action="store_true")
    parser.add_argument("--vision", action="store_true")
    parser.add_argument("--coding", action="store_true")

    args = parser.parse_args()
    fetch_and_rank_models(vars(args))