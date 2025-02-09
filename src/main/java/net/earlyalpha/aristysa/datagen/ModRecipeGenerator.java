package net.earlyalpha.aristysa.datagen;


import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.datagen.recipe.FusionCrafterRecipeBuilder;
import net.earlyalpha.aristysa.datagen.recipe.LabotaryTrayRecipeBuilder;
import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.recipe.LabotaryTrayRecipe;
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
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LABOTARY_TRAY)
                    .pattern("SWG")
                    .pattern("OWA")
                    .pattern("III")
                    .input('S',ModItems.EMPTY_SYRINGE)
                    .input('A',ModItems.ALUMINUM_PLATE)
                    .input('O',Items.GOLD_INGOT)
                    .input('W',ModItems.WIRE)
                    .input('G',Items.GLASS_BOTTLE)
                    .input('I',Items.IRON_INGOT)
                    .criterion(hasItem(ModItems.EMPTY_SYRINGE), conditionsFromItem(ModItems.EMPTY_SYRINGE))
                    .offerTo(exporter,new Identifier(getRecipeName(ModBlocks.LABOTARY_TRAY)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMPTY_SYRINGE)
                    .pattern(" GI")
                    .pattern("G G")
                    .pattern("IG ")
                    .input('G',Blocks.GLASS)
                    .input('I',Items.IRON_INGOT)
                    .criterion(hasItem(Blocks.GLASS), conditionsFromItem(Blocks.GLASS))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.EMPTY_SYRINGE)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WARDEN_HEART_1)
                    .pattern("YIY")
                    .pattern("LSL")
                    .pattern("HCH")
                    .input('S',Items.NETHER_STAR)
                    .input('C',ModItems.MICRO_CHIP)
                    .input('H', Items.ECHO_SHARD)
                    .input('L', ModTags.Items.LEAD_PLATES)
                    .input('Y', ModItems.SYNTHETIC_MUSCLE)
                    .input('I',Items.NETHERITE_INGOT)
                    .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.WARDEN_HEART_1)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WARDEN_HEART_2)
                    .pattern("SPS")
                    .pattern("PCP")
                    .pattern("SMS")
                    .input('C',ModItems.WARDEN_HEART_1)
                    .input('S',Items.NETHER_STAR)
                    .input('P',ModItems.CONDUCTIVE_PASTE)
                    .input('M',ModItems.MICRO_CHIP)
                    .criterion(hasItem(ModItems.WARDEN_HEART_1), conditionsFromItem(ModItems.WARDEN_HEART_1))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.WARDEN_HEART_2)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WARDEN_HEART_3)
                    .pattern("NSN")
                    .pattern("SCS")
                    .pattern("NSN")
                    .input('C', ModItems.WARDEN_HEART_2)
                    .input('S',Items.NETHER_STAR)
                    .input('N', Items.NETHERITE_INGOT)
                    .criterion(hasItem(ModItems.WARDEN_HEART_2), conditionsFromItem(ModItems.WARDEN_HEART_2))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.WARDEN_HEART_3)));


            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OPTICAL_CAMO_1)
                    .pattern("GOG")
                    .pattern("OCO")
                    .pattern("GOG")
                    .input('C',ModItems.SUBDERMAL_ARMOR_1)
                    .input('O',ModItems.OPTICAL_FIBER)
                    .input('G', Blocks.GLASS)
                    .criterion(hasItem(ModItems.SUBDERMAL_ARMOR_1), conditionsFromItem(ModItems.SUBDERMAL_ARMOR_1))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.OPTICAL_CAMO_1)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OPTICAL_CAMO_2)
                    .pattern("APA")
                    .pattern("MCP")
                    .pattern("AMA")
                    .input('C',ModItems.OPTICAL_CAMO_1)
                    .input('A', ModTags.Items.ALUMINUM_PLATES)
                    .input('P',ModItems.CONDUCTIVE_PASTE)
                    .input('M',ModItems.MICRO_CHIP)
                    .criterion(hasItem(ModItems.OPTICAL_CAMO_1), conditionsFromItem(ModItems.OPTICAL_CAMO_1))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.OPTICAL_CAMO_2)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OPTICAL_CAMO_3)
                    .pattern("DND")
                    .pattern("OCN")
                    .pattern("OOD")
                    .input('C', ModItems.OPTICAL_CAMO_2)
                    .input('O',ModItems.OPTICAL_FIBER)
                    .input('D', Items.DIAMOND)
                    .input('N', Items.NETHERITE_INGOT)
                    .criterion(hasItem(ModItems.OPTICAL_CAMO_2), conditionsFromItem(ModItems.OPTICAL_CAMO_2))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.OPTICAL_CAMO_3)));


            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SUBDERMAL_ARMOR_1)
                    .pattern("PAP")
                    .pattern("ALA")
                    .pattern("PAP")
                    .input('L',ModBlocks.LEAD_BLOCK)
                    .input('A', ModTags.Items.ALUMINUM_PLATES)
                    .input('P',ModTags.Items.LEAD_PLATES)
                    .criterion(hasItem(ModBlocks.LEAD_BLOCK), conditionsFromItem(ModBlocks.LEAD_BLOCK))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.SUBDERMAL_ARMOR_1)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SUBDERMAL_ARMOR_2)
                    .pattern("ADA")
                    .pattern("DCD")
                    .pattern("ADA")
                    .input('C',ModItems.SUBDERMAL_ARMOR_1)
                    .input('A', ModTags.Items.ALUMINUM_PLATES)
                    .input('D',Items.DIAMOND)
                    .criterion(hasItem(ModItems.SUBDERMAL_ARMOR_1), conditionsFromItem(ModItems.SUBDERMAL_ARMOR_1))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.SUBDERMAL_ARMOR_2)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SUBDERMAL_ARMOR_3)
                    .pattern("DND")
                    .pattern("ACN")
                    .pattern("AAD")
                    .input('C', ModItems.SUBDERMAL_ARMOR_2)
                    .input('A',ModTags.Items.ALUMINUM_PLATES)
                    .input('D', Items.DIAMOND)
                    .input('N', Items.NETHERITE_INGOT)
                    .criterion(hasItem(ModItems.SUBDERMAL_ARMOR_2), conditionsFromItem(ModItems.SUBDERMAL_ARMOR_2))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.SUBDERMAL_ARMOR_3)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLEMARM_1)
                    .pattern("SII")
                    .pattern("WMI")
                    .pattern("SWS")
                    .input('I',Blocks.IRON_BLOCK)
                    .input('W', ModItems.WIRE)
                    .input('M',ModItems.MICRO_CHIP)
                    .input('S',ModItems.SYNTHETIC_MUSCLE)
                    .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.GOLEMARM_1)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLEMARM_2)
                    .pattern("WSI")
                    .pattern("SCS")
                    .pattern("SSW")
                    .input('C',ModItems.GOLEMARM_1)
                    .input('I',Blocks.IRON_BLOCK)
                    .input('W', ModItems.WIRE)
                    .input('S',ModItems.SYNTHETIC_MUSCLE)
                    .criterion(hasItem(ModItems.GOLEMARM_1), conditionsFromItem(ModItems.GOLEMARM_1))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.GOLEMARM_2)));
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLEMARM_3)
                    .pattern("NDD")
                    .pattern("ICD")
                    .pattern("WIN")
                    .input('C', ModItems.GOLEMARM_2)
                    .input('I',Blocks.IRON_BLOCK)
                    .input('D', Items.DIAMOND)
                    .input('N', Items.NETHERITE_INGOT)
                    .input('W',ModItems.WIRE)
                    .criterion(hasItem(ModItems.GOLEMARM_2), conditionsFromItem(ModItems.GOLEMARM_2))
                    .offerTo(exporter,new Identifier(getRecipeName(ModItems.GOLEMARM_3)));

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
            new LabotaryTrayRecipeBuilder(new ItemConvertible[]{ModItems.WITHER_COMPOUND, Items.NETHERITE_SCRAP}
                    , ModItems.CRIMSON_LACE, 1).offerTo(exporter, "crimson_lace");
            new LabotaryTrayRecipeBuilder(new ItemConvertible[]{ModItems.EMPTY_SYRINGE, Items.WITHER_SKELETON_SKULL}
                    , ModItems.WITHER_COMPOUND, 1).offerTo(exporter, "wither_compound");

        }
    }
}

