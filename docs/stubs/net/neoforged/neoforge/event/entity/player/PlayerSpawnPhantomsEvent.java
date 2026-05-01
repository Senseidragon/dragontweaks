Compiled from "PlayerSpawnPhantomsEvent.java"
public class net.neoforged.neoforge.event.entity.player.PlayerSpawnPhantomsEvent extends net.neoforged.neoforge.event.entity.player.PlayerEvent {
  public net.neoforged.neoforge.event.entity.player.PlayerSpawnPhantomsEvent(net.minecraft.world.entity.player.Player, int);
  public int getPhantomsToSpawn();
  public void setPhantomsToSpawn(int);
  public void setResult(net.neoforged.neoforge.event.entity.player.PlayerSpawnPhantomsEvent$Result);
  public net.neoforged.neoforge.event.entity.player.PlayerSpawnPhantomsEvent$Result getResult();
  public boolean shouldSpawnPhantoms(net.minecraft.server.level.ServerLevel, net.minecraft.core.BlockPos);
}
