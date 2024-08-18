package net.termilu.termc.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.termilu.termc.entity.ModEntities;
import net.termilu.termc.entity.variant.DodoVariant;
import net.termilu.termc.item.ModItems;
import org.jetbrains.annotations.Nullable;

//TODO: Make Dodos tame- and rideable
public class DodoEntity extends AnimalEntity {

    //TrackedData for Variant ID's synced between Server and Client
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(DodoEntity.class, TrackedDataHandlerRegistry.INTEGER);

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

    /* VARIANT */

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        //By default spawn blue dodo variant
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    private int getTypeVariant(){
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    public DodoVariant getVariant(){
        //Bitwise & taken from HorseEntity
        return DodoVariant.byID(getTypeVariant() & 255);
    }

    public void setVariant(DodoVariant variant){
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    //Dodo chooses random variant when it spawns
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        DodoVariant variant = Util.getRandom(DodoVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    //Read and write custom variant data to nbt so variants persist through world saving
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }
}
