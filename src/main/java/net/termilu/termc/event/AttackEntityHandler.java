package net.termilu.termc.event;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.termilu.termc.entity.custom.CapybaraEntity;
import net.termilu.termc.item.ModItems;
import org.jetbrains.annotations.Nullable;

/**
 * Handles the event when a player attacks an entity.
 */
public class AttackEntityHandler implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand,
                                 Entity entity, @Nullable EntityHitResult hitResult) {

        if(entity instanceof CapybaraEntity && !world.isClient() && !player.isSpectator()){
//            if(player.getMainHandStack().getItem() == ModItems.METAL_DETECTOR){
//                player.sendMessage(Text.literal(player.getName().getString() + " tried metal detecting a Sheep!"));
//            }

            if(player.getMainHandStack().getItem() == ModItems.FLUORITE_SWORD){
                player.sendMessage(Text.literal(player.getName().getString() + " hit a Capybara with a Fluorite Sword! >:("));
            }
        }

        //Add more events here


        //Return PASS to allow the player to attack the entity
        return ActionResult.PASS;
    }
}
