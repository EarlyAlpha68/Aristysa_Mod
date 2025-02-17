package net.earlyalpha.aristysa.networking.packet;


import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public class Nbt_Tag_SYNC_S2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        NbtCompound nbtData = buf.readNbt();
        if (((IEntityDataSaver) client.player).getPersistentData() != null) {
            ((IEntityDataSaver) client.player).getPersistentData().copyFrom(nbtData);
        }
    }
}
