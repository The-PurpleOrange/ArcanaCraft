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
	
	public static ItemStack ObjectToInput(Object input, int size){
		ItemStack stack = ItemStack.EMPTY;
		if(input instanceof ItemStack){
			return (ItemStack) input;
		}else if(input instanceof Block){
			stack = new ItemStack(Item.getItemFromBlock((Block) input), size, 32767);
		}else if(input instanceof Item){
			stack = new ItemStack((Item) input, size, 32767);
		}else if(input instanceof String){
			stack = OreDictionaryHandler.getOreDictionaryEntry((String) input);
		}else{
			return ItemStack.EMPTY;
		}
		
		return stack;
	}
	
	public static ItemStack ObjectToInput(Object input){
		return ObjectToInput(input, 1);
	}
	
	public static ArrayList<ItemStack> InitObjectListToItemStackList(ArrayList<Object> inputObjects, int maxCount){
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		for(Object inputObject : inputObjects){
			
			ItemStack input = ObjectToInput(inputObject);
			if(input.isEmpty()){
				return null;
			}
			if(input.getCount() > maxCount){
				input.setCount(maxCount);
			}
			inputs.add(input);
		}
		return inputs;
	}
	
	public static ArrayList<ItemStack> InitObjectListToItemStackList(ArrayList<Object> inputObjects){
		return InitObjectListToItemStackList(inputObjects, 64);
	}
	
	public static ArrayList<ItemStack> InitObjectListToItemStackList(Object[] inputObjects, int maxCount){
		ArrayList<Object> objectList = new ArrayList<Object>();
		for(Object object : inputObjects){
			objectList.add(object);
		}
		
		return InitObjectListToItemStackList(objectList, maxCount);
	}
	
	public static ArrayList<ItemStack> InitObjectListToItemStackList(Object[] inputObjects){
		return InitObjectListToItemStackList(inputObjects, 64);
	}

	public static ArrayList<Object> InitObjectListToItemStackOrStringList(Object[] inputObjects) {
		ArrayList<Object> objectList = new ArrayList<Object>();
		for(Object input : inputObjects){
			Object stack = ItemStack.EMPTY;
			if(input instanceof ItemStack){
				stack =  input;
			}else if(input instanceof Block){
				stack = new ItemStack(Item.getItemFromBlock((Block) input), 1, 32767);
			}else if(input instanceof Item){
				stack = new ItemStack((Item) input, 1, 32767);
			}else if(input instanceof String){
				//String[] parts = ((String) input).split(":");
				stack = input;
				
				//stack = OreDictionaryHandler.getOreDictionaryEntry((String) input);
			}else{
				return null;
			}
			objectList.add(stack);
		}
		return objectList;
	}
}
