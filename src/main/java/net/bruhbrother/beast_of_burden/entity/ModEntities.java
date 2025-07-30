package net.bruhbrother.beast_of_burden.entity;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.custom.BobotEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BeastofBurden.MOD_ID);

    public static final RegistryObject<EntityType<BobotEntity>> BOBOT =
            ENTITY_TYPES.register("bobot",
                    () -> EntityType.Builder.of(BobotEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 3.1f)
                            .build(new ResourceLocation(BeastofBurden.MOD_ID, "bobot").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
