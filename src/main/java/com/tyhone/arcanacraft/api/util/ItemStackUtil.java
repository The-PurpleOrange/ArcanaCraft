package com.tyhone.arcanacraft.api.util;

import java.util.List;

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
	
	
	public static List<ItemStack> compactItems(List<ItemStack> itemStacks){
		//List<ItemStack> tempItemStackList;
		for(int i = 0; i<itemStacks.size(); i++){
			for(int j = 0; j<itemStacks.size(); j++){
				if(i!=j){
					if(simpleAreStacksEqual(itemStacks.get(i), itemStacks.get(j))){
						ItemStack tempItemStack = itemStacks.get(i);
						tempItemStack.setCount(itemStacks.get(i).getCount() + itemStacks.get(j).getCount());
						//tempItemStackList.add(tempItemStackList);
						itemStacks.set(i, tempItemStack);
						itemStacks.remove(j);
						j--;
					}
				}
			}
		}
		return itemStacks;
	}
}
