package net.earlyalpha.aristysa.util;

import net.minecraft.entity.player.PlayerEntity;
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
}
