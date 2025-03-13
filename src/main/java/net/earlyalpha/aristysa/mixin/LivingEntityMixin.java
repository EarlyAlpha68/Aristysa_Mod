package net.earlyalpha.aristysa.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.command.EffectCommand;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.Objects;


@Debug(export = true)
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract void pushAwayFrom(Entity entity);

    private StatusEffectInstance beingRemoved = null;

    @WrapWithCondition(method = "clearStatusEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;onStatusEffectRemoved(Lnet/minecraft/entity/effect/StatusEffectInstance;)V"))
    private boolean noInvokeOnRemove(LivingEntity instance, StatusEffectInstance effect) {
        beingRemoved = effect;
        if (shouldNotClear(instance)) {
            return false;
        }
        return true;
    }

    @WrapWithCondition(method = "clearStatusEffects", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;remove()V"))
    private boolean noRemove(Iterator<StatusEffect> instance) {
        return !shouldNotClear((LivingEntity) (Object) this);
    }

    @Unique
    private boolean shouldNotClear(LivingEntity entity) {
        if (EarlyUtil.drinkingMilkORTotemUse.get(entity) == null){
            EarlyUtil.drinkingMilkORTotemUse.put(entity,false);
        }

        return  EarlyUtil.drinkingMilkORTotemUse.get(entity) && beingRemoved != null && (
                beingRemoved.getEffectType() == ModEffects.WARDEN_HEART_COOLDOWN ||
                        beingRemoved.getEffectType() == ModEffects.ENDER_EYE_COOLDOWN ||
                        beingRemoved.getEffectType() == ModEffects.OPTICAL_CAMO_COOLDOWN ||
                        beingRemoved.getEffectType() == ModEffects.CYBERLEG_COOLDOWN ||
                        beingRemoved.getEffectType() == ModEffects.CRIMSON_WOUND
        );
    }


    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void setBoolTrue(DamageSource source, CallbackInfoReturnable<Boolean> cir){
        EarlyUtil.drinkingMilkORTotemUse.put((LivingEntity) (Object) this,true);
    }

    @Inject(method = "tryUseTotem", at = @At("RETURN"), cancellable = true)
    private void resetBool(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        EarlyUtil.drinkingMilkORTotemUse.put((LivingEntity) (Object) this,false);
    }
}
