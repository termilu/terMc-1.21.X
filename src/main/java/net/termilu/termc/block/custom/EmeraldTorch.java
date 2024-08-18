package net.termilu.termc.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

//TODO: Add emerald/lapis redstone that doesnt connect with normal redstone
public class EmeraldTorch extends RedstoneTorchBlock {
    public EmeraldTorch(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((Boolean)state.get(LIT)) {
            double d = (double)pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            double e = (double)pos.getY() + 0.7 + (random.nextDouble() - 0.5) * 0.2;
            double f = (double)pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
        }
    }
}
