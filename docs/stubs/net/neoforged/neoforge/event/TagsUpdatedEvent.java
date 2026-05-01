Compiled from "TagsUpdatedEvent.java"
public class net.neoforged.neoforge.event.TagsUpdatedEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.TagsUpdatedEvent(net.minecraft.core.RegistryAccess, boolean, boolean);
  public net.minecraft.core.RegistryAccess getRegistryAccess();
  public net.neoforged.neoforge.event.TagsUpdatedEvent$UpdateCause getUpdateCause();
  public boolean shouldUpdateStaticData();
}
