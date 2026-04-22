# OpenRouter Migration Design

**Date:** 2026-04-22
**Status:** Approved

## Goal

Migrate the LLM backend from a local Ollama instance (`OllamaClient`) to OpenRouter (cloud, OpenAI-compatible API). API key is stored server-side in a `.env` file, never in config or logs.

---

## Files Changed

| Action | File |
|---|---|
| Rename + rewrite | `OllamaClient.java` → `LLMClient.java` |
| New | `EnvLoader.java` |
| Update defaults/comments | `Config.java` |
| Import update only | `ChatInterceptor.java`, `ObservationTicker.java`, `DragonTweaks.java` |

---

## EnvLoader

Static utility class. Reads `KEY=VALUE` pairs from a `.env` file in the server's working directory (`System.getProperty("user.dir")`). Ignores blank lines and lines starting with `#`. Caches the parsed map after first load — file is only read once per JVM session.

- `EnvLoader.get(String key)` → `String` or `null` if file missing or key absent
- Never logs the value of any key — only logs presence/absence

Expected `.env` entry:
```
OPENROUTER_API_KEY=sk-or-...
```

---

## LLMClient

Rename of `OllamaClient`. All callers updated to `LLMClient`. Static utility class, same single-thread executor pattern, same async threading guarantees — only the HTTP layer changes.

### Request format

Replaces Ollama's `{model, system, prompt, stream, think, options}` with OpenAI messages format:

```json
{
  "model": "<Config.LLM_MODEL>",
  "messages": [
    { "role": "system", "content": "<buildSystemPrompt(...)>" },
    { "role": "user",   "content": "<history + player message>" }
  ],
  "max_tokens": 100,
  "stream": false
}
```

Removed Ollama-specific fields: `think`, `options.num_predict`.

### Auth header

Every request adds:
```
Authorization: Bearer <OPENROUTER_API_KEY>
```

Key loaded via `EnvLoader.get("OPENROUTER_API_KEY")`. If null: log error, send fallback, do not throw.

### Response parsing

`parseResponse()` updated from Ollama's `obj.get("response")` to:
```java
obj.get("choices").getAsJsonArray()
   .get(0).getAsJsonObject()
   .get("message").getAsJsonObject()
   .get("content").getAsString()
```

### warmup() removed

Ollama required a warmup to pre-load the model into VRAM. OpenRouter is a cloud API — no warmup needed. The `warmup()` method is deleted and its call site in `DragonTweaks.java` is removed.

### Everything else unchanged

- `query()` — same signature, same async pattern, same fallback behavior
- `observe()` — same signature, same async pattern
- `scanSurroundings()` / `scanSurroundingsRaw()` — no change (not HTTP)
- `buildSystemPrompt()` — no change
- `ConversationMemory` history injection — no change (flat string in user message)
- `shutdown()` — no change

---

## Config Changes

Two existing values updated — no new values added. API key is not in config.

| Key | Old default | New default |
|---|---|---|
| `llmEndpoint` | `http://SENSEI:11434/api/generate` | `https://openrouter.ai/api/v1/chat/completions` |
| `llmModel` | `gemma4:26b` | `google/gemma-2-27b-it` |

Comments on both values updated from "Ollama" to "OpenRouter". No structural change to `Config.java`.

---

## Constraints

- `max_tokens: 100` always — NPC responses must be short. Never omit.
- `stream: false` always.
- API key never transmitted to clients. Never logged.
- All HTTP calls remain async on the `dragontweaks-llm` thread. Hard Architectural Rules 1–3 unchanged.
- `ConversationMemory` storage format unchanged (flat string deque, NBT serialization unchanged).

---

## Definition of Done

- `./gradlew build` passes.
- In-game: player speaks to NPC — response arrives via OpenRouter within timeout.
- In-game: missing `.env` or missing key → fallback message, no crash, error logged.
- No Ollama references remain in the codebase (class names, config comments, log prefixes).
