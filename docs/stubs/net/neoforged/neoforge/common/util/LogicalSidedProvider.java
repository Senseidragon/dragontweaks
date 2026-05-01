Compiled from "LogicalSidedProvider.java"
public class net.neoforged.neoforge.common.util.LogicalSidedProvider<T> {
  public static final net.neoforged.neoforge.common.util.LogicalSidedProvider<net.minecraft.util.thread.BlockableEventLoop<? super net.minecraft.server.TickTask>> WORKQUEUE;
  public static final net.neoforged.neoforge.common.util.LogicalSidedProvider<java.util.Optional<net.minecraft.world.level.Level>> CLIENTWORLD;
  public static void setClient(java.util.function.Supplier<net.minecraft.client.Minecraft>);
  public static void setServer(java.util.function.Supplier<net.minecraft.server.MinecraftServer>);
  public T get(net.neoforged.fml.LogicalSide);
}
