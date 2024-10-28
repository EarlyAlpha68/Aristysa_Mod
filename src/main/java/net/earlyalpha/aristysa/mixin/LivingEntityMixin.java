package net.earlyalpha.aristysa.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

@Debug(export = true)
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    private StatusEffectInstance beingRemoved = null;

    @WrapWithCondition(method = "clearStatusEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;onStatusEffectRemoved(Lnet/minecraft/entity/effect/StatusEffectInstance;)V"))
    private boolean noInvokeOnRemove(LivingEntity instance, StatusEffectInstance effect) {
        beingRemoved = effect;
        if (shouldNotClear()) {
            return false;
        }
        return true;
    }

    @WrapWithCondition(method = "clearStatusEffects", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;remove()V"))
    private boolean noRemove(Iterator<StatusEffect> instance) {
        return !shouldNotClear();
    }

    @Unique
    private boolean shouldNotClear() {

        return EarlyUtil.drinkingMilk && beingRemoved != null && (
                beingRemoved.getEffectType() == ModEffects.WARDEN_HEARTH_COOLDOWN ||
                beingRemoved.getEffectType() == ModEffects.ENDER_EYE_COOLDOWN ||
                beingRemoved.getEffectType() == ModEffects.OPTICAL_CAMO_COOLDOWN ||
                beingRemoved.getEffectType() == ModEffects.CYBERLEG_COOLDOWN
        );
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void tryUseCustomTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity instanceof ServerPlayerEntity player) {
            if (((IEntityDataSaver) player).getPersistentData().getInt("wardenHearthTier") > 0 && !player.hasStatusEffect(ModEffects.WARDEN_HEARTH_COOLDOWN)) {
                switch (((IEntityDataSaver) player).getPersistentData().getInt("wardenHearthTier")){
                    case 1:
                        player.setHealth(1.0F);
                        player.clearStatusEffects();
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.WARDEN_HEARTH_COOLDOWN,18000,0,false,false,true));
                        player.setHealth(player.getMaxHealth());
                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        cir.setReturnValue(true);
                        applyEffectToNearbyEntities(player,3);
                        break;
                    case 2:
                        player.setHealth(1.0F);
                        player.clearStatusEffects();
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.WARDEN_HEARTH_COOLDOWN,12000,0,false,false,true));
                        player.setHealth(player.getMaxHealth());
                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        cir.setReturnValue(true);
                        applyEffectToNearbyEntities(player,6);

                        break;
                    case 3:
                        player.setHealth(1.0F);
                        player.clearStatusEffects();
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.WARDEN_HEARTH_COOLDOWN,6000,0,false,false,true));
                        player.setHealth(player.getMaxHealth());
                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        cir.setReturnValue(true);
                        applyEffectToNearbyEntities(player,10);
                        break;
                    default:
                        break;
                }
            }
        }

    }
    private void applyEffectToNearbyEntities(ServerPlayerEntity player,int radius) {
        List<Entity> nearbyEntities = player.getWorld().getOtherEntities(player, player.getBoundingBox().expand(radius));

        for (Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 600, 1));
            }
        }
    }
}


