package me.give_me_moneyz.apollosarrows.world.entity;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.world.entity.projectile.BombArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            ApollosArrows.MODID);

    public static final RegistryObject<EntityType<BombArrow>> BOMB_ARROW = ENTITIES.register("bomb_arrow",
            () -> EntityType.Builder.<BombArrow>of(BombArrow::new, MobCategory.MISC).sized(0.5F,
                    0.5F).clientTrackingRange(4).updateInterval(20).build(
                    new ResourceLocation(ApollosArrows.MODID, "bomb_arrow").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
