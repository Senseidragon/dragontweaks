Compiled from "ICivilianData.java"
public interface com.minecolonies.api.colony.ICivilianData extends com.minecolonies.api.colony.ICitizen, net.neoforged.neoforge.common.util.INBTSerializable<net.minecraft.nbt.CompoundTag> {
  public abstract void setEntity(com.minecolonies.api.entity.citizen.AbstractCivilianEntity);
  public abstract void markDirty(int);
  public abstract void initForNewCivilian();
  public abstract void initEntityValues();
  public abstract void setGenderAndGenerateName(boolean);
  public abstract void setGender(boolean);
  public abstract int getTextureId();
  public abstract boolean isDirty();
  public abstract void clearDirty();
  public abstract void updateEntityIfNecessary();
  public abstract void serializeViewNetworkData(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract void increaseSaturation(double);
  public abstract void decreaseSaturation(double);
  public abstract void setName(java.lang.String);
  public abstract <R extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> com.minecolonies.api.colony.requestsystem.token.IToken<?> createRequest(R);
  public abstract <R extends com.minecolonies.api.colony.requestsystem.requestable.IRequestable> com.minecolonies.api.colony.requestsystem.token.IToken<?> createRequestAsync(R);
  public abstract void onRequestCancelled(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract boolean isRequestAsync(com.minecolonies.api.colony.requestsystem.token.IToken<?>);
  public abstract void onResponseTriggered(net.minecraft.network.chat.Component, int, net.minecraft.world.entity.player.Player);
  public abstract void update(int);
  public abstract void triggerInteraction(com.minecolonies.api.colony.interactionhandling.IInteractionResponseHandler);
  public abstract java.lang.String getTextureSuffix();
  public abstract void setSuffix(java.lang.String);
  public abstract java.util.Optional<? extends com.minecolonies.api.entity.citizen.AbstractCivilianEntity> getEntity();
  public abstract int getVoiceProfile();
  public abstract void setVoiceProfile(int);
  public abstract java.util.UUID getUUID();
}
