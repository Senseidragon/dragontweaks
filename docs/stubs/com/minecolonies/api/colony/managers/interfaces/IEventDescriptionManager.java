Compiled from "IEventDescriptionManager.java"
public interface com.minecolonies.api.colony.managers.interfaces.IEventDescriptionManager extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract void addEventDescription(com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription);
  public abstract java.util.List<com.minecolonies.api.colony.colonyEvents.descriptions.IColonyEventDescription> getEventDescriptions();
}
