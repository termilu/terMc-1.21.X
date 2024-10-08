package net.termilu.termc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.block.custom.FluoriteLampBlock;
import net.termilu.termc.item.ModItems;

import java.util.Optional;

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

        BlockStateModelGenerator.BlockTexturePool blackwoodTexturePool = blockStateModelGenerator
                .registerCubeAllModelTexturePool(ModBlocks.BLACKWOOD_PLANKS);

        //blockStateModels

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_DEEPSLATE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_NETHER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLUORITE_END_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);

        blockStateModelGenerator.registerTorch(ModBlocks.LAPIS_TORCH, ModBlocks.LAPIS_WALL_TORCH);
        blockStateModelGenerator.registerTorch(ModBlocks.EMERALD_TORCH, ModBlocks.EMERALD_WALL_TORCH);

        //Generate derivables
            //Fluorite
        fluoriteTexturePool.stairs(ModBlocks.FLUORITE_STAIRS);
        fluoriteTexturePool.slab(ModBlocks.FLUORITE_SLAB);
        fluoriteTexturePool.button(ModBlocks.FLUORITE_BUTTON);
        fluoriteTexturePool.pressurePlate(ModBlocks.FLUORITE_PRESSURE_PLATE);
            //Blackwood
        blackwoodTexturePool.stairs(ModBlocks.BLACKWOOD_STAIRS);
        blackwoodTexturePool.slab(ModBlocks.BLACKWOOD_SLAB);
        blackwoodTexturePool.button(ModBlocks.BLACKWOOD_BUTTON);
        blackwoodTexturePool.pressurePlate(ModBlocks.BLACKWOOD_PRESSURE_PLATE);

        //Fences, Fence gates and Walls
        // need a custom BlockTag otherwise they won't connect
        fluoriteTexturePool.fence(ModBlocks.FLUORITE_FENCE);
        fluoriteTexturePool.fenceGate(ModBlocks.FLUORITE_FENCE_GATE);
        fluoriteTexturePool.wall(ModBlocks.FLUORITE_WALL);

        blackwoodTexturePool.fence(ModBlocks.BLACKWOOD_FENCE);
        blackwoodTexturePool.fenceGate(ModBlocks.BLACKWOOD_FENCE_GATE);
        blackwoodTexturePool.wall(ModBlocks.BLACKWOOD_WALL);

        //Doors & Trapdoors
        blockStateModelGenerator.registerDoor(ModBlocks.FLUORITE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.FLUORITE_TRAPDOOR);

        blockStateModelGenerator.registerDoor(ModBlocks.BLACKWOOD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.BLACKWOOD_TRAPDOOR);

        //Logs
        blockStateModelGenerator.registerLog(ModBlocks.BLACKWOOD_LOG)
                .log(ModBlocks.BLACKWOOD_LOG)
                .wood(ModBlocks.BLACKWOOD_WOOD);

        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_BLACKWOOD_LOG)
                .log(ModBlocks.STRIPPED_BLACKWOOD_LOG)
                .wood(ModBlocks.STRIPPED_BLACKWOOD_WOOD);


        //Leaves
        blockStateModelGenerator.registerSingleton(ModBlocks.BLACKWOOD_LEAVES, TexturedModel.LEAVES);

        //Sapling
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.BLACKWOOD_SAPLING,
                BlockStateModelGenerator.TintType.NOT_TINTED);

        //Blockstate lamps
        //Lamp off
        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.FLUORITE_LAMP, blockStateModelGenerator.modelCollector);
        //Lamp on
        Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.FLUORITE_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);
        //Magic json generation wizardry
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.FLUORITE_LAMP)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(FluoriteLampBlock.CLICKED, lampOnIdentifier, lampOffIdentifier)));
    }

    //Generate Mod Item models jsons
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //Materials
        itemModelGenerator.register(ModItems.FLUORITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_FLUORITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);

        //New Tools
        itemModelGenerator.register(ModItems.CHAINSAW, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_DETECTOR, Models.GENERATED);
        // Data Tablet is handled by ModModelPredicates
        // itemModelGenerator.register(ModItems.DATA_TABLET, Models.GENERATED);

        //Handheld Tools
        itemModelGenerator.register(ModItems.POISONED_DIAMOND_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.FLUORITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_MULTITOOL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLUORITE_HAMMER, Models.HANDHELD);

        //Armor
        itemModelGenerator.register(ModItems.FLUORITE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLUORITE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLUORITE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLUORITE_BOOTS, Models.GENERATED);

        //Spawn eggs
        itemModelGenerator.register(ModItems.DODO_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));

        itemModelGenerator.register(ModItems.CAPYBARA_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));

        //Food
        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGO, Models.GENERATED);

        //Fuel
        itemModelGenerator.register(ModItems.STAR_FRAGMENT, Models.GENERATED);
    }
}
