Compiled from "ICraftingSetting.java"
public interface com.minecolonies.api.colony.buildings.modules.settings.ICraftingSetting extends com.minecolonies.api.colony.buildings.modules.settings.ISetting<com.minecolonies.api.colony.requestsystem.token.IToken<?>> {
  public abstract com.minecolonies.api.crafting.IRecipeStorage getValue(com.minecolonies.api.colony.buildings.IBuilding);
  public abstract com.minecolonies.api.crafting.IRecipeStorage getValue(com.minecolonies.api.colony.buildings.views.IBuildingView);
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getSettings(com.minecolonies.api.colony.buildings.IBuilding);
  public abstract java.util.List<net.minecraft.world.item.ItemStack> getSettings(com.minecolonies.api.colony.buildings.views.IBuildingView);
  public abstract void set(com.minecolonies.api.crafting.IRecipeStorage);
}
