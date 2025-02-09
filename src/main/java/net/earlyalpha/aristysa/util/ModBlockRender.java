package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.block.ModBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ModBlockRender {
    public static void registerBlockRender() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LABOTARY_TRAY, RenderLayer.getCutout());
    }
}

