package net.earlyalpha.aristysa.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class TestRecipeSerializer implements RecipeSerializer<TestRecipe> {
    private TestRecipeSerializer(){
    }
    public static final TestRecipeSerializer INSTANCE = new TestRecipeSerializer();
    public static final Identifier ID = new Identifier("test:test_recipe");
    @Override
    public TestRecipe read(Identifier recipeId, JsonObject json) {
        TestRecipeJsonFormat recipeJson = new Gson().fromJson(json, TestRecipeJsonFormat.class);
        if (recipeJson.input0 == null || recipeJson.input1 == null || recipeJson.input2 == null || recipeJson.input3 == null || recipeJson.outputItem ==null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;
        Ingredient input0 = Ingredient.fromJson(recipeJson.input0);
        Ingredient input1 = Ingredient.fromJson(recipeJson.input1);
        Ingredient input2 = Ingredient.fromJson(recipeJson.input2);
        Ingredient input3 = Ingredient.fromJson(recipeJson.input3);
        Item outputItem = Registries.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                .orElseThrow(() -> new JsonSyntaxException("no such item" + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new TestRecipe(input0 ,input1, input2 ,input3 , output, recipeId);
    }

    @Override
    public TestRecipe read(Identifier recipeId, PacketByteBuf packetData) {
        Ingredient input0 = Ingredient.fromPacket(packetData);
        Ingredient input1 = Ingredient.fromPacket(packetData);
        Ingredient input2 = Ingredient.fromPacket(packetData);
        Ingredient input3 = Ingredient.fromPacket(packetData);
        ItemStack output = packetData.readItemStack();
        return new TestRecipe(input0, input1, input2, input3, output, recipeId);
    }

    @Override
    public void write(PacketByteBuf packetData, TestRecipe recipe) {
        recipe.getInput0().write(packetData);
        recipe.getInput1().write(packetData);
        recipe.getInput2().write(packetData);
        recipe.getInput3().write(packetData);
        packetData.writeItemStack(recipe.getOutput(DynamicRegistryManager.EMPTY));

    }
}
