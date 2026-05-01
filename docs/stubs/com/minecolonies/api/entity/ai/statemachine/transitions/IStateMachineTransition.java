Compiled from "IStateMachineTransition.java"
public interface com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition<S extends com.minecolonies.api.entity.ai.statemachine.states.IState> {
  public abstract S getState();
  public abstract S getNextState();
  public abstract boolean checkCondition();
  public abstract net.minecraft.network.chat.Component getName();
  public abstract com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition withName(java.lang.String);
}
