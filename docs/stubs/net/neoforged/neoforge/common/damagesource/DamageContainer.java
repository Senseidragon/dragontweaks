Compiled from "DamageContainer.java"
public class net.neoforged.neoforge.common.damagesource.DamageContainer {
  public net.neoforged.neoforge.common.damagesource.DamageContainer(net.minecraft.world.damagesource.DamageSource, float);
  public float getOriginalDamage();
  public net.minecraft.world.damagesource.DamageSource getSource();
  public void setNewDamage(float);
  public float getNewDamage();
  public void addModifier(net.neoforged.neoforge.common.damagesource.DamageContainer$Reduction, net.neoforged.neoforge.common.damagesource.IReductionFunction);
  public float getBlockedDamage();
  public float getShieldDamage();
  public void setPostAttackInvulnerabilityTicks(int);
  public int getPostAttackInvulnerabilityTicks();
  public float getReduction(net.neoforged.neoforge.common.damagesource.DamageContainer$Reduction);
  public void setBlockedDamage(net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent);
  public void setReduction(net.neoforged.neoforge.common.damagesource.DamageContainer$Reduction, float);
}
