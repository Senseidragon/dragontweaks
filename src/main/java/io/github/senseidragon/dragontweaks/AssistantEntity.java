package io.github.senseidragon.dragontweaks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AssistantEntity extends PathfinderMob {

    private String role = "villager";
    private boolean following = false;
    private final Map<UUID, Long> greetingCooldowns = new HashMap<>();

    public AssistantEntity(EntityType<? extends AssistantEntity> type, Level level) {
        super(type, level);
        this.setCustomName(Component.literal("Assistant"));
        this.setCustomNameVisible(true);
        this.setPersistenceRequired();
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isFollowing() { return following; }
    public void setFollowing(boolean following) {
        this.following = following;
        if (!following) this.getNavigation().stop();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("AssistantRole", role);
        tag.putBoolean("AssistantFollowing", following);

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
        // Intentionally always false on load — NPCs start stationary
        following = false;

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

    public void tryGreetPlayer(ServerPlayer player, ServerLevel level) {
        UUID playerUUID = player.getUUID();
        if (level.getGameTime() < greetingCooldowns.getOrDefault(playerUUID, 0L)) return;
        if (level.random.nextDouble() >= Config.FLAVOR_NPC_GREETING_CHANCE.get()) return;

        greetingCooldowns.put(playerUUID, level.getGameTime() + Config.FLAVOR_NPC_GREETING_COOLDOWN_TICKS.get());

        String timeOfDay = LLMClient.timeOfDay(level.getDayTime());
        String weather = LLMClient.weather(level.isRaining(), level.isThundering());
        String surroundings = LLMClient.scanSurroundings(level, this);
        Component nameComponent = this.getCustomName() != null ? this.getCustomName() : Component.literal("Assistant");
        String playerName = player.getGameProfile().getName();
        String greetingPrompt = playerName + " has just come within earshot. Greet them in character, considering it is " + timeOfDay + " and the weather is " + weather + ".";

        LLMClient.observe(level.getServer(), player, nameComponent, role,
                timeOfDay, weather, surroundings, greetingPrompt, this.getUUID());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FollowPlayerGoal(this, 1.0, 5.0F, 3.0F));
        this.goalSelector.addGoal(7, new MoveTowardsRestrictionGoal(this, 0.6));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class,
                Config.COMMAND_PROXIMITY.get().floatValue(), 0.02f));
    }

    public void setHomePosition(BlockPos pos) {
        this.restrictTo(pos, Config.FLAVOR_NPC_WANDER_RADIUS.get());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        this.setHomePosition(this.blockPosition());
        return data;
    }

    public static AttributeSupplier createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .build();
    }
}
