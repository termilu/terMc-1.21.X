package net.termilu.termc.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.termilu.termc.TerMc;

/**
 * Class responsible for registering custom configured features in the mod.
 */
public class ModConfiguredFeatures {

    /**
     * Bootstraps the registration of configured features.
     *
     * @param context The registerable context for configured features.
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // register(context, registerKey("example_feature"), ModFeatures.EXAMPLE_FEATURE, new ExampleFeatureConfig());
    }

    /**
     * Generates a RegistryKey for a ConfiguredFeature using the given name.
     *
     * @param name The name of the configured feature.
     * @return The generated RegistryKey.
     */
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TerMc.MOD_ID, name));
    }

    /**
     * Registers a ConfiguredFeature with the given context.
     *
     * @param context The registerable context.
     * @param key The registry key for the feature.
     * @param feature The feature to be registered.
     * @param configuration The configuration of the feature.
     * @param <FC> The type of the feature configuration.
     * @param <F> The type of the feature.
     */
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}