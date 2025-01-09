package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.earlyalpha.aristysa.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.item.Items;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;

public class ModRegistries {
    public static void registerModUtil() {
        registerCustomBrewRecipe();
    }

    private static void registerCustomBrewRecipe() {
        FabricBrewingRecipeRegistry.registerItemRecipe(ModItems.EMPTY_SYRINGE, Ingredient.ofItems(),ModItems.WITHER_COMPOUND);

    }
}
