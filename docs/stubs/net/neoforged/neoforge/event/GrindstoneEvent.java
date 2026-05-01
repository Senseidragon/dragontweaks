Compiled from "GrindstoneEvent.java"
public abstract class net.neoforged.neoforge.event.GrindstoneEvent extends net.neoforged.bus.api.Event {
  public net.minecraft.world.item.ItemStack getTopItem();
  public net.minecraft.world.item.ItemStack getBottomItem();
  public int getXp();
  public void setXp(int);
}
