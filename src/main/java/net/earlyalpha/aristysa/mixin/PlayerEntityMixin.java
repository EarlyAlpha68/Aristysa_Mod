package net.earlyalpha.aristysa.mixin;

import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.EnderEyeTp;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
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
                int tier = EarlyUtil.getImplantTier(player,"subdermalArmorTier");
                double baseArmorValue = EarlyUtil.getArmorValueModifier(tier);

                EntityAttributeInstance armorAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);
                if (armorAttribute != null) {
                    armorAttribute.setBaseValue(baseArmorValue);
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
                double KnockbackValue = EarlyUtil.getGolemArmModifierValue(tier,"knockback");
                double AttackValue = EarlyUtil.getGolemArmModifierValue(tier,"attack");
                double SpeedValue = EarlyUtil.getGolemArmModifierValue(tier,"speed");

            EarlyUtil.applyModifier(player, EntityAttributes.GENERIC_ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER_ID, AttackValue);
            EarlyUtil.applyModifier(player, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, ATTACK_KNOCKBACK_MODIFIER_ID, KnockbackValue);
            EarlyUtil.applyModifier(player, EntityAttributes.GENERIC_ATTACK_SPEED, ATTACK_SPEED_MODIFIER_ID, SpeedValue);
        }
    }




    private boolean shouldTeleport = false;
    @Inject(method = "tick", at = @At("HEAD"))
    private void getLookingPosition(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (shouldTeleport){
            EarlyUtil.teleportEnderEyePlayer(player);
            shouldTeleport = false;
        }
    }
    public void triggerTeleport(){
        if (!shouldTeleport) {
            shouldTeleport = true;
        }
    }


}