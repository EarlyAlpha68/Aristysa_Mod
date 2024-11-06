package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class EarlyUtil {
    public static boolean drinkingMilk = false;
    public static HashMap<String, Integer> implantType = new HashMap<>();
    public static HashMap<String, String> implantName = new HashMap<>();
    static {
        implantType.put("cyberLegTier", 0);
        implantType.put("enderEyeTier", 1);
        implantType.put("golemArmTier", 2);
        implantType.put("opticalCamoTier", 3);
        implantType.put("subdermalArmorTier", 4);
        implantType.put("wardenHearthTier", 5);
        //Init the implant in their specific order
        implantName.put("cyberLegTier","Cyber Leg");
        implantName.put("enderEyeTier","Ender Eye");
        implantName.put("golemArmTier","Golem Arm");
        implantName.put("opticalCamoTier","Optical Camo");
        implantName.put("subdermalArmorTier","SubDermal Armor");
        implantName.put("wardenHearthTier","Warden Hearth");
    }

    public static int getImplantType(String nbtTag) {
        return implantType.getOrDefault(nbtTag, -1);
        //give the attached number of order of a specific implant
    }
    public static String getImplantName(String nbtTag) {
        return implantName.getOrDefault(nbtTag, "");
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

    public static void toolTip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, int tier,int type) {
        switch (type) {
            case 0 :
            switch (tier) {
                case 1:
                    if (Screen.hasShiftDown()) {
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg1_shift0"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg1_shift1"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg1_shift2"));

                    } else {
                        tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                    }
                    break;
                case 2:
                    if (Screen.hasShiftDown()) {
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg2_shift0"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg2_shift1"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg2_shift2"));

                    } else {
                        tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                    }
                    break;
                case 3:
                    if (Screen.hasShiftDown()) {
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg3_shift0"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg3_shift1"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg3_shift2"));

                    } else {
                        tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                    }
                    break;
                default:
                    break;
            } break;
            case 1 :
                switch (tier) {
                    case 1:
                        if (Screen.hasShiftDown()){
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye1_shift0"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye1_shift1"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye1_shift2"));

                        } else {
                            tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                        }
                        break;
                    case 2:
                        if (Screen.hasShiftDown()){
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye2_shift0"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye2_shift1"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye2_shift2"));

                        } else {
                            tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                        }
                        break;
                    case 3:
                        if (Screen.hasShiftDown()){
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye3_shift0"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye3_shift1"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye3_shift2"));

                        } else {
                            tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                        }
                        break;
                    default:
                        break;
                }break;
        }
    }
}
