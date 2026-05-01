Compiled from "IOUtilities.java"
public final class net.neoforged.neoforge.common.IOUtilities {
  public static void tryCleanupTempFiles(java.nio.file.Path, java.lang.String);
  public static void cleanupTempFiles(java.nio.file.Path, java.lang.String) throws java.io.IOException;
  public static void writeNbtCompressed(net.minecraft.nbt.CompoundTag, java.nio.file.Path) throws java.io.IOException;
  public static void writeNbt(net.minecraft.nbt.CompoundTag, java.nio.file.Path) throws java.io.IOException;
  public static void atomicWrite(java.nio.file.Path, net.neoforged.neoforge.common.IOUtilities$WriteCallback) throws java.io.IOException;
  public static void withIOWorker(java.lang.Runnable);
  public static void waitUntilIOWorkerComplete();
}
