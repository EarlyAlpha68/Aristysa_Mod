package net.earlyalpha.aristysa;

import net.earlyalpha.aristysa.block.ModBlocks;
import net.earlyalpha.aristysa.block.entity.ModBlockEntities;
import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.event.PlayerDeathHandler;
import net.earlyalpha.aristysa.item.ModItemGroups;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.networking.NetworkingsMessages;
import net.earlyalpha.aristysa.recipe.ModRecipes;
import net.earlyalpha.aristysa.screen.ModScreenHandlers;

import net.earlyalpha.aristysa.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Aristysa implements ModInitializer {
	public static final String MOD_ID = "aristysa";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
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