package com.tyhone.arcanacraft.common.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtils {

	public static ItemStack readItemCompound(String tag, NBTTagCompound compound){
        if (compound.hasKey(tag)) {
        	if(!new ItemStack(compound.getCompoundTag(tag)).isEmpty()){
                return new ItemStack(compound.getCompoundTag(tag));
        	}
        }
        return ItemStack.EMPTY;
	}
	
	public static NBTTagCompound writeItemCompound(String tag, NBTTagCompound compound, ItemStack itemStack){
		if (!itemStack.isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            itemStack.writeToNBT(tagCompound);
            compound.setTag(tag, tagCompound);
        }
		return compound;
	}
	
}
