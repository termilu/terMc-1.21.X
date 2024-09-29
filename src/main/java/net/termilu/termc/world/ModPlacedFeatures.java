package net.termilu.termc.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.block.ModBlocks;

import java.util.List;

/**
 * Class responsible for registering custom placed features in the mod.
 * Handles how features are placed in the world.
 */
public class ModPlacedFeatures {

    // Registry key for the Blackwood placed feature
    public static final RegistryKey<PlacedFeature> BLACKWOOD_PLACED_KEY = registerKey("blackwood_placed");

    // Registry keys for various Fluorite ore placed features
    public static final RegistryKey<PlacedFeature> FLUORITE_ORE_PLACED_KEY = registerKey("fluorite_ore_placed");
    public static final RegistryKey<PlacedFeature> NETHER_FLUORITE_ORE_PLACED_KEY = registerKey("nether_fluorite_ore_placed");
    public static final RegistryKey<PlacedFeature> END_FLUORITE_ORE_PLACED_KEY = registerKey("end_fluorite_ore_placed");

    /**
     * Bootstraps the registration of placed features.
     *
     * @param context The registerable context for placed features.
     */
    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, BLACKWOOD_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BLACKWOOD_KEY),
                // This method ensures that the tree will only be placed if it can survive in the given conditions.
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        /*  Place 2 trees with a 10% chance of 2 extra trees.
                            IMPORTANT: 1 / extraChance parameter has to be an integer.
                            Example:
                            1 / 0.1 = 10 -> 10% chance
                            1 / 0.5 = 2 -> 50% chance
                            1 / 0.3 = 3.3333 -> Cannot be used
                        */
                        PlacedFeatures.createCountExtraModifier(2, 0.1f, 2), ModBlocks.BLACKWOOD_SAPLING));


        register(context, FLUORITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FLUORITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        14, // How many veins per chunk
                        // Height range
                        HeightRangePlacementModifier.
                                // Trapezoid => More ores in the middle height levels
                                // trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));

                                // Uniform => Roughly the same amount of ores in each height level
                                uniform(YOffset.fixed(-80), YOffset.fixed(80))));

        register(context, NETHER_FLUORITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NETHER_FLUORITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        10, // How many veins per chunk
                        // Height range
                        HeightRangePlacementModifier.
                                // Trapezoid => More ores in the middle height levels
                                // trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));

                                // Uniform => Roughly the same amount of ores in each height level
                                        uniform(YOffset.fixed(-80), YOffset.fixed(80))));

        register(context, END_FLUORITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.END_FLUORITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        8, // How many veins per chunk
                        // Height range
                        HeightRangePlacementModifier.
                                // Trapezoid => More ores in the middle height levels
                                // trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));

                                // Uniform => Roughly the same amount of ores in each height level
                                        uniform(YOffset.fixed(-80), YOffset.fixed(80))));


        // Add more placed features here
    }

    /**
     * Generates a RegistryKey for a PlacedFeature using the given name.
     *
     * @param name The name of the placed feature.
     * @return The generated RegistryKey.
     */
    public static RegistryKey<PlacedFeature> registerKey(String name){
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(TerMc.MOD_ID, name));
    }

    /**
     * Registers a PlacedFeature with the given context.
     *
     * @param context The registerable context.
     * @param key The registry key for the placed feature.
     * @param configuration The configuration of the placed feature.
     * @param modifiers The placement modifiers for the placed feature.
     */
    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    /**
     * Registers a PlacedFeature with the given context using varargs for placement modifiers.
     *
     * @param context The registerable context.
     * @param key The registry key for the placed feature.
     * @param configuration The configuration of the placed feature.
     * @param modifiers The placement modifiers for the placed feature.
     * @param <FC> The type of the feature configuration.
     * @param <F> The type of the feature.
     */
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context,
                                                                                   RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}