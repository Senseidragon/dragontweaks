Compiled from "ModLootConditions.java"
public final class com.minecolonies.api.loot.ModLootConditions {
  public static final net.neoforged.neoforge.registries.DeferredRegister<net.minecraft.world.level.storage.loot.predicates.LootItemConditionType> DEFERRED_REGISTER;
  public static final net.minecraft.resources.ResourceLocation ENTITY_IN_BIOME_TAG_ID;
  public static final net.minecraft.resources.ResourceLocation RESEARCH_UNLOCKED_ID;
  public static final net.minecraft.resources.ResourceLocation GENERATE_SUPPLY_LOOT_ID;
  public static final net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.world.level.storage.loot.predicates.LootItemConditionType, net.minecraft.world.level.storage.loot.predicates.LootItemConditionType> entityInBiomeTag;
  public static final net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.world.level.storage.loot.predicates.LootItemConditionType, net.minecraft.world.level.storage.loot.predicates.LootItemConditionType> researchUnlocked;
  public static final net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.world.level.storage.loot.predicates.LootItemConditionType, net.minecraft.world.level.storage.loot.predicates.LootItemConditionType> generateSupplyLoot;
  public static final net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder HAS_SHEARS;
  public static final net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder HAS_NETHERITE_HOE;
  public static final net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder HAS_DIAMOND_HOE;
  public static final net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder HAS_IRON_HOE;
  public static final net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder HAS_GOLDEN_HOE;
  public static final net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder HAS_HOE;
  public static net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder hasShears();
  public static net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder hasHoe();
  public static net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder hasSilkTouch(net.minecraft.core.HolderLookup$RegistryLookup<net.minecraft.world.item.enchantment.Enchantment>);
  public static net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder hasShearsOrSilkTouch(net.minecraft.core.HolderLookup$RegistryLookup<net.minecraft.world.item.enchantment.Enchantment>);
  public static net.minecraft.world.level.storage.loot.predicates.LootItemCondition$Builder doesNotHaveShearsOrSilkTouch(net.minecraft.core.HolderLookup$RegistryLookup<net.minecraft.world.item.enchantment.Enchantment>);
  public static void init();
}
