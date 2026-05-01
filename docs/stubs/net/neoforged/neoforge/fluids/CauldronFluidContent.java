Compiled from "CauldronFluidContent.java"
public final class net.neoforged.neoforge.fluids.CauldronFluidContent {
  public final net.minecraft.world.level.block.Block block;
  public final net.minecraft.world.level.material.Fluid fluid;
  public final int totalAmount;
  public final int maxLevel;
  public final net.minecraft.world.level.block.state.properties.IntegerProperty levelProperty;
  public int currentLevel(net.minecraft.world.level.block.state.BlockState);
  public static net.neoforged.neoforge.fluids.CauldronFluidContent getForBlock(net.minecraft.world.level.block.Block);
  public static net.neoforged.neoforge.fluids.CauldronFluidContent getForFluid(net.minecraft.world.level.material.Fluid);
  public static void init();
  public static void registerCapabilities(net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent);
}
