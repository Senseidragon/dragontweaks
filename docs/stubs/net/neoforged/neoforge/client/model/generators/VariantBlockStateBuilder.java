Compiled from "VariantBlockStateBuilder.java"
public class net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder implements net.neoforged.neoforge.client.model.generators.IGeneratedBlockState {
  public java.util.Map<net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder$PartialBlockstate, net.neoforged.neoforge.client.model.generators.BlockStateProvider$ConfiguredModelList> getModels();
  public net.minecraft.world.level.block.Block getOwner();
  public com.google.gson.JsonObject toJson();
  public net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder addModels(net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder$PartialBlockstate, net.neoforged.neoforge.client.model.generators.ConfiguredModel...);
  public net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder setModels(net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder$PartialBlockstate, net.neoforged.neoforge.client.model.generators.ConfiguredModel...);
  public net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder$PartialBlockstate partialState();
  public net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder forAllStates(java.util.function.Function<net.minecraft.world.level.block.state.BlockState, net.neoforged.neoforge.client.model.generators.ConfiguredModel[]>);
  public net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder forAllStatesExcept(java.util.function.Function<net.minecraft.world.level.block.state.BlockState, net.neoforged.neoforge.client.model.generators.ConfiguredModel[]>, net.minecraft.world.level.block.state.properties.Property<?>...);
}
