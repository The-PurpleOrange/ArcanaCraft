package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.registries.RitualManager;
import com.tyhone.arcanacraft.api.registries.RitualRegistry;
import com.tyhone.arcanacraft.api.ritual.IRitual;
import com.tyhone.arcanacraft.api.util.ItemStackUtil;

import net.minecraft.item.ItemStack;

public class RecipeRitualCircle {
	String ritualName;
	
	//EnumRitual ritual;
	List<ItemStack> blockPosList;
	List<ItemStack> inputs;
	int middleInt = 0;

	public RecipeRitualCircle(String ritualName, ItemStack[] inputs, Object[] blockData){
		this.ritualName = ritualName;

		if(inputs != null){
			List<ItemStack> inputList = new ArrayList();
			for(ItemStack obj : inputs) {
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
					if(stringSize % 2 != 1){
						Arcanacraft.logger.error("Ritual Circle for " + ritualName + " is invalid");
					}else{
						this.middleInt = (int) Math.floor(stringSize / 2);
					}
				}
			}
			else if(blockData[i] instanceof Character){
				blockChar.add((Character) blockData[i]);
				i++;
				blockInput.add((ItemStack) blockData[i]);
			}
			else{throw new IllegalArgumentException("Invalid input");}
		}
		
		
		for(int f = 0; f< blockPlaceRow.size(); f++){
			String row = blockPlaceRow.get(f);
			char[] charRow = row.toCharArray();
			for(int j = 0; j< charRow.length; j++){
				if(charRow[j] == ' '){
					blockPosList.add(null);
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
		
		this.blockPosList = blockPosList;
	}
	
	
	
	public boolean matchesBlocks(List<ItemStack> blocks) {
		List<ItemStack> inputsRequired = new ArrayList(blockPosList);
		Arcanacraft.logger.info(inputsRequired.size()+":"+blocks.size());
		boolean flag = true;
		for(int i = 0; i < inputsRequired.size(); i++){
			if(areBlocksEqual(blocks.get(i), inputsRequired.get(i))==false){
				flag = false;
			}
		}
		return flag;
	}
	
	
	
	public boolean doInputsMatch(ArrayList<ItemStack> inputActual) {
		ArrayList<ItemStack> inputsRequired = (ArrayList<ItemStack>) new ArrayList(inputs).clone();

		
		for(int i = 0; i < inputActual.size(); i++) {
			ItemStack stack = inputActual.get(i);
			if(stack == null)
				break;

			int stackI = -1;

			for(int j = 0; j < inputsRequired.size(); j++) {
				Object input = inputsRequired.get(j);
				if(ItemStackUtil.simpleAreStackSizeEqual((ItemStack) input, stack)) {
					stackI = j;
					break;
				}
			}

			if(stackI != -1)
				inputsRequired.remove(stackI);
			else return false;
		}

		return inputsRequired.isEmpty();
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
	
	
	public static RecipeRitualCircle getRecipe(ArrayList<ItemStack> itemStacks, List<ItemStack> blockStacks){
		
		for(RecipeRitualCircle recipe : ArcanacraftRitualManager.getRitualCircleRecipes()){
			if(recipe.matchesBlocks(blockStacks)){
				if(itemStacks!=null && recipe.doInputsMatch(itemStacks)){
					return recipe;
				}
			}
		}
		return null;
	}
	
	
	public String getRitualName(){
		return this.ritualName;
	}
	
	public List<ItemStack> getBlockRequirements() {
		return new ArrayList(blockPosList);
	}

	public List<ItemStack> getItemRequirements() {
		if(inputs!=null){
			return new ArrayList(inputs);
		}
		return null;
	}
	
	public int getMiddleInt(){
		return middleInt;
	}



	public Class<? extends IRitual> getRitual(String ritualName) {
		for(RitualManager ritual : RitualRegistry.getRitualList()){
			if(ritualName == ritual.getRitualName()){
				return ritual.GetRitualClass();
			}
		}
		return null;
	}
}
