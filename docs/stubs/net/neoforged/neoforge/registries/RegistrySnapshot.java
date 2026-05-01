Compiled from "RegistrySnapshot.java"
public class net.neoforged.neoforge.registries.RegistrySnapshot {
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.registries.RegistrySnapshot> STREAM_CODEC;
  public <T> net.neoforged.neoforge.registries.RegistrySnapshot(net.minecraft.core.Registry<T>, boolean);
  public it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap<net.minecraft.resources.ResourceLocation> getIds();
  public java.util.Map<net.minecraft.resources.ResourceLocation, net.minecraft.resources.ResourceLocation> getAliases();
  public <T> net.minecraft.core.Registry<T> getFullBackup();
}
