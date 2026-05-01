Compiled from "RegisterClientExtensionsEvent.java"
public final class net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public void registerBlock(net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions, net.minecraft.world.level.block.Block...);
  public final void registerBlock(net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions, net.minecraft.core.Holder<net.minecraft.world.level.block.Block>...);
  public boolean isBlockRegistered(net.minecraft.world.level.block.Block);
  public void registerItem(net.neoforged.neoforge.client.extensions.common.IClientItemExtensions, net.minecraft.world.item.Item...);
  public final void registerItem(net.neoforged.neoforge.client.extensions.common.IClientItemExtensions, net.minecraft.core.Holder<net.minecraft.world.item.Item>...);
  public boolean isItemRegistered(net.minecraft.world.item.Item);
  public void registerMobEffect(net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions, net.minecraft.world.effect.MobEffect...);
  public final void registerMobEffect(net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions, net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect>...);
  public boolean isMobEffectRegistered(net.minecraft.world.effect.MobEffect);
  public void registerFluidType(net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions, net.neoforged.neoforge.fluids.FluidType...);
  public final void registerFluidType(net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions, net.minecraft.core.Holder<net.neoforged.neoforge.fluids.FluidType>...);
  public boolean isFluidTypeRegistered(net.neoforged.neoforge.fluids.FluidType);
}
