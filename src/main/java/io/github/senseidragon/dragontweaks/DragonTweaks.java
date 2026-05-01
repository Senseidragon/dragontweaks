package io.github.senseidragon.dragontweaks;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

@Mod(DragonTweaks.MODID)
public class DragonTweaks {
    public static final String MODID = "dragontweaks";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DragonTweaks(IEventBus modEventBus, ModContainer modContainer) {
        ModEntities.ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(Config::onLoad);
        modEventBus.addListener(ModEntities::onAttributeCreate);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.addListener((LevelEvent.Load e) -> {
            if (e.getLevel() instanceof ServerLevel serverLevel
                    && serverLevel.dimension() == Level.OVERWORLD) {
                RoleAssignmentData.get(serverLevel);
            }
        });
        NeoForge.EVENT_BUS.addListener(ChatInterceptor::onServerChat);
        NeoForge.EVENT_BUS.addListener(ObservationTicker::onServerTick);
        NeoForge.EVENT_BUS.addListener((ServerStoppingEvent e) -> LLMClient.shutdown());
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("DragonTweaks loaded — LLM endpoint: {}", Config.LLM_ENDPOINT.get());
    }
}
