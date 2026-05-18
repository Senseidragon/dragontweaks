package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.IMinecoloniesAPI;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.eventbus.events.colony.ColonyCreatedModEvent;
import com.minecolonies.api.eventbus.events.colony.ColonyDeletedModEvent;
import com.minecolonies.api.eventbus.events.colony.buildings.BuildingConstructionModEvent;
import com.minecolonies.api.eventbus.events.colony.citizens.CitizenAddedModEvent;
import com.minecolonies.api.eventbus.events.colony.citizens.CitizenDiedModEvent;
import com.minecolonies.api.eventbus.events.colony.citizens.CitizenJobChangedModEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(DragonTweaks.MODID)
public class DragonTweaks {
    public static final String MODID = "dragontweaks";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static boolean LITE_MODE = false;
    private static final Set<UUID> liteModeNotified = new HashSet<>();
    private static final Set<UUID> colonyGreetedPlayers = new HashSet<>();
    private static final Set<UUID> citizenArrivalNotifiedPlayers = new HashSet<>();

    public DragonTweaks(IEventBus modEventBus, ModContainer modContainer) {
        ModEntities.ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(Config::onLoad);
        modEventBus.addListener(ModEntities::onAttributeCreate);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.addListener((LevelEvent.Load e) -> {
            if (e.getLevel() instanceof ServerLevel serverLevel
                    && serverLevel.dimension() == Level.OVERWORLD) {
                RoleAssignmentData roleData = RoleAssignmentData.get(serverLevel);
                AdvisorStateData advisorStateData = AdvisorStateData.get(serverLevel);
                for (AssistantRoleRecord record : roleData.getAssignments()) {
                    if ("Advisor".equalsIgnoreCase(record.roleType())
                            && advisorStateData.getState(record.playerUUID()) == AdvisorState.COLONY_NO_CITIZEN) {
                        advisorStateData.setState(record.playerUUID(), AdvisorState.COLONY_WITH_CITIZEN);
                        advisorStateData.setAssignedCitizenId(record.playerUUID(), record.citizenId());
                        LOGGER.info("[DragonTweaks] Healed AdvisorState for player={} citizenId={} → COLONY_WITH_CITIZEN",
                            record.playerUUID(), record.citizenId());
                    }
                }
            }
        });
        NeoForge.EVENT_BUS.addListener(CitizenInteractDetector::onEntityInteract);
        NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, ChatInterceptor::onServerChat);
        NeoForge.EVENT_BUS.addListener(ObservationTicker::onServerTick);
        NeoForge.EVENT_BUS.addListener(AdvisorDiagnosticLoop::onServerTick);
        NeoForge.EVENT_BUS.addListener(AdvisorHotbarWatcher::onPlayerTick);
        NeoForge.EVENT_BUS.addListener(PreColonyScoutTicker::onServerTick);
        NeoForge.EVENT_BUS.addListener((ServerStoppingEvent e) -> LLMClient.shutdown());
        modEventBus.addListener(this::registerServerPackets);
        NeoForge.EVENT_BUS.addListener((net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent e) -> {
            if (LITE_MODE && e.getEntity() instanceof ServerPlayer sp && liteModeNotified.add(sp.getUUID())) {
                sp.sendSystemMessage(Component.literal(
                    "Assistant Mod is running in Lite mode. Add an OpenRouter API key in config to unlock full AI companion features."));
            }
        });
    }

    private void registerServerPackets(RegisterPayloadHandlersEvent event) {
        event.registrar(MODID)
                .playToServer(RoleSelectionPacket.TYPE, RoleSelectionPacket.STREAM_CODEC,
                        RoleSelectionPacket::handleOnServer);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        PlannerDependencyRegistry.load();

        String apiKey = EnvLoader.get("OPENROUTER_API_KEY");
        if (apiKey == null || apiKey.isBlank() || apiKey.equals("your-api-key-here")) {
            LITE_MODE = true;
            LOGGER.warn("DragonTweaks: No OpenRouter API key found — running in Lite mode.");
        } else {
            LITE_MODE = false;
            LOGGER.info("DragonTweaks loaded — LLM endpoint: {}", Config.LLM_ENDPOINT.get());
        }

        event.enqueueWork(() -> {
            if (!ModList.get().isLoaded("minecolonies")) return;

            IMinecoloniesAPI.getInstance().getEventBus().subscribe(CitizenDiedModEvent.class, e -> {
                int colonyId = e.getColony().getID();
                ColonyDiagnosticCache.invalidate(colonyId);
                AdvisorDiagnosticLoop.markDirty(colonyId);
                if (!(e.getColony().getWorld() instanceof ServerLevel level)) return;
                NicknameData.get(level.getServer().overworld()).removeNickname(colonyId, e.getCitizen().getId());
                CitizenConversationMemory.get(level.getServer().overworld()).clearHistory(colonyId, e.getCitizen().getId());
                String citizenName = e.getCitizen().getName();
                String prompt = citizenName + " has died. React with grief or shock in character.";
                ObservationTicker.fireColonyEventObservation(level.getServer(), level, prompt);
                if (!LITE_MODE) {
                    handleAdvisorCitizenLost(e.getColony(), e.getCitizen().getId(), level);
                }
            });

            IMinecoloniesAPI.getInstance().getEventBus().subscribe(BuildingConstructionModEvent.class, e -> {
                int colonyId = e.getColony().getID();
                ColonyDiagnosticCache.invalidate(colonyId);
                AdvisorDiagnosticLoop.markDirty(colonyId);
                if (!(e.getColony().getWorld() instanceof ServerLevel level)) return;
                String buildingName = e.getBuilding().getBuildingType().getTranslationKey();
                Component buildingDisplay = Component.translatable(buildingName);
                String prompt = "the " + buildingDisplay.getString() + " has just finished construction. React with excitement or pride in character.";
                ObservationTicker.fireColonyEventObservation(level.getServer(), level, prompt);
            });

            IMinecoloniesAPI.getInstance().getEventBus().subscribe(CitizenJobChangedModEvent.class, e -> {
                int colonyId = e.getColony().getID();
                ColonyDiagnosticCache.invalidate(colonyId);
                AdvisorDiagnosticLoop.markDirty(colonyId);
                if (!LITE_MODE && e.getColony().getWorld() instanceof ServerLevel level) {
                    handleAdvisorCitizenLost(e.getColony(), e.getCitizen().getId(), level);
                }
            });

            IMinecoloniesAPI.getInstance().getEventBus().subscribe(CitizenAddedModEvent.class, e -> {
                int colonyId = e.getColony().getID();
                ColonyDiagnosticCache.invalidate(colonyId);
                AdvisorDiagnosticLoop.markDirty(colonyId);
                if (LITE_MODE) return;
                UUID ownerUUID = e.getColony().getPermissions().getOwner();
                if (!(e.getColony().getWorld() instanceof ServerLevel serverLevel)) return;
                AdvisorStateData stateData = AdvisorStateData.get(serverLevel.getServer().getLevel(Level.OVERWORLD));
                if (stateData.getState(ownerUUID) != AdvisorState.COLONY_NO_CITIZEN) return;
                if (!citizenArrivalNotifiedPlayers.add(ownerUUID)) return;
                ServerPlayer player = serverLevel.getServer().getPlayerList().getPlayer(ownerUUID);
                if (player != null) {
                    player.sendSystemMessage(Component.literal(
                        "New colonists have arrived, Dev. Consider assigning one of them to serve as your Advisor."));
                }
            });

            IMinecoloniesAPI.getInstance().getEventBus().subscribe(ColonyCreatedModEvent.class, e -> {
                if (LITE_MODE) return;
                UUID playerUUID = e.getColony().getPermissions().getOwner();
                if (!(e.getColony().getWorld() instanceof ServerLevel serverLevel)) return;
                AdvisorStateData stateData = AdvisorStateData.get(serverLevel.getServer().getLevel(Level.OVERWORLD));
                if (stateData.getState(playerUUID) != AdvisorState.PRE_COLONY) return;
                stateData.setState(playerUUID, AdvisorState.COLONY_NO_CITIZEN);
                if (colonyGreetedPlayers.add(playerUUID)) {
                    ServerPlayer player = serverLevel.getServer().getPlayerList().getPlayer(playerUUID);
                    if (player != null) {
                        player.sendSystemMessage(Component.literal(
                            "A colony has been established. I'll be staying close from now on. If you need me, say 'Advisor' followed by your question."));
                    }
                }
            });

            // TODO: Subscribe to RaidStartedEvent (Section 5 — advisor_branching_spec_v0_2.md)
            // No stub found in docs/stubs/ — package path unconfirmed. When verified:
            //   ColonyDiagnosticCache.invalidate(colonyId); AdvisorDiagnosticLoop.markDirty(colonyId);

            IMinecoloniesAPI.getInstance().getEventBus().subscribe(ColonyDeletedModEvent.class, e -> {
                if (LITE_MODE) return;
                UUID playerUUID = e.getColony().getPermissions().getOwner();
                if (!(e.getColony().getWorld() instanceof ServerLevel serverLevel)) return;
                ServerLevel overworld = serverLevel.getServer().getLevel(Level.OVERWORLD);
                AdvisorStateData stateData = AdvisorStateData.get(overworld);
                AdvisorState currentState = stateData.getState(playerUUID);
                if (currentState != AdvisorState.COLONY_NO_CITIZEN && currentState != AdvisorState.COLONY_WITH_CITIZEN) return;
                UUID entityUUID = stateData.getAdvisorEntityUUID(playerUUID);
                if (entityUUID != null) {
                    for (ServerLevel lvl : serverLevel.getServer().getAllLevels()) {
                        Entity existing = lvl.getEntity(entityUUID);
                        if (existing != null) { existing.discard(); break; }
                    }
                }
                ServerPlayer player = serverLevel.getServer().getPlayerList().getPlayer(playerUUID);
                if (player != null && player.level() instanceof ServerLevel playerLevel) {
                    BookAdvisorEntity newEntity = ModEntities.BOOK_ADVISOR.get().create(playerLevel);
                    if (newEntity != null) {
                        newEntity.setOwner(player);
                        newEntity.moveTo(player.getX(), player.getY() + 1.0, player.getZ(), 0f, 0f);
                        playerLevel.addFreshEntity(newEntity);
                        stateData.setAdvisorEntityUUID(playerUUID, newEntity.getUUID());
                    }
                } else {
                    stateData.setAdvisorEntityUUID(playerUUID, null);
                }
                stateData.setState(playerUUID, AdvisorState.PRE_COLONY);
                stateData.setAssignedCitizenId(playerUUID, null);
            });
        });
    }

    private static void handleAdvisorCitizenLost(IColony colony, int citizenId, ServerLevel level) {
        UUID playerUUID = colony.getPermissions().getOwner();
        ServerLevel overworld = level.getServer().getLevel(Level.OVERWORLD);
        AdvisorStateData stateData = AdvisorStateData.get(overworld);
        if (stateData.getState(playerUUID) != AdvisorState.COLONY_WITH_CITIZEN) return;
        Integer assignedId = stateData.getAssignedCitizenId(playerUUID);
        if (assignedId == null || assignedId.intValue() != citizenId) return;
        UUID entityUUID = stateData.getAdvisorEntityUUID(playerUUID);
        if (entityUUID != null) {
            for (ServerLevel lvl : level.getServer().getAllLevels()) {
                Entity existing = lvl.getEntity(entityUUID);
                if (existing != null) { existing.discard(); break; }
            }
        }
        if (colony.getServerBuildingManager().hasTownHall()) {
            BlockPos thPos = colony.getServerBuildingManager().getTownHall().getPosition();
            BookAdvisorEntity newEntity = ModEntities.BOOK_ADVISOR.get().create(level);
            if (newEntity != null) {
                newEntity.moveTo(thPos.getX() + 0.5, thPos.getY() + 1.0, thPos.getZ() + 0.5, 0f, 0f);
                level.addFreshEntity(newEntity);
                stateData.setAdvisorEntityUUID(playerUUID, newEntity.getUUID());
            }
        } else {
            stateData.setAdvisorEntityUUID(playerUUID, null);
        }
        stateData.setState(playerUUID, AdvisorState.COLONY_NO_CITIZEN);
        stateData.setAssignedCitizenId(playerUUID, null);
        ServerPlayer player = level.getServer().getPlayerList().getPlayer(playerUUID);
        if (player != null) {
            player.sendSystemMessage(Component.literal(
                "Your advisor's role is now vacant. Assign a new citizen to restore full capability."));
        }
    }
}
