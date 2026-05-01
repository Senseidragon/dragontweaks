Compiled from "FoodUtils.java"
public class com.minecolonies.api.util.FoodUtils {
  public static final java.util.function.Predicate<net.minecraft.world.item.ItemStack> EDIBLE;
  public com.minecolonies.api.util.FoodUtils();
  public static boolean canEat(net.minecraft.world.item.ItemStack, com.minecolonies.api.colony.buildings.IBuilding, com.minecolonies.api.colony.buildings.IBuilding);
  public static boolean canEatLevel(net.minecraft.world.item.ItemStack, int);
  public static int getBuildingLevelForFood(net.minecraft.world.item.ItemStack);
  public static double getFoodValue(net.minecraft.world.item.ItemStack, net.minecraft.world.food.FoodProperties, double);
  public static double getFoodValue(net.minecraft.world.item.ItemStack, com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
  public static int getFoodTier(double);
  public static int getBestFoodForCitizen(com.minecolonies.api.inventory.InventoryCitizen, com.minecolonies.api.colony.ICitizenData, java.util.Set<com.minecolonies.api.crafting.ItemStorage>);
  public static com.minecolonies.api.crafting.ItemStorage checkForFoodInBuilding(com.minecolonies.api.colony.ICitizenData, java.util.Set<com.minecolonies.api.crafting.ItemStorage>, com.minecolonies.api.colony.buildings.IBuilding);
  public static boolean hasBestOptionInInv(com.minecolonies.api.inventory.InventoryCitizen, com.minecolonies.api.colony.ICitizenData, java.util.Set<com.minecolonies.api.crafting.ItemStorage>, com.minecolonies.api.colony.buildings.IBuilding);
  public static int getMinFoodQualityRequirement(int);
  public static int getMinFoodDiversityRequirement(int);
  public static net.minecraft.world.item.ItemStack consumeFoodStack(net.minecraft.world.item.ItemStack, com.minecolonies.api.entity.citizen.AbstractEntityCitizen);
}
