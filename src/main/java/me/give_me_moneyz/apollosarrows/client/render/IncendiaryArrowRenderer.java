package me.give_me_moneyz.apollosarrows.client.render;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.entities.IncendiaryArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class IncendiaryArrowRenderer extends ArrowRenderer<IncendiaryArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ApollosArrows.MODID,
            "textures/entity/incendiary_arrow.png");

    public IncendiaryArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(IncendiaryArrowEntity arrow) {
        return TEXTURE;
    }
}