Compiled from "LevelChunkAuxiliaryLightManager.java"
public final class net.neoforged.neoforge.common.world.LevelChunkAuxiliaryLightManager implements net.neoforged.neoforge.common.world.AuxiliaryLightManager, net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.ListTag> {
  public static final java.lang.String LIGHT_NBT_KEY;
  public net.neoforged.neoforge.common.world.LevelChunkAuxiliaryLightManager(net.minecraft.world.level.chunk.LevelChunk);
  public void setLightAt(net.minecraft.core.BlockPos, int);
  public int getLightAt(net.minecraft.core.BlockPos);
  public net.minecraft.nbt.ListTag serializeNBT(net.minecraft.core.HolderLookup$Provider);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.ListTag);
  public net.minecraft.network.protocol.Packet<?> sendLightDataTo(net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket);
  public void handleLightDataSync(java.util.Map<net.minecraft.core.BlockPos, java.lang.Byte>);
  public void deserializeNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
  public net.minecraft.nbt.Tag serializeNBT(net.minecraft.core.HolderLookup$Provider);
}
