package net.termilu.termc.world.gen;

/**
 * Class responsible for initializing world generation features for the mod.
 * This includes ore generation and tree generation.
 */
public class ModWorldGeneration {

    /**
     * Initializes the world generation features for the mod.
     * This method sets up the order of generation and calls the methods
     * responsible for generating ores and trees.
     */
    public static void generateModWorldGeneration() {

        /*
            Order of generation:

            RAW_GENERATION("raw_generation"),
            LAKES("lakes"),
            LOCAL_MODIFICATIONS("local_modifications"),
            UNDERGROUND_STRUCTURES("underground_structures"),
            SURFACE_STRUCTURES("surface_structures"),
            STRONGHOLDS("strongholds"),
            UNDERGROUND_ORES("underground_ores"),
            UNDERGROUND_DECORATION("underground_decoration"),
            FLUID_SPRINGS("fluid_springs"),
            VEGETAL_DECORATION("vegetal_decoration"),
            TOP_LAYER_MODIFICATION("top_layer_modification");
         */

        // Generate ores in the world
        ModOreGeneration.generateOres();

        // Generate trees in the world
        ModTreeGeneration.generateTrees();
    }
}