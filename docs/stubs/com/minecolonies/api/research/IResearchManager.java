Compiled from "IResearchManager.java"
public interface com.minecolonies.api.research.IResearchManager {
  public abstract void readFromNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void writeToNBT(net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.CompoundTag);
  public abstract void sendPackets(java.util.Set<net.minecraft.server.level.ServerPlayer>, java.util.Set<net.minecraft.server.level.ServerPlayer>);
  public abstract void markDirty();
  public abstract boolean isDirty();
  public abstract void clearDirty();
  public abstract com.minecolonies.api.research.ILocalResearchTree getResearchTree();
  public abstract com.minecolonies.api.research.IResearchEffectManager getResearchEffects();
  public abstract net.minecraft.resources.ResourceLocation getResearchEffectIdFrom(net.minecraft.world.level.block.Block);
  public abstract void checkAutoStartResearch();
}
