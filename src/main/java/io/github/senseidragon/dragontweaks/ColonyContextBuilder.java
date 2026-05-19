package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.IVisitorData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;

public class ColonyContextBuilder {

    /**
     * Builds a plaintext roster of known colony members for LLM context injection.
     * Includes: citizens (name, job, work location, mod role if assigned) and
     * flavor NPCs (AssistantEntity) within colony bounds.
     */
    public static String buildRoster(IColony colony, ServerLevel overworld,
                                     ServerLevel level, ServerPlayer player) {
        if (colony == null) return "";

        RoleAssignmentData roleData = RoleAssignmentData.get(overworld);
        StringBuilder sb = new StringBuilder();

        for (ICitizenData citizen : colony.getCitizenManager().getCitizens()) {
            if (citizen instanceof IVisitorData) continue;

            String displayName = NicknameData.resolve(
                    overworld, colony.getID(), citizen.getId(), citizen.getName());

            String jobDesc = "unemployed colonist";
            var job = citizen.getJob();
            if (job != null) {
                String jn = job.getNameTagDescription();
                if (jn != null && !jn.isBlank()) jobDesc = jn;
            }

            String workLocation = "";
            var workBuilding = citizen.getWorkBuilding();
            if (workBuilding != null) {
                var pos = workBuilding.getPosition();
                int dx = pos.getX() - player.getBlockX();
                int dz = pos.getZ() - player.getBlockZ();
                workLocation = " (last seen to the " + compassDir(dx, dz) + ")";
            }

            AssistantRoleRecord record = roleData.getRecord(colony.getID(), citizen.getId());
            String modRole = record != null ? " [" + record.roleType() + "]" : "";

            sb.append("- ").append(displayName).append(", ").append(jobDesc)
              .append(workLocation).append(modRole).append("\n");
        }

        // Flavor NPCs (our mod's AssistantEntity) within colony bounds
        AABB scanBox = AABB.ofSize(player.position(), 400, 128, 400);
        for (AssistantEntity npc : level.getEntitiesOfClass(AssistantEntity.class, scanBox)) {
            if (!colony.isCoordInColony(level, npc.blockPosition())) continue;
            String npcName = npc.getCustomName() != null
                    ? npc.getCustomName().getString() : "a companion";
            String npcRole = (npc.getRole() != null && !npc.getRole().isEmpty())
                    ? npc.getRole() : "companion";
            sb.append("- ").append(npcName)
              .append(" [companion NPC, ").append(npcRole).append("]\n");
        }

        return sb.toString().trim();
    }

    private static String compassDir(int dx, int dz) {
        double angle = Math.toDegrees(Math.atan2(dz, dx));
        if (angle < 0) angle += 360;
        if (angle < 22.5 || angle >= 337.5) return "east";
        if (angle < 67.5)  return "southeast";
        if (angle < 112.5) return "south";
        if (angle < 157.5) return "southwest";
        if (angle < 202.5) return "west";
        if (angle < 247.5) return "northwest";
        if (angle < 292.5) return "north";
        return "northeast";
    }
}
