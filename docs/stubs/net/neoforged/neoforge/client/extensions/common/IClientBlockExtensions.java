Compiled from "IClientBlockExtensions.java"
public interface net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions {
  public static final net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions DEFAULT;
  public static net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions of(net.minecraft.world.level.block.state.BlockState);
  public static net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions of(net.minecraft.world.level.block.Block);
  public default boolean addHitEffects(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.world.phys.HitResult, net.minecraft.client.particle.ParticleEngine);
  public default boolean addDestroyEffects(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.client.particle.ParticleEngine);
  public default boolean playBreakSound(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public default org.joml.Vector3d getFogColor(net.minecraft.world.level.block.state.BlockState, net.minecraft.world.level.LevelReader, net.minecraft.core.BlockPos, net.minecraft.world.entity.Entity, org.joml.Vector3d, float);
  public default boolean areBreakingParticlesTinted(net.minecraft.world.level.block.state.BlockState, net.minecraft.client.multiplayer.ClientLevel, net.minecraft.core.BlockPos);
}
