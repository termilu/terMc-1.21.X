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
                    .icon(() -> new ItemStack(ModItems.PINK_GARNET)).entries((displayContext, entries) -> {
                        //Items
                        entries.add(ModItems.FLUORITE);
                        entries.add(ModItems.RAW_FLUORITE);
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);

                        //Custom items
                        entries.add(ModItems.CHAINSAW);

                        //Blocks
                        entries.add(ModBlocks.FLUORITE_BLOCK);
                        entries.add(ModBlocks.FLUORITE_ORE);
                        entries.add(ModBlocks.FLUORITE_DEEPSLATE_ORE);
                        entries.add(ModBlocks.FLUORITE_NETHER_ORE);
                        entries.add(ModBlocks.FLUORITE_END_ORE);

                        //Custom blocks

                        entries.add(ModBlocks.MAGIC_BLOCK);

                        //Food
                        entries.add(ModItems.STRAWBERRY);

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
                        entries.add(ModBlocks.FLUORITE_ORE);
                        entries.add(ModBlocks.FLUORITE_DEEPSLATE_ORE);
                        entries.add(ModBlocks.FLUORITE_NETHER_ORE);
                        entries.add(ModBlocks.FLUORITE_END_ORE);
                        // Add new Items for Itemgroup fluorite_blocks here

                    }).build());

    public static final ItemGroup FOOD = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TerMc.MOD_ID, "food"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.food"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModItems.STRAWBERRY)).entries((displayContext, entries) -> {
                        entries.add(ModItems.STRAWBERRY);


                    }).build());

    public static void registerItemGroups(){
        TerMc.LOGGER.info("Registering Item Groups for " + TerMc.MOD_ID);
    }
}
