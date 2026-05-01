Compiled from "RegisterJsonAnimationTypesEvent.java"
public class net.neoforged.neoforge.client.event.RegisterJsonAnimationTypesEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.client.event.RegisterJsonAnimationTypesEvent(com.google.common.collect.ImmutableMap$Builder<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.client.entity.animation.AnimationTarget>, com.google.common.collect.ImmutableMap$Builder<net.minecraft.resources.ResourceLocation, net.minecraft.client.animation.AnimationChannel$Interpolation>);
  public void registerTarget(net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.client.entity.animation.AnimationTarget);
  public void registerInterpolation(net.minecraft.resources.ResourceLocation, net.minecraft.client.animation.AnimationChannel$Interpolation);
}
