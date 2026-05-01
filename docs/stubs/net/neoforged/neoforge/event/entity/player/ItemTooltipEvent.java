Compiled from "ItemTooltipEvent.java"
public class net.neoforged.neoforge.event.entity.player.ItemTooltipEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.neoforged.neoforge.event.entity.player.ItemTooltipEvent(net.minecraft.world.item.ItemStack, net.minecraft.world.entity.player.Player, java.util.List<net.minecraft.network.chat.Component>, net.minecraft.world.item.TooltipFlag, net.minecraft.world.item.Item$TooltipContext);
  public net.minecraft.world.item.TooltipFlag getFlags();
  public net.minecraft.world.item.ItemStack getItemStack();
  public java.util.List<net.minecraft.network.chat.Component> getToolTip();
  public net.minecraft.world.entity.player.Player getEntity();
  public net.minecraft.world.item.Item$TooltipContext getContext();
  public net.minecraft.world.entity.LivingEntity getEntity();
  public net.minecraft.world.entity.Entity getEntity();
}
