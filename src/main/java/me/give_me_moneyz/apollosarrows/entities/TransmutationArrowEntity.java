package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class TransmutationArrowEntity extends AbstractArrow {
    private BlockPos blockPos;
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
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        blockPos = pResult.getBlockPos();
    }

    @Override
    protected void tickDespawn() {
        super.tickDespawn();
        if(this.inGroundTime > 40) {
            var itemInHand = ((LivingEntity) getOwner()).getOffhandItem();
            var blockHandItem = Block.byItem(itemInHand.getItem());
            if(blockHandItem != Blocks.AIR) {
                transformBlocks(blockPos, itemInHand);
            }
        }
    }

    private void transformBlocks(BlockPos pos, ItemStack item) {
        if(item.getCount() > 0) {
            var blockHandItem = Block.byItem(item.getItem());
            if (!level.getBlockState(pos).is(blockHandItem)) {
                level.setBlockAndUpdate(pos, blockHandItem.defaultBlockState());
                item.shrink(1);
                for(Direction direction: Direction.values()) {
                    var offsetPos = pos.relative(direction);
                    var blockOffsetPos = level.getBlockState(offsetPos).getBlock();
                    if(blockOffsetPos != Blocks.AIR) {
                        transformBlocks(offsetPos, item);
                    }
                }
            }
        }
        else {
            discard();
        }
    }
}