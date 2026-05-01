Compiled from "ClientInformationUpdatedEvent.java"
public class net.neoforged.neoforge.event.entity.player.ClientInformationUpdatedEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.neoforged.neoforge.event.entity.player.ClientInformationUpdatedEvent(net.minecraft.server.level.ServerPlayer, net.minecraft.server.level.ClientInformation, net.minecraft.server.level.ClientInformation);
  public net.minecraft.server.level.ServerPlayer getEntity();
  public net.minecraft.server.level.ClientInformation getUpdatedInformation();
  public net.minecraft.server.level.ClientInformation getOldInformation();
  public net.minecraft.world.entity.player.Player getEntity();
  public net.minecraft.world.entity.LivingEntity getEntity();
  public net.minecraft.world.entity.Entity getEntity();
}
