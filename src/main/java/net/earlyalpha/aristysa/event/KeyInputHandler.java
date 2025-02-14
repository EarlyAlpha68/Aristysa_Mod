package net.earlyalpha.aristysa.event;


import net.earlyalpha.aristysa.networking.NetworkingsMessages;
import net.earlyalpha.aristysa.screen.CyberwareGuiScreen;
import net.earlyalpha.aristysa.screen.CyberwareScreenHandler;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import org.apache.logging.log4j.message.Message;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY = "key.category.aristysa.aristysa";
    public static final String KEY_OPTICAL_CAMO_USE = "key.aristysa.optical_camo_use";
    public static final String KEY_ENDER_EYE_USE = "key.aristysa.ender_eye_use";
    public static final String KEY_CYBERLEG_USE = "key.aristysa.cyberleg_use";
    public static final String KEY_CYBER_IMPLANT_SCREEN_OPEN = "key.aristysa.cyber_implant_screen_open";

    public static KeyBinding opticalCamoUse;
    public static KeyBinding enderEyeUse;
    public static KeyBinding cyberLegUse;
    public static KeyBinding cyberImplantScreenOpen;


    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (opticalCamoUse.wasPressed()) {
                ClientPlayNetworking.send(NetworkingsMessages.OPTICAL_CAMO_USE_ID, PacketByteBufs.create());
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (enderEyeUse.wasPressed()) {
                ClientPlayNetworking.send(NetworkingsMessages.ENDER_EYE_USE_ID, PacketByteBufs.create());
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client ->{
            if (cyberLegUse.wasPressed()) {
                ClientPlayNetworking.send(NetworkingsMessages.CYBERLEG_USE_ID, PacketByteBufs.create());
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client ->{
            if (cyberImplantScreenOpen.wasPressed()) {
                if (client.player != null) {
                    ClientPlayNetworking.send(NetworkingsMessages.NBT_TAGC2S_ID_SYNC, PacketByteBufs.create());
                    client.setScreen(new CyberwareGuiScreen(new CyberwareScreenHandler
                            (0, client.player.getInventory(),client.player.getInventory(),
                                    ((IEntityDataSaver) client.player).getPersistentData()),
                            client.player.getInventory(),
                            Text.of("Cyberware Screen")));
                }
            }
        });
    }
    public static void register() {
        opticalCamoUse = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPTICAL_CAMO_USE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_0,
                KEY_CATEGORY
        ));
        enderEyeUse = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ENDER_EYE_USE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_1,
                KEY_CATEGORY
        ));
        cyberLegUse = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CYBERLEG_USE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_2,
                KEY_CATEGORY
        ));
        cyberImplantScreenOpen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CYBER_IMPLANT_SCREEN_OPEN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_3,
                KEY_CATEGORY
        ));
    }
}
