package net.bruhbrother.beast_of_burden.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class BobotEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public BobotEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.4f)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true) {
            @Override
            protected double getAttackReachSqr(@NotNull LivingEntity target) {
                return 3.0D;
            }});
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.00));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Zombie.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Skeleton.class, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {
            state.getController().setAnimation(
                    RawAnimation.begin().thenLoop("Robot_Walking")
            );
        } else {
            state.getController().setAnimation(
                    RawAnimation.begin().thenLoop("Robot_Idle1")
            );
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
