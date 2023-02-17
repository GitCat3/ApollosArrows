package me.give_me_moneyz.apollosarrows.world.entity.projectile;

import me.give_me_moneyz.apollosarrows.registry.ModItems;
import me.give_me_moneyz.apollosarrows.world.entity.ModEntityType;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class BombArrow extends AbstractArrow {
    private final Item referenceItem;

    public BombArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.referenceItem = ModItems.BOMB_ARROW.get();
    }

    public BombArrow(LivingEntity pShooter, Level pLevel, Item referenceItem) {
        super(ModEntityType.BOMB_ARROW.get(), pShooter, pLevel);
        this.referenceItem = referenceItem;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 30, Level.ExplosionInteraction.MOB);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
