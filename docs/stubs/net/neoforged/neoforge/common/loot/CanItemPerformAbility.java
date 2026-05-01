Compiled from "CanItemPerformAbility.java"
public class net.neoforged.neoforge.common.loot.CanItemPerformAbility implements net.minecraft.world.level.storage.loot.predicates.LootItemCondition {
  public static com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.loot.CanItemPerformAbility> CODEC;
  public static final net.minecraft.world.level.storage.loot.predicates.LootItemConditionType LOOT_CONDITION_TYPE;
  public net.neoforged.neoforge.common.loot.CanItemPerformAbility(net.neoforged.neoforge.common.ItemAbility);
  public net.minecraft.world.level.storage.loot.predicates.LootItemConditionType getType();
  public java.util.Set<net.minecraft.world.level.storage.loot.parameters.LootContextParam<?>> getReferencedContextParams();
  public boolean test(net.minecraft.world.level.storage.loot.LootContext);
  public static net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder canItemPerformAbility(net.neoforged.neoforge.common.ItemAbility);
  public boolean test(java.lang.Object);
}
