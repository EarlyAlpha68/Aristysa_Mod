package net.earlyalpha.aristysa.screen.slot;



import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;


public class OnlyReadImplantSlot extends Slot {
    private final NbtCompound nbt;
    private final String implant;

    public OnlyReadImplantSlot(Inventory inventory, int index, int x, int y, NbtCompound nbt, String implant) {
        super(inventory, index, x, y);
        this.nbt = nbt;
        this.implant = implant;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canTakeItems(PlayerEntity playerEntity) {
        return false;
    }

    @Override
    public ItemStack getStack() {
        int implantType = EarlyUtil.getImplantType(this.implant);
        int implantTier = nbt.getInt(this.implant);
        return  EarlyUtil.CyberwareItemstack(implantType,implantTier);
    }

    @Override
    public void setStack(ItemStack stack) {
        super.setStack(stack);
    }
}
