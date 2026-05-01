Compiled from "ClientCommandSourceStack.java"
public class net.neoforged.neoforge.client.ClientCommandSourceStack extends net.minecraft.commands.CommandSourceStack {
  public net.neoforged.neoforge.client.ClientCommandSourceStack(net.minecraft.commands.CommandSource, net.minecraft.world.phys.Vec3, net.minecraft.world.phys.Vec2, int, java.lang.String, net.minecraft.network.chat.Component, net.minecraft.world.entity.Entity);
  public void sendSuccess(java.util.function.Supplier<net.minecraft.network.chat.Component>, boolean);
  public java.util.Collection<java.lang.String> getAllTeams();
  public java.util.Collection<java.lang.String> getOnlinePlayerNames();
  public java.util.stream.Stream<net.minecraft.resources.ResourceLocation> getRecipeNames();
  public java.util.Set<net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level>> levels();
  public net.minecraft.core.RegistryAccess registryAccess();
  public net.minecraft.world.scores.Scoreboard getScoreboard();
  public net.minecraft.advancements.AdvancementHolder getAdvancement(net.minecraft.resources.ResourceLocation);
  public net.minecraft.world.item.crafting.RecipeManager getRecipeManager();
  public net.minecraft.world.level.Level getUnsidedLevel();
  public net.minecraft.server.MinecraftServer getServer();
  public net.minecraft.server.level.ServerLevel getLevel();
}
