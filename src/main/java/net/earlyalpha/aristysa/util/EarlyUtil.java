package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

import java.util.HashMap;

public class EarlyUtil {
    public static boolean drinkingMilk = false;
    public static HashMap<String, Integer> implantType = new HashMap<>();
    static {
        implantType.put("cyberLegTier", 0);
        implantType.put("enderEyeTier", 1);
        implantType.put("golemArmTier", 2);
        implantType.put("opticalCamoTier", 3);
        implantType.put("subdermalArmorTier", 4);
        implantType.put("wardenHearthTier", 5);
        //Init the implant in their specific order
    }
    public static int getImplantType(String nbtTag) {
        return implantType.getOrDefault(nbtTag, -1);
        //give the attached number of order of a specific implant
    }
    public static int getImplantTier(PlayerEntity player ,String key) {
        return ((IEntityDataSaver) player).getPersistentData().getInt(key);
        // Allow to get the implant tier from anywhere
    }
    public static void addTier(IEntityDataSaver player, int tierAdd, String nbt_data) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt(nbt_data, tierAdd);
    }
    public static ItemStack CyberwareItemstack(int implantType, int implantTier){
        return switch (implantType) {
            case 0 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.CYBERLEG_1);
                case 2 -> new ItemStack(ModItems.CYBERLEG_2);
                case 3 -> new ItemStack(ModItems.CYBERLEG_3);
                default -> ItemStack.EMPTY;
            };
            case 1 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.ENDEREYE_1);
                case 2 -> new ItemStack(ModItems.ENDEREYE_2);
                case 3 -> new ItemStack(ModItems.ENDEREYE_3);
                default -> ItemStack.EMPTY;
            };
            case 2 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.GOLEMARM_1);
                case 2 -> new ItemStack(ModItems.GOLEMARM_2);
                case 3 -> new ItemStack(ModItems.GOLEMARM_3);
                default -> ItemStack.EMPTY;
            };
            case 3 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.OPTICAL_CAMO_1);
                case 2 -> new ItemStack(ModItems.OPTICAL_CAMO_2);
                case 3 -> new ItemStack(ModItems.OPTICAL_CAMO_3);
                default -> ItemStack.EMPTY;
            };
            case 4 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_1);
                case 2 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_2);
                case 3 -> new ItemStack(ModItems.SUBDERMAL_ARMOR_3);
                default -> ItemStack.EMPTY;
            };
            case 5 -> switch (implantTier) {
                case 1 -> new ItemStack(ModItems.WARDEN_HEARTH_1);
                case 2 -> new ItemStack(ModItems.WARDEN_HEARTH_2);
                case 3 -> new ItemStack(ModItems.WARDEN_HEARTH_3);
                default -> ItemStack.EMPTY;
            };
            default -> ItemStack.EMPTY;
        };
    }
}
