package net.termilu.termc.block;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.termilu.termc.TerMc;
import net.termilu.termc.block.custom.*;

public class ModBlocks {

    //Create Fluorite Block with the Sounds of Amethyst Blocks, Strength = time to destroy, requiresTool still needs to
    //be specified to drop Item
    public static final Block FLUORITE_BLOCK = registerBlock("fluorite_block",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .strength(4f)
                    .requiresTool()));

    public static final Block FLUORITE_ORE = registerBlock("fluorite_ore",
            //Use ExperienceDroppingBlock for Ores -> Drops 2-4 XP
            new ExperienceDroppingBlock(UniformIntProvider.create(2,4),
                    AbstractBlock.Settings.create()
                            .strength(4f)
                            .requiresTool()));

    public static final Block FLUORITE_DEEPSLATE_ORE = registerBlock("fluorite_deepslate_ore",
            //Higher Strength & more XP drop because Deepslate
            new ExperienceDroppingBlock(UniformIntProvider.create(3,6),
                    AbstractBlock.Settings.create()
                            .sounds(BlockSoundGroup.DEEPSLATE)
                            .strength(6f)
                            .requiresTool()));

    public static final Block FLUORITE_NETHER_ORE = registerBlock("fluorite_nether_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2,4),
                    AbstractBlock.Settings.create()
                            .sounds(BlockSoundGroup.NETHER_ORE)
                            .strength(4f)
                            .requiresTool()));

    public static final Block FLUORITE_END_ORE = registerBlock("fluorite_end_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2,4),
                    AbstractBlock.Settings.create()
                            .sounds(BlockSoundGroup.STONE)
                            .strength(4f)
                            .requiresTool()));

    //Stairs
    public static final Block FLUORITE_STAIRS = registerBlock("fluorite_stairs",
            new StairsBlock(ModBlocks.FLUORITE_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(2f)
                            .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));

    //Slab doesnt need defaultState
    public static final Block FLUORITE_SLAB = registerBlock("fluorite_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));

    //Buttons
    public static final Block FLUORITE_BUTTON = registerBlock("fluorite_button",
            new ButtonBlock(BlockSetType.IRON, 10, AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));

    //Pressure Plate
    public static final Block FLUORITE_PRESSURE_PLATE = registerBlock("fluorite_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));

    //Fence & Walls
    public static final Block FLUORITE_FENCE = registerBlock("fluorite_fence",
            new FenceBlock(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));

    public static final Block FLUORITE_FENCE_GATE = registerBlock("fluorite_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));

    public static final Block FLUORITE_WALL = registerBlock("fluorite_wall",
            new WallBlock(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));

    //Doors & Trapdoors IMPORTANT: also add cutouts in TerMcClient
    public static final Block FLUORITE_DOOR = registerBlock("fluorite_door",
            //BlockSetType.IRON => cant open with hand
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()
                    //.nonOpaque to prevent xray bug with doors & trapdoors
                    .nonOpaque()));

    public static final Block FLUORITE_TRAPDOOR = registerBlock("fluorite_trapdoor",
            //BlockSetType.IRON => cant open with hand
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()
                    //.nonOpaque to prevent xray bug with doors & trapdoors
                    .nonOpaque()));


    //Custom blocks
    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create()
                    .strength(1f)
                    .requiresTool()));

    public static final Block FLUORITE_LAMP = registerBlock("fluorite_lamp",
            new FluoriteLampBlock(AbstractBlock.Settings.create()
                    .strength(1f)
                    .requiresTool()
                    //get state from FluoriteLampBlock, if on create light source with strength 15, else 0
                    .luminance(state -> state.get(FluoriteLampBlock.CLICKED) ? 15 : 0)));

    public static final Block LAPIS_TORCH = registerBlock("lapis_torch",
            new LapisTorch(AbstractBlock.Settings.create()
                    .noCollision()
                    .breakInstantly()
                    .luminance(state -> 14)
                    .sounds(BlockSoundGroup.WOOD)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block LAPIS_WALL_TORCH = registerBlock("lapis_wall_torch",
            new LapisWallTorch(AbstractBlock.Settings.create()
                    .noCollision()
                    .breakInstantly()
                    .luminance(state -> 14)
                    .sounds(BlockSoundGroup.WOOD)
                    .dropsLike(ModBlocks.LAPIS_TORCH)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block EMERALD_TORCH = registerBlock("emerald_torch",
            new EmeraldTorch(AbstractBlock.Settings.create()
                    .noCollision()
                    .breakInstantly()
                    .luminance(state -> 14)
                    .sounds(BlockSoundGroup.WOOD)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block EMERALD_WALL_TORCH = registerBlock("emerald_wall_torch",
            new EmeraldWallTorch(AbstractBlock.Settings.create()
                    .noCollision()
                    .breakInstantly()
                    .luminance(state -> 14)
                    .sounds(BlockSoundGroup.WOOD)
                    .dropsLike(ModBlocks.EMERALD_TORCH)
                    .pistonBehavior(PistonBehavior.DESTROY)));


    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TerMc.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TerMc.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    //Add Blocks to "Natural" tab in creative menu
    private static void customNatural(FabricItemGroupEntries entries){
        entries.add(FLUORITE_ORE);
        entries.add(FLUORITE_DEEPSLATE_ORE);
        entries.add(FLUORITE_NETHER_ORE);
        entries.add(FLUORITE_END_ORE);
    }

    public static void registerModBlocks(){
        TerMc.LOGGER.info("Registering Mod Blocks for " + TerMc.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModBlocks::customNatural);
    }
}
