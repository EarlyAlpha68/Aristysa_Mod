package net.earlyalpha.aristysa;

import net.earlyalpha.aristysa.event.KeyInputHandler;
import net.earlyalpha.aristysa.networking.NetworkingsMessages;
import net.earlyalpha.aristysa.screen.*;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;


public class AristysaClient implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
        NetworkingsMessages.registerS2CPackets();
        KeyInputHandler.register();
        KeyInputHandler.registerKeyInputs();
        ModHandledScreens.registerHandledScreen();

    }
}
