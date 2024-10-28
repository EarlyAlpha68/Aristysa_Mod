package net.earlyalpha.aristysa.networking.packet;

import net.earlyalpha.aristysa.networking.NetworkingsMessages;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.earlyalpha.aristysa.util.ImplantUsage;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class Nbt_TagC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        ((IEntityDataSaver) player).getPersistentData().putInt(buf.readString(), buf.readInt());

    }
}
