Compiled from "ChunkRenderTypeSet.java"
public class net.neoforged.neoforge.client.ChunkRenderTypeSet implements java.lang.Iterable<net.minecraft.client.renderer.RenderType> {
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet none();
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet all();
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet of(net.minecraft.client.renderer.RenderType...);
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet of(java.util.Collection<net.minecraft.client.renderer.RenderType>);
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet union(net.neoforged.neoforge.client.ChunkRenderTypeSet...);
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet union(java.util.Collection<net.neoforged.neoforge.client.ChunkRenderTypeSet>);
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet union(java.lang.Iterable<net.neoforged.neoforge.client.ChunkRenderTypeSet>);
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet intersection(net.neoforged.neoforge.client.ChunkRenderTypeSet...);
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet intersection(java.util.Collection<net.neoforged.neoforge.client.ChunkRenderTypeSet>);
  public static net.neoforged.neoforge.client.ChunkRenderTypeSet intersection(java.lang.Iterable<net.neoforged.neoforge.client.ChunkRenderTypeSet>);
  public boolean isEmpty();
  public boolean contains(net.minecraft.client.renderer.RenderType);
  public java.util.Iterator<net.minecraft.client.renderer.RenderType> iterator();
  public java.util.List<net.minecraft.client.renderer.RenderType> asList();
}
