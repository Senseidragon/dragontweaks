Compiled from "ColonyEventTypeRegistryEntry.java"
public class com.minecolonies.api.colony.colonyEvents.registry.ColonyEventTypeRegistryEntry {
  public com.minecolonies.api.colony.colonyEvents.registry.ColonyEventTypeRegistryEntry(org.apache.commons.lang3.function.TriFunction<com.minecolonies.api.colony.IColony, net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.colonyEvents.IColonyEvent>, net.minecraft.resources.ResourceLocation);
  public com.minecolonies.api.colony.colonyEvents.registry.ColonyEventTypeRegistryEntry(org.apache.commons.lang3.function.TriFunction<com.minecolonies.api.colony.IColony, net.minecraft.nbt.CompoundTag, net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.colonyEvents.IColonyEvent>, net.minecraft.resources.ResourceLocation, boolean);
  public com.minecolonies.api.colony.colonyEvents.IColonyEvent deserializeEvent(com.minecolonies.api.colony.IColony, net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public net.minecraft.resources.ResourceLocation getRegistryName();
  public boolean isRaidEvent();
}
