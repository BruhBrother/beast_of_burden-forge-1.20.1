package net.bruhbrother.beast_of_burden.entity.client;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.custom.CoalconstructEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CoalconstructRenderer extends GeoEntityRenderer<CoalconstructEntity> {
    public CoalconstructRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CoalconstructModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CoalconstructEntity animatable) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "textures/entity/coal_construct.png");
    }
}
