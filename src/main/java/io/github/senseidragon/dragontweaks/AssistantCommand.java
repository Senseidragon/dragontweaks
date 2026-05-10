package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.IColonyManager;
import com.minecolonies.api.colony.ICitizenData;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@EventBusSubscriber(modid = DragonTweaks.MODID)
public class AssistantCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssistantCommand.class);
    public static String localeOverride = null;

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
            Commands.literal("assistant")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("spawn")
                    .executes(ctx -> spawnWithNameAndRole(ctx, "Assistant", "villager"))
                    .then(Commands.argument("name", StringArgumentType.string())
                        .then(Commands.argument("role", StringArgumentType.string())
                            .executes(ctx -> spawnWithNameAndRole(ctx,
                                StringArgumentType.getString(ctx, "name"),
                                StringArgumentType.getString(ctx, "role")))
                        )
                    )
                )
                .then(Commands.literal("delete")
                    .executes(ctx -> deleteAll(ctx))
                    .then(Commands.argument("name", StringArgumentType.greedyString())
                        .executes(ctx -> deleteNearest(ctx, StringArgumentType.getString(ctx, "name")))
                    )
                )
                .then(Commands.literal("follow")
                    .executes(ctx -> setFollowStateNearest(ctx, true))
                    .then(Commands.argument("name", StringArgumentType.greedyString())
                        .executes(ctx -> setFollowState(ctx, StringArgumentType.getString(ctx, "name"), true))
                    )
                )
                .then(Commands.literal("stop")
                    .executes(ctx -> setFollowStateNearest(ctx, false))
                    .then(Commands.argument("name", StringArgumentType.greedyString())
                        .executes(ctx -> setFollowState(ctx, StringArgumentType.getString(ctx, "name"), false))
                    )
                )
                .then(Commands.literal("locale")
                    .executes(ctx -> reportLocale(ctx))
                    .then(Commands.literal("reset")
                        .executes(ctx -> resetLocale(ctx))
                    )
                    .then(Commands.argument("code", StringArgumentType.string())
                        .executes(ctx -> setLocale(ctx, StringArgumentType.getString(ctx, "code")))
                    )
                )
                .then(Commands.literal("advisor")
                    .executes(AssistantPanelCommand::openAdvisorPanel)
                )
                .then(Commands.literal("planner")
                    .executes(AssistantPanelCommand::openPlannerPanel)
                )
                .then(Commands.literal("revoke")
                    .then(Commands.argument("citizenName", StringArgumentType.greedyString())
                        .executes(ctx -> revokeByName(ctx, StringArgumentType.getString(ctx, "citizenName")))
                    )
                )
        );
    }

    private static int spawnWithNameAndRole(CommandContext<CommandSourceStack> ctx, String name, String role) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        ServerLevel level = player.serverLevel();

        AssistantEntity entity = ModEntities.ASSISTANT.get().create(level);
        if (entity == null) {
            ctx.getSource().sendFailure(Component.literal("Failed to create assistant entity."));
            return 0;
        }

        entity.setCustomName(Component.literal(name));
        entity.setRole(role);
        entity.moveTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), 0f);
        level.addFreshEntity(entity);

        ctx.getSource().sendSuccess(() -> Component.literal(name + " (" + role + ") spawned."), false);
        return 1;
    }

    private static int deleteAll(CommandContext<CommandSourceStack> ctx) {
        MinecraftServer server = ctx.getSource().getServer();
        int count = 0;
        for (ServerLevel level : server.getAllLevels()) {
            List<AssistantEntity> entities = level.getEntitiesOfClass(
                AssistantEntity.class, new AABB(-3e7, -3e7, -3e7, 3e7, 3e7, 3e7)
            );
            for (AssistantEntity entity : entities) {
                ConversationMemory.clearAll(entity.getUUID());
                ObservationTicker.clearState(entity.getUUID());
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

    private static int deleteNearest(CommandContext<CommandSourceStack> ctx, String name) {
        ServerPlayer player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendFailure(Component.literal("This command must be run by a player."));
            return 0;
        }
        ServerLevel level = player.serverLevel();

        AssistantEntity nearest = null;
        double nearestDistSq = Double.MAX_VALUE;
        for (AssistantEntity candidate : level.getEntitiesOfClass(
                AssistantEntity.class, new AABB(-3e7, -3e7, -3e7, 3e7, 3e7, 3e7))) {
            if (candidate.getCustomName() == null) continue;
            if (!nameMatches(candidate.getCustomName().getString(), name)) continue;
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

        ConversationMemory.clearAll(nearest.getUUID());
        ObservationTicker.clearState(nearest.getUUID());
        nearest.discard();
        ctx.getSource().sendSuccess(() -> Component.literal(name + " removed."), false);
        return 1;
    }

    private static int setFollowStateNearest(CommandContext<CommandSourceStack> ctx, boolean follow) {
        ServerPlayer player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendFailure(Component.literal("This command must be run by a player."));
            return 0;
        }
        ServerLevel level = player.serverLevel();

        AssistantEntity nearest = null;
        double nearestDistSq = Double.MAX_VALUE;
        for (AssistantEntity candidate : level.getEntitiesOfClass(
                AssistantEntity.class, player.getBoundingBox().inflate(64))) {
            double distSq = player.distanceToSqr(candidate);
            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                nearest = candidate;
            }
        }

        if (nearest == null) {
            ctx.getSource().sendFailure(Component.literal("No assistant nearby."));
            return 0;
        }

        nearest.setFollowing(follow);
        if (!follow) nearest.setHomePosition(nearest.blockPosition());
        String entityName = nearest.getCustomName() != null ? nearest.getCustomName().getString() : "Assistant";
        String verb = follow ? "following." : "stopped.";
        ctx.getSource().sendSuccess(() -> Component.literal(entityName + " is now " + verb), false);
        return 1;
    }

    private static int setFollowState(CommandContext<CommandSourceStack> ctx, String name, boolean follow) {
        ServerPlayer player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendFailure(Component.literal("This command must be run by a player."));
            return 0;
        }
        ServerLevel level = player.serverLevel();

        AssistantEntity nearest = null;
        double nearestDistSq = Double.MAX_VALUE;
        for (AssistantEntity candidate : level.getEntitiesOfClass(
                AssistantEntity.class, new AABB(-3e7, -3e7, -3e7, 3e7, 3e7, 3e7))) {
            if (candidate.getCustomName() == null) continue;
            if (!nameMatches(candidate.getCustomName().getString(), name)) continue;
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

        nearest.setFollowing(follow);
        if (!follow) nearest.setHomePosition(nearest.blockPosition());
        String verb = follow ? "following." : "stopped.";
        String entityName = nearest.getCustomName().getString();
        ctx.getSource().sendSuccess(() -> Component.literal(entityName + " is now " + verb), false);
        return 1;
    }

    private static int reportLocale(CommandContext<CommandSourceStack> ctx) {
        if (localeOverride == null) {
            ctx.getSource().sendSuccess(() -> Component.literal("No locale override set."), false);
        } else {
            ctx.getSource().sendSuccess(() -> Component.literal("Active locale override: " + localeOverride), false);
        }
        return 1;
    }

    private static int resetLocale(CommandContext<CommandSourceStack> ctx) {
        localeOverride = null;
        ctx.getSource().sendSuccess(() -> Component.literal("Locale override cleared. Using en_us."), false);
        return 1;
    }

    private static int setLocale(CommandContext<CommandSourceStack> ctx, String code) {
        String normalized = code.toLowerCase();
        if (!normalized.matches("[a-z]{2}_[a-z]{2}")) {
            LOGGER.warn("Invalid locale code '{}' — falling back to en_us", code);
            localeOverride = "en_us";
        } else {
            localeOverride = normalized;
        }
        final String active = localeOverride;
        ctx.getSource().sendSuccess(() -> Component.literal("Locale override set to: " + active), false);
        return 1;
    }

    private static int revokeByName(CommandContext<CommandSourceStack> ctx, String input) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();

        if (DragonTweaks.LITE_MODE) {
            ctx.getSource().sendFailure(Component.literal("Role assignment is not available in Lite mode."));
            return 0;
        }

        if (!ModList.get().isLoaded("minecolonies")) {
            ctx.getSource().sendFailure(Component.literal("MineColonies is not loaded."));
            return 0;
        }

        MinecraftServer server = player.getServer();
        ServerLevel overworld = server.getLevel(Level.OVERWORLD);
        if (overworld == null) return 0;

        RoleAssignmentData roleData = RoleAssignmentData.get(overworld);
        AdvisorStateData advisorData = AdvisorStateData.get(overworld);

        int matchedCitizenId = -1;
        String matchedCitizenName = null;
        String matchedRoleType = null;
        IColony matchedColony = null;
        ServerLevel matchedLevel = null;

        search:
        for (ServerLevel level : server.getAllLevels()) {
            // TODO: verify IColonyManager.getInstance().getColonies(level) against stubs
            for (IColony colony : IColonyManager.getInstance().getColonies(level)) {
                // TODO: verify colony.getCitizenManager().getCitizens() against stubs
                for (ICitizenData citizen : colony.getCitizenManager().getCitizens()) {
                    if (!roleData.isAssigned(citizen.getId())) continue;
                    // TODO: verify ICitizenData.getName() against stubs
                    String citizenName = citizen.getName();
                    if (nameMatches(citizenName, input)) {
                        matchedCitizenId = citizen.getId();
                        matchedCitizenName = citizenName;
                        matchedRoleType = roleData.getRecord(matchedCitizenId).roleType();
                        matchedColony = colony;
                        matchedLevel = level;
                        break search;
                    }
                }
            }
        }

        if (matchedCitizenId == -1) {
            ctx.getSource().sendFailure(Component.literal("No assigned citizen matching '" + input + "' was found."));
            return 0;
        }

        roleData.revoke(matchedCitizenId);

        final String citizenName = matchedCitizenName;
        ctx.getSource().sendSuccess(() -> Component.literal(citizenName + "'s role has been revoked."), false);

        if ("advisor".equalsIgnoreCase(matchedRoleType)) {
            UUID playerUUID = player.getUUID();
            advisorData.setState(playerUUID, AdvisorState.COLONY_NO_CITIZEN);
            advisorData.setAssignedCitizenId(playerUUID, null);

            UUID advisorEntityUUID = advisorData.getAdvisorEntityUUID(playerUUID);
            if (advisorEntityUUID != null) {
                for (ServerLevel level : server.getAllLevels()) {
                    Entity entity = level.getEntity(advisorEntityUUID);
                    if (entity instanceof BookAdvisorEntity) {
                        entity.discard();
                        break;
                    }
                }
                advisorData.setAdvisorEntityUUID(playerUUID, null);
            }

            if (matchedColony != null && matchedLevel != null
                    && matchedColony.getServerBuildingManager().hasTownHall()) {
                BlockPos thPos = matchedColony.getServerBuildingManager().getTownHall().getPosition();
                BookAdvisorEntity newAdvisor = ModEntities.BOOK_ADVISOR.get().create(matchedLevel);
                if (newAdvisor != null) {
                    newAdvisor.setOwner(player);
                    newAdvisor.moveTo(thPos.getX() + 0.5, thPos.getY() + 1.0, thPos.getZ() + 0.5, 0f, 0f);
                    matchedLevel.addFreshEntity(newAdvisor);
                    advisorData.setAdvisorEntityUUID(playerUUID, newAdvisor.getUUID());
                }
            }
        }

        return 1;
    }

    static boolean nameMatches(String entityName, String token) {
        String e = entityName.toLowerCase();
        String t = token.toLowerCase();
        return e.contains(t) || t.contains(e);
    }
}
