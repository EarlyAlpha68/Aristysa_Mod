package net.earlyalpha.aristysa.effect;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect OPTICAL_CAMO_COOLDOWN;
    public static StatusEffect ENDER_EYE_COOLDOWN;
    public static StatusEffect WARDEN_HEART_COOLDOWN;
    public static StatusEffect CYBERLEG_COOLDOWN;
    public static StatusEffect CRIMSON_WOUND;

    public static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Aristysa.MOD_ID, name),effect);
    }
    public static void registerEffects() {
        OPTICAL_CAMO_COOLDOWN = registerStatusEffect("optical_camo_cooldown",
                new CyberWareCooldown(StatusEffectCategory.NEUTRAL,200200200));
        ENDER_EYE_COOLDOWN = registerStatusEffect("ender_eye_cooldown",
                new CyberWareCooldown(StatusEffectCategory.NEUTRAL,200200200));
        WARDEN_HEART_COOLDOWN = registerStatusEffect("warden_heart_cooldown",
                new CyberWareCooldown(StatusEffectCategory.NEUTRAL,200200200));
        CYBERLEG_COOLDOWN = registerStatusEffect("cyberleg_cooldown",
                new CyberWareCooldown(StatusEffectCategory.NEUTRAL,200200200));
        CRIMSON_WOUND = registerStatusEffect("crimson_wound",
                new CrimsonWound(StatusEffectCategory.HARMFUL,0x8B0000));
    }
}
