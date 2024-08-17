package net.termilu.termc.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.termilu.termc.TerMc;
import net.termilu.termc.entity.custom.CapybaraEntity;
import net.termilu.termc.entity.variant.CapybaraVariant;

import java.util.Map;

public class CapybaraRenderer extends MobEntityRenderer<CapybaraEntity, CapybaraModel> {
    //For variants map is needed
    private static final Map<CapybaraVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(CapybaraVariant.class), map -> {
                map.put(CapybaraVariant.BROWN, Identifier.of(TerMc.MOD_ID, "textures/entity/capybara/capybara.png"));
                map.put(CapybaraVariant.WHITE, Identifier.of(TerMc.MOD_ID, "textures/entity/capybara/capybara_snow.png"));

            });

    public CapybaraRenderer(EntityRendererFactory.Context context) {
        super(context, new CapybaraModel(context.getPart(ModEntityModelLayers.CAPYBARA)), 0.5f);
    }

    @Override
    public Identifier getTexture(CapybaraEntity entity) {
        //If only one variant exists
        //return Identifier.of(TerMc.MOD_ID, "textures/entity/capybara/capybara.png");

        //For multiple Variants
        return LOCATION_BY_VARIANT.get(entity.getVariant());
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
