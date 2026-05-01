Compiled from "IEntitySelectorType.java"
public interface net.neoforged.neoforge.common.command.IEntitySelectorType {
  public abstract net.minecraft.commands.arguments.selector.EntitySelector build(net.minecraft.commands.arguments.selector.EntitySelectorParser) throws com.mojang.brigadier.exceptions.CommandSyntaxException;
  public abstract net.minecraft.network.chat.Component getSuggestionTooltip();
}
