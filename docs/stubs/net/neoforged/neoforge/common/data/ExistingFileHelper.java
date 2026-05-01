Compiled from "ExistingFileHelper.java"
public class net.neoforged.neoforge.common.data.ExistingFileHelper {
  public net.neoforged.neoforge.common.data.ExistingFileHelper(java.util.Collection<java.nio.file.Path>, java.util.Set<java.lang.String>, boolean, java.lang.String, java.io.File);
  public boolean exists(net.minecraft.resources.ResourceLocation, net.minecraft.server.packs.PackType);
  public boolean exists(net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.common.data.ExistingFileHelper$IResourceType);
  public boolean exists(net.minecraft.resources.ResourceLocation, net.minecraft.server.packs.PackType, java.lang.String, java.lang.String);
  public void trackGenerated(net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.common.data.ExistingFileHelper$IResourceType);
  public void trackGenerated(net.minecraft.resources.ResourceLocation, net.minecraft.server.packs.PackType, java.lang.String, java.lang.String);
  public net.minecraft.server.packs.resources.Resource getResource(net.minecraft.resources.ResourceLocation, net.minecraft.server.packs.PackType, java.lang.String, java.lang.String) throws java.io.FileNotFoundException;
  public net.minecraft.server.packs.resources.Resource getResource(net.minecraft.resources.ResourceLocation, net.minecraft.server.packs.PackType) throws java.io.FileNotFoundException;
  public java.util.List<net.minecraft.server.packs.resources.Resource> getResourceStack(net.minecraft.resources.ResourceLocation, net.minecraft.server.packs.PackType);
  public boolean isEnabled();
}
