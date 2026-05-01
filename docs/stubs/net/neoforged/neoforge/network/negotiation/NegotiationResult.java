Compiled from "NegotiationResult.java"
public final class net.neoforged.neoforge.network.negotiation.NegotiationResult extends java.lang.Record {
  public net.neoforged.neoforge.network.negotiation.NegotiationResult(java.util.List<net.neoforged.neoforge.network.negotiation.NegotiatedNetworkComponent>, boolean, java.util.Map<net.minecraft.resources.ResourceLocation, net.minecraft.network.chat.Component>);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public java.util.List<net.neoforged.neoforge.network.negotiation.NegotiatedNetworkComponent> components();
  public boolean success();
  public java.util.Map<net.minecraft.resources.ResourceLocation, net.minecraft.network.chat.Component> failureReasons();
}
