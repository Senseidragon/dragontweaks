Compiled from "ICitizenDataView.java"
public interface com.minecolonies.api.colony.ICitizenDataView extends com.minecolonies.api.colony.ICitizen {
  public abstract int getEntityId();
  public abstract java.lang.String getJob();
  public abstract net.minecraft.network.chat.MutableComponent getJobComponent();
  public abstract net.minecraft.core.BlockPos getHomeBuilding();
  public abstract net.minecraft.core.BlockPos getWorkBuilding();
  public abstract void setWorkBuilding(net.minecraft.core.BlockPos);
  public abstract int getColonyId();
  public abstract double getHappiness();
  public abstract net.minecraft.core.BlockPos getPosition();
  public abstract void deserialize(net.minecraft.network.RegistryFriendlyByteBuf);
  public abstract double getHealth();
  public abstract double getMaxHealth();
  public abstract java.util.List<com.minecolonies.api.colony.interactionhandling.IInteractionResponseHandler> getOrderedInteractions();
  public abstract com.minecolonies.api.colony.interactionhandling.IInteractionResponseHandler getSpecificInteraction(net.minecraft.network.chat.Component);
  public abstract boolean hasBlockingInteractions();
  public abstract boolean hasVisibleStatus();
  public abstract boolean hasPendingInteractions();
  public abstract com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenSkillHandler getCitizenSkillHandler();
  public abstract com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenHappinessHandler getHappinessHandler();
  public abstract net.minecraft.resources.ResourceLocation getStatusIcon();
  public abstract com.minecolonies.api.entity.citizen.VisibleCitizenStatus getVisibleStatus();
  public abstract net.minecraft.core.BlockPos getStatusPosition();
  public abstract com.minecolonies.api.colony.jobs.IJobView getJobView();
  public abstract java.lang.Integer getPartner();
  public abstract java.util.List<java.lang.Integer> getChildren();
  public abstract java.util.List<java.lang.Integer> getSiblings();
  public abstract com.minecolonies.api.util.Tuple<java.lang.String, java.lang.String> getParents();
  public abstract net.minecraft.resources.ResourceLocation getCustomTexture();
  public abstract void setJobView(com.minecolonies.api.colony.jobs.IJobView);
  public abstract void setHomeBuilding(net.minecraft.core.BlockPos);
  public abstract java.util.UUID getCustomTextureUUID();
  public abstract net.minecraft.world.item.ItemStack getDisplayArmor(net.minecraft.world.entity.EquipmentSlot);
  public abstract boolean isSick();
  public abstract com.minecolonies.api.colony.IColonyView getColony();
  public default com.minecolonies.api.colony.IColony getColony();
}
