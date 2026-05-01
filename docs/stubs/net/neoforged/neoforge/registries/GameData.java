Compiled from "GameData.java"
public class net.neoforged.neoforge.registries.GameData {
  public net.neoforged.neoforge.registries.GameData();
  public static java.util.Map<net.minecraft.world.level.block.Block, net.minecraft.world.item.Item> getBlockItemMap();
  public static net.minecraft.core.IdMapper<net.minecraft.world.level.block.state.BlockState> getBlockStateIDMap();
  public static java.util.Map<net.minecraft.world.level.block.state.BlockState, net.minecraft.core.Holder<net.minecraft.world.entity.ai.village.poi.PoiType>> getBlockStatePointOfInterestTypeMap();
  public static void vanillaSnapshot();
  public static void unfreezeData();
  public static void freezeData();
  public static void postRegisterEvents();
  public static java.util.Set<net.minecraft.resources.ResourceLocation> getRegistrationOrder();
}
