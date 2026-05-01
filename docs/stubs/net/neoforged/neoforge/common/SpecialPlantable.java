Compiled from "SpecialPlantable.java"
public interface net.neoforged.neoforge.common.SpecialPlantable {
  public abstract boolean canPlacePlantAtPosition(net.minecraft.world.item.ItemStack, net.minecraft.world.level.LevelReader, net.minecraft.core.BlockPos, net.minecraft.core.Direction);
  public abstract void spawnPlantAtPosition(net.minecraft.world.item.ItemStack, net.minecraft.world.level.LevelAccessor, net.minecraft.core.BlockPos, net.minecraft.core.Direction);
  public default boolean villagerCanPlantItem(net.minecraft.world.entity.npc.Villager);
}
