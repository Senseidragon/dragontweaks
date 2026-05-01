Compiled from "IVisitorData.java"
public interface com.minecolonies.api.colony.IVisitorData extends com.minecolonies.api.colony.ICitizenData {
  public abstract void setRecruitCosts(net.minecraft.world.item.ItemStack);
  public abstract net.minecraft.world.item.ItemStack getRecruitCost();
  public abstract net.minecraft.core.BlockPos getSittingPosition();
  public abstract void setSittingPosition(net.minecraft.core.BlockPos);
}
