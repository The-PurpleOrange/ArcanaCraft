package com.tyhone.arcanacraft.api.tinkture;

import net.minecraft.item.ItemStack;

public interface IEssenceVessel {
	
	public int getFluidAmount(ItemStack stack);

	//Returns a string name of the fluid contained
	public TinktureType getFluidType(ItemStack stack);
}
