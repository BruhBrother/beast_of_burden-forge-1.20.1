package net.bruhbrother.beast_of_burden.entity.ai;

import net.bruhbrother.beast_of_burden.entity.custom.LampbugEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class EatFlowerGoal extends Goal {
    private final LampbugEntity lampbug;
    private int cooldown = 0;

    public EatFlowerGoal(LampbugEntity lampbug) {
        this.lampbug = lampbug;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Only run if weakened
        return lampbug.isWeakened();
    }

    @Override
    public void tick() {
        if (cooldown > 0) {
            cooldown--;
            return;
        }

        BlockPos pos = lampbug.blockPosition();
        BlockState stateBelow = lampbug.level().getBlockState(pos.below());

        if (stateBelow.is(BlockTags.FLOWERS)) {
            lampbug.level().destroyBlock(pos.below(), false); // "eat" the flower
            lampbug.incrementFlowers();
            cooldown = 40; // 2 seconds cooldown before eating next flower
        }
    }

}
