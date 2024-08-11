package net.termilu.termc.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;

public class ModItemGroups {

    public static final ItemGroup TERMC_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TerMc.MOD_ID, "termc"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.termc"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModItems.PINK_GARNET)).entries((displayContext, entries) -> {
                        entries.add(ModItems.FLUORITE);
                        entries.add(ModItems.RAW_FLUORITE);
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);
                        // Add new Items for Itemgroup fluorite here

                    }).build());

    public static final ItemGroup FLUORITE_GROUP = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(TerMc.MOD_ID, "fluorite"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fluorite"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModItems.FLUORITE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.FLUORITE);
                        entries.add(ModItems.RAW_FLUORITE);
                        // Add new Items for Itemgroup fluorite here

                    }).build());

    public static final ItemGroup FLUORITE_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(TerMc.MOD_ID, "fluorite_blocks"),
            // Build displayName of Itemgroup
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fluorite_blocks"))
                    // Build Icon of Itemgroup
                    .icon(() -> new ItemStack(ModItems.RAW_FLUORITE)).entries((displayContext, entries) -> {
                        entries.add(Blocks.AMETHYST_BLOCK);
                        // Add new Items for Itemgroup fluorite_blocks here

                    }).build());

    public static void registerItemGroups(){
        TerMc.LOGGER.info("Registering Item Groups for " + TerMc.MOD_ID);
    }
}
