package net.earlyalpha.aristysa.util;

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

        // Print each key and its fetched value to the console
        printImplantTypes();
    }

    public static int getImplantType(String nbtTag) {
        return implantType.getOrDefault(nbtTag, -1);
    }

    private static void printImplantTypes() {
        for (String key : implantType.keySet()) {
            int value = getImplantType(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }
}
