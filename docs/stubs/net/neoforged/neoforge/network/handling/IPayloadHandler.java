Compiled from "IPayloadHandler.java"
public interface net.neoforged.neoforge.network.handling.IPayloadHandler<T extends net.minecraft.network.protocol.common.custom.CustomPacketPayload> {
  public abstract void handle(T, net.neoforged.neoforge.network.handling.IPayloadContext);
}
