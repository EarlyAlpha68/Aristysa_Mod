package net.earlyalpha.aristysa.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.management.GarbageCollectionNotificationInfo;
import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CraftStationScreen extends HandledScreen<CraftStationScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Aristysa.MOD_ID, "textures/gui/craft_station_gui.png");

    public CraftStationScreen(CraftStationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height -backgroundHeight) /2;
        context.drawTexture(TEXTURE, x, y,0,0,backgroundWidth,backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
