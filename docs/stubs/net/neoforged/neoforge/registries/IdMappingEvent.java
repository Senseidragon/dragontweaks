Compiled from "IdMappingEvent.java"
public class net.neoforged.neoforge.registries.IdMappingEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.registries.IdMappingEvent(java.util.Map<net.minecraft.resources.ResourceLocation, java.util.Map<net.minecraft.resources.ResourceLocation, net.neoforged.neoforge.registries.IdMappingEvent$IdRemapping>>, boolean);
  public com.google.common.collect.ImmutableSet<net.minecraft.resources.ResourceLocation> getRegistries();
  public com.google.common.collect.ImmutableList<net.neoforged.neoforge.registries.IdMappingEvent$ModRemapping> getRemaps(net.minecraft.resources.ResourceLocation);
  public boolean isFrozen();
}
