package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class MagneticArrowEntity extends AbstractArrow {
    private AABB aabb;

    public MagneticArrowEntity(EntityType<MagneticArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public MagneticArrowEntity(EntityType<MagneticArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public MagneticArrowEntity(EntityType<MagneticArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.MAGNETIC_ARROW.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        aabb = AABB.ofSize(pResult.getLocation(), 10, 10, 10);
    }

    @Override
    public void tickDespawn() {
        super.tickDespawn();
        if (this.inGround) {
            if (this.inGroundTime >= 100) {
                discard();
                return;
            }
            for (Entity entity : this.level.getEntities(this, aabb)) {
                entity.setDeltaMovement(position().subtract(entity.getPosition(1)));
            }
        }
    }
}