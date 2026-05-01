Compiled from "ComputeFovModifierEvent.java"
public class net.neoforged.neoforge.client.event.ComputeFovModifierEvent extends net.neoforged.bus.api.Event {
  public net.neoforged.neoforge.client.event.ComputeFovModifierEvent(net.minecraft.world.entity.player.Player, float);
  public net.minecraft.world.entity.player.Player getPlayer();
  public float getFovModifier();
  public float getNewFovModifier();
  public void setNewFovModifier(float);
}
