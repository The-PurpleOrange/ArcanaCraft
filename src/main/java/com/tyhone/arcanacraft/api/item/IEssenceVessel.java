package com.tyhone.arcanacraft.api.item;

import net.minecraft.item.ItemStack;

public interface IEssenceVessel {
	
	public double getFluid();

	//Returns a string name of the fluid contained
	public String getFluidType(ItemStack stack);
}
