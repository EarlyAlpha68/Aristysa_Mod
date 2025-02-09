package net.earlyalpha.aristysa.item.custom;

import java.util.List;

import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
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

public class SyringeItem extends Item {
    private static final int INJECTION_TIME = 40;
    private static final String INJECTION = "UsageTicks";
    protected String key;

    public SyringeItem(Settings settings, String key) {
        super(settings);
        this.key = key;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.setCurrentHand(hand);
        return TypedActionResult.consume(player.getStackInHand(hand));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient) {
            NbtCompound nbt = stack.getOrCreateNbt();
            int usageTicks = INJECTION_TIME - remainingUseTicks;
            nbt.putInt(INJECTION, usageTicks);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            NbtCompound nbt = stack.getOrCreateNbt();
            int usageTicks = nbt.getInt(INJECTION);

            if (usageTicks >= 30) {
                EarlyUtil.SyringeInject(player,this.key);
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
        EarlyUtil.SyringeItemToolTip(stack,world,tooltip,context,EarlyUtil.getSyringeType(this.key));
    }
}
