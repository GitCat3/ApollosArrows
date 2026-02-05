package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MitosisArrowEntity extends BaseArrowEntity {
    public MitosisArrowEntity(EntityType<MitosisArrowEntity> entityType, Level world) {
        super(entityType, world, ModItems.MITOSIS_ARROW);
    }

    public MitosisArrowEntity(EntityType<MitosisArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world, ModItems.MITOSIS_ARROW);
    }

    public MitosisArrowEntity(EntityType<MitosisArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world, ModItems.MITOSIS_ARROW);
    }

    @Override
    protected void tickDespawn() {
        super.tickDespawn();
        if (this.inGroundTime > 40) {
            ServerLevel serverLevel = (ServerLevel) this.level();
            Vec3 direction = this.getDeltaMovement().normalize();
            Vec3 oppositeYDirection = new Vec3(direction.x, -direction.y, direction.z);
            oppositeYDirection = new Vec3(-oppositeYDirection.x, oppositeYDirection.y, -oppositeYDirection.z);
            for (int i = 0; i < 4; i++) {
                double angle = Math.PI / 2 * i;
                double sinAngle = Math.sin(angle);
                double cosAngle = Math.cos(angle);
                Vec3 offset = new Vec3(oppositeYDirection.x * cosAngle + oppositeYDirection.z * sinAngle, 0,
                        -oppositeYDirection.x * sinAngle + oppositeYDirection.z * cosAngle);
                offset = offset.normalize().multiply(0.2, 0.2, 0.2).add(0, -getDeltaMovement().normalize().y, 0);
                Arrow arrow = new Arrow(this.level(), this.getX(), this.getY(), this.getZ());
                arrow.setDeltaMovement(offset.x, offset.y, offset.z);
                serverLevel.addFreshEntity(arrow);
            }
            this.discard();
        }
    }
}