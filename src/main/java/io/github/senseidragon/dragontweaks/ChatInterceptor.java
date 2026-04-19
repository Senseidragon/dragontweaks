package io.github.senseidragon.dragontweaks;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.ArrayList;
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
        String[] messageWords = messageLower.split("\\s+");

        List<AssistantEntity> matched = new ArrayList<>();
        AssistantEntity nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (AssistantEntity candidate : candidates) {
            double distSq = player.distanceToSqr(candidate);
            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                nearest = candidate;
            }
            if (candidate.getCustomName() != null) {
                String entityName = candidate.getCustomName().getString();
                for (String word : messageWords) {
                    if (!word.isEmpty() && AssistantCommand.nameMatches(entityName, word)) {
                        matched.add(candidate);
                        break;
                    }
                }
            }
        }

        List<AssistantEntity> targets = matched.isEmpty()
            ? (nearest != null ? List.of(nearest) : List.of())
            : matched;

        if (targets.isEmpty()) return;

        String rawMessage = event.getRawText();
        player.sendSystemMessage(
                Component.literal("<" + player.getGameProfile().getName() + "> " + rawMessage)
        );
        event.setCanceled(true);

        MinecraftServer server = player.getServer();
        if (server == null) return;

        String timeOfDay = OllamaClient.timeOfDay(serverLevel.getDayTime());
        String weather = OllamaClient.weather(serverLevel.isRaining(), serverLevel.isThundering());

        for (AssistantEntity target : targets) {
            Component entityName = target.getCustomName() != null
                ? target.getCustomName()
                : Component.literal("Assistant [PoC]");

            player.sendSystemMessage(
                Component.literal("[").append(entityName).append("]: Hmm...")
            );

            DragonTweaks.LOGGER.info("[ChatInterceptor] {} -> {}: {}",
                player.getGameProfile().getName(),
                entityName.getString(),
                rawMessage);

            String surroundings = OllamaClient.scanSurroundings(serverLevel, target);
            OllamaClient.query(server, player, entityName, rawMessage, target.getRole(), timeOfDay, weather, surroundings, target.getUUID());
        }
    }
}
