Compiled from "IAbstractMinecartExtension.java"
public interface net.neoforged.neoforge.common.extensions.IAbstractMinecartExtension {
  public static final float DEFAULT_MAX_SPEED_AIR_LATERAL;
  public static final float DEFAULT_MAX_SPEED_AIR_VERTICAL;
  public static final double DEFAULT_AIR_DRAG;
  public default net.minecraft.core.BlockPos getCurrentRailPosition();
  public abstract double getMaxSpeedWithRail();
  public abstract void moveMinecartOnRail(net.minecraft.core.BlockPos);
  public abstract boolean canUseRail();
  public abstract void setCanUseRail(boolean);
  public default boolean shouldDoRailFunctions();
  public default boolean isPoweredCart();
  public default boolean canBeRidden();
  public default float getMaxCartSpeedOnRail();
  public abstract float getCurrentCartSpeedCapOnRail();
  public abstract void setCurrentCartSpeedCapOnRail(float);
  public abstract float getMaxSpeedAirLateral();
  public abstract void setMaxSpeedAirLateral(float);
  public abstract float getMaxSpeedAirVertical();
  public abstract void setMaxSpeedAirVertical(float);
  public abstract double getDragAir();
  public abstract void setDragAir(double);
  public default double getSlopeAdjustment();
  public default int getComparatorLevel();
}
