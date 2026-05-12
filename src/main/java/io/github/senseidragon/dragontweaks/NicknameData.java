package io.github.senseidragon.dragontweaks;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class NicknameData extends SavedData {

    public static final String NAME = "dragontweaks_nicknames";

    private final Map<String, String> nicknames = new HashMap<>();

    public NicknameData() {}

    public static NicknameData get(ServerLevel overworld) {
        return overworld.getDataStorage().computeIfAbsent(
            new SavedData.Factory<>(NicknameData::new, NicknameData::load),
            NAME
        );
    }

    private static String key(int colonyId, int citizenId) {
        return colonyId + ":" + citizenId;
    }

    public void setNickname(int colonyId, int citizenId, String nickname) {
        nicknames.put(key(colonyId, citizenId), nickname);
        setDirty();
    }

    @Nullable
    public String getNickname(int colonyId, int citizenId) {
        return nicknames.get(key(colonyId, citizenId));
    }

    public void removeNickname(int colonyId, int citizenId) {
        if (nicknames.remove(key(colonyId, citizenId)) != null) {
            setDirty();
        }
    }

    public static String resolve(ServerLevel overworld, int colonyId, int citizenId, String fallback) {
        String nick = NicknameData.get(overworld).getNickname(colonyId, citizenId);
        return nick != null ? nick : fallback;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        CompoundTag nicksTag = new CompoundTag();
        for (Map.Entry<String, String> entry : nicknames.entrySet()) {
            nicksTag.putString(entry.getKey(), entry.getValue());
        }
        tag.put("nicknames", nicksTag);
        return tag;
    }

    public static NicknameData load(CompoundTag tag, HolderLookup.Provider provider) {
        NicknameData data = new NicknameData();
        CompoundTag nicksTag = tag.getCompound("nicknames");
        for (String k : nicksTag.getAllKeys()) {
            data.nicknames.put(k, nicksTag.getString(k));
        }
        return data;
    }
}
