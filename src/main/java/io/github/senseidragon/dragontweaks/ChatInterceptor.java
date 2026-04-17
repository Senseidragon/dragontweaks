package io.github.senseidragon.dragontweaks;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.List;

public class ChatInterceptor {

    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        ServerLevel serverLevel = (ServerLevel) player.level();

        double range = Config.COMMAND_PROXIMITY.get();
        AABB searchBox = AABB.ofSize(player.position(), range * 2, range * 2, range * 2);
        List<AssistantEntity> candidates = serverLevel.getEntitiesOfClass(AssistantEntity.class, searchBox);

        if (candidates.isEmpty()) return;

        AssistantEntity nearest = null;
        double nearestDistSq = range * range;
        for (AssistantEntity candidate : candidates) {
            double distSq = player.distanceToSqr(candidate);
            if (distSq <= nearestDistSq) {
                nearestDistSq = distSq;
                nearest = candidate;
            }
        }

        if (nearest == null) return;

        String rawMessage = event.getRawText();
        event.setCanceled(true);

        Component entityName = nearest.getCustomName() != null
            ? nearest.getCustomName()
            : Component.literal("Assistant [PoC]");

        player.sendSystemMessage(
            Component.literal("[").append(entityName).append("]: Hmm...")
        );

        DragonTweaks.LOGGER.info("[ChatInterceptor] {} -> {}: {}",
            player.getGameProfile().getName(),
            entityName.getString(),
            rawMessage);

        MinecraftServer server = player.getServer();
        if (server == null) return;

        String timeOfDay = OllamaClient.timeOfDay(serverLevel.getDayTime());
        String weather = OllamaClient.weather(serverLevel.isRaining(), serverLevel.isThundering());

        OllamaClient.query(server, player, entityName, rawMessage, timeOfDay, weather);
    }
}
