package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.registry.ModEntityType;
import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;

public class AirstrikeArrowEntity extends AbstractArrow {
    public AirstrikeArrowEntity(EntityType<AirstrikeArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public AirstrikeArrowEntity(EntityType<AirstrikeArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public AirstrikeArrowEntity(EntityType<AirstrikeArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.AIRSTRIKE_ARROW.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 80) {
            var aabb = AABB.ofSize(this.position(), 10, 1, 10);
            BlockPos.betweenClosedStream(aabb).forEach(pos -> {
                var newpos = pos.above(40);
                var entity = ModEntityType.EXPLOSIVE_ARROW.get();
                entity.spawn(level.getServer().getLevel(level.dimension()), newpos, MobSpawnType.MOB_SUMMONED);
                ApollosArrows.LOGGER.debug("entity summoned");
            });
            discard();
        }
    }
}