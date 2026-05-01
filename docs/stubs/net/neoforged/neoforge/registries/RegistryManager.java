Compiled from "RegistryManager.java"
public class net.neoforged.neoforge.registries.RegistryManager {
  public static final io.netty.util.AttributeKey<java.util.Map<net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<?>>, java.util.Collection<net.minecraft.resources.ResourceLocation>>> ATTRIBUTE_KNOWN_DATA_MAPS;
  public net.neoforged.neoforge.registries.RegistryManager();
  public static <R> net.neoforged.neoforge.registries.datamaps.DataMapType<R, ?> getDataMap(net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<R>>, net.minecraft.resources.ResourceLocation);
  public static java.util.Map<net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<?>>, java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.registries.datamaps.DataMapType<?, ?>>> getDataMaps();
  public static void postNewRegistryEvent();
  public static void initDataMaps();
  public static void revertToVanilla();
  public static void revertToFrozen();
  public static java.util.Set<net.minecraft.resources.ResourceKey<?>> applySnapshot(java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.registries.RegistrySnapshot>, boolean);
  public static java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.registries.RegistrySnapshot> takeSnapshot(net.neoforged.neoforge.registries.RegistryManager$SnapshotType);
  public static java.util.List<net.neoforged.neoforge.network.payload.FrozenRegistryPayload> generateRegistryPackets(boolean);
  public static java.util.List<net.minecraft.resources.ResourceLocation> getRegistryNamesForSyncToClient();
  public static java.util.Set<net.minecraft.resources.ResourceLocation> getVanillaRegistryKeys();
  public static void handleKnownDataMapsReply(net.neoforged.neoforge.network.payload.KnownRegistryDataMapsReplyPayload, net.neoforged.neoforge.network.handling.IPayloadContext);
  public static boolean isNonSyncedBuiltInRegistry(net.minecraft.core.Registry<?>);
}
