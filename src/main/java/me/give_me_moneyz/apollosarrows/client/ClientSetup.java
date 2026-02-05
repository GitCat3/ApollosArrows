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
        EntityRenderers.register(ModEntityType.MAGNETIC_ARROW.get(), ctx -> new ModArrowRenderer<>(ctx, ResourceLocation.fromNamespaceAndPath(ApollosArrows.MODID, "textures/entity/magnetic_arrow.png")));
        EntityRenderers.register(ModEntityType.ENDER_ARROW.get(), ctx -> new ModArrowRenderer<>(ctx, ResourceLocation.fromNamespaceAndPath(ApollosArrows.MODID, "textures/entity/ender_arrow.png")));
        EntityRenderers.register(ModEntityType.AIRSTRIKE_ARROW.get(), ctx -> new ModArrowRenderer<>(ctx, ResourceLocation.fromNamespaceAndPath(ApollosArrows.MODID, "textures/entity/airstrike_arrow.png")));
        EntityRenderers.register(ModEntityType.TRANSMUTATION_ARROW.get(), ctx -> new ModArrowRenderer<>(ctx, ResourceLocation.fromNamespaceAndPath(ApollosArrows.MODID, "textures/entity/transmutation_arrow.png")));
        EntityRenderers.register(ModEntityType.MITOSIS_ARROW.get(), ctx -> new ModArrowRenderer<>(ctx, ResourceLocation.fromNamespaceAndPath(ApollosArrows.MODID, "textures/entity/mitosis_arrow.png")));
        EntityRenderers.register(ModEntityType.INCENDIARY_ARROW.get(), ctx -> new ModArrowRenderer<>(ctx, ResourceLocation.fromNamespaceAndPath(ApollosArrows.MODID, "textures/entity/incendiary_arrow.png")));

        event.enqueueWork(() -> MenuScreens.register(ModMenuTypes.FLETCHING_TABLE.get(), FletchingTableScreen::new));
    }
}