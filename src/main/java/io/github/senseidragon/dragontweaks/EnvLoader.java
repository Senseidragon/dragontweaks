package io.github.senseidragon.dragontweaks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static Map<String, String> loadFile() {
        Path envPath = Path.of(System.getProperty("user.dir"), ".env");

        DragonTweaks.LOGGER.info("Loading .env file from {}", envPath);

        try {
            return Files.readAllLines(envPath, StandardCharsets.UTF_8).stream()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .filter(line -> line.indexOf('=') >= 1)
                    .collect(Collectors.toUnmodifiableMap(
                            line -> line.substring(0, line.indexOf('=')).trim(),
                            line -> line.substring(line.indexOf('=') + 1).trim(),
                            (a, b) -> a));
        } catch (NoSuchFileException e) {
            DragonTweaks.LOGGER.info("No .env file found at {}", envPath);
            return Collections.emptyMap();
        } catch (IOException e) {
            DragonTweaks.LOGGER.error("Failed to read .env file: {}", e.getMessage());
            return Collections.emptyMap();
        }
    }
}
