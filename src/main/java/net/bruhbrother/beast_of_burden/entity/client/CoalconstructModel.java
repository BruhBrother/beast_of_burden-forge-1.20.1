package net.bruhbrother.beast_of_burden.entity.client;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.custom.BobotEntity;
import net.bruhbrother.beast_of_burden.entity.custom.CoalconstructEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CoalconstructModel extends GeoModel<CoalconstructEntity> {
    @Override
    public ResourceLocation getModelResource(CoalconstructEntity coalconstructEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "geo/coal_construct.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CoalconstructEntity coalconstructEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "textures/entity/coal_construct.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CoalconstructEntity coalconstructEntity) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "animations/coal_construct.animation.json");
    }

    @Override
    public void setCustomAnimations(CoalconstructEntity animatable, long instanceId, AnimationState<CoalconstructEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
