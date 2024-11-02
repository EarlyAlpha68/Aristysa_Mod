package net.earlyalpha.aristysa.screen;

import net.earlyalpha.aristysa.block.entity.CraftStationBlockEntity;
import net.earlyalpha.aristysa.screen.slot.OnlyReadImplantSlot;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class CyberwareScreenHandler extends ScreenHandler {


    public CyberwareScreenHandler(int syncId, Inventory inventory, PlayerEntity player){
        super(ModScreenHandlers.CYBERWARE_SCREEN_HANDLER,syncId);

        this.addSlot(new OnlyReadImplantSlot(inventory,0,120,35,"cyberLegTier",player));
        this.addSlot(new OnlyReadImplantSlot(inventory,1,100,35,"enderEyeTier",player));
        this.addSlot(new OnlyReadImplantSlot(inventory,2,80,35,"golemArmTier",player));
        this.addSlot(new OnlyReadImplantSlot(inventory,3,60,35,"opticalCamoTier",player));
        this.addSlot(new OnlyReadImplantSlot(inventory,4,40,35,"subdermalArmorTier",player));
        this.addSlot(new OnlyReadImplantSlot(inventory,5,20,35,"wardenHearthTier",player));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
