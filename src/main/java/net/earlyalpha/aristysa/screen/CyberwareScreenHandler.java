package net.earlyalpha.aristysa.screen;

import net.earlyalpha.aristysa.block.entity.CraftStationBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CyberwareScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegete;
    public final CraftStationBlockEntity blockEntity;

    public CyberwareScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId ,inventory ,inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    public CyberwareScreenHandler(int syncId, PlayerInventory playerinventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.CRAFT_STATION_SCREEN_HANDLER,syncId);
        checkSize(((Inventory)blockEntity), 2);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerinventory.player);
        this.propertyDelegete = arrayPropertyDelegate;
        this.blockEntity = ((CraftStationBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0 , 40 , 24));
        this.addSlot(new Slot(inventory, 1 , 40,  42));
        this.addSlot(new Slot(inventory, 2 , 58 , 42));
        this.addSlot(new Slot(inventory, 3 , 58 , 24));
        this.addSlot(new Slot(inventory, 4 , 125 , 37));
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
