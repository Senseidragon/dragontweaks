Compiled from "BlockGrowFeatureEvent.java"
public class net.neoforged.neoforge.event.level.BlockGrowFeatureEvent extends net.neoforged.neoforge.event.level.LevelEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.level.BlockGrowFeatureEvent(net.minecraft.world.level.LevelAccessor, net.minecraft.util.RandomSource, net.minecraft.core.BlockPos, net.minecraft.core.Holder<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>);
  public net.minecraft.util.RandomSource getRandom();
  public net.minecraft.core.BlockPos getPos();
  public net.minecraft.core.Holder<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> getFeature();
  public void setFeature(net.minecraft.core.Holder<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>);
  public void setFeature(net.minecraft.resources.ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>);
  public void setCanceled(boolean);
}
