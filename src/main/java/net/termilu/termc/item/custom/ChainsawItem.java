package net.termilu.termc.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.termilu.termc.components.ModDataComponentTypes;
import net.termilu.termc.sound.ModSounds;
import net.termilu.termc.util.PlayerUtils;

import java.util.List;
import java.util.Objects;

public class ChainsawItem extends Item {
    public ChainsawItem(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        //Get world reference
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        //only execute on server, client can't break blocks
        if(!world.isClient()){
            //Is the block we are using the item on a log or leaves while it's also not raining?
            if(((world.getBlockState(context.getBlockPos()).isIn(BlockTags.LOGS)) ||
                    world.getBlockState(context.getBlockPos()).isIn(BlockTags.LEAVES)) &&
                    (!context.getWorld().isRaining() || PlayerUtils.hasRoofOverhead(world, player.getBlockPos()))){

                //then let the player instantly break the block and drop it
                world.breakBlock(context.getBlockPos(), true, context.getPlayer());
                //damage the unstackable item by 1
                context.getStack().damage(1, (ServerWorld) world, ((ServerPlayerEntity) context.getPlayer()), item ->
                        Objects.requireNonNull(context.getPlayer()).sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                //Custom data component
                context.getStack().set(ModDataComponentTypes.COORDINATES, context.getBlockPos());

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

    //Delete previous coords to make room for new coords on use
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getEquippedStack(EquipmentSlot.MAINHAND);

        if(stack.get(ModDataComponentTypes.COORDINATES) != null){
            stack.set(ModDataComponentTypes.COORDINATES, null);
        }

        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //if Tooltip is showing and shift is held down then display custom tooltip
        if(!Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.termc.general.tooltip.shift"));
        }else{
            tooltip.add(Text.translatable("tooltip.termc.chainsaw.tooltip.1"));
            tooltip.add(Text.translatable("tooltip.termc.chainsaw.tooltip.2"));
        }

        //add tooltip from component if coordinates aren't null
        if (stack.get(ModDataComponentTypes.COORDINATES) != null) {
            //show coords of last chopped tree
            tooltip.add(Text.literal("Last Chopped Tree at " + stack.get(ModDataComponentTypes.COORDINATES)));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
