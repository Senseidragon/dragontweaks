Compiled from "LivingUseTotemEvent.java"
public class net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent extends net.neoforged.neoforge.event.entity.living.LivingEvent implements net.neoforged.bus.api.ICancellableEvent {
  public net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent(net.minecraft.world.entity.LivingEntity, net.minecraft.world.damagesource.DamageSource, net.minecraft.world.item.ItemStack, net.minecraft.world.InteractionHand);
  public net.minecraft.world.damagesource.DamageSource getSource();
  public net.minecraft.world.item.ItemStack getTotem();
  public net.minecraft.world.InteractionHand getHandHolding();
}
