package net.earlyalpha.aristysa.item.custom;

import net.earlyalpha.aristysa.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CrimsonLaceItem extends Item {
    public CrimsonLaceItem(Settings settings) {
        super(settings);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient && !player.hasStatusEffect(ModEffects.CRIMSON_WOUND)) {
            itemStack.decrement(1);
            player.addStatusEffect(new StatusEffectInstance(ModEffects.CRIMSON_WOUND, 6600, 0, true, true,true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6600, 2, true, true,true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6600, 1, true, true,true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 6600, 2, true, true,true));
        }
        return TypedActionResult.success(itemStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return super.getMaxUseTime(stack);
    }
}
