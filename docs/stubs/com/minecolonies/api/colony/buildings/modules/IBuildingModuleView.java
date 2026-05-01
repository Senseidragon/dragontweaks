Compiled from "IBuildingModuleView.java"
public interface com.minecolonies.api.colony.buildings.modules.IBuildingModuleView {
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract com.minecolonies.api.colony.buildings.modules.IBuildingModuleView setBuildingView(com.minecolonies.api.colony.buildings.views.IBuildingView);
  public default boolean isPageVisible();
  public abstract com.ldtteam.blockui.views.BOWindow getWindow();
  public default java.lang.String getIcon();
  public default net.minecraft.resources.ResourceLocation getIconResourceLocation();
  public abstract net.minecraft.network.chat.Component getDesc();
  public abstract com.minecolonies.api.colony.buildings.modules.IBuildingModuleView setColonyView(com.minecolonies.api.colony.IColonyView);
  public abstract com.minecolonies.api.colony.IColonyView getColony();
  public abstract com.minecolonies.api.colony.buildings.views.IBuildingView getBuildingView();
  public abstract <M extends com.minecolonies.api.colony.buildings.modules.IBuildingModule, V extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> com.minecolonies.api.colony.buildings.modules.IBuildingModuleView setProducer(com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer<M, V>);
  public abstract <M extends com.minecolonies.api.colony.buildings.modules.IBuildingModule, V extends com.minecolonies.api.colony.buildings.modules.IBuildingModuleView> com.minecolonies.api.colony.buildings.registry.BuildingEntry$ModuleProducer<M, V> getProducer();
}
