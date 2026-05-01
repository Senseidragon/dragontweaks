Compiled from "InsertingContents.java"
public final class net.neoforged.neoforge.common.util.InsertingContents extends java.lang.Record implements net.minecraft.network.chat.ComponentContents {
  public static final com.mojang.serialization.MapCodec<net.neoforged.neoforge.common.util.InsertingContents> CODEC;
  public static final net.minecraft.network.chat.ComponentContents$Type<net.neoforged.neoforge.common.util.InsertingContents> TYPE;
  public net.neoforged.neoforge.common.util.InsertingContents(int);
  public static boolean pushTranslation(net.minecraft.network.chat.contents.TranslatableContents);
  public static void popTranslation();
  public <T> java.util.Optional<T> visit(net.minecraft.network.chat.FormattedText$ContentConsumer<T>);
  public <T> java.util.Optional<T> visit(net.minecraft.network.chat.FormattedText$StyledContentConsumer<T>, net.minecraft.network.chat.Style);
  public net.minecraft.network.chat.ComponentContents$Type<?> type();
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int index();
}
