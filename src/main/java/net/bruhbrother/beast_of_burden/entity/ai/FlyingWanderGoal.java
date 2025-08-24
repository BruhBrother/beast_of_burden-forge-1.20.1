package net.bruhbrother.beast_of_burden.entity.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class FlyingWanderGoal extends Goal {
    private final Mob mob;
    private final double speed;

    public FlyingWanderGoal(Mob mob, double speed) {
        this.mob = mob;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return mob.getNavigation().isDone();
    }

    @Override
    public void start() {
        Vec3 vec3 = DefaultRandomPos.getPos((PathfinderMob) mob, 10, 7); // range, height
        if (vec3 != null) {
            mob.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, speed);
        }
    }
}

