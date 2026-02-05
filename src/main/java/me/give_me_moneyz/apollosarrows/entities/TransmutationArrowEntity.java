package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

import java.util.LinkedList;
import java.util.Queue;

public class TransmutationArrowEntity extends BaseArrowEntity {
    private BlockPos blockPos;

    public TransmutationArrowEntity(EntityType<TransmutationArrowEntity> entityType, Level world) {
        super(entityType, world, ModItems.TRANSMUTATION_ARROW);
    }

    public TransmutationArrowEntity(EntityType<TransmutationArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world, ModItems.TRANSMUTATION_ARROW);
    }

    public TransmutationArrowEntity(EntityType<TransmutationArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world, ModItems.TRANSMUTATION_ARROW);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        blockPos = pResult.getBlockPos();
    }

    @Override
    protected void tickDespawn() {
        super.tickDespawn();
        if (this.inGroundTime > 40) {
            var itemInHand = ((LivingEntity) getOwner()).getOffhandItem();
            var blockHandItem = Block.byItem(itemInHand.getItem());
            if (blockHandItem != Blocks.AIR) {
                breadthFirstSearch(blockPos, itemInHand);
            }
        }
    }

    public void breadthFirstSearch(BlockPos start, ItemStack itemStack) {
        var block = Block.byItem(itemStack.getItem());
        Queue<BlockPos> queue = new LinkedList<>();

        // Add the starting block to the queue
        queue.add(start);

        while (!queue.isEmpty() && !itemStack.isEmpty()) {
            // Get the next block to process
            BlockPos current = queue.remove();
            var changed = false;

            // Check if the block meets the conditions you are searching for and perform any necessary actions
            if (level().getBlockState(current).getBlock() != Blocks.AIR && level().getBlockState(current).getBlock() != block) {
                level().setBlockAndUpdate(current, block.defaultBlockState());
                itemStack.shrink(1);
                changed = true;
            }

            // Get the neighboring blocks
            BlockPos[] neighbors = new BlockPos[]{
                    current.north(), current.south(), current.east(), current.west(), current.above(), current.below()
            };

            // Add the unvisited neighbors to the queue
            for (BlockPos neighbor : neighbors) {
                if (!queue.contains(neighbor) && changed) {
                    queue.add(neighbor);
                }
            }
        }
        this.discard();
    }
}