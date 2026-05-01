Compiled from "PartEntity.java"
public abstract class net.neoforged.neoforge.entity.PartEntity<T extends net.minecraft.world.entity.Entity> extends net.minecraft.world.entity.Entity {
  public net.neoforged.neoforge.entity.PartEntity(T);
  public T getParent();
  public net.minecraft.network.protocol.Packet<net.minecraft.network.protocol.game.ClientGamePacketListener> getAddEntityPacket(net.minecraft.server.level.ServerEntity);
}
