Compiled from "BlockCapabilityCache.java"
public final class net.neoforged.neoforge.capabilities.BlockCapabilityCache<T, C> {
  public static <T, C> net.neoforged.neoforge.capabilities.BlockCapabilityCache<T, C> create(net.neoforged.neoforge.capabilities.BlockCapability<T, C>, net.minecraft.server.level.ServerLevel, net.minecraft.core.BlockPos, C);
  public static <T, C> net.neoforged.neoforge.capabilities.BlockCapabilityCache<T, C> create(net.neoforged.neoforge.capabilities.BlockCapability<T, C>, net.minecraft.server.level.ServerLevel, net.minecraft.core.BlockPos, C, java.util.function.BooleanSupplier, java.lang.Runnable);
  public net.minecraft.server.level.ServerLevel level();
  public net.minecraft.core.BlockPos pos();
  public C context();
  public T getCapability();
}
