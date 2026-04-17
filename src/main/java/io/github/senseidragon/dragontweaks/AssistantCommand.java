package io.github.senseidragon.dragontweaks;

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

@EventBusSubscriber(modid = DragonTweaks.MODID)
public class AssistantCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("assistant")
                        .requires(src -> src.hasPermission(2))
                        .then(Commands.literal("spawn")
                                .executes(AssistantCommand::spawn))
        );
    }

    private static int spawn(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        ServerLevel level = player.serverLevel();

        AssistantEntity entity = ModEntities.ASSISTANT.get().create(level);
        if (entity == null) {
            ctx.getSource().sendFailure(Component.literal("Failed to create assistant entity."));
            return 0;
        }

        entity.moveTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), 0f);
        level.addFreshEntity(entity);

        ctx.getSource().sendSuccess(() -> Component.literal("Assistant spawned."), false);
        return 1;
    }
}
