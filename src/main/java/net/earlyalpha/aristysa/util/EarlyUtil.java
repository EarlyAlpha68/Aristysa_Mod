package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EarlyUtil {
    public static boolean drinkingMilk = false;
    public static HashMap<String, Integer> implantType = new HashMap<>();
    public static HashMap<String, String> implantName = new HashMap<>();
    public static HashMap<String, Integer> syringeType = new HashMap<>();
    static {
        //Init the key to sort the custom item
        syringeType.put("crimsonLace", 0);
        syringeType.put("phantomElixir", 1);
        syringeType.put("shadowHaste", 2);
        //
        implantType.put("cyberLegTier", 0);
        implantType.put("enderEyeTier", 1);
        implantType.put("golemArmTier", 2);
        implantType.put("opticalCamoTier", 3);
        implantType.put("subdermalArmorTier", 4);
        implantType.put("wardenHeartTier", 5);
        //Init the implants name in their specific order
        implantName.put("cyberLegTier","Cyber Leg");
        implantName.put("enderEyeTier","Ender Eye");
        implantName.put("golemArmTier","Golem Arm");
        implantName.put("opticalCamoTier","Optical Camo");
        implantName.put("subdermalArmorTier","SubDermal Armor");
        implantName.put("wardenHeartTier","Warden Heart");
    }

    public static int getImplantType(String key) {
        return implantType.getOrDefault(key, -1);
        //give the attached number of order of a specific implant
    }

    public static String getImplantName(String key) {
        return implantName.getOrDefault(key, "");
        //give the attached number of order of a specific implant
    }
    public static int getSyringeType(String key) {
        return syringeType.getOrDefault(key, -1);
        //give the attached number of order of a specific implant
    }
    public static int getImplantTier(PlayerEntity player ,String key) {
        return ((IEntityDataSaver) player).getPersistentData().getInt(key);
        // Allow to get the implant tier from anywhere
    }
    public static void addTier(IEntityDataSaver player, int tierAdd, String nbt_data) {
        //Allow to change the nbt data of the player
        NbtCompound nbt = player.getPersistentData();
        nbt.putInt(nbt_data, tierAdd);
    }
    public static void wardenHeartEffect(ServerPlayerEntity player ,int tier) {
        int duration = getWardenHeartDuration(tier);
        player.setHealth(1.0F);
        player.clearStatusEffects();
        player.addStatusEffect(new StatusEffectInstance(ModEffects.WARDEN_HEART_COOLDOWN,duration,0,false,false,true));
        player.setHealth(player.getMaxHealth());
        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.PLAYERS, 1.0f, 1.0f);
        applyEffectToNearbyEntities(player,tier*3);
    }

    public static int getWardenHeartDuration(int tier) {
        return switch(tier){
            case 1 ->18000;
            case 2 ->12000;
            case 3 ->6000;
            default -> 0;
        };
    }

    public static void applyEffectToNearbyEntities(ServerPlayerEntity player,int radius) {
        List<Entity> nearbyEntities = player.getWorld().getOtherEntities(player, player.getBoundingBox().expand(radius));

        for (Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 600, 1));
            }
        }
    }



public static void applyModifier(ServerPlayerEntity player, EntityAttribute attribute, UUID modifierId, double value) {
        EntityAttributeInstance attributeInstance = player.getAttributeInstance(attribute);
        if (attributeInstance != null) {
            EntityAttributeModifier existingModifier = attributeInstance.getModifier(modifierId);
            if (existingModifier != null) {
                attributeInstance.removeModifier(modifierId);
            }
            attributeInstance.addPersistentModifier(new EntityAttributeModifier(modifierId, "Attribute Modifier", value, EntityAttributeModifier.Operation.ADDITION));
        }
    }
    public static void teleportEnderEyePlayer(PlayerEntity player) {
        int currentTeleportDistance = EarlyUtil.getCurrentTeleportDistance(player);
        BlockHitResult hitResult = EarlyUtil.getHitBlock(player,currentTeleportDistance);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            Vec3d hitPos = hitResult.getPos();
            player.teleport(hitPos.getX(), hitPos.getY()+ 1, hitPos.getZ());
            switch (EarlyUtil.getImplantTier(player,"enderEyeTier")) {
                case 1:
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_EYE_COOLDOWN,1800,0,false,false,true));
                    break;
                case 2:
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_EYE_COOLDOWN,1200,0,false,false,true));
                    break;
                case 3:
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_EYE_COOLDOWN,600,0,false,false,true));
                    break;
                default:
                    break;
            }
        }
    }
    public static double getArmorValueModifier(int tier) {
        return switch (tier) {
            case 1 -> 5.0;
            case 2 -> 7.0;
            case 3 -> 10.0;
            default -> 0.0;
        };
    }
    public static double getGolemArmModifierValue(int tier, String key) {
        return switch (key) {
            case "knockbach" -> switch (tier) {
                case 1 -> 3.0;
                case 2 -> 4.0;
                case 3 -> 5.0;
                default -> 0.0;
            };
            case "attack" -> switch (tier) {
                case 1 -> 3.0;
                case 2 -> 5.0;
                case 3 -> 7.0;
                default -> 2.0;
            };
            case "speed" -> switch (tier) {
                case 1, 2, 3 -> 2.0;
                default -> 4.0;
            };
            default ->0;
        };

    }
    public static int getCurrentTeleportDistance(PlayerEntity player) {
        int currentTeleportDistance = switch (EarlyUtil.getImplantTier(player, "enderEyeTier")) {
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> 40;
            default -> 0;
        };
        return currentTeleportDistance;
    }
    public static BlockHitResult getHitBlock(PlayerEntity player, int currentTeleportDistance ) {
        //Utility for the enderEye(calculation of the block to teleport to)
        World world = player.getWorld();
        Vec3d startVec = player.getCameraPosVec(1.0F);
        Vec3d lookVec = player.getRotationVec(1.0F);
        Vec3d endVec = startVec.add(lookVec.x * currentTeleportDistance, lookVec.y * currentTeleportDistance, lookVec.z * currentTeleportDistance);

        BlockHitResult hitResult = world.raycast(new RaycastContext(
                startVec,
                endVec,
                RaycastContext.ShapeType.OUTLINE,
                RaycastContext.FluidHandling.NONE,
                player
        ));
        return hitResult;
    }
    public static void SyringeInject(LivingEntity player, String key) {
        //Utility for the effect of the custom SyringeItem class
        switch (getSyringeType(key)) {
            case 0 :
                player.addStatusEffect(new StatusEffectInstance(ModEffects.CRIMSON_WOUND, 12000, 0, true, true, true));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6000, 3, true, true, true));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 6000, 2, true, true, true));
                break;
            case 1 :
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 6000, 6, true, true, true));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 6000, 4, true, true, true));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 6000, 4, true, true, true));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 6000, 2, true, true, true));
                break;
            case 2 :
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000, 3, true, true, true));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 6000, 1, true, true, true));
            default:
                break;
        }

    }
    public static ItemStack CyberwareItemstack(int implantType, int implantTier){
        //When call give you the ItemStack of the Implant
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
                case 1 -> new ItemStack(ModItems.WARDEN_HEART_1);
                case 2 -> new ItemStack(ModItems.WARDEN_HEART_2);
                case 3 -> new ItemStack(ModItems.WARDEN_HEART_3);
                default -> ItemStack.EMPTY;
            };
            default -> ItemStack.EMPTY;
        };

    }
    // ************************************************************************************************************************************** \\
    // All Custom Item Tooltip
    public static void SyringeItemToolTip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, int type) {
        //Utility for the tooltip of the custom SyringeItem class
        switch (type) {
            case 0:
                if (Screen.hasShiftDown()) {
                    tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift0"));
                    tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift1"));
                    tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift2"));
                } else {
                    tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                }
                break;
            case 1:
                if (Screen.hasShiftDown()) {
                    tooltip.add(Text.translatable("tooltip.aristysa.phantom_elixir_shift0"));
                    tooltip.add(Text.translatable("tooltip.aristysa.phantom_elixir_shift1"));
                } else {
                    tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                }
                break;
            case 2:
                if (Screen.hasShiftDown()) {
                    tooltip.add(Text.translatable("tooltip.aristysa.shadow_haste_shift0"));
                    tooltip.add(Text.translatable("tooltip.aristysa.shadow_haste_shift1"));
                } else {
                    tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                }
            default:
                break;
        }
    }

    public static void cyberwareItemToolTip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, int tier, int type) {
        //Utility for the tooltip of the custom CyberwareItem class
        switch (type) {
            case 0 :
            switch (tier) {
                case 1:
                    if (Screen.hasShiftDown()) {
                        tooltip.add(Text.translatable("tooltip.aristysa.tier_1"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg_shift1"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg1_shift2"));

                    } else {
                        tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                    }
                    break;
                case 2:
                    if (Screen.hasShiftDown()) {
                        tooltip.add(Text.translatable("tooltip.aristysa.tier_2"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg_shift1"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg2_shift2"));

                    } else {
                        tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                    }
                    break;
                case 3:
                    if (Screen.hasShiftDown()) {
                        tooltip.add(Text.translatable("tooltip.aristysa.tier_3"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg_shift1"));
                        tooltip.add(Text.translatable("tooltip.aristysa.cyberleg3_shift2"));

                    } else {
                        tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                    }
                    break;
                default:
                    break;
            }
            break;
            case 1 :
                switch (tier) {
                    case 1:
                        if (Screen.hasShiftDown()){
                            tooltip.add(Text.translatable("tooltip.aristysa.tier_1"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye1_shift1"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye1_shift2"));

                        } else {
                            tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                        }
                        break;
                    case 2:
                        if (Screen.hasShiftDown()){
                            tooltip.add(Text.translatable("tooltip.aristysa.tier_2"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye2_shift1"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye2_shift2"));

                        } else {
                            tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                        }
                        break;
                    case 3:
                        if (Screen.hasShiftDown()){
                            tooltip.add(Text.translatable("tooltip.aristysa.tier_3"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye3_shift1"));
                            tooltip.add(Text.translatable("tooltip.aristysa.endereye3_shift2"));

                        } else {
                            tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                        }
                        break;
                    default:
                        break;
                }
                break;
                case 2:
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.golemarm_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.golemarm1_shift2"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Text.translatable("tooltip.aristysa.golemarm_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.golemarm2_shift2"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Text.translatable("tooltip.aristysa.golemarm_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.golemarm3_shift2"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.optical_camo1_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.optical_camo1_shift2"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Text.translatable("tooltip.aristysa.optical_camo2_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.optical_camo2_shift2"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Text.translatable("tooltip.aristysa.optical_camo3_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.optical_camo3_shift2"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.subdermalarmor1_shift1"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Text.translatable("tooltip.aristysa.subdermalarmor2_shift1"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Text.translatable("tooltip.aristysa.subdermalarmor3_shift1"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    switch (tier) {
                        case 1:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart1_shift2"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart1_shift3"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 2:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_2"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart2_shift2"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart2_shift3"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        case 3:
                            if (Screen.hasShiftDown()){
                                tooltip.add(Text.translatable("tooltip.aristysa.tier_3"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart_shift1"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart3_shift2"));
                                tooltip.add(Text.translatable("tooltip.aristysa.wardenheart3_shift3"));

                            } else {
                                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
        }
    }


}
