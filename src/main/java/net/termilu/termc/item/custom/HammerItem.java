package net.termilu.termc.item.custom;

import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends MiningToolItem {

    public HammerItem(ToolMaterial material, Settings settings) {
        //Nan mine everything that a pickaxe can
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.raycast(20, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;

            //If looking straight up or down then expand blocks to mine into x and z direction
            if(blockHit.getSide() == Direction.DOWN || blockHit.getSide() == Direction.UP) {
                for(int x = -range; x <= range; x++) {
                    for(int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
                    }
                }
            }

            //If looking at north or south side then expand blocks to mine into x and y direction
            if(blockHit.getSide() == Direction.NORTH || blockHit.getSide() == Direction.SOUTH) {
                for(int x = -range; x <= range; x++) {
                    for(int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                    }
                }
            }

            //If looking at east or west side then expand blocks to mine into y and z direction
            if(blockHit.getSide() == Direction.EAST || blockHit.getSide() == Direction.WEST) {
                for(int x = -range; x <= range; x++) {
                    for(int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
                    }
                }
            }
        }

        return positions;
    }
}
