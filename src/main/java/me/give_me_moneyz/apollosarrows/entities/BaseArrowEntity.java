package me.give_me_moneyz.apollosarrows.entities;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

public abstract class BaseArrowEntity extends AbstractArrow {
    private final Supplier<Item> pickupItem;

    protected BaseArrowEntity(EntityType<? extends AbstractArrow> entityType, Level world, Supplier<Item> pickupItem) {
        super(entityType, world);
        this.pickupItem = pickupItem;
    }

    protected BaseArrowEntity(EntityType<? extends AbstractArrow> entityType, double x, double y, double z, Level world, Supplier<Item> pickupItem) {
        super(entityType, x, y, z, world);
        this.pickupItem = pickupItem;
    }

    protected BaseArrowEntity(EntityType<? extends AbstractArrow> entityType, LivingEntity shooter, Level world, Supplier<Item> pickupItem) {
        super(entityType, shooter, world);
        this.pickupItem = pickupItem;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(pickupItem.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
