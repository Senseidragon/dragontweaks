package io.github.senseidragon.dragontweaks;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class AdvisorPanelScreen extends Screen {

    // Panel geometry
    private static final int PANEL_W      = 304;
    private static final int PANEL_H      = 250;
    private static final int ITEMS_PER_PAGE = 5;
    private static final int ROW_H        = 16;
    private static final int FACTOR_LINE_H = 10;
    // Expanded height per citizen: 10 factors + 1 commute line + 2px gap
    private static final int EXPANDED_EXTRA_H = (10 * FACTOR_LINE_H) + FACTOR_LINE_H + 2;

    // Colors (ARGB)
    private static final int COL_PANEL_BG   = 0xC0101010;
    private static final int COL_BORDER     = 0xFF4455AA;
    private static final int COL_HEADER_BG  = 0xFF12122A;
    private static final int COL_BANNER_ENV = 0xEE663300;
    private static final int COL_BANNER_SYS = 0xEE550011;
    private static final int COL_ROW_RED    = 0x44FF3333;
    private static final int COL_ROW_YELLOW = 0x44FFAA00;
    private static final int COL_ROW_OK     = 0x22FFFFFF;
    private static final int COL_EXPAND_BG  = 0x33FFFFFF;
    private static final int COL_RED        = 0xFFFF5555;
    private static final int COL_YELLOW     = 0xFFFFAA00;
    private static final int COL_GREEN      = 0xFF55FF55;
    private static final int COL_WHITE      = 0xFFFFFFFF;
    private static final int COL_GRAY       = 0xFFAAAAAA;
    private static final int COL_TITLE      = 0xFFFFFFAA;

    private final AdvisorPanelPayload payload;
    private int currentPage = 0;
    private final Set<Integer> expandedRows = new HashSet<>();

    // Computed each frame in render()
    private int panelLeft, panelTop;
    private int listTop, listBottom;

    private Button btnPrev;
    private Button btnNext;

    public AdvisorPanelScreen(AdvisorPanelPayload payload) {
        super(Component.literal("Colony Advisor"));
        this.payload = payload;
    }

    @Override
    protected void init() {
        panelLeft = (this.width - PANEL_W) / 2;
        panelTop  = (this.height - PANEL_H) / 2;

        int navY  = panelTop + PANEL_H - 20;
        int bw    = 48;

        btnPrev = addRenderableWidget(
            Button.builder(Component.literal("< Prev"), b -> { if (currentPage > 0) currentPage--; })
                  .bounds(panelLeft + 2, navY, bw, 16)
                  .build()
        );
        addRenderableWidget(
            Button.builder(Component.literal("Close"), b -> onClose())
                  .bounds(panelLeft + PANEL_W / 2 - bw / 2, navY, bw, 16)
                  .build()
        );
        btnNext = addRenderableWidget(
            Button.builder(Component.literal("Next >"), b -> { if (currentPage < getTotalPages() - 1) currentPage++; })
                  .bounds(panelLeft + PANEL_W - bw - 2, navY, bw, 16)
                  .build()
        );
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        // Darken world background
        this.renderBackground(g, mouseX, mouseY, partialTick);

        int px = panelLeft;
        int py = panelTop;

        // Panel background and border
        g.fill(px, py, px + PANEL_W, py + PANEL_H, COL_PANEL_BG);
        g.hLine(px, px + PANEL_W - 1, py, COL_BORDER);
        g.hLine(px, px + PANEL_W - 1, py + PANEL_H - 1, COL_BORDER);
        g.vLine(px, py, py + PANEL_H - 1, COL_BORDER);
        g.vLine(px + PANEL_W - 1, py, py + PANEL_H - 1, COL_BORDER);

        int y = py + 4;

        // Title
        g.drawCenteredString(this.font, this.title, px + PANEL_W / 2, y, COL_TITLE);
        y += 13;

        // Environmental warnings banner (conditional)
        List<ColonyDiagnosticReport.EnvironmentalFlag> flags = payload.getEnvironmentalFlags();
        if (!flags.isEmpty()) {
            g.fill(px + 2, y, px + PANEL_W - 2, y + 16, COL_BANNER_ENV);
            String msg = "Warning: " + buildFlagText(flags) + " — data may be unreliable";
            g.drawString(this.font, msg, px + 5, y + 4, COL_WHITE);
            y += 18;
        }

        // Systemic pattern banner (conditional)
        ColonyDiagnosticReport.SystemicPattern pattern = payload.getSystemicPattern();
        if (pattern != null) {
            g.fill(px + 2, y, px + PANEL_W - 2, y + 16, COL_BANNER_SYS);
            g.drawString(this.font, patternLabel(pattern), px + 5, y + 4, COL_RED);
            y += 18;
        }

        // Colony summary header
        g.fill(px + 2, y, px + PANEL_W - 2, y + 20, COL_HEADER_BG);
        String happStr = String.format("Happiness: %.2f", payload.getOverallHappiness());
        String citStr  = "Citizens: " + payload.getCitizenCount() + " / " + payload.getHousingCap() + " beds";
        g.drawString(this.font, happStr, px + 6, y + 6, happinessColor(payload.getOverallHappiness()));
        g.drawString(this.font, citStr,  px + 162, y + 6, COL_GRAY);
        y += 22;

        // Citizen list area
        listTop    = y;
        listBottom = py + PANEL_H - 22;

        renderCitizenList(g);

        // Page counter
        int total = getTotalPages();
        String pageLabel = (currentPage + 1) + " / " + total;
        g.drawCenteredString(this.font, Component.literal(pageLabel), px + PANEL_W / 2, py + PANEL_H - 17, COL_GRAY);

        // Enable/disable nav buttons
        btnPrev.active = currentPage > 0;
        btnNext.active = currentPage < total - 1;

        // Render all added widgets (buttons)
        for (Renderable renderable : this.renderables) {
            renderable.render(g, mouseX, mouseY, partialTick);
        }
    }

    private void renderCitizenList(GuiGraphics g) {
        List<AdvisorPanelPayload.CitizenEntry> citizens = payload.getCitizens();

        if (citizens.isEmpty()) {
            g.drawString(this.font, "No citizens to display.", panelLeft + 6, listTop + 4, COL_GRAY);
            return;
        }

        int startIdx = currentPage * ITEMS_PER_PAGE;
        int endIdx   = Math.min(startIdx + ITEMS_PER_PAGE, citizens.size());

        g.enableScissor(panelLeft, listTop, panelLeft + PANEL_W, listBottom);
        int cy = listTop;
        for (int i = startIdx; i < endIdx; i++) {
            cy = renderRow(g, citizens.get(i), i, cy);
        }
        g.disableScissor();
    }

    private int renderRow(GuiGraphics g, AdvisorPanelPayload.CitizenEntry c, int idx, int cy) {
        boolean expanded = expandedRows.contains(idx);
        int px = panelLeft;

        // Tier colors
        int rowBg = switch (c.getTier()) {
            case RED     -> COL_ROW_RED;
            case YELLOW  -> COL_ROW_YELLOW;
            case HEALTHY -> COL_ROW_OK;
        };
        int dotColor = switch (c.getTier()) {
            case RED     -> COL_RED;
            case YELLOW  -> COL_YELLOW;
            case HEALTHY -> COL_GREEN;
        };

        // Collapsed header row background
        g.fill(px + 2, cy, px + PANEL_W - 2, cy + ROW_H, rowBg);

        // Severity dot (6×6 square)
        g.fill(px + 5, cy + 5, px + 11, cy + 11, dotColor);

        // Expand arrow
        g.drawString(this.font, expanded ? "v" : ">", px + 14, cy + 4, COL_GRAY);

        // Citizen name (truncated to 14 chars)
        String name = c.getName().length() > 14 ? c.getName().substring(0, 13) + "." : c.getName();
        g.drawString(this.font, name, px + 24, cy + 4, COL_WHITE);

        // Worst factor
        String wf = c.getWorstFactorId() + ": " + String.format("%.2f", c.getWorstFactorValue());
        g.drawString(this.font, wf, px + 116, cy + 4, dotColor);

        // Additional complaints badge
        if (c.getAdditionalComplaintsCount() > 0) {
            g.drawString(this.font, "+" + c.getAdditionalComplaintsCount(), px + 196, cy + 4, COL_YELLOW);
        }

        // Commute flag
        if (c.isCommuteFlagged()) {
            g.drawString(this.font, "[far]", px + PANEL_W - 34, cy + 4, COL_RED);
        }

        cy += ROW_H;

        // Expanded section
        if (expanded) {
            // 10 canonical factors
            for (AdvisorPanelPayload.FactorDetail fd : c.getFactors()) {
                g.fill(px + 4, cy, px + PANEL_W - 4, cy + FACTOR_LINE_H, COL_EXPAND_BG);
                int fc = switch (fd.getSeverity()) {
                    case RED     -> COL_RED;
                    case YELLOW  -> COL_YELLOW;
                    case HEALTHY -> COL_GREEN;
                };
                // Factor severity dot
                g.fill(px + 7, cy + 2, px + 12, cy + 8, fc);
                String fLine = fd.getFactorId() + ": " + String.format("%.2f", fd.getValue())
                               + "  [" + fd.getModifierTypeLabel() + "]";
                g.drawString(this.font, fLine, px + 15, cy + 1, fc);
                cy += FACTOR_LINE_H;
            }

            // Commute line
            g.fill(px + 4, cy, px + PANEL_W - 4, cy + FACTOR_LINE_H, COL_EXPAND_BG);
            String commuteLine;
            int commuteColor;
            if (c.isCommuteFlagged()) {
                commuteLine = "commute: " + c.getCommuteDistance()
                              + " blocks — exceeds " + c.getCommuteThreshold() + "-block threshold";
                commuteColor = COL_RED;
            } else {
                commuteLine = "commute: " + c.getCommuteDistance() + " blocks";
                commuteColor = COL_GRAY;
            }
            g.drawString(this.font, commuteLine, px + 15, cy + 1, commuteColor);
            cy += FACTOR_LINE_H + 2;
        }

        return cy;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Let buttons handle their own clicks first
        if (super.mouseClicked(mouseX, mouseY, button)) return true;

        if (button != 0) return false;

        int mx = (int) mouseX;
        int my = (int) mouseY;

        // Only handle clicks within the citizen list area
        if (mx < panelLeft || mx >= panelLeft + PANEL_W || my < listTop || my >= listBottom) {
            return false;
        }

        List<AdvisorPanelPayload.CitizenEntry> citizens = payload.getCitizens();
        int startIdx = currentPage * ITEMS_PER_PAGE;
        int endIdx   = Math.min(startIdx + ITEMS_PER_PAGE, citizens.size());

        int cy = listTop;
        for (int i = startIdx; i < endIdx; i++) {
            // Click on the collapsed header row toggles expand/collapse
            if (my >= cy && my < cy + ROW_H) {
                if (expandedRows.contains(i)) {
                    expandedRows.remove(i);
                } else {
                    expandedRows.add(i);
                }
                return true;
            }
            cy += ROW_H;
            if (expandedRows.contains(i)) {
                cy += EXPANDED_EXTRA_H;
            }
        }

        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        expandedRows.clear();
        super.onClose();
    }

    // ---- helpers ----

    private int getTotalPages() {
        return Math.max(1, (int) Math.ceil((double) payload.getCitizens().size() / ITEMS_PER_PAGE));
    }

    private int happinessColor(double h) {
        if (h < 0.5) return COL_RED;
        if (h < 0.9) return COL_YELLOW;
        return COL_GREEN;
    }

    private static String buildFlagText(List<ColonyDiagnosticReport.EnvironmentalFlag> flags) {
        List<String> parts = new ArrayList<>();
        for (ColonyDiagnosticReport.EnvironmentalFlag f : flags) {
            parts.add(switch (f) {
                case DAYLIGHT_CYCLE_DISABLED -> "daylight cycle off";
                case RAID_ACTIVE             -> "raid in progress";
                case THUNDERSTORM            -> "thunderstorm";
            });
        }
        return String.join(", ", parts);
    }

    private static String patternLabel(ColonyDiagnosticReport.SystemicPattern p) {
        return switch (p) {
            case NEWLY_FOUNDED_ALL_RED         -> "Pattern: Newly founded — all citizens struggling";
            case ALL_CITIZENS_RED              -> "Pattern: Systemic — all citizens have critical issues";
            case HOUSING_SLEEP_COMMUTE_CLUSTER -> "Pattern: Housing/sleep/commute cluster (2+ citizens)";
        };
    }
}
