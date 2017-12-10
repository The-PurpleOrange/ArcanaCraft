package com.tyhone.arcanacraft.api.util;

import net.minecraft.item.ItemStack;

public class ItemStackUtil {
	
	public static boolean simpleAreStacksEqual(ItemStack stack1, ItemStack stack2){
		return stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage();
	}
	
	public static boolean simpleAreStackSizeEqual(ItemStack stack1, ItemStack stack2) {
		if(simpleAreStacksEqual(stack1, stack2)){
			return stack1.getCount() == stack2.getCount();
		}
		return false;
	}
}
