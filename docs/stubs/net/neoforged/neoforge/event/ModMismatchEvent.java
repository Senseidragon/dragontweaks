Compiled from "ModMismatchEvent.java"
public class net.neoforged.neoforge.event.ModMismatchEvent extends net.neoforged.bus.api.Event implements net.neoforged.fml.event.IModBusEvent {
  public net.neoforged.neoforge.event.ModMismatchEvent(net.minecraft.world.level.storage.LevelStorageSource$LevelDirectory, java.util.Map<java.lang.String, org.apache.maven.artifact.versioning.ArtifactVersion>, java.util.Map<java.lang.String, org.apache.maven.artifact.versioning.ArtifactVersion>);
  public net.minecraft.world.level.storage.LevelStorageSource$LevelDirectory getLevelDirectory();
  public org.apache.maven.artifact.versioning.ArtifactVersion getPreviousVersion(java.lang.String);
  public org.apache.maven.artifact.versioning.ArtifactVersion getCurrentVersion(java.lang.String);
  public void markResolved(java.lang.String);
  public boolean wasResolved(java.lang.String);
  public java.util.Optional<net.neoforged.neoforge.event.ModMismatchEvent$MismatchedVersionInfo> getVersionDifference(java.lang.String);
  public java.util.Optional<net.neoforged.fml.ModContainer> getResolver(java.lang.String);
  public boolean anyUnresolved();
  public java.util.stream.Stream<net.neoforged.neoforge.event.ModMismatchEvent$MismatchResolutionResult> getUnresolved();
  public boolean anyResolved();
  public java.util.stream.Stream<net.neoforged.neoforge.event.ModMismatchEvent$MismatchResolutionResult> getResolved();
}
