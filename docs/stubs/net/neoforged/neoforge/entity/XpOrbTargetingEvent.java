Compiled from "XpOrbTargetingEvent.java"
public class net.neoforged.neoforge.entity.XpOrbTargetingEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.entity.XpOrbTargetingEvent(net.minecraft.world.entity.ExperienceOrb, double);
  public net.minecraft.world.entity.player.Player getFollowingPlayer();
  public void setFollowingPlayer(net.minecraft.world.entity.player.Player);
  public net.minecraft.world.entity.ExperienceOrb getXpOrb();
  public double getScanDistance();
}
