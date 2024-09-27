package net.termilu.termc.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.termilu.termc.world.ModPlacedFeatures;

public class ModTreeGeneration {

    public static void generateTrees(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.SNOWY_PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.BLACKWOOD_PLACED_KEY);
    }
}
