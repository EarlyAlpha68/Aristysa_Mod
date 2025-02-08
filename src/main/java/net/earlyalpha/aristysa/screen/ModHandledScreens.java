package net.earlyalpha.aristysa.screen;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ModHandledScreens {
    public static void registerHandledScreen() {
        Aristysa.LOGGER.info("Registering Handled Screens for " + Aristysa.MOD_ID);
        HandledScreens.register(ModScreenHandlers.CRAFT_STATION_SCREEN_HANDLER, CraftStationScreen::new);
        HandledScreens.register(ModScreenHandlers.CYBERWARE_SCREEN_HANDLER, CyberwareGuiScreen::new);
        HandledScreens.register(ModScreenHandlers.FUSION_CRAFTER_SCREEN_HANDLER, FusionCrafterScreen::new);
        HandledScreens.register(ModScreenHandlers.LABOTARY_TRAY_SCREEN_HANDLER, LabotaryTrayScreen::new);
    }
}
