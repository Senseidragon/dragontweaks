Compiled from "ExtraFaceData.java"
public final class net.neoforged.neoforge.client.model.ExtraFaceData extends java.lang.Record {
  public static final net.neoforged.neoforge.client.model.ExtraFaceData DEFAULT;
  public static final com.mojang.serialization.Codec<java.lang.Integer> COLOR;
  public static final com.mojang.serialization.Codec<net.neoforged.neoforge.client.model.ExtraFaceData> CODEC;
  public net.neoforged.neoforge.client.model.ExtraFaceData(int, int, int, boolean);
  public static net.neoforged.neoforge.client.model.ExtraFaceData read(com.google.gson.JsonElement, net.neoforged.neoforge.client.model.ExtraFaceData) throws com.google.gson.JsonParseException;
  public final java.lang.String toString();
  public final int hashCode();
  public final boolean equals(java.lang.Object);
  public int color();
  public int blockLight();
  public int skyLight();
  public boolean ambientOcclusion();
}
