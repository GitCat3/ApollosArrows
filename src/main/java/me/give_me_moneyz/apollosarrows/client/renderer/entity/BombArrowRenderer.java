package me.give_me_moneyz.apollosarrows.client.renderer.entity;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.world.entity.projectile.BombArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BombArrowRenderer extends ArrowRenderer<BombArrow> {
    public BombArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BombArrow pEntity) {
        return new ResourceLocation(ApollosArrows.MODID, "textures/entity/projectiles/bomb_arrow.png");
    }
}
