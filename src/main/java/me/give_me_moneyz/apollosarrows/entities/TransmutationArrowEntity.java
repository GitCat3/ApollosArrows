package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkHooks;

public class TransmutationArrowEntity extends AbstractArrow {
    public TransmutationArrowEntity(EntityType<TransmutationArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public TransmutationArrowEntity(EntityType<TransmutationArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public TransmutationArrowEntity(EntityType<TransmutationArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.TRANSMUTATION_ARROW.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void tickDespawn() {
        if(this.inGroundTime > 40) {
            var itemInHand = ((LivingEntity) getOwner()).getOffhandItem();
            var amount = itemInHand.getCount();
            if(amount != 0) {
                itemInHand.setCount(amount - 1);
            }
            else {
                discard();
            }
        }
    }
}