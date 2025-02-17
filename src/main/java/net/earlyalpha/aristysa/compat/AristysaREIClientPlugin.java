package net.earlyalpha.aristysa.compat;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.math.Rectangle;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.recipe.FusionCrafterRecipe;
import net.earlyalpha.aristysa.recipe.LabotaryTrayRecipe;
import net.earlyalpha.aristysa.screen.FusionCrafterScreen;

import net.earlyalpha.aristysa.screen.LabotaryTrayScreen;

public class AristysaREIClientPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new FusionCrafterCategory());

        registry.addWorkstations(FusionCrafterCategory.FUSION_CRAFTER_DISPLAY, EntryStacks.of(ModBlocks.FUSION_CRAFTER));

        registry.add(new LabotaryTrayCategory());

        registry.addWorkstations(LabotaryTrayCategory.LABOTARY_TRAY_DISPLAY, EntryStacks.of(ModBlocks.LABOTARY_TRAY));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(FusionCrafterRecipe.class, FusionCrafterRecipe.Type.INSTANCE,
                FusionCrafterDisplay::new);

        registry.registerRecipeFiller(LabotaryTrayRecipe.class, LabotaryTrayRecipe.Type.INSTANCE,
                LabotaryTrayDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(75,30,20,30), FusionCrafterScreen.class,
                FusionCrafterCategory.FUSION_CRAFTER_DISPLAY);

        registry.registerClickArea(screen -> new Rectangle(75,30,20,30), LabotaryTrayScreen.class,
                LabotaryTrayCategory.LABOTARY_TRAY_DISPLAY);
    }
}
