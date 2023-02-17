package me.give_me_moneyz.apollosarrows.events;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApollosArrows.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CreativeTabEvent {
    @SubscribeEvent
    public static void CreativeModeTabEvent(final CreativeModeTabEvent.BuildContents event) {
        event.accept(ModItems.BOMB_ARROW);
    }
}
