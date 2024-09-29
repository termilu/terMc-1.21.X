package net.termilu.termc.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.termilu.termc.TerMc;
import net.termilu.termc.block.ModBlocks;

import java.util.List;

/**
 * Class responsible for registering custom configured features in the mod.
 * Handles how e.g. trees are going to look like.
 */
public class ModConfiguredFeatures {

    // Registry key for the Blackwood tree feature
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLACKWOOD_KEY = registerKey("blackwood_configured");

    // Registry keys for various Fluorite ore features
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLUORITE_ORE_KEY = registerKey("fluorite_ore_configured");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_FLUORITE_ORE_KEY = registerKey("nether_fluorite_ore_configured");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_FLUORITE_ORE_KEY = registerKey("end_fluorite_ore_configured");

    /**
     * Bootstraps the registration of configured features.
     *
     * @param context The registerable context for configured features.
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // Tree features
        register(context, BLACKWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                // Tree trunk will be made of blackwood logs
                BlockStateProvider.of(ModBlocks.BLACKWOOD_LOG),
                // Tree trunk will be straight and vary in height
                new StraightTrunkPlacer(5, 6, 3),

                // Foliage (leaves) will be made of blackwood leaves
                BlockStateProvider.of(ModBlocks.BLACKWOOD_LEAVES),
                // Foliage (leaves) will be placed in a cherry-like fashion with custom parameters
                new CherryFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(1), ConstantIntProvider.create(5),
                0.25f, 0.5f, 0.15f, 0.05f),

                // Configures the size of the foliage
                new TwoLayersFeatureSize(1, 0, 2)).build());

        // Ore features
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        // Define targets for Fluorite ores in different dimensions
        List<OreFeatureConfig.Target> overworldFluoriteOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.FLUORITE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.FLUORITE_DEEPSLATE_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> netherFluoriteOres = List.of(
                OreFeatureConfig.createTarget(netherReplaceables, ModBlocks.FLUORITE_NETHER_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> endFluoriteOres = List.of(
                OreFeatureConfig.createTarget(endReplaceables, ModBlocks.FLUORITE_END_ORE.getDefaultState())
        );

        // Register Fluorite ore features with specified vein sizes
        register(context, FLUORITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldFluoriteOres, 12)); // Size of the vein
        register(context, NETHER_FLUORITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherFluoriteOres, 9)); // Size of the vein
        register(context, END_FLUORITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(endFluoriteOres, 8)); // Size of the vein

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