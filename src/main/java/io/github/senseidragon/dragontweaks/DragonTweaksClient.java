package io.github.senseidragon.dragontweaks;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = DragonTweaks.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = DragonTweaks.MODID, value = Dist.CLIENT)
public class DragonTweaksClient {
    public DragonTweaksClient(IEventBus modEventBus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener(DragonTweaksClientEvents::onRegisterRenderers);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        DragonTweaks.LOGGER.debug("DragonTweaks client setup complete");
    }
}
