package net.earlyalpha.aristysa.screen;

import net.earlyalpha.aristysa.block.entity.CraftStationBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class CraftStationScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegete;
    public final CraftStationBlockEntity blockEntity;

    public CraftStationScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId ,inventory ,inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    private void addPlayerInventory(PlayerInventory playerInventory){
        for(int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l){
                this.addSlot(new Slot(playerInventory ,l + i * 9 + 9,8 + l* 18, 84 + i *18));
            }
        }
    }
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory,i, 8 + i * 18,142));
        }
    }
    public CraftStationScreenHandler(int syncId, PlayerInventory playerinventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.CRAFT_STATION_SCREEN_HANDLER,syncId);
        checkSize(((Inventory)blockEntity), 2);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerinventory.player);
        this.propertyDelegete = arrayPropertyDelegate;
        this.blockEntity = ((CraftStationBlockEntity) blockEntity);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(inventory, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }
        this.addSlot(new Slot(inventory,9,124,35));
        addPlayerInventory(playerinventory);
        addPlayerHotbar(playerinventory);
        addProperties(propertyDelegete);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

}
