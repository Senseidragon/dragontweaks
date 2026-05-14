package io.github.senseidragon.dragontweaks;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RoleAssignmentData extends SavedData {

    public static final String NAME = "dragontweaks_roles";

    private final Map<String, AssistantRoleRecord> assignments = new HashMap<>();

    private static String key(int colonyId, int citizenId) {
        return colonyId + ":" + citizenId;
    }

    public RoleAssignmentData() {}

    // --- Factory ---

    public static RoleAssignmentData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
            new SavedData.Factory<>(RoleAssignmentData::new, RoleAssignmentData::load),
            NAME
        );
    }

    // --- Public API ---

    public boolean assign(int colonyId, int citizenId, String roleType, UUID playerUUID) {
        assignments.put(key(colonyId, citizenId), new AssistantRoleRecord(citizenId, roleType, System.currentTimeMillis(), playerUUID, null));
        setDirty();
        return true;
    }

    public void revoke(int colonyId, int citizenId) {
        assignments.remove(key(colonyId, citizenId));
        setDirty();
    }

    public AssistantRoleRecord getRecord(int colonyId, int citizenId) {
        return assignments.get(key(colonyId, citizenId));
    }

    public boolean isAssigned(int colonyId, int citizenId) {
        return assignments.containsKey(key(colonyId, citizenId));
    }

    public Iterable<AssistantRoleRecord> getAssignments() {
        return assignments.values();
    }

    public int getAssignedCount(UUID playerUUID) {
        int count = 0;
        for (AssistantRoleRecord record : assignments.values()) {
            if (record.playerUUID().equals(playerUUID)) count++;
        }
        return count;
    }

    // --- Serialization ---

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag list = new ListTag();
        for (Map.Entry<String, AssistantRoleRecord> entry : assignments.entrySet()) {
            AssistantRoleRecord record = entry.getValue();
            CompoundTag recordTag = new CompoundTag();
            recordTag.putString("key", entry.getKey());
            recordTag.putInt("citizenId", record.citizenId());
            recordTag.putString("roleType", record.roleType());
            recordTag.putString("playerUUID", record.playerUUID().toString());
            recordTag.putLong("timestamp", record.assignmentTimestamp());
            list.add(recordTag);
        }
        tag.put("assignments", list);
        return tag;
    }

    public static RoleAssignmentData load(CompoundTag tag, HolderLookup.Provider provider) {
        RoleAssignmentData data = new RoleAssignmentData();
        ListTag list = tag.getList("assignments", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag recordTag = list.getCompound(i);
            String entryKey = recordTag.getString("key");
            int citizenId = recordTag.getInt("citizenId");
            String roleType = recordTag.getString("roleType");
            UUID playerUUID = UUID.fromString(recordTag.getString("playerUUID"));
            long timestamp = recordTag.getLong("timestamp");
            data.assignments.put(entryKey, new AssistantRoleRecord(citizenId, roleType, timestamp, playerUUID, null));
        }
        return data;
    }
}
