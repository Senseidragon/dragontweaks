Compiled from "CanContinueSleepingEvent.java"
public class net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent {
  public net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent(net.minecraft.world.entity.LivingEntity, net.minecraft.world.entity.player.Player$BedSleepingProblem);
  public net.minecraft.world.entity.player.Player$BedSleepingProblem getProblem();
  public boolean mayContinueSleeping();
  public void setContinueSleeping(boolean);
}
