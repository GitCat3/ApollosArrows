package me.give_me_moneyz.apollosarrows.client.render;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.entities.ExplosiveArrowEntity;
import me.give_me_moneyz.apollosarrows.entities.MagneticArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MagneticArrowRenderer extends ArrowRenderer<MagneticArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ApollosArrows.MODID,
            "textures/entity/magnetic_arrow.png");

    public MagneticArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(MagneticArrowEntity arrow) {
        return TEXTURE;
    }
}