Compiled from "IInteractionResponseHandlerDataManager.java"
public interface com.minecolonies.api.colony.interactionhandling.registry.IInteractionResponseHandlerDataManager {
  public static com.minecolonies.api.colony.interactionhandling.registry.IInteractionResponseHandlerDataManager getInstance();
  public abstract com.minecolonies.api.colony.interactionhandling.IInteractionResponseHandler createFrom(net.minecraft.core.HolderLookup$Provider, com.minecolonies.api.colony.ICitizen, net.minecraft.nbt.CompoundTag);
}
