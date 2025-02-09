package net.earlyalpha.aristysa.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.earlyalpha.aristysa.recipe.LabotaryTrayRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabotaryTrayDisplay extends BasicDisplay {
    public LabotaryTrayDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }
    public LabotaryTrayDisplay(LabotaryTrayRecipe recipe) {
        super(getInputList(recipe), List.of(EntryIngredient.of(EntryStacks.of(recipe.getOutput(null)))));
    }

    private static List<EntryIngredient> getInputList(LabotaryTrayRecipe recipe) {
        if(recipe == null) return Collections.emptyList();
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(0)));
        list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(1)));
        return list;
    }


    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return LabotaryTrayCategory.LABOTARY_TRAY_DISPLAY;
    }
}
