Compiled from "ModelData.java"
public final class net.neoforged.neoforge.client.model.data.ModelData {
  public static final net.neoforged.neoforge.client.model.data.ModelData EMPTY;
  public java.util.Set<net.neoforged.neoforge.client.model.data.ModelProperty<?>> getProperties();
  public boolean has(net.neoforged.neoforge.client.model.data.ModelProperty<?>);
  public <T> T get(net.neoforged.neoforge.client.model.data.ModelProperty<T>);
  public net.neoforged.neoforge.client.model.data.ModelData$Builder derive();
  public static net.neoforged.neoforge.client.model.data.ModelData$Builder builder();
  public static <T> net.neoforged.neoforge.client.model.data.ModelData of(net.neoforged.neoforge.client.model.data.ModelProperty<T>, T);
}
