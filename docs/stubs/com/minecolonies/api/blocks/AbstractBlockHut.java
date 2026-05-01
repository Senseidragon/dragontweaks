Compiled from "AbstractBlockHut.java"
public abstract class com.minecolonies.api.blocks.AbstractBlockHut<B extends com.minecolonies.api.blocks.AbstractBlockHut<B>> extends com.minecolonies.api.blocks.AbstractColonyBlock<B> implements com.ldtteam.structurize.blocks.interfaces.IAnchorBlock, com.ldtteam.structurize.blocks.interfaces.INamedBlueprintAnchorBlock, com.ldtteam.structurize.blocks.interfaces.ILeveledBlueprintAnchorBlock, com.ldtteam.structurize.blocks.interfaces.IRequirementsBlueprintAnchorBlock, com.ldtteam.structurize.blocks.interfaces.IInvisibleBlueprintAnchorBlock, com.ldtteam.structurize.blocks.interfaces.ISpecialCreativeHandlerAnchorBlock, com.minecolonies.api.blocks.interfaces.IBuildingBrowsableBlock {
  public com.minecolonies.api.blocks.AbstractBlockHut();
  public com.minecolonies.api.blocks.AbstractBlockHut(net.minecraft.world.level.block.state.BlockBehaviour$Properties);
  public void onBlockPlacedByBuildTool(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState, net.minecraft.world.entity.LivingEntity, net.minecraft.world.item.ItemStack, com.ldtteam.structurize.api.RotationMirror, java.lang.String, java.lang.String);
  public boolean isVisible(net.minecraft.nbt.CompoundTag);
  public java.util.List<net.minecraft.network.chat.MutableComponent> getRequirements(net.minecraft.client.multiplayer.ClientLevel, net.minecraft.core.BlockPos, net.minecraft.client.player.LocalPlayer);
  public boolean areRequirementsMet(net.minecraft.client.multiplayer.ClientLevel, net.minecraft.core.BlockPos, net.minecraft.client.player.LocalPlayer);
  public java.util.List<net.minecraft.network.chat.MutableComponent> getDesc();
  public net.minecraft.network.chat.Component getBlueprintDisplayName();
  public int getLevel(net.minecraft.nbt.CompoundTag);
  public com.ldtteam.structurize.placement.structure.AbstractStructureHandler getStructureHandler(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, com.ldtteam.structurize.blueprints.v1.Blueprint, com.ldtteam.structurize.api.RotationMirror, boolean);
  public boolean setup(net.minecraft.server.level.ServerPlayer, net.minecraft.world.level.Level, net.minecraft.core.BlockPos, com.ldtteam.structurize.blueprints.v1.Blueprint, com.ldtteam.structurize.api.RotationMirror, boolean, java.lang.String, java.lang.String);
  public java.lang.String getBlueprintName();
  public void appendHoverText(net.minecraft.world.item.ItemStack, net.minecraft.world.item.Item$TooltipContext, java.util.List<net.minecraft.network.chat.Component>, net.minecraft.world.item.TooltipFlag);
  public void registerBlockItem(net.minecraft.core.Registry<net.minecraft.world.item.Item>, net.minecraft.world.item.Item$Properties);
  public boolean canRightClickWithoutPermissions();
  public boolean canPlaceAt(net.minecraft.core.BlockPos, net.minecraft.world.entity.player.Player);
}
