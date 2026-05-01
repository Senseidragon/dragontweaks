Compiled from "IMinecartCollisionHandler.java"
public interface net.neoforged.neoforge.common.IMinecartCollisionHandler {
  public abstract void onEntityCollision(net.minecraft.world.entity.vehicle.AbstractMinecart, net.minecraft.world.entity.Entity);
  public abstract net.minecraft.world.phys.AABB getCollisionBox(net.minecraft.world.entity.vehicle.AbstractMinecart, net.minecraft.world.entity.Entity);
  public abstract net.minecraft.world.phys.AABB getMinecartCollisionBox(net.minecraft.world.entity.vehicle.AbstractMinecart);
  public abstract net.minecraft.world.phys.AABB getBoundingBox(net.minecraft.world.entity.vehicle.AbstractMinecart);
}
