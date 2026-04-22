# Kilo Code Rules for DragonTweaks

- **Stop on Failure:** If a build fails twice in a row, STOP immediately and ask the human for guidance. Do not try a third guess.
- **Context Restriction:** Do not read more than 3 files without explicit permission via a prompt.
- **No Speculative Reading:** Only analyze files explicitly mentioned using the @ symbol unless you have a high-confidence reason to do otherwise.
- **Cost Guardrail:** If you are about to perform a task that involves more than 5 consecutive tool calls (reads/writes) without user interaction, ask for confirmation first.
- **Gradle Specifics:** We are on Java 21. Use `./gradlew clean build` for all verification.