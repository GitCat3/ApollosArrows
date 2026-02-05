package me.give_me_moneyz.apollosarrows.recipe;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ApollosArrows.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, ApollosArrows.MODID);

    public static final RegistryObject<RecipeSerializer<FletchingRecipe>> FLETCHING_SERIALIZER =
            SERIALIZERS.register("fletching", FletchingRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<FletchingRecipe>> FLETCHING_TYPE =
            TYPES.register("fletching", () -> new RecipeType<FletchingRecipe>() {
                @Override
                public String toString() {
                    return "fletching";
                }
            });
}
