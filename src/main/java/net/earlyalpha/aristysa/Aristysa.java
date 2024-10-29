package net.earlyalpha.aristysa;

import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.block.entity.ModBlockEntities;
import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.event.PlayerDeathHandler;
import net.earlyalpha.aristysa.item.ModItemGroups;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.networking.NetworkingsMessages;
import net.earlyalpha.aristysa.networking.packet.Nbt_TagC2SPacket;
import net.earlyalpha.aristysa.networking.packet.Nbt_Tag_S2CPacket;
import net.earlyalpha.aristysa.recipe.ModRecipes;
import net.earlyalpha.aristysa.screen.ModScreenHandlers;
import net.earlyalpha.aristysa.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Aristysa implements ModInitializer {
	public static final String MOD_ID = "aristysa";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Identifier INITIAL_SYNC = new Identifier(MOD_ID, "initial_sync");
	@Override
	public void onInitialize() {



		ModItemGroups.registerItemGroups();
		ModItems.registeredModItems();
		ModBlocks.registerModBlocks();
		NetworkingsMessages.registerC2SPackets();
		ModEffects.registerEffects();
		PlayerDeathHandler.register();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModWorldGeneration.generateModWorldGen();
		ModRecipes.registerRecipes();
	}
}