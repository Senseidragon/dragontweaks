Compiled from "IQuadTransformer.java"
public interface net.neoforged.neoforge.client.model.IQuadTransformer {
  public static final int STRIDE;
  public static final int POSITION;
  public static final int COLOR;
  public static final int UV0;
  public static final int UV1;
  public static final int UV2;
  public static final int NORMAL;
  public abstract void processInPlace(net.minecraft.client.renderer.block.model.BakedQuad);
  public default void processInPlace(java.util.List<net.minecraft.client.renderer.block.model.BakedQuad>);
  public default net.minecraft.client.renderer.block.model.BakedQuad process(net.minecraft.client.renderer.block.model.BakedQuad);
  public default java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> process(java.util.List<net.minecraft.client.renderer.block.model.BakedQuad>);
  public default net.neoforged.neoforge.client.model.IQuadTransformer andThen(net.neoforged.neoforge.client.model.IQuadTransformer);
}
