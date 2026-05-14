package io.github.senseidragon.dragontweaks;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class CitizenConversationMemory extends SavedData {

    public static final String NAME = "dragontweaks_citizen_memory";
    private static final int MAX_HISTORY = 20;

    private final Map<String, Deque<String>> history = new HashMap<>();

    public CitizenConversationMemory() {}

    public static CitizenConversationMemory get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
            new SavedData.Factory<>(CitizenConversationMemory::new, CitizenConversationMemory::load),
            NAME
        );
    }

    private static String key(int colonyId, int citizenId) {
        return colonyId + ":" + citizenId;
    }

    public Deque<String> getHistory(int colonyId, int citizenId) {
        return history.getOrDefault(key(colonyId, citizenId), new ArrayDeque<>());
    }

    public void appendHistory(int colonyId, int citizenId, String role, String content) {
        String k = key(colonyId, citizenId);
        Deque<String> deque = history.computeIfAbsent(k, x -> new ArrayDeque<>());
        deque.addLast("[" + role + "]: " + content);
        while (deque.size() > MAX_HISTORY) deque.pollFirst();
        setDirty();
    }

    public void clearHistory(int colonyId, int citizenId) {
        if (history.remove(key(colonyId, citizenId)) != null) setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag list = new ListTag();
        for (Map.Entry<String, Deque<String>> entry : history.entrySet()) {
            CompoundTag ct = new CompoundTag();
            ct.putString("key", entry.getKey());
            ListTag lines = new ListTag();
            for (String line : entry.getValue()) lines.add(StringTag.valueOf(line));
            ct.put("lines", lines);
            list.add(ct);
        }
        tag.put("entries", list);
        return tag;
    }

    public static CitizenConversationMemory load(CompoundTag tag, HolderLookup.Provider provider) {
        CitizenConversationMemory data = new CitizenConversationMemory();
        ListTag list = tag.getList("entries", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag ct = list.getCompound(i);
            String k = ct.getString("key");
            ListTag lines = ct.getList("lines", Tag.TAG_STRING);
            Deque<String> deque = new ArrayDeque<>();
            for (int j = 0; j < lines.size(); j++) deque.addLast(lines.getString(j));
            data.history.put(k, deque);
        }
        return data;
    }
}
