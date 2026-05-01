Compiled from "IConditionBuilder.java"
public interface net.neoforged.neoforge.common.conditions.IConditionBuilder {
  public default net.neoforged.neoforge.common.conditions.ICondition and(net.neoforged.neoforge.common.conditions.ICondition...);
  public default net.neoforged.neoforge.common.conditions.ICondition FALSE();
  public default net.neoforged.neoforge.common.conditions.ICondition TRUE();
  public default net.neoforged.neoforge.common.conditions.ICondition not(net.neoforged.neoforge.common.conditions.ICondition);
  public default net.neoforged.neoforge.common.conditions.ICondition or(net.neoforged.neoforge.common.conditions.ICondition...);
  public default net.neoforged.neoforge.common.conditions.ICondition itemExists(java.lang.String, java.lang.String);
  public default net.neoforged.neoforge.common.conditions.ICondition modLoaded(java.lang.String);
  public default net.neoforged.neoforge.common.conditions.ICondition tagEmpty(net.minecraft.tags.TagKey<net.minecraft.world.item.Item>);
}
