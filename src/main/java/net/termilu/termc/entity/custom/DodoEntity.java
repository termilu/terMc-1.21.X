package net.termilu.termc.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.termilu.termc.entity.ModEntities;
import net.termilu.termc.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class DodoEntity extends AnimalEntity {

    public final AnimationState idleAnimationstate = new AnimationState();
    private int idleAnimationTimeout = 0;

    public DodoEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    //Entity AI copied from cow
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.25, stack -> stack.isOf(ModItems.STRAWBERRY), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            //Idle Animation length in seconds * 20 ticks
            //Dodo Animation length: 2s * 20 ticks = 40
            this.idleAnimationTimeout = 40;
            this.idleAnimationstate.start(this.age);
        }else {
            --this.idleAnimationTimeout;
        }
    }

    //Setup animations on client
    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()){
            this.setupAnimationStates();
        }
    }

    public static DefaultAttributeContainer.Builder createDodoAttributes(){
        return MobEntity.createMobAttributes()
                //Always needed Attributes
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1);
    }

    //Set breeding item
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.STRAWBERRY);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.DODO.create(world);
    }
}
