Compiled from "GuardGear.java"
public class com.minecolonies.api.entity.ai.workers.util.GuardGear implements java.util.function.Predicate<net.minecraft.world.item.ItemStack> {
  public com.minecolonies.api.entity.ai.workers.util.GuardGear(com.minecolonies.api.equipment.registry.EquipmentTypeEntry, net.minecraft.world.entity.EquipmentSlot, int, int, net.minecraft.util.Tuple<java.lang.Integer, java.lang.Integer>, net.minecraft.util.Tuple<java.lang.Integer, java.lang.Integer>);
  public int getMinLevelRequired();
  public int getMaxLevelRequired();
  public net.minecraft.world.entity.EquipmentSlot getType();
  public int getMinArmorLevel();
  public int getMaxArmorLevel();
  public com.minecolonies.api.equipment.registry.EquipmentTypeEntry getItemNeeded();
  public int getMinBuildingLevelRequired();
  public int getMaxBuildingLevelRequired();
  public boolean test(net.minecraft.world.item.ItemStack);
  public boolean test(java.lang.Object);
}
