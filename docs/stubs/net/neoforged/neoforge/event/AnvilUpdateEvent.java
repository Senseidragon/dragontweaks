Compiled from "AnvilUpdateEvent.java"
public class net.neoforged.neoforge.event.AnvilUpdateEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.AnvilUpdateEvent(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack, java.lang.String, long, net.minecraft.world.entity.player.Player);
  public net.minecraft.world.item.ItemStack getLeft();
  public net.minecraft.world.item.ItemStack getRight();
  public java.lang.String getName();
  public net.minecraft.world.item.ItemStack getOutput();
  public void setOutput(net.minecraft.world.item.ItemStack);
  public long getCost();
  public void setCost(long);
  public int getMaterialCost();
  public void setMaterialCost(int);
  public net.minecraft.world.entity.player.Player getPlayer();
}
