package me.give_me_moneyz.apollosarrows.registry;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.items.ExplosiveArrowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            ApollosArrows.MODID);
    public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            () -> new ExplosiveArrowItem(new Item.Properties()));
}
