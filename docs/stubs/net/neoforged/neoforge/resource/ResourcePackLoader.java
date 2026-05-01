Compiled from "ResourcePackLoader.java"
public class net.neoforged.neoforge.resource.ResourcePackLoader {
  public static final java.lang.String MOD_DATA_ID;
  public static final java.lang.String MOD_RESOURCES_ID;
  public static final net.minecraft.server.packs.metadata.MetadataSectionType<net.minecraft.server.packs.metadata.pack.PackMetadataSection> OPTIONAL_FORMAT;
  public net.neoforged.neoforge.resource.ResourcePackLoader();
  public static java.util.Optional<net.minecraft.server.packs.repository.Pack$ResourcesSupplier> getPackFor(java.lang.String);
  public static void populatePackRepository(net.minecraft.server.packs.repository.PackRepository, net.minecraft.server.packs.PackType, boolean);
  public static net.minecraft.server.packs.repository.RepositorySource buildPackFinder(java.util.Map<net.neoforged.neoforgespi.locating.IModFile, net.minecraft.server.packs.repository.Pack$ResourcesSupplier>, net.minecraft.server.packs.PackType);
  public static net.minecraft.server.packs.repository.Pack readWithOptionalMeta(net.minecraft.server.packs.PackLocationInfo, net.minecraft.server.packs.repository.Pack$ResourcesSupplier, net.minecraft.server.packs.PackType, net.minecraft.server.packs.PackSelectionConfig) throws java.io.IOException;
  public static net.minecraft.server.packs.repository.Pack$ResourcesSupplier createPackForMod(net.neoforged.neoforgespi.language.IModFileInfo);
  public static java.util.List<java.lang.String> getPackNames(net.minecraft.server.packs.PackType);
  public static java.util.List<net.minecraft.server.packs.repository.Pack> expandAndRemoveRootChildren(java.util.stream.Stream<net.minecraft.server.packs.repository.Pack>, java.util.Collection<net.minecraft.server.packs.repository.Pack>);
  public static void reorderNewlyDiscoveredPacks(java.util.Collection<java.lang.String>, java.util.Collection<java.lang.String>, net.minecraft.server.packs.repository.PackRepository);
}
