package me.give_me_moneyz.apollosarrows.events;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.registry.CreativeTabRegistry;
import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApollosArrows.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AddToCreativeTab {
    @SubscribeEvent
    public static void addToCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == CreativeTabRegistry.ARROWS_TAB.get()) {
            event.accept(ModItems.EXPLOSIVE_ARROW);
            event.accept(ModItems.MAGNETIC_ARROW);
            event.accept(ModItems.ENDER_ARROW);
            event.accept(ModItems.AIRSTRIKE_ARROW);
            event.accept(ModItems.TRANSMUTATION_ARROW);
            event.accept(ModItems.MITOSIS_ARROW);
            event.accept(ModItems.INCENDIARY_ARROW);
        }
    }
}
