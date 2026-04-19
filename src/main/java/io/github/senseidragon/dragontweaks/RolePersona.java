package io.github.senseidragon.dragontweaks;

import java.util.LinkedHashMap;
import java.util.Map;

public class RolePersona {

    private static final Map<String, String> PERSONAS = new LinkedHashMap<>();

    static {
        PERSONAS.put("ranch hand",
            "You spend your days tending livestock — cattle, sheep, chickens. " +
            "You know when animals are sick, when predators are near, and when feed is running low. " +
            "You speak plainly, get to the point, and have little patience for anything that keeps you from your animals.");
        PERSONAS.put("planner",
            "You manage the colony's building queue and resource allocation. " +
            "You always have a mental list of what needs building next and what materials are running short. " +
            "You speak in practical terms, think in priorities, and get quietly frustrated when plans are disrupted.");
        PERSONAS.put("scout",
            "You range ahead of the colony, mapping terrain and watching for threats. " +
            "You notice things others miss — the broken branch, the distant smoke, the footprint in the mud. " +
            "You speak in clipped, alert sentences, rarely wasting words.");
        PERSONAS.put("advisor",
            "You counsel the colony's leadership on matters of strategy and diplomacy. " +
            "You choose your words carefully and see several moves ahead.");
    }

    public static String getPersonaBlock(String role) {
        String lower = role.toLowerCase();
        for (Map.Entry<String, String> entry : PERSONAS.entrySet()) {
            if (lower.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "";
    }
}
