package net.termilu.termc.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.termilu.termc.item.custom.HammerItem;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class HammerUsageEvent implements PlayerBlockBreakEvents.Before{

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/c23d117dcd3b3b3408a138716b15507f709494cd/src/main/java/cofh/core/event/AreaEffectEvents.java
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {

        ItemStack mainHandItem = player.getMainHandStack();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayerEntity serverPlayer){
            //if block is already in the set return early
            if(HARVESTED_BLOCKS.contains(pos)){
                return true;
            }

            /*
                [position][position][position]
                [position][  pos   ][position]
                [position][position][position]
            */
            //Loop through blocks in range
            for(BlockPos position : HammerItem.getBlocksToBeDestroyed(1, pos, serverPlayer)){
                //If loop hits the block we already destroyed OR is not mineable with hammer just continue with the next iteration
                if(pos == position || !hammer.isCorrectForDrops(mainHandItem, world.getBlockState(position))){
                    continue;
                }

                /*  Add positions to Set, break the block, then remove it from Set
                    This is needed so when method calls itself when breaking surrounding blocks it returns early
                    If removed we are stuck in infinite ever expanding loop of blocks to break
                */
                HARVESTED_BLOCKS.add(position);
                serverPlayer.interactionManager.tryBreakBlock(position);
                HARVESTED_BLOCKS.remove(position);

            }
        }

        return true;
    }
}
