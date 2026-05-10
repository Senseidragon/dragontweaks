package io.github.senseidragon.dragontweaks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class BookAdvisorRenderer extends EntityRenderer<BookAdvisorEntity> {

    public BookAdvisorRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(BookAdvisorEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        DragonTweaks.LOGGER.info("BookAdvisorRenderer.render() called, ownerUUID={}, pos={},{},{}", entity.getOwnerUUID(), entity.getX(), entity.getY(), entity.getZ());
        UUID ownerUUID = entity.getOwnerUUID();
        if (ownerUUID == null) return;

        boolean showWritableBook = false;
        Minecraft mc = Minecraft.getInstance();
        if (mc.getSingleplayerServer() != null) {
            ServerLevel overworld = mc.getSingleplayerServer().getLevel(Level.OVERWORLD);
            if (overworld != null) {
                AdvisorState state = AdvisorStateData.get(overworld).getState(ownerUUID);
                showWritableBook = (state == AdvisorState.COLONY_WITH_CITIZEN);
            }
        }

        ItemStack stack = new ItemStack(showWritableBook ? Items.WRITABLE_BOOK : Items.BOOK);

        // one full Y rotation per 4 seconds = 80 ticks
        float angle = ((entity.tickCount + partialTick) % 80) / 80.0f * 360.0f;

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(angle));
        mc.getItemRenderer().renderStatic(
            stack,
            ItemDisplayContext.GROUND,
            packedLight,
            OverlayTexture.NO_OVERLAY,
            poseStack,
            bufferSource,
            entity.level(),
            entity.getId()
        );
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(BookAdvisorEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(DragonTweaks.MODID, "textures/entity/book_advisor.png");
    }
}
