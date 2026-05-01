Compiled from "ModdedConfigurationPayloadRegistration.java"
public final class net.neoforged.neoforge.network.registration.ModdedConfigurationPayloadRegistration<T extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> extends java.lang.Record {
  public net.neoforged.neoforge.network.registration.ModdedConfigurationPayloadRegistration(net.minecraft.resources.ResourceLocation, java.lang.Class<T>, net.neoforged.neoforge.network.handling.IPayloadHandler<T>, net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, T>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.resources.ResourceLocation id();
  public java.lang.Class<T> type();
  public net.neoforged.neoforge.network.handling.IPayloadHandler<T> handler();
  public net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, T> reader();
}
