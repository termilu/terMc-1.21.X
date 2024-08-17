package net.termilu.termc.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.termilu.termc.TerMc;
import net.termilu.termc.entity.custom.DodoEntity;

public class DodoRenderer extends MobEntityRenderer<DodoEntity, DodoModel> {
    public DodoRenderer(EntityRendererFactory.Context context) {
        super(context, new DodoModel(context.getPart(ModEntityModelLayers.DODO)), 0.5f);
    }

    @Override
    public Identifier getTexture(DodoEntity entity) {
        return Identifier.of(TerMc.MOD_ID, "textures/entity/dodo/dodo_blue.png");
    }

    @Override
    public void render(DodoEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        //Scale down if it's a baby
        if(livingEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
