package net.earlyalpha.aristysa.event;

import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class PlayerDeathHandler {
    public static void register() {
        ServerPlayerEvents.ALLOW_DEATH.register((player, damageSource, damageAmount) -> {
            onPlayerDeath(player);
            return true;
        });
    }
    private static void onPlayerDeath(ServerPlayerEntity player) {
        World world = player.getWorld();
        boolean hasTotem = player.getMainHandStack().getItem() == Items.TOTEM_OF_UNDYING
                || player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING;
        boolean hasWardenHearthReady = ((IEntityDataSaver) player).getPersistentData().getInt("wardenHearthTier") > 0 && !player.hasStatusEffect(ModEffects.WARDEN_HEARTH_COOLDOWN);
        if (!world.isClient() && player.getHealth() <= 0 && !hasTotem && !hasWardenHearthReady) {
            switch (((IEntityDataSaver) player).getPersistentData().getInt("opticalCamoTier")) {
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
                switch (((IEntityDataSaver) player).getPersistentData().getInt("golemArmTier")) {
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
                switch (((IEntityDataSaver) player).getPersistentData().getInt("enderEyeTier")) {
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
                switch (((IEntityDataSaver) player).getPersistentData().getInt("subdermalArmorTier")) {
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
            switch (((IEntityDataSaver) player).getPersistentData().getInt("wardenHearthTier")) {
                case 1:
                    player.dropItem(ModItems.WARDEN_HEARTH_1);
                    break;
                case 2:
                    player.dropItem(ModItems.WARDEN_HEARTH_2);
                    break;
                case 3:
                    player.dropItem(ModItems.WARDEN_HEARTH_3);
                    break;
                default:
                    break;
            }
            switch (((IEntityDataSaver) player).getPersistentData().getInt("cyberLegTier")) {
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

