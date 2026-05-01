Compiled from "ITimeBasedHappinessModifier.java"
public interface com.minecolonies.api.entity.citizen.happiness.ITimeBasedHappinessModifier extends com.minecolonies.api.entity.citizen.happiness.IHappinessModifier {
  public default void dayEnd(com.minecolonies.api.colony.ICitizenData);
  public default void reset();
  public abstract int getDays();
}
