package net.termilu.termc.block.custom;

import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.ParticleTypes;

public class LapisWallTorch extends WallTorchBlock {
    public LapisWallTorch(Settings settings) {
        super(ParticleTypes.SOUL_FIRE_FLAME, settings);
    }
}
