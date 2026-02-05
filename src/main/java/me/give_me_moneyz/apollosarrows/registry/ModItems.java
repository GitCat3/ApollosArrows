package me.give_me_moneyz.apollosarrows.registry;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.entities.*;
import me.give_me_moneyz.apollosarrows.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            ApollosArrows.MODID);
    public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            () -> new ModArrowItem(new Item.Properties(),
                    (world, shooter) -> new ExplosiveArrowEntity(ModEntityType.EXPLOSIVE_ARROW.get(), shooter, world)));
    public static final RegistryObject<Item> MAGNETIC_ARROW = ITEMS.register("magnetic_arrow",
            () -> new ModArrowItem(new Item.Properties(),
                    (world, shooter) -> new MagneticArrowEntity(ModEntityType.MAGNETIC_ARROW.get(), shooter, world)));
    public static final RegistryObject<Item> ENDER_ARROW = ITEMS.register("ender_arrow",
            () -> new ModArrowItem(new Item.Properties(),
                    (world, shooter) -> new EnderArrowEntity(ModEntityType.ENDER_ARROW.get(), shooter, world)));
    public static final RegistryObject<Item> AIRSTRIKE_ARROW = ITEMS.register("airstrike_arrow",
            () -> new ModArrowItem(new Item.Properties(),
                    (world, shooter) -> new AirstrikeArrowEntity(ModEntityType.AIRSTRIKE_ARROW.get(), shooter, world)));
    public static final RegistryObject<Item> TRANSMUTATION_ARROW = ITEMS.register("transmutation_arrow",
            () -> new ModArrowItem(new Item.Properties(),
                    (world, shooter) -> new TransmutationArrowEntity(ModEntityType.TRANSMUTATION_ARROW.get(), shooter, world)));
    public static final RegistryObject<Item> MITOSIS_ARROW = ITEMS.register("mitosis_arrow",
            () -> new ModArrowItem(new Item.Properties(),
                    (world, shooter) -> new MitosisArrowEntity(ModEntityType.MITOSIS_ARROW.get(), shooter, world)));
    public static final RegistryObject<Item> INCENDIARY_ARROW = ITEMS.register("incendiary_arrow",
            () -> new ModArrowItem(new Item.Properties(),
                    (world, shooter) -> new IncendiaryArrowEntity(ModEntityType.INCENDIARY_ARROW.get(), shooter, world)));
}
