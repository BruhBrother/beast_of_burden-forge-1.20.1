package net.bruhbrother.beast_of_burden.entity.custom;

import net.bruhbrother.beast_of_burden.entity.ai.EatFlowerGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class LampbugEntity extends PathfinderMob implements GeoEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private int flowersEaten = 0;
    private boolean weakened = false;

    public LampbugEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,4)
                .add(Attributes.FLYING_SPEED,0.6)
                .add(Attributes.MOVEMENT_SPEED,0.2)
                .add(Attributes.FOLLOW_RANGE,16.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomFlyingGoal(this,1.0D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new EatFlowerGoal(this));
        super.registerGoals();
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        if (!this.level().isClientSide && !weakened) {
            // Spawn regen cloud
            AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            cloud.setRadius(2.0F);
            cloud.setDuration(200);
            cloud.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
            this.level().addFreshEntity(cloud);

            weakened = true;
            this.flowersEaten = 0;
            this.refreshDimensions(); // for texture change
        }
        return super.hurt(pSource, pAmount);
    }

    public boolean isWeakened() {
        return weakened;
    }

    public void incrementFlowers() {
        flowersEaten++;
        if (flowersEaten >= 3) {
            weakened = false;
            flowersEaten = 0;
        }
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {
            state.getController().setAnimation(
                    RawAnimation.begin().thenLoop("idle")
            );
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
