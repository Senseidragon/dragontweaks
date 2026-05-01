Compiled from "IStringSetting.java"
public interface com.minecolonies.api.colony.buildings.modules.settings.IStringSetting<S> extends com.minecolonies.api.colony.buildings.modules.settings.ISetting<S> {
  public abstract S getValue();
  public abstract S getDefault();
  public abstract int getCurrentIndex();
  public abstract java.util.List<java.lang.String> getSettings();
  public abstract void set(S);
}
