package io.github.senseidragon.dragontweaks;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class AssistantEntity extends PathfinderMob {

    public AssistantEntity(EntityType<? extends AssistantEntity> type, Level level) {
        super(type, level);
        this.setCustomName(Component.literal("Assistant [PoC]"));
        this.setCustomNameVisible(true);
        this.setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        // Intentionally empty — no wandering, no combat, no targeting
    }

    public static AttributeSupplier createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .build();
    }
}
