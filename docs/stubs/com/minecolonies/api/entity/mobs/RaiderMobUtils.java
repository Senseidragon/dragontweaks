Compiled from "RaiderMobUtils.java"
public final class com.minecolonies.api.entity.mobs.RaiderMobUtils {
  public static final net.neoforged.neoforge.registries.DeferredRegister<net.minecraft.world.entity.ai.attributes.Attribute> ATTRIBUTES;
  public static final net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.world.entity.ai.attributes.Attribute, net.minecraft.world.entity.ai.attributes.RangedAttribute> MOB_ATTACK_DAMAGE;
  public static int DAMAGE_PER_X_RAID_LEVEL;
  public static int MAX_RAID_LEVEL_DAMAGE;
  public static void setMobAttributes(com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider, com.minecolonies.api.colony.IColony);
  public static double getHealthBasedOnRaidLevel(int);
  public static void spawn(net.minecraft.world.entity.EntityType<?>, int, net.minecraft.core.BlockPos, net.minecraft.world.level.Level, com.minecolonies.api.colony.IColony, int);
  public static void setEquipment(com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster);
  public static java.util.List<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesRaider> getBarbariansCloseToEntity(net.minecraft.world.entity.Entity, double);
}
