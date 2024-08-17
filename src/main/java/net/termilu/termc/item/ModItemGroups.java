package net.termilu.termc.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup TERMC_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TerMc.MOD_ID, "termc"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.termc"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModItems.STAR_FRAGMENT)).entries((displayContext, entries) -> {
                        //Items
                        entries.add(ModItems.FLUORITE);
                        entries.add(ModItems.RAW_FLUORITE);
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);

                        //Custom items
                        entries.add(ModItems.CHAINSAW);

                        //Tools
                        entries.add(ModItems.FLUORITE_SWORD);
                        entries.add(ModItems.FLUORITE_PICKAXE);
                        entries.add(ModItems.FLUORITE_AXE);
                        entries.add(ModItems.FLUORITE_SHOVEL);
                        entries.add(ModItems.FLUORITE_HOE);
                        entries.add(ModItems.FLUORITE_MULTITOOL);
                        entries.add(ModItems.FLUORITE_HAMMER);

                        //Blocks
                        entries.add(ModBlocks.FLUORITE_BLOCK);
                        entries.add(ModBlocks.FLUORITE_ORE);
                        entries.add(ModBlocks.FLUORITE_DEEPSLATE_ORE);
                        entries.add(ModBlocks.FLUORITE_NETHER_ORE);
                        entries.add(ModBlocks.FLUORITE_END_ORE);

                        entries.add(ModBlocks.FLUORITE_STAIRS);
                        entries.add(ModBlocks.FLUORITE_SLAB);

                        entries.add(ModBlocks.LAPIS_TORCH);
                        entries.add(ModBlocks.EMERALD_TORCH);


                        //Redstone
                        entries.add(ModBlocks.FLUORITE_BUTTON);
                        entries.add(ModBlocks.FLUORITE_PRESSURE_PLATE);

                        //Custom blocks

                        entries.add(ModBlocks.MAGIC_BLOCK);

                        //Spawn eggs
                        entries.add(ModItems.DODO_SPAWN_EGG);

                        //Food
                        entries.add(ModItems.STRAWBERRY);
                        entries.add(ModItems.ORANGE);

                        //Fuel
                        entries.add(ModItems.STAR_FRAGMENT);

                        // Add new Items for Itemgroup fluorite here

                    }).build());

    public static final ItemGroup ORE_GROUP = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(TerMc.MOD_ID, "ore"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ore"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModItems.FLUORITE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.FLUORITE);
                        entries.add(ModItems.RAW_FLUORITE);

                        entries.add(ModBlocks.FLUORITE_ORE);
                        entries.add(ModBlocks.FLUORITE_DEEPSLATE_ORE);
                        entries.add(ModBlocks.FLUORITE_NETHER_ORE);
                        entries.add(ModBlocks.FLUORITE_END_ORE);

                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);
                        // Add new Items for Itemgroup fluorite here

                    }).build());

    public static final ItemGroup FLUORITE_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(TerMc.MOD_ID, "fluorite_blocks"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fluorite_blocks"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModBlocks.FLUORITE_BLOCK)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.FLUORITE_BLOCK);

                        entries.add(ModBlocks.FLUORITE_STAIRS);
                        entries.add(ModBlocks.FLUORITE_SLAB);

                        entries.add(ModBlocks.FLUORITE_BUTTON);
                        entries.add(ModBlocks.FLUORITE_PRESSURE_PLATE);

                        entries.add(ModBlocks.FLUORITE_FENCE);
                        entries.add(ModBlocks.FLUORITE_FENCE_GATE);
                        entries.add(ModBlocks.FLUORITE_WALL);

                        entries.add(ModBlocks.FLUORITE_DOOR);
                        entries.add(ModBlocks.FLUORITE_TRAPDOOR);

                        // Add new Items for Itemgroup fluorite_blocks here

                    }).build());

    public static final ItemGroup TOOL_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TerMc.MOD_ID, "tools"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.tools"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModItems.CHAINSAW)).entries((displayContext, entries) -> {
                        entries.add(ModItems.CHAINSAW);

                        entries.add(ModItems.FLUORITE_SWORD);
                        entries.add(ModItems.FLUORITE_PICKAXE);
                        entries.add(ModItems.FLUORITE_AXE);
                        entries.add(ModItems.FLUORITE_SHOVEL);
                        entries.add(ModItems.FLUORITE_HOE);
                        entries.add(ModItems.FLUORITE_MULTITOOL);
                        entries.add(ModItems.FLUORITE_HAMMER);

                        // Add new Items for Itemgroup fluorite_blocks here

                    }).build());

    public static final ItemGroup FOOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TerMc.MOD_ID, "food"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.food"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModItems.STRAWBERRY)).entries((displayContext, entries) -> {
                        entries.add(ModItems.STRAWBERRY);
                        entries.add(ModItems.ORANGE);


                    }).build());

    public static void registerItemGroups(){
        TerMc.LOGGER.info("Registering Item Groups for " + TerMc.MOD_ID);
    }
}
