package net.termilu.termc.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.termilu.termc.item.ModItems;
import net.termilu.termc.util.PlayerUtils;

public class UseBlockCallbackHandler implements UseBlockCallback {
    /**
     * Called when a player interacts with a block.
     *
     * @param player the player using the block
     * @param world the world in which the interaction occurs
     * @param hand the hand used for the interaction
     * @param hitResult the result of the block hit
     * @return the result of the interaction
     */
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getStackInHand(hand);

        // Check if the item is a Chainsaw, it is raining, the player has a roof overhead (excluding leaves),
        // the world is not client-side,
        // and the player is not in spectator mode
        if (itemStack.getItem() == ModItems.CHAINSAW && world.isRaining() && !PlayerUtils.hasRoofOverhead(world, player.getBlockPos())
                && !world.isClient() && !player.isSpectator()) {
            // Send a message to the player indicating that the chainsaw cannot be used during rain
            player.sendMessage(Text.literal("Can't use chainsaw during rain!"));
            // Return Pass to allow chainsaw to play pull sound
            return ActionResult.PASS;
        }

        // Add more events here

        // Return pass to allow other handlers to process the event
        return ActionResult.PASS;
    }
}
