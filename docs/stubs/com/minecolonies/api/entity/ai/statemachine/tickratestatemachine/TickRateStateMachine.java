Compiled from "TickRateStateMachine.java"
public class com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.TickRateStateMachine<S extends com.minecolonies.api.entity.ai.statemachine.states.IState> extends com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicStateMachine<com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickingTransition<S>, S> implements com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickRateStateMachine<S> {
  public static double slownessFactor;
  public com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.TickRateStateMachine(S, java.util.function.Consumer<java.lang.RuntimeException>);
  public com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.TickRateStateMachine(S, java.util.function.Consumer<java.lang.RuntimeException>, int);
  public void tick();
  public boolean checkTransition(com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickingTransition<S>);
  public int getTickRate();
  public void setTickRate(int);
  public void setCurrentDelay(int);
  public boolean checkTransition(com.minecolonies.api.entity.ai.statemachine.transitions.IStateMachineTransition);
}
