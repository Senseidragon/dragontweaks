Compiled from "IInteractionResponseHandler.java"
public interface com.minecolonies.api.colony.interactionhandling.IInteractionResponseHandler extends net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract net.minecraft.network.chat.Component getInquiry();
  public default net.minecraft.network.chat.Component getInquiry(net.minecraft.world.entity.player.Player);
  public abstract java.util.List<net.minecraft.network.chat.Component> getPossibleResponses();
  public abstract net.minecraft.network.chat.Component getResponseResult(net.minecraft.network.chat.Component);
  public abstract boolean isPrimary();
  public abstract com.minecolonies.api.colony.interactionhandling.IChatPriority getPriority();
  public abstract boolean isVisible(net.minecraft.world.level.Level);
  public abstract boolean isValid(com.minecolonies.api.colony.ICitizenData);
  public abstract void onServerResponseTriggered(int, net.minecraft.world.entity.player.Player, com.minecolonies.api.colony.ICitizenData);
  public abstract boolean onClientResponseTriggered(int, net.minecraft.world.entity.player.Player, com.minecolonies.api.colony.ICitizenDataView, com.ldtteam.blockui.views.BOWindow);
  public abstract void removeParent(net.minecraft.network.chat.Component);
  public abstract java.util.List<com.minecolonies.api.colony.interactionhandling.IInteractionResponseHandler> genChildInteractions();
  public abstract java.lang.String getType();
  public default void onWindowOpened(com.ldtteam.blockui.views.BOWindow, com.minecolonies.api.colony.ICitizenDataView);
  public default net.minecraft.resources.ResourceLocation getInteractionIcon();
  public default void onClosed();
  public default void onOpened(net.minecraft.world.entity.player.Player);
  public abstract net.minecraft.network.chat.Component getId();
}
