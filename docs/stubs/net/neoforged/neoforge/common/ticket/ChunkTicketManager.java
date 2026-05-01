Compiled from "ChunkTicketManager.java"
public class net.neoforged.neoforge.common.ticket.ChunkTicketManager<T> implements net.neoforged.neoforge.common.ticket.ITicketGetter<T> {
  public final net.minecraft.world.level.ChunkPos pos;
  public net.neoforged.neoforge.common.ticket.ChunkTicketManager(net.minecraft.world.level.ChunkPos);
  public void add(net.neoforged.neoforge.common.ticket.SimpleTicket<T>);
  public void remove(net.neoforged.neoforge.common.ticket.SimpleTicket<T>);
  public java.util.Collection<net.neoforged.neoforge.common.ticket.SimpleTicket<T>> getTickets();
}
