Compiled from "EnchantmentLevelSetEvent.java"
public class net.neoforged.neoforge.event.enchanting.EnchantmentLevelSetEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.enchanting.EnchantmentLevelSetEvent(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, int, int, net.minecraft.world.item.ItemStack, int);
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.core.BlockPos getPos();
  public int getEnchantRow();
  public int getPower();
  public net.minecraft.world.item.ItemStack getItem();
  public int getOriginalLevel();
  public int getEnchantLevel();
  public void setEnchantLevel(int);
}
