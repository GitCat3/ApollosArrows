package me.give_me_moneyz.apollosarrows.registry;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTRAR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ApollosArrows.MODID);

    public static final RegistryObject<CreativeModeTab> ARROWS_TAB = REGISTRAR.register("arrows_tab",
    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EXPLOSIVE_ARROW.get())).title(
            Component.translatable("creativemodetab.arrow_tab")).build());

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }
}
