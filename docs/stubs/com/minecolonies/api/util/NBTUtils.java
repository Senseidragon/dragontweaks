Compiled from "NBTUtils.java"
public class com.minecolonies.api.util.NBTUtils {
  public com.minecolonies.api.util.NBTUtils();
  public static java.util.stream.Stream<net.minecraft.nbt.CompoundTag> streamCompound(net.minecraft.nbt.ListTag);
  public static java.util.stream.Stream<net.minecraft.nbt.Tag> streamBase(net.minecraft.nbt.ListTag);
  public static java.util.stream.Collector<net.minecraft.nbt.Tag, ?, net.minecraft.nbt.ListTag> toListNBT();
  public static net.minecraft.core.BlockPos readBlockPos(net.minecraft.nbt.CompoundTag, java.lang.String);
  public static net.minecraft.core.BlockPos readBlockPos(net.minecraft.nbt.Tag);
  public static net.minecraft.nbt.Tag writeBlockPos(net.minecraft.core.BlockPos);
}
