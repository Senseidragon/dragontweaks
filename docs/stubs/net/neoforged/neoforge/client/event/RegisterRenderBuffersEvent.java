Compiled from "RegisterRenderBuffersEvent.java"
public class net.neoforged.neoforge.client.event.RegisterRenderBuffersEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterRenderBuffersEvent(java.util.SequencedMap<net.minecraft.client.renderer.RenderType, com.mojang.blaze3d.vertex.ByteBufferBuilder>);
  public void registerRenderBuffer(net.minecraft.client.renderer.RenderType);
  public void registerRenderBuffer(net.minecraft.client.renderer.RenderType, com.mojang.blaze3d.vertex.ByteBufferBuilder);
}
