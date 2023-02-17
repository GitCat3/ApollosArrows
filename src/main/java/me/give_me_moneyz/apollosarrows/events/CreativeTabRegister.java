package me.give_me_moneyz.apollosarrows.events;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApollosArrows.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabRegister {
    public static CreativeModeTab CREATIVE_TAB;

    @SubscribeEvent
    public static void RegisterCreativeTab(CreativeModeTabEvent.Register event) {
        CREATIVE_TAB = event.registerCreativeModeTab(new ResourceLocation(ApollosArrows.MODID, "arrows_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.EXPLOSIVE_ARROW.get())).title(
                        Component.translatable("creativemodetab.arrow_tab")));
    }
}
