package net.earlyalpha.aristysa.screen;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class ModScreenHandlers {
    public static final ScreenHandlerType<CraftStationScreenHandler> CRAFT_STATION_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Aristysa.MOD_ID, "craft_station"),
                    new ExtendedScreenHandlerType<>(CraftStationScreenHandler::new));
    public static final ScreenHandlerType<CyberwareScreenHandler> CYBERWARE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Aristysa.MOD_ID, "cyberware_screen"),
                    new ExtendedScreenHandlerType<>(CyberwareScreenHandler::new));

    public static void registerScreenHandlers() {
        Aristysa.LOGGER.info("Registering Screen Handlers for " + Aristysa.MOD_ID);
    }
}
