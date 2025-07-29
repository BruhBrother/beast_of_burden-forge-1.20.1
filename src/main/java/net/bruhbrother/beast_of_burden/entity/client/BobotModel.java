package net.bruhbrother.beast_of_burden.entity.client;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.custom.BobotEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BobotModel extends GeoModel<BobotEntity> {
    @Override
    public ResourceLocation getModelResource(BobotEntity bobotEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "geo/bobot.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BobotEntity bobotEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "textures/entity/bobot.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BobotEntity bobotEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "animations/bobot.animation.json");
    }

    @Override
    public void setCustomAnimations(BobotEntity animatable, long instanceId, AnimationState<BobotEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(-entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
