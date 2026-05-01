Compiled from "PayloadRegistration.java"
public final class net.neoforged.neoforge.network.registration.PayloadRegistration<T extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> extends java.lang.Record {
  public net.neoforged.neoforge.network.registration.PayloadRegistration(net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<T>, net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.RegistryFriendlyByteBuf, T>, net.neoforged.neoforge.network.handling.IPayloadHandler<T>, java.util.List<net.minecraft.network.ConnectionProtocol>, java.util.Optional<net.minecraft.network.protocol.PacketFlow>, java.lang.String, boolean);
  public net.minecraft.resources.ResourceLocation id();
  public boolean matchesFlow(net.minecraft.network.protocol.PacketFlow);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<T> type();
  public net.minecraft.network.codec.StreamCodec<? super net.minecraft.network.RegistryFriendlyByteBuf, T> codec();
  public net.neoforged.neoforge.network.handling.IPayloadHandler<T> handler();
  public java.util.List<net.minecraft.network.ConnectionProtocol> protocols();
  public java.util.Optional<net.minecraft.network.protocol.PacketFlow> flow();
  public java.lang.String version();
  public boolean optional();
}
