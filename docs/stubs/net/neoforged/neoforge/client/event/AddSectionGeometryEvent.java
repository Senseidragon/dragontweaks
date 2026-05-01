Compiled from "AddSectionGeometryEvent.java"
public class net.neoforged.neoforge.client.event.AddSectionGeometryEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.AddSectionGeometryEvent(net.minecraft.core.BlockPos, net.minecraft.world.level.Level);
  public void addRenderer(net.neoforged.neoforge.client.event.AddSectionGeometryEvent$AdditionalSectionRenderer);
  public java.util.List<net.neoforged.neoforge.client.event.AddSectionGeometryEvent$AdditionalSectionRenderer> getAdditionalRenderers();
  public net.minecraft.core.BlockPos getSectionOrigin();
  public net.minecraft.world.level.Level getLevel();
}
