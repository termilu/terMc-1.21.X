package net.termilu.termc.block.custom;

import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleTypes;

public class LapisTorch extends TorchBlock {

    public LapisTorch(Settings settings) {
        super(ParticleTypes.SOUL_FIRE_FLAME, settings);
    }
}
