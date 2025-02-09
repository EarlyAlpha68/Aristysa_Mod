package net.earlyalpha.aristysa.block.entity;

import net.earlyalpha.aristysa.recipe.LabotaryTrayRecipe;
import net.earlyalpha.aristysa.screen.LabotaryTrayScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LabotaryTrayBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3,ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int SYRINGE_SLOT = 2;

    public LabotaryTrayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LABOTARY_TRAY_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> LabotaryTrayBlockEntity.this.progress;
                    case 1 -> LabotaryTrayBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: LabotaryTrayBlockEntity.this.progress = value;
                    case 1: LabotaryTrayBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Labotary Tray");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new LabotaryTrayScreenHandler(syncId,playerInventory,this,propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,inventory);
        super.readNbt(nbt);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(canInsertIntoOutputSlot() && hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world,pos,state);
            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }
    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean canInsertIntoOutputSlot() {
        return this.getStack(SYRINGE_SLOT).isEmpty() ||
                this.getStack(SYRINGE_SLOT).getCount() < this.getStack(SYRINGE_SLOT).getMaxCount();
    }

    private boolean hasRecipe() {
        Optional<LabotaryTrayRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().getOutput(null);


        return canInsertAmountIntoOutputSlot(output.getCount())
                && canInsertItemIntoOutputSlot(output);
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<LabotaryTrayRecipe> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT_1,1);
        this.removeStack(INPUT_SLOT_2,1);

        this.setStack(SYRINGE_SLOT,new ItemStack(recipe.get().getOutput(null).getItem(),
                this.getStack(SYRINGE_SLOT).getCount() + recipe.get().getOutput(null).getCount()));
    }
    private Optional<LabotaryTrayRecipe> getCurrentRecipe() {
        SimpleInventory inventory = new SimpleInventory((this.size()));
        for (int i = 0; i < this.size(); i++) {
            inventory.setStack(i , this.getStack(i));
        }
        return this.getWorld().getRecipeManager().getFirstMatch(LabotaryTrayRecipe.Type.INSTANCE, inventory,this.getWorld());
    }
    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(SYRINGE_SLOT).isEmpty() || this.getStack(SYRINGE_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.getStack(SYRINGE_SLOT).getMaxCount() >= this.getStack(SYRINGE_SLOT).getCount() + count;
    }

}
