Compiled from "AbstractCivilianEntity.java"
public abstract class com.minecolonies.api.entity.citizen.AbstractCivilianEntity extends com.minecolonies.api.entity.other.AbstractFastMinecoloniesEntity implements net.minecraft.world.entity.npc.Npc {
  public abstract void setCivilianData(com.minecolonies.api.colony.ICivilianData);
  public abstract com.minecolonies.api.colony.ICivilianData getCivilianData();
  public abstract void markDirty(int);
  public abstract int getCivilianID();
  public abstract void setCitizenId(int);
  public boolean checkBedExists();
  public void push(net.minecraft.world.entity.Entity);
  public void onPlayerCollide(net.minecraft.world.entity.player.Player);
  public abstract void queueSound(net.minecraft.sounds.SoundEvent, net.minecraft.core.BlockPos, int, int);
  public abstract void queueSound(net.minecraft.sounds.SoundEvent, net.minecraft.core.BlockPos, int, int, float, float);
  public java.lang.String toString();
  public boolean startRiding(net.minecraft.world.entity.Entity, boolean);
}
