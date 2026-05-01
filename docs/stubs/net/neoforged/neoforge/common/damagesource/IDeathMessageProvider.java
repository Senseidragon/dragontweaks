Compiled from "IDeathMessageProvider.java"
public interface net.neoforged.neoforge.common.damagesource.IDeathMessageProvider {
  public static final net.neoforged.neoforge.common.damagesource.IDeathMessageProvider DEFAULT;
  public abstract net.minecraft.network.chat.Component getDeathMessage(net.minecraft.world.entity.LivingEntity, net.minecraft.world.damagesource.CombatEntry, net.minecraft.world.damagesource.CombatEntry);
}
