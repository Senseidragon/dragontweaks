package io.github.senseidragon.dragontweaks;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(value = DragonTweaks.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = DragonTweaks.MODID, value = Dist.CLIENT)
public class DragonTweaksClient {
    public DragonTweaksClient(IEventBus modEventBus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener(DragonTweaksClientEvents::onRegisterRenderers);
        modEventBus.addListener(this::registerPackets);
    }

    private void registerPackets(RegisterPayloadHandlersEvent event) {
        event.registrar(DragonTweaks.MODID)
                .playToClient(OpenAdvisorPanelPacket.TYPE, OpenAdvisorPanelPacket.STREAM_CODEC,
                        ClientPanelHandler::handleAdvisorPanel)
                .playToClient(OpenPlannerPanelPacket.TYPE, OpenPlannerPanelPacket.STREAM_CODEC,
                        ClientPanelHandler::handlePlannerPanel)
                .playToClient(RoleAssignmentPayload.TYPE, RoleAssignmentPayload.STREAM_CODEC,
                        (packet, ctx) -> ctx.enqueueWork(() ->
                                Minecraft.getInstance().setScreen(new RoleAssignmentScreen(packet))));
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        DragonTweaks.LOGGER.debug("DragonTweaks client setup complete");
    }
}
