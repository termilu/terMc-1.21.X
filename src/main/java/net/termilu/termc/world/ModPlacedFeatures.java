package net.termilu.termc.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.termilu.termc.TerMc;

import java.util.List;

/**
 * Class responsible for registering custom placed features in the mod.
 */
public class ModPlacedFeatures {

    /**
     * Bootstraps the registration of placed features.
     *
     * @param context The registerable context for placed features.
     */
    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
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