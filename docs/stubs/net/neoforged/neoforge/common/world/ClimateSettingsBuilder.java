Compiled from "ClimateSettingsBuilder.java"
public class net.neoforged.neoforge.common.world.ClimateSettingsBuilder {
  public static net.neoforged.neoforge.common.world.ClimateSettingsBuilder copyOf(net.minecraft.world.level.biome.Biome$ClimateSettings);
  public static net.neoforged.neoforge.common.world.ClimateSettingsBuilder create(boolean, float, net.minecraft.world.level.biome.Biome$TemperatureModifier, float);
  public net.minecraft.world.level.biome.Biome$ClimateSettings build();
  public boolean hasPrecipitation();
  public void setHasPrecipitation(boolean);
  public float getTemperature();
  public void setTemperature(float);
  public net.minecraft.world.level.biome.Biome$TemperatureModifier getTemperatureModifier();
  public void setTemperatureModifier(net.minecraft.world.level.biome.Biome$TemperatureModifier);
  public float getDownfall();
  public void setDownfall(float);
}
