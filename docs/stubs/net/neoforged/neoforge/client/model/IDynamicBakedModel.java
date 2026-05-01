Compiled from "IDynamicBakedModel.java"
public interface net.neoforged.neoforge.client.model.IDynamicBakedModel extends net.minecraft.client.resources.model.BakedModel {
  public default java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> getQuads(net.minecraft.world.level.block.state.BlockState, net.minecraft.core.Direction, net.minecraft.util.RandomSource);
  public abstract java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> getQuads(net.minecraft.world.level.block.state.BlockState, net.minecraft.core.Direction, net.minecraft.util.RandomSource, net.neoforged.neoforge.client.model.data.ModelData, net.minecraft.client.renderer.RenderType);
}
