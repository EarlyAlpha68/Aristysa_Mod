package net.earlyalpha.aristysa.datagen;


import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.datagen.recipe.FusionCrafterRecipeBuilder;
import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.earlyalpha.aristysa.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDEREYE_1)
                    .pattern("WGG")
                    .pattern("OMG")
                    .pattern("EOW")
                    .input('G',Blocks.GLASS)
                    .input('W', ModItems.WIRE)
                    .input('M',ModItems.MICRO_CHIP)
                    .input('O',ModItems.OPTICAL_FIBER)
                    .input('E',Items.ENDER_EYE)
                    .criterion(hasItem(ModItems.OPTICAL_FIBER), conditionsFromItem(ModItems.OPTICAL_FIBER))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.ENDEREYE_1)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDEREYE_2)
                    .pattern("RRR")
                    .pattern("OCR")
                    .pattern("WOR")
                    .input('C',ModItems.ENDEREYE_1)
                    .input('O',ModItems.OPTICAL_FIBER)
                    .input('R',Items.REDSTONE)
                    .input('W',ModItems.WIRE)
                    .criterion(hasItem(ModItems.ENDEREYE_1), conditionsFromItem(ModItems.ENDEREYE_1))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.ENDEREYE_2)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDEREYE_3)
                    .pattern("NDD")
                    .pattern("OCD")
                    .pattern("WON")
                    .input('C', ModItems.ENDEREYE_2)
                    .input('O', ModItems.OPTICAL_FIBER)
                    .input('D', Items.DIAMOND)
                    .input('N', Items.NETHERITE_INGOT)
                    .input('W',ModItems.WIRE)
                    .criterion(hasItem(ModItems.ENDEREYE_2), conditionsFromItem(ModItems.ENDEREYE_2))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.ENDEREYE_3)));


            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CYBERLEG_1)
                    .pattern("AWS")
                    .pattern("WSW")
                    .pattern("SWA")
                    .input('A',ModTags.Items.ALUMINUM_PLATES)
                    .input('W', ModItems.WIRE)
                    .input('S',ModItems.SYNTHETIC_MUSCLE)
                    .criterion(hasItem(ModItems.SYNTHETIC_MUSCLE), conditionsFromItem(ModItems.SYNTHETIC_MUSCLE))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.CYBERLEG_1)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CYBERLEG_2)
                    .pattern("DSS")
                    .pattern("WCS")
                    .pattern("DWD")
                    .input('C',ModItems.CYBERLEG_1)
                    .input('S', ModItems.SYNTHETIC_MUSCLE)
                    .input('D',Items.DIAMOND)
                    .input('W',ModItems.WIRE)
                    .criterion(hasItem(ModItems.CYBERLEG_1), conditionsFromItem(ModItems.CYBERLEG_1))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.CYBERLEG_2)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CYBERLEG_3)
                    .pattern("NON")
                    .pattern("OCO")
                    .pattern("NON")
                    .input('C', ModItems.CYBERLEG_2)
                    .input('O', ModItems.OPTICAL_FIBER)
                    .input('N', Items.NETHERITE_INGOT)
                    .criterion(hasItem(ModItems.CYBERLEG_2), conditionsFromItem(ModItems.CYBERLEG_2))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.CYBERLEG_3)));


            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,ModBlocks.LEAD_BLOCK)
                    .input(ModItems.LEAD_INGOT,9)
                    .criterion(hasItem(ModItems.LEAD_INGOT), conditionsFromItem(ModItems.LEAD_INGOT))
                    .offerTo(exporter,new Identifier(getRecipeName(ModBlocks.LEAD_BLOCK)));


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
                    .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.CONDUCTIVE_PASTE)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OPTICAL_FIBER)
                    .pattern("DSG")
                    .pattern("SGS")
                    .pattern("GSD")
                    .input('S', ModItems.SHEATHE)
                    .input('G', Blocks.GLASS)
                    .input('D', Items.DIAMOND)
                    .criterion(hasItem(ModItems.SHEATHE), conditionsFromItem(ModItems.SHEATHE))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.OPTICAL_FIBER)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WIRE)
                    .pattern(" SG")
                    .pattern("SGS")
                    .pattern("GS ")
                    .input('S', ModItems.SHEATHE)
                    .input('G', Items.COPPER_INGOT)
                    .criterion(hasItem(ModItems.SHEATHE), conditionsFromItem(ModItems.SHEATHE))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.WIRE)));

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
                    .input('S',Items.COPPER_INGOT)
                    .input('Q', ModItems.CONDUCTIVE_PASTE)
                    .input('D',Items.SCUTE)
                    .input('E',Items.GOLD_INGOT)
                    .input('F',Blocks.LIGHTNING_ROD)
                    .input('H',Items.REPEATER)
                    .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
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
            new FusionCrafterRecipeBuilder(new ItemConvertible[]{Items.DRIED_KELP, Items.IRON_INGOT}
                    , ModItems.SHEATHE, 1).offerTo(exporter, "sheathe");

        }
    }
}

