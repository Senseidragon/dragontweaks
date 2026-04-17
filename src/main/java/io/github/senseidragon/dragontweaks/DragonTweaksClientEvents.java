package io.github.senseidragon.dragontweaks;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class DragonTweaksClientEvents {

    static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ASSISTANT.get(), AssistantRenderer::new);
    }
}
