Compiled from "BasicStateMachine.java"
public class com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicStateMachine<T extends com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition<S>, S extends com.minecolonies.api.entity.ai.statemachine.states.IState> implements com.minecolonies.api.entity.ai.statemachine.basestatemachine.IStateMachine<T, S> {
  public void addTransition(T);
  public void addTransitionGroup(java.util.List<S>, T);
  public void removeTransition(T);
  public void tick();
  public boolean checkTransition(T);
  public boolean transitionToNext(T);
  public final S getState();
  public void reset();
  public void setHistoryEnabled(boolean, int);
  public net.minecraft.network.chat.Component getHistory();
}
