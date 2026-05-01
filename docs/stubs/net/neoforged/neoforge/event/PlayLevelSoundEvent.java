Compiled from "PlayLevelSoundEvent.java"
public class net.neoforged.neoforge.event.PlayLevelSoundEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.PlayLevelSoundEvent(net.minecraft.world.level.Level, net.minecraft.core.Holder<net.minecraft.sounds.SoundEvent>, net.minecraft.sounds.SoundSource, float, float);
  public net.minecraft.world.level.Level getLevel();
  public net.minecraft.core.Holder<net.minecraft.sounds.SoundEvent> getSound();
  public void setSound(net.minecraft.core.Holder<net.minecraft.sounds.SoundEvent>);
  public net.minecraft.sounds.SoundSource getSource();
  public void setSource(net.minecraft.sounds.SoundSource);
  public float getOriginalVolume();
  public float getOriginalPitch();
  public float getNewVolume();
  public void setNewVolume(float);
  public float getNewPitch();
  public void setNewPitch(float);
}
