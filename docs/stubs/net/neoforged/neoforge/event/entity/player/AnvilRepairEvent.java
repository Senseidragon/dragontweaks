Compiled from "AnvilRepairEvent.java"
public class net.neoforged.neoforge.event.entity.player.AnvilRepairEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.neoforged.neoforge.event.entity.player.AnvilRepairEvent(net.minecraft.world.entity.player.Player, net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack);
  public net.minecraft.world.item.ItemStack getOutput();
  public net.minecraft.world.item.ItemStack getLeft();
  public net.minecraft.world.item.ItemStack getRight();
  public float getBreakChance();
  public void setBreakChance(float);
}
