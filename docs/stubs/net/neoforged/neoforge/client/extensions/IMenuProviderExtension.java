Compiled from "IMenuProviderExtension.java"
public interface net.neoforged.neoforge.client.extensions.IMenuProviderExtension {
  public default boolean shouldTriggerClientSideContainerClosingOnOpen();
  public default void writeClientSideData(net.minecraft.world.inventory.AbstractContainerMenu, net.minecraft.network.RegistryFriendlyByteBuf);
}
