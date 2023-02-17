package me.give_me_moneyz.apollosarrows.client.render;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.entities.ExplosiveArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ExplosiveArrowRenderer extends ArrowRenderer<ExplosiveArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ApollosArrows.MODID,
            "textures/entity/explosive_arrow.png");

    public ExplosiveArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(ExplosiveArrowEntity arrow) {
        return TEXTURE;
    }
}