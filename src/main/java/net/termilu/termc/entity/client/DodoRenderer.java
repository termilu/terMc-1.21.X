package net.termilu.termc.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.termilu.termc.TerMc;
import net.termilu.termc.entity.custom.DodoEntity;
import net.termilu.termc.entity.variant.DodoVariant;

import java.util.Map;

public class DodoRenderer extends MobEntityRenderer<DodoEntity, DodoModel> {
    //For variants map is needed
    private static final Map<DodoVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DodoVariant.class), map -> {
                map.put(DodoVariant.BLUE, Identifier.of(TerMc.MOD_ID, "textures/entity/dodo/dodo_blue.png"));
                map.put(DodoVariant.BLACK, Identifier.of(TerMc.MOD_ID, "textures/entity/dodo/dodo_black.png"));

            });

    public DodoRenderer(EntityRendererFactory.Context context) {
        super(context, new DodoModel(context.getPart(ModEntityModelLayers.DODO)), 0.5f);
    }

    @Override
    public Identifier getTexture(DodoEntity entity) {
        //If only one variant exists
        //return Identifier.of(TerMc.MOD_ID, "textures/entity/dodo/dodo_blue.png");

        //For multiple Variants
        return LOCATION_BY_VARIANT.get(entity.getVariant());
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
