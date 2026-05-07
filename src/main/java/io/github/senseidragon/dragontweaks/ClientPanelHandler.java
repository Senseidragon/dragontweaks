package io.github.senseidragon.dragontweaks;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

@OnlyIn(Dist.CLIENT)
public class ClientPanelHandler {

    public static void handleAdvisorPanel(OpenAdvisorPanelPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() ->
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        new AdvisorPanelScreen(packet.payload())));
    }

    public static void handlePlannerPanel(OpenPlannerPanelPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() ->
                net.minecraft.client.Minecraft.getInstance().setScreen(
                        new PlannerPanelScreen(packet.payload(), null)));
    }
}
