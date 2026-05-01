Compiled from "GetEnchantmentLevelEvent.java"
public class net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent(net.minecraft.world.item.ItemStack, net.minecraft.world.item.enchantment.ItemEnchantments$Mutable, net.minecraft.core.Holder<net.minecraft.world.item.enchantment.Enchantment>, net.minecraft.core.HolderLookup$RegistryLookup<net.minecraft.world.item.enchantment.Enchantment>);
  public net.minecraft.world.item.ItemStack getStack();
  public net.minecraft.world.item.enchantment.ItemEnchantments$Mutable getEnchantments();
  public net.minecraft.core.Holder<net.minecraft.world.item.enchantment.Enchantment> getTargetEnchant();
  public boolean isTargetting(net.minecraft.core.Holder<net.minecraft.world.item.enchantment.Enchantment>);
  public boolean isTargetting(net.minecraft.resources.ResourceKey<net.minecraft.world.item.enchantment.Enchantment>);
  public java.util.Optional<net.minecraft.core.Holder$Reference<net.minecraft.world.item.enchantment.Enchantment>> getHolder(net.minecraft.resources.ResourceKey<net.minecraft.world.item.enchantment.Enchantment>);
  public net.minecraft.core.HolderLookup$RegistryLookup<net.minecraft.world.item.enchantment.Enchantment> getLookup();
}
