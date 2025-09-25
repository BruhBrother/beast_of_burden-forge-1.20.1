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
            () -> new ForgeSpawnEggItem(ModEntities.BOBOT, 0x171616, 0x171616,
                    new Item.Properties()));

    public static final RegistryObject<Item> COALCONSTRUCT_SPAWN_EGG = ITEMS.register("coalconstruct_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.COALCONSTRUCT, 0x171616, 0x6B6969,
                    new Item.Properties()));

    public static final RegistryObject<Item> LAMPBUG_SPAWN_EGG = ITEMS.register("lampbug_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LAMPBUG, 0x171616, 0x7ECC49,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
