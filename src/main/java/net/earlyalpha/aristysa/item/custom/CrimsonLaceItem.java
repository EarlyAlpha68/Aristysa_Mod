package net.earlyalpha.aristysa.item.custom;

import net.earlyalpha.aristysa.effect.ModEffects;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrimsonLaceItem extends Item {
    public CrimsonLaceItem(Settings settings) {
        super(settings);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient && !player.hasStatusEffect(ModEffects.CRIMSON_WOUND)) {
            itemStack.decrement(1);
            player.addStatusEffect(new StatusEffectInstance(ModEffects.CRIMSON_WOUND, 12000, 0, true, true,true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6000, 2, true, true,true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1, true, true,true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 6000, 2, true, true,true));
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift0"));
            tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift1"));
            tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift2"));

        } else {
            tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
        }
    }
}
