Compiled from "IResearchEffectManager.java"
public interface com.minecolonies.api.research.IResearchEffectManager {
  public abstract double getEffectStrength(net.minecraft.resources.ResourceLocation);
  public abstract void applyEffect(com.minecolonies.api.research.IResearchEffect);
  public abstract void removeAllEffects();
}
