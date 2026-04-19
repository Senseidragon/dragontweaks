package io.github.senseidragon.dragontweaks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConversationMemory {

    private static final int MAX_EXCHANGES = 10;
    private static final Map<UUID, Map<String, Deque<String>>> MEMORY = new HashMap<>();

    public static void addExchange(UUID npcId, String playerName, String playerLine, String npcLine) {
        Map<String, Deque<String>> npcMemory = MEMORY.computeIfAbsent(npcId, k -> new HashMap<>());
        Deque<String> history = npcMemory.computeIfAbsent(playerName.toLowerCase(), k -> new ArrayDeque<>());
        history.addLast(playerLine);
        history.addLast(npcLine);
        while (history.size() > MAX_EXCHANGES * 2) {
            history.pollFirst();
            history.pollFirst();
        }
    }

    public static String getHistory(UUID npcId, String playerName) {
        Map<String, Deque<String>> npcMemory = MEMORY.get(npcId);
        if (npcMemory == null) return "";
        Deque<String> history = npcMemory.get(playerName.toLowerCase());
        if (history == null || history.isEmpty()) return "";
        return String.join("\n", history);
    }

    public static void clear(UUID npcId, String playerName) {
        Map<String, Deque<String>> npcMemory = MEMORY.get(npcId);
        if (npcMemory != null) npcMemory.remove(playerName.toLowerCase());
    }

    public static void clearAll(UUID npcId) {
        MEMORY.remove(npcId);
    }
}
