package net.termilu.termc.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.termilu.termc.sound.ModSounds;

import java.util.Objects;

public class ChainsawItem extends Item {
    public ChainsawItem(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        //Get world reference
        World world = context.getWorld();

        //only execute on server, client can't break blocks
        if(!world.isClient()){
            //Is the block we are using the item on a log or leaves while it's also not raining?
            if(((world.getBlockState(context.getBlockPos()).isIn(BlockTags.LOGS)) ||
                    world.getBlockState(context.getBlockPos()).isIn(BlockTags.LEAVES)) &&
                    (!context.getWorld().isRaining())){

                //then let the player instantly break the block and drop it
                world.breakBlock(context.getBlockPos(), true, context.getPlayer());
                //damage the unstackable item by 1
                context.getStack().damage(1, (ServerWorld) world, ((ServerPlayerEntity) context.getPlayer()), item ->
                        Objects.requireNonNull(context.getPlayer()).sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                //Play cut sound
                context.getWorld().playSound(null, context.getBlockPos(), ModSounds.CHAINSAW_CUT, SoundCategory.BLOCKS,
                        1f, 1f);

            }else{
                //Play pull sound
                context.getWorld().playSound(null, context.getBlockPos(), ModSounds.CHAINSAW_PULL, SoundCategory.BLOCKS,
                        1f, 1f);
            }

        }

        return ActionResult.CONSUME;
    }
}
