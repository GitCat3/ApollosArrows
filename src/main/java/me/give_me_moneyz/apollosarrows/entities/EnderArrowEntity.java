package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class EnderArrowEntity extends BaseArrowEntity {
    public EnderArrowEntity(EntityType<EnderArrowEntity> entityType, Level world) {
        super(entityType, world, ModItems.ENDER_ARROW);
    }

    public EnderArrowEntity(EntityType<EnderArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world, ModItems.ENDER_ARROW);
    }

    public EnderArrowEntity(EntityType<EnderArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world, ModItems.ENDER_ARROW);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        var owner = getOwner();
        if (owner != null) {
            var location = pResult.getLocation();
            owner.teleportTo(location.x, location.y, location.z);
            getServer().getLevel(level().dimension()).sendParticles(ParticleTypes.PORTAL, location.x, location.y,
                    location.z, 90, 0, 0, 0, 0.2);
            discard();
        }
        super.onHitBlock(pResult);
    }
}