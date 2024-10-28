package net.earlyalpha.aristysa.mixin;

import net.earlyalpha.aristysa.effect.ModEffects;
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
                double baseKnockbackValue;
                double baseAttackValue;
                double baseSpeedValue;

                switch (tier) {
                    case 1:
                        baseKnockbackValue = 3.0;
                        baseAttackValue = 3.0;
                        baseSpeedValue = 2.0;
                        break;
                    case 2:
                        baseKnockbackValue = 4.0;
                        baseAttackValue = 5.0;
                        baseSpeedValue = 2.0;
                        break;
                    case 3:
                        baseKnockbackValue = 5.0;
                        baseAttackValue = 7.0;
                        baseSpeedValue = 2.0;
                        break;
                    default:
                        baseKnockbackValue = 0.0;
                        baseAttackValue = 2.0;
                        baseSpeedValue = 4.0;
                        break;
                }

                applyModifier(player, EntityAttributes.GENERIC_ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER_ID, baseAttackValue);
                applyModifier(player, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, ATTACK_KNOCKBACK_MODIFIER_ID, baseKnockbackValue);
                applyModifier(player, EntityAttributes.GENERIC_ATTACK_SPEED, ATTACK_SPEED_MODIFIER_ID, baseSpeedValue);
        }
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

    public void teleportPlayer(PlayerEntity player) {
        int currentTeleportDistance;
        switch (((IEntityDataSaver) player).getPersistentData().getInt("enderEyeTier")) {
            case 1:
                currentTeleportDistance = 10 ;
                break;
            case 2:
                currentTeleportDistance = 20 ;
                break;
            case 3:
                currentTeleportDistance = 40;
                break;
            default:
                currentTeleportDistance = 0;
                break;
        }
        World world = player.getWorld();
        Vec3d startVec = player.getCameraPosVec(1.0F);
        Vec3d lookVec = player.getRotationVec(1.0F);
        Vec3d endVec = startVec.add(lookVec.x * currentTeleportDistance, lookVec.y * currentTeleportDistance, lookVec.z * currentTeleportDistance);

        BlockHitResult hitResult = world.raycast(new RaycastContext(
                startVec,
                endVec,
                RaycastContext.ShapeType.OUTLINE,
                RaycastContext.FluidHandling.NONE,
                player
        ));

        if (hitResult.getType() == HitResult.Type.BLOCK) {

            Vec3d hitPos = hitResult.getPos();
            player.teleport(hitPos.getX(), hitPos.getY()+ 1, hitPos.getZ());
            switch (((IEntityDataSaver) player).getPersistentData().getInt("enderEyeTier")) {
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
public void triggerTeleport(){
        if (!shouldTeleport) {
            shouldTeleport = true;
        }
    }
}