Compiled from "NetworkPayloadSetup.java"
public final class net.neoforged.neoforge.network.registration.NetworkPayloadSetup extends java.lang.Record {
  public static net.minecraft.network.codec.StreamCodec<net.minecraft.network.FriendlyByteBuf, net.neoforged.neoforge.network.registration.NetworkPayloadSetup> STREAM_CODEC;
  public net.neoforged.neoforge.network.registration.NetworkPayloadSetup(java.util.Map<net.minecraft.network.ConnectionProtocol, java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.network.registration.NetworkChannel>>);
  public java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.network.registration.NetworkChannel> getChannels(net.minecraft.network.ConnectionProtocol);
  public net.neoforged.neoforge.network.registration.NetworkChannel getChannel(net.minecraft.network.ConnectionProtocol, net.minecraft.resources.ResourceLocation);
  public static net.neoforged.neoforge.network.registration.NetworkPayloadSetup empty();
  public static net.neoforged.neoforge.network.registration.NetworkPayloadSetup from(java.util.Map<net.minecraft.network.ConnectionProtocol, net.neoforged.neoforge.network.negotiation.NegotiationResult>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.Map<net.minecraft.network.ConnectionProtocol, java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.network.registration.NetworkChannel>> channels();
}
