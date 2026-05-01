Compiled from "AddPackFindersEvent.java"
public class net.neoforged.neoforge.event.AddPackFindersEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.event.AddPackFindersEvent(net.minecraft.server.packs.PackType, java.util.function.Consumer<net.minecraft.server.packs.repository.RepositorySource>, boolean);
  public void addRepositorySource(net.minecraft.server.packs.repository.RepositorySource);
  public net.minecraft.server.packs.PackType getPackType();
  public void addPackFinders(net.minecraft.resources.ResourceLocation, net.minecraft.server.packs.PackType, net.minecraft.network.chat.Component, net.minecraft.server.packs.repository.PackSource, boolean, net.minecraft.server.packs.repository.Pack$Position);
  public boolean isTrusted();
}
