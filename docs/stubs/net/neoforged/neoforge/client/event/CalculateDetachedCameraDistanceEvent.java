Compiled from "CalculateDetachedCameraDistanceEvent.java"
public class net.neoforged.neoforge.client.event.CalculateDetachedCameraDistanceEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.CalculateDetachedCameraDistanceEvent(net.minecraft.client.Camera, boolean, float, float);
  public net.minecraft.client.Camera getCamera();
  public boolean isCameraFlipped();
  public float getEntityScalingFactor();
  public float getDistance();
  public void setDistance(float);
}
