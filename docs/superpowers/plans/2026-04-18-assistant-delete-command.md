# Assistant Delete Command Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add `/assistant delete [name]` — no argument deletes all `AssistantEntity` instances across all loaded dimensions; a name argument deletes the nearest matching entity in the player's current dimension.

**Architecture:** All changes are in a single file (`AssistantCommand.java`). Two new private static methods (`deleteAll`, `deleteNearest`) are added and wired into the existing Brigadier command tree via a new `delete` literal. No new files needed.

**Tech Stack:** NeoForge 1.21.1, Brigadier (Minecraft's command framework), JUnit 5 (existing test suite)

---

### Task 1: Add `deleteAll` and `deleteNearest` methods to `AssistantCommand`

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/AssistantCommand.java`

Note: These methods depend on `MinecraftServer`, `ServerLevel`, and `AssistantEntity` — all of which require a running game environment. They are not unit-testable in isolation. Manual in-game testing is the verification path (see Task 2).

- [ ] **Step 1: Add `deleteAll` after `spawnWithName`**

In `AssistantCommand.java`, add this method after the closing brace of `spawnWithName`:

```java
private static int deleteAll(CommandContext<CommandSourceStack> ctx) {
    MinecraftServer server = ctx.getSource().getServer();
    int count = 0;
    for (ServerLevel level : server.getAllLevels()) {
        List<AssistantEntity> entities = level.getEntitiesOfClass(
            AssistantEntity.class, level.getWorldBounds()
        );
        for (AssistantEntity entity : entities) {
            entity.discard();
            count++;
        }
    }
    if (count == 0) {
        ctx.getSource().sendFailure(Component.literal("No assistants found."));
        return 0;
    }
    final int total = count;
    ctx.getSource().sendSuccess(() -> Component.literal("Removed " + total + " assistant(s)."), false);
    return 1;
}
```

- [ ] **Step 2: Add `deleteNearest` after `deleteAll`**

```java
private static int deleteNearest(CommandContext<CommandSourceStack> ctx, String name)
        throws CommandSyntaxException {
    ServerPlayer player = ctx.getSource().getPlayerOrException();
    ServerLevel level = player.serverLevel();

    AssistantEntity nearest = null;
    double nearestDistSq = Double.MAX_VALUE;
    for (AssistantEntity candidate : level.getEntitiesOfClass(AssistantEntity.class, level.getWorldBounds())) {
        if (candidate.getCustomName() == null) continue;
        if (!candidate.getCustomName().getString().equals(name)) continue;
        double distSq = player.distanceToSqr(candidate);
        if (distSq < nearestDistSq) {
            nearestDistSq = distSq;
            nearest = candidate;
        }
    }

    if (nearest == null) {
        ctx.getSource().sendFailure(Component.literal("No assistant named '" + name + "' found."));
        return 0;
    }

    nearest.discard();
    ctx.getSource().sendSuccess(() -> Component.literal(name + " removed."), false);
    return 1;
}
```

- [ ] **Step 3: Add `MinecraftServer` import if not already present**

Check the import block at the top of `AssistantCommand.java`. Add if missing:

```java
import net.minecraft.server.MinecraftServer;
```

---

### Task 2: Register the `delete` subcommand in `onRegisterCommands`

**Files:**
- Modify: `src/main/java/io/github/senseidragon/dragontweaks/AssistantCommand.java:29-39`

- [ ] **Step 1: Add the `delete` branch to the command tree**

The existing registration block ends like this:

```java
        event.getDispatcher().register(
            Commands.literal("assistant")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("spawn")
                    .executes(ctx -> spawnWithName(ctx, randomName()))
                    .then(Commands.argument("name", StringArgumentType.greedyString())
                        .executes(ctx -> spawnWithName(ctx, StringArgumentType.getString(ctx, "name")))
                    )
                )
        );
```

Replace it with:

```java
        event.getDispatcher().register(
            Commands.literal("assistant")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("spawn")
                    .executes(ctx -> spawnWithName(ctx, randomName()))
                    .then(Commands.argument("name", StringArgumentType.greedyString())
                        .executes(ctx -> spawnWithName(ctx, StringArgumentType.getString(ctx, "name")))
                    )
                )
                .then(Commands.literal("delete")
                    .executes(ctx -> deleteAll(ctx))
                    .then(Commands.argument("name", StringArgumentType.greedyString())
                        .executes(ctx -> deleteNearest(ctx, StringArgumentType.getString(ctx, "name")))
                    )
                )
        );
```

- [ ] **Step 2: Build**

```bash
./gradlew build
```

Expected: `BUILD SUCCESSFUL`. Fix any compile errors before continuing.

---

### Task 3: In-game verification

**Files:** None — manual testing only.

- [ ] **Step 1: Launch the client**

```bash
./gradlew runClient
```

- [ ] **Step 2: Test `delete` with no assistants present**

In-game, run:
```
/assistant delete
```
Expected chat: red failure message `No assistants found.`

- [ ] **Step 3: Test `delete` removes all**

Spawn two assistants:
```
/assistant spawn Marcus
/assistant spawn Elara
```
Then run:
```
/assistant delete
```
Expected: `Removed 2 assistant(s).` Both entities disappear from the world.

- [ ] **Step 4: Test `delete <name>` with exact match**

Spawn two assistants with the same name and one with a different name:
```
/assistant spawn Marcus
/assistant spawn Marcus
/assistant spawn Elara
```
Stand next to one Marcus. Run:
```
/assistant delete Marcus
```
Expected: `Marcus removed.` Only the nearest Marcus disappears. The other Marcus and Elara remain.

- [ ] **Step 5: Test `delete <name>` with no match**

With no assistant named "Zara" present, run:
```
/assistant delete Zara
```
Expected: red failure message `No assistant named 'Zara' found.`

- [ ] **Step 6: Test case-sensitivity**

Spawn:
```
/assistant spawn Marcus
```
Run:
```
/assistant delete marcus
```
Expected: `No assistant named 'marcus' found.` (lowercase does not match).

---

### Task 4: Commit

- [ ] **Step 1: Commit**

```bash
git add src/main/java/io/github/senseidragon/dragontweaks/AssistantCommand.java
git commit -m "feat: add /assistant delete [name] command"
```

- [ ] **Step 2: Push**

```bash
git push
```
