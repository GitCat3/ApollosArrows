package me.give_me_moneyz.apollosarrows.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FletchingRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final NonNullList<CountedIngredient> ingredients;
    private final ItemStack result;

    public FletchingRecipe(ResourceLocation id, NonNullList<CountedIngredient> ingredients, ItemStack result) {
        this.id = id;
        this.ingredients = ingredients;
        this.result = result;
    }

    @Override
    public boolean matches(Container container, Level level) {
        if (container.getContainerSize() < 3) return false;
        for (int i = 0; i < 3; i++) {
            if (!ingredients.get(i).test(container.getItem(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.FLETCHING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.FLETCHING_TYPE.get();
    }

    public NonNullList<CountedIngredient> getIngredientsWithCounts() {
        return ingredients;
    }

    public static class CountedIngredient {
        private final Ingredient ingredient;
        private final int count;

        public CountedIngredient(Ingredient ingredient, int count) {
            this.ingredient = ingredient;
            this.count = count;
        }

        public boolean test(ItemStack stack) {
            return ingredient.test(stack) && stack.getCount() >= count;
        }

        public int getCount() {
            return count;
        }

        public Ingredient getIngredient() {
            return ingredient;
        }

        public static CountedIngredient fromJson(JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
            int count = GsonHelper.getAsInt(json, "count", 1);
            return new CountedIngredient(ingredient, count);
        }

        public void toNetwork(FriendlyByteBuf buffer) {
            ingredient.toNetwork(buffer);
            buffer.writeInt(count);
        }

        public static CountedIngredient fromNetwork(FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            int count = buffer.readInt();
            return new CountedIngredient(ingredient, count);
        }
    }

    public static class Serializer implements RecipeSerializer<FletchingRecipe> {
        @Override
        public FletchingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonArray ingredientsJson = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<CountedIngredient> ingredients = NonNullList.withSize(3, new CountedIngredient(Ingredient.EMPTY, 0));

            for (int i = 0; i < ingredientsJson.size() && i < 3; i++) {
                ingredients.set(i, CountedIngredient.fromJson(ingredientsJson.get(i).getAsJsonObject()));
            }

            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new FletchingRecipe(recipeId, ingredients, result);
        }

        @Override
        public @Nullable FletchingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            NonNullList<CountedIngredient> ingredients = NonNullList.withSize(3, new CountedIngredient(Ingredient.EMPTY, 0));
            for (int i = 0; i < 3; i++) {
                ingredients.set(i, CountedIngredient.fromNetwork(buffer));
            }
            ItemStack result = buffer.readItem();
            return new FletchingRecipe(recipeId, ingredients, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FletchingRecipe recipe) {
            for (CountedIngredient ingredient : recipe.ingredients) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.result);
        }
    }
}
