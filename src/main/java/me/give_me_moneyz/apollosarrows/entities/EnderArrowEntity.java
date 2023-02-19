package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import java.util.Random;

public class EnderArrowEntity extends AbstractArrow {
    public EnderArrowEntity(EntityType<EnderArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public EnderArrowEntity(EntityType<EnderArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public EnderArrowEntity(EntityType<EnderArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.ENDER_ARROW.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        var owner = getOwner();
        if (owner != null) {
            var location = pResult.getLocation();
            owner.teleportTo(location.x, location.y, location.z);
            getServer().getLevel(level.dimension()).sendParticles(ParticleTypes.PORTAL, location.x, location.y,
                    location.z, 90, 0, 0, 0, 0.2);
            discard();
        }
        super.onHitBlock(pResult);
    }
}