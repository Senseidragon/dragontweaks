Compiled from "CapabilityHooks.java"
public class net.neoforged.neoforge.capabilities.CapabilityHooks {
  public net.neoforged.neoforge.capabilities.CapabilityHooks();
  public static void init();
  public static void markProxyableCapabilities(net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent);
  public static void registerVanillaProviders(net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent);
  public static void registerFallbackVanillaProviders(net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent);
  public static void invalidateCapsOnChunkLoad(net.neoforged.neoforge.event.level.ChunkEvent$Load);
  public static void invalidateCapsOnChunkUnload(net.neoforged.neoforge.event.level.ChunkEvent$Unload);
  public static void cleanCapabilityListenerReferencesOnTick(net.neoforged.neoforge.event.tick.LevelTickEvent$Post);
}
