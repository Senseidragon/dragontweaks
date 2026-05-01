Compiled from "ChunkClaimData.java"
public class com.minecolonies.api.colony.claim.ChunkClaimData implements com.minecolonies.api.colony.claim.IChunkClaimData, net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public com.minecolonies.api.colony.claim.ChunkClaimData();
  public void addColony(int, net.minecraft.world.level.chunk.LevelChunk);
  public void removeColony(int, net.minecraft.world.level.chunk.LevelChunk);
  public void setStaticColonyClaim(java.util.List<java.lang.Integer>);
  public void reset(net.minecraft.world.level.chunk.LevelChunk);
  public void addBuildingClaim(int, net.minecraft.core.BlockPos, net.minecraft.world.level.chunk.LevelChunk);
  public void removeBuildingClaim(int, net.minecraft.core.BlockPos, net.minecraft.world.level.chunk.LevelChunk);
  public void setOwningColony(int, net.minecraft.world.level.chunk.LevelChunk);
  public int getOwningColony();
  public java.util.List<java.lang.Integer> getStaticClaimColonies();
  public java.util.Map<java.lang.Integer, java.util.Set<net.minecraft.core.BlockPos>> getAllClaimingBuildings();
  public net.minecraft.nbt.CompoundTag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public void serialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public void deSerialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
  public net.minecraft.nbt.Tag serializeNBT(net.minecraft.core.HolderLookup$Provider);
}
