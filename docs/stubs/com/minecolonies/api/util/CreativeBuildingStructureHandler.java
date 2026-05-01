Compiled from "CreativeBuildingStructureHandler.java"
public final class com.minecolonies.api.util.CreativeBuildingStructureHandler extends com.ldtteam.structurize.placement.structure.CreativeStructureHandler {
  public com.minecolonies.api.util.CreativeBuildingStructureHandler(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, com.ldtteam.structurize.blueprints.v1.Blueprint, com.ldtteam.structurize.api.RotationMirror, boolean);
  public com.minecolonies.api.util.CreativeBuildingStructureHandler(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, java.util.concurrent.Future<com.ldtteam.structurize.blueprints.v1.Blueprint>, com.ldtteam.structurize.api.RotationMirror, boolean);
  public void triggerSuccess(net.minecraft.core.BlockPos, java.util.List<net.minecraft.world.item.ItemStack>, boolean);
  public boolean isStackFree(net.minecraft.world.item.ItemStack);
  public static com.ldtteam.structurize.blueprints.v1.Blueprint loadAndPlaceStructureWithRotation(net.minecraft.world.level.Level, java.util.concurrent.Future<com.ldtteam.structurize.blueprints.v1.Blueprint>, net.minecraft.core.BlockPos, com.ldtteam.structurize.api.RotationMirror, boolean, net.minecraft.server.level.ServerPlayer);
  public void onCompletion();
}
