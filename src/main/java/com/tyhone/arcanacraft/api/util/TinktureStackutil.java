package com.tyhone.arcanacraft.api.util;

import java.util.List;

import com.tyhone.arcanacraft.api.registries.TinktureStack;

import net.minecraft.item.ItemStack;

public class TinktureStackutil {
	
	public static boolean simpleAreStacksEqual(TinktureStack stack1, TinktureStack stack2){
		return stack1.getTinktureType() == stack2.getTinktureType();
	}
	
	public static boolean simpleIsAmountGreaterThan(TinktureStack testing, TinktureStack required) {
		if(simpleAreStacksEqual(testing, required)){
			return testing.getAmount() >= required.getAmount();
		}
		return false;
	}
}
