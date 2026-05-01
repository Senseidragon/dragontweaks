Compiled from "ChunkDataEvent.java"
public abstract class net.neoforged.neoforge.event.level.ChunkDataEvent extends net.neoforged.neoforge.event.level.ChunkEvent {
  public net.neoforged.neoforge.event.level.ChunkDataEvent(net.minecraft.world.level.chunk.ChunkAccess, net.minecraft.nbt.CompoundTag);
  public net.neoforged.neoforge.event.level.ChunkDataEvent(net.minecraft.world.level.chunk.ChunkAccess, net.minecraft.world.level.LevelAccessor, net.minecraft.nbt.CompoundTag);
  public net.minecraft.nbt.CompoundTag getData();
}
