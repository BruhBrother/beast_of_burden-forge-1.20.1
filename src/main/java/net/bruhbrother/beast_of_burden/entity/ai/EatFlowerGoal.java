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
    private BlockPos findNearbyFlower() {
        BlockPos origin = lampbug.blockPosition();
        int radius = 20; // search radius
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = origin.offset(x, y, z);
                    if (lampbug.level().getBlockState(pos).is(BlockTags.FLOWERS)) {
                        return pos;
                    }
                }
            }
        }
        return null; // no flower found nearby
    }

    public EatFlowerGoal(LampbugEntity lampbugEntity) {
        this.lampbug = lampbugEntity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return lampbug.isWeakened();
    }

    @Override
    public void tick() {
        if (cooldown > 0) {
            cooldown--;
            return;
        }

        // 1. Find a nearby flower
        BlockPos flowerPos = findNearbyFlower();
        if (flowerPos != null) {
            // Move toward it
            lampbug.getNavigation().moveTo(flowerPos.getX(), flowerPos.getY(), flowerPos.getZ(), 1.0);
        }

        // 2. Check block below to eat
        BlockPos pos = lampbug.blockPosition();
        BlockState stateBelow = lampbug.level().getBlockState(pos.below());

        if (stateBelow.is(BlockTags.FLOWERS)) {
            lampbug.level().destroyBlock(pos.below(), false); // eat the flower
            lampbug.incrementFlowers();
            cooldown = 40;
        }
    }

    @Override
    public boolean canContinueToUse() {
        // Keep running until Lampbug recovers
        return lampbug.isWeakened();
    }
}

