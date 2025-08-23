package net.bruhbrother.beast_of_burden.entity.client;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.custom.LampbugEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LampbugModel extends GeoModel<LampbugEntity> {

    @Override
    public ResourceLocation getModelResource(LampbugEntity lampbugEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "geo/lamp_bug.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LampbugEntity lampbugEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID,
                lampbugEntity.isWeakened()
                        ? "textures/entity/lamp_bug_used.png"
                        : "textures/entity/lamp_bug_full.png"
        );
    }

    @Override
    public ResourceLocation getAnimationResource(LampbugEntity lampbugEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "animations/lamp_bug.animation.json");
    }
}