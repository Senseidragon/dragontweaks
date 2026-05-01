Compiled from "LootTableLoadEvent.java"
public class net.neoforged.neoforge.event.LootTableLoadEvent extends net.neoforged.bus.api.Event implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.LootTableLoadEvent(net.minecraft.resources.ResourceLocation, net.minecraft.world.level.storage.loot.LootTable);
  public net.neoforged.neoforge.event.LootTableLoadEvent(net.minecraft.core.HolderLookup$Provider, net.minecraft.resources.ResourceLocation, net.minecraft.world.level.storage.loot.LootTable);
  public net.minecraft.core.HolderLookup$Provider getRegistries();
  public net.minecraft.resources.ResourceLocation getName();
  public net.minecraft.resources.ResourceKey<net.minecraft.world.level.storage.loot.LootTable> getKey();
  public net.minecraft.world.level.storage.loot.LootTable getTable();
  public void setTable(net.minecraft.world.level.storage.loot.LootTable);
}
