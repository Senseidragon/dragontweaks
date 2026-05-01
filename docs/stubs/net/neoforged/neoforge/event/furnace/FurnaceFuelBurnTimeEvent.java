Compiled from "FurnaceFuelBurnTimeEvent.java"
public class net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent(net.minecraft.world.item.ItemStack, int, net.minecraft.world.item.crafting.RecipeType<?>);
  public net.minecraft.world.item.ItemStack getItemStack();
  public net.minecraft.world.item.crafting.RecipeType<?> getRecipeType();
  public void setBurnTime(int);
  public int getBurnTime();
}
