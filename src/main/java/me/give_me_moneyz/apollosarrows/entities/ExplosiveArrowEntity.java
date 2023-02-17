package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class ExplosiveArrowEntity extends AbstractArrow {
    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.EXPLOSIVE_ARROW.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 60) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, true, Level.ExplosionInteraction.MOB);
            this.discard();
        }
    }
}