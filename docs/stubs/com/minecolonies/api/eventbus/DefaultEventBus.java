Compiled from "DefaultEventBus.java"
public class com.minecolonies.api.eventbus.DefaultEventBus implements com.minecolonies.api.eventbus.EventBus {
  public com.minecolonies.api.eventbus.DefaultEventBus();
  public <T extends com.minecolonies.api.eventbus.IModEvent> void subscribe(java.lang.Class<T>, com.minecolonies.api.eventbus.EventBus$EventHandler<T>);
  public void post(com.minecolonies.api.eventbus.IModEvent);
}
