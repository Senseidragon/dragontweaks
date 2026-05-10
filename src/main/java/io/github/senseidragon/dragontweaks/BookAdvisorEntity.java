package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.IColonyManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class BookAdvisorEntity extends Entity {

    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID =
            SynchedEntityData.defineId(BookAdvisorEntity.class, EntityDataSerializers.OPTIONAL_UUID);

    public BookAdvisorEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.setGlowingTag(true);
        this.noCulling = true;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distanceSq) {
        return true;
    }

    public void setOwner(Player player) {
        this.entityData.set(OWNER_UUID, Optional.of(player.getUUID()));
        DragonTweaks.LOGGER.info("BookAdvisorEntity ownerUUID set to {}", player.getUUID());
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.entityData.get(OWNER_UUID).orElse(null);
    }

    @Override
    public void tick() {
        super.tick();
        UUID ownerUUID = getOwnerUUID();
        if (level().isClientSide() || ownerUUID == null) return;

        ServerLevel serverLevel = (ServerLevel) level();
        Player player = level().getPlayerByUUID(ownerUUID);
        if (player == null) return;

        ServerLevel overworld = serverLevel.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) return;
        AdvisorState state = AdvisorStateData.get(overworld).getState(ownerUUID);

        if ((state == AdvisorState.COLONY_NO_CITIZEN || state == AdvisorState.COLONY_WITH_CITIZEN)
                && ModList.get().isLoaded("minecolonies")) {
            IColony colony = IColonyManager.getInstance().getIColony(level(), this.blockPosition());
            if (colony == null) {
                followPlayer(player);
                return;
            }

            if (colony.isCoordInColony(level(), player.blockPosition())) {
                followPlayer(player);
            } else {
                double dx = player.getX() - this.getX();
                double dy = player.getY() - this.getY();
                double dz = player.getZ() - this.getZ();
                double distSq = dx * dx + dy * dy + dz * dz;
                int range = Config.ADVISOR_BOUNDARY_DETECTION_RANGE.get();
                if (distSq > (double) (range * range)) {
                    if (colony.getServerBuildingManager().hasTownHall()) {
                        BlockPos thPos = colony.getServerBuildingManager().getTownHall().getPosition();
                        this.teleportTo(thPos.getX() + 0.5, thPos.getY() + 1.0, thPos.getZ() + 0.5);
                    }
                }
                // else: player within detection range but outside colony — hold position, no setPos call
            }
        } else {
            followPlayer(player);
        }
    }

    private void followPlayer(Player player) {
        float yawRad = (float) Math.toRadians(player.getYRot());
        double fwdX  = -Math.sin(yawRad);
        double fwdZ  =  Math.cos(yawRad);
        double leftX =  Math.cos(yawRad);
        double leftZ =  Math.sin(yawRad);
        this.setPos(
            player.getX() + fwdX * 1.5 + leftX * 1.2,
            player.getY() + 1.4,
            player.getZ() + fwdZ * 1.5 + leftZ * 1.2
        );
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity serverEntity) {
        return new ClientboundAddEntityPacket(this, serverEntity);
    }

    // --- Synced data ---

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(OWNER_UUID, Optional.empty());
    }

    // --- NBT ---

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        UUID ownerUUID = getOwnerUUID();
        if (ownerUUID != null) {
            tag.putString("OwnerUUID", ownerUUID.toString());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("OwnerUUID")) {
            UUID ownerUUID = UUID.fromString(tag.getString("OwnerUUID"));
            this.entityData.set(OWNER_UUID, Optional.of(ownerUUID));
            DragonTweaks.LOGGER.info("BookAdvisorEntity loaded, ownerUUID={}", ownerUUID);
        }
    }
}
