Compiled from "RegisterCapabilitiesEvent.java"
public class net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public <T, C> void registerBlock(net.neoforged.neoforge.capabilities.BlockCapability<T, C>, net.neoforged.neoforge.capabilities.IBlockCapabilityProvider<T, C>, net.minecraft.world.level.block.Block...);
  public <T, C, BE extends net.minecraft.world.level.block.entity.BlockEntity> void registerBlockEntity(net.neoforged.neoforge.capabilities.BlockCapability<T, C>, net.minecraft.world.level.block.entity.BlockEntityType<BE>, net.neoforged.neoforge.capabilities.ICapabilityProvider<? super BE, C, T>);
  public boolean isBlockRegistered(net.neoforged.neoforge.capabilities.BlockCapability<?, ?>, net.minecraft.world.level.block.Block);
  public void setProxyable(net.neoforged.neoforge.capabilities.BlockCapability<?, ?>);
  public void setNonProxyable(net.neoforged.neoforge.capabilities.BlockCapability<?, ?>);
  public <T, C, E extends net.minecraft.world.entity.Entity> void registerEntity(net.neoforged.neoforge.capabilities.EntityCapability<T, C>, net.minecraft.world.entity.EntityType<E>, net.neoforged.neoforge.capabilities.ICapabilityProvider<? super E, C, T>);
  public boolean isEntityRegistered(net.neoforged.neoforge.capabilities.EntityCapability<?, ?>, net.minecraft.world.entity.EntityType<?>);
  public <T, C> void registerItem(net.neoforged.neoforge.capabilities.ItemCapability<T, C>, net.neoforged.neoforge.capabilities.ICapabilityProvider<net.minecraft.world.item.ItemStack, C, T>, net.minecraft.world.level.ItemLike...);
  public boolean isItemRegistered(net.neoforged.neoforge.capabilities.ItemCapability<?, ?>, net.minecraft.world.item.Item);
}
