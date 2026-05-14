package io.github.senseidragon.dragontweaks;

import com.minecolonies.api.IMinecoloniesAPI;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.api.colony.buildings.ModBuildings;
import com.minecolonies.api.colony.workorders.IServerWorkOrder;
import com.minecolonies.api.research.ILocalResearchTree;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AssistantDebugCommand {

    private static final DateTimeFormatter TIMESTAMP_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private static final Set<String> VALID_TABLES = Set.of(
            "buildings", "beds", "happiness", "research", "citizens",
            "workers", "workorders", "jobs", "registry", "skills", "stats", "visitors");

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("assistant")
                        .requires(src -> src.hasPermission(2))
                        .then(Commands.literal("debug")
                                .then(Commands.argument("table", StringArgumentType.string())
                                        .executes(ctx -> runDebug(ctx,
                                                StringArgumentType.getString(ctx, "table")))
                                )
                        )
        );
    }

    private static int runDebug(CommandContext<CommandSourceStack> ctx, String table)
            throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        String lower = table.toLowerCase(Locale.ROOT);

        if (!VALID_TABLES.contains(lower)) {
            ctx.getSource().sendFailure(Component.literal(
                    "Unknown table: " + table + ". Valid tables: buildings, beds, happiness, " +
                    "research, citizens, workers, workorders, jobs, registry, skills, stats, visitors"));
            return 0;
        }

        String timestamp = LocalDateTime.now().format(TIMESTAMP_FMT);
        Path outFile = Path.of(".").toAbsolutePath().normalize()
                .resolve("dragontweaks_debug_" + lower + "_" + timestamp + ".txt");

        StringBuilder sb = new StringBuilder();
        try {
            if (!ModList.get().isLoaded("minecolonies")) {
                sb.append("MineColonies not loaded.");
                writeAndNotify(ctx, outFile, sb.toString());
                return 1;
            }

            IColony colony = null;
            if (!lower.equals("jobs") && !lower.equals("registry")) {
                colony = IMinecoloniesAPI.getInstance().getColonyManager()
                        .getIColonyByOwner(player.serverLevel(), player);
                if (colony == null) {
                    sb.append("No colony found for player.");
                    writeAndNotify(ctx, outFile, sb.toString());
                    return 1;
                }
            }

            switch (lower) {
                case "buildings"  -> handleBuildings(sb);
                case "beds"       -> handleBeds(sb, colony);
                case "happiness"  -> handleHappiness(sb, colony);
                case "research"   -> handleResearch(sb, colony);
                case "citizens"   -> handleCitizens(sb, colony);
                case "workers"    -> handleWorkers(sb, colony);
                case "workorders" -> handleWorkOrders(sb, colony);
                case "jobs"       -> handleJobs(sb);
                case "registry"   -> handleRegistry(sb);
                case "skills"     -> handleSkills(sb, colony);
                case "stats"      -> handleStats(sb, colony);
                case "visitors"   -> handleVisitors(sb, colony);
            }
        } catch (Exception e) {
            sb.append("Exception: ").append(e.getMessage());
        }

        writeAndNotify(ctx, outFile, sb.toString());
        return 1;
    }

    private static void writeAndNotify(CommandContext<CommandSourceStack> ctx,
                                       Path outFile, String content) {
        try {
            Files.writeString(outFile, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            ctx.getSource().sendFailure(Component.literal("Failed to write debug file: " + e.getMessage()));
            return;
        }
        ctx.getSource().sendSuccess(
                () -> Component.literal("Debug written to: " + outFile.toAbsolutePath()), false);
    }

    // -------------------------------------------------------------------------
    // Table handlers
    // -------------------------------------------------------------------------

    private static void handleBuildings(StringBuilder sb) {
        Map<String, DeferredHolder<?, ?>> holders = new LinkedHashMap<>();
        holders.put("townhall",      ModBuildings.townHall);
        holders.put("builders_hut",  ModBuildings.builder);
        holders.put("residence",     ModBuildings.home);
        holders.put("warehouse",     ModBuildings.wareHouse);
        holders.put("courier_post",  ModBuildings.deliveryman);
        holders.put("guard_tower",   ModBuildings.guardTower);
        holders.put("tavern",        ModBuildings.tavern);
        holders.put("university",    ModBuildings.university);
        holders.put("forester",      ModBuildings.lumberjack);
        holders.put("sawmill",       ModBuildings.sawmill);
        holders.put("fletcher",      ModBuildings.fletcher);
        holders.put("miner",         ModBuildings.miner);
        holders.put("blacksmith",    ModBuildings.blacksmith);
        holders.put("farmer",        ModBuildings.farmer);
        holders.put("fisher",        ModBuildings.fisherman);
        holders.put("cook",          ModBuildings.cook);
        holders.put("school",        ModBuildings.school);
        holders.put("library",       ModBuildings.library);
        holders.put("hospital",      ModBuildings.hospital);
        holders.put("mystical_site", ModBuildings.mysticalSite);
        for (Map.Entry<String, DeferredHolder<?, ?>> e : holders.entrySet()) {
            sb.append(e.getKey()).append(" -> ").append(e.getValue().getId()).append('\n');
        }
    }

    private static void handleBeds(StringBuilder sb, IColony colony) {
        for (IBuilding b : colony.getServerBuildingManager().getBuildings().values()) {
            sb.append(b.getPosition())
              .append(" | ").append(b.getBuildingType().getRegistryName())
              .append(" | ").append(b.getBuildingLevel())
              .append(" | ").append(b.isBuilt())
              .append('\n');
        }
    }

    private static void handleHappiness(StringBuilder sb, IColony colony) {
        for (ICitizenData citizen : colony.getCitizenManager().getCitizens()) {
            sb.append(citizen.getName()).append('\n');
            try {
                var handler = citizen.getCitizenHappinessHandler();
                for (String modName : handler.getModifiers()) {
                    var modifier = handler.getModifier(modName);
                    double factor = modifier.getFactor(citizen);
                    double weight = modifier.getWeight();
                    sb.append("  ").append(modName)
                      .append(" | ").append(factor)
                      .append(" | ").append(weight)
                      .append('\n');
                }
            } catch (Exception e) {
                sb.append("  [error: ").append(e.getMessage()).append("]\n");
            }
        }
    }

    private static void handleResearch(StringBuilder sb, IColony colony) {
        ILocalResearchTree tree = colony.getResearchManager().getResearchTree();
        sb.append("=== Completed ===\n");
        try {
            for (ResourceLocation rl : tree.getCompletedList()) {
                sb.append(rl).append('\n');
            }
        } catch (Exception e) {
            sb.append("[getCompletedList error: ").append(e.getMessage()).append("]\n");
        }
        sb.append("=== In Progress ===\n");
        try {
            sb.append(tree.getResearchInProgress().toString()).append('\n');
        } catch (Exception e) {
            sb.append("[getResearchInProgress error: ").append(e.getMessage()).append("]\n");
        }
    }

    private static void handleCitizens(StringBuilder sb, IColony colony) {
        for (ICitizenData c : colony.getCitizenManager().getCitizens()) {
            String job = c.getJob() != null ? c.getJob().getClass().getSimpleName() : "unemployed";
            String work = c.getWorkBuilding() != null ? c.getWorkBuilding().toString() : "none";
            String home = c.getHomeBuilding() != null ? c.getHomeBuilding().toString() : "none";
            sb.append(c.getId())
              .append(" | ").append(c.getName())
              .append(" | ").append(job)
              .append(" | ").append(work)
              .append(" | ").append(home)
              .append('\n');
        }
    }

    private static void handleWorkers(StringBuilder sb, IColony colony) {
        for (IBuilding b : colony.getServerBuildingManager().getBuildings().values()) {
            sb.append(b.getPosition())
              .append(" | ").append(b.getBuildingType().getRegistryName())
              .append(" | ").append(b.getBuildingLevel())
              .append(" | ").append(b.isBuilt())
              .append(" | ").append(b.isPendingConstruction());
            try {
                Method m = b.getClass().getMethod("getFirstModuleOccurance", Class.class);
                Object module = m.invoke(b, Class.forName(
                        "com.minecolonies.api.colony.buildings.IBuildingWorkerModule"));
                if (module != null) {
                    Method getAssigned = module.getClass().getMethod("getAssignedCitizen");
                    List<?> assigned = (List<?>) getAssigned.invoke(module);
                    if (!assigned.isEmpty()) {
                        Object first = assigned.get(0);
                        Method getName = first.getClass().getMethod("getName");
                        sb.append(" | ").append(getName.invoke(first));
                    } else {
                        sb.append(" | unassigned");
                    }
                } else {
                    sb.append(" | unassigned");
                }
            } catch (Exception e) {
                sb.append(" | worker lookup unsupported");
            }
            sb.append('\n');
        }
    }

    private static void handleWorkOrders(StringBuilder sb, IColony colony) {
        for (IServerWorkOrder wo : colony.getWorkManager().getWorkOrders().values()) {
            sb.append(wo.getID())
              .append(" | ").append(wo.getClass().getSimpleName())
              .append(" | ").append(wo.isClaimed());
            try {
                sb.append(" | ").append(wo.getLocation());
            } catch (Exception e) {
                sb.append(" | [no location]");
            }
            sb.append('\n');
        }
    }

    private static void handleJobs(StringBuilder sb) {
        try {
            for (ResourceLocation rl : IMinecoloniesAPI.getInstance().getJobRegistry().keySet()) {
                sb.append(rl).append('\n');
            }
        } catch (Exception e) {
            sb.append("[job registry error: ").append(e.getMessage()).append("]\n");
        }
    }

    private static void handleRegistry(StringBuilder sb) {
        try {
            for (ResourceLocation rl : IMinecoloniesAPI.getInstance().getBuildingRegistry().keySet()) {
                sb.append(rl).append('\n');
            }
        } catch (Exception e) {
            sb.append("[building registry error: ").append(e.getMessage()).append("]\n");
        }
    }

    private static void handleSkills(StringBuilder sb, IColony colony) {
        for (ICitizenData c : colony.getCitizenManager().getCitizens()) {
            sb.append(c.getName()).append('\n');
            try {
                var skillHandler = c.getCitizenSkillHandler();
                for (var entry : skillHandler.getSkills().entrySet()) {
                    sb.append("  ").append(entry.getKey())
                      .append(" | ").append(entry.getValue())
                      .append('\n');
                }
            } catch (Exception e) {
                sb.append("  [skill error: ").append(e.getMessage()).append("]\n");
            }
        }
    }

    private static void handleStats(StringBuilder sb, IColony colony) {
        try {
            sb.append(colony.getStatisticsManager().toString()).append('\n');
        } catch (Exception e) {
            sb.append("StatisticsManager exposed no queryable data.\n");
        }
    }

    private static void handleVisitors(StringBuilder sb, IColony colony) {
        try {
            var visitors = colony.getVisitorManager().getCivilianDataMap().values();
            for (var visitor : visitors) {
                try {
                    sb.append(visitor.getName())
                      .append(" | ").append(visitor.getClass().getSimpleName());
                } catch (Exception e) {
                    sb.append("[visitor] | [unknown]");
                }
                try {
                    var entity = visitor.getEntity().orElse(null);
                    if (entity != null) {
                        var feet = entity.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET);
                        sb.append(" | feet=").append(feet);
                        var nbt = new net.minecraft.nbt.CompoundTag();
                        entity.saveWithoutId(nbt);
                        sb.append(" | nbt_tags=").append(nbt.getAllKeys());
                    }
                } catch (Exception e) {
                    sb.append(" | [entity lookup error]");
                }
                sb.append('\n');
            }
        } catch (Exception e) {
            sb.append("Visitor API not exposed on ICitizenManager.\n");
        }
    }
}
