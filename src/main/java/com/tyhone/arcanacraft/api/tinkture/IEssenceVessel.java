package com.tyhone.arcanacraft.api.tinkture;

import net.minecraft.item.ItemStack;

public interface IEssenceVessel {

	public int getFluidAmount(ItemStack stack);
	public int getFluidAmount();

	//Returns a string name of the fluid contained
	public TinktureType getFluidType(ItemStack stack);
	public TinktureType getFluidType();
	
	public boolean canAcceptsFluid(ItemStack stack);
	public boolean addFluid(ItemStack stack, int amount);
	public int addFluidRemainder(ItemStack stack, int amount);
}
