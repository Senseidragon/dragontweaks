Compiled from "ModelDataManager.java"
public class net.neoforged.neoforge.client.model.data.ModelDataManager {
  public static final it.unimi.dsi.fastutil.longs.Long2ObjectFunction<net.neoforged.neoforge.client.model.data.ModelData> EMPTY_SNAPSHOT;
  public net.neoforged.neoforge.client.model.data.ModelDataManager(net.minecraft.world.level.Level);
  public void requestRefresh(net.minecraft.world.level.block.entity.BlockEntity);
  public it.unimi.dsi.fastutil.longs.Long2ObjectMap<net.neoforged.neoforge.client.model.data.ModelData> getAt(net.minecraft.core.SectionPos);
  public net.neoforged.neoforge.client.model.data.ModelData getAt(net.minecraft.core.BlockPos);
  public it.unimi.dsi.fastutil.longs.Long2ObjectFunction<net.neoforged.neoforge.client.model.data.ModelData> snapshotSectionRegion(int, int, int, int, int, int);
  public static void onChunkUnload(net.neoforged.neoforge.event.level.ChunkEvent$Unload);
}
