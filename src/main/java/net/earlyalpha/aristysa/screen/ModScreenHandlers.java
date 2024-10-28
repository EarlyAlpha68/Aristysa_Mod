package net.earlyalpha.aristysa.screen;

import net.earlyalpha.aristysa.Aristysa;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<CraftStationScreenHandler> CRAFT_STATION_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Aristysa.MOD_ID,"craft_station"),
                    new ExtendedScreenHandlerType<>(CraftStationScreenHandler::new));

    public static void registerScreenHandlers() {
    Aristysa.LOGGER.info("Registering Screen Handler for " + Aristysa.MOD_ID);
    }

}
