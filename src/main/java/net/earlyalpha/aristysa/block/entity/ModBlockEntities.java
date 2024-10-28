package net.earlyalpha.aristysa.block.entity;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CraftStationBlockEntity> CRAFT_STATION_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Aristysa.MOD_ID,"craft_station_be"),
                    FabricBlockEntityTypeBuilder.create(CraftStationBlockEntity::new,
                            ModBlocks.CRAFT_STATION).build());
    public static void registerBlockEntities() {
        Aristysa.LOGGER.info("Registering Block Entities for " + Aristysa.MOD_ID);
    }
}
