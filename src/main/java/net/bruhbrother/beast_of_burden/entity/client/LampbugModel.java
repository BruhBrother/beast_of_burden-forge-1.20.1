package net.bruhbrother.beast_of_burden.entity.client;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.custom.LampbugEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LampbugModel extends GeoModel<LampbugEntity> {
    @Override
    public ResourceLocation getModelResource(LampbugEntity lampbugEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "geo/lamp_bug.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LampbugEntity lampbugEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID,
                lampbugEntity.isWeakened()
                        ? "textures/entity/lamp_bug_weakened.png"
                        : "textures/entity/lamp_bug_full.png"
        );
    }

    @Override
    public ResourceLocation getAnimationResource(LampbugEntity lampbugEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "lamp_bug.animation.json");
    }

    @Override
    public void setCustomAnimations(LampbugEntity animatable, long instanceId, AnimationState<LampbugEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
