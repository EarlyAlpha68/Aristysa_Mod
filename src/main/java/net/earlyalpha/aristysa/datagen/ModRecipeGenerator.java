package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.datagen.recipe.FusionCrafterRecipeBuilder;
import net.earlyalpha.aristysa.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        {
            new FusionCrafterRecipeBuilder(new ItemConvertible[]{ModItems.SYNTHETIC_MUSCLE, ModItems.SYNTHETIC_MUSCLE}
                    , ModItems.CYBERLEG_1, 1).criterion("has_iron_ingot", InventoryChangedCriterion.Conditions.items(ModItems.SYNTHETIC_MUSCLE)).offerTo(exporter, "cyberlegtier1");
        }
    }
}

