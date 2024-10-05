package net.termilu.termc.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpeziItem extends HoneyBottleItem {
    public SpeziItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        //Don't call super.finishUsing() because it will return an empty bottle
        //super.finishUsing(stack, world, user);

        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1));  // Regeneration for 10 seconds
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0));  // Speed boost for 10 seconds
        }

        // Reduce the item stack (consume the item) but do NOT return an empty bottle
        if (stack.getCount() > 1) {
            stack.decrement(1);  // Reduce the stack count by 1
            return stack;
        } else {
            return ItemStack.EMPTY;  // If this is the last item in the stack, return an empty stack (no empty bottle)
        }
    }



}
