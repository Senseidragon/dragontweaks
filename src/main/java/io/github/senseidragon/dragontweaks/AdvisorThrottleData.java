package io.github.senseidragon.dragontweaks;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashSet;
import java.util.Set;

public class AdvisorThrottleData extends SavedData {

    public static final String NAME = "dragontweaks_advisor_throttle";

    private final Set<String> firedKeys = new HashSet<>();

    public AdvisorThrottleData() {}

    public static AdvisorThrottleData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
            new SavedData.Factory<>(AdvisorThrottleData::new, AdvisorThrottleData::load),
            NAME
        );
    }

    public boolean hasFired(String key) {
        return firedKeys.contains(key);
    }

    public void markFired(String key) {
        firedKeys.add(key);
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag list = new ListTag();
        for (String key : firedKeys) {
            list.add(StringTag.valueOf(key));
        }
        tag.put("firedKeys", list);
        return tag;
    }

    public static AdvisorThrottleData load(CompoundTag tag, HolderLookup.Provider provider) {
        AdvisorThrottleData data = new AdvisorThrottleData();
        ListTag list = tag.getList("firedKeys", Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            data.firedKeys.add(list.getString(i));
        }
        return data;
    }
}
