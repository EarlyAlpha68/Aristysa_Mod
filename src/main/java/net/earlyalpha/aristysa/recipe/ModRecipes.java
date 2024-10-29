package net.earlyalpha.aristysa.recipe;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static RecipeType<TestRecipe> TEST_RECIPE;
    public static RecipeSerializer<TestRecipe> TEST_RECIPE_SERIALIZER;

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, TestRecipeSerializer.ID,
                TestRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Aristysa.MOD_ID, TestRecipe.Type.ID), TestRecipe.Type.INSTANCE);
    }
}
