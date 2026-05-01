Compiled from "ThreatTable.java"
public class com.minecolonies.api.entity.ai.combat.threat.ThreatTable<T extends net.minecraft.world.entity.LivingEntity & com.minecolonies.api.entity.ai.combat.threat.IThreatTableEntity> {
  public com.minecolonies.api.entity.ai.combat.threat.ThreatTable(T);
  public void addThreat(net.minecraft.world.entity.LivingEntity, int);
  public int getThreatFor(net.minecraft.world.entity.LivingEntity);
  public com.minecolonies.api.entity.ai.combat.threat.ThreatTableEntry getTarget();
  public net.minecraft.world.entity.LivingEntity getTargetMob();
  public void resetCurrentTargetThreat();
  public void markInvalidTarget();
  public void removeCurrentTarget();
  public void resetTable();
}
