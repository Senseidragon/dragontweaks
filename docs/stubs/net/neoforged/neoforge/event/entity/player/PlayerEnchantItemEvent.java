Compiled from "PlayerEnchantItemEvent.java"
public class net.neoforged.neoforge.event.entity.player.PlayerEnchantItemEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.neoforged.neoforge.event.entity.player.PlayerEnchantItemEvent(net.minecraft.world.entity.player.Player, net.minecraft.world.item.ItemStack, java.util.List<net.minecraft.world.item.enchantment.EnchantmentInstance>);
  public net.minecraft.world.item.ItemStack getEnchantedItem();
  public java.util.List<net.minecraft.world.item.enchantment.EnchantmentInstance> getEnchantments();
}
