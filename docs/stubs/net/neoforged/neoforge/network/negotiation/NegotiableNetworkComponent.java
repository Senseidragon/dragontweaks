Compiled from "NegotiableNetworkComponent.java"
public final class net.neoforged.neoforge.network.negotiation.NegotiableNetworkComponent extends java.lang.Record {
  public net.neoforged.neoforge.network.negotiation.NegotiableNetworkComponent(net.neoforged.neoforge.network.registration.PayloadRegistration<?>);
  public net.neoforged.neoforge.network.negotiation.NegotiableNetworkComponent(net.neoforged.neoforge.network.payload.ModdedNetworkQueryComponent);
  public net.neoforged.neoforge.network.negotiation.NegotiableNetworkComponent(net.minecraft.resources.ResourceLocation, java.lang.String, java.util.Optional<net.minecraft.network.protocol.PacketFlow>, boolean);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.minecraft.resources.ResourceLocation id();
  public java.lang.String version();
  public java.util.Optional<net.minecraft.network.protocol.PacketFlow> flow();
  public boolean optional();
}
