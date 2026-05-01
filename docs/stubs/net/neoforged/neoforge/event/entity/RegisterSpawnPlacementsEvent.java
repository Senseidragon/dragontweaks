Compiled from "RegisterSpawnPlacementsEvent.java"
public class net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent(java.util.Map<net.minecraft.world.entity.EntityType<?>, net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent$MergedSpawnPredicate<?>>);
  public <T extends net.minecraft.world.entity.Entity> void register(net.minecraft.world.entity.EntityType<T>, net.minecraft.world.entity.SpawnPlacements$SpawnPredicate<T>);
  public <T extends net.minecraft.world.entity.Entity> void register(net.minecraft.world.entity.EntityType<T>, net.minecraft.world.entity.SpawnPlacements$SpawnPredicate<T>, net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent$Operation);
  public <T extends net.minecraft.world.entity.Entity> void register(net.minecraft.world.entity.EntityType<T>, net.minecraft.world.entity.SpawnPlacementType, net.minecraft.world.level.levelgen.Heightmap$Types, net.minecraft.world.entity.SpawnPlacements$SpawnPredicate<T>, net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent$Operation);
}
