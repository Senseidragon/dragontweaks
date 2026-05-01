Compiled from "FakePlayer.java"
public class net.neoforged.neoforge.common.util.FakePlayer extends net.minecraft.server.level.ServerPlayer {
  public net.neoforged.neoforge.common.util.FakePlayer(net.minecraft.server.level.ServerLevel, com.mojang.authlib.GameProfile);
  public void displayClientMessage(net.minecraft.network.chat.Component, boolean);
  public void awardStat(net.minecraft.stats.Stat<?>, int);
  public boolean isInvulnerableTo(net.minecraft.world.damagesource.DamageSource);
  public boolean canHarmPlayer(net.minecraft.world.entity.player.Player);
  public void die(net.minecraft.world.damagesource.DamageSource);
  public void tick();
  public void updateOptions(net.minecraft.server.level.ClientInformation);
  public java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider, java.util.function.Consumer<net.minecraft.network.RegistryFriendlyByteBuf>);
  public void openHorseInventory(net.minecraft.world.entity.animal.horse.AbstractHorse, net.minecraft.world.Container);
  public boolean startRiding(net.minecraft.world.entity.Entity, boolean);
  public net.minecraft.server.MinecraftServer getServer();
  public boolean isFakePlayer();
}
