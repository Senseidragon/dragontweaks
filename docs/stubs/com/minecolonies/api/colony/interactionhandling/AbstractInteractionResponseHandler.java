Compiled from "AbstractInteractionResponseHandler.java"
public abstract class com.minecolonies.api.colony.interactionhandling.AbstractInteractionResponseHandler implements com.minecolonies.api.colony.interactionhandling.IInteractionResponseHandler {
  public com.minecolonies.api.colony.interactionhandling.AbstractInteractionResponseHandler(net.minecraft.network.chat.Component, boolean, com.minecolonies.api.colony.interactionhandling.IChatPriority, com.minecolonies.api.util.Tuple<net.minecraft.network.chat.Component, net.minecraft.network.chat.Component>...);
  public com.minecolonies.api.colony.interactionhandling.AbstractInteractionResponseHandler();
  public net.minecraft.network.chat.Component getInquiry();
  public net.minecraft.network.chat.Component getResponseResult(net.minecraft.network.chat.Component);
  public java.util.List<net.minecraft.network.chat.Component> getPossibleResponses();
  public net.minecraft.nbt.CompoundTag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public boolean isPrimary();
  public com.minecolonies.api.colony.interactionhandling.IChatPriority getPriority();
  public boolean isVisible(net.minecraft.world.level.Level);
  public boolean isValid(com.minecolonies.api.colony.ICitizenData);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
  public net.minecraft.nbt.Tag serializeNBT(net.minecraft.core.HolderLookup$Provider);
}
