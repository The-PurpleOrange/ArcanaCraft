package com.tyhone.arcanacraft.api.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class RecipeUtil {
	public static boolean doInputsMatch(ArrayList<ItemStack> inputRecipe, ArrayList<ItemStack> inputActual) {
		ArrayList<ItemStack> inputsRequired = (ArrayList<ItemStack>) new ArrayList(inputRecipe).clone();

		
		for(int i = 0; i < inputActual.size(); i++) {
			ItemStack actual = inputActual.get(i);
			if(actual.isEmpty()){
				break;
			}

			int stackI = -1;

			for(int j = 0; j < inputsRequired.size(); j++) {
				ItemStack required = inputsRequired.get(j);
				if(ItemStackUtil.simpleAreStackSizeEqual(required, actual)) {
					stackI = j;
					break;
				}
			}

			if(stackI != -1)
				inputsRequired.remove(stackI);
			else{
				return false;
			}
		}

		return inputsRequired.isEmpty();
	}
}
