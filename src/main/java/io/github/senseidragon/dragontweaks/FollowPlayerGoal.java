package io.github.senseidragon.dragontweaks;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.PathType;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class FollowPlayerGoal extends Goal {

    private final AssistantEntity entity;
    private final PathNavigation navigation;
    private final double speedModifier;
    private final float startDistance;
    private final float stopDistance;

    @Nullable
    private Player target;
    private int timeToRecalcPath;
    private float oldWaterCost;

    public FollowPlayerGoal(AssistantEntity entity, double speedModifier, float startDistance, float stopDistance) {
        this.entity = entity;
        this.navigation = entity.getNavigation();
        this.speedModifier = speedModifier;
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!entity.isFollowing()) return false;
        List<Player> nearby = entity.level().getEntitiesOfClass(
                Player.class,
                entity.getBoundingBox().inflate(64),
                p -> !p.isSpectator() && !p.isCreative() || true
        );
        // isCreative filter intentionally omitted — follow in all modes
        Player nearest = null;
        double nearestDist = Double.MAX_VALUE;
        for (Player p : nearby) {
            double dist = entity.distanceToSqr(p);
            if (dist < nearestDist) {
                nearestDist = dist;
                nearest = p;
            }
        }
        if (nearest == null) return false;
        if (nearestDist < startDistance * startDistance) return false;
        target = nearest;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (!entity.isFollowing()) return false;
        if (target == null) return false;
        if (navigation.isDone()) return false;
        return entity.distanceToSqr(target) > stopDistance * stopDistance;
    }

    @Override
    public void start() {
        timeToRecalcPath = 0;
        oldWaterCost = entity.getPathfindingMalus(PathType.WATER);
        entity.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public void stop() {
        target = null;
        navigation.stop();
        entity.setPathfindingMalus(PathType.WATER, oldWaterCost);
    }

    @Override
    public void tick() {
        if (target == null) return;
        entity.getLookControl().setLookAt(target, 10.0F, (float) entity.getMaxHeadXRot());
        if (--timeToRecalcPath <= 0) {
            timeToRecalcPath = adjustedTickDelay(10);
            double distSq = entity.distanceToSqr(target);
            if (distSq > stopDistance * stopDistance) {
                navigation.moveTo(target, speedModifier);
            } else {
                navigation.stop();
            }
        }
    }
}
