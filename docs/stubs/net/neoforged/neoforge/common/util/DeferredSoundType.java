Compiled from "DeferredSoundType.java"
public class net.neoforged.neoforge.common.util.DeferredSoundType extends net.minecraft.world.level.block.SoundType {
  public net.neoforged.neoforge.common.util.DeferredSoundType(float, float, java.util.function.Supplier<net.minecraft.sounds.SoundEvent>, java.util.function.Supplier<net.minecraft.sounds.SoundEvent>, java.util.function.Supplier<net.minecraft.sounds.SoundEvent>, java.util.function.Supplier<net.minecraft.sounds.SoundEvent>, java.util.function.Supplier<net.minecraft.sounds.SoundEvent>);
  public net.minecraft.sounds.SoundEvent getBreakSound();
  public net.minecraft.sounds.SoundEvent getStepSound();
  public net.minecraft.sounds.SoundEvent getPlaceSound();
  public net.minecraft.sounds.SoundEvent getHitSound();
  public net.minecraft.sounds.SoundEvent getFallSound();
}
