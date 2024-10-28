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

public class EnderEyeItem extends Item {
    protected int tier;
    protected String key = "enderEyeTier";
    public EnderEyeItem(Settings settings, int tier){
        super(settings);
        this.tier = tier;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        boolean sync = false;
        if (!world.isClient()) {
            if (!(((IEntityDataSaver) player).getPersistentData().getInt(key) == this.tier)) {
                alreadyHasIt(player,this.tier);
                AddTier.addTier(((IEntityDataSaver) player), this.tier,"enderEyeTier" );
                player.sendMessage(Text.literal("You successfully implant yourself an Ender Eye tier " + ((IEntityDataSaver) player).getPersistentData().getInt(key)));
                sync = true;
            } else {
                player.sendMessage(Text.literal("You already have an Ender Eye tier " + ((IEntityDataSaver) player).getPersistentData().getInt(key)));
                sync = false;
            }
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeString(key);
            buffer.writeInt(((IEntityDataSaver) player).getPersistentData().getInt(key));
            ServerPlayNetworking.send((ServerPlayerEntity) player, NetworkingsMessages.NBT_TAGS2C_ID_SYNC, buffer);
        }
        if (sync) {
            ItemStack itemStack = player.getStackInHand(hand);
            itemStack.decrement(1);
            player.setStackInHand(hand, itemStack);

        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
    private static void alreadyHasIt(PlayerEntity player, int tiers){
        ItemStack oneItemStack = new ItemStack(ModItems.ENDEREYE_1, 1);
        ItemStack twoItemStack = new ItemStack(ModItems.ENDEREYE_2, 1);
        ItemStack threeItemStack = new ItemStack(ModItems.ENDEREYE_3, 1);
        if ((((IEntityDataSaver) player).getPersistentData().getInt("enderEyeTier") == 1 && (!(tiers == 1)))) {
            if (!player.getInventory().insertStack(oneItemStack)) {
                player.dropItem(oneItemStack, false);
            }
        }
        if ((((IEntityDataSaver) player).getPersistentData().getInt("enderEyeTier") == 2 && (!(tiers == 2)))) {
            if (!player.getInventory().insertStack(twoItemStack)) {
                player.dropItem(twoItemStack, false);
            }
        }
        if ((((IEntityDataSaver) player).getPersistentData().getInt("enderEyeTier") == 3 && (!(tiers == 3)))) {
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
                tooltip.add(Text.translatable("tooltip.aristysa.endereye1_shift0"));
                tooltip.add(Text.translatable("tooltip.aristysa.endereye1_shift1"));
                tooltip.add(Text.translatable("tooltip.aristysa.endereye1_shift2"));

            } else {
                tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
            }
                break;
            case 2:
                if (Screen.hasShiftDown()){
                    tooltip.add(Text.translatable("tooltip.aristysa.endereye2_shift0"));
                    tooltip.add(Text.translatable("tooltip.aristysa.endereye2_shift1"));
                    tooltip.add(Text.translatable("tooltip.aristysa.endereye2_shift2"));

                } else {
                    tooltip.add(Text.translatable("tooltip.aristysa.hold_shift"));
                }
                break;
            case 3:
                if (Screen.hasShiftDown()){
                    tooltip.add(Text.translatable("tooltip.aristysa.endereye3_shift0"));
                    tooltip.add(Text.translatable("tooltip.aristysa.endereye3_shift1"));
                    tooltip.add(Text.translatable("tooltip.aristysa.endereye3_shift2"));

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

