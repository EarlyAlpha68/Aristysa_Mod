package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;


public class ImplantUsage {
    public static void OpticalCamo(ServerPlayerEntity player) {
        if (!player.hasStatusEffect(ModEffects.OPTICAL_CAMO_COOLDOWN)) {
            int i = EarlyUtil.getImplantTier(player, "opticalCamoTier");
            switch (i) {
                case 1:
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 400, 0, false, false, true));
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN, 2200, 0, false, false, true));
                    break;
                case 2:
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 800, 0, false, false, true));
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN, 2000, 0, false, false, true));
                    break;
                case 3:
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 1200, 0, false, false, true));
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.OPTICAL_CAMO_COOLDOWN, 1800, 0, false, false, true));
                    break;
                default:
                    break;
            }
        }
    }

    public static void EnderEye(ServerPlayerEntity player) {
        if ((!player.hasStatusEffect(ModEffects.ENDER_EYE_COOLDOWN)) && ((IEntityDataSaver) player).getPersistentData().getInt("enderEyeTier") > 0) {
            if (player instanceof EnderEyeTp teleportation) {
                teleportation.triggerTeleport();
            }
        }
    }

    public static void CyberLeg(ServerPlayerEntity player) {
        int i = EarlyUtil.getImplantTier(player, "cyberLegTier");
        if (!player.hasStatusEffect(ModEffects.CYBERLEG_COOLDOWN)) {
            switch (i) {
                case 1:
                    Dash(player);
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.CYBERLEG_COOLDOWN, 300, 0, false, false, true));
                    break;
                case 2:
                    Dash(player);
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.CYBERLEG_COOLDOWN, 200, 0, false, false, true));
                    break;
                case 3:
                    Dash(player);
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.CYBERLEG_COOLDOWN, 100, 0, false, false, true));
                    break;
                default:
                    break;
            }
        }
    }

    private static void Dash (ServerPlayerEntity player){
        Vec3d lookVec = player.getRotationVec(1.0F);
        Vec3d dashVec = lookVec.multiply(1);
        player.addVelocity(dashVec.x, dashVec.y, dashVec.z);
        player.velocityModified = true;
        // Modifying the velocity of the player to achieve a "Dash" movement
    }

}
