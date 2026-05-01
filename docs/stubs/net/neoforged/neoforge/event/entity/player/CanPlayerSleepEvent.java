Compiled from "CanPlayerSleepEvent.java"
public class net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent(net.minecraft.server.level.ServerPlayer, net.minecraft.core.BlockPos, net.minecraft.world.entity.player.Player$BedSleepingProblem);
  public net.minecraft.server.level.ServerPlayer getEntity();
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.core.BlockPos getPos();
  public net.minecraft.world.level.block.state.BlockState getState();
  public net.minecraft.world.entity.player.Player$BedSleepingProblem getProblem();
  public void setProblem(net.minecraft.world.entity.player.Player$BedSleepingProblem);
  public net.minecraft.world.entity.player.Player$BedSleepingProblem getVanillaProblem();
  public net.minecraft.world.entity.player.Player getEntity();
  public net.minecraft.world.entity.LivingEntity getEntity();
  public net.minecraft.world.entity.Entity getEntity();
}
