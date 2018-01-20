package com.tyhone.arcanacraft.common.util;

import java.util.ArrayList;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.api.util.TinktureStackUtil;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

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
	
	
	public static boolean compareObjectType(Object obj1, Object obj2){

		if(obj1 == null && obj2 == null){
			return true;
		}
		if(obj1 instanceof ItemStack && obj2 instanceof ItemStack){
			if(ItemStackUtil.simpleAreItemStackSizeEqual((ItemStack) obj1, (ItemStack) obj2, false)){
				return true;
			}
			return false;
		}
		if(obj1 instanceof OreStack && obj2 instanceof ItemStack){
			if(ItemStackUtil.simpleAreOreStackSizeEqual((OreStack) obj1, (ItemStack) obj2)){
				return true;
			}
			return false;
		}
		if(obj2 instanceof OreStack && obj1 instanceof ItemStack){
			if(ItemStackUtil.simpleAreOreStackSizeEqual((OreStack) obj2, (ItemStack) obj1)){
				return true;
			}
			return false;
		}
		if(obj1 instanceof NBTItemStack && obj2 instanceof NBTItemStack){
			if(ItemStackUtil.simpleAreItemStackSizeEqual(((NBTItemStack) obj1).getStack(), ((NBTItemStack) obj2).getStack(), false)){
				if(((NBTItemStack) obj1).compareNBT(((NBTItemStack) obj1).getNBT())){
					return true;
				}
			}
			return false;
		}
		if(obj1 instanceof TinktureStack && obj2 instanceof TinktureStack){
			if(TinktureStackUtil.simpleAreStacksEqual((TinktureStack) obj1, (TinktureStack) obj2)){
				return true;
			}
			return false;
		}
		if(obj1 instanceof FluidStack && obj2 instanceof FluidStack){
			if(((FluidStack) obj1).isFluidStackIdentical(((FluidStack) obj2))){
				return true;
			}
			return false;
		}
		
		return false;
	}

	
	//OBJECT LIST TO ITEM OR ORESTACK
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
		}else if(input instanceof NBTItemStack){
			return input;
		}else{
			return null;
		}
	}
	
	
	
	//OBJECT LIST TO ANYTHING
	public static ArrayList<Object> objectListToAnything(Object[] inputObjects) {
		ArrayList<Object> objectList = new ArrayList<Object>();
		for(Object input : inputObjects){
			objectList.add(objectToAnything(input));
		}
		return objectList;
	}

	public static Object objectToAnything(Object input) {
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
		}else if(input instanceof TinktureStack){
			return input;
		}else if(input instanceof NBTItemStack){
			return input;
		}else if(input instanceof FluidStack){
			return input;
		}else if(input instanceof Fluid){
			return new FluidStack((Fluid) input, 1000);
		}else{
			return null;
		}
	} 
}
