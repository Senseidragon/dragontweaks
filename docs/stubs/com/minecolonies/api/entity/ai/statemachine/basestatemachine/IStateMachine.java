Compiled from "IStateMachine.java"
public interface com.minecolonies.api.entity.ai.statemachine.basestatemachine.IStateMachine<T extends com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition<S>, S extends com.minecolonies.api.entity.ai.statemachine.states.IState> {
  public abstract void addTransition(T);
  public abstract void addTransitionGroup(java.util.List<S>, T);
  public abstract void removeTransition(T);
  public abstract void tick();
  public abstract boolean checkTransition(T);
  public abstract boolean transitionToNext(T);
  public abstract S getState();
  public abstract void reset();
  public abstract void setHistoryEnabled(boolean, int);
  public abstract net.minecraft.network.chat.Component getHistory();
}
