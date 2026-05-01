Compiled from "EntityUtils.java"
public final class com.minecolonies.api.util.EntityUtils {
  public static net.minecraft.world.entity.player.Player getPlayerOfFakePlayer(net.minecraft.world.entity.player.Player, net.minecraft.world.level.Level);
  public static net.minecraft.world.entity.Entity getPlayerByUUID(net.minecraft.world.level.Level, java.util.UUID);
  public static java.util.List<net.minecraft.world.entity.Entity> getEntitiesFromID(net.minecraft.world.level.Level, java.util.List<java.lang.Integer>);
  public static double updateRotation(double, double, double);
  public static boolean checkForFreeSpace(net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public static boolean solidOrLiquid(net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public static net.minecraft.core.BlockPos getSpawnPoint(net.minecraft.world.level.Level, net.minecraft.core.BlockPos);
  public static boolean tryMoveLivingToXYZ(net.minecraft.world.entity.Mob, int, int, int);
  public static boolean tryMoveLivingToXYZ(net.minecraft.world.entity.Mob, int, int, int, double);
  public static boolean isLivingAtSiteWithMove(net.minecraft.world.entity.LivingEntity, int, int, int, int);
  public static boolean isLivingAtSite(net.minecraft.world.entity.LivingEntity, int, int, int, int);
  public static boolean isFlying(net.minecraft.world.entity.LivingEntity);
  public static java.util.function.Predicate<net.minecraft.world.entity.Entity> pushableBy();
}
