package net.termilu.termc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    //Generate Mod recipes jsons automatically
    @Override
    public void generate(RecipeExporter exporter) {
        //Create list of all smeltables that smelt into the same material
        List<ItemConvertible> FLUORITE_SMELTABLES = List.of(ModItems.RAW_FLUORITE, ModBlocks.FLUORITE_ORE,
                ModBlocks.FLUORITE_DEEPSLATE_ORE, ModBlocks.FLUORITE_NETHER_ORE, ModBlocks.FLUORITE_END_ORE);

        //Generate smelting recipe for all in List, Category, Output Item, Experience dropped, cooking Time taken, group name
        offerSmelting(exporter, FLUORITE_SMELTABLES, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 200, "fluorite" );
        offerBlasting(exporter, FLUORITE_SMELTABLES, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 100, "fluorite" );

        //Generate the 3x3 item -> block crafting recipe AND the Block -> 9 Item recipe
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.FLUORITE, RecipeCategory.DECORATIONS, ModBlocks.FLUORITE_BLOCK);

        //Generate Shaped recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MAGIC_BLOCK)
                //Recipe
                .pattern("FFF")
                .pattern("FRF")
                .pattern("FFF")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('R', Items.REDSTONE)
                //Unlock recipe in recipe book if you pick up either of these items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter);

    }
}
