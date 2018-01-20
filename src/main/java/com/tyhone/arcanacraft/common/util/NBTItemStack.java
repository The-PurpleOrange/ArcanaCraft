package com.tyhone.arcanacraft.common.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTItemStack {

	ItemStack stack;
	NBTTagCompound nbt;
	
	NBTItemStack(ItemStack stack, NBTTagCompound nbt){
		this.stack = stack;
		this.nbt = nbt;
	}
	
	public ItemStack getStack(){
		return stack;
	}
	
	public NBTTagCompound getNBT(){
		return nbt;
	}
	
	public void setNBT(NBTTagCompound nbt){
		this.nbt = nbt;
	}
	
	public boolean compareNBT(NBTTagCompound newNBT){
		boolean matches = true;
		for(String nbtName : this.nbt.getKeySet()){
			if(this.nbt.hasKey(nbtName)  && newNBT.hasKey(nbtName) && this.nbt.getTag(nbtName).equals(newNBT.getTag(nbtName))){
				//It's true
			}
			else{
				matches = false;
			}
		}
		return matches;
	}
}
