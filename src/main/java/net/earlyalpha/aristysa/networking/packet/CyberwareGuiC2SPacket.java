package net.earlyalpha.aristysa.networking.packet;

import net.earlyalpha.aristysa.screen.CyberwareScreenHandler;
import net.earlyalpha.aristysa.screen.ImplantInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;


public class CyberwareGuiC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity serverPlayer, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            serverPlayer.openHandledScreen(new ExtendedScreenHandlerFactory() {
                @Override
                public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

                }

                @Override
                public Text getDisplayName() {
                    return Text.of("Cyberware Screen");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                    ImplantInventory implantInventory = new ImplantInventory();
                    return new CyberwareScreenHandler(syncId, implantInventory, serverPlayer);
                }
            });
        });
    }
}
