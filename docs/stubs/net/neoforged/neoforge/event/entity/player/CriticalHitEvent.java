Compiled from "CriticalHitEvent.java"
public class net.neoforged.neoforge.event.entity.player.CriticalHitEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.neoforged.neoforge.event.entity.player.CriticalHitEvent(net.minecraft.world.entity.player.Player, net.minecraft.world.entity.Entity, float, boolean);
  public net.minecraft.world.entity.Entity getTarget();
  public float getDamageMultiplier();
  public void setDamageMultiplier(float);
  public boolean isCriticalHit();
  public void setCriticalHit(boolean);
  public float getVanillaMultiplier();
  public boolean isVanillaCritical();
  public void setDisableSweep(boolean);
  public boolean disableSweep();
}
