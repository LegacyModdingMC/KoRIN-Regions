package de.rinonline.korinrpg.Helper.Gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryRINPlayer implements IInventory {

    private final String name = "Custom Inventory";

    private final String tagName = "CustomInvTag";

    public static final int INV_SIZE = 6;

    ItemStack[] inventory = new ItemStack[6];

    public int getSizeInventory() {
        return this.inventory.length;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory[slot];
    }

    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize > amount) {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) setInventorySlotContents(slot, null);
            } else {
                setInventorySlotContents(slot, null);
            }
            markDirty();
        }
        return stack;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) setInventorySlotContents(slot, null);
        return stack;
    }

    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        this.inventory[slot] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
            itemstack.stackSize = getInventoryStackLimit();
        markDirty();
    }

    public String getInventoryName() {
        return "Custom Inventory";
    }

    public boolean hasCustomInventoryName() {
        return ("Custom Inventory".length() > 0);
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public void markDirty() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null && (getStackInSlot(i)).stackSize == 0) setInventorySlotContents(i, null);
        }
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return true;
    }

    public void writeToNBT(NBTTagCompound tagcompound) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(nbttagcompound1);
                nbttaglist.appendTag((NBTBase) nbttagcompound1);
            }
        }
        tagcompound.setTag("CustomInvTag", (NBTBase) nbttaglist);
    }

    public void readFromNBT(NBTTagCompound tagcompound) {
        NBTTagList items = tagcompound.getTagList("CustomInvTag", tagcompound.getId());
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = items.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");
            if (slot >= 0 && slot < getSizeInventory())
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
        }
    }
}
