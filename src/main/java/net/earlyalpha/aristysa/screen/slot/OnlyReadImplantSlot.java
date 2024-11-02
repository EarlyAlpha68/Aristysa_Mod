package net.earlyalpha.aristysa.screen.slot;


import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;

import java.util.HashMap;

public class OnlyReadImplantSlot extends Slot {
    private final String implant;
    private final PlayerEntity player;

    public OnlyReadImplantSlot(Inventory inventory, int index, int x, int y, String implant , PlayerEntity player) {
        super(inventory, index, x, y);
        this.implant = implant;
        this.player = player;
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
        System.out.println("Implant Type for " + implant + ": " + implantType);
        NbtCompound nbt = ((IEntityDataSaver) player).getPersistentData();
        int implantTier = nbt.getInt(this.implant);
        System.out.println("Implant Tier for " + this.implant + ": " + implantTier);
        return switch (implantType) {
            case 0 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.CYBERLEG_1);
                case 2 -> new ItemStack(ModItems.CYBERLEG_2);
                case 3 -> new ItemStack(ModItems.CYBERLEG_3);
                default -> new ItemStack(ModItems.LEAD_INGOT);
            };
            case 1 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.ENDEREYE_1);
                case 2 -> new ItemStack(ModItems.ENDEREYE_2);
                case 3 -> new ItemStack(ModItems.ENDEREYE_3);
                default -> new ItemStack(ModItems.MICRO_CHIP);
            };
            case 2 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.GOLEMARM_1);
                case 2 -> new ItemStack(ModItems.GOLEMARM_2);
                case 3 -> new ItemStack(ModItems.GOLEMARM_3);
                default -> new ItemStack(ModItems.MICRO_CHIP);
            };
            case 3 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.OPTICAL_CAMO_1);
                case 2 -> new ItemStack(ModItems.OPTICAL_CAMO_2);
                case 3 -> new ItemStack(ModItems.OPTICAL_CAMO_3);
                default -> new ItemStack(ModItems.MICRO_CHIP);
            };
            case 4 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_1);
                case 2 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_2);
                case 3 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_3);
                default -> new ItemStack(ModItems.MICRO_CHIP);
            };
            case 5 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.WARDEN_HEARTH_1);
                case 2 -> new ItemStack(ModItems.WARDEN_HEARTH_2);
                case 3 -> new ItemStack(ModItems.WARDEN_HEARTH_3);
                default -> new ItemStack(ModItems.MICRO_CHIP);
            };
            default -> new ItemStack(ModItems.SYNTHETIC_MUSCLE);
        };

    }

    @Override
    public void setStack(ItemStack stack) {
        super.setStack(stack);
    }
}
