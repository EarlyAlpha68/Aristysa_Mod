package net.earlyalpha.aristysa.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LabotaryTrayScreen extends HandledScreen<LabotaryTrayScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(Aristysa.MOD_ID,"textures/gui/labotary_tray_gui.png");

    public LabotaryTrayScreen(LabotaryTrayScreenHandler handler, PlayerInventory inventory, Text title) {
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
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE,x,y,0,0,backgroundWidth,backgroundHeight);

        renderProgressArrow(context,x,y);
    }
    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 83, y + 38, 176, 0,  handler.getScaledProgress(), 8);

        }
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
    }
}
