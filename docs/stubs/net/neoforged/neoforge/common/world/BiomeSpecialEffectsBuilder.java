Compiled from "BiomeSpecialEffectsBuilder.java"
public class net.neoforged.neoforge.common.world.BiomeSpecialEffectsBuilder extends net.minecraft.world.level.biome.BiomeSpecialEffects$Builder {
  public static net.neoforged.neoforge.common.world.BiomeSpecialEffectsBuilder copyOf(net.minecraft.world.level.biome.BiomeSpecialEffects);
  public static net.neoforged.neoforge.common.world.BiomeSpecialEffectsBuilder create(int, int, int, int);
  public int getFogColor();
  public int waterColor();
  public int getWaterFogColor();
  public int getSkyColor();
  public net.minecraft.world.level.biome.BiomeSpecialEffects$GrassColorModifier getGrassColorModifier();
  public java.util.Optional<java.lang.Integer> getFoliageColorOverride();
  public java.util.Optional<java.lang.Integer> getGrassColorOverride();
  public java.util.Optional<net.minecraft.world.level.biome.AmbientParticleSettings> getAmbientParticle();
  public java.util.Optional<net.minecraft.core.Holder<net.minecraft.sounds.SoundEvent>> getAmbientLoopSound();
  public java.util.Optional<net.minecraft.world.level.biome.AmbientMoodSettings> getAmbientMoodSound();
  public java.util.Optional<net.minecraft.world.level.biome.AmbientAdditionsSettings> getAmbientAdditionsSound();
  public java.util.Optional<net.minecraft.sounds.Music> getBackgroundMusic();
}
