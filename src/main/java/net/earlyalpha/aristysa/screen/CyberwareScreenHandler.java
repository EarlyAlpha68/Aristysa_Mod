package net.earlyalpha.aristysa.screen;

import net.earlyalpha.aristysa.screen.slot.OnlyReadImplantSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.*;

public class CyberwareScreenHandler extends ScreenHandler {
private final Inventory implantInventory;
private final NbtCompound nbt;



    public CyberwareScreenHandler(int syncId, PlayerInventory playerInventory, Inventory implantInventory,NbtCompound nbt){
        super(ModScreenHandlers.CYBERWARE_SCREEN_HANDLER,syncId);
        this.implantInventory = implantInventory;
        this.nbt = nbt;
        addSlot(new OnlyReadImplantSlot(implantInventory,0,19,137,nbt,"cyberLegTier"));
        addSlot(new OnlyReadImplantSlot(implantInventory,1,19,17,nbt,"enderEyeTier"));
        addSlot(new OnlyReadImplantSlot(implantInventory,2,19,57,nbt,"golemArmTier"));
        addSlot(new OnlyReadImplantSlot(implantInventory,3,141,57,nbt,"opticalCamoTier"));
        addSlot(new OnlyReadImplantSlot(implantInventory,4,141,97,nbt,"subdermalArmorTier"));
        addSlot(new OnlyReadImplantSlot(implantInventory,5,19,97,nbt,"wardenHearthTier"));
    }

    public CyberwareScreenHandler(int syncId,PlayerInventory playerInventory,PacketByteBuf buf){
        this(syncId , playerInventory ,new ImplantInventory(),new NbtCompound());
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
