package net.earlyalpha.aristysa.recipe;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static RecipeType<CradtStationRecipe> TEST_RECIPE;
    public static RecipeSerializer<CradtStationRecipe> TEST_RECIPE_SERIALIZER;

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, CraftStationRecipeSerializer.ID,
                CraftStationRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Aristysa.MOD_ID, CradtStationRecipe.Type.ID), CradtStationRecipe.Type.INSTANCE);
    }
}
