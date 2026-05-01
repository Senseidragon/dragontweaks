Compiled from "StructureSettingsBuilder.java"
public class net.neoforged.neoforge.common.world.StructureSettingsBuilder {
  public static net.neoforged.neoforge.common.world.StructureSettingsBuilder copyOf(net.minecraft.world.level.levelgen.structure.Structure$StructureSettings);
  public net.minecraft.world.level.levelgen.structure.Structure$StructureSettings build();
  public net.minecraft.core.HolderSet<net.minecraft.world.level.biome.Biome> getBiomes();
  public void setBiomes(net.minecraft.core.HolderSet<net.minecraft.world.level.biome.Biome>);
  public net.neoforged.neoforge.common.world.StructureSettingsBuilder$StructureSpawnOverrideBuilder getSpawnOverrides(net.minecraft.world.entity.MobCategory);
  public net.neoforged.neoforge.common.world.StructureSettingsBuilder$StructureSpawnOverrideBuilder getOrAddSpawnOverrides(net.minecraft.world.entity.MobCategory);
  public void removeSpawnOverrides(net.minecraft.world.entity.MobCategory);
  public net.minecraft.world.level.levelgen.GenerationStep$Decoration getDecorationStep();
  public void setDecorationStep(net.minecraft.world.level.levelgen.GenerationStep$Decoration);
  public net.minecraft.world.level.levelgen.structure.TerrainAdjustment getTerrainAdaptation();
  public void setTerrainAdaptation(net.minecraft.world.level.levelgen.structure.TerrainAdjustment);
}
