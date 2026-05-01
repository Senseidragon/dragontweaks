Compiled from "IMobAIRegistry.java"
public interface com.minecolonies.api.entity.mobs.registry.IMobAIRegistry {
  public static com.minecolonies.api.entity.mobs.registry.IMobAIRegistry getInstance();
  public abstract com.google.common.collect.Multimap<java.lang.Integer, net.minecraft.world.entity.ai.goal.Goal> getEntityAiTasksForMobs(com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster);
  public default com.minecolonies.api.entity.mobs.registry.IMobAIRegistry registerNewAiTaskForMobs(int, java.util.function.Function<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster, net.minecraft.world.entity.ai.goal.Goal>);
  public abstract com.minecolonies.api.entity.mobs.registry.IMobAIRegistry registerNewAiTaskForMobs(int, java.util.function.Function<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster, net.minecraft.world.entity.ai.goal.Goal>, java.util.function.Predicate<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster>);
  public abstract com.minecolonies.api.entity.mobs.registry.IMobAIRegistry registerNewStateAI(java.util.function.Function<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster, com.minecolonies.api.entity.ai.IStateAI>, java.util.function.Predicate<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster>);
  public abstract void applyToMob(com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster);
  public abstract com.google.common.collect.Multimap<java.lang.Integer, net.minecraft.world.entity.ai.goal.Goal> getEntityAiTargetTasksForMobs(com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster);
  public default com.minecolonies.api.entity.mobs.registry.IMobAIRegistry registerNewAiTargetTaskForMobs(int, java.util.function.Function<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster, net.minecraft.world.entity.ai.goal.Goal>);
  public abstract com.minecolonies.api.entity.mobs.registry.IMobAIRegistry registerNewAiTargetTaskForMobs(int, java.util.function.Function<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster, net.minecraft.world.entity.ai.goal.Goal>, java.util.function.Predicate<com.minecolonies.api.entity.mobs.AbstractEntityMinecoloniesMonster>);
}
