package com.tyhone.arcanacraft.common.util;

import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;

public class ItemStackUtil {

	//Compare for OreStack/ItemStack
	public static boolean simpleAreStacksEqual(Object stack1, ItemStack stack2, boolean ingoreMeta){
		if(stack1 instanceof ItemStack){
			return simpleAreItemStacksEqual((ItemStack) stack1, stack2, ingoreMeta);
		}
		else if(stack1 instanceof OreStack){
			return simpleAreOreStacksEqual((OreStack) stack1, stack2);
		}
		return false;
	}
	
	public static boolean simpleAreStacksEqual(Object stack1, ItemStack stack2){

		return simpleAreStacksEqual(stack1, stack2, false);
	}
	
	//Compare for OreStack/ItemStack with StackSize
	public static boolean simpleAreStackSizeEqual(Object stack1, ItemStack stack2, boolean ignoreMeta) {
		if(stack1 instanceof ItemStack){
			return simpleAreItemStackSizeEqual((ItemStack) stack1, stack2, ignoreMeta);
		}
		else if(stack1 instanceof OreStack){
			return simpleAreOreStackSizeEqual((OreStack) stack1, stack2);
		}
		return false;
	}
	
	public static boolean simpleAreStackSizeEqual(Object stack1, ItemStack stack2) {
		return simpleAreStackSizeEqual(stack1, stack2, false);
	}
	
	//Compare ItemStack
	public static boolean simpleAreItemStacksEqual(ItemStack stack1, ItemStack stack2, boolean ingoreMeta){
		if(stack1.getItem() == stack2.getItem()){
			if(ingoreMeta){
				return true;
			}else{
				if(stack1.getMetadata() == stack2.getMetadata()){
					return true;
				}
			}
		}
		return false;
	}

	//Compare ItemStack with Size
	public static boolean simpleAreItemStackSizeEqual(ItemStack stack1, ItemStack stack2, boolean ingoreMeta) {
		if(simpleAreItemStacksEqual(stack1, stack2, ingoreMeta)){
			return stack1.getCount() == stack2.getCount();
		}
		return false;
	}
	

	//Compare OreStack
	public static boolean simpleAreOreStacksEqual(OreStack stack1, ItemStack stack2){
		return OreStack.isItemStackFromOre(stack1, stack2);
	}

	//Compare OreStack with Size
	public static boolean simpleAreOreStackSizeEqual(OreStack stack1, ItemStack stack2) {
		if(simpleAreOreStacksEqual(stack1, stack2)){
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
						ItemStack tempItemStack = itemStacks.get(i).copy();
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
	
	public static boolean isItemStackBucket(Object stack) {
		if(stack instanceof ItemStack) {
			ItemStack itemStack = (ItemStack) stack;
			return isItemStackBucket(itemStack);
		}
		return false;
	}
	
	public static boolean isItemStackBucket(ItemStack stack) {
		if (stack.getItem() == Items.LAVA_BUCKET || 
			stack.getItem() == Items.WATER_BUCKET || 
			stack.getItem() == Items.MILK_BUCKET || 
			stack.getItem() == UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FluidRegistry.WATER).getItem())
		{
			return true;
		}
		return false;
	}
}
