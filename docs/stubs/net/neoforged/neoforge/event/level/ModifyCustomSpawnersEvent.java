Compiled from "ModifyCustomSpawnersEvent.java"
public class net.neoforged.neoforge.event.level.ModifyCustomSpawnersEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.level.ModifyCustomSpawnersEvent(net.minecraft.server.level.ServerLevel, java.util.List<net.minecraft.world.level.CustomSpawner>);
  public net.minecraft.server.level.ServerLevel getLevel();
  public java.util.List<net.minecraft.world.level.CustomSpawner> getCustomSpawners();
  public void addCustomSpawner(net.minecraft.world.level.CustomSpawner);
}
