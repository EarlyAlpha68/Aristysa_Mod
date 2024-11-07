package net.earlyalpha.aristysa.item;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item OPTICAL_CAMO_1 = registerItem("optical_camo_1", new CyberwareItem(new FabricItemSettings(),1,"opticalCamoTier"));
    public static final Item OPTICAL_CAMO_2 = registerItem("optical_camo_2", new CyberwareItem(new FabricItemSettings(),2,"opticalCamoTier"));
    public static final Item OPTICAL_CAMO_3 = registerItem("optical_camo_3", new CyberwareItem(new FabricItemSettings(),3,"opticalCamoTier"));
    public static final Item SUBDERMAL_ARMOR_1 = registerItem("subdermal_armor_1", new CyberwareItem(new FabricItemSettings(),1,"subdermalArmorTier"));
    public static final Item SUBDERMAL_ARMOR_2 = registerItem("subdermal_armor_2", new CyberwareItem(new FabricItemSettings(),2,"subdermalArmorTier"));
    public static final Item SUBDERMAL_ARMOR_3 = registerItem("subdermal_armor_3", new CyberwareItem(new FabricItemSettings(),3,"subdermalArmorTier"));
    public static final Item GOLEMARM_1 = registerItem("golemarm_1", new CyberwareItem(new FabricItemSettings(),1,"golemArmTier"));
    public static final Item GOLEMARM_2 = registerItem("golemarm_2", new CyberwareItem(new FabricItemSettings(),2,"golemArmTier"));
    public static final Item GOLEMARM_3 = registerItem("golemarm_3", new CyberwareItem(new FabricItemSettings(),3,"golemArmTier"));
    public static final Item ENDEREYE_1 = registerItem("endereye_1", new CyberwareItem(new FabricItemSettings(),1,"enderEyeTier"));
    public static final Item ENDEREYE_2 = registerItem("endereye_2", new CyberwareItem(new FabricItemSettings(),2,"enderEyeTier"));
    public static final Item ENDEREYE_3 = registerItem("endereye_3", new CyberwareItem(new FabricItemSettings(),3,"enderEyeTier"));
    public static final Item CYBERLEG_1 = registerItem("cyberleg_1", new CyberwareItem(new FabricItemSettings(),1,"cyberLegTier"));
    public static final Item CYBERLEG_2 = registerItem("cyberleg_2", new CyberwareItem(new FabricItemSettings(),2,"cyberLegTier"));
    public static final Item CYBERLEG_3 = registerItem("cyberleg_3", new CyberwareItem(new FabricItemSettings(),3,"cyberLegTier"));
    public static final Item WARDEN_HEARTH_1 = registerItem("wardenhearth_1", new CyberwareItem(new FabricItemSettings(),1,"wardenHearthTier"));
    public static final Item WARDEN_HEARTH_2 = registerItem("wardenhearth_2", new CyberwareItem(new FabricItemSettings(),2,"wardenHearthTier"));
    public static final Item WARDEN_HEARTH_3 = registerItem("wardenhearth_3", new CyberwareItem(new FabricItemSettings(),3,"wardenHearthTier"));
    public static final Item MICRO_CHIP = registerItem("micro_chip", new Item(new FabricItemSettings()));
    public static final Item SYNTHETIC_MUSCLE = registerItem("synthetic_muscle", new Item(new FabricItemSettings()));
    public static final Item RAW_LEAD = registerItem("raw_lead", new Item(new FabricItemSettings()));
    public static final Item LEAD_INGOT = registerItem("lead_ingot", new Item(new FabricItemSettings()));
    public static final Item LEAD_PLATE = registerItem("lead_plate", new Item(new FabricItemSettings()));
    public static final Item CRIMSON_LACE = registerItem("crimson_lace", new CrimsonLaceItem(new FabricItemSettings()));


    private static Item registerItem(String name,Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Aristysa.MOD_ID, name), item);
    }
    public static void registeredModItems() {
    Aristysa.LOGGER.info("Registering Mod Item for " + Aristysa.MOD_ID);
    }
}
