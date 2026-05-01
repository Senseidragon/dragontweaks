Compiled from "ColonyEventDescriptionTypeRegistryEntry.java"
public class com.minecolonies.api.colony.colonyEvents.registry.ColonyEventDescriptionTypeRegistryEntry {
  public com.minecolonies.api.colony.colonyEvents.registry.ColonyEventDescriptionTypeRegistryEntry(java.util.function.BiFunction<net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag, com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription>, java.util.function.Function<net.minecraft.network.RegistryFriendlyByteBuf, com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription>, net.minecraft.resources.ResourceLocation);
  public com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription deserializeEventDescriptionFromNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription deserializeEventDescriptionFromFriendlyByteBuf(net.minecraft.network.RegistryFriendlyByteBuf);
  public net.minecraft.resources.ResourceLocation getRegistryName();
}
