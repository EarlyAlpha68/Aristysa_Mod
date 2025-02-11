package net.earlyalpha.aristysa.mixin;

import net.earlyalpha.aristysa.effect.ModEffects;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.EnderEyeTp;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Debug(export = true)
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements EnderEyeTp {
    @Inject(method = "tick", at = @At("HEAD"))
    private void modifyBaseArmorValue(CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
            NbtCompound persistentData = ((IEntityDataSaver) player).getPersistentData();

            if (persistentData.contains("subdermalArmorTier")) {
                int tier = persistentData.getInt("subdermalArmorTier");
                double baseArmorValue = 0.0;

                switch (tier) {
                    case 1:
                        baseArmorValue = 5.0;
                        break;
                    case 2:
                        baseArmorValue = 7.0;
                        break;
                    case 3:
                        baseArmorValue = 10.0;
                        break;
                    default:
                        baseArmorValue = 0.0;
                        break;
                }

                EntityAttributeInstance armorAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);
                if (armorAttribute != null) {
                    armorAttribute.setBaseValue(baseArmorValue);
                }
            }
        }
    }
    private static final UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("55fc0c0b-12d6-4a0f-8b8c-4a3cddae36c5");
    private static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("fa233e1c-4180-4865-b01b-bc45191b7e5d");
    private static final UUID ATTACK_KNOCKBACK_MODIFIER_ID = UUID.fromString("5f5d9b3f-83ed-4d5a-9f79-0cc4727b95ff");

    @Inject(method = "tick", at = @At("HEAD"))
    private void modifyBaseAttackANDKnockbackValue(CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
            NbtCompound persistentData = ((IEntityDataSaver) player).getPersistentData();

                int tier = persistentData.getInt("golemArmTier");
                double KnockbackValue = getGolemArmModifierValue(tier,"knockback");
                double AttackValue = getGolemArmModifierValue(tier,"attack");
                double SpeedValue = getGolemArmModifierValue(tier,"speed");

                switch (tier) {
                    case 1:
                        KnockbackValue = 3.0;
                        AttackValue = 3.0;
                        SpeedValue = 2.0;
                        break;
                    case 2:
                        KnockbackValue = 4.0;
                        AttackValue = 5.0;
                        SpeedValue = 2.0;
                        break;
                    case 3:
                        KnockbackValue = 5.0;
                        AttackValue = 7.0;
                        SpeedValue = 2.0;
                        break;
                    default:
                        KnockbackValue = 0.0;
                        AttackValue = 2.0;
                        SpeedValue = 4.0;
                        break;
                }

                applyModifier(player, EntityAttributes.GENERIC_ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER_ID, AttackValue);
                applyModifier(player, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, ATTACK_KNOCKBACK_MODIFIER_ID, KnockbackValue);
                applyModifier(player, EntityAttributes.GENERIC_ATTACK_SPEED, ATTACK_SPEED_MODIFIER_ID, SpeedValue);
        }
    }

    private double getGolemArmModifierValue(int tier, String key) {
        return switch (key) {
            case "knockbach" -> switch (tier) {
                case 1 -> 3.0;
                default -> 0;
            };
            default ->0;
        };

    }


    private void applyModifier(ServerPlayerEntity player, EntityAttribute attribute, UUID modifierId, double value) {
        EntityAttributeInstance attributeInstance = player.getAttributeInstance(attribute);
        if (attributeInstance != null) {
            EntityAttributeModifier existingModifier = attributeInstance.getModifier(modifierId);
            if (existingModifier != null) {
                attributeInstance.removeModifier(modifierId);
            }
            attributeInstance.addPersistentModifier(new EntityAttributeModifier(modifierId, "Golem Arm Modifier", value, EntityAttributeModifier.Operation.ADDITION));
        }
    }
    private boolean shouldTeleport = false;
    @Inject(method = "tick", at = @At("HEAD"))
    private void getLookingPosition(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (shouldTeleport){
            teleportPlayer(player);
            shouldTeleport = false;
        }
    }
    public void triggerTeleport(){
        if (!shouldTeleport) {
            shouldTeleport = true;
        }
    }

    public void teleportPlayer(PlayerEntity player) {
        int currentTeleportDistance = EarlyUtil.getCurrentTeleportDistance(player);
        BlockHitResult hitResult = EarlyUtil.getHitBlock(player,currentTeleportDistance);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            Vec3d hitPos = hitResult.getPos();
            player.teleport(hitPos.getX(), hitPos.getY()+ 1, hitPos.getZ());
            switch (EarlyUtil.getImplantTier(player,"enderEyeTier")) {
                case 1:
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_EYE_COOLDOWN,1800,0,false,false,true));
                    break;
                case 2:
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_EYE_COOLDOWN,1200,0,false,false,true));
                    break;
                case 3:
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.ENDER_EYE_COOLDOWN,600,0,false,false,true));
                    break;
                default:
                    break;
            }
        }
    }
}