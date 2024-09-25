package net.termilu.termc.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.components.ModDataComponentTypes;
import net.termilu.termc.item.ModItems;

import static net.minecraft.client.item.ModelPredicateProviderRegistry.register;
public class ModModelPredicates {
    //Predicates register in TerMcClient
    //MANUALLY CREATE JSON FOR MODELPREDICATE ITEMS!! (resources/assets/termc/models)
    public static void registerModelPredicates(){
        //Change texture of data tablet depending on predicate on or off
        ModelPredicateProviderRegistry.register(ModItems.DATA_TABLET, Identifier.of(TerMc.MOD_ID, "on"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.FOUND_BLOCK) != null ? 1f : 0f);

        registerCustomBow(ModItems.TERM_BOW);
    }

    //Taken from ModelPredicateProviderRegistry
    private static void registerCustomBow(Item item){
        register(item, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        register(
                item,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
}
