Compiled from "LivingDropsEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingDropsEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingDropsEvent(net.minecraft.world.entity.LivingEntity, net.minecraft.world.damagesource.DamageSource, java.util.Collection<net.minecraft.world.entity.item.ItemEntity>, boolean);
  public net.minecraft.world.damagesource.DamageSource getSource();
  public java.util.Collection<net.minecraft.world.entity.item.ItemEntity> getDrops();
  public boolean isRecentlyHit();
}
