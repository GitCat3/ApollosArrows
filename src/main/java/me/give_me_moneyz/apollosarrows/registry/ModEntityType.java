package me.give_me_moneyz.apollosarrows.registry;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.entities.ExplosiveArrowEntity;
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
}
