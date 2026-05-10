package io.github.senseidragon.dragontweaks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public class TerrainScanner {

    public static String scan(ServerLevel level, BlockPos center) {
        boolean ice = false, snow = false, water = false, lava = false,
                sand = false, gravel = false, farmland = false, forest = false,
                stone = false, ore = false, structures = false, village = false,
                crops = false;
        int cx = center.getX(), cy = center.getY(), cz = center.getZ();
        outer:
        for (int x = cx - 32; x <= cx + 32; x += 2) {
            for (int z = cz - 32; z <= cz + 32; z += 2) {
                for (int y = cy - 4; y <= cy + 4; y++) {
                    var block = level.getBlockState(new BlockPos(x, y, z)).getBlock();
                    if (!ice && (block == Blocks.ICE || block == Blocks.PACKED_ICE || block == Blocks.BLUE_ICE || block == Blocks.FROSTED_ICE)) ice = true;
                    else if (!snow && (block == Blocks.SNOW_BLOCK || block == Blocks.SNOW)) snow = true;
                    else if (!water && block == Blocks.WATER) water = true;
                    else if (!lava && block == Blocks.LAVA) lava = true;
                    else if (!sand && (block == Blocks.SAND || block == Blocks.RED_SAND)) sand = true;
                    else if (!gravel && block == Blocks.GRAVEL) gravel = true;
                    else if (!farmland && block == Blocks.FARMLAND) farmland = true;
                    else if (!crops && (block == Blocks.PUMPKIN || block == Blocks.MELON || block == Blocks.WHEAT || block == Blocks.CARROTS || block == Blocks.POTATOES || block == Blocks.BEETROOTS)) crops = true;
                    else if (!forest && (block == Blocks.OAK_LOG || block == Blocks.SPRUCE_LOG || block == Blocks.BIRCH_LOG || block == Blocks.JUNGLE_LOG || block == Blocks.DARK_OAK_LOG || block == Blocks.ACACIA_LOG)) forest = true;
                    else if (!stone && (block == Blocks.STONE || block == Blocks.DEEPSLATE || block == Blocks.COBBLESTONE)) stone = true;
                    else if (!ore && (block == Blocks.COAL_ORE || block == Blocks.IRON_ORE || block == Blocks.GOLD_ORE || block == Blocks.DEEPSLATE_IRON_ORE || block == Blocks.DEEPSLATE_GOLD_ORE)) ore = true;
                    else if (!structures && (block == Blocks.OAK_PLANKS || block == Blocks.SPRUCE_PLANKS || block == Blocks.COBBLESTONE_SLAB || block == Blocks.COBBLESTONE_STAIRS || block == Blocks.STONE_BRICKS || block == Blocks.COBBLESTONE_WALL || block == Blocks.TORCH || block == Blocks.LANTERN)) structures = true;
                    else if (!village && (block == Blocks.BELL || block == Blocks.HAY_BLOCK || block == Blocks.LECTERN || block == Blocks.BLAST_FURNACE || block == Blocks.SMOKER || block == Blocks.COMPOSTER || block == Blocks.FLETCHING_TABLE || block == Blocks.CARTOGRAPHY_TABLE)) village = true;
                    if (ice && snow && water && lava && sand && gravel && farmland && forest && stone && ore && structures && village && crops) break outer;
                }
            }
        }
        List<String> labels = new ArrayList<>();
        if (ice) labels.add("ice");
        if (snow) labels.add("snow");
        if (water) labels.add("water");
        if (lava) labels.add("lava");
        if (sand) labels.add("sand");
        if (gravel) labels.add("gravel");
        if (farmland) labels.add("farmland");
        if (crops) labels.add("crops");
        if (forest) labels.add("forest");
        if (stone) labels.add("stone");
        if (ore) labels.add("ore");
        if (village) labels.add("village");
        else if (structures) labels.add("structures");
        String terrainLine = labels.isEmpty() ? "none notable" : String.join(", ", labels);

        List<Integer> yValues = new ArrayList<>();
        for (int x = cx - 32; x <= cx + 32; x += 2) {
            for (int z = cz - 32; z <= cz + 32; z += 2) {
                yValues.add(level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z));
            }
        }
        double mean = 0;
        for (int y : yValues) mean += y;
        mean /= yValues.size();
        double variance = 0;
        for (int y : yValues) variance += (y - mean) * (y - mean);
        double stdDev = Math.sqrt(variance / yValues.size());
        String relief = stdDev < 3.0 ? "flat" : stdDev <= 8.0 ? "rolling" : "steep";

        return terrainLine + "\nTerrain relief: " + relief;
    }
}
