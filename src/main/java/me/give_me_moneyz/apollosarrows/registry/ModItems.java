package me.give_me_moneyz.apollosarrows.registry;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            ApollosArrows.MODID);
    public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            () -> new ExplosiveArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGNETIC_ARROW = ITEMS.register("magnetic_arrow",
            () -> new MagneticArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_ARROW = ITEMS.register("ender_arrow",
            () -> new EnderArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> AIRSTRIKE_ARROW = ITEMS.register("airstrike_arrow",
            () -> new AirstrikeArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> TRANSMUTATION_ARROW = ITEMS.register("transmutation_arrow",
            () -> new TransmutationArrowItem(new Item.Properties()));
}
