package io.github.senseidragon.dragontweaks;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class RoleAssignmentScreen extends Screen {

    private static final int PANEL_W = 210;
    private static final int PANEL_H = 220;
    private static final int ROW_H = 22;

    private final RoleAssignmentPayload payload;
    private final List<String> roles;

    private int leftPos;
    private int topPos;
    private int listTop;
    private int listBottom;
    private int scrollOffset;
    private int maxScroll;

    private int selectedIndex = -1;
    private Button assignButton;

    public RoleAssignmentScreen(RoleAssignmentPayload payload) {
        super(Component.literal("Assign Role"));
        this.payload = payload;
        this.roles = payload.availableRoles();
    }

    @Override
    protected void init() {
        leftPos = (width - PANEL_W) / 2;
        topPos = (height - PANEL_H) / 2;

        listTop = topPos + 58;
        listBottom = topPos + 175;
        int listHeight = listBottom - listTop;
        maxScroll = Math.max(0, roles.size() * ROW_H - listHeight);
        scrollOffset = 0;

        int btnY = topPos + PANEL_H - 38;

        addRenderableWidget(Button.builder(Component.literal("Cancel"), btn -> onClose())
                .bounds(leftPos + 10, btnY, 55, 20)
                .build());

        addRenderableWidget(Button.builder(Component.literal("Say Hi"), btn -> onAcknowledge())
                .bounds(leftPos + 70, btnY, 70, 20)
                .build());

        assignButton = addRenderableWidget(Button.builder(Component.literal("Assign"), btn -> onAssign())
                .bounds(leftPos + 145, btnY, 55, 20)
                .build());
        assignButton.active = false;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics, mouseX, mouseY, partialTick);

        // Panel background
        graphics.fill(leftPos, topPos, leftPos + PANEL_W, topPos + PANEL_H, 0xC0101010);
        graphics.fill(leftPos, topPos, leftPos + PANEL_W, topPos + 1, 0xFF888888);
        graphics.fill(leftPos, topPos + PANEL_H - 1, leftPos + PANEL_W, topPos + PANEL_H, 0xFF888888);
        graphics.fill(leftPos, topPos, leftPos + 1, topPos + PANEL_H, 0xFF888888);
        graphics.fill(leftPos + PANEL_W - 1, topPos, leftPos + PANEL_W, topPos + PANEL_H, 0xFF888888);

        // Title
        graphics.drawCenteredString(font, "Assign Role", leftPos + PANEL_W / 2, topPos + 10, 0xFFFFFF);

        // Citizen name
        graphics.drawCenteredString(font, payload.citizenName(), leftPos + PANEL_W / 2, topPos + 23, 0xAAAAAA);

        // Slot counter
        String slotText = "Slots: " + payload.slotsUsed() + " / " + payload.slotsMax();
        graphics.drawCenteredString(font, slotText, leftPos + PANEL_W / 2, topPos + 37, 0xDDDDDD);

        // Divider above list
        graphics.fill(leftPos + 5, topPos + 50, leftPos + PANEL_W - 5, topPos + 51, 0xFF555555);

        // Role list (scissor-clipped)
        graphics.enableScissor(leftPos + 1, listTop, leftPos + PANEL_W - 1, listBottom);
        for (int i = 0; i < roles.size(); i++) {
            int rowY = listTop + i * ROW_H - scrollOffset;
            if (rowY + ROW_H < listTop || rowY > listBottom) continue;

            boolean selected = (i == selectedIndex);
            int rowBg = selected ? 0xFF1A4A1A : 0xFF1A1A1A;
            graphics.fill(leftPos + 5, rowY + 1, leftPos + PANEL_W - 5, rowY + ROW_H - 1, rowBg);

            int textColor = selected ? 0x00FF44 : 0xCCCCCC;
            graphics.drawString(font, roles.get(i), leftPos + 14, rowY + 7, textColor);
        }
        graphics.disableScissor();

        // Divider below list
        graphics.fill(leftPos + 5, listBottom, leftPos + PANEL_W - 5, listBottom + 1, 0xFF555555);

        // Render buttons
        for (var widget : this.renderables) {
            widget.render(graphics, mouseX, mouseY, partialTick);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) return true;

        // Hit-test role rows
        if (mouseX >= leftPos + 5 && mouseX <= leftPos + PANEL_W - 5
                && mouseY >= listTop && mouseY < listBottom) {
            int relY = (int) mouseY - listTop + scrollOffset;
            int clickedIndex = relY / ROW_H;
            if (clickedIndex >= 0 && clickedIndex < roles.size()) {
                selectedIndex = clickedIndex;
                assignButton.active = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (maxScroll > 0) {
            scrollOffset = (int) Math.max(0, Math.min(maxScroll, scrollOffset - scrollY * ROW_H));
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void onAcknowledge() {
        PacketDistributor.sendToServer(new AcknowledgeCitizenPacket(payload.citizenId()));
        onClose();
    }

    private void onAssign() {
        if (selectedIndex < 0 || selectedIndex >= roles.size()) return;
        PacketDistributor.sendToServer(new RoleSelectionPacket(payload.citizenId(), roles.get(selectedIndex)));
        onClose();
    }
}
