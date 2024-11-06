package net.earlyalpha.aristysa.item.custom;

import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.util.EarlyUtil;
import net.earlyalpha.aristysa.util.IEntityDataSaver;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CyberLegItem extends Item {
    protected int tier;
    protected String key;
    public CyberLegItem(Settings settings, int tier,String key){
        super(settings);
        this.tier = tier;
        this.key = key;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        boolean sync = false;
        if (!world.isClient()) {
            if (!(((IEntityDataSaver) player).getPersistentData().getInt(this.key) == this.tier)) {
                alreadyHasIt(player,this.tier,this.key);
                EarlyUtil.addTier(((IEntityDataSaver) player), this.tier,key );
                player.sendMessage(Text.literal("You successfully implant yourself an " + EarlyUtil.getImplantName(this.key)+" tier " + ((IEntityDataSaver) player).getPersistentData().getInt(key)));
                sync = true;
            } else {
                player.sendMessage(Text.literal("You already have an " +EarlyUtil.getImplantName(this.key)+ " tier " + ((IEntityDataSaver) player).getPersistentData().getInt(key)));
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
    private static void alreadyHasIt(PlayerEntity player, int tiers, String key){
        int implantType = EarlyUtil.getImplantType(key);
        int implantTier = EarlyUtil.getImplantTier(player,key);
        if ((implantTier == 1 && (!(tiers == 1)))) {
            if (!player.getInventory().insertStack(EarlyUtil.CyberwareItemstack(implantType,1))) {
                player.dropItem(EarlyUtil.CyberwareItemstack(implantType,1), false);
            }
        }
        if ((implantTier == 2 && (!(tiers == 2)))) {
            if (!player.getInventory().insertStack(EarlyUtil.CyberwareItemstack(implantType,2))) {
                player.dropItem(EarlyUtil.CyberwareItemstack(implantType,2), false);
            }
        }
        if ((implantTier == 3 && (!(tiers == 3)))) {
            if (!player.getInventory().insertStack(EarlyUtil.CyberwareItemstack(implantType,3))) {
                player.dropItem(EarlyUtil.CyberwareItemstack(implantType,3), false);
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        EarlyUtil.toolTip(stack,world,tooltip,context,this.tier,EarlyUtil.getImplantType(this.key));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
