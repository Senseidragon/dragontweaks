Compiled from "IPlayerListExtension.java"
public interface net.neoforged.neoforge.common.extensions.IPlayerListExtension {
  public default net.minecraft.server.players.PlayerList self();
  public default void broadcastAll(net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public default void broadcastAll(net.minecraft.network.protocol.common.custom.CustomPacketPayload, net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>);
  public default void broadcast(double, double, double, double, net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>, net.minecraft.network.protocol.common.custom.CustomPacketPayload);
  public default void broadcast(net.minecraft.world.entity.player.Player, double, double, double, double, net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>, net.minecraft.network.protocol.common.custom.CustomPacketPayload);
}
