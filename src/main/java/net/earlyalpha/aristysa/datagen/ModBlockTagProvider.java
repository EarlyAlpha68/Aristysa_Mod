package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.LEAD_BLOCK,
                        ModBlocks.RAW_LEAD_BLOCK,
                        ModBlocks.DEEPSLATE_LEAD_ORE,
                        ModBlocks.LEAD_ORE,
                        ModBlocks.FUSION_CRAFTER);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LEAD_BLOCK,
                        ModBlocks.RAW_LEAD_BLOCK,
                        ModBlocks.DEEPSLATE_LEAD_ORE,
                        ModBlocks.LEAD_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.ALUMINUM_ORE)
                .add(ModBlocks.ALUMINUM_ORE)
                .add(ModBlocks.DEEPSLATE_ALUMINUM_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.LEAD_ORE)
                .add(ModBlocks.LEAD_ORE)
                .add(ModBlocks.DEEPSLATE_LEAD_ORE);
        getOrCreateTagBuilder(ModTags.Blocks.LEAD_BLOCKS)
                .add(ModBlocks.LEAD_BLOCK);

    }
}
