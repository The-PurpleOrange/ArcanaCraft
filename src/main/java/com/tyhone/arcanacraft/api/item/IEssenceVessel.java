package com.tyhone.arcanacraft.api.item;

import com.tyhone.arcanacraft.api.tinkture.TinktureType;

import net.minecraft.item.ItemStack;

public interface IEssenceVessel {
	
	public int getFluidAmount(ItemStack stack);

	//Returns a string name of the fluid contained
	public TinktureType getFluidType(ItemStack stack);
}
