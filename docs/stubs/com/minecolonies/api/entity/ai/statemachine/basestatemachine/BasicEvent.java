Compiled from "BasicEvent.java"
public class com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicEvent extends com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicTransition<com.minecolonies.api.entity.ai.statemachine.states.IAIState> implements com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineEvent<com.minecolonies.api.entity.ai.statemachine.states.IAIState> {
  public com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicEvent(com.minecolonies.api.entity.ai.statemachine.states.IStateEventType, com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.IBooleanConditionSupplier, com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.IStateSupplier<com.minecolonies.api.entity.ai.statemachine.states.IAIState>);
  public com.minecolonies.api.entity.ai.statemachine.states.IStateEventType getEventType();
  public final com.minecolonies.api.entity.ai.statemachine.states.IAIState getState();
  public com.minecolonies.api.entity.ai.statemachine.states.IState getState();
}
