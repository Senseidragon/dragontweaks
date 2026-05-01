Compiled from "NeoForgeEventHandler.java"
public class net.neoforged.neoforge.common.NeoForgeEventHandler {
  public net.neoforged.neoforge.common.NeoForgeEventHandler();
  public void onEntityJoinWorld(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent);
  public void onDimensionUnload(net.neoforged.neoforge.event.level.LevelEvent$Unload);
  public void preServerTick(net.neoforged.neoforge.event.tick.ServerTickEvent$Pre);
  public void postServerTick(net.neoforged.neoforge.event.tick.ServerTickEvent$Post);
  public void onChunkUnload(net.neoforged.neoforge.event.level.ChunkEvent$Unload);
  public void playerLogin(net.neoforged.neoforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent);
  public void tagsUpdated(net.neoforged.neoforge.event.TagsUpdatedEvent);
  public void onDpSync(net.neoforged.neoforge.event.OnDatapackSyncEvent);
  public void onCommandsRegister(net.neoforged.neoforge.event.RegisterCommandsEvent);
  public void onResourceReload(net.neoforged.neoforge.event.AddReloadListenerEvent);
  public void resourceReloadListeners(net.neoforged.neoforge.event.AddReloadListenerEvent);
  public void builtinMobSpawnBlocker(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent);
}
