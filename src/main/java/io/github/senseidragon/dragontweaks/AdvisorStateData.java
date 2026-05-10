package io.github.senseidragon.dragontweaks;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdvisorStateData extends SavedData {

    public static final String NAME = "dragontweaks_advisor_state";

    private final Map<UUID, PlayerAdvisorState> playerStates = new HashMap<>();

    public AdvisorStateData() {}

    public static AdvisorStateData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
            new SavedData.Factory<>(AdvisorStateData::new, AdvisorStateData::load),
            NAME
        );
    }

    // --- Per-player record ---

    public static class PlayerAdvisorState {
        public AdvisorState advisorState = AdvisorState.DORMANT;
        public boolean buildToolTriggerFired = false;
        @Nullable public Integer assignedCitizenId = null;
        @Nullable public UUID advisorEntityUUID = null;
    }

    // --- Public API ---

    public PlayerAdvisorState getOrCreate(UUID playerUUID) {
        return playerStates.computeIfAbsent(playerUUID, k -> new PlayerAdvisorState());
    }

    public void setState(UUID playerUUID, AdvisorState state) {
        getOrCreate(playerUUID).advisorState = state;
        setDirty();
    }

    public AdvisorState getState(UUID playerUUID) {
        return getOrCreate(playerUUID).advisorState;
    }

    public void setBuildToolTriggerFired(UUID playerUUID) {
        getOrCreate(playerUUID).buildToolTriggerFired = true;
        setDirty();
    }

    public boolean hasBuildToolTriggerFired(UUID playerUUID) {
        return getOrCreate(playerUUID).buildToolTriggerFired;
    }

    public void setAssignedCitizenId(UUID playerUUID, @Nullable Integer citizenId) {
        getOrCreate(playerUUID).assignedCitizenId = citizenId;
        setDirty();
    }

    @Nullable
    public Integer getAssignedCitizenId(UUID playerUUID) {
        return getOrCreate(playerUUID).assignedCitizenId;
    }

    public void setAdvisorEntityUUID(UUID playerUUID, @Nullable UUID entityUUID) {
        getOrCreate(playerUUID).advisorEntityUUID = entityUUID;
        setDirty();
    }

    @Nullable
    public UUID getAdvisorEntityUUID(UUID playerUUID) {
        return getOrCreate(playerUUID).advisorEntityUUID;
    }

    // --- Serialization ---

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag list = new ListTag();
        for (Map.Entry<UUID, PlayerAdvisorState> entry : playerStates.entrySet()) {
            PlayerAdvisorState state = entry.getValue();
            CompoundTag playerTag = new CompoundTag();
            playerTag.putString("playerUUID", entry.getKey().toString());
            playerTag.putString("advisorState", state.advisorState.name());
            playerTag.putBoolean("buildToolTriggerFired", state.buildToolTriggerFired);
            if (state.assignedCitizenId != null) {
                playerTag.putInt("assignedCitizenId", state.assignedCitizenId);
            }
            if (state.advisorEntityUUID != null) {
                playerTag.putString("advisorEntityUUID", state.advisorEntityUUID.toString());
            }
            list.add(playerTag);
        }
        tag.put("playerStates", list);
        return tag;
    }

    public static AdvisorStateData load(CompoundTag tag, HolderLookup.Provider provider) {
        AdvisorStateData data = new AdvisorStateData();
        ListTag list = tag.getList("playerStates", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag playerTag = list.getCompound(i);
            UUID playerUUID = UUID.fromString(playerTag.getString("playerUUID"));
            PlayerAdvisorState state = new PlayerAdvisorState();
            String stateName = playerTag.getString("advisorState");
            try {
                state.advisorState = AdvisorState.valueOf(stateName);
            } catch (IllegalArgumentException e) {
                state.advisorState = AdvisorState.DORMANT;
            }
            state.buildToolTriggerFired = playerTag.getBoolean("buildToolTriggerFired");
            if (playerTag.contains("assignedCitizenId")) {
                state.assignedCitizenId = playerTag.getInt("assignedCitizenId");
            }
            if (playerTag.contains("advisorEntityUUID")) {
                state.advisorEntityUUID = UUID.fromString(playerTag.getString("advisorEntityUUID"));
            }
            data.playerStates.put(playerUUID, state);
        }
        return data;
    }
}
