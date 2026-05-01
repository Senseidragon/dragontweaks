Compiled from "IBuildingEventsModule.java"
public interface com.minecolonies.api.colony.buildings.modules.IBuildingEventsModule extends com.minecolonies.api.colony.buildings.modules.IBuildingModule {
  public default void onDestroyed();
  public default void onUpgradeComplete(int);
  public default void onWakeUp();
  public default void onPlayerEnterBuilding(net.minecraft.world.entity.player.Player);
}
