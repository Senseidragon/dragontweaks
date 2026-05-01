Compiled from "AnimalTameEvent.java"
public class net.neoforged.neoforge.event.entity.living.AnimalTameEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.AnimalTameEvent(net.minecraft.world.entity.animal.Animal, net.minecraft.world.entity.player.Player);
  public net.minecraft.world.entity.animal.Animal getAnimal();
  public net.minecraft.world.entity.player.Player getTamer();
}
