Compiled from "AdvancedOpenScreenPayload.java"
public final class net.neoforged.neoforge.network.payload.AdvancedOpenScreenPayload extends java.lang.Record implements net.minecraft.network.protocol.common.custom.CustomPacketPayload {
  public static final net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.AdvancedOpenScreenPayload> TYPE;
  public static final net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, net.neoforged.neoforge.network.payload.AdvancedOpenScreenPayload> STREAM_CODEC;
  public net.neoforged.neoforge.network.payload.AdvancedOpenScreenPayload(int, net.minecraft.world.inventory.MenuType<?>, net.minecraft.network.chat.Component, byte[]);
  public net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type<net.neoforged.neoforge.network.payload.AdvancedOpenScreenPayload> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int windowId();
  public net.minecraft.world.inventory.MenuType<?> menuType();
  public net.minecraft.network.chat.Component name();
  public byte[] additionalData();
}
