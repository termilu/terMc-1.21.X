package net.termilu.termc.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallRedstoneTorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class EmeraldWallTorch extends WallRedstoneTorchBlock {
    public EmeraldWallTorch(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((Boolean)state.get(LIT)) {
            Direction direction = ((Direction)state.get(FACING)).getOpposite();
            double d = 0.27;
            double e = (double)pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2 + 0.27 * (double)direction.getOffsetX();
            double f = (double)pos.getY() + 0.7 + (random.nextDouble() - 0.5) * 0.2 + 0.22;
            double g = (double)pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2 + 0.27 * (double)direction.getOffsetZ();
            world.addParticle(ParticleTypes.SMOKE, e, f, g, 0.0, 0.0, 0.0);
        }
    }
}
