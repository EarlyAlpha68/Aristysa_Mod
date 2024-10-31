package net.earlyalpha.aristysa.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class CrimsonWound extends StatusEffect {
    public static final UUID HALF_MAX_HEALTH_UUID = UUID.fromString("cc9880e9-7b53-4e45-8c2e-7d292f717c30");

    protected CrimsonWound(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        EntityAttributeModifier HALF_MAX_HEALTH_MODIFIER = new EntityAttributeModifier( HALF_MAX_HEALTH_UUID, "Half max health", -0.5,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        EntityAttributeModifier halfMaxHealthModifier = new EntityAttributeModifier( HALF_MAX_HEALTH_UUID, "Half max health", -0.5, EntityAttributeModifier.Operation.MULTIPLY_TOTAL );
        if (entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH) != null) {
            if (!entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).hasModifier(halfMaxHealthModifier)) {
                entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(halfMaxHealthModifier);
            }
        }
        super.applyUpdateEffect(entity,amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
        if (entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH) != null) {
            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).removeModifier(HALF_MAX_HEALTH_UUID);
        }
    }
}