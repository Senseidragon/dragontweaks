package io.github.senseidragon.dragontweaks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Static utility for loading key-value pairs from a {@code .env} file located
 * in the JVM working directory ({@code System.getProperty("user.dir")}).
 *
 * <p>The file is parsed once per JVM session and cached. Lines starting with
 * {@code #} and blank lines are ignored. Each remaining line must have the form
 * {@code KEY=VALUE}; whitespace around both the key and value is trimmed.
 *
 * <p>Values are never written to the log — only the presence or absence of the
 * file is logged.
 */
public final class EnvLoader {

    /** Cached entries; {@code null} means the file has not been loaded yet. */
    private static volatile Map<String, String> cache = null;

    private EnvLoader() {
        // utility class — no instances
    }

    /**
     * Returns the value associated with {@code key}, or {@code null} if the
     * {@code .env} file was not found or the key is absent.
     *
     * @param key the environment-variable key to look up
     * @return the string value, or {@code null}
     */
    public static String get(String key) {
        return loaded().get(key);
    }

    // -------------------------------------------------------------------------
    // Internal helpers
    // -------------------------------------------------------------------------

    /**
     * Returns the (possibly freshly parsed) cache map. Thread-safe via
     * double-checked locking on the volatile {@link #cache} field.
     */
    private static Map<String, String> loaded() {
        if (cache == null) {
            synchronized (EnvLoader.class) {
                if (cache == null) {
                    cache = loadFile();
                }
            }
        }
        return cache;
    }

    /**
     * Reads and parses the {@code .env} file. Returns an empty, unmodifiable
     * map if the file does not exist or cannot be read.
     */
    private static Map<String, String> loadFile() {
        Path envPath = Path.of(System.getProperty("user.dir"), ".env");

        if (!Files.exists(envPath)) {
            DragonTweaks.LOGGER.info("[EnvLoader] No .env file found at {}", envPath);
            return Collections.emptyMap();
        }

        DragonTweaks.LOGGER.info("[EnvLoader] Loading .env file from {}", envPath);

        try {
            Map<String, String> entries = new HashMap<>();
            for (String rawLine : Files.readAllLines(envPath)) {
                String line = rawLine.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                int eq = line.indexOf('=');
                if (eq < 1) {
                    // no '=' or '=' is the very first character — skip
                    continue;
                }
                String k = line.substring(0, eq).trim();
                String v = line.substring(eq + 1).trim();
                entries.put(k, v);
            }
            return Collections.unmodifiableMap(entries);
        } catch (IOException e) {
            DragonTweaks.LOGGER.error("[EnvLoader] Failed to read .env file: {}", e.getMessage());
            return Collections.emptyMap();
        }
    }
}
