package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class IncendiaryArrowEntity extends AbstractArrow {

    private BlockPos blockPos;
    public IncendiaryArrowEntity(EntityType<IncendiaryArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public IncendiaryArrowEntity(EntityType<IncendiaryArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public IncendiaryArrowEntity(EntityType<IncendiaryArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.INCENDIARY_ARROW.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        blockPos = pResult.getBlockPos();
    }

    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 60) {
            var aabb = AABB.ofSize(this.getPosition(1), 10, 10, 10);
            var aabbmin = new BlockPos(aabb.minX, aabb.minY, aabb.minZ);
            var aabbmax = new BlockPos(aabb.maxX, aabb.maxY, aabb.maxZ);
            for(BlockPos blockPos: BlockPos.betweenClosed(aabbmin, aabbmax)) {
                if(this.level.getBlockState(blockPos).isAir()) {
                    this.level.setBlockAndUpdate(blockPos, Blocks.FIRE.defaultBlockState());
                }
            }

        }
    }
}