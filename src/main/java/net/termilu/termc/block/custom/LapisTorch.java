package net.termilu.termc.block.custom;

import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleTypes;

//TODO: Maybe make into RedstoneTorchBlock
public class LapisTorch extends TorchBlock {

    public LapisTorch(Settings settings) {
        super(ParticleTypes.SOUL_FIRE_FLAME, settings);
    }
}
