package io.github.senseidragon.dragontweaks;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.List;
import java.util.random.RandomGenerator;

@EventBusSubscriber(modid = DragonTweaks.MODID)
public class AssistantCommand {

    private static final List<String> RANDOM_NAMES = List.of(
        "Aldric", "Brenna", "Caden", "Dessa", "Emric",
        "Faye", "Gareth", "Hilda", "Isak", "Jora",
        "Kellan", "Lysa", "Maren", "Niels", "Orra"
    );

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
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
    }

    private static String randomName() {
        return RANDOM_NAMES.get(RandomGenerator.getDefault().nextInt(RANDOM_NAMES.size()));
    }

    private static int spawnWithName(CommandContext<CommandSourceStack> ctx, String name) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        ServerLevel level = player.serverLevel();

        AssistantEntity entity = ModEntities.ASSISTANT.get().create(level);
        if (entity == null) {
            ctx.getSource().sendFailure(Component.literal("Failed to create assistant entity."));
            return 0;
        }

        entity.setCustomName(Component.literal(name));
        entity.moveTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), 0f);
        level.addFreshEntity(entity);

        ctx.getSource().sendSuccess(() -> Component.literal(name + " spawned."), false);
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
}
