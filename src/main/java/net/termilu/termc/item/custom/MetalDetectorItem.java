package net.termilu.termc.item.custom;

import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.components.FoundBlockData;
import net.termilu.termc.components.ModDataComponentTypes;
import net.termilu.termc.item.ModItems;
import net.termilu.termc.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            //Check blocks below for ValuableBlock
            for(int i = 0; i <= positionClicked.getY(); i++) {
                BlockState stateBelow = context.getWorld().getBlockState(positionClicked.down(i));
                Block blockBelow = stateBelow.getBlock();

                //ValuableBlock found
                if(isValuableBlock(stateBelow)) {
                    outputValuableCoordinates(positionClicked.add(0, -i, 0), player, blockBelow);
                    foundBlock = true;

                    //Add coordinates to data tablet if found in inventory
                    if(InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET)) {
                        addComponentToStack(player, positionClicked.add(0, -i, 0), blockBelow);
                    }

                    context.getWorld().playSound(null, positionClicked, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 0.5f, 1f);

                    break;
                }
            }

            //Output when no Valuable Block was found
            if(!foundBlock) {
                player.sendMessage(Text.translatable("item.termc.metal_detector.no_valuables"), false);
            }
        }

        return ActionResult.SUCCESS;
    }

    //Adds the component to the data tablet
    private void addComponentToStack(PlayerEntity player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet =
                player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET));

        FoundBlockData data = new FoundBlockData(blockBelow.getDefaultState(), pos);
        dataTablet.set(ModDataComponentTypes.FOUND_BLOCK, data);
    }

    //Outputs coordinates of found ValuableBlock
    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block blockBelow) {
        player.sendMessage(Text.literal("Found " + blockBelow.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }

    //Blocks to scan for
    private boolean isValuableBlock(BlockState block) {
        return block.isOf(Blocks.IRON_ORE) || block.isOf(Blocks.DEEPSLATE_IRON_ORE)
                || block.isOf(Blocks.GOLD_ORE) || block.isOf(Blocks.DEEPSLATE_GOLD_ORE)
                || block.isOf(Blocks.DIAMOND_ORE) || block.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)
                || block.isOf(ModBlocks.FLUORITE_ORE) || block.isOf(ModBlocks.FLUORITE_DEEPSLATE_ORE);
    }
}