package net.termilu.termc.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.entity.custom.CapybaraEntity;

public class CapybaraRenderer extends MobEntityRenderer<CapybaraEntity, CapybaraModel> {

    public CapybaraRenderer(EntityRendererFactory.Context context) {
        super(context, new CapybaraModel(context.getPart(ModEntityModelLayers.CAPYBARA)), 0.5f);
    }

    @Override
    public Identifier getTexture(CapybaraEntity entity) {
        return Identifier.of(TerMc.MOD_ID, "textures/entity/capybara/capybara.png");
    }

    @Override
    public void render(CapybaraEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        //Scale down if it's a baby
        if(livingEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
