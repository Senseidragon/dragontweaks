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

        String messageLower = event.getRawText().toLowerCase();

        // Prefer an NPC whose name tokens appear in the message; fall back to nearest.
        AssistantEntity nearest = null;
        double nearestDistSq = range * range;
        AssistantEntity named = null;
        double namedDistSq = Double.MAX_VALUE;

        for (AssistantEntity candidate : candidates) {
            double distSq = player.distanceToSqr(candidate);

            if (candidate.getCustomName() != null) {
                for (String token : candidate.getCustomName().getString().split("\\s+")) {
                    if (!token.isEmpty() && messageLower.contains(token.toLowerCase())) {
                        if (distSq < namedDistSq) {
                            namedDistSq = distSq;
                            named = candidate;
                        }
                        break;
                    }
                }
            }

            if (distSq <= nearestDistSq) {
                nearestDistSq = distSq;
                nearest = candidate;
            }
        }

        nearest = (named != null) ? named : nearest;
        if (nearest == null) return;

        String rawMessage = event.getRawText();
        player.sendSystemMessage(
                Component.literal("<" + player.getGameProfile().getName() + "> " + rawMessage)
        );
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

        OllamaClient.query(server, player, entityName, rawMessage, nearest.getRole(), timeOfDay, weather);
    }
}
