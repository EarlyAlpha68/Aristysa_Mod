package net.earlyalpha.aristysa.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class CraftStationRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItem;

    public CraftStationRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItem){
        this.id = id;
        this.output = output;
        this.recipeItem = recipeItem;
    }


    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient){
            return false;
        }
        int i;
        for (i = 0 ; i < recipeItem.size() ; i++) {
            if (!recipeItem.get(i).test(inventory.getStack(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }
    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.recipeItem;
    }

    public static class Type implements RecipeType<CraftStationRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "craft_station";
    }
    public static class Serializer implements RecipeSerializer<CraftStationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "craft_station";

        @Override
        public CraftStationRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json,"output"));
            JsonArray ingredients = JsonHelper.getArray(json,"ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(8, Ingredient.EMPTY);
            for (int i =0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new CraftStationRecipe(id, output, inputs);
        }

        @Override
        public CraftStationRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(),Ingredient.EMPTY);
            for (int i =0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }
            ItemStack output = buf.readItemStack();
            return new CraftStationRecipe(id, output, inputs);

        }

        @Override
        public void write(PacketByteBuf buf, CraftStationRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput(null));

        }
    }

}
