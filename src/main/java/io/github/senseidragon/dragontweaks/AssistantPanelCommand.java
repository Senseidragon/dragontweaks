package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.IMinecoloniesAPI;
import com.minecolonies.api.colony.IColony;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class AssistantPanelCommand {

    static int openAdvisorPanel(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        if (!ModList.get().isLoaded("minecolonies")) {
            ctx.getSource().sendFailure(Component.literal("MineColonies is not loaded."));
            return 0;
        }
        IColony colony = findNearestColony(player);
        if (colony == null) {
            ctx.getSource().sendFailure(Component.literal("No colony found. You must be near or own a colony."));
            return 0;
        }
        AdvisorPanelPayload payload = AdvisorPanelPayload.build(colony);
        PacketDistributor.sendToPlayer(player, new OpenAdvisorPanelPacket(payload));
        return 1;
    }

    static int openPlannerPanel(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        if (!ModList.get().isLoaded("minecolonies")) {
            ctx.getSource().sendFailure(Component.literal("MineColonies is not loaded."));
            return 0;
        }
        IColony colony = findNearestColony(player);
        if (colony == null) {
            ctx.getSource().sendFailure(Component.literal("No colony found. You must be near or own a colony."));
            return 0;
        }
        PlannerPanelPayload payload = PlannerPanelPayload.build(colony, null);
        PacketDistributor.sendToPlayer(player, new OpenPlannerPanelPacket(payload));
        return 1;
    }

    // Prefers the player's own colony, then checks if the player is inside any colony,
    // then falls back to the first colony in the level if present.
    // A proper nearest-by-distance lookup is not possible without a verified colony position API.
    private static IColony findNearestColony(ServerPlayer player) {
        ServerLevel level = player.serverLevel();
        IColony colony = IMinecoloniesAPI.getInstance().getColonyManager()
                .getIColonyByOwner(level, player);
        if (colony != null) return colony;
        List<IColony> all = IMinecoloniesAPI.getInstance().getColonyManager().getColonies(level);
        for (IColony c : all) {
            if (c.isCoordInColony(level, player.blockPosition())) return c;
        }
        return all.isEmpty() ? null : all.get(0);
    }
}
