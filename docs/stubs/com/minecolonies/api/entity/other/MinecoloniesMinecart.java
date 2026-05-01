Compiled from "MinecoloniesMinecart.java"
public class com.minecolonies.api.entity.other.MinecoloniesMinecart extends net.minecraft.world.entity.vehicle.Minecart {
  public com.minecolonies.api.entity.other.MinecoloniesMinecart(net.minecraft.world.entity.EntityType<?>, net.minecraft.world.level.Level);
  public void destroy(net.minecraft.world.damagesource.DamageSource);
  public net.minecraft.world.InteractionResult interact(net.minecraft.world.entity.player.Player, net.minecraft.world.InteractionHand);
  public boolean isPickable();
  public net.minecraft.world.entity.vehicle.AbstractMinecart$Type getMinecartType();
  public void push(net.minecraft.world.entity.Entity);
  public void playerTouch(net.minecraft.world.entity.player.Player);
  public boolean isPushable();
  public boolean canCollideWith(net.minecraft.world.entity.Entity);
  public void tick();
}
