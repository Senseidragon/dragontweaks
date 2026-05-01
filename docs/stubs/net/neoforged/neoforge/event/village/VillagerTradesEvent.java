Compiled from "VillagerTradesEvent.java"
public class net.neoforged.neoforge.event.village.VillagerTradesEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.event.village.VillagerTradesEvent(it.unimi.dsi.fastutil.ints.Int2ObjectMap<java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing>>, net.minecraft.world.entity.npc.VillagerProfession);
  public net.neoforged.neoforge.event.village.VillagerTradesEvent(it.unimi.dsi.fastutil.ints.Int2ObjectMap<java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing>>, net.minecraft.world.entity.npc.VillagerProfession, net.minecraft.core.RegistryAccess);
  public it.unimi.dsi.fastutil.ints.Int2ObjectMap<java.util.List<net.minecraft.world.entity.npc.VillagerTrades$ItemListing>> getTrades();
  public net.minecraft.world.entity.npc.VillagerProfession getType();
  public net.minecraft.core.RegistryAccess getRegistryAccess();
}
