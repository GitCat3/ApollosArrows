package me.give_me_moneyz.apollosarrows.client;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.client.render.*;
import me.give_me_moneyz.apollosarrows.registry.ModEntityType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ApollosArrows.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityType.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
        EntityRenderers.register(ModEntityType.MAGNETIC_ARROW.get(), MagneticArrowRenderer::new);
        EntityRenderers.register(ModEntityType.ENDER_ARROW.get(), EnderArrowRenderer::new);
        EntityRenderers.register(ModEntityType.AIRSTRIKE_ARROW.get(), AirstrikeArrowRenderer::new);
        EntityRenderers.register(ModEntityType.TRANSMUTATION_ARROW.get(), TransmutationArrowRenderer::new);
    }
}