Compiled from "IColonyEvent.java"
public interface com.minecolonies.api.colony.colonyEvents.IColonyEvent extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract com.minecolonies.api.colony.colonyEvents.EventStatus getStatus();
  public abstract void setStatus(com.minecolonies.api.colony.colonyEvents.EventStatus);
  public abstract int getID();
  public abstract net.minecraft.resources.ResourceLocation getEventTypeID();
  public abstract void setColony(com.minecolonies.api.colony.IColony);
  public default void onUpdate();
  public default void onStart();
  public default void onFinish();
  public default void onTileEntityBreak(net.minecraft.world.level.block.entity.BlockEntity);
  public default void onNightFall();
}
