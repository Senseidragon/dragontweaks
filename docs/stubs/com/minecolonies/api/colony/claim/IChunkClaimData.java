Compiled from "IChunkClaimData.java"
public interface com.minecolonies.api.colony.claim.IChunkClaimData {
  public abstract void removeColony(int, net.minecraft.world.level.chunk.LevelChunk);
  public abstract void addColony(int, net.minecraft.world.level.chunk.LevelChunk);
  public abstract java.util.List<java.lang.Integer> getStaticClaimColonies();
  public abstract void setOwningColony(int, net.minecraft.world.level.chunk.LevelChunk);
  public abstract int getOwningColony();
  public abstract void reset(net.minecraft.world.level.chunk.LevelChunk);
  public abstract void addBuildingClaim(int, net.minecraft.core.BlockPos, net.minecraft.world.level.chunk.LevelChunk);
  public abstract void removeBuildingClaim(int, net.minecraft.core.BlockPos, net.minecraft.world.level.chunk.LevelChunk);
  public abstract void setStaticColonyClaim(java.util.List<java.lang.Integer>);
  public abstract java.util.Map<java.lang.Integer, java.util.Set<net.minecraft.core.BlockPos>> getAllClaimingBuildings();
}
