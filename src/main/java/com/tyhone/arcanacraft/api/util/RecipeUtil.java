package com.tyhone.arcanacraft.api.util;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.handler.OreDictionaryHandler;

import net.minecraft.item.ItemStack;

public class RecipeUtil {
	public static boolean doInputsMatch(ArrayList<Object> inputRecipe, ArrayList<ItemStack> inputActual) {
		ArrayList<Object> inputsRequired = (ArrayList<Object>) new ArrayList(inputRecipe).clone();

		
		for(int i = 0; i < inputActual.size(); i++) {
			ItemStack actual = inputActual.get(i);
			if(actual.isEmpty()){
				break;
			}

			int stackI = -1;

			for(int j = 0; j < inputsRequired.size(); j++) {
				Object required = inputsRequired.get(j);
				if(required instanceof ItemStack){
					if(ItemStackUtil.simpleAreStackSizeEqual((ItemStack) required, actual)) {
						stackI = j;
						break;
					}
				}
				else if(required instanceof String){
					String[] parts = ((String) required).split(":");
					int count = Integer.valueOf(parts[1]);
					
					if(count==actual.getCount()){
						List<ItemStack> ores = OreDictionaryHandler.getOreDictionaryEntries(parts[0]);
						for(ItemStack ore : ores){
							Arcanacraft.log(ore.getDisplayName());
							Arcanacraft.log(actual.getDisplayName());
							if(ItemStackUtil.simpleAreStacksEqual(ore, actual)){
								stackI = j;
								break;
							}
						}
					}
				}
				else{
					Arcanacraft.logger.error("Arcanacraft Recipe Util passed Invaild Recipe - Major error, please alert mod author with actions done to provoke this message");
					return false;
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
