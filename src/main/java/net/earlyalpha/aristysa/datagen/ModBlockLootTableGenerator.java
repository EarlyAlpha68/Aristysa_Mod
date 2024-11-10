package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.LEAD_BLOCK);
        addDrop(ModBlocks.RAW_LEAD_BLOCK);
        addDrop(ModBlocks.FUSION_CRAFTER);

        addDrop(ModBlocks.DEEPSLATE_LEAD_ORE,oreDrops(ModBlocks.DEEPSLATE_LEAD_ORE, ModItems.RAW_LEAD));
        addDrop(ModBlocks.LEAD_ORE,oreDrops(ModBlocks.LEAD_ORE,ModItems.RAW_LEAD));
        addDrop(ModBlocks.DEEPSLATE_ALUMINUM_ORE,oreDrops(ModBlocks.DEEPSLATE_ALUMINUM_ORE, ModItems.RAW_ALUMINUM));
        addDrop(ModBlocks.ALUMINUM_ORE,oreDrops(ModBlocks.ALUMINUM_ORE,ModItems.RAW_ALUMINUM));

    }
}
