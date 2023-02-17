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

public class MagneticArrowEntity extends AbstractArrow {
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
}