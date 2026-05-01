Compiled from "InteractionValidatorRegistry.java"
public final class com.minecolonies.api.colony.interactionhandling.InteractionValidatorRegistry {
  public static java.util.function.Predicate<com.minecolonies.api.colony.ICitizenData> getStandardInteractionValidatorPredicate(net.minecraft.network.chat.Component);
  public static java.util.function.BiPredicate<com.minecolonies.api.colony.ICitizenData, net.minecraft.core.BlockPos> getPosBasedInteractionValidatorPredicate(net.minecraft.network.chat.Component);
  public static java.util.function.BiPredicate<com.minecolonies.api.colony.ICitizenData, com.minecolonies.api.colony.requestsystem.token.IToken<?>> getTokenBasedInteractionValidatorPredicate(net.minecraft.network.chat.Component);
  public static void registerStandardPredicate(net.minecraft.network.chat.Component, java.util.function.Predicate<com.minecolonies.api.colony.ICitizenData>);
  public static void registerPosBasedPredicate(net.minecraft.network.chat.Component, java.util.function.BiPredicate<com.minecolonies.api.colony.ICitizenData, net.minecraft.core.BlockPos>);
  public static void registerTokenBasedPredicate(net.minecraft.network.chat.Component, java.util.function.BiPredicate<com.minecolonies.api.colony.ICitizenData, com.minecolonies.api.colony.requestsystem.token.IToken<?>>);
  public static boolean hasValidator(net.minecraft.network.chat.MutableComponent);
}
