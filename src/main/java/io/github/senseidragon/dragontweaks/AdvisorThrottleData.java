package io.github.senseidragon.dragontweaks;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;

public class AdvisorThrottleData extends SavedData {

    public static final String NAME = "dragontweaks_advisor_throttle";

    private final Map<String, Integer> firedKeys = new HashMap<>();
    private final Map<String, Integer> suppressedKeys = new HashMap<>();

    public AdvisorThrottleData() {}

    public static AdvisorThrottleData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
            new SavedData.Factory<>(AdvisorThrottleData::new, AdvisorThrottleData::load),
            NAME
        );
    }

    public boolean hasFiredToday(String key, int colonyDay) {
        Integer day = firedKeys.get(key);
        return day != null && day == colonyDay;
    }

    public void markFiredToday(String key, int colonyDay) {
        firedKeys.put(key, colonyDay);
        firedKeys.entrySet().removeIf(e -> colonyDay - e.getValue() > 2);
        setDirty();
    }

    public int getSuppressedSinceDay(String key) {
        return suppressedKeys.getOrDefault(key, -1);
    }

    public void recordSuppression(String key, int colonyDay) {
        suppressedKeys.put(key, colonyDay);
        setDirty();
    }

    public void clearSuppression(String key) {
        suppressedKeys.remove(key);
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        CompoundTag fired = new CompoundTag();
        for (Map.Entry<String, Integer> entry : firedKeys.entrySet()) {
            fired.putInt(entry.getKey(), entry.getValue());
        }
        tag.put("firedKeys", fired);

        CompoundTag suppressed = new CompoundTag();
        for (Map.Entry<String, Integer> entry : suppressedKeys.entrySet()) {
            suppressed.putInt(entry.getKey(), entry.getValue());
        }
        tag.put("suppressedKeys", suppressed);
        return tag;
    }

    public static AdvisorThrottleData load(CompoundTag tag, HolderLookup.Provider provider) {
        // Old format stored firedKeys as a ListTag of strings — discard silently and start fresh
        if (tag.contains("firedKeys", Tag.TAG_LIST)) {
            return new AdvisorThrottleData();
        }

        AdvisorThrottleData data = new AdvisorThrottleData();

        if (tag.contains("firedKeys", Tag.TAG_COMPOUND)) {
            CompoundTag fired = tag.getCompound("firedKeys");
            for (String key : fired.getAllKeys()) {
                data.firedKeys.put(key, fired.getInt(key));
            }
        }

        if (tag.contains("suppressedKeys", Tag.TAG_COMPOUND)) {
            CompoundTag suppressed = tag.getCompound("suppressedKeys");
            for (String key : suppressed.getAllKeys()) {
                data.suppressedKeys.put(key, suppressed.getInt(key));
            }
        }

        return data;
    }
}
