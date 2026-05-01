Compiled from "DirectionalPayloadHandler.java"
public final class net.neoforged.neoforge.network.handling.DirectionalPayloadHandler<T extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> extends java.lang.Record implements net.neoforged.neoforge.network.handling.IPayloadHandler<T> {
  public net.neoforged.neoforge.network.handling.DirectionalPayloadHandler(net.neoforged.neoforge.network.handling.IPayloadHandler<T>, net.neoforged.neoforge.network.handling.IPayloadHandler<T>);
  public void handle(T, net.neoforged.neoforge.network.handling.IPayloadContext);
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public net.neoforged.neoforge.network.handling.IPayloadHandler<T> clientSide();
  public net.neoforged.neoforge.network.handling.IPayloadHandler<T> serverSide();
}
