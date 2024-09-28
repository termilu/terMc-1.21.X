package net.termilu.termc.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.termilu.termc.TerMc;
import net.termilu.termc.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator BLACKWOOD = new SaplingGenerator(TerMc.MOD_ID + ":blackwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLACKWOOD_KEY), Optional.empty());
}
