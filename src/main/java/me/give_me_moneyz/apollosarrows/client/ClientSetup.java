package me.give_me_moneyz.apollosarrows.client;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.client.render.*;
import me.give_me_moneyz.apollosarrows.inventory.FletchingTableScreen;
import me.give_me_moneyz.apollosarrows.registry.ModEntityType;
import me.give_me_moneyz.apollosarrows.registry.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ApollosArrows.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityType.EXPLOSIVE_ARROW.get(), ctx -> new ModArrowRenderer<>(ctx, ResourceLocation.fromNamespaceAndPath(ApollosArrows.MODID, "textures/entity/explosive_arrow.png")));

        event.enqueueWork(() -> MenuScreens.register(ModMenuTypes.FLETCHING_TABLE.get(), FletchingTableScreen::new));
    }
}