Compiled from "ITickRateStateMachine.java"
public interface com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickRateStateMachine<S extends com.minecolonies.api.entity.ai.statemachine.states.IState> extends com.minecolonies.api.entity.ai.statemachine.basestatemachine.IStateMachine<com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickingTransition<S>, S> {
  public abstract void tick();
  public abstract boolean checkTransition(com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickingTransition<S>);
  public abstract int getTickRate();
  public abstract void setTickRate(int);
  public abstract void setCurrentDelay(int);
  public default boolean checkTransition(com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition);
}
