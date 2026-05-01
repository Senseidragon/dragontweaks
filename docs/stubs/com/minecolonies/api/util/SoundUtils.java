Compiled from "SoundUtils.java"
public final class com.minecolonies.api.util.SoundUtils {
  public static final float PITCH;
  public static final double VOLUME;
  public static final net.neoforged.neoforge.event.level.NoteBlockEvent$Note[] PENTATONIC;
  public static void playRandomSound(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, com.minecolonies.api.colony.ICitizenData);
  public static void playSoundAtCitizen(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.sounds.SoundEvent);
  public static void playSuccessSound(net.minecraft.world.entity.player.Player, net.minecraft.core.BlockPos);
  public static void playErrorSound(net.minecraft.world.entity.player.Player, net.minecraft.core.BlockPos);
  public static void playSoundAtCitizenWith(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, com.minecolonies.api.sounds.EventType, com.minecolonies.api.colony.ICivilianData);
  public static void playSoundAtCitizenWith(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, com.minecolonies.api.sounds.EventType, com.minecolonies.api.colony.ICivilianData, double, double);
  public static void playSoundAtCitizenWith(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, com.minecolonies.api.sounds.EventType, com.minecolonies.api.colony.ICivilianData, double);
  public static double getRandomPitch(net.minecraft.util.RandomSource);
  public static double getRandomPentatonic(net.minecraft.util.RandomSource);
  public static void playSoundForPlayer(net.minecraft.server.level.ServerPlayer, net.minecraft.sounds.SoundEvent, float, float);
}
