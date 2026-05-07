package io.github.senseidragon.dragontweaks;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class PlannerPanelScreen extends Screen {

    // Panel geometry
    private static final int PANEL_W             = 324;
    private static final int PANEL_H             = 270;
    private static final int SNAP_PER_PAGE       = 3;
    private static final int CHAIN_PER_PAGE      = 8;
    private static final int LINE_H              = 10;
    private static final int REC_SEP             = 4;

    // Colors (ARGB)
    private static final int COL_PANEL_BG    = 0xC0101010;
    private static final int COL_BORDER      = 0xFF4455AA;
    private static final int COL_HEADER_BG   = 0xFF12122A;
    private static final int COL_COST_BG     = 0xFF111126;
    private static final int COL_MAT_BG      = 0xFF0E0E1E;
    private static final int COL_SEP         = 0xFF333355;
    private static final int COL_CRISIS      = 0xFFFF5555;
    private static final int COL_NO_CRISIS   = 0xFFAAAAAA;
    private static final int COL_IN_PROG     = 0xFF55AAFF;
    private static final int COL_RED         = 0xFFFF5555;
    private static final int COL_YELLOW      = 0xFFFFAA00;
    private static final int COL_GREEN       = 0xFF55FF55;
    private static final int COL_WHITE       = 0xFFFFFFFF;
    private static final int COL_GRAY        = 0xFF888888;
    private static final int COL_TITLE       = 0xFFFFFFAA;
    private static final int COL_DONE        = 0xFF666666;
    private static final int COL_FIRST_INC   = 0xFFFFDD44;

    private PlannerPanelPayload currentPayload;
    @Nullable private final Consumer<String> goalCallback;

    private int currentPage      = 0;
    private boolean matsExpanded = false;

    // Set during render(), read in mouseClicked()
    private int contentTop    = 0;
    private int contentBottom = 0;
    private int matsToggleY   = -1;

    private EditBox goalInput;
    private Button  btnPrev;
    private Button  btnNext;

    public PlannerPanelScreen(PlannerPanelPayload initialPayload,
                              @Nullable Consumer<String> goalCallback) {
        super(Component.literal("Colony Planner"));
        this.currentPayload = initialPayload;
        this.goalCallback   = goalCallback;
    }

    /** Called by the packet handler when a new payload arrives (e.g. after goal query). */
    public void updatePayload(PlannerPanelPayload newPayload) {
        this.currentPayload = newPayload;
        this.currentPage    = 0;
        this.matsExpanded   = false;
        this.matsToggleY    = -1;
    }

    // ---- Screen lifecycle ----

    @Override
    protected void init() {
        int px  = (this.width  - PANEL_W) / 2;
        int py  = (this.height - PANEL_H) / 2;
        int navY = py + PANEL_H - 20;
        int bw   = 48;

        // Goal input field — sits just below the title row
        int fieldX = px + 36;
        int fieldW = PANEL_W - 40;
        goalInput = new EditBox(this.font, fieldX, py + 16, fieldW, 12, Component.empty());
        goalInput.setMaxLength(60);
        goalInput.setBordered(true);
        goalInput.setFocused(false);
        addRenderableWidget(goalInput);

        btnPrev = addRenderableWidget(
            Button.builder(Component.literal("< Prev"), b -> { if (currentPage > 0) currentPage--; })
                  .bounds(px + 2, navY, bw, 16)
                  .build()
        );
        addRenderableWidget(
            Button.builder(Component.literal("Close"), b -> onClose())
                  .bounds(px + PANEL_W / 2 - bw / 2, navY, bw, 16)
                  .build()
        );
        btnNext = addRenderableWidget(
            Button.builder(Component.literal("Next >"), b -> { if (currentPage < totalPages() - 1) currentPage++; })
                  .bounds(px + PANEL_W - bw - 2, navY, bw, 16)
                  .build()
        );
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == InputConstants.KEY_RETURN && goalInput != null && goalInput.isFocused()) {
            String text = goalInput.getValue().trim();
            if (!text.isEmpty() && goalCallback != null) {
                goalCallback.accept(text);
            }
            goalInput.setFocused(false);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) return true;
        if (button != 0) return false;

        int my = (int) mouseY;

        // Materials toggle click detection
        if (matsToggleY >= 0 && my >= matsToggleY && my < matsToggleY + 13) {
            matsExpanded  = !matsExpanded;
            currentPage   = 0;
            return true;
        }

        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    // ---- Render ----

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(g, mouseX, mouseY, partialTick);

        int px = (this.width  - PANEL_W) / 2;
        int py = (this.height - PANEL_H) / 2;

        // Panel chrome
        g.fill(px, py, px + PANEL_W, py + PANEL_H, COL_PANEL_BG);
        g.hLine(px, px + PANEL_W - 1, py,            COL_BORDER);
        g.hLine(px, px + PANEL_W - 1, py + PANEL_H - 1, COL_BORDER);
        g.vLine(px,            py, py + PANEL_H - 1, COL_BORDER);
        g.vLine(px + PANEL_W - 1, py, py + PANEL_H - 1, COL_BORDER);

        // Title
        g.drawCenteredString(this.font, this.title, px + PANEL_W / 2, py + 4, COL_TITLE);

        // "Goal:" label
        g.drawString(this.font, "Goal:", px + 4, py + 19, COL_GRAY);

        int y = py + 32;

        // Worker/bed header strip — snapshot mode only
        if (currentPayload.getMode() == PlannerPanelPayload.Mode.SNAPSHOT
                && currentPayload.getWorkerHeader() != null) {
            g.fill(px + 2, y, px + PANEL_W - 2, y + 13, COL_HEADER_BG);
            PlannerPanelPayload.WorkerHeader wh = currentPayload.getWorkerHeader();
            PlannerPanelPayload.BedHeader    bh = currentPayload.getBedHeader();
            g.drawString(this.font,
                "Workers: " + wh.getWorkersAssigned() + " / " + wh.getTotalSlots() + " filled",
                px + 5, y + 3, COL_WHITE);
            g.drawString(this.font,
                "Beds: " + bh.getOccupied() + " / " + bh.getCapacity() + " occupied",
                px + 175, y + 3, COL_WHITE);
            y += 15;
        }

        contentTop    = y;
        contentBottom = py + PANEL_H - 22;
        matsToggleY   = -1; // reset; set below if applicable

        if (currentPayload.getMode() == PlannerPanelPayload.Mode.SNAPSHOT) {
            renderSnapshot(g, px);
        } else {
            renderGoalMode(g, px);
        }

        // Page counter
        int tp = totalPages();
        g.drawCenteredString(this.font,
            Component.literal((currentPage + 1) + " / " + tp),
            px + PANEL_W / 2, py + PANEL_H - 17, COL_GRAY);

        btnPrev.active = currentPage > 0;
        btnNext.active = currentPage < tp - 1;

        for (Renderable r : this.renderables) {
            r.render(g, mouseX, mouseY, partialTick);
        }
    }

    // ---- Snapshot mode ----

    private void renderSnapshot(GuiGraphics g, int px) {
        List<PlannerPanelPayload.Recommendation> recs = currentPayload.getRecommendations();
        if (recs.isEmpty()) {
            g.drawString(this.font, "No recommendations at this time.", px + 5, contentTop + 4, COL_GRAY);
            return;
        }

        int start = currentPage * SNAP_PER_PAGE;
        int end   = Math.min(start + SNAP_PER_PAGE, recs.size());

        g.enableScissor(px, contentTop, px + PANEL_W, contentBottom);
        int cy = contentTop;
        for (int i = start; i < end; i++) {
            cy = renderRecCard(g, recs.get(i), px, cy);
        }
        g.disableScissor();
    }

    private int renderRecCard(GuiGraphics g, PlannerPanelPayload.Recommendation rec, int px, int cy) {
        // Card separator line
        g.fill(px + 2, cy, px + PANEL_W - 2, cy + 1, COL_SEP);
        cy += 2;

        // Line 1: priority badge + target name + [In Progress]
        boolean crisis  = rec.getPriority() == PlannerPanelPayload.Priority.CRISIS;
        String badge    = crisis ? "[!]" : "[ ]";
        int badgeColor  = crisis ? COL_CRISIS : COL_NO_CRISIS;
        g.drawString(this.font, badge, px + 4, cy, badgeColor);

        String name = truncate(rec.getTargetDisplayName(), 20);
        g.drawString(this.font, name, px + 26, cy, COL_WHITE);

        if (rec.isInProgress()) {
            g.drawString(this.font, "[In Progress]", px + PANEL_W - 82, cy, COL_IN_PROG);
        }
        cy += LINE_H + 1;

        // Line 2: immediate blocker
        String blocker = rec.getImmediateBlocker();
        if (blocker != null && !blocker.isEmpty()) {
            g.drawString(this.font, "  \u25b8 " + truncate(blocker, 36), px + 4, cy, COL_YELLOW);
            cy += LINE_H;
        }

        // Line 3: research prereq (conditional — research is the active blocker)
        if (rec.getResearchPrereqName() != null) {
            g.drawString(this.font, "  Research: " + rec.getResearchPrereqName(), px + 4, cy, COL_IN_PROG);
            cy += LINE_H;
        }

        // Line 4: worker slots constraint (conditional)
        if (rec.getWorkerSlotsNeeded() > 0) {
            g.drawString(this.font, "  Worker slot needed", px + 4, cy, COL_RED);
            cy += LINE_H;
        }

        // Line 5: bed delta constraint (conditional)
        if (rec.getBedDelta() > 0) {
            g.drawString(this.font, "  +" + rec.getBedDelta() + " bed(s) needed", px + 4, cy, COL_RED);
            cy += LINE_H;
        }

        // Line 6: steps remaining
        g.drawString(this.font, "  Steps remaining: " + rec.getStepsRemaining(), px + 4, cy, COL_GRAY);
        cy += LINE_H;

        cy += REC_SEP;
        return cy;
    }

    // ---- Goal input mode ----

    private void renderGoalMode(GuiGraphics g, int px) {
        PlannerPanelPayload.GoalResult result = currentPayload.getGoalResult();
        if (result == null) {
            g.drawString(this.font, "Enter a building name above and press Enter.",
                px + 5, contentTop + 4, COL_GRAY);
            return;
        }

        if (result.getChain() == null) {
            renderNoMatch(g, result, px, contentTop);
            return;
        }

        int cy = contentTop;

        // Cost estimate block
        PlannerPanelPayload.CostEstimate cost = result.getCostEstimate();
        if (cost != null) {
            g.fill(px + 2, cy, px + PANEL_W - 2, cy + 14, COL_COST_BG);
            g.drawString(this.font,
                "Steps remaining: " + cost.getStepsRemaining(),
                px + 5, cy + 3, COL_WHITE);
            if (!cost.getResearchNeeded().isEmpty()) {
                String res = "Research: " + String.join(", ", cost.getResearchNeeded());
                g.drawString(this.font, truncate(res, 26), px + 162, cy + 3, COL_IN_PROG);
            }
            cy += 16;
        }

        // Materials collapsible
        matsToggleY = cy;
        cy = renderMaterials(g, result, px, cy);

        // Chain steps (paginated)
        renderChainSteps(g, result.getChain(), px, cy);
    }

    private void renderNoMatch(GuiGraphics g, PlannerPanelPayload.GoalResult result, int px, int cy) {
        g.drawString(this.font,
            "No match: \"" + truncate(result.getInput(), 28) + "\"",
            px + 5, cy, COL_RED);
        cy += LINE_H + 3;

        List<PlannerDependencyRegistry.BuildingNode> suggestions = result.getSuggestions();
        if (suggestions.isEmpty()) {
            g.drawString(this.font, "No suggestions found.", px + 5, cy, COL_GRAY);
        } else {
            g.drawString(this.font, "Did you mean:", px + 5, cy, COL_YELLOW);
            cy += LINE_H;
            for (PlannerDependencyRegistry.BuildingNode s : suggestions) {
                String line = "  - " + s.displayName + "  (" + s.id + ")";
                g.drawString(this.font, line, px + 5, cy, COL_WHITE);
                cy += LINE_H;
            }
        }
    }

    // Returns cy after the toggle row (and expanded content if open)
    private int renderMaterials(GuiGraphics g, PlannerPanelPayload.GoalResult result, int px, int cy) {
        // Toggle row
        g.fill(px + 2, cy, px + PANEL_W - 2, cy + 12, COL_MAT_BG);
        String toggle = (matsExpanded ? "v" : ">") + " Materials needed";
        g.drawString(this.font, toggle, px + 5, cy + 2, COL_GRAY);
        if (!result.hasWarehouse()) {
            g.drawString(this.font, "(no warehouse)", px + PANEL_W - 90, cy + 2, COL_RED);
        }
        cy += 13;

        if (!matsExpanded) return cy;

        List<PlannerPanelPayload.MaterialEntry> mats = result.getMaterials();
        if (mats.isEmpty()) {
            g.drawString(this.font, "  Material data pending API implementation.", px + 5, cy, COL_GRAY);
            cy += LINE_H;
            return cy;
        }

        for (PlannerPanelPayload.MaterialEntry m : mats) {
            if (m.getDeficit() <= 0) {
                g.drawString(this.font,
                    "  \u2713 " + m.getItemDisplayName() + " satisfied",
                    px + 5, cy, COL_GREEN);
            } else {
                String line = "  " + truncate(m.getItemDisplayName(), 14)
                    + "  need " + m.getDeficit()
                    + "  (" + m.getRequired() + " req, " + m.getInWarehouse() + " in wh)";
                g.drawString(this.font, line, px + 5, cy, COL_WHITE);
            }
            cy += LINE_H;
        }
        return cy;
    }

    private void renderChainSteps(GuiGraphics g, List<PlannerPanelPayload.ChainStep> chain,
                                  int px, int listStartY) {
        int start = currentPage * CHAIN_PER_PAGE;
        int end   = Math.min(start + CHAIN_PER_PAGE, chain.size());

        g.enableScissor(px, listStartY, px + PANEL_W, contentBottom);
        int cy = listStartY;
        for (int i = start; i < end; i++) {
            cy = renderChainStep(g, chain.get(i), px, cy);
        }
        g.disableScissor();
    }

    private int renderChainStep(GuiGraphics g, PlannerPanelPayload.ChainStep cs, int px, int cy) {
        PlannerDependencyRegistry.DependencyStep step = cs.getStep();

        String prefix;
        int color;
        if (cs.isCompleted()) {
            prefix = "  \u2713 ";
            color  = COL_DONE;
        } else if (cs.isFirstIncomplete()) {
            prefix = " \u25ba ";
            color  = COL_FIRST_INC;
        } else {
            prefix = "    ";
            color  = COL_WHITE;
        }

        String typeTag = switch (step.stepType) {
            case BUILDING        -> "";
            case BUILDING_PREREQ -> "[prereq] ";
            case RESEARCH        -> "[research] ";
        };

        String detail = "";
        if (step.stepType == PlannerDependencyRegistry.DependencyStep.StepType.BUILDING_PREREQ) {
            detail = " (L" + step.minTotalLevel + " total)";
        } else if (step.stepType == PlannerDependencyRegistry.DependencyStep.StepType.RESEARCH
                && step.universityLevelRequired > 0) {
            detail = " (Univ L" + step.universityLevelRequired + ")";
        }

        String line = prefix + typeTag + truncate(step.displayName, 28) + detail;
        g.drawString(this.font, line, px + 4, cy, color);
        return cy + LINE_H + 1;
    }

    // ---- Helpers ----

    private int totalPages() {
        if (currentPayload.getMode() == PlannerPanelPayload.Mode.SNAPSHOT) {
            return Math.max(1, (int) Math.ceil(
                (double) currentPayload.getRecommendations().size() / SNAP_PER_PAGE));
        }
        PlannerPanelPayload.GoalResult gr = currentPayload.getGoalResult();
        if (gr != null && gr.getChain() != null) {
            return Math.max(1, (int) Math.ceil(
                (double) gr.getChain().size() / CHAIN_PER_PAGE));
        }
        return 1;
    }

    private static String truncate(String s, int maxChars) {
        if (s == null) return "";
        return s.length() <= maxChars ? s : s.substring(0, maxChars - 1) + "\u2026";
    }
}
