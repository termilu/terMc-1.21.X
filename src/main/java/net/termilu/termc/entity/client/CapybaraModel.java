package net.termilu.termc.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.termilu.termc.entity.client.animation.CapybaraAnimations;
import net.termilu.termc.entity.custom.CapybaraEntity;

public class CapybaraModel extends SinglePartEntityModel<CapybaraEntity> {
    private final ModelPart Body;
    private final ModelPart Head;

    public CapybaraModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = Body.getChild("Head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -2.5F, -4.5F, 5.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 17.95F, -0.75F));

        ModelPartData Head = Body.addChild("Head", ModelPartBuilder.create().uv(0, 14).cuboid(-1.5F, -1.5F, -4.75F, 3.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, -1.45F, -4.0F));

        ModelPartData EarL = Head.addChild("EarL", ModelPartBuilder.create(), ModelTransform.pivot(1.35F, -1.45F, -1.25F));

        ModelPartData EarL_r1 = EarL.addChild("EarL_r1", ModelPartBuilder.create().uv(0, 1).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, -0.35F, 0.0F, 0.0369F, -0.2062F, -0.1784F));

        ModelPartData EarR = Head.addChild("EarR", ModelPartBuilder.create(), ModelTransform.pivot(-1.35F, -1.45F, -1.25F));

        ModelPartData EarR_r1 = EarR.addChild("EarR_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, -0.35F, 0.0F, 0.0369F, 0.2062F, 0.1784F));

        ModelPartData LegBR = Body.addChild("LegBR", ModelPartBuilder.create().uv(16, 14).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 2.55F, 2.95F));

        ModelPartData LegBL = Body.addChild("LegBL", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.9F, 2.55F, 2.95F));

        ModelPartData LegFL = Body.addChild("LegFL", ModelPartBuilder.create().uv(14, 20).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.9F, 2.55F, -2.35F));

        ModelPartData LegFR = Body.addChild("LegFR", ModelPartBuilder.create().uv(19, 0).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 2.55F, -2.35F));

        ModelPartData Fur = Body.addChild("Fur", ModelPartBuilder.create(), ModelTransform.pivot(-0.3714F, 0.7768F, -1.5214F));

        ModelPartData cube_r1 = Fur.addChild("cube_r1", ModelPartBuilder.create().uv(12, 20).mirrored().cuboid(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.7286F, 3.3732F, 1.2714F, 0.0F, 0.0F, 0.0524F));

        ModelPartData cube_r2 = Fur.addChild("cube_r2", ModelPartBuilder.create().uv(12, 20).mirrored().cuboid(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0286F, 2.9732F, 1.0714F, 0.0F, 0.0F, 0.3316F));

        ModelPartData cube_r3 = Fur.addChild("cube_r3", ModelPartBuilder.create().uv(12, 20).mirrored().cuboid(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0286F, 2.5732F, 1.8714F, 0.0F, 0.0F, 0.2269F));

        ModelPartData cube_r4 = Fur.addChild("cube_r4", ModelPartBuilder.create().uv(12, 20).cuboid(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.7714F, 2.9732F, 1.2714F, 0.0F, 0.0F, -0.3316F));

        ModelPartData cube_r5 = Fur.addChild("cube_r5", ModelPartBuilder.create().uv(12, 20).mirrored().cuboid(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.7286F, 3.7732F, 1.2714F, 0.0F, 0.0F, 0.1571F));

        ModelPartData cube_r6 = Fur.addChild("cube_r6", ModelPartBuilder.create().uv(12, 20).cuboid(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.7714F, 2.5732F, 1.7714F, 0.0F, 0.0F, -0.2269F));

        ModelPartData cube_r7 = Fur.addChild("cube_r7", ModelPartBuilder.create().uv(12, 20).cuboid(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.4714F, 3.3732F, 1.2714F, 0.0F, 0.0F, -0.0524F));

        ModelPartData cube_r8 = Fur.addChild("cube_r8", ModelPartBuilder.create().uv(-2, 28).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.2286F, -3.3268F, -1.5286F, 0.0873F, 0.0F, 0.0F));

        ModelPartData cube_r9 = Fur.addChild("cube_r9", ModelPartBuilder.create().uv(-2, 28).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5714F, -3.3268F, -1.5286F, 0.2618F, 0.0F, 0.0F));

        ModelPartData cube_r10 = Fur.addChild("cube_r10", ModelPartBuilder.create().uv(-2, 28).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.1714F, -3.3268F, -2.2286F, 0.2356F, 0.0F, 0.0F));

        ModelPartData cube_r11 = Fur.addChild("cube_r11", ModelPartBuilder.create().uv(-2, 28).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5714F, -3.3268F, -2.0286F, 0.1745F, 0.0F, 0.0F));

        ModelPartData cube_r12 = Fur.addChild("cube_r12", ModelPartBuilder.create().uv(-2, 28).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.1714F, -3.3268F, -1.7286F, 0.4102F, 0.0F, 0.0F));

        ModelPartData cube_r13 = Fur.addChild("cube_r13", ModelPartBuilder.create().uv(-2, 28).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.2286F, -3.3268F, -2.0286F, 0.0F, 0.0F, 0.0F));

        ModelPartData cube_r14 = Fur.addChild("cube_r14", ModelPartBuilder.create().uv(12, 20).cuboid(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.4714F, 3.7732F, 1.2714F, 0.0F, 0.0F, -0.1571F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(CapybaraEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //Don't stack animations on top of each other
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        //Add Animations, numbers taken from camel
        this.animateMovement(CapybaraAnimations.walk, limbSwing, limbSwingAmount, 2, 2.5F);
        this.updateAnimation(entity.idleAnimationstate, CapybaraAnimations.idle, ageInTicks, 1);
    }

    private void setHeadAngles(float headYaw, float headPitch){
        //Head turn angle
        headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
        headPitch = MathHelper.clamp(headPitch, -25F, 45F);

        //Taken from camel
        this.Head.yaw = headYaw * (float) (Math.PI / 180.0);
        this.Head.pitch = headPitch * (float) (Math.PI / 180.0);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Body.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return Body;
    }

}
