Compiled from "IColonyEventDescription.java"
public interface com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract net.minecraft.resources.ResourceLocation getEventTypeId();
  public abstract java.lang.String getName();
  public default java.lang.String toDisplayString();
  public abstract net.minecraft.core.BlockPos getEventPos();
  public abstract void setEventPos(net.minecraft.core.BlockPos);
  public abstract void serialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
}
