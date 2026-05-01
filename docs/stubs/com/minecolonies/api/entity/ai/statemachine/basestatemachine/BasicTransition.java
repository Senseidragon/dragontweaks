Compiled from "BasicTransition.java"
public class com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicTransition<S extends com.minecolonies.api.entity.ai.statemachine.states.IState> implements com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition<S> {
  public com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicTransition(S, com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.IBooleanConditionSupplier, com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.IStateSupplier<S>);
  public S getState();
  public S getNextState();
  public boolean checkCondition();
  public net.minecraft.network.chat.Component getName();
  public static java.lang.String getMethodName(java.io.Serializable);
  public com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicTransition<S> withName(java.lang.String);
  public com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition withName(java.lang.String);
}
