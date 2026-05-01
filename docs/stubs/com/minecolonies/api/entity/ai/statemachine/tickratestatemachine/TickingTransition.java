Compiled from "TickingTransition.java"
public class com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.TickingTransition<S extends com.minecolonies.api.entity.ai.statemachine.states.IState> extends com.minecolonies.api.entity.ai.statemachine.basestatemachine.BasicTransition<S> implements com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickingTransition<S> {
  public com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.TickingTransition(S, com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.IBooleanConditionSupplier, com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.IStateSupplier<S>, int);
  public com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.TickingTransition(com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.IBooleanConditionSupplier, com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.IStateSupplier<S>, int);
  public int getTickRate();
  public void setTickRate(int);
  public int countdownTicksToUpdate(int);
  public void setTicksToUpdate(int);
}
