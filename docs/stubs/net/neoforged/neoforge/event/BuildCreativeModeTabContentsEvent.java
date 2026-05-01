Compiled from "BuildCreativeModeTabContentsEvent.java"
public final class net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent,net.minecraft.world.item.CreativeModeTab$Output {
  public net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent(net.minecraft.world.item.CreativeModeTab, net.minecraft.resources.ResourceKey<net.minecraft.world.item.CreativeModeTab>, net.minecraft.world.item.CreativeModeTab$ItemDisplayParameters, net.neoforged.neoforge.common.util.InsertableLinkedOpenCustomHashSet<net.minecraft.world.item.ItemStack>, net.neoforged.neoforge.common.util.InsertableLinkedOpenCustomHashSet<net.minecraft.world.item.ItemStack>);
  public net.minecraft.world.item.CreativeModeTab getTab();
  public net.minecraft.resources.ResourceKey<net.minecraft.world.item.CreativeModeTab> getTabKey();
  public net.minecraft.world.flag.FeatureFlagSet getFlags();
  public net.minecraft.world.item.CreativeModeTab$ItemDisplayParameters getParameters();
  public boolean hasPermissions();
  public it.unimi.dsi.fastutil.objects.ObjectSortedSet<net.minecraft.world.item.ItemStack> getParentEntries();
  public it.unimi.dsi.fastutil.objects.ObjectSortedSet<net.minecraft.world.item.ItemStack> getSearchEntries();
  public void accept(net.minecraft.world.item.ItemStack, net.minecraft.world.item.CreativeModeTab$TabVisibility);
  public void insertAfter(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack, net.minecraft.world.item.CreativeModeTab$TabVisibility);
  public void insertBefore(net.minecraft.world.item.ItemStack, net.minecraft.world.item.ItemStack, net.minecraft.world.item.CreativeModeTab$TabVisibility);
  public void insertFirst(net.minecraft.world.item.ItemStack, net.minecraft.world.item.CreativeModeTab$TabVisibility);
  public void remove(net.minecraft.world.item.ItemStack, net.minecraft.world.item.CreativeModeTab$TabVisibility);
}
