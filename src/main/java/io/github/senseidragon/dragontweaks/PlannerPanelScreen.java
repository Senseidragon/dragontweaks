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
import net.neoforged.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class PlannerPanelScreen extends Screen {

    private static final Logger LOGGER = LogManager.getLogger();

    // Panel geometry
    private static final int PANEL_W        = 324;
    private static final int PANEL_H        = 270;
    private static final int SNAP_PER_PAGE  = 3;
    private static final int CHAIN_PER_PAGE = 8;
    private static final int ITEMS_PER_PAGE = 8;
    private static final int LINE_H         = 10;
    private static final int REC_SEP        = 4;

    // Colors (ARGB)
    private static final int COL_PANEL_BG  = 0xC0101010;
    private static final int COL_BORDER    = 0xFF4455AA;
    private static final int COL_HEADER_BG = 0xFF12122A;
    private static final int COL_COST_BG   = 0xFF111126;
    private static final int COL_MAT_BG    = 0xFF0E0E1E;
    private static final int COL_SEP       = 0xFF333355;
    private static final int COL_CRISIS    = 0xFFFF5555;
    private static final int COL_NO_CRISIS = 0xFFAAAAAA;
    private static final int COL_IN_PROG   = 0xFF55AAFF;
    private static final int COL_RED       = 0xFFFF5555;
    private static final int COL_YELLOW    = 0xFFFFAA00;
    private static final int COL_GREEN     = 0xFF55FF55;
    private static final int COL_WHITE     = 0xFFFFFFFF;
    private static final int COL_GRAY      = 0xFF888888;
    private static final int COL_TITLE     = 0xFFFFFFAA;
    private static final int COL_DONE      = 0xFF666666;
    private static final int COL_FIRST_INC = 0xFFFFDD44;

    private PlannerPanelPayload currentPayload;
    @Nullable private final Consumer<String> goalCallback;

    private int currentPage    = 0;
    private boolean matsExpanded = false;

    // Set during render(), read in mouseClicked()
    private int contentTop    = 0;
    private int contentBottom = 0;
    private int matsToggleY   = -1;

    private EditBox goalInput;
    private Button  btnPrev;
    private Button  btnNext;
    private Button  btnBrowse;
    private Button  btnBack;

    // ---- Browse mode state ----

    private enum BrowseLevel { PACK_LIST, CATEGORY_LIST, BUILDING_LIST, LEVEL_SELECTOR }

    private boolean     browseActive   = false;
    private BrowseLevel browseLevel    = BrowseLevel.PACK_LIST;
    private int         browsePage     = 0;

    private final List<String>  browsePacks      = new ArrayList<>();
    private final List<String>  browseCategories = new ArrayList<>();
    private final List<String>  browseBuildings  = new ArrayList<>();
    private final List<Integer> browseLevels     = new ArrayList<>();

    private String selectedPack     = null;
    private String selectedCategory = null;
    private String selectedBuilding = null;
    private int    selectedLevel    = 1;

    private Map<String, Integer> browseMaterials = Collections.emptyMap();

    // Hitbox tracking (set during renderBrowse, read in mouseClicked)
    private final int[] browseEntryYs    = new int[ITEMS_PER_PAGE];
    private       int   browseEntryCount = 0;
    private       int   levelBtnY        = -1;
    private final int[] levelBtnXs       = new int[5];
    private final int[] levelBtnWidths   = new int[5];

    // ---- Constructor ----

    public PlannerPanelScreen(PlannerPanelPayload initialPayload,
                              @Nullable Consumer<String> goalCallback) {
        super(Component.literal("Colony Planner"));
        this.currentPayload = initialPayload;
        this.goalCallback   = goalCallback;
    }

    public void updatePayload(PlannerPanelPayload newPayload) {
        this.currentPayload = newPayload;
        this.currentPage    = 0;
        this.matsExpanded   = false;
        this.matsToggleY    = -1;
    }

    // ---- Screen lifecycle ----

    @Override
    protected void init() {
        int px   = (this.width  - PANEL_W) / 2;
        int py   = (this.height - PANEL_H) / 2;
        int navY = py + PANEL_H - 20;
        int bw   = 48;

        // Goal input — narrowed to make room for Browse button
        int browseW = 55;
        int fieldX  = px + 36;
        int fieldW  = PANEL_W - 40 - browseW - 2;
        goalInput = new EditBox(this.font, fieldX, py + 16, fieldW, 12, Component.empty());
        goalInput.setMaxLength(60);
        goalInput.setBordered(true);
        goalInput.setFocused(false);
        addRenderableWidget(goalInput);

        // Browse toggle button — sits to the right of the goal input
        btnBrowse = addRenderableWidget(
            Button.builder(Component.literal("Browse"), b -> toggleBrowse())
                  .bounds(fieldX + fieldW + 2, py + 15, browseW, 14)
                  .build()
        );

        // Back button — visible only in browse mode
        btnBack = addRenderableWidget(
            Button.builder(Component.literal("\u2190 Back"), b -> browseBack())
                  .bounds(px + 2, py + 33, 45, 11)
                  .build()
        );
        btnBack.visible = false;

        // Navigation buttons
        btnPrev = addRenderableWidget(
            Button.builder(Component.literal("< Prev"), b -> prevPage())
                  .bounds(px + 2, navY, bw, 16)
                  .build()
        );
        addRenderableWidget(
            Button.builder(Component.literal("Close"), b -> onClose())
                  .bounds(px + PANEL_W / 2 - bw / 2, navY, bw, 16)
                  .build()
        );
        btnNext = addRenderableWidget(
            Button.builder(Component.literal("Next >"), b -> nextPage())
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

        int mx = (int) mouseX;
        int my = (int) mouseY;

        // Browse entry clicks
        if (browseActive && browseLevel != BrowseLevel.LEVEL_SELECTOR) {
            for (int i = 0; i < browseEntryCount; i++) {
                if (my >= browseEntryYs[i] && my < browseEntryYs[i] + LINE_H + 1) {
                    selectBrowseEntry(browsePage * ITEMS_PER_PAGE + i);
                    return true;
                }
            }
        }

        // Level button clicks
        if (browseActive && browseLevel == BrowseLevel.LEVEL_SELECTOR && levelBtnY >= 0) {
            if (my >= levelBtnY && my < levelBtnY + LINE_H) {
                for (int i = 0; i < browseLevels.size() && i < levelBtnXs.length; i++) {
                    if (mx >= levelBtnXs[i] && mx < levelBtnXs[i] + levelBtnWidths[i]) {
                        selectLevel(browseLevels.get(i));
                        return true;
                    }
                }
            }
        }

        // Materials toggle click detection (goal input mode only)
        if (!browseActive && matsToggleY >= 0 && my >= matsToggleY && my < matsToggleY + 13) {
            matsExpanded = !matsExpanded;
            currentPage  = 0;
            return true;
        }

        return false;
    }

    @Override
    public boolean isPauseScreen() { return false; }

    // ---- Render ----

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(g, mouseX, mouseY, partialTick);

        int px = (this.width  - PANEL_W) / 2;
        int py = (this.height - PANEL_H) / 2;

        // Panel chrome
        g.fill(px, py, px + PANEL_W, py + PANEL_H, COL_PANEL_BG);
        g.hLine(px, px + PANEL_W - 1, py,                COL_BORDER);
        g.hLine(px, px + PANEL_W - 1, py + PANEL_H - 1,  COL_BORDER);
        g.vLine(px,                py, py + PANEL_H - 1,  COL_BORDER);
        g.vLine(px + PANEL_W - 1, py, py + PANEL_H - 1,  COL_BORDER);

        // Title
        g.drawCenteredString(this.font, this.title, px + PANEL_W / 2, py + 4, COL_TITLE);

        // "Goal:" label
        g.drawString(this.font, "Goal:", px + 4, py + 19, COL_GRAY);

        int y = py + 32;

        // Worker/bed header strip — snapshot mode only, not in browse
        if (!browseActive
                && currentPayload.getMode() == PlannerPanelPayload.Mode.SNAPSHOT
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
        matsToggleY   = -1;
        levelBtnY     = -1;

        if (browseActive) {
            renderBrowse(g, px);
        } else if (currentPayload.getMode() == PlannerPanelPayload.Mode.SNAPSHOT) {
            renderSnapshot(g, px);
        } else {
            renderGoalMode(g, px);
        }

        // Page counter + nav button state
        int tp         = totalPages();
        int activePage = browseActive ? browsePage : currentPage;
        g.drawCenteredString(this.font,
            Component.literal((activePage + 1) + " / " + tp),
            px + PANEL_W / 2, py + PANEL_H - 17, COL_GRAY);

        btnPrev.active  = activePage > 0;
        btnNext.active  = activePage < tp - 1;
        btnBack.visible = browseActive;

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
        g.fill(px + 2, cy, px + PANEL_W - 2, cy + 1, COL_SEP);
        cy += 2;

        boolean crisis = rec.getPriority() == PlannerPanelPayload.Priority.CRISIS;
        String badge   = crisis ? "[!]" : "[ ]";
        int badgeColor = crisis ? COL_CRISIS : COL_NO_CRISIS;
        g.drawString(this.font, badge, px + 4, cy, badgeColor);

        String name = truncate(rec.getTargetDisplayName(), 20);
        g.drawString(this.font, name, px + 26, cy, COL_WHITE);

        if (rec.isInProgress()) {
            g.drawString(this.font, "[In Progress]", px + PANEL_W - 82, cy, COL_IN_PROG);
        }
        cy += LINE_H + 1;

        String blocker = rec.getImmediateBlocker();
        if (blocker != null && !blocker.isEmpty()) {
            g.drawString(this.font, "  \u25b8 " + truncate(blocker, 36), px + 4, cy, COL_YELLOW);
            cy += LINE_H;
        }

        if (rec.getResearchPrereqName() != null) {
            g.drawString(this.font, "  Research: " + rec.getResearchPrereqName(), px + 4, cy, COL_IN_PROG);
            cy += LINE_H;
        }

        if (rec.getWorkerSlotsNeeded() > 0) {
            g.drawString(this.font, "  Worker slot needed", px + 4, cy, COL_RED);
            cy += LINE_H;
        }

        if (rec.getBedDelta() > 0) {
            g.drawString(this.font, "  +" + rec.getBedDelta() + " bed(s) needed", px + 4, cy, COL_RED);
            cy += LINE_H;
        }

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

        matsToggleY = cy;
        cy = renderMaterials(g, result, px, cy);

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

    private int renderMaterials(GuiGraphics g, PlannerPanelPayload.GoalResult result, int px, int cy) {
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

    // ---- Browse mode ----

    private void renderBrowse(GuiGraphics g, int px) {
        // Breadcrumb header (offset right of the Back button)
        String crumb = buildBreadcrumb();
        g.drawString(this.font, "Browsing: " + crumb, px + 52, contentTop + 2, COL_GRAY);
        g.hLine(px + 2, px + PANEL_W - 3, contentTop + 13, COL_SEP);

        int listTop = contentTop + 15;
        browseEntryCount = 0;

        if (browseLevel == BrowseLevel.LEVEL_SELECTOR) {
            renderBrowseLevelSelector(g, px, listTop);
            return;
        }

        List<String> entries = getCurrentBrowseList();
        int start = browsePage * ITEMS_PER_PAGE;
        int end   = Math.min(start + ITEMS_PER_PAGE, entries.size());

        int cy = listTop;
        if (entries.isEmpty()) {
            g.drawString(this.font, "  (empty)", px + 5, cy, COL_GRAY);
        } else {
            for (int i = start; i < end; i++) {
                browseEntryYs[browseEntryCount++] = cy;
                g.drawString(this.font, "  " + entries.get(i), px + 5, cy, COL_WHITE);
                cy += LINE_H + 1;
            }
        }
    }

    private void renderBrowseLevelSelector(GuiGraphics g, int px, int cy) {
        // Level buttons row
        levelBtnY = cy;
        g.drawString(this.font, "Level:", px + 5, cy, COL_GRAY);
        int bx = px + 47;
        for (int i = 0; i < browseLevels.size() && i < levelBtnXs.length; i++) {
            int lv = browseLevels.get(i);
            String label = "[" + lv + "]";
            int color = (lv == selectedLevel) ? COL_YELLOW : COL_WHITE;
            g.drawString(this.font, label, bx, cy, color);
            levelBtnXs[i]    = bx;
            levelBtnWidths[i] = this.font.width(label);
            bx += levelBtnWidths[i] + 4;
        }
        cy += LINE_H + 4;

        // Materials list for selected level
        if (browseMaterials.isEmpty()) {
            g.drawString(this.font, "  No material data for this level.", px + 5, cy, COL_GRAY);
            return;
        }

        g.drawString(this.font, "Materials:", px + 5, cy, COL_TITLE);
        cy += LINE_H + 1;

        List<Map.Entry<String, Integer>> matList = new ArrayList<>(browseMaterials.entrySet());
        int start = browsePage * ITEMS_PER_PAGE;
        int end   = Math.min(start + ITEMS_PER_PAGE, matList.size());

        g.enableScissor(px, cy, px + PANEL_W, contentBottom);
        for (int i = start; i < end; i++) {
            Map.Entry<String, Integer> e = matList.get(i);
            g.drawString(this.font,
                "  " + truncate(e.getKey(), 22) + "  x" + e.getValue(),
                px + 5, cy, COL_WHITE);
            cy += LINE_H;
        }
        g.disableScissor();
    }

    // ---- Browse mode helpers ----

    private void toggleBrowse() {
        browseActive = !browseActive;
        if (browseActive) {
            browseLevel    = BrowseLevel.PACK_LIST;
            selectedPack   = null;
            selectedCategory = null;
            selectedBuilding = null;
            browsePage     = 0;
            populatePacks();
        }
    }

    private void browseBack() {
        switch (browseLevel) {
            case PACK_LIST -> {
                browseActive = false;
            }
            case CATEGORY_LIST -> {
                browseLevel  = BrowseLevel.PACK_LIST;
                selectedPack = null;
                browsePage   = 0;
            }
            case BUILDING_LIST -> {
                browseLevel      = BrowseLevel.CATEGORY_LIST;
                selectedCategory = null;
                browsePage       = 0;
            }
            case LEVEL_SELECTOR -> {
                browseLevel      = BrowseLevel.BUILDING_LIST;
                selectedBuilding = null;
                browseMaterials  = Collections.emptyMap();
                browsePage       = 0;
            }
        }
    }

    private void selectBrowseEntry(int idx) {
        switch (browseLevel) {
            case PACK_LIST -> {
                if (idx < browsePacks.size()) {
                    selectedPack = browsePacks.get(idx);
                    populateCategories(selectedPack);
                    browseLevel = BrowseLevel.CATEGORY_LIST;
                    browsePage  = 0;
                }
            }
            case CATEGORY_LIST -> {
                if (idx < browseCategories.size()) {
                    selectedCategory = browseCategories.get(idx);
                    populateBuildings(selectedPack, selectedCategory);
                    browseLevel = BrowseLevel.BUILDING_LIST;
                    browsePage  = 0;
                }
            }
            case BUILDING_LIST -> {
                if (idx < browseBuildings.size()) {
                    selectedBuilding = browseBuildings.get(idx);
                    populateLevels(selectedPack, selectedCategory, selectedBuilding);
                    selectedLevel   = browseLevels.isEmpty() ? 1 : browseLevels.get(0);
                    browseMaterials = BlueprintMaterialsLoader.getMaterials(
                        selectedPack, selectedCategory + "/" + selectedBuilding, selectedLevel);
                    browseLevel = BrowseLevel.LEVEL_SELECTOR;
                    browsePage  = 0;
                }
            }
            default -> { /* LEVEL_SELECTOR handled in mouseClicked */ }
        }
    }

    private void selectLevel(int level) {
        selectedLevel   = level;
        browseMaterials = BlueprintMaterialsLoader.getMaterials(
            selectedPack, selectedCategory + "/" + selectedBuilding, level);
        browsePage = 0;
    }

    private String buildBreadcrumb() {
        return switch (browseLevel) {
            case PACK_LIST       -> "Packs";
            case CATEGORY_LIST   -> selectedPack;
            case BUILDING_LIST   -> selectedPack + " > " + selectedCategory;
            case LEVEL_SELECTOR  -> selectedPack + " > " + selectedCategory + " > " + selectedBuilding;
        };
    }

    private List<String> getCurrentBrowseList() {
        return switch (browseLevel) {
            case PACK_LIST      -> browsePacks;
            case CATEGORY_LIST  -> browseCategories;
            case BUILDING_LIST  -> browseBuildings;
            case LEVEL_SELECTOR -> Collections.emptyList();
        };
    }

    // ---- Browse filesystem population ----

    private void populatePacks() {
        browsePacks.clear();
        Path base = FMLPaths.GAMEDIR.get().resolve("config").resolve("DragonTweaks");
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(base, Files::isDirectory)) {
            for (Path p : ds) browsePacks.add(p.getFileName().toString());
        } catch (IOException e) {
            LOGGER.warn("[DragonTweaks] Could not list packs: {}", e.getMessage());
        }
        Collections.sort(browsePacks);
    }

    private void populateCategories(String pack) {
        browseCategories.clear();
        Path dir = FMLPaths.GAMEDIR.get().resolve("config").resolve("DragonTweaks").resolve(pack);
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, Files::isDirectory)) {
            for (Path p : ds) browseCategories.add(p.getFileName().toString());
        } catch (IOException e) {
            LOGGER.warn("[DragonTweaks] Could not list categories in {}: {}", pack, e.getMessage());
        }
        Collections.sort(browseCategories);
    }

    private void populateBuildings(String pack, String category) {
        browseBuildings.clear();
        Path dir = FMLPaths.GAMEDIR.get().resolve("config").resolve("DragonTweaks")
                       .resolve(pack).resolve(category);
        Set<String> seen = new LinkedHashSet<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, "*.json")) {
            for (Path p : ds) {
                String fname = p.getFileName().toString();
                String base  = fname.substring(0, fname.length() - 5).replaceAll("\\d+$", "");
                if (!base.isEmpty()) seen.add(base);
            }
        } catch (IOException e) {
            LOGGER.warn("[DragonTweaks] Could not list buildings in {}/{}: {}", pack, category, e.getMessage());
        }
        browseBuildings.addAll(seen);
        Collections.sort(browseBuildings);
    }

    private void populateLevels(String pack, String category, String buildingBase) {
        browseLevels.clear();
        Path dir = FMLPaths.GAMEDIR.get().resolve("config").resolve("DragonTweaks")
                       .resolve(pack).resolve(category);
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, buildingBase + "*.json")) {
            for (Path p : ds) {
                String fname  = p.getFileName().toString();
                String suffix = fname.substring(buildingBase.length(), fname.length() - 5);
                try { browseLevels.add(Integer.parseInt(suffix)); }
                catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            LOGGER.warn("[DragonTweaks] Could not list levels for {}: {}", buildingBase, e.getMessage());
        }
        Collections.sort(browseLevels);
    }

    // ---- Pagination ----

    private void prevPage() {
        if (browseActive) { if (browsePage > 0) browsePage--; }
        else              { if (currentPage  > 0) currentPage--; }
    }

    private void nextPage() {
        if (browseActive) { if (browsePage  < totalPages() - 1) browsePage++; }
        else              { if (currentPage < totalPages() - 1) currentPage++; }
    }

    private int totalPages() {
        if (browseActive) {
            if (browseLevel == BrowseLevel.LEVEL_SELECTOR) {
                if (browseMaterials.isEmpty()) return 1;
                return Math.max(1, (int) Math.ceil((double) browseMaterials.size() / ITEMS_PER_PAGE));
            }
            return Math.max(1, (int) Math.ceil(
                (double) getCurrentBrowseList().size() / ITEMS_PER_PAGE));
        }
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

    // ---- Helpers ----

    private static String truncate(String s, int maxChars) {
        if (s == null) return "";
        return s.length() <= maxChars ? s : s.substring(0, maxChars - 1) + "\u2026";
    }
}
