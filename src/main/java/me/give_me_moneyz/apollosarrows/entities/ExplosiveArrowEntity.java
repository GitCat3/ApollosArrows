package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class ExplosiveArrowEntity extends BaseArrowEntity {
    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, Level world) {
        super(entityType, world, ModItems.EXPLOSIVE_ARROW);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world, ModItems.EXPLOSIVE_ARROW);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world, ModItems.EXPLOSIVE_ARROW);
    }

    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 60) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, false,
                    Level.ExplosionInteraction.MOB);
            this.discard();
        }
    }
}