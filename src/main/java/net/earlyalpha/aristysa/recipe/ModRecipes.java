package net.earlyalpha.aristysa.recipe;

import net.earlyalpha.aristysa.Aristysa;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER,new Identifier(Aristysa.MOD_ID,CraftStationRecipe.Serializer.ID),
                CraftStationRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE,new Identifier(Aristysa.MOD_ID,CraftStationRecipe.Type.ID),
                CraftStationRecipe.Type.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER,new Identifier(Aristysa.MOD_ID,FusionCrafterRecipe.Serializer.ID),
                FusionCrafterRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE,new Identifier(Aristysa.MOD_ID,FusionCrafterRecipe.Type.ID),
                FusionCrafterRecipe.Type.INSTANCE);
    }
}
