Compiled from "DataMapType.java"
public class net.neoforged.neoforge.registries.datamaps.DataMapType<R, T> {
  public static <T, R> net.neoforged.neoforge.registries.datamaps.DataMapType$Builder<T, R> builder(net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<R>>, com.mojang.serialization.Codec<T>);
  public net.minecraft.resources.ResourceKey<net.minecraft.core.Registry<R>> registryKey();
  public net.minecraft.resources.ResourceLocation id();
  public com.mojang.serialization.Codec<T> codec();
  public com.mojang.serialization.Codec<T> networkCodec();
  public boolean mandatorySync();
}
