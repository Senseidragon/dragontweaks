Compiled from "ITagAppenderExtension.java"
public interface net.neoforged.neoforge.common.extensions.ITagAppenderExtension<T> {
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> addTags(net.minecraft.tags.TagKey<T>...);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> addOptionalTag(net.minecraft.tags.TagKey<T>);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> addOptionalTags(net.minecraft.tags.TagKey<T>...);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> replace();
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> replace(boolean);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> remove(net.minecraft.resources.ResourceLocation);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> remove(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation...);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> remove(net.minecraft.resources.ResourceKey<T>);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> remove(net.minecraft.resources.ResourceKey<T>, net.minecraft.resources.ResourceKey<T>...);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> remove(net.minecraft.tags.TagKey<T>);
  public default net.minecraft.data.tags.TagsProvider$TagAppender<T> remove(net.minecraft.tags.TagKey<T>, net.minecraft.tags.TagKey<T>...);
}
