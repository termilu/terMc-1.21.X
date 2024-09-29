package net.termilu.termc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }
    //Generate Mod Loot table jsons automatically
    @Override
    public void generate() {
        //Generate dropping Block when its mined
        addDrop(ModBlocks.FLUORITE_BLOCK);

        addDrop(ModBlocks.MAGIC_BLOCK);
        addDrop(ModBlocks.FLUORITE_LAMP);

        addDrop(ModBlocks.FLUORITE_STAIRS);

        addDrop(ModBlocks.BLACKWOOD_STAIRS);

        addDrop(ModBlocks.LAPIS_TORCH);
        addDrop(ModBlocks.EMERALD_TORCH);

        //Trees
        addDrop(ModBlocks.BLACKWOOD_LOG);
        addDrop(ModBlocks.BLACKWOOD_WOOD);

        addDrop(ModBlocks.STRIPPED_BLACKWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_BLACKWOOD_WOOD);

        addDrop(ModBlocks.BLACKWOOD_PLANKS);

        addDrop(ModBlocks.BLACKWOOD_SAPLING);

        addDrop(ModBlocks.BLACKWOOD_LEAVES, leavesDrops(ModBlocks.BLACKWOOD_LEAVES, ModBlocks.BLACKWOOD_SAPLING, 0.0625f));


        //Double-Blocks need extra typeDrops otherwise they drop twice
        addDrop(ModBlocks.FLUORITE_SLAB, slabDrops(ModBlocks.FLUORITE_SLAB));
        addDrop(ModBlocks.FLUORITE_DOOR, doorDrops(ModBlocks.FLUORITE_DOOR));

        addDrop(ModBlocks.BLACKWOOD_SLAB, slabDrops(ModBlocks.BLACKWOOD_SLAB));
        addDrop(ModBlocks.BLACKWOOD_DOOR, doorDrops(ModBlocks.BLACKWOOD_DOOR));

        addDrop(ModBlocks.FLUORITE_TRAPDOOR);
        addDrop(ModBlocks.FLUORITE_BUTTON);
        addDrop(ModBlocks.FLUORITE_PRESSURE_PLATE);

        addDrop(ModBlocks.BLACKWOOD_TRAPDOOR);
        addDrop(ModBlocks.BLACKWOOD_BUTTON);
        addDrop(ModBlocks.BLACKWOOD_PRESSURE_PLATE);

        addDrop(ModBlocks.FLUORITE_FENCE);
        addDrop(ModBlocks.FLUORITE_FENCE_GATE);
        addDrop(ModBlocks.FLUORITE_WALL);

        addDrop(ModBlocks.BLACKWOOD_FENCE);
        addDrop(ModBlocks.BLACKWOOD_FENCE_GATE);
        addDrop(ModBlocks.BLACKWOOD_WALL);


        //Generate ore drops when mined, oreDrops(withSilkTouch, withoutSilkTouch)
        //                               multipleOreDrops(withSilkTouch, withoutSilkTouch, minDrops, maxDrops)
        addDrop(ModBlocks.FLUORITE_ORE, oreDrops(ModBlocks.FLUORITE_ORE, ModItems.RAW_FLUORITE));
        addDrop(ModBlocks.FLUORITE_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.FLUORITE_DEEPSLATE_ORE, ModItems.RAW_FLUORITE, 2,5));
        addDrop(ModBlocks.FLUORITE_NETHER_ORE, multipleOreDrops(ModBlocks.FLUORITE_NETHER_ORE, ModItems.RAW_FLUORITE, 2,5));
        addDrop(ModBlocks.FLUORITE_END_ORE, multipleOreDrops(ModBlocks.FLUORITE_END_ORE, ModItems.RAW_FLUORITE, 2,5));
    }

    //Stolen & modified from copperOreDrops()
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop,
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops)))
                        .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

}
