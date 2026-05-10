package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.buildings.workerbuildings.ITownHall;
import com.minecolonies.api.colony.jobs.IJob;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class CitizenInteractDetector {

    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide()) return;
        if (!ModList.get().isLoaded("minecolonies")) return;

        Entity target = event.getTarget();
        if (!(target instanceof AbstractEntityCitizen citizen)) return;

        ICitizenData citizenData = citizen.getCitizenData();
        if (citizenData == null) return;

        String name = citizenData.getName();
        int citizenId = citizenData.getId();
        IJob<?> job = citizenData.getJob();
        String jobName = (job == null || job.getNameTagDescription().isBlank())
                ? "unemployed" : job.getNameTagDescription();

        ServerLevel serverLevel = (ServerLevel) event.getLevel();
        Player player = event.getEntity();

        RoleAssignmentData roleData = RoleAssignmentData.get(serverLevel);
        if (roleData.isAssigned(citizenId)) return;

        ITownHall townHall = citizenData.getColony().getServerBuildingManager().getTownHall();
        int thLevel = (townHall != null) ? townHall.getBuildingLevel() : 1;
        int maxSlots = thLevel < 3 ? 3 : Math.min(thLevel + 1, 6);

        if (roleData.getAssignedCount(player.getUUID()) >= maxSlots) {
            player.sendSystemMessage(Component.literal(
                    "No role slots available. Upgrade your Town Hall to unlock more."));
            return;
        }

        event.setCanceled(true);
        DragonTweaks.LOGGER.debug("[DragonTweaks] Clicked citizen: {}, Job: {}, ID: {}", name, jobName, citizenId);
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        int slotsUsed = roleData.getAssignedCount(player.getUUID());
        PacketDistributor.sendToPlayer(serverPlayer, new RoleAssignmentPayload(
                name, citizenId, slotsUsed, maxSlots, RoleAssignmentPayload.AVAILABLE_ROLES));
    }
}
