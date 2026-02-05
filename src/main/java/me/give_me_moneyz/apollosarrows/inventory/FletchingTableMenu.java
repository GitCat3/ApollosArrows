package me.give_me_moneyz.apollosarrows.inventory;

import me.give_me_moneyz.apollosarrows.recipe.FletchingRecipe;
import me.give_me_moneyz.apollosarrows.recipe.ModRecipes;
import me.give_me_moneyz.apollosarrows.registry.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;

public class FletchingTableMenu extends AbstractContainerMenu {
    private final SimpleContainer inputSlots = new SimpleContainer(3) {
        @Override
        public void setChanged() {
            super.setChanged();
            FletchingTableMenu.this.slotsChanged(this);
        }
    };
    private final ResultContainer resultSlot = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;

    public FletchingTableMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    public FletchingTableMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(ModMenuTypes.FLETCHING_TABLE.get(), containerId);
        this.access = access;
        this.player = playerInventory.player;

        this.addSlot(new Slot(inputSlots, 0, 26, 47));
        this.addSlot(new Slot(inputSlots, 1, 51, 47));
        this.addSlot(new Slot(inputSlots, 2, 76, 47));
        this.addSlot(new Slot(resultSlot, 3, 134, 47) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                FletchingTableMenu.this.onTake(player, stack);
            }
        });

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    protected void onTake(Player player, ItemStack stack) {
        stack.onCraftedBy(player.level(), player, stack.getCount());
        Optional<FletchingRecipe> recipe = player.level().getRecipeManager().getRecipeFor(ModRecipes.FLETCHING_TYPE.get(), inputSlots, player.level());

        if (recipe.isPresent()) {
            for (int i = 0; i < 3; i++) {
                inputSlots.removeItem(i, recipe.get().getIngredientsWithCounts().get(i).getCount());
            }
        } else {
            this.inputSlots.removeItem(0, 1);
            this.inputSlots.removeItem(1, 1);
            this.inputSlots.removeItem(2, 1);
        }
    }

    @Override
    public void slotsChanged(net.minecraft.world.Container container) {
        super.slotsChanged(container);
        if (container == this.inputSlots) {
            this.createResult();
        }
    }

    private void createResult() {
        if (!player.level().isClientSide) {
            Optional<FletchingRecipe> recipe = player.level().getRecipeManager().getRecipeFor(ModRecipes.FLETCHING_TYPE.get(), inputSlots, player.level());
            if (recipe.isPresent()) {
                this.resultSlot.setItem(0, recipe.get().assemble(inputSlots, player.level().registryAccess()));
            } else {
                this.resultSlot.setItem(0, ItemStack.EMPTY);
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 3) {
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 0 && index != 1 && index != 2) {
                if (index >= 4 && index < 40) {
                    if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, Blocks.FLETCHING_TABLE);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.clearContainer(player, this.inputSlots);
    }
}
