Compiled from "ModEquipmentTypes.java"
public class com.minecolonies.api.equipment.ModEquipmentTypes {
  public static final net.neoforged.neoforge.registries.DeferredRegister<com.minecolonies.api.equipment.registry.EquipmentTypeEntry> DEFERRED_REGISTER;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> none;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> pickaxe;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> shovel;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> axe;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> hoe;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> sword;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> bow;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> fishing_rod;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> shears;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> shield;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> helmet;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> leggings;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> chestplate;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> boots;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> flint_and_steel;
  public static final net.neoforged.neoforge.registries.DeferredHolder<com.minecolonies.api.equipment.registry.EquipmentTypeEntry, com.minecolonies.api.equipment.registry.EquipmentTypeEntry> lead;
  public com.minecolonies.api.equipment.ModEquipmentTypes();
  public static net.minecraft.core.Registry<com.minecolonies.api.equipment.registry.EquipmentTypeEntry> getRegistry();
  public static int vanillaToolLevel(net.minecraft.world.item.ItemStack, com.minecolonies.api.equipment.registry.EquipmentTypeEntry);
  public static int durabilityBasedLevel(net.minecraft.world.item.ItemStack, int);
  public static boolean canPerformDefaultActions(net.minecraft.world.item.ItemStack, java.util.Set<net.neoforged.neoforge.common.ItemAbility>);
}
