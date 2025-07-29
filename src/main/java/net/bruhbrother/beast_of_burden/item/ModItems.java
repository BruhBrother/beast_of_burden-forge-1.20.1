package net.bruhbrother.beast_of_burden.item;

import net.bruhbrother.beast_of_burden.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "beast_of_burden");

    public static final RegistryObject<Item> BOBOT_SPAWN_EGG = ITEMS.register("bobot_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BOBOT, 0x4CAF50, 0x2E7D32,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
