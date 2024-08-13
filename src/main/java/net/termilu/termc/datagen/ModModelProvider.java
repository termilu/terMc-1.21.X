package net.termilu.termc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    //Generate Mod Blockstates & Blockmodels jsons automatically
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //Generate Block and variants like stairs and slab if provided below
        BlockStateModelGenerator.BlockTexturePool fluoriteTexturePool = blockStateModelGenerator
                .registerCubeAllModelTexturePool(ModBlocks.FLUORITE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_DEEPSLATE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_NETHER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_END_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);

        //Generate derivables
        fluoriteTexturePool.stairs(ModBlocks.FLUORITE_STAIRS);
        fluoriteTexturePool.slab(ModBlocks.FLUORITE_SLAB);
        fluoriteTexturePool.button(ModBlocks.FLUORITE_BUTTON);
        fluoriteTexturePool.pressurePlate(ModBlocks.FLUORITE_PRESSURE_PLATE);

        //Fences, Fence gates and Walls need a custom BlockTag otherwise they won't connect
        fluoriteTexturePool.fence(ModBlocks.FLUORITE_FENCE);
        fluoriteTexturePool.fenceGate(ModBlocks.FLUORITE_FENCE_GATE);
        fluoriteTexturePool.wall(ModBlocks.FLUORITE_WALL);

        //Doors & Trapdoors
        blockStateModelGenerator.registerDoor(ModBlocks.FLUORITE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.FLUORITE_TRAPDOOR);

    }

    //Generate Mod Item models jsons
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //Materials
        itemModelGenerator.register(ModItems.FLUORITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_FLUORITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);

        //Tools
        itemModelGenerator.register(ModItems.CHAINSAW, Models.GENERATED);

        //Food
        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);

        //Fuel
        itemModelGenerator.register(ModItems.STAR_FRAGMENT, Models.GENERATED);
    }
}
