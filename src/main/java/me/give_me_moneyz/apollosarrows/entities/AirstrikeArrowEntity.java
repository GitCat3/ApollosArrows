package me.give_me_moneyz.apollosarrows.entities;

import me.give_me_moneyz.apollosarrows.registry.ModEntityType;
import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Random;

public class AirstrikeArrowEntity extends BaseArrowEntity {
    public AirstrikeArrowEntity(EntityType<AirstrikeArrowEntity> entityType, Level world) {
        super(entityType, world, ModItems.AIRSTRIKE_ARROW);
    }

    public AirstrikeArrowEntity(EntityType<AirstrikeArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world, ModItems.AIRSTRIKE_ARROW);
    }

    public AirstrikeArrowEntity(EntityType<AirstrikeArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world, ModItems.AIRSTRIKE_ARROW);
    }

    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 80) {
            var aabb = AABB.ofSize(this.position(), 10, 1, 10);
            BlockPos.betweenClosedStream(aabb).forEach(pos -> {
                var newpos = pos.above(40);
                var explosiveentity = ModEntityType.EXPLOSIVE_ARROW.get();
                var incendiaryentity = ModEntityType.INCENDIARY_ARROW.get();
                var random = new Random();
                var randomInt = random.nextInt(6);
                if(randomInt < 5) {
                    explosiveentity.spawn(level().getServer().getLevel(level().dimension()), newpos, MobSpawnType.MOB_SUMMONED);
                }
                else {
                    incendiaryentity.spawn(level().getServer().getLevel(level().dimension()), newpos, MobSpawnType.MOB_SUMMONED);
                }
            });
            discard();
        }
    }
}