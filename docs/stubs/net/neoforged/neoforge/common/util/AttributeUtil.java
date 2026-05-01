Compiled from "AttributeUtil.java"
public class net.neoforged.neoforge.common.util.AttributeUtil {
  public static final net.minecraft.resources.ResourceLocation BASE_ATTACK_DAMAGE_ID;
  public static final net.minecraft.resources.ResourceLocation BASE_ATTACK_SPEED_ID;
  public static final net.minecraft.resources.ResourceLocation BASE_ENTITY_REACH_ID;
  public static final net.minecraft.resources.ResourceLocation FAKE_MERGED_ID;
  public static final java.util.Comparator<net.minecraft.world.entity.ai.attributes.AttributeModifier> ATTRIBUTE_MODIFIER_COMPARATOR;
  public net.neoforged.neoforge.common.util.AttributeUtil();
  public static void addAttributeTooltips(net.minecraft.world.item.ItemStack, java.util.function.Consumer<net.minecraft.network.chat.Component>, net.neoforged.neoforge.common.util.AttributeTooltipContext);
  public static void applyModifierTooltips(net.minecraft.world.item.ItemStack, java.util.function.Consumer<net.minecraft.network.chat.Component>, net.neoforged.neoforge.common.util.AttributeTooltipContext);
  public static void applyTextFor(net.minecraft.world.item.ItemStack, java.util.function.Consumer<net.minecraft.network.chat.Component>, com.google.common.collect.Multimap<net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>, net.minecraft.world.entity.ai.attributes.AttributeModifier>, net.neoforged.neoforge.common.util.AttributeTooltipContext);
  public static void addPotionTooltip(java.util.List<com.mojang.datafixers.util.Pair<net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>, net.minecraft.world.entity.ai.attributes.AttributeModifier>>, java.util.function.Consumer<net.minecraft.network.chat.Component>);
  public static com.google.common.collect.Multimap<net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>, net.minecraft.world.entity.ai.attributes.AttributeModifier> sortedMap();
  public static com.google.common.collect.Multimap<net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute>, net.minecraft.world.entity.ai.attributes.AttributeModifier> getSortedModifiers(net.minecraft.world.item.ItemStack, net.minecraft.world.entity.EquipmentSlotGroup);
}
