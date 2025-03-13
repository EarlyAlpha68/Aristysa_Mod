package net.earlyalpha.aristysa.mixin;

import net.earlyalpha.aristysa.util.EarlyUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(MilkBucketItem.class)
public class MilkBucketMixin {

    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void setBoolTrue(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
        EarlyUtil.drinkingMilkORTotemUse.put(user,true);
    }

    @Inject(method = "finishUsing", at = @At("RETURN"))
    private void resetBool(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
        EarlyUtil.drinkingMilkORTotemUse.put(user,false);
    }
}