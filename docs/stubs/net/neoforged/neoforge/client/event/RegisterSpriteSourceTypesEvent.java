Compiled from "RegisterSpriteSourceTypesEvent.java"
public class net.neoforged.neoforge.client.event.RegisterSpriteSourceTypesEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterSpriteSourceTypesEvent(com.google.common.collect.BiMap<net.minecraft.resources.ResourceLocation, net.minecraft.client.renderer.texture.atlas.SpriteSourceType>);
  public net.minecraft.client.renderer.texture.atlas.SpriteSourceType register(net.minecraft.resources.ResourceLocation, com.mojang.serialization.MapCodec<? extends net.minecraft.client.renderer.texture.atlas.SpriteSource>);
  public void register(net.minecraft.resources.ResourceLocation, net.minecraft.client.renderer.texture.atlas.SpriteSourceType);
}
