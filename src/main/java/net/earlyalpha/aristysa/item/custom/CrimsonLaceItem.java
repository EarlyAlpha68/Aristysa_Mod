package net.earlyalpha.aristysa.item.custom;

import net.earlyalpha.aristysa.effect.ModEffects;
import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CrimsonLaceItem extends Item {
    private static final int INJECTION_TIME = 40;
    private static final String INJECTION = "UsageTicks";

    public CrimsonLaceItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.setCurrentHand(hand);
        System.out.println("start");
        return TypedActionResult.consume(player.getStackInHand(hand));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient) {
            NbtCompound nbt = stack.getOrCreateNbt();
            int usageTicks = INJECTION_TIME - remainingUseTicks;
            nbt.putInt(INJECTION, usageTicks);
            System.out.println(nbt.getInt(INJECTION));
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            NbtCompound nbt = stack.getOrCreateNbt();
            int usageTicks = nbt.getInt(INJECTION);

            if (usageTicks >= 30) {
                inject(player);
                stack.decrement(1);

            }
            nbt.putInt(INJECTION, 0);
        }
        return stack;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(INJECTION, 0);
    }

    private void inject(LivingEntity player) {
        player.addStatusEffect(new StatusEffectInstance(ModEffects.CRIMSON_WOUND, 12000, 0, true, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6000, 2, true, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1, true, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 6000, 2, true, true, true));
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return INJECTION_TIME ;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift0"));
            tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift1"));
            tooltip.add(Text.translatable("tooltip.aristysa.crimsonlace_shift2"));
        } else {
            tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
        }
    }
}
