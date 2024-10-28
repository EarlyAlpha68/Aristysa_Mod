package net.earlyalpha.aristysa.util;

import net.minecraft.nbt.NbtCompound;

public class AddTier {
    public static void addTier(IEntityDataSaver player, int tierAdd, String nbt_data) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt(nbt_data, tierAdd);
    }
}
