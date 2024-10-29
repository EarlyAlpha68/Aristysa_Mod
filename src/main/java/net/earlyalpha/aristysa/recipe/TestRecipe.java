package net.earlyalpha.aristysa.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TestRecipe implements Recipe<SimpleInventory> {
    private final Ingredient input0;
    private final Ingredient input1;
    private final Ingredient input2;
    private final Ingredient input3;
    private final ItemStack outputStack;
    private final Identifier id;

    public TestRecipe(Ingredient input0, Ingredient input1, Ingredient input2, Ingredient input3, ItemStack outputStack, Identifier id){
        this.input0 = input0;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input2;
        this.outputStack = outputStack;
        this.id = id;

    }
    public Ingredient getInput0(){
        return this.input0;
    }
    public Ingredient getInput1(){
        return this.input1;
    }
    public Ingredient getInput2(){
        return this.input2;
    }
    public Ingredient getInput3(){
        return this.input3;
    }
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (inventory.size() < 4) return false;
        return input0.test(inventory.getStack(0)) && input1.test(inventory.getStack(1)) && input2.test(inventory.getStack(2)) && input3.test(inventory.getStack(3));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return this.getOutput(registryManager).copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.outputStack;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<TestRecipe>{
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "test_recipe";
    }
}
