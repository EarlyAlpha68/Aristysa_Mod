package net.earlyalpha.aristysa.event;

import net.earlyalpha.aristysa.util.EarlyUtil;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public class ServerPlayerTickEvent implements ServerTickEvents.StartTick{

    private static final UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("55fc0c0b-12d6-4a0f-8b8c-4a3cddae36c5");
    private static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("fa233e1c-4180-4865-b01b-bc45191b7e5d");
    private static final UUID ATTACK_KNOCKBACK_MODIFIER_ID = UUID.fromString("5f5d9b3f-83ed-4d5a-9f79-0cc4727b95ff");

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()){
            subdermalArmorTicking(player);
            golemArmTicking(player);
        }
    }

    private static void subdermalArmorTicking(ServerPlayerEntity player) {
        int tier = EarlyUtil.getImplantTier(player,"subdermalArmorTier");
        double baseArmorValue = EarlyUtil.getArmorValueModifier(tier);

        EntityAttributeInstance armorAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);
        if (armorAttribute != null) {
            armorAttribute.setBaseValue(baseArmorValue);
        }
    }

    private static void golemArmTicking(ServerPlayerEntity player) {
        int tier = EarlyUtil.getImplantTier(player,"golemArmTier");
        double KnockbackValue = EarlyUtil.getGolemArmModifierValue(tier,"knockback");
        double AttackValue = EarlyUtil.getGolemArmModifierValue(tier,"attack");
        double SpeedValue = EarlyUtil.getGolemArmModifierValue(tier,"speed");

        EarlyUtil.applyModifier(player, EntityAttributes.GENERIC_ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER_ID, AttackValue);
        EarlyUtil.applyModifier(player, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, ATTACK_KNOCKBACK_MODIFIER_ID, KnockbackValue);
        EarlyUtil.applyModifier(player, EntityAttributes.GENERIC_ATTACK_SPEED, ATTACK_SPEED_MODIFIER_ID, SpeedValue);
    }
}
