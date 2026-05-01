Compiled from "InventoryFunctions.java"
public final class com.minecolonies.api.util.InventoryFunctions {
  public static boolean matchFirstInProvider(com.minecolonies.api.util.IItemHandlerCapProvider, java.util.function.Predicate<net.minecraft.world.item.ItemStack>);
  public static boolean matchFirstInProviderWithAction(com.minecolonies.api.util.IItemHandlerCapProvider, java.util.function.Predicate<net.minecraft.world.item.ItemStack>, com.minecolonies.api.util.InventoryFunctions$IMatchActionResult);
  public static boolean matchFirstInHandlerWithAction(net.neoforged.neoforge.items.IItemHandler, java.util.function.Predicate<net.minecraft.world.item.ItemStack>, com.minecolonies.api.util.InventoryFunctions$IMatchActionResultHandler);
  public static boolean matchFirstInProviderWithSimpleAction(com.minecolonies.api.util.IItemHandlerCapProvider, java.util.function.Predicate<net.minecraft.world.item.ItemStack>, java.util.function.Consumer<java.lang.Integer>);
  public static boolean matchFirstInProvider(com.minecolonies.api.util.IItemHandlerCapProvider, java.util.function.BiPredicate<java.lang.Integer, net.minecraft.world.item.ItemStack>);
}
