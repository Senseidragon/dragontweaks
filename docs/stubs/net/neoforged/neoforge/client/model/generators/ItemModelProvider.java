Compiled from "ItemModelProvider.java"
public abstract class net.neoforged.neoforge.client.model.generators.ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ModelProvider<net.neoforged.neoforge.client.model.generators.ItemModelBuilder> {
  public net.neoforged.neoforge.client.model.generators.ItemModelProvider(net.minecraft.data.PackOutput, java.lang.String, net.neoforged.neoforge.common.data.ExistingFileHelper);
  public net.neoforged.neoforge.client.model.generators.ItemModelBuilder basicItem(net.minecraft.world.item.Item);
  public net.neoforged.neoforge.client.model.generators.ItemModelBuilder basicItem(net.minecraft.resources.ResourceLocation);
  public net.neoforged.neoforge.client.model.generators.ItemModelBuilder handheldItem(net.minecraft.world.item.Item);
  public net.neoforged.neoforge.client.model.generators.ItemModelBuilder handheldItem(net.minecraft.resources.ResourceLocation);
  public net.neoforged.neoforge.client.model.generators.ItemModelBuilder spawnEggItem(net.minecraft.world.item.Item);
  public net.neoforged.neoforge.client.model.generators.ItemModelBuilder spawnEggItem(net.minecraft.resources.ResourceLocation);
  public net.neoforged.neoforge.client.model.generators.ItemModelBuilder simpleBlockItem(net.minecraft.world.level.block.Block);
  public net.neoforged.neoforge.client.model.generators.ItemModelBuilder simpleBlockItem(net.minecraft.resources.ResourceLocation);
  public java.lang.String getName();
}
