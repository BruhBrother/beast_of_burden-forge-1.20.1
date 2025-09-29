package net.bruhbrother.beast_of_burden.entity.ai;

import net.bruhbrother.beast_of_burden.entity.custom.CoalconstructEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class CoalAttackGoal extends MeleeAttackGoal {
    private final CoalconstructEntity entity;
    private int attackDelay = 17; // ticks until damage is applied
    private int ticksUntilNextAttack = 20;
    private boolean shouldCountDown = false;

    public CoalAttackGoal(CoalconstructEntity mob, double speedModifier, boolean followEvenIfNotSeen) {
        super(mob, speedModifier, followEvenIfNotSeen);
        this.entity = mob;
    }

    @Override
    public void start() {
        super.start();
        ticksUntilNextAttack = 20;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target, double distToEnemySqr) {
        if (distToEnemySqr <= this.getAttackReachSqr(target)) {
            shouldCountDown = true;

            // Start attack animation a bit before the hit
            if (ticksUntilNextAttack <= attackDelay) {
                if (!entity.isAttacking()) {
                    entity.setAttacking(true);  // triggers the animation
                }
            }

            if (ticksUntilNextAttack <= 0) {
                this.mob.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ());
                this.mob.doHurtTarget(target);
                resetAttackCooldown();
                entity.setAttacking(false); // stop flag after hit
            }
        } else {
            resetAttackCooldown();
            shouldCountDown = false;
            entity.setAttacking(false);
        }
    }

    @Override
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20); // every 20 ticks
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountDown) {
            ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}
