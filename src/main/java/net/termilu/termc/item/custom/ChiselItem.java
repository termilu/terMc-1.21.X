package net.termilu.termc.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {
/*
 Map.of() is not bidirectional (because immutable), meaning it can only map in one way and not implement reversibility.
 Using HashMap<>() instead solves this problem
*/
/*
    public static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    //Left side turns into right side
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS,
                    Blocks.TUFF, Blocks.TUFF_BRICKS,
                    Blocks.PACKED_MUD, Blocks.MUD_BRICKS,
                    Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS,
                    Blocks.NETHERRACK, Blocks.NETHER_BRICKS,
                    Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS



                    //reversible
                    Blocks.STONE_BRICKS, Blocks.STONE,
                    Blocks.END_STONE_BRICKS, Blocks.END_STONE,
                    Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE,
                    Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE,
                    Blocks.TUFF_BRICKS, Blocks.TUFF,
                    Blocks.MUD_BRICKS, Blocks.PACKED_MUD,
                    Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE,
                    Blocks.NETHER_BRICKS, Blocks.NETHERRACK,
                    Blocks.QUARTZ_BRICKS, Blocks.QUARTZ_BLOCK,
            );
*/

    // Create a mutable map that will handle both forward and reverse mappings
public static final Map<Block, Block> CHISEL_MAP = new HashMap<>();

    static {
        // Forward mappings (normal block to brick)
        CHISEL_MAP.put(Blocks.STONE, Blocks.STONE_BRICKS);
        CHISEL_MAP.put(Blocks.END_STONE, Blocks.END_STONE_BRICKS);
        CHISEL_MAP.put(Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS);
        CHISEL_MAP.put(Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS);
        CHISEL_MAP.put(Blocks.TUFF, Blocks.TUFF_BRICKS);
        CHISEL_MAP.put(Blocks.PACKED_MUD, Blocks.MUD_BRICKS);
        CHISEL_MAP.put(Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS);
        CHISEL_MAP.put(Blocks.NETHERRACK, Blocks.NETHER_BRICKS);
        CHISEL_MAP.put(Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS);

        // Reverse mappings (brick block to normal)
        CHISEL_MAP.put(Blocks.STONE_BRICKS, Blocks.STONE);
        CHISEL_MAP.put(Blocks.END_STONE_BRICKS, Blocks.END_STONE);
        CHISEL_MAP.put(Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE);
        CHISEL_MAP.put(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE);
        CHISEL_MAP.put(Blocks.TUFF_BRICKS, Blocks.TUFF);
        CHISEL_MAP.put(Blocks.MUD_BRICKS, Blocks.PACKED_MUD);
        CHISEL_MAP.put(Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE);
        CHISEL_MAP.put(Blocks.NETHER_BRICKS, Blocks.NETHERRACK);
        CHISEL_MAP.put(Blocks.QUARTZ_BRICKS, Blocks.QUARTZ_BLOCK);
    }

    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)){
            if(!world.isClient()){
                //Change block at context position
                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());

                //Damage chisel item
                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                //Play sound
                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);
            }
        }


        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //if Tooltip is showing and shift is held down then display custom tooltip
        if(!Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.termc.general.tooltip.shift"));
        }else{
            tooltip.add(Text.translatable("tooltip.termc.chisel.tooltip.1"));
            tooltip.add(Text.translatable("tooltip.termc.chisel.tooltip.2"));
            tooltip.add(Text.translatable("tooltip.termc.chisel.tooltip.3"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
