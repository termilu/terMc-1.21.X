package net.termilu.termc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.item.ModItems;
import net.termilu.termc.util.ModTags;

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
                //One recipe for the item
                .offerTo(exporter);

        //Multiple recipes for same Item
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MAGIC_BLOCK)
                //Recipe
                .pattern("FFF")
                .pattern("FLF")
                .pattern("FFF")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('L', Items.LAPIS_LAZULI)
                //Unlock recipe in recipe book if you pick up either of these items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                //Multiple recipes for the same Item
                .offerTo(exporter, Identifier.of(TerMc.MOD_ID, "magic_block_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModItems.LAPIS_TORCH)
                //Recipe
                .pattern("L")
                .pattern("S")

                //Define input patternStr
                .input('L', Items.LAPIS_LAZULI)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up either of these items
                .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                //One recipe for the item
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.EMERALD_TORCH)
                //Recipe
                .pattern("E")
                .pattern("S")

                //Define input patternStr
                .input('E', Items.EMERALD)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up either of these items
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.LAPIS_LAZULI))
                //One recipe for the item
                .offerTo(exporter);

        //Tools
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TERM_BOW)
                //Recipe
                .pattern(" FI")
                .pattern("S I")
                .pattern(" FI")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                .input('I', Items.STRING)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_SWORD)
                //Recipe
                .pattern("F")
                .pattern("F")
                .pattern("S")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_PICKAXE)
                //Recipe
                .pattern("FFF")
                .pattern(" S ")
                .pattern(" S ")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_AXE)
                //Recipe
                .pattern("FF")
                .pattern("SF")
                .pattern("S ")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_AXE)
                //Recipe
                .pattern("FF")
                .pattern("FS")
                .pattern(" S")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up either of these items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                //Multiple recipes for the same Item
                .offerTo(exporter, Identifier.of(TerMc.MOD_ID, "fluorite_axe_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_SHOVEL)
                //Recipe
                .pattern("F")
                .pattern("S")
                .pattern("S")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_HOE)
                //Recipe
                .pattern("FF")
                .pattern("S ")
                .pattern("S ")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_HOE)
                //Recipe
                .pattern("FF")
                .pattern(" S")
                .pattern(" S")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up either of these items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                //Multiple recipes for the same Item
                .offerTo(exporter, Identifier.of(TerMc.MOD_ID, "fluorite_hoe_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_HAMMER)
                //Recipe
                .pattern("FFF")
                .pattern("FSF")
                .pattern(" S ")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                .input('S', Items.STICK)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FLUORITE_MULTITOOL)
                .input(ModItems.FLUORITE_SWORD)
                .input(ModItems.FLUORITE_PICKAXE)
                .input(ModItems.FLUORITE_AXE)
                .input(ModItems.FLUORITE_SHOVEL)
                .criterion(hasItem(ModItems.FLUORITE_SWORD), conditionsFromItem(ModItems.FLUORITE_SWORD))
                .criterion(hasItem(ModItems.FLUORITE_PICKAXE), conditionsFromItem(ModItems.FLUORITE_PICKAXE))
                .criterion(hasItem(ModItems.FLUORITE_AXE), conditionsFromItem(ModItems.FLUORITE_AXE))
                .criterion(hasItem(ModItems.FLUORITE_SHOVEL), conditionsFromItem(ModItems.FLUORITE_SHOVEL))
                .offerTo(exporter);

        //Armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_HELMET)
                //Recipe
                .pattern("FFF")
                .pattern("F F")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_CHESTPLATE)
                //Recipe
                .pattern("F F")
                .pattern("FFF")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_LEGGINGS)
                //Recipe
                .pattern("FFF")
                .pattern("F F")
                .pattern("F F")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FLUORITE_BOOTS)
                //Recipe
                .pattern("F F")
                .pattern("F F")
                //Define input patternStr
                .input('F', ModItems.FLUORITE)
                //Unlock recipe in recipe book if you pick up this items
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        //Logs & Wood -> Planks
        //Remember to create custom tags for the logs and the planks
        offerPlanksRecipe(exporter, ModBlocks.BLACKWOOD_PLANKS, ModTags.Items.BLACKWOOD_LOGS_ITEM, 4);

        //Slabs
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_SLAB, Ingredient.ofItems(ModItems.FLUORITE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_SLAB, Ingredient.ofItems(ModBlocks.BLACKWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS))
                .offerTo(exporter);

        //Stairs
        createStairsRecipe(ModBlocks.FLUORITE_STAIRS, Ingredient.ofItems(ModItems.FLUORITE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        createStairsRecipe(ModBlocks.BLACKWOOD_STAIRS, Ingredient.ofItems(ModBlocks.BLACKWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS))
                .offerTo(exporter);

        //Doors
        createDoorRecipe(ModBlocks.FLUORITE_DOOR, Ingredient.ofItems(ModItems.FLUORITE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        createDoorRecipe(ModBlocks.BLACKWOOD_DOOR, Ingredient.ofItems(ModBlocks.BLACKWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS))
                .offerTo(exporter);


        //Trapdoors
        createTrapdoorRecipe(ModBlocks.FLUORITE_TRAPDOOR, Ingredient.ofItems(ModItems.FLUORITE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        createTrapdoorRecipe(ModBlocks.BLACKWOOD_TRAPDOOR, Ingredient.ofItems(ModBlocks.BLACKWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS))
                .offerTo(exporter);

        //Buttons
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.FLUORITE_BUTTON)
                .input(ModItems.FLUORITE)
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.BLACKWOOD_BUTTON)
                .input(ModBlocks.BLACKWOOD_PLANKS)
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS))
                .offerTo(exporter);

        //Pressure Plates
        createPressurePlateRecipe(RecipeCategory.REDSTONE,ModBlocks.FLUORITE_PRESSURE_PLATE, Ingredient.ofItems(ModItems.FLUORITE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        createPressurePlateRecipe(RecipeCategory.REDSTONE,ModBlocks.BLACKWOOD_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.BLACKWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS))
                .offerTo(exporter);

        //Fences
        createFenceRecipe(ModBlocks.FLUORITE_FENCE, Ingredient.ofItems(ModItems.FLUORITE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        createFenceRecipe(ModBlocks.BLACKWOOD_FENCE, Ingredient.ofItems(ModBlocks.BLACKWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS))
                .offerTo(exporter);

        //Fence gates
        createFenceGateRecipe(ModBlocks.FLUORITE_FENCE_GATE, Ingredient.ofItems(ModItems.FLUORITE))
                .criterion(hasItem(ModItems.FLUORITE), conditionsFromItem(ModItems.FLUORITE))
                .offerTo(exporter);

        createFenceGateRecipe(ModBlocks.BLACKWOOD_FENCE_GATE, Ingredient.ofItems(ModBlocks.BLACKWOOD_PLANKS))
                .criterion(hasItem(ModBlocks.BLACKWOOD_PLANKS), conditionsFromItem(ModBlocks.BLACKWOOD_PLANKS))
                .offerTo(exporter);

        //Walls
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_WALL, ModBlocks.FLUORITE_BLOCK);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACKWOOD_WALL, ModBlocks.BLACKWOOD_PLANKS);

    }
}
