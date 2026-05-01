Compiled from "EmptyPackResources.java"
public class net.neoforged.neoforge.resource.EmptyPackResources extends net.minecraft.server.packs.AbstractPackResources {
  public net.neoforged.neoforge.resource.EmptyPackResources(net.minecraft.server.packs.PackLocationInfo, net.minecraft.server.packs.metadata.pack.PackMetadataSection);
  public <T> T getMetadataSection(net.minecraft.server.packs.metadata.MetadataSectionSerializer<T>);
  public void close();
  public void listResources(net.minecraft.server.packs.PackType, java.lang.String, java.lang.String, net.minecraft.server.packs.PackResources$ResourceOutput);
  public java.util.Set<java.lang.String> getNamespaces(net.minecraft.server.packs.PackType);
  public net.minecraft.server.packs.resources.IoSupplier<java.io.InputStream> getRootResource(java.lang.String...);
  public net.minecraft.server.packs.resources.IoSupplier<java.io.InputStream> getResource(net.minecraft.server.packs.PackType, net.minecraft.resources.ResourceLocation);
}
