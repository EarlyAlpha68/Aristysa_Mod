package net.earlyalpha.aristysa.item.custom;

import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.networking.NetworkingsMessages;
import net.earlyalpha.aristysa.util.AddTier;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WardenHearthItem extends Item {
    protected int tier;
    protected String key = "wardenHearthTier";
    public WardenHearthItem(Settings settings, int tier){
        super(settings);
        this.tier = tier;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        boolean sync = false;
        if (!world.isClient()) {
            if (!(((IEntityDataSaver) player).getPersistentData().getInt(key) == this.tier)) {
                alreadyHasIt(player,this.tier);
                AddTier.addTier(((IEntityDataSaver) player), this.tier,"wardenHearthTier" );
                player.sendMessage(Text.literal("You successfully implant yourself an Warden Hearth tier " + ((IEntityDataSaver) player).getPersistentData().getInt(key)));
                sync = true;
            } else {
                player.sendMessage(Text.literal("You already have an Warden Hearth tier " + ((IEntityDataSaver) player).getPersistentData().getInt(key)));
                sync = false;
            }

        }
        if (sync) {
            ItemStack itemStack = player.getStackInHand(hand);
            itemStack.decrement(1);
            player.setStackInHand(hand, itemStack);

        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
    private static void alreadyHasIt(PlayerEntity player, int tiers){
        ItemStack oneItemStack = new ItemStack(ModItems.WARDEN_HEARTH_1, 1);
        ItemStack twoItemStack = new ItemStack(ModItems.WARDEN_HEARTH_2, 1);
        ItemStack threeItemStack = new ItemStack(ModItems.WARDEN_HEARTH_3, 1);
        if ((((IEntityDataSaver) player).getPersistentData().getInt("wardenHearthTier") == 1 && (!(tiers == 1)))) {
            if (!player.getInventory().insertStack(oneItemStack)) {
                player.dropItem(oneItemStack, false);
            }
        }
        if ((((IEntityDataSaver) player).getPersistentData().getInt("wardenHearthTier") == 2 && (!(tiers == 2)))) {
            if (!player.getInventory().insertStack(twoItemStack)) {
                player.dropItem(twoItemStack, false);
            }
        }
        if ((((IEntityDataSaver) player).getPersistentData().getInt("wardenHearthTier") == 3 && (!(tiers == 3)))) {
            if (!player.getInventory().insertStack(threeItemStack)) {
                player.dropItem(threeItemStack, false);
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        switch (this.tier) {
            case 1:
                if (Screen.hasShiftDown()){
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth1_shift0"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth1_shift1"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth1_shift2"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth1_shift3"));

                } else {
                    tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                }
                break;
            case 2:
                if (Screen.hasShiftDown()){
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth2_shift0"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth2_shift1"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth2_shift2"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth2_shift3"));

                } else {
                    tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                }
                break;
            case 3:
                if (Screen.hasShiftDown()){
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth3_shift0"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth3_shift1"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth3_shift2"));
                    tooltip.add(Text.translatable("tooltip.aristysa.wardenhearth3_shift3"));

                } else {
                    tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                }
                break;
            default:
                break;
        }


        super.appendTooltip(stack, world, tooltip, context);
    }
}
