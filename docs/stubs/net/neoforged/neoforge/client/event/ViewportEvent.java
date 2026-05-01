Compiled from "ViewportEvent.java"
public abstract class net.neoforged.neoforge.client.event.ViewportEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.ViewportEvent(net.minecraft.client.renderer.GameRenderer, net.minecraft.client.Camera, double);
  public net.minecraft.client.renderer.GameRenderer getRenderer();
  public net.minecraft.client.Camera getCamera();
  public double getPartialTick();
}
