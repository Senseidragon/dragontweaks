Compiled from "AttributeTooltipContext.java"
public interface net.neoforged.neoforge.common.util.AttributeTooltipContext extends net.minecraft.world.item.Item$TooltipContext {
  public abstract net.minecraft.world.entity.player.Player player();
  public abstract net.minecraft.world.item.TooltipFlag flag();
  public static net.neoforged.neoforge.common.util.AttributeTooltipContext of(net.minecraft.world.entity.player.Player, net.minecraft.world.item.Item$TooltipContext, net.minecraft.world.item.TooltipFlag);
}
