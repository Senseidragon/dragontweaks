Compiled from "ITickingStateAI.java"
public interface com.minecolonies.api.entity.ai.ITickingStateAI {
  public abstract void tick();
  public abstract com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickRateStateMachine<com.minecolonies.api.entity.ai.statemachine.states.IAIState> getStateAI();
  public abstract void onRemoval();
  public abstract void resetAI();
  public abstract com.minecolonies.api.entity.ai.statemachine.states.IState getState();
}
