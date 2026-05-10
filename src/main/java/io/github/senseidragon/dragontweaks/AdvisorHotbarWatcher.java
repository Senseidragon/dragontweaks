package io.github.senseidragon.dragontweaks;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.UUID;

public class AdvisorHotbarWatcher {

    private static final ResourceLocation BUILD_TOOL_ID =
            ResourceLocation.fromNamespaceAndPath("structurize", "sceptergold");

    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        UUID playerUUID = player.getUUID();

        ServerLevel overworld = serverLevel.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) return;

        AdvisorStateData data = AdvisorStateData.get(overworld);

        AdvisorState state = data.getState(playerUUID);

        if (state == AdvisorState.DORMANT && !data.hasBuildToolTriggerFired(playerUUID)) {
            for (int slot = 0; slot <= 8; slot++) {
                ItemStack stack = player.getInventory().getItem(slot);
                if (stack.isEmpty()) continue;
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
                if (BUILD_TOOL_ID.equals(itemId)) {
                    data.setBuildToolTriggerFired(playerUUID);
                    data.setState(playerUUID, AdvisorState.PRE_COLONY);

                    DragonTweaks.LOGGER.info("Attempting BookAdvisorEntity spawn for player {}", playerUUID);
                    BookAdvisorEntity entity = ModEntities.BOOK_ADVISOR.get().create(serverLevel);
                    if (entity == null) {
                        DragonTweaks.LOGGER.error("BookAdvisorEntity creation returned null for player {}", playerUUID);
                        return;
                    }
                    DragonTweaks.LOGGER.info("BookAdvisorEntity created, UUID={}", entity.getUUID());
                    entity.setOwner(player);
                    entity.moveTo(player.getX(), player.getY() + 1.0, player.getZ(), 0f, 0f);
                    serverLevel.addFreshEntity(entity);
                    data.setAdvisorEntityUUID(playerUUID, entity.getUUID());
                    break;
                }
            }
        } else if (state == AdvisorState.PRE_COLONY) {
            boolean hasTool = false;
            for (int slot = 0; slot <= 8; slot++) {
                ItemStack stack = player.getInventory().getItem(slot);
                if (!stack.isEmpty() && BUILD_TOOL_ID.equals(BuiltInRegistries.ITEM.getKey(stack.getItem()))) {
                    hasTool = true;
                    break;
                }
            }

            UUID existingUUID = data.getAdvisorEntityUUID(playerUUID);
            if (hasTool && existingUUID == null) {
                BookAdvisorEntity entity = ModEntities.BOOK_ADVISOR.get().create(serverLevel);
                if (entity != null) {
                    entity.setOwner(player);
                    entity.moveTo(player.getX(), player.getY() + 1.0, player.getZ(), 0f, 0f);
                    serverLevel.addFreshEntity(entity);
                    data.setAdvisorEntityUUID(playerUUID, entity.getUUID());
                }
            } else if (!hasTool && existingUUID != null) {
                net.minecraft.world.entity.Entity existing = serverLevel.getEntity(existingUUID);
                if (existing != null) {
                    existing.discard();
                }
                data.setAdvisorEntityUUID(playerUUID, null);
            }
        }
    }
}
