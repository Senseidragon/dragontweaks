Compiled from "IResearchEffect.java"
public interface com.minecolonies.api.research.IResearchEffect {
  public abstract com.minecolonies.api.research.ModResearchEffects$ResearchEffectEntry getRegistryEntry();
  public abstract net.minecraft.resources.ResourceLocation getId();
  public abstract net.minecraft.network.chat.contents.TranslatableContents getName();
  public abstract net.minecraft.network.chat.contents.TranslatableContents getSubtitle();
  public abstract double getEffect();
  public abstract boolean overrides(com.minecolonies.api.research.IResearchEffect);
  public abstract net.minecraft.nbt.CompoundTag writeToNBT();
}
