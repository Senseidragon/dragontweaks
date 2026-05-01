Compiled from "ITickingTransition.java"
public interface com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickingTransition<S extends com.minecolonies.api.entity.ai.statemachine.states.IState> extends com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition<S> {
  public abstract int getTickRate();
  public abstract void setTickRate(int);
  public abstract int countdownTicksToUpdate(int);
  public abstract void setTicksToUpdate(int);
}
