Compiled from "IBlockMinecolonies.java"
public interface com.minecolonies.api.blocks.interfaces.IBlockMinecolonies<B extends com.minecolonies.api.blocks.interfaces.IBlockMinecolonies<B>> {
  public abstract B registerBlock(net.minecraft.core.Registry<net.minecraft.world.level.block.Block>);
  public abstract void registerBlockItem(net.minecraft.core.Registry<net.minecraft.world.item.Item>, net.minecraft.world.item.Item$Properties);
  public abstract net.minecraft.resources.ResourceLocation getRegistryName();
}
