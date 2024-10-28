package net.earlyalpha.aristysa.block.entity;

import net.earlyalpha.aristysa.item.ModItems;
import net.earlyalpha.aristysa.screen.CraftStationScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
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

public class CraftStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 =  1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int INPUT_SLOT_4 = 3;
    private static final int OUTPUT_SLOT = 4;

    protected final PropertyDelegate propertyDelegate;

    public CraftStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRAFT_STATION_BLOCK_ENTITY, pos, state);
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
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Craft Station");
    }


    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CraftStationScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        if (isOutputSlotEmptyOrReceivable()) {
            if(this.hasRecipeEnderEyeTier1()){
                markDirty( world ,pos ,state);
                this.craftItemEnderEyeTier1();
            }else if (this.hasRecipeEnderEyeTier2()) {
                markDirty( world ,pos ,state);
                this.craftItemEnderEyeTier2();
            }else if (this.hasRecipeEnderEyeTier3()){
                markDirty( world ,pos ,state);
                this.craftItemEnderEyeTier3();
            }else if(this.hasRecipeGolemArmTier1()){
                markDirty( world ,pos ,state);
                this.craftItemGolemArmTier1();
            }else if (this.hasRecipeGolemArmTier2()) {
                markDirty( world ,pos ,state);
                this.craftItemGolemArmTier2();
            }else if (this.hasRecipeGolemArmTier3()){
                markDirty( world ,pos ,state);
                this.craftItemGolemArmTier3();
            }

        }


    }
    private void craftItemGolemArmTier1() {
        this.removeStack(INPUT_SLOT_1,1);
        this.removeStack(INPUT_SLOT_2,1);
        this.removeStack(INPUT_SLOT_3,1);
        this.removeStack(INPUT_SLOT_4,1);
        ItemStack result = new ItemStack(ModItems.GOLEMARM_1);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount()+ result.getCount()));

    }
    private void craftItemGolemArmTier2() {
        this.removeStack(INPUT_SLOT_1,1);
        this.removeStack(INPUT_SLOT_2,1);
        this.removeStack(INPUT_SLOT_3,1);
        this.removeStack(INPUT_SLOT_4,1);
        ItemStack result = new ItemStack(ModItems.GOLEMARM_2);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount()+ result.getCount()));

    }
    private void craftItemGolemArmTier3() {
        this.removeStack(INPUT_SLOT_1,1);
        this.removeStack(INPUT_SLOT_2,1);
        this.removeStack(INPUT_SLOT_3,1);
        this.removeStack(INPUT_SLOT_4,1);
        ItemStack result = new ItemStack(ModItems.GOLEMARM_3);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount()+ result.getCount()));

    }

    private boolean hasRecipeGolemArmTier1() {
        ItemStack result = new ItemStack(ModItems.GOLEMARM_1);
        boolean hasInput = getStack(INPUT_SLOT_1).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_2).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_3).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_4).getItem() == ModItems.RAW_LEAD;
        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }
    private boolean hasRecipeGolemArmTier2() {
        ItemStack result = new ItemStack(ModItems.GOLEMARM_2);
        boolean hasInput = getStack(INPUT_SLOT_1).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_2).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_3).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_4).getItem() == ModItems.GOLEMARM_1;
        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }
    private boolean hasRecipeGolemArmTier3() {
        ItemStack result = new ItemStack(ModItems.GOLEMARM_3);
        boolean hasInput = getStack(INPUT_SLOT_1).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_2).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_3).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_4).getItem() == ModItems.GOLEMARM_2;
        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }


    private void craftItemEnderEyeTier1() {
        this.removeStack(INPUT_SLOT_1,1);
        this.removeStack(INPUT_SLOT_2,1);
        this.removeStack(INPUT_SLOT_3,1);
        this.removeStack(INPUT_SLOT_4,1);
        ItemStack result = new ItemStack(ModItems.ENDEREYE_1);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount()+ result.getCount()));

    }
    private void craftItemEnderEyeTier2() {
        this.removeStack(INPUT_SLOT_1,1);
        this.removeStack(INPUT_SLOT_2,1);
        this.removeStack(INPUT_SLOT_3,1);
        this.removeStack(INPUT_SLOT_4,1);
        ItemStack result = new ItemStack(ModItems.ENDEREYE_2);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount()+ result.getCount()));

    }
    private void craftItemEnderEyeTier3() {
        this.removeStack(INPUT_SLOT_1,1);
        this.removeStack(INPUT_SLOT_2,1);
        this.removeStack(INPUT_SLOT_3,1);
        this.removeStack(INPUT_SLOT_4,1);
        ItemStack result = new ItemStack(ModItems.ENDEREYE_3);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount()+ result.getCount()));

    }

    private boolean hasRecipeEnderEyeTier1() {
        ItemStack result = new ItemStack(ModItems.ENDEREYE_1);
        boolean hasInput = getStack(INPUT_SLOT_1).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_2).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_3).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_4).getItem() == ModItems.LEAD_INGOT;
        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }
    private boolean hasRecipeEnderEyeTier2() {
        ItemStack result = new ItemStack(ModItems.ENDEREYE_2);
        boolean hasInput = getStack(INPUT_SLOT_1).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_2).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_3).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_4).getItem() == ModItems.ENDEREYE_1;
        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }
    private boolean hasRecipeEnderEyeTier3() {
        ItemStack result = new ItemStack(ModItems.ENDEREYE_3);
        boolean hasInput = getStack(INPUT_SLOT_1).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_2).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_3).getItem() == ModItems.LEAD_INGOT && getStack(INPUT_SLOT_4).getItem() == ModItems.ENDEREYE_2;
        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
}
