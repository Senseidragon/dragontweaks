Compiled from "MinecoloniesAPIProxy.java"
public final class com.minecolonies.api.MinecoloniesAPIProxy implements com.minecolonies.api.IMinecoloniesAPI {
  public static com.minecolonies.api.MinecoloniesAPIProxy getInstance();
  public void setApiInstance(com.minecolonies.api.IMinecoloniesAPI);
  public com.minecolonies.api.colony.IColonyManager getColonyManager();
  public com.minecolonies.api.colony.ICitizenDataManager getCitizenDataManager();
  public com.minecolonies.api.entity.mobs.registry.IMobAIRegistry getMobAIRegistry();
  public com.minecolonies.api.entity.pathfinding.registry.IPathNavigateRegistry getPathNavigateRegistry();
  public com.minecolonies.api.colony.buildings.registry.IBuildingDataManager getBuildingDataManager();
  public net.minecraft.core.Registry<com.minecolonies.api.colony.buildings.registry.BuildingEntry> getBuildingRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.colony.buildingextensions.registry.BuildingExtensionRegistries$BuildingExtensionEntry> getBuildingExtensionRegistry();
  public com.minecolonies.api.colony.jobs.registry.IJobDataManager getJobDataManager();
  public net.minecraft.core.Registry<com.minecolonies.api.colony.jobs.registry.JobEntry> getJobRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.colony.interactionhandling.registry.InteractionResponseHandlerEntry> getInteractionResponseHandlerRegistry();
  public com.minecolonies.api.colony.guardtype.registry.IGuardTypeDataManager getGuardTypeDataManager();
  public net.minecraft.core.Registry<com.minecolonies.api.colony.guardtype.GuardType> getGuardTypeRegistry();
  public com.minecolonies.api.client.render.modeltype.registry.IModelTypeRegistry getModelTypeRegistry();
  public com.ldtteam.common.config.Configurations<com.minecolonies.api.configuration.ClientConfiguration, com.minecolonies.api.configuration.ServerConfiguration, com.minecolonies.api.configuration.CommonConfiguration> getConfig();
  public com.minecolonies.api.compatibility.IFurnaceRecipes getFurnaceRecipes();
  public com.minecolonies.api.colony.interactionhandling.registry.IInteractionResponseHandlerDataManager getInteractionResponseHandlerDataManager();
  public com.minecolonies.api.research.IGlobalResearchTree getGlobalResearchTree();
  public net.minecraft.core.Registry<com.minecolonies.api.research.ModResearchRequirements$ResearchRequirementEntry> getResearchRequirementRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.research.ModResearchEffects$ResearchEffectEntry> getResearchEffectRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.colony.colonyEvents.registry.ColonyEventTypeRegistryEntry> getColonyEventRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.colony.colonyEvents.registry.ColonyEventDescriptionTypeRegistryEntry> getColonyEventDescriptionRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.crafting.registry.RecipeTypeEntry> getRecipeTypeRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.crafting.registry.CraftingType> getCraftingTypeRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.quests.registries.QuestRegistries$RewardEntry> getQuestRewardRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.quests.registries.QuestRegistries$ObjectiveEntry> getQuestObjectiveRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.quests.registries.QuestRegistries$TriggerEntry> getQuestTriggerRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.quests.registries.QuestRegistries$DialogueAnswerEntry> getQuestDialogueAnswerRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.entity.citizen.happiness.HappinessRegistry$HappinessFactorTypeEntry> getHappinessTypeRegistry();
  public net.minecraft.core.Registry<com.minecolonies.api.entity.citizen.happiness.HappinessRegistry$HappinessFunctionEntry> getHappinessFunctionRegistry();
  public void onRegistryNewRegistry(net.neoforged.neoforge.registries.NewRegistryEvent);
  public net.minecraft.core.Registry<com.minecolonies.api.equipment.registry.EquipmentTypeEntry> getEquipmentTypeRegistry();
  public com.minecolonies.api.eventbus.EventBus getEventBus();
}
