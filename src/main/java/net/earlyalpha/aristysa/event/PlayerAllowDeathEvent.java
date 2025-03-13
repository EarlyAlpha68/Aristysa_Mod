package net.earlyalpha.aristysa.event;

import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerAllowDeathEvent implements ServerLivingEntityEvents.AllowDeath {
    @Override
    public boolean allowDeath(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        if (entity instanceof ServerPlayerEntity player) {
            if (damageSource.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                return true;
            } else {
                int tier = EarlyUtil.getImplantTier(player, "wardenHeartTier");
                if (tier > 0 && !player.hasStatusEffect(ModEffects.WARDEN_HEART_COOLDOWN)) {
                EarlyUtil.wardenHeartEffect(player, tier);
                return false;
                }else {
                    return true;
                }
            }
        } else {
            return true;
        }
    }
}
