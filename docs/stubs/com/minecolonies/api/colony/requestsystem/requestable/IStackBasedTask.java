Compiled from "IStackBasedTask.java"
public interface com.minecolonies.api.colony.requestsystem.requestable.IStackBasedTask {
  public abstract net.minecraft.world.item.ItemStack getTaskStack();
  public abstract int getDisplayCount();
  public abstract net.minecraft.network.chat.MutableComponent getDisplayPrefix();
}
