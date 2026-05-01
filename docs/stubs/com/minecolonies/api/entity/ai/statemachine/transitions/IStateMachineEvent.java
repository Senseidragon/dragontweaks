Compiled from "IStateMachineEvent.java"
public interface com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineEvent<S extends com.minecolonies.api.entity.ai.statemachine.states.IState> extends com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition<S> {
  public abstract com.minecolonies.api.entity.ai.statemachine.states.IStateEventType getEventType();
}
