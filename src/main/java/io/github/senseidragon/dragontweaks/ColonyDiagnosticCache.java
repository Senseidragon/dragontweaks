package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.IColony;

import java.util.concurrent.ConcurrentHashMap;

public class ColonyDiagnosticCache {

    private static final long TTL_MS = 30_000L;

    private static final ConcurrentHashMap<Integer, CacheEntry> cache = new ConcurrentHashMap<>();

    private static class CacheEntry {
        final ColonyDiagnosticReport report;
        final long timestamp;

        CacheEntry(ColonyDiagnosticReport report) {
            this.report = report;
            this.timestamp = System.currentTimeMillis();
        }

        boolean isStale() {
            return System.currentTimeMillis() - timestamp > TTL_MS;
        }
    }

    public static ColonyDiagnosticReport getOrGenerate(IColony colony) {
        int colonyId = colony.getID();
        CacheEntry entry = cache.get(colonyId);
        if (entry != null && !entry.isStale()) {
            return entry.report;
        }
        ColonyDiagnosticReport report = ColonyDiagnosticReportGenerator.generate(colony);
        cache.put(colonyId, new CacheEntry(report));
        return report;
    }

    public static void invalidate(int colonyId) {
        cache.remove(colonyId);
    }
}
