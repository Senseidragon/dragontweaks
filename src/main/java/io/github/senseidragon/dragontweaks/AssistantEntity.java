package io.github.senseidragon.dragontweaks;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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

        Map<String, Deque<String>> allHistory = ConversationMemory.getAll(this.getUUID());
        if (!allHistory.isEmpty()) {
            CompoundTag historyTag = new CompoundTag();
            for (Map.Entry<String, Deque<String>> entry : allHistory.entrySet()) {
                ListTag playerHistory = new ListTag();
                for (String line : entry.getValue()) {
                    playerHistory.add(StringTag.valueOf(line));
                }
                historyTag.put(entry.getKey(), playerHistory);
            }
            tag.put("dragontweaks:conversation_history", historyTag);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("AssistantRole")) {
            role = tag.getString("AssistantRole");
        }

        if (tag.contains("dragontweaks:conversation_history", Tag.TAG_COMPOUND)) {
            CompoundTag historyTag = tag.getCompound("dragontweaks:conversation_history");
            Map<String, Deque<String>> restored = new HashMap<>();
            for (String playerName : historyTag.getAllKeys()) {
                ListTag playerHistory = historyTag.getList(playerName, Tag.TAG_STRING);
                Deque<String> lines = new ArrayDeque<>();
                for (int i = 0; i < playerHistory.size(); i++) {
                    lines.addLast(playerHistory.getString(i));
                }
                restored.put(playerName, lines);
            }
            ConversationMemory.restoreAll(this.getUUID(), restored);
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
