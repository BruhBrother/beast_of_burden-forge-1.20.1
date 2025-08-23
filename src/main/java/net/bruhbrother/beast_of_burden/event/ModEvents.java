package net.bruhbrother.beast_of_burden.event;

import net.bruhbrother.beast_of_burden.BeastofBurden;
import net.bruhbrother.beast_of_burden.entity.ModEntities;
import net.bruhbrother.beast_of_burden.entity.custom.BobotEntity;
import net.bruhbrother.beast_of_burden.entity.custom.LampbugEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BeastofBurden.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BOBOT.get(), BobotEntity.setAttributes());
        event.put(ModEntities.LAMPBUG.get(), LampbugEntity.setAttributes());
    }
}
