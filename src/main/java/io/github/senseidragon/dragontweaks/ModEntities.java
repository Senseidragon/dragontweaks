package io.github.senseidragon.dragontweaks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, DragonTweaks.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<AssistantEntity>> ASSISTANT =
            ENTITY_TYPES.register("assistant", () ->
                    EntityType.Builder.<AssistantEntity>of(AssistantEntity::new, MobCategory.MISC)
                            .sized(0.6f, 1.95f)
                            .clientTrackingRange(10)
                            .build(ResourceLocation.fromNamespaceAndPath(DragonTweaks.MODID, "assistant").toString())
            );

    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ASSISTANT.get(), AssistantEntity.createAttributes());
    }
}
