Compiled from "SimpleTicket.java"
public abstract class net.neoforged.neoforge.common.ticket.SimpleTicket<T> {
  public net.neoforged.neoforge.common.ticket.SimpleTicket();
  public final void setManager(net.neoforged.neoforge.common.ticket.ITicketManager<T>, net.neoforged.neoforge.common.ticket.ITicketManager<T>...);
  public boolean isValid();
  public void invalidate();
  public boolean unload(net.neoforged.neoforge.common.ticket.ITicketManager<T>);
  public void validate();
  public abstract boolean matches(T);
}
