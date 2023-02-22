package me.give_me_moneyz.apollosarrows.client.render;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.entities.TransmutationArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TransmutationArrowRenderer extends ArrowRenderer<TransmutationArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ApollosArrows.MODID,
            "textures/entity/transmutation_arrow.png");

    public TransmutationArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(TransmutationArrowEntity arrow) {
        return TEXTURE;
    }
}