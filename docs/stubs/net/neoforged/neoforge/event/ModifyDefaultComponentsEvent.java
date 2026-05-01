Compiled from "ModifyDefaultComponentsEvent.java"
public final class net.neoforged.neoforge.event.ModifyDefaultComponentsEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.event.ModifyDefaultComponentsEvent();
  public void modify(net.minecraft.world.level.ItemLike, java.util.function.Consumer<net.minecraft.core.component.DataComponentPatch$Builder>);
  public void modifyMatching(java.util.function.Predicate<? super net.minecraft.world.item.Item>, java.util.function.Consumer<net.minecraft.core.component.DataComponentPatch$Builder>);
  public java.util.stream.Stream<net.minecraft.world.item.Item> getAllItems();
}
