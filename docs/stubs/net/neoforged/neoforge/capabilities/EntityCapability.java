Compiled from "EntityCapability.java"
public final class net.neoforged.neoforge.capabilities.EntityCapability<T, C> extends net.neoforged.neoforge.capabilities.BaseCapability<T, C> {
  public static <T, C> net.neoforged.neoforge.capabilities.EntityCapability<T, C> create(net.minecraft.resources.ResourceLocation, java.lang.Class<T>, java.lang.Class<C>);
  public static <T> net.neoforged.neoforge.capabilities.EntityCapability<T, java.lang.Void> createVoid(net.minecraft.resources.ResourceLocation, java.lang.Class<T>);
  public static <T> net.neoforged.neoforge.capabilities.EntityCapability<T, net.minecraft.core.Direction> createSided(net.minecraft.resources.ResourceLocation, java.lang.Class<T>);
  public static synchronized java.util.List<net.neoforged.neoforge.capabilities.EntityCapability<?, ?>> getAll();
  public T getCapability(net.minecraft.world.entity.Entity, C);
}
