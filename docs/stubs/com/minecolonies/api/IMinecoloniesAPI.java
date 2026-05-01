Compiled from "IMinecoloniesAPI.java"
public interface com.minecolonies.api.IMinecoloniesAPI {
  public static com.minecolonies.api.IMinecoloniesAPI getInstance();
  public abstract com.minecolonies.api.colony.IColonyManager getColonyManager();
  public abstract com.minecolonies.api.colony.ICitizenDataManager getCitizenDataManager();
  public abstract com.minecolonies.api.entity.mobs.registry.IMobAIRegistry getMobAIRegistry();
  public abstract com.minecolonies.api.entity.pathfinding.registry.IPathNavigateRegistry getPathNavigateRegistry();
  public abstract com.minecolonies.api.colony.buildings.registry.IBuildingDataManager getBuildingDataManager();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.colony.buildings.registry.BuildingEntry> getBuildingRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.colony.buildingextensions.registry.BuildingExtensionRegistries$BuildingExtensionEntry> getBuildingExtensionRegistry();
  public abstract com.minecolonies.api.colony.jobs.registry.IJobDataManager getJobDataManager();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.colony.jobs.registry.JobEntry> getJobRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.colony.interactionhandling.registry.InteractionResponseHandlerEntry> getInteractionResponseHandlerRegistry();
  public abstract com.minecolonies.api.colony.guardtype.registry.IGuardTypeDataManager getGuardTypeDataManager();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.colony.guardtype.GuardType> getGuardTypeRegistry();
  public abstract com.minecolonies.api.client.render.modeltype.registry.IModelTypeRegistry getModelTypeRegistry();
  public abstract com.ldtteam.common.config.Configurations<com.minecolonies.api.configuration.ClientConfiguration, com.minecolonies.api.configuration.ServerConfiguration, com.minecolonies.api.configuration.CommonConfiguration> getConfig();
  public abstract com.minecolonies.api.compatibility.IFurnaceRecipes getFurnaceRecipes();
  public abstract com.minecolonies.api.colony.interactionhandling.registry.IInteractionResponseHandlerDataManager getInteractionResponseHandlerDataManager();
  public abstract com.minecolonies.api.research.IGlobalResearchTree getGlobalResearchTree();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.research.ModResearchRequirements$ResearchRequirementEntry> getResearchRequirementRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.research.ModResearchEffects$ResearchEffectEntry> getResearchEffectRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.colony.colonyEvents.registry.ColonyEventTypeRegistryEntry> getColonyEventRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.colony.colonyEvents.registry.ColonyEventDescriptionTypeRegistryEntry> getColonyEventDescriptionRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.crafting.registry.RecipeTypeEntry> getRecipeTypeRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.crafting.registry.CraftingType> getCraftingTypeRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.quests.registries.QuestRegistries$RewardEntry> getQuestRewardRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.quests.registries.QuestRegistries$ObjectiveEntry> getQuestObjectiveRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.quests.registries.QuestRegistries$TriggerEntry> getQuestTriggerRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.quests.registries.QuestRegistries$DialogueAnswerEntry> getQuestDialogueAnswerRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.entity.citizen.happiness.HappinessRegistry$HappinessFactorTypeEntry> getHappinessTypeRegistry();
  public abstract net.minecraft.core.Registry<com.minecolonies.api.entity.citizen.happiness.HappinessRegistry$HappinessFunctionEntry> getHappinessFunctionRegistry();
  public abstract void onRegistryNewRegistry(net.neoforged.neoforge.registries.NewRegistryEvent);
  public abstract net.minecraft.core.Registry<com.minecolonies.api.equipment.registry.EquipmentTypeEntry> getEquipmentTypeRegistry();
  public abstract com.minecolonies.api.eventbus.EventBus getEventBus();
}
