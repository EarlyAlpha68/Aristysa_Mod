package net.earlyalpha.aristysa.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class CrimsonWound extends StatusEffect {
    public static final UUID HALF_MAX_HEALTH_UUID = UUID.fromString("cc9880e9-7b53-4e45-8c2e-7d292f717c30");

    protected CrimsonWound(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_MAX_HEALTH,
                HALF_MAX_HEALTH_UUID.toString(),
                -0.5,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }
    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity.getHealth() > entity.getMaxHealth()/2) {
            entity.setHealth(entity.getMaxHealth()/2);
        } else {
            entity.setHealth(entity.getHealth());
        }
        //manually cut the health to its half if it is to high or just set it to itself for client hud sync
        super.onApplied(entity, attributes, amplifier);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
