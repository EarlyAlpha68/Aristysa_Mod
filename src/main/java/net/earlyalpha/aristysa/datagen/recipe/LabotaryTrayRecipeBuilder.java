package net.earlyalpha.aristysa.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.recipe.FusionCrafterRecipe;
import net.earlyalpha.aristysa.recipe.LabotaryTrayRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class LabotaryTrayRecipeBuilder implements CraftingRecipeJsonBuilder {
    private final Item result;
    private final Ingredient[] ingredients;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.create();

    public LabotaryTrayRecipeBuilder(ItemConvertible[] ingredients, ItemConvertible result, int count) {
        this.ingredients = new Ingredient[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            this.ingredients[i] = Ingredient.ofItems(ingredients[i]);
        }
        this.result = result.asItem();
        this.count = count;
    }

    @Override
    public CraftingRecipeJsonBuilder criterion(String name, CriterionConditions conditions) {
        this.advancement.criterion(name, conditions);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return result;
    }

    @Override
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        this.advancement.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId));

        exporter.accept(new JsonBuilder(recipeId, this.result, this.count, this.ingredients,
                this.advancement, new Identifier(recipeId.getNamespace(), "recipes/"
                + recipeId.getPath())));
    }

    public static class JsonBuilder implements RecipeJsonProvider {
        private final Identifier id;
        private final Item result;
        private final Ingredient[] ingredients;
        private final int count;
        private final Advancement.Builder advancement;
        private final Identifier advancementId;

        public JsonBuilder(Identifier id, Item result, int count, Ingredient[] ingredients,
                           Advancement.Builder advancement, Identifier advancementId) {
            this.id = id;
            this.result = result;
            this.ingredients = ingredients;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serialize(JsonObject json) {
            JsonArray jsonarray = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                jsonarray.add(ingredient.toJson());
            }

            json.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registries.ITEM.getId(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            json.add("output", jsonobject);
        }

        @Override
        public Identifier getRecipeId() {
            return new Identifier(Aristysa.MOD_ID,
                    Registries.ITEM.getId(this.result).getPath() + "_from_labotary_tray");
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return LabotaryTrayRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject toAdvancementJson() {
            return this.advancement.toJson();
        }

        @Nullable
        @Override
        public Identifier getAdvancementId() {
            return this.advancementId;
        }
    }
}
