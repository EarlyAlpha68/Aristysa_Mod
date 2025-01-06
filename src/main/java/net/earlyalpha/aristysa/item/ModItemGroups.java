package net.earlyalpha.aristysa.item;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ARISTYSA_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Aristysa.MOD_ID, "cyberware" ), FabricItemGroup.builder().displayName(Text.translatable("itemgroup.cyberware"))
                    .icon(() -> new ItemStack(ModItems.SUBDERMAL_ARMOR_3)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.LEAD_ORE);
                        entries.add(ModBlocks.DEEPSLATE_LEAD_ORE);
                        entries.add(ModItems.LEAD_INGOT);
                        entries.add(ModBlocks.LEAD_BLOCK);
                        entries.add(ModItems.RAW_LEAD);
                        entries.add(ModBlocks.ALUMINUM_ORE);
                        entries.add(ModBlocks.DEEPSLATE_ALUMINUM_ORE);
                        entries.add(ModItems.ALUMINUM_INGOT);
                        entries.add(ModItems.RAW_ALUMINUM);
                        entries.add(ModBlocks.RAW_LEAD_BLOCK);
                        entries.add(ModItems.CRIMSON_LACE);
                        entries.add(ModItems.MICRO_CHIP);
                        entries.add(ModItems.SYNTHETIC_MUSCLE);
                        entries.add(ModItems.SHEATHE);
                        entries.add(ModItems.CONDUCTIVE_PASTE);
                        entries.add(ModItems.OPTICAL_FIBER);
                        entries.add(ModItems.WIRE);
                        entries.add(ModItems.OPTICAL_CAMO_1);
                        entries.add(ModItems.OPTICAL_CAMO_2);
                        entries.add(ModItems.OPTICAL_CAMO_3);
                        entries.add(ModItems.SUBDERMAL_ARMOR_1);
                        entries.add(ModItems.SUBDERMAL_ARMOR_2);
                        entries.add(ModItems.SUBDERMAL_ARMOR_3);
                        entries.add(ModItems.GOLEMARM_1);
                        entries.add(ModItems.GOLEMARM_2);
                        entries.add(ModItems.GOLEMARM_3);
                        entries.add(ModItems.ENDEREYE_1);
                        entries.add(ModItems.ENDEREYE_2);
                        entries.add(ModItems.ENDEREYE_3);
                        entries.add(ModItems.WARDEN_HEARTH_1);
                        entries.add(ModItems.WARDEN_HEARTH_2);
                        entries.add(ModItems.WARDEN_HEARTH_3);
                        entries.add(ModItems.CYBERLEG_1);
                        entries.add(ModItems.CYBERLEG_2);
                        entries.add(ModItems.CYBERLEG_3);
                        entries.add(ModItems.LEAD_PLATE);
                        entries.add(ModItems.ALUMINUM_PLATE);

                    }).build());


    public static void registerItemGroups(){
        Aristysa.LOGGER.info("Registering Item Groups for " + Aristysa.MOD_ID);
    }
}
