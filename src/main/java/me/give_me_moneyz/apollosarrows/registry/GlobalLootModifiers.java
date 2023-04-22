package me.give_me_moneyz.apollosarrows.registry;

import com.mojang.serialization.Codec;
import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.gameplay.loot.TransmutationArrowLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GlobalLootModifiers {
    public static DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(
            ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
            ApollosArrows.MODID);
    public static final RegistryObject<Codec<TransmutationArrowLootModifier>> DUNGEON_LOOT = GLM.register(
            "transmutation_arrow_loot_modifier",
            TransmutationArrowLootModifier.CODEC);
}
