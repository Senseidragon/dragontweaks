Compiled from "SelectMusicEvent.java"
public class net.neoforged.neoforge.client.event.SelectMusicEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.client.event.SelectMusicEvent(net.minecraft.sounds.Music, net.minecraft.client.resources.sounds.SoundInstance);
  public net.minecraft.sounds.Music getOriginalMusic();
  public net.minecraft.client.resources.sounds.SoundInstance getPlayingMusic();
  public net.minecraft.sounds.Music getMusic();
  public void setMusic(net.minecraft.sounds.Music);
  public void overrideMusic(net.minecraft.sounds.Music);
}
