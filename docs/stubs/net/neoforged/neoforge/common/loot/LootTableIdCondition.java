Compiled from "LootTableIdCondition.java"
public class net.neoforged.neoforge.common.loot.LootTableIdCondition implements net.minecraft.world.level.storage.loot.predicates.LootItemCondition {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.loot.LootTableIdCondition> CODEC;
  public static final net.minecraft.world.level.storage.loot.predicates.LootItemConditionType LOOT_TABLE_ID;
  public static final net.minecraft.resources.ResourceLocation UNKNOWN_LOOT_TABLE;
  public net.minecraft.world.level.storage.loot.predicates.LootItemConditionType getType();
  public boolean test(net.minecraft.world.level.storage.loot.LootContext);
  public static net.neoforged.neoforge.common.loot.LootTableIdCondition$Builder builder(net.minecraft.resources.ResourceLocation);
  public boolean test(java.lang.Object);
}
