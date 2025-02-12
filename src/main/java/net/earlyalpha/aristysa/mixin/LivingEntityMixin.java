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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;


@Debug(export = true)
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract void pushAwayFrom(Entity entity);

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
                beingRemoved.getEffectType() == ModEffects.WARDEN_HEART_COOLDOWN ||
                        beingRemoved.getEffectType() == ModEffects.ENDER_EYE_COOLDOWN ||
                        beingRemoved.getEffectType() == ModEffects.OPTICAL_CAMO_COOLDOWN ||
                        beingRemoved.getEffectType() == ModEffects.CYBERLEG_COOLDOWN ||
                        beingRemoved.getEffectType() == ModEffects.CRIMSON_WOUND
        );
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void tryUseCustomTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity instanceof ServerPlayerEntity player) {
            int tier = EarlyUtil.getImplantTier(player, "wardenHeartTier");
            if (tier > 0 && !player.hasStatusEffect(ModEffects.WARDEN_HEART_COOLDOWN)) {
                EarlyUtil.wardenHeartEffect(player, tier);
                cir.setReturnValue(true);
            }
        }

    }
}
