package net.bruhbrother.beast_of_burden.entity.client;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.custom.LampbugEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LampbugRenderer extends GeoEntityRenderer<LampbugEntity> {
    public LampbugRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LampbugModel());
    }

    @Override
    public ResourceLocation getTextureLocation(LampbugEntity animatable) {
        return new ResourceLocation(BeastofBurden.MOD_ID, "textures/entity/lamp_bug.png");
    }
}