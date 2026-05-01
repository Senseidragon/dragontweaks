Compiled from "WandererTradesEvent.java"
public class net.neoforged.neoforge.event.village.WandererTradesEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.village.WandererTradesEvent(java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing>, java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing>);
  public net.neoforged.neoforge.event.village.WandererTradesEvent(java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing>, java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing>, net.minecraft.core.RegistryAccess);
  public java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing> getGenericTrades();
  public java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing> getRareTrades();
  public net.minecraft.core.RegistryAccess getRegistryAccess();
}
