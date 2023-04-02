package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class MitosisArrowEntity extends AbstractArrow {
    public MitosisArrowEntity(EntityType<MitosisArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public MitosisArrowEntity(EntityType<MitosisArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public MitosisArrowEntity(EntityType<MitosisArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.MITOSIS_ARROW.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void tickDespawn() {
        super.tickDespawn();
        if (this.inGroundTime > 40) {
            ServerLevel serverLevel = (ServerLevel) this.level;
            Vec3 direction = this.getDeltaMovement().normalize();
            for (int i = 0; i < 4; i++) {
                double angle = Math.PI / 2 * i;
                double sinAngle = Math.sin(angle);
                double cosAngle = Math.cos(angle);
                Vec3 offset = new Vec3(direction.x * cosAngle + direction.z * sinAngle, 0,
                        -direction.x * sinAngle + direction.z * cosAngle);
                offset = offset.normalize().multiply(0.2, 0.2, 0.2).add(0, 1, 0);
                Arrow arrow = new Arrow(this.level, this.getX(), this.getY(), this.getZ());
                arrow.setDeltaMovement(offset.x, offset.y, offset.z);
                serverLevel.addFreshEntity(arrow);
            }
            this.discard();
        }
    }
}