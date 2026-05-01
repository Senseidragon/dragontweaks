Compiled from "AddReloadListenerEvent.java"
public class net.neoforged.neoforge.event.AddReloadListenerEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.AddReloadListenerEvent(net.minecraft.server.ReloadableServerResources, net.minecraft.core.RegistryAccess);
  public void addListener(net.minecraft.server.packs.resources.PreparableReloadListener);
  public java.util.List<net.minecraft.server.packs.resources.PreparableReloadListener> getListeners();
  public net.minecraft.server.ReloadableServerResources getServerResources();
  public net.neoforged.neoforge.common.conditions.ICondition$IContext getConditionContext();
  public net.minecraft.core.RegistryAccess getRegistryAccess();
}
