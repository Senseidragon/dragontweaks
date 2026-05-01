Compiled from "Utils.java"
public final class com.minecolonies.api.util.Utils {
  public static boolean isBlockInRange(net.minecraft.world.level.Level, net.minecraft.world.level.block.Block, int, int, int, int);
  public static java.lang.String[] splitPath(java.lang.String);
  public static java.nio.file.Path resolvePath(java.nio.file.Path, java.lang.String);
  public static boolean testFlag(long, long);
  public static long mask(long, long);
  public static long setFlag(long, long);
  public static long unsetFlag(long, long);
  public static long toggleFlag(long, long);
  public static void checkDirectory(java.io.File);
  public static java.lang.String format(long);
  public static int getBlueprintLevel(java.lang.String);
  public static <T> net.minecraft.core.Holder<T> getRegistryValue(net.minecraft.resources.ResourceKey<T>, net.minecraft.world.level.Level);
  public static <T> net.minecraft.nbt.Tag serializeCodecMess(com.mojang.serialization.Codec<T>, net.minecraft.core.HolderLookup$Provider, T);
  public static <T> T deserializeCodecMess(com.mojang.serialization.Codec<T>, net.minecraft.core.HolderLookup$Provider, net.minecraft.nbt.Tag);
  public static <T> com.google.gson.JsonElement serializeCodecMessToJson(com.mojang.serialization.Codec<T>, net.minecraft.core.HolderLookup$Provider, T);
  public static <T> T deserializeCodecMessFromJson(com.mojang.serialization.Codec<T>, net.minecraft.core.HolderLookup$Provider, com.google.gson.JsonElement);
  public static <T> void serializeCodecMess(net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, T>, net.minecraft.network.RegistryFriendlyByteBuf, T);
  public static <T> T deserializeCodecMess(net.minecraft.network.codec.StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, T>, net.minecraft.network.RegistryFriendlyByteBuf);
  public static void serializeCodecMess(net.minecraft.network.RegistryFriendlyByteBuf, net.minecraft.world.item.ItemStack);
  public static net.minecraft.world.item.ItemStack deserializeCodecMess(net.minecraft.network.RegistryFriendlyByteBuf);
}
