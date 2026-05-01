Compiled from "GlobalLootModifierProvider.java"
public abstract class net.neoforged.neoforge.common.data.GlobalLootModifierProvider implements net.minecraft.data.DataProvider {
  public net.neoforged.neoforge.common.data.GlobalLootModifierProvider(net.minecraft.data.PackOutput, java.util.concurrent.CompletableFuture<net.minecraft.core.HolderLookup$Provider>, java.lang.String);
  public final java.util.concurrent.CompletableFuture<?> run(net.minecraft.data.CachedOutput);
  public <T extends net.neoforged.neoforge.common.loot.IGlobalLootModifier> void add(java.lang.String, T, java.util.List<net.neoforged.neoforge.common.conditions.ICondition>);
  public <T extends net.neoforged.neoforge.common.loot.IGlobalLootModifier> void add(java.lang.String, T, net.neoforged.neoforge.common.conditions.ICondition...);
  public java.lang.String getName();
}
