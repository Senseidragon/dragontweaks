Compiled from "NetworkChannel.java"
public final class net.neoforged.neoforge.network.registration.NetworkChannel extends java.lang.Record {
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.registration.NetworkChannel> STREAM_CODEC;
  public net.neoforged.neoforge.network.registration.NetworkChannel(net.minecraft.resources.ResourceLocation, java.lang.String);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.resources.ResourceLocation id();
  public java.lang.String chosenVersion();
}
