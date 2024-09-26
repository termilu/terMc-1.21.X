package net.termilu.termc.event;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * Handles the event when a player uses an item.
 */
public class UseItemCallbackHandler implements UseItemCallback {
    /**
     * Called when a player interacts with an item.
     *
     * @param player the player using the item
     * @param world the world in which the interaction occurs
     * @param hand the hand used for the interaction
     * @return the result of the interaction
     */
    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);


        // Add more events here


        // Return pass to allow other handlers to process the event
        return new TypedActionResult<>(ActionResult.PASS, itemStack);
    }
}
