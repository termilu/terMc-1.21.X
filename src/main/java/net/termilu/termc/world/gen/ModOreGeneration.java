package net.termilu.termc.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.termilu.termc.world.ModPlacedFeatures;

/**
 * Class responsible for generating ores in different biomes.
 */
public class ModOreGeneration {

    /**
     * Registers the ore generation features for different biomes.
     * Adds Fluorite ore generation to the Overworld, Nether, and End biomes.
     */
    public static void generateOres() {
        // Add Fluorite ore generation to the Overworld biomes
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.FLUORITE_ORE_PLACED_KEY);

        // Add Fluorite ore generation to the Nether biomes
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.NETHER_FLUORITE_ORE_PLACED_KEY);

        // Add Fluorite ore generation to the End biomes
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.END_FLUORITE_ORE_PLACED_KEY);
    }
}