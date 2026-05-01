Compiled from "ICitizenSleepHandler.java"
public interface com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenSleepHandler {
  public abstract boolean isAsleep();
  public abstract boolean trySleep(net.minecraft.core.BlockPos);
  public abstract void onWakeUp();
  public abstract net.minecraft.core.BlockPos getBedLocation();
  public abstract boolean shouldGoSleep();
}
