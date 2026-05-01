Compiled from "IAttributeExtension.java"
public interface net.neoforged.neoforge.common.extensions.IAttributeExtension {
  public static final java.text.DecimalFormat FORMAT;
  public default net.minecraft.network.chat.MutableComponent toValueComponent(net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation, double, net.minecraft.world.item.TooltipFlag);
  public default net.minecraft.network.chat.MutableComponent toComponent(net.minecraft.world.entity.ai.attributes.AttributeModifier, net.minecraft.world.item.TooltipFlag);
  public default net.minecraft.network.chat.Component getDebugInfo(net.minecraft.world.entity.ai.attributes.AttributeModifier, net.minecraft.world.item.TooltipFlag);
  public default net.minecraft.resources.ResourceLocation getBaseId();
  public default net.minecraft.network.chat.MutableComponent toBaseComponent(double, double, boolean, net.minecraft.world.item.TooltipFlag);
  public abstract net.minecraft.network.chat.TextColor getMergedStyle(boolean);
  public static boolean isNullOrAddition(net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation);
}
