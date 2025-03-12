package net.earlyalpha.aristysa.event;

import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class PlayerDeathHandler {
    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource ) -> {
            onPlayerDeath(entity);
        });
    }
    private static void onPlayerDeath(LivingEntity entity) {
        World world = entity.getWorld();
        if (!world.isClient() && entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;

                switch (EarlyUtil.getImplantTier(player,"opticalCamoTier")) {
                    case 1:
                        player.dropItem(ModItems.OPTICAL_CAMO_1);
                        break;
                    case 2:
                        player.dropItem(ModItems.OPTICAL_CAMO_2);
                        break;
                    case 3:
                        player.dropItem(ModItems.OPTICAL_CAMO_3);
                        break;
                    default:
                        break;
                }
                switch (EarlyUtil.getImplantTier(player,"golemArmTier")) {
                    case 1:
                        player.dropItem(ModItems.GOLEMARM_1);
                        break;
                    case 2:
                        player.dropItem(ModItems.GOLEMARM_2);
                        break;
                    case 3:
                        player.dropItem(ModItems.GOLEMARM_3);
                        break;
                    default:
                        break;
                }
                switch (EarlyUtil.getImplantTier(player,"enderEyeTier")) {
                    case 1:
                        player.dropItem(ModItems.ENDEREYE_1);
                        break;
                    case 2:
                        player.dropItem(ModItems.ENDEREYE_2);
                        break;
                    case 3:
                        player.dropItem(ModItems.ENDEREYE_3);
                        break;
                    default:
                        break;
                }
                switch (EarlyUtil.getImplantTier(player,"subdermalArmorTier")) {
                    case 1:
                        player.dropItem(ModItems.SUBDERMAL_ARMOR_1);
                        break;
                    case 2:
                        player.dropItem(ModItems.SUBDERMAL_ARMOR_2);
                        break;
                    case 3:
                        player.dropItem(ModItems.SUBDERMAL_ARMOR_3);
                        break;
                    default:
                        break;
                }
                switch (EarlyUtil.getImplantTier(player,"wardenHeartTier")) {
                    case 1:
                        player.dropItem(ModItems.WARDEN_HEART_1);
                        break;
                    case 2:
                        player.dropItem(ModItems.WARDEN_HEART_2);
                        break;
                    case 3:
                        player.dropItem(ModItems.WARDEN_HEART_3);
                        break;
                    default:
                        break;
                }
                switch (EarlyUtil.getImplantTier(player,"cyberLegTier")) {
                    case 1:
                        player.dropItem(ModItems.CYBERLEG_1);
                        break;
                    case 2:
                        player.dropItem(ModItems.CYBERLEG_2);
                        break;
                    case 3:
                        player.dropItem(ModItems.CYBERLEG_3);
                        break;
                    default:
                        break;
                }
        }
    }
}

