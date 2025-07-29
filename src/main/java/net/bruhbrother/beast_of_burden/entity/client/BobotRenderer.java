package net.bruhbrother.beast_of_burden.entity.client;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.custom.BobotEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BobotRenderer extends GeoEntityRenderer<BobotEntity> {
    public BobotRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BobotModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BobotEntity animatable) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "textures/entity/bobot.png");
    }

}
