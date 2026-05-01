Compiled from "AbstractCrafting.java"
public abstract class com.minecolonies.api.colony.requestsystem.requestable.crafting.AbstractCrafting implements com.minecolonies.api.colony.requestsystem.requestable.IRequestable {
  public com.minecolonies.api.colony.requestsystem.requestable.crafting.AbstractCrafting(net.minecraft.world.item.ItemStack, int, int, com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public net.minecraft.world.item.ItemStack getStack();
  public int getCount();
  public int getMinCount();
  public com.minecolonies.api.colony.requestsystem.token.IToken<?> getRecipeID();
  public boolean equals(java.lang.Object);
  public int hashCode();
}
