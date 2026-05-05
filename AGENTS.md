# Repository Overview

## Project Description
DragonTweaks is a Minecraft mod built for the NeoForge loader. Its primary goal is to introduce intelligent, personality-driven NPCs (Assistants) that can interact with players using Large Language Models (LLMs).

**Key Features:**
- **LLM-Powered NPCs:** NPCs use an LLM (via OpenRouter) to generate contextually aware responses.
- **Environmental Awareness:** NPCs can "observe" their surroundings (time of day, weather, nearby entities) and react to changes.
- **Conversation Memory:** Maintains a history of interactions between players and specific NPCs to allow for continuous, coherent dialogue.
- **Role-Based Personas:** Different NPC roles (e.g., blacksmith, farmer) have distinct personality blocks and behaviors.

**Key Technologies:**
- **Java 21**
- **NeoForge** (Minecraft Modding API)
- **OpenRouter API** (for LLM access)
- **Gradle** (Build system)
- **Gson** (JSON processing)

## Architecture Overview

The mod follows a typical Minecraft modding architecture but includes a specialized asynchronous layer for LLM communication.

### Main Components:
- **DragonTweaks (Core):** The entry point that handles mod initialization and event registration.
- **AssistantEntity & AssistantRenderer:** Manages the lifecycle, logic, and visual representation of the intelligent NPCs.
- **LLMClient:** An asynchronous service that handles HTTP requests to the LLM provider, including prompt construction (System Prompts with environmental context).
- **ConversationMemory:** A storage mechanism for tracking chat history between players and NPCs.
- **ObservationTicker:** Periodically scans for environmental changes or player proximity to trigger NPC "observations."
- **ChatInterceptor:** Hooks into server chat to allow players to communicate with NPCs.
- **Config:** Manages mod settings like LLM endpoints, models, and NPC awareness parameters.

### Data Flow:
1. **Input:** A player sends a chat message or an environmental trigger occurs (e.g., player approaches NPC).
2. **Context Gathering:** The system gathers current world state (weather, time, surrounding entities) and historical conversation data.
3. **Prompt Construction:** `LLMClient` builds a complex system prompt including the NPC's persona and environmental context.
4. **Async Request:** An asynchronous HTTP request is sent to the LLM provider.
5. **Response Handling:** Once the response arrives, it is parsed and sent back to the Minecraft server to be displayed as a chat message and stored in `ConversationMemory`.

## Directory Structure

- `src/main/java/io/github/senseidragon/dragontweaks/`: Main source code.
    - `AssistantEntity.java`: Logic for the NPC entity.
    - `LLMClient.java`: Handles API communication and prompt building.
    - `ConversationMemory.java`: Manages dialogue history.
    - `ObservationTicker.java`: Triggers NPC environmental awareness.
    - `RolePersona.java`: Defines character personality templates.
    - `Config.java`: Configuration management.
- `src/main/resources/`: Assets, language files, and NeoForge metadata.
- `docs/`: Project documentation, design specs, and stubs.
- `run/`: Local development environment (server/client instances, logs, configs).
- `libs/`: Local dependency JARs.
- `gradle/`: Gradle wrapper files.

## Development Workflow

### Building and Running
- **Build:** Use `./gradlew clean build` to compile the project.
- **Run:** The project is designed to be run within a NeoForge development environment. The `run/` directory contains the necessary configurations for running a local test instance.

### Testing
- Unit tests are located in `src/test/java/io/github/senseidragon/dragontweaks/`.
- Use `./gradlew test` to execute them.

### Environment Setup
- Ensure an `OPENROUTER_API_KEY` is present in a `.env` file in the root directory for LLM features to function.
- The mod uses `EnvLoader` to read environment variables.

### Verification
- Always use `./gradlew clean build` to ensure a clean state before verification.

## Security Rules

- **Never hardcode API keys, tokens, or secrets in any tracked file** (`.ps1`, `.sh`, `.java`, configs, etc.). Always read credentials from `run/.env` at runtime.
- `run/.env` is gitignored and must never be committed.
- If a script needs a credential, use the same pattern as `claude_gemma.ps1`: parse `KEY=value` from `run/.env` at runtime.
