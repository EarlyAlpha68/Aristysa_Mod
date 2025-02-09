package net.earlyalpha.aristysa.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class LabotaryTrayCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE =
            new Identifier(Aristysa.MOD_ID, "textures/gui/labotary_tray_gui_recipe_showcase.png");
    public static CategoryIdentifier<LabotaryTrayDisplay> LABOTARY_TRAY_DISPLAY =
            CategoryIdentifier.of(Aristysa.MOD_ID, "labotary_tray");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return LABOTARY_TRAY_DISPLAY;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Labotary Tray");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.LABOTARY_TRAY.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startpoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE,
                new Rectangle(startpoint.x,startpoint.y,176,120)));

        widgets.add(Widgets.createSlot(new Point(startpoint.x + 42 ,startpoint.y + 35))
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startpoint.x + 64 ,startpoint.y + 35))
                .entries(display.getInputEntries().get(1)));
        widgets.add(Widgets.createSlot(new Point(startpoint.x + 118 ,startpoint.y + 35))
                .markOutput().entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 120;
    }
}
