Compiled from "RegisterShadersEvent.java"
public class net.neoforged.neoforge.client.event.RegisterShadersEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterShadersEvent(net.minecraft.server.packs.resources.ResourceProvider, java.util.List<com.mojang.datafixers.util.Pair<net.minecraft.client.renderer.ShaderInstance, java.util.function.Consumer<net.minecraft.client.renderer.ShaderInstance>>>);
  public net.minecraft.server.packs.resources.ResourceProvider getResourceProvider();
  public void registerShader(net.minecraft.client.renderer.ShaderInstance, java.util.function.Consumer<net.minecraft.client.renderer.ShaderInstance>);
}
