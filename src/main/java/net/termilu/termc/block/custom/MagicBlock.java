package net.termilu.termc.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.termilu.termc.util.ModTags;

import java.util.List;

public class MagicBlock extends Block {
    public MagicBlock(Settings settings){
        super(settings);
    }

    //On Block right click play Sound and Animation
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos,
                                 PlayerEntity player, BlockHitResult hit) {
        //When rightclicked play Amethyst block chime sound
        world.playSound(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 5, 1);
        return ActionResult.SUCCESS;
    }

    //Entity dropped on the block
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        //Is it a Valid Item entity?
        if(entity instanceof ItemEntity item){
            //Turn into Redstone dust at day
            if(isValidItem(item.getStack()) && world.getTimeOfDay() % 24000 < 13000){                                   //24000 ticks per day
                item.setStack(new ItemStack(Items.REDSTONE, item.getStack().getCount()));                               //Morning starts at 0 ticks
                //Turn into Lapis at Night
            }else if(isValidItem(item.getStack()) && world.getTimeOfDay() % 24000 >= 13000){                            //Night starts at 13000 ticks
                    item.setStack(new ItemStack(Items.LAPIS_LAZULI, item.getStack().getCount()));
            }

/*          Cant use world.isDay() or .isNight() because ambientDarkness sometimes switches to 0 even during night.
            This turns the item into Redstone for one Frame, then into Lapis even though it is night.


            if(isValidItem(item.getStack())){
                //Turn into Redstone dust at day
                if(world.isDay()){
                    item.setStack(new ItemStack(Items.REDSTONE, item.getStack().getCount()));
                //Turn into Lapis at Night
                }else{
                    item.setStack(new ItemStack(Items.LAPIS_LAZULI, item.getStack().getCount()));
                }
            }
*/        }
        super.onSteppedOn(world, pos, state, entity);
    }

    //Valid items: Fluorite, Amethyst Shard
    private boolean isValidItem(ItemStack stack) {
        //custom tag for transformable items
        return stack.isIn(ModTags.Items.TRANSFORMABLE_ITEMS);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.termc.magic_block.tooltip.1"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}
