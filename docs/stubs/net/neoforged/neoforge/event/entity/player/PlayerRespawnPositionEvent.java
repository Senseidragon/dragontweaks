Compiled from "PlayerRespawnPositionEvent.java"
public class net.neoforged.neoforge.event.entity.player.PlayerRespawnPositionEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.neoforged.neoforge.event.entity.player.PlayerRespawnPositionEvent(net.minecraft.server.level.ServerPlayer, net.minecraft.world.level.portal.DimensionTransition, boolean);
  public net.minecraft.world.level.portal.DimensionTransition getDimensionTransition();
  public void setDimensionTransition(net.minecraft.world.level.portal.DimensionTransition);
  public void setRespawnLevel(net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>);
  public net.minecraft.world.level.portal.DimensionTransition getOriginalDimensionTransition();
  public boolean copyOriginalSpawnPosition();
  public void setCopyOriginalSpawnPosition(boolean);
  public boolean isFromEndFight();
}
