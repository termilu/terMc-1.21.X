package net.termilu.termc.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;

public class TermBowItem extends BowItem {
    public TermBowItem(Settings settings) {
        super(settings);
    }

    @Override
    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, LivingEntity target) {
        if (projectile instanceof ArrowEntity) {
            ((ArrowEntity) projectile).setDamage(((ArrowEntity) projectile).getDamage() * 1.5); // Increase the damage
        }
        super.shoot(shooter, projectile, index, speed, divergence, yaw, target);
    }
}
