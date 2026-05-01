Compiled from "TicketHelper.java"
public class net.neoforged.neoforge.common.world.chunk.TicketHelper {
  public java.util.Map<net.minecraft.core.BlockPos, net.neoforged.neoforge.common.world.chunk.TicketSet> getBlockTickets();
  public java.util.Map<java.util.UUID, net.neoforged.neoforge.common.world.chunk.TicketSet> getEntityTickets();
  public void removeAllTickets(net.minecraft.core.BlockPos);
  public void removeAllTickets(java.util.UUID);
  public void removeTicket(net.minecraft.core.BlockPos, long, boolean);
  public void removeTicket(java.util.UUID, long, boolean);
}
