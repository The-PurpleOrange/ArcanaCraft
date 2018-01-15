package com.tyhone.arcanacraft.common.util;

import java.util.ArrayList;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.handler.OreDictionaryHandler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WildStack {
	public static ItemStack W(Object input, int size){
		ItemStack stack = ItemStack.EMPTY;
		if(input instanceof ItemStack){
			return (ItemStack) input;
		}else if(input instanceof Block){
			stack = new ItemStack(Item.getItemFromBlock((Block) input), size, 32767);
		}else if(input instanceof Item){
			stack = new ItemStack((Item) input, size, 32767);
		}else{
			Arcanacraft.logger.error("Error register WildStack: " + input.toString());
			return ItemStack.EMPTY;
		}
		
		return stack;
	}

	public static ArrayList<Object> objectListToItemStackOrOreStack(Object[] inputObjects) {
		ArrayList<Object> objectList = new ArrayList<Object>();
		for(Object input : inputObjects){
			objectList.add(objectToItemStackOrOreStack(input));
		}
		return objectList;
	}

	public static Object objectToItemStackOrOreStack(Object input) {
		Object stack = ItemStack.EMPTY;
		if(input instanceof ItemStack){
			return input;
		}else if(input instanceof Block){
			return new ItemStack(Item.getItemFromBlock((Block) input), 1, 32767);
		}else if(input instanceof Item){
			return new ItemStack((Item) input, 1, 32767);
		}else if(input instanceof OreStack){
			return input;
		}else if(input instanceof String){
			return new OreStack(((String) input), 1);
		}else{
			return null;
		}
	}
}
