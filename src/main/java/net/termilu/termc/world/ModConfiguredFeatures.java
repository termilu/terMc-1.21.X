package net.termilu.termc.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.termilu.termc.TerMc;
import net.termilu.termc.block.ModBlocks;

/**
 * Class responsible for registering custom configured features in the mod.
 * Handels how e.g. trees are going to look like.
 */
public class ModConfiguredFeatures {

    // Registry key for the Blackwood tree feature
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLACKWOOD_KEY = registerKey("blackwood_configured");

    /**
     * Bootstraps the registration of configured features.
     *
     * @param context The registerable context for configured features.
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, BLACKWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                // Tree trunk will be made of blackwood logs
                BlockStateProvider.of(ModBlocks.BLACKWOOD_LOG),
                // Tree trunk will be straight and vary in height
                new StraightTrunkPlacer(5, 6, 3),

                // Foliage (leaves) will be made of blackwood leaves
                BlockStateProvider.of(ModBlocks.BLACKWOOD_LEAVES),
                // Foliage (leaves) will be placed in a cherry-like fashion with custom parameters
                // Standard:
                // new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), ConstantIntProvider.create(3),
                new CherryFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(1), ConstantIntProvider.create(5),
                0.25f, 0.5f, 0.15f, 0.05f),

                // Configures the size of the foliage
                new TwoLayersFeatureSize(1, 0, 2)).build());

        // Add more configured features here
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