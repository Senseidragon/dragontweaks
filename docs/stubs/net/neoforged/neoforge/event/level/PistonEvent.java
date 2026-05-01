Compiled from "PistonEvent.java"
public abstract class net.neoforged.neoforge.event.level.PistonEvent extends net.neoforged.neoforge.event.level.BlockEvent {
  public net.neoforged.neoforge.event.level.PistonEvent(net.minecraft.world.level.Level, net.minecraft.core.BlockPos, net.minecraft.core.Direction, net.neoforged.neoforge.event.level.PistonEvent$PistonMoveType);
  public net.minecraft.core.Direction getDirection();
  public net.minecraft.core.BlockPos getFaceOffsetPos();
  public net.neoforged.neoforge.event.level.PistonEvent$PistonMoveType getPistonMoveType();
  public net.minecraft.world.level.block.piston.PistonStructureResolver getStructureHelper();
}
