Compiled from "IIntrinsicHolderTagAppenderExtension.java"
public interface net.neoforged.neoforge.common.extensions.IIntrinsicHolderTagAppenderExtension<T> extends net.neoforged.neoforge.common.extensions.ITagAppenderExtension<T> {
  public abstract net.minecraft.resources.ResourceKey<T> getKey(T);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> remove(T);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> remove(T, T...);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> addTags(net.minecraft.tags.TagKey<T>...);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> replace();
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> replace(boolean);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> remove(net.minecraft.resources.ResourceLocation);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> remove(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation...);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> remove(net.minecraft.resources.ResourceKey<T>);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> remove(net.minecraft.resources.ResourceKey<T>, net.minecraft.resources.ResourceKey<T>...);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> remove(net.minecraft.tags.TagKey<T>);
  public default net.minecraft.data.tags.IntrinsicHolderTagsProvider$IntrinsicTagAppender<T> remove(net.minecraft.tags.TagKey<T>, net.minecraft.tags.TagKey<T>...);
  public default net.minecraft.data.tags.TagsProvider$TagAppender remove(net.minecraft.tags.TagKey, net.minecraft.tags.TagKey[]);
  public default net.minecraft.data.tags.TagsProvider$TagAppender remove(net.minecraft.tags.TagKey);
  public default net.minecraft.data.tags.TagsProvider$TagAppender remove(net.minecraft.resources.ResourceKey, net.minecraft.resources.ResourceKey[]);
  public default net.minecraft.data.tags.TagsProvider$TagAppender remove(net.minecraft.resources.ResourceKey);
  public default net.minecraft.data.tags.TagsProvider$TagAppender remove(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation[]);
  public default net.minecraft.data.tags.TagsProvider$TagAppender remove(net.minecraft.resources.ResourceLocation);
  public default net.minecraft.data.tags.TagsProvider$TagAppender replace(boolean);
  public default net.minecraft.data.tags.TagsProvider$TagAppender replace();
  public default net.minecraft.data.tags.TagsProvider$TagAppender addTags(net.minecraft.tags.TagKey[]);
}
