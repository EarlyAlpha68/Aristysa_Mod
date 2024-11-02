package net.earlyalpha.aristysa;

import net.earlyalpha.aristysa.event.KeyInputHandler;
import net.earlyalpha.aristysa.networking.NetworkingsMessages;
import net.earlyalpha.aristysa.screen.CraftStationScreen;
import net.earlyalpha.aristysa.screen.CyberwareGuiScreen;
import net.earlyalpha.aristysa.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;


public class AristysaClient implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
        NetworkingsMessages.registerS2CPackets();
        KeyInputHandler.register();
        KeyInputHandler.registerKeyInputs();
        HandledScreens.register(ModScreenHandlers.CRAFT_STATION_SCREEN_HANDLER, CraftStationScreen::new);
        HandledScreens.register(ModScreenHandlers.CYBERWARE_SCREEN_HANDLER, CyberwareGuiScreen::new);

    }
}
