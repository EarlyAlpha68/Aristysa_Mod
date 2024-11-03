package net.earlyalpha.aristysa.networking;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.networking.packet.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class NetworkingsMessages {
    public static final Identifier OPTICAL_CAMO_USE_ID = new Identifier(Aristysa.MOD_ID, "optical_camo_use");
    public static final Identifier ENDER_EYE_USE_ID = new Identifier(Aristysa.MOD_ID, "ender_eye_use");
    public static final Identifier NBT_TAGS2C_ID_SYNC = new Identifier(Aristysa.MOD_ID, "nbt_tag_id_sync_0");
    public static final Identifier NBT_TAGC2S_ID_SYNC = new Identifier(Aristysa.MOD_ID, "nbt_tag_id_sync_1");
    public static final Identifier CYBERLEG_USE_ID = new Identifier(Aristysa.MOD_ID, "cyberleg_use");



    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(OPTICAL_CAMO_USE_ID, OpticalCamoC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(ENDER_EYE_USE_ID, EnderEyeC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(CYBERLEG_USE_ID, CyberLegC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(NBT_TAGC2S_ID_SYNC, Nbt_Tag_SYNC_C2SPacket::receive);




    }
    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(NBT_TAGS2C_ID_SYNC, Nbt_Tag_SYNC_S2CPacket::receive);
    }
}
