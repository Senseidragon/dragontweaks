Compiled from "EntityInBiomeTag.java"
public class com.minecolonies.api.loot.EntityInBiomeTag implements net.minecraft.world.level.storage.loot.predicates.LootItemCondition {
  public static final com.minecolonies.api.loot.EntityInBiomeTag ANY;
  public static final com.mojang.serialization.MapCodec<com.minecolonies.api.loot.EntityInBiomeTag> CODEC;
  public static net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder any();
  public static net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder of(net.minecraft.tags.TagKey<net.minecraft.world.level.biome.Biome>);
  public net.minecraft.world.level.storage.loot.predicates.LootItemConditionType getType();
  public boolean test(net.minecraft.world.level.storage.loot.LootContext);
  public boolean test(java.lang.Object);
}
