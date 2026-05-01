Compiled from "IBlockSettingFactory.java"
public interface com.minecolonies.api.colony.buildings.modules.settings.IBlockSettingFactory<T extends com.minecolonies.api.colony.buildings.modules.settings.ISetting> extends com.minecolonies.api.colony.requestsystem.factory.IFactory<com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, T> {
  public default T getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, com.minecolonies.api.colony.requestsystem.factory.FactoryVoidInput, java.lang.Object...);
  public abstract T getNewInstance(net.minecraft.world.item.BlockItem, net.minecraft.world.item.BlockItem);
  public default java.lang.Object getNewInstance(com.minecolonies.api.colony.requestsystem.factory.IFactoryController, java.lang.Object, java.lang.Object[]) throws java.lang.IllegalArgumentException;
}
