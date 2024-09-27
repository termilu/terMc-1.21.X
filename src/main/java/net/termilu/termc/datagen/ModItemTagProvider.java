package net.termilu.termc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.item.ModItems;
import net.termilu.termc.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    //Generate Mod Item Tag jsons automatically
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //Generate transformable_items.json
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.FLUORITE)
                .add(Items.AMETHYST_SHARD);

        //Add Blackwood Logs item to Logs
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLACKWOOD_LOG.asItem(), ModBlocks.BLACKWOOD_WOOD.asItem(),
                        ModBlocks.STRIPPED_BLACKWOOD_LOG.asItem(), ModBlocks.STRIPPED_BLACKWOOD_WOOD.asItem());

        //Add Blackwood Planks item to Planks
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.BLACKWOOD_PLANKS.asItem());

        //Add Blackwood Logs to Blackwood Logs item
        //Necessary for crafting recipes (offerPlanksRecipe)
        getOrCreateTagBuilder(ModTags.Items.BLACKWOOD_LOGS_ITEM)
                .add(ModBlocks.BLACKWOOD_LOG.asItem(), ModBlocks.BLACKWOOD_WOOD.asItem(),
                        ModBlocks.STRIPPED_BLACKWOOD_LOG.asItem(), ModBlocks.STRIPPED_BLACKWOOD_WOOD.asItem());


    }
}
