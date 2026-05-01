Compiled from "ItemExistsCondition.java"
public class net.neoforged.neoforge.common.conditions.ItemExistsCondition implements net.neoforged.neoforge.common.conditions.ICondition {
  public static com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.conditions.ItemExistsCondition> CODEC;
  public net.neoforged.neoforge.common.conditions.ItemExistsCondition(java.lang.String);
  public net.neoforged.neoforge.common.conditions.ItemExistsCondition(java.lang.String, java.lang.String);
  public net.neoforged.neoforge.common.conditions.ItemExistsCondition(net.minecraft.resources.ResourceLocation);
  public boolean test(net.neoforged.neoforge.common.conditions.ICondition$IContext);
  public com.mojang.serialization.MapCodec<? extends net.neoforged.neoforge.common.conditions.ICondition> codec();
  public net.minecraft.resources.ResourceLocation getItem();
  public java.lang.String toString();
}
