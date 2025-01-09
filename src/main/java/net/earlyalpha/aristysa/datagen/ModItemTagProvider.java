package net.earlyalpha.aristysa.datagen;

import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.earlyalpha.aristysa.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.registry.RegistryWrapper;



import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Items.ALUMINUM_INGOTS)
                .add(ModItems.ALUMINUM_INGOT);
        getOrCreateTagBuilder(ModTags.Items.LEAD_INGOTS)
                .add(ModItems.LEAD_INGOT);
        getOrCreateTagBuilder(ModTags.Items.ALUMINUM_PLATES)
                .add(ModItems.ALUMINUM_PLATE);
        getOrCreateTagBuilder(ModTags.Items.LEAD_PLATES)
                .add(ModItems.LEAD_PLATE);
        getOrCreateTagBuilder(ModTags.Items.BREWING_STAND)
                .add(ModItems.WITHER_COMPOUND)
                .add(ModItems.EMPTY_SYRINGE)
                .add(ModItems.CRIMSON_LACE);
    }



}
