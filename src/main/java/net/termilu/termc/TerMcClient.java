package net.termilu.termc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.termilu.termc.block.ModBlocks;
import net.termilu.termc.entity.ModEntities;
import net.termilu.termc.entity.client.*;
import net.termilu.termc.util.ModModelPredicates;

public class TerMcClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Make doors and trapdoors cutouts see-through
            //Fluorite
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_TRAPDOOR, RenderLayer.getCutout());
            //Blackwood
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACKWOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACKWOOD_TRAPDOOR, RenderLayer.getCutout());

        //Same for torches
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LAPIS_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LAPIS_WALL_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMERALD_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMERALD_WALL_TORCH, RenderLayer.getCutout());

        //Same for Saplings
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACKWOOD_SAPLING, RenderLayer.getCutout());

        //Entity models
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.DODO, DodoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DODO, DodoRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CAPYBARA, CapybaraModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CAPYBARA, CapybaraRenderer::new);

        //ModelPredicates
        ModModelPredicates.registerModelPredicates();
    }
}
