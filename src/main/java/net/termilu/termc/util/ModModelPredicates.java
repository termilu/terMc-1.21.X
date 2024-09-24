package net.termilu.termc.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.components.ModDataComponentTypes;
import net.termilu.termc.item.ModItems;

public class ModModelPredicates {
    //Predicates register in TerMcClient
    //MANUALLY CREATE JSON FOR MODELPREDICATE ITEMS!! (resources/assets/termc/models)
    public static void registerModelPredicates(){
        //Change texture of data tablet depending on predicate on or off
        ModelPredicateProviderRegistry.register(ModItems.DATA_TABLET, Identifier.of(TerMc.MOD_ID, "on"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.FOUND_BLOCK) != null ? 1f : 0f);
    }
}
