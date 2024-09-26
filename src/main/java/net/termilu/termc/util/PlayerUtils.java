package net.termilu.termc.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerUtils {

    /**
     * Checks if the player has a roof (solid blocks) over their head, excluding leaves.
     *
     * @param world the world in which the player is located
     * @param playerPos the position of the player
     * @return true if there is a roof over the player's head, false otherwise
     */
    public static boolean hasRoofOverhead(World world, BlockPos playerPos) {
        for (int y = playerPos.getY() + 1; y <= world.getHeight(); y++) {
            BlockPos pos = new BlockPos(playerPos.getX(), y, playerPos.getZ());
            BlockState state = world.getBlockState(pos);
            if (state.isSolidBlock(world, pos) && !(state.getBlock() instanceof LeavesBlock)) {
                return true;
            }
        }
        return false;
    }
}