package net.termilu.termc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    //Generate Mod Block Tag jsons automatically
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //Generate pickaxe.json
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.FLUORITE_BLOCK,
                        ModBlocks.FLUORITE_ORE,
                        ModBlocks.FLUORITE_DEEPSLATE_ORE,
                        ModBlocks.FLUORITE_NETHER_ORE,
                        ModBlocks.FLUORITE_END_ORE,
                        ModBlocks.FLUORITE_STAIRS,
                        ModBlocks.FLUORITE_SLAB,
                        ModBlocks.FLUORITE_BUTTON,
                        ModBlocks.FLUORITE_PRESSURE_PLATE,
                        ModBlocks.FLUORITE_FENCE,
                        ModBlocks.FLUORITE_FENCE_GATE,
                        ModBlocks.FLUORITE_WALL,
                        ModBlocks.FLUORITE_DOOR,
                        ModBlocks.FLUORITE_TRAPDOOR);

        //Generate needs_iron_tool.json
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.FLUORITE_BLOCK,
                        ModBlocks.FLUORITE_ORE,
                        ModBlocks.FLUORITE_DEEPSLATE_ORE,
                        ModBlocks.FLUORITE_NETHER_ORE,
                        ModBlocks.FLUORITE_END_ORE,
                        ModBlocks.FLUORITE_STAIRS,
                        ModBlocks.FLUORITE_SLAB,
                        ModBlocks.FLUORITE_BUTTON,
                        ModBlocks.FLUORITE_PRESSURE_PLATE,
                        ModBlocks.FLUORITE_FENCE,
                        ModBlocks.FLUORITE_FENCE_GATE,
                        ModBlocks.FLUORITE_WALL,
                        ModBlocks.FLUORITE_DOOR,
                        ModBlocks.FLUORITE_TRAPDOOR);

        //Fences, Fence gates and Walls need a custom BlockTag otherwise they won't connect
        //BlockTags.WOODEN_FENCES to connect with wooden fences
        //BlockTags.FENCES to connect with non-wooden fences
        getOrCreateTagBuilder(BlockTags.FENCES).add(ModBlocks.FLUORITE_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.FLUORITE_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.FLUORITE_WALL);

        //Multitool TagBuilder to determine what it can mine
        getOrCreateTagBuilder(ModTags.Blocks.MULTITOOL_MINEABLE)
                .forceAddTag(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(BlockTags.AXE_MINEABLE)
                .forceAddTag(BlockTags.SHOVEL_MINEABLE);

        //Tree Logs and Wood (that can burn)
        //Needs to be added to BlockTags.LOGS_THAT_BURN otherwise leaves will decay
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLACKWOOD_LOG, ModBlocks.BLACKWOOD_WOOD,
                        ModBlocks.STRIPPED_BLACKWOOD_LOG, ModBlocks.STRIPPED_BLACKWOOD_WOOD);


        getOrCreateTagBuilder(ModTags.Blocks.BLACKWOOD_LOGS)
                .add(ModBlocks.BLACKWOOD_LOG, ModBlocks.BLACKWOOD_WOOD,
                        ModBlocks.STRIPPED_BLACKWOOD_LOG, ModBlocks.STRIPPED_BLACKWOOD_WOOD);
    }
}
