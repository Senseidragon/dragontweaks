package io.github.senseidragon.dragontweaks;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class AssistantEntity extends PathfinderMob {

    private String role = "villager";

    public AssistantEntity(EntityType<? extends AssistantEntity> type, Level level) {
        super(type, level);
        this.setCustomName(Component.literal("Assistant"));
        this.setCustomNameVisible(true);
        this.setPersistenceRequired();
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("AssistantRole", role);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("AssistantRole")) {
            role = tag.getString("AssistantRole");
        }
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
