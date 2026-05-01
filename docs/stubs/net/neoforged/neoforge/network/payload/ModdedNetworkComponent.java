Compiled from "ModdedNetworkComponent.java"
public final class net.neoforged.neoforge.network.payload.ModdedNetworkComponent extends java.lang.Record {
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.payload.ModdedNetworkComponent> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.ModdedNetworkComponent(net.minecraft.resources.ResourceLocation, java.util.Optional<java.lang.String>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.resources.ResourceLocation id();
  public java.util.Optional<java.lang.String> version();
}
