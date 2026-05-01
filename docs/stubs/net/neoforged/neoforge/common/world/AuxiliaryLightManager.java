Compiled from "AuxiliaryLightManager.java"
public interface net.neoforged.neoforge.common.world.AuxiliaryLightManager {
  public abstract void setLightAt(net.minecraft.core.BlockPos, int);
  public default void removeLightAt(net.minecraft.core.BlockPos);
  public abstract int getLightAt(net.minecraft.core.BlockPos);
}
