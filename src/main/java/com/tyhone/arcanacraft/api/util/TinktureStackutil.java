package com.tyhone.arcanacraft.api.util;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.registries.TinktureManager;
import com.tyhone.arcanacraft.api.registries.TinktureStack;
import com.tyhone.arcanacraft.api.registries.TinktureType;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

import net.minecraft.item.ItemStack;

public class TinktureStackUtil {
	
	public static boolean simpleAreStacksEqual(TinktureStack stack1, TinktureStack stack2){
		return stack1.getTinktureType() == stack2.getTinktureType();
	}
	
	public static boolean simpleIsAmountGreaterThan(TinktureStack testing, TinktureStack required) {
		if(simpleAreStacksEqual(testing, required)){
			return testing.getAmount() >= required.getAmount();
		}
		return false;
	}

	public static ArrayList<TinktureStack> compactList(ArrayList<TinktureStack> list) {
		
		for(int i = 0; i<list.size(); i++){
			for(int j = 0; j<list.size(); j++){
				if(i!=j){
					if(simpleAreStacksEqual(list.get(i), list.get(j))){
						TinktureStack tempTinkStack = list.get(i);
						tempTinkStack.setAmount(list.get(i).getAmount() + list.get(j).getAmount());
						list.set(i, tempTinkStack);
						list.remove(j);
						j--;
					}
				}
			}
		}
		
		return list;
	}

	public static TinktureType getTinktureTypeFromString(String tinktureType){
		for(TinktureType type : TinktureManager.getTinktureTypes()){
			if(tinktureType == type.getTinktureName()){
				return type;
			}
		}
		return ModTinktureTypes.EMPTY;
	}
	
	
	/*public static List<ItemStack> compactItems(List<ItemStack> itemStacks){
		//List<ItemStack> tempItemStackList;
		for(int i = 0; i<itemStacks.size(); i++){
			for(int j = 0; j<itemStacks.size(); j++){
				if(i!=j){
					//if(simpleAreStacksEqual(itemStacks.get(i), itemStacks.get(j))){
						ItemStack tempItemStack = itemStacks.get(i);
						tempItemStack.setCount(itemStacks.get(i).getCount() + itemStacks.get(j).getCount());
						//tempItemStackList.add(tempItemStackList);
						itemStacks.set(i, tempItemStack);
						itemStacks.remove(j);
						j--;
					//}
				}
			}
		}
		return itemStacks;
	}*/
}
