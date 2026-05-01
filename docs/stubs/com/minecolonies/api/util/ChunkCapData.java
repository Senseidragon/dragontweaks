Compiled from "ChunkCapData.java"
public class com.minecolonies.api.util.ChunkCapData {
  public final int x;
  public final int z;
  public com.minecolonies.api.util.ChunkCapData(int, int);
  public com.minecolonies.api.util.ChunkCapData(int, int, int, java.util.List<java.lang.Integer>, java.util.Map<java.lang.Integer, java.util.Set<net.minecraft.core.BlockPos>>);
  public void toBytes(net.minecraft.network.RegistryFriendlyByteBuf);
  public static com.minecolonies.api.util.ChunkCapData fromBytes(net.minecraft.network.RegistryFriendlyByteBuf);
  public java.util.List<java.lang.Integer> getStaticColonyClaim();
  public int getOwningColony();
  public java.util.Map<java.lang.Integer, java.util.Set<net.minecraft.core.BlockPos>> getAllClaimingBuildings();
}
