package me.give_me_moneyz.apollosarrows.client.render;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.entities.EnderArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EnderArrowRenderer extends ArrowRenderer<EnderArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ApollosArrows.MODID,
            "textures/entity/ender_arrow.png");

    public EnderArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(EnderArrowEntity arrow) {
        return TEXTURE;
    }
}