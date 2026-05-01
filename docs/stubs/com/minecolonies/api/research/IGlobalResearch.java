Compiled from "IGlobalResearch.java"
public interface com.minecolonies.api.research.IGlobalResearch {
  public abstract boolean canResearch(com.minecolonies.api.colony.buildings.IBuilding, com.minecolonies.api.research.ILocalResearchTree);
  public abstract boolean canDisplay(int);
  public abstract boolean hasEnoughResources(net.minecraft.world.entity.player.Player, net.minecraft.core.BlockPos);
  public abstract java.util.List<net.neoforged.neoforge.common.crafting.SizedIngredient> getCostList();
  public abstract void startResearch(com.minecolonies.api.research.ILocalResearchTree);
  public abstract net.minecraft.network.chat.contents.TranslatableContents getName();
  public abstract net.minecraft.network.chat.contents.TranslatableContents getSubtitle();
  public abstract net.minecraft.resources.ResourceLocation getId();
  public abstract net.minecraft.resources.ResourceLocation getParent();
  public abstract net.minecraft.resources.ResourceLocation getBranch();
  public abstract int getDepth();
  public abstract int getSortOrder();
  public abstract boolean isInstant();
  public abstract boolean isAutostart();
  public abstract boolean isHidden();
  public abstract boolean isImmutable();
  public abstract boolean hasOnlyChild();
  public abstract boolean hasResearchedChild(com.minecolonies.api.research.ILocalResearchTree);
  public abstract void addChild(com.minecolonies.api.research.IGlobalResearch);
  public abstract void addChild(net.minecraft.resources.ResourceLocation);
  public abstract void addCost(net.neoforged.neoforge.common.crafting.SizedIngredient);
  public abstract void addEffect(com.minecolonies.api.research.IResearchEffect);
  public abstract void addRequirement(com.minecolonies.api.research.IResearchRequirement);
  public abstract java.util.List<net.minecraft.resources.ResourceLocation> getChildren();
  public abstract java.util.List<com.minecolonies.api.research.IResearchRequirement> getResearchRequirements();
  public abstract java.util.List<com.minecolonies.api.research.IResearchEffect> getEffects();
  public static boolean isPlayerResearchMatch(net.minecraft.world.item.ItemStack, net.neoforged.neoforge.common.crafting.SizedIngredient);
  public static boolean isUniversityResearchMatch(net.minecraft.world.item.ItemStack, net.neoforged.neoforge.common.crafting.SizedIngredient);
}
