Compiled from "BlockEntityTypeAddBlocksEvent.java"
public class net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent();
  public void modify(net.minecraft.world.level.block.entity.BlockEntityType<?>, net.minecraft.world.level.block.Block...);
  public void modify(net.minecraft.resources.ResourceKey<net.minecraft.world.level.block.entity.BlockEntityType<?>>, net.minecraft.world.level.block.Block...);
  public void modify(java.util.function.BiPredicate<net.minecraft.resources.ResourceKey<net.minecraft.world.level.block.entity.BlockEntityType<?>>, net.minecraft.world.level.block.entity.BlockEntityType<?>>, net.minecraft.world.level.block.Block...);
}
