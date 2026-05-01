Compiled from "PotionBrewEvent.java"
public abstract class net.neoforged.neoforge.event.brewing.PotionBrewEvent extends net.neoforged.bus.api.Event {
  public net.minecraft.world.item.ItemStack getItem(int);
  public void setItem(int, net.minecraft.world.item.ItemStack);
  public int getLength();
}
