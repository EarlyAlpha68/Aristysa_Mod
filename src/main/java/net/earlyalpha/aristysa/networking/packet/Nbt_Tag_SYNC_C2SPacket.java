package net.earlyalpha.aristysa.networking.packet;

import io.netty.buffer.Unpooled;
import net.earlyalpha.aristysa.networking.NetworkingsMessages;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class Nbt_Tag_SYNC_C2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        NbtCompound nbtData = ((IEntityDataSaver) player).getPersistentData();
        server.execute(() -> {
            send(player, nbtData);
        });
    }
    public static void send(ServerPlayerEntity player, NbtCompound nbtData) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeNbt(nbtData);
        ServerPlayNetworking.send(player,NetworkingsMessages.NBT_TAGS2C_ID_SYNC,buf);
    }
}
