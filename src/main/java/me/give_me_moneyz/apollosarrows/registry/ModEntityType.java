package me.give_me_moneyz.apollosarrows.registry;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.entities.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            ApollosArrows.MODID);

    public static final RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW = ENTITY_TYPES.register(
            "torch_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<ExplosiveArrowEntity>) ExplosiveArrowEntity::new,
                            MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("torch_arrow"));
    public static final RegistryObject<EntityType<MagneticArrowEntity>> MAGNETIC_ARROW = ENTITY_TYPES.register(
            "magnet_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<MagneticArrowEntity>) MagneticArrowEntity::new,
                            MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("magnet_arrow"));
    public static final RegistryObject<EntityType<EnderArrowEntity>> ENDER_ARROW = ENTITY_TYPES.register(
            "teleport_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<EnderArrowEntity>) EnderArrowEntity::new,
                            MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("teleport_arrow"));
    public static final RegistryObject<EntityType<AirstrikeArrowEntity>> AIRSTRIKE_ARROW = ENTITY_TYPES.register(
            "strike_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<AirstrikeArrowEntity>) AirstrikeArrowEntity::new,
                            MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("strike_arrow"));
    public static final RegistryObject<EntityType<TransmutationArrowEntity>> TRANSMUTATION_ARROW = ENTITY_TYPES.register(
            "change_arrow", () -> EntityType.Builder.of(
                    (EntityType.EntityFactory<TransmutationArrowEntity>) TransmutationArrowEntity::new,
                    MobCategory.MISC).sized(0.5F, 0.5F).build("change_arrow"));
}
