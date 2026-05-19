package io.github.senseidragon.dragontweaks;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashSet;
import java.util.Set;

public class CitizenAcknowledgmentData extends SavedData {

    private static final String SAVE_KEY = "dragontweaks_citizen_acknowledgments";
    private final Set<String> acknowledged = new HashSet<>();

    public static CitizenAcknowledgmentData get(ServerLevel overworld) {
        return overworld.getDataStorage().computeIfAbsent(
                new SavedData.Factory<CitizenAcknowledgmentData>(CitizenAcknowledgmentData::new, CitizenAcknowledgmentData::load),
                SAVE_KEY);
    }

    private static CitizenAcknowledgmentData load(CompoundTag tag, HolderLookup.Provider provider) {
        CitizenAcknowledgmentData data = new CitizenAcknowledgmentData();
        ListTag list = tag.getList("acknowledged", 8);
        for (int i = 0; i < list.size(); i++) {
            data.acknowledged.add(list.getString(i));
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag list = new ListTag();
        for (String key : acknowledged) {
            list.add(StringTag.valueOf(key));
        }
        tag.put("acknowledged", list);
        return tag;
    }

    public void acknowledge(int colonyId, int citizenId) {
        acknowledged.add(colonyId + ":" + citizenId);
        setDirty();
    }

    public boolean isAcknowledged(int colonyId, int citizenId) {
        return acknowledged.contains(colonyId + ":" + citizenId);
    }

    public void remove(int colonyId, int citizenId) {
        if (acknowledged.remove(colonyId + ":" + citizenId)) setDirty();
    }
}
