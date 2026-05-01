Compiled from "EventBus.java"
public interface com.minecolonies.api.eventbus.EventBus {
  public abstract <T extends com.minecolonies.api.eventbus.IModEvent> void subscribe(java.lang.Class<T>, com.minecolonies.api.eventbus.EventBus$EventHandler<T>);
  public abstract void post(com.minecolonies.api.eventbus.IModEvent);
}
