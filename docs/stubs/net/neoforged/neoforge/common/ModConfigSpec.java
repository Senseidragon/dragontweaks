Compiled from "ModConfigSpec.java"
public class net.neoforged.neoforge.common.ModConfigSpec implements net.neoforged.fml.config.IConfigSpec {
  public boolean isEmpty();
  public java.lang.String getLevelComment(java.util.List<java.lang.String>);
  public java.lang.String getLevelTranslationKey(java.util.List<java.lang.String>);
  public void acceptConfig(net.neoforged.fml.config.IConfigSpec$ILoadedConfig);
  public void validateSpec(net.neoforged.fml.config.ModConfig);
  public boolean isLoaded();
  public com.electronwill.nightconfig.core.UnmodifiableConfig getSpec();
  public com.electronwill.nightconfig.core.UnmodifiableConfig getValues();
  public void afterReload();
  public void resetCaches(net.neoforged.neoforge.common.ModConfigSpec$RestartType);
  public void save();
  public boolean isCorrect(com.electronwill.nightconfig.core.UnmodifiableCommentedConfig);
  public void correct(com.electronwill.nightconfig.core.CommentedConfig);
  public int correct(com.electronwill.nightconfig.core.CommentedConfig, com.electronwill.nightconfig.core.ConfigSpec$CorrectionListener);
  public int correct(com.electronwill.nightconfig.core.CommentedConfig, com.electronwill.nightconfig.core.ConfigSpec$CorrectionListener, com.electronwill.nightconfig.core.ConfigSpec$CorrectionListener);
}
