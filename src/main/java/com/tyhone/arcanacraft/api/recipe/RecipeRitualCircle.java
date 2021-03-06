package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.common.handler.OreDictionaryHandler;
import com.tyhone.arcanacraft.common.util.OreStack;

import net.minecraft.item.ItemStack;

public class RecipeRitualCircle {
	Ritual ritual;
	
	//EnumRitual ritual;
	List<ItemStack> blockPosList;
	List<Object> inputs;
	int middleInt = 0;
	int width;

	public RecipeRitualCircle(Ritual ritual, Object[] inputs, Object[] blockData){
		this.ritual = ritual;

		if(inputs != null){
			List<Object> inputList = new ArrayList();
			for(Object obj : inputs) {
				inputList.add(obj);
			}
			this.inputs = inputList;
		}
		else{
			this.inputs = null;
		}

		List<String> blockPlaceRow = new ArrayList();
		List<Character> blockChar = new ArrayList();
		List<ItemStack> blockInput = new ArrayList();

		List<ItemStack> blockPosList = new ArrayList();
		
		
		for(int i = 0; i < blockData.length;i++){
			if(blockData[i] instanceof String){
				blockPlaceRow.add((String) blockData[i]);
				if(middleInt == 0){
					int stringSize = ((String) blockData[i]).length();
					this.width = stringSize;
					if(stringSize % 2 != 1){
						Arcanacraft.logger.error("Ritual Circle for " + ritual.getDisplayName() + " is invalid");
					}else{
						this.middleInt = (int) Math.floor(stringSize / 2);
					}
				}
			}
			else if(blockData[i] instanceof Character){
				blockChar.add((Character) blockData[i]);
				i++;
				if(blockData[i] instanceof OreStack) {
					blockInput.add(OreStack.getOreDictionaryEntryForOreStack((OreStack) blockData[i]));
				}
				else {
					blockInput.add((ItemStack) blockData[i]);
				}
			}
			else{throw new IllegalArgumentException("Invalid input");}
		}
		
		
		for(int f = 0; f< blockPlaceRow.size(); f++){
			String row = blockPlaceRow.get(f);
			char[] charRow = row.toCharArray();
			for(int j = 0; j< charRow.length; j++){
				if(charRow[j] == ' '){
					blockPosList.add(ItemStack.EMPTY);
				}
				else {
					for(int k = 0; k < blockChar.size(); k++){
						if(charRow[j] == blockChar.get(k)){
							blockPosList.add(blockInput.get(k));
						}
					}
				}
			}
		}

		if(blockPosList.size() != ritual.getRitualType().getRitualRecipePlaceOrder().length){
			Arcanacraft.logger.error("Ritual Circle for " + ritual.getDisplayName() + " is invalid, and will crash the game, it is:should be " + blockPosList.size() + ":" + ritual.getRitualType().getRitualRecipePlaceOrder().length);
		}
		
		this.blockPosList = blockPosList;
	}
	
	
	
	public boolean matchesBlocks(List<ItemStack> blocks) {
		List<ItemStack> inputsRequired = new ArrayList(blockPosList);
		boolean flag = true; 
		for(int i = 0; i < inputsRequired.size(); i++){
			if(inputsRequired.get(i) != null){
				if(!areBlocksEqual(blocks.get(i), inputsRequired.get(i))){
					flag = false;
				}
			}
		}
		return flag;
	}

	boolean areBlocksEqual(ItemStack stack1, ItemStack stack2) {
		if(stack1 == null && stack2 == null){
			return true;
		}
		else if(stack1 == null || stack2 == null){
			return false;
		}
		else if(stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage()){
			return true;
		}
		return false;
	}
	
	
	public List<ItemStack> getBlockRequirements() {
		return new ArrayList(blockPosList);
	}

	public List<Object> getItemRequirements() {
		if(inputs!=null){
			return new ArrayList(inputs);
		}
		return null;
	}
	
	public List<ItemStack> getItemStackRequirements(){
		List<ItemStack> itemStacks = new ArrayList<>();
		for(Object object : getItemRequirements()){
			ItemStack itemStack = ItemStack.EMPTY;
			if(object instanceof OreStack){
				itemStack = OreDictionaryHandler.getOreDictionaryEntry(((OreStack)object).getOre());
			}else if(object instanceof ItemStack){
				itemStack = (ItemStack) object;
			}
			itemStacks.add(itemStack);
		}
		return itemStacks;
	}
	
	public int getMiddleInt(){
		return middleInt;
	}

	public Ritual getRitual() {
		return ritual;
	}

	public int getWidth() {
		return width;
	}
}
