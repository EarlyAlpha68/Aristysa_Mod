package net.earlyalpha.aristysa.util;

import net.minecraft.nbt.NbtCompound;

public interface IEntityDataSaver {
    NbtCompound getPersistentData();
    /*((IEntityDataSaver) player).getPersistentData().getInt("Nbt_Tag") #it call the nbt tag value
     With this code you can add value do the Nbt tag
            NbtCompound nbt = ((IEntityDataSaver) player).getPersistentData()
            int i = nbt.getInt("Nbt_Tag");
            i ++;
            nbt.putInt("Nbt_Tag", i);*/
}