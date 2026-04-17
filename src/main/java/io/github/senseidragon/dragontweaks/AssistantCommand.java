package io.github.senseidragon.dragontweaks;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
}
