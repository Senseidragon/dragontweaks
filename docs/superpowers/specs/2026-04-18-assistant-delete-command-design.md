# Design: `/assistant delete [name]`

**Date:** 2026-04-18
**Status:** Approved

---

## Summary

Add a `delete` subcommand to `/assistant` that removes `AssistantEntity` instances from the world. With no argument it removes all assistants across all loaded dimensions; with a name argument it removes the nearest matching assistant in the player's current dimension.

---

## Command Structure

```
/assistant delete             # delete all assistants, all dimensions
/assistant delete <name>      # delete nearest assistant named <name> in player's dimension
```

Both branches require permission level 2 (inherited from the parent `assistant` literal).

---

## Implementation

### Command Registration (`AssistantCommand.onRegisterCommands`)

Add a `delete` literal to the existing `Commands.literal("assistant")` chain:

```
.then(Commands.literal("delete")
    .executes(ctx -> deleteAll(ctx))
    .then(Commands.argument("name", StringArgumentType.greedyString())
        .executes(ctx -> deleteNearest(ctx, StringArgumentType.getString(ctx, "name")))
    )
)
```

### `deleteAll(ctx)`

- Obtains `MinecraftServer` from `ctx.getSource().getServer()`
- Iterates `server.getAllLevels()`
- For each level: `level.getEntitiesOfClass(AssistantEntity.class, level.getWorldBounds())`
- Calls `entity.discard()` on every result, accumulates count
- Success: `"Removed N assistant(s)."` (works from console — no player required)
- If count == 0: sends failure `"No assistants found."`
- Returns 1 if any removed, 0 otherwise

### `deleteNearest(ctx, name)`

- Requires a player; sends failure `"This command must be run by a player."` if source is not a player
- Searches `player.serverLevel()` only (proximity meaningless across dimensions)
- `level.getEntitiesOfClass(AssistantEntity.class, level.getWorldBounds())`
- Filters to entities where `entity.getCustomName() != null && entity.getCustomName().getString().equals(name)` (exact, case-sensitive — matches how names are set at spawn)
- Picks entity with smallest `player.distanceToSqr(entity)`
- Calls `entity.discard()` on match
- Success: `"<name> removed."`
- No match: `"No assistant named '<name>' found."` (failure)
- Returns 1 on success, 0 on failure

---

## Constraints

- Name matching is exact and case-sensitive — consistent with how custom names are assigned at spawn
- `deleteNearest` scopes to the player's current dimension only; assistants in other dimensions are not considered
- `deleteAll` has no dimension scope restriction — it clears all loaded levels
- `entity.discard()` is the correct removal method for server-side entities in NeoForge (safe to call on server thread; command execution is on server thread)
