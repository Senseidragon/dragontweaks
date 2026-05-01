Compiled from "IAnimalDataView.java"
public interface com.minecolonies.api.colony.managers.interfaces.IAnimalDataView {
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract int getId();
  public abstract net.minecraft.core.BlockPos getHomeBuilding();
  public abstract float getCombatCooldown();
}
