 package com.tyhone.arcanacraft.common.tileentity.base;

import com.tyhone.arcanacraft.common.util.NBTUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;

public class ModTileEntitySingleInventoryBase extends ModTileEntityBase{

	private ItemStack stack = ItemStack.EMPTY;
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void setStack(ItemStack stack) {
		this.stack = stack;
		markForClean();
	}

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        stack = NBTUtils.readItemCompound("item", compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTUtils.writeItemCompound("item", compound, stack);
        return compound;
    }
}
