package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class IncendiaryArrowEntity extends BaseArrowEntity {
    public IncendiaryArrowEntity(EntityType<IncendiaryArrowEntity> entityType, Level world) {
        super(entityType, world, ModItems.INCENDIARY_ARROW);
    }

    public IncendiaryArrowEntity(EntityType<IncendiaryArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world, ModItems.INCENDIARY_ARROW);
    }

    public IncendiaryArrowEntity(EntityType<IncendiaryArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world, ModItems.INCENDIARY_ARROW);
    }

    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 60) {
            var aabb = AABB.ofSize(this.getPosition(1), 10, 10, 10);
            var aabbmin = new BlockPos((int) aabb.minX, (int) aabb.minY, (int) aabb.minZ);
            var aabbmax = new BlockPos((int) aabb.maxX, (int) aabb.maxY, (int) aabb.maxZ);
            for(BlockPos blockPos: BlockPos.betweenClosed(aabbmin, aabbmax)) {
                if(this.level().getBlockState(blockPos).isAir()) {
                    this.level().setBlockAndUpdate(blockPos, Blocks.FIRE.defaultBlockState());
                }
            }
            this.discard();

        }
    }
}