package net.earlyalpha.aristysa.datagen;

import dev.architectury.platform.Mod;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.datagen.recipe.FusionCrafterRecipeBuilder;
import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.earlyalpha.aristysa.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        {


            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FUSION_CRAFTER)
                    .pattern("SQS")
                    .pattern("QDQ")
                    .pattern("SQS")
                    .input('S',ModTags.Items.LEAD_INGOTS)
                    .input('Q', Items.IRON_INGOT)
                    .input('D',ModBlocks.LEAD_BLOCK)
                    .criterion(hasItem(ModItems.LEAD_INGOT), conditionsFromItem(ModItems.LEAD_INGOT))
                    .offerTo(exporter,new Identifier(getRecipeName(ModBlocks.FUSION_CRAFTER)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CONDUCTIVE_PASTE)
                    .pattern("SQS")
                    .pattern("QDQ")
                    .pattern("SQS")
                    .input('S', Items.COAL)
                    .input('Q', Items.COPPER_INGOT)
                    .input('D', Items.REDSTONE)
                    .criterion(hasItem(ModItems.LEAD_INGOT), conditionsFromItem(ModItems.LEAD_INGOT))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.CONDUCTIVE_PASTE)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SYNTHETIC_MUSCLE)
                    .pattern("QED")
                    .pattern("ESE")
                    .pattern("DEQ")
                    .input('S',Items.BLAZE_ROD)
                    .input('Q', Items.ROTTEN_FLESH)
                    .input('D',Items.LEATHER)
                    .input('E', ModItems.ALUMINUM_INGOT)
                    .criterion(hasItem(ModItems.ALUMINUM_INGOT), conditionsFromItem(ModItems.ALUMINUM_INGOT))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.SYNTHETIC_MUSCLE)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MICRO_CHIP)
                    .pattern("SHS")
                    .pattern("DQD")
                    .pattern("EFE")
                    .input('S',Items.BLAZE_ROD)
                    .input('Q', Blocks.REDSTONE_BLOCK)
                    .input('D',Items.SCUTE)
                    .input('E',Items.GOLD_INGOT)
                    .input('F',Blocks.LIGHTNING_ROD)
                    .input('H',Items.REPEATER)
                    .criterion(hasItem(ModItems.ALUMINUM_INGOT), conditionsFromItem(ModItems.ALUMINUM_INGOT))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.MICRO_CHIP)));

            offerSmelting(exporter, List.of(ModItems.RAW_LEAD,ModBlocks.LEAD_ORE,ModBlocks.DEEPSLATE_LEAD_ORE),RecipeCategory.MISC, ModItems.LEAD_INGOT,
                    0.25F,200,"lead");
            offerBlasting(exporter, List.of(ModItems.RAW_LEAD,ModBlocks.LEAD_ORE,ModBlocks.DEEPSLATE_LEAD_ORE),RecipeCategory.MISC, ModItems.LEAD_INGOT,
                    0.25F,100,"lead");
            offerSmelting(exporter, List.of(ModBlocks.RAW_LEAD_BLOCK),RecipeCategory.MISC,ModBlocks.LEAD_BLOCK,
                    2.25F,900,"lead");
            offerSmelting(exporter, List.of(ModItems.RAW_ALUMINUM,ModBlocks.ALUMINUM_ORE,ModBlocks.DEEPSLATE_ALUMINUM_ORE),RecipeCategory.MISC, ModItems.ALUMINUM_INGOT,
                    0.25F,200,"aluminum");
            offerBlasting(exporter, List.of(ModItems.RAW_ALUMINUM,ModBlocks.ALUMINUM_ORE,ModBlocks.DEEPSLATE_ALUMINUM_ORE),RecipeCategory.MISC, ModItems.ALUMINUM_INGOT,
                    0.25F,200,"aluminum");
            new FusionCrafterRecipeBuilder(new ItemConvertible[]{ModItems.LEAD_INGOT, ModItems.LEAD_INGOT}
                    , ModItems.LEAD_PLATE, 1).offerTo(exporter, "lead_plate");
            new FusionCrafterRecipeBuilder(new ItemConvertible[]{ModItems.ALUMINUM_INGOT, ModItems.ALUMINUM_INGOT}
                    , ModItems.ALUMINUM_PLATE, 1).offerTo(exporter, "aluminum_plate");
        }
    }
}

