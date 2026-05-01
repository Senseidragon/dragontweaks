package io.github.senseidragon.dragontweaks;

import java.util.UUID;

/**
 * Represents a single role assignment for a MineColonies citizen.
 *
 * @param roleType The assigned role (e.g., "ranch hand", "scout").
 * @param assignmentTimestamp The epoch millisecond timestamp when the role was assigned.
 * @param playerUUID The UUID of the player who assigned the role.
 * @param shadowEntityUUID The UUID of the associated shadow entity, if applicable.
 */
public record AssistantRoleRecord(
    int citizenId,
    String roleType,
    long assignmentTimestamp,
    UUID playerUUID,
    UUID shadowEntityUUID
) {}
