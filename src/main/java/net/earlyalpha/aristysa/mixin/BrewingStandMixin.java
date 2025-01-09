package net.earlyalpha.aristysa.mixin;

import net.earlyalpha.aristysa.datagen.tags.ModTags;
import net.fabricmc.fabric.mixin.item.BrewingStandBlockEntityMixin;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandMixin {
    @Inject(method = "isValid", at = @At(value = "HEAD"), cancellable = true)
    public void isValidInject(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(slot != 4 && slot != 3 && true)  {
            cir.setReturnValue(true);
        }
    }
}
