package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LEAD_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LEAD_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_LEAD_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_LEAD_BLOCK);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.LEAD_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CYBERLEG_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.CYBERLEG_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.CYBERLEG_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDEREYE_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDEREYE_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDEREYE_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.OPTICAL_CAMO_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.OPTICAL_CAMO_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.OPTICAL_CAMO_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARDEN_HEARTH_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARDEN_HEARTH_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARDEN_HEARTH_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUBDERMAL_ARMOR_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUBDERMAL_ARMOR_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUBDERMAL_ARMOR_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLEMARM_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLEMARM_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLEMARM_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_LACE, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEAD_PLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MICRO_CHIP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SYNTHETIC_MUSCLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_LEAD, Models.GENERATED);
    }
}
