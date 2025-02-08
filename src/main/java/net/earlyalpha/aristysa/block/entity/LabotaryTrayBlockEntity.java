package net.earlyalpha.aristysa.block.entity;

import dev.architectury.platform.Mod;
import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.screen.FusionCrafterScreenHandler;
import net.earlyalpha.aristysa.screen.LabotaryTrayScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class LabotaryTrayBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3,ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int SYRINGE_SLOT = 2;

    public LabotaryTrayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LABOTARY_TRAY_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return 0;
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 0;
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

    public void tick(World world1, BlockPos pos, BlockState state1) {
        if(canInsertIntoOutputSlot() && hasRecipe()){
            craft();
        }
    }

    private boolean canInsertIntoOutputSlot() {
        return true;
    }

    private boolean hasRecipe() {
        return this.getStack(0) == null;
    }



    private void craft() {
        this.setStack(INPUT_SLOT_1,ItemStack.EMPTY);
        this.setStack(INPUT_SLOT_2,ItemStack.EMPTY);
        this.setStack(SYRINGE_SLOT, ModItems.CRIMSON_LACE.getDefaultStack());
    }

}
