Compiled from "ITagBuilderExtension.java"
public interface net.neoforged.neoforge.common.extensions.ITagBuilderExtension {
  public default net.minecraft.tags.TagBuilder getRawBuilder();
  public default net.minecraft.tags.TagBuilder remove(net.minecraft.tags.TagEntry, java.lang.String);
  public default net.minecraft.tags.TagBuilder removeElement(net.minecraft.resources.ResourceLocation, java.lang.String);
  public default net.minecraft.tags.TagBuilder removeElement(net.minecraft.resources.ResourceLocation);
  public default net.minecraft.tags.TagBuilder removeTag(net.minecraft.resources.ResourceLocation, java.lang.String);
  public default net.minecraft.tags.TagBuilder removeTag(net.minecraft.resources.ResourceLocation);
}
