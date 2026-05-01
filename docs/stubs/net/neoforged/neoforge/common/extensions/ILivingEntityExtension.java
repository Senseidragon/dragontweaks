Compiled from "ILivingEntityExtension.java"
public interface net.neoforged.neoforge.common.extensions.ILivingEntityExtension extends net.neoforged.neoforge.common.extensions.IEntityExtension {
  public default net.minecraft.world.entity.LivingEntity self();
  public default boolean canSwimInFluidType(net.neoforged.neoforge.fluids.FluidType);
  public default void jumpInFluid(net.neoforged.neoforge.fluids.FluidType);
  public default void sinkInFluid(net.neoforged.neoforge.fluids.FluidType);
  public default boolean canDrownInFluidType(net.neoforged.neoforge.fluids.FluidType);
  public default boolean moveInFluid(net.minecraft.world.level.material.FluidState, net.minecraft.world.phys.Vec3, double);
  public default void onDamageTaken(net.neoforged.neoforge.common.damagesource.DamageContainer);
}
