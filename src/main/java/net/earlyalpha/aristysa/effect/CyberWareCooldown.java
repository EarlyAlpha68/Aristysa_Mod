package net.earlyalpha.aristysa.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class CyberWareCooldown extends StatusEffect {
    protected CyberWareCooldown(StatusEffectCategory category, int color) {
        super(category, color);
    }
    public void applyUpdateEffect(LivingEntity entity, int pAmplifier) {
        super.applyUpdateEffect(entity, pAmplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return false;
    }

}
