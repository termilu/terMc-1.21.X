package net.termilu.termc.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.termilu.termc.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// With help from https://github.com/Globox1997/MedievalWeapons/blob/1.21/src/main/java/net/medievalweapons/mixin/client/AbstractClientPlayerEntityMixin.java
// Under MIT License!
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    /**
     * Injects custom behavior into the getFovMultiplier method of AbstractClientPlayerEntity.
     * Adjusts the FOV multiplier when the player is using the custom TERM_BOW item.
     *
     * @param info the callback information containing the return value
     * @param f the original FOV multiplier
     */
    @Inject(method="getFovMultiplier", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void getFovMultiplierMixin(CallbackInfoReturnable<Float> info, float f) {
        ItemStack itemStack = this.getActiveItem();
        if (this.isUsingItem() && itemStack.isOf(ModItems.TERM_BOW)) {
            int i = this.getItemUseTime();
            float g = (float) i / 20.0F;
            if (g > 1.0F) {
                g = 1.0F;
            } else {
                g *= g;
            }

            f *= 1.0F - g * 0.15F;
            info.setReturnValue(MathHelper.lerp(MinecraftClient.getInstance().options.getFovEffectScale().getValue().floatValue(), 1.0f, f));
        }
    }
}
