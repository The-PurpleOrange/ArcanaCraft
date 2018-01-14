package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.util.RecipeUtil;

import net.minecraft.item.ItemStack;

public class RecipeAlchemicArray {
	
	ItemStack output;
	ArrayList<ItemStack> inputs;
	
	public RecipeAlchemicArray(ItemStack output, ArrayList<ItemStack> inputs){
		
		if(inputs == null){
			Arcanacraft.logger.error("Invalid Alchemic Array recipe added for: " + output);
		}
		
		this.output = output;

		this.inputs = (ArrayList<ItemStack>) inputs.clone();
	}

	public ArrayList<ItemStack> getInputs() {
		return new ArrayList(inputs);
	}

	public ItemStack getOutput() {
		return this.output;
	}
	
	public static RecipeAlchemicArray getRecipe(ArrayList<ItemStack> inputs){
		for(RecipeAlchemicArray recipe : ArcanacraftCraftingManager.getAlchemicArrayRecipes()){
			if(RecipeUtil.doInputsMatch(recipe.getInputs(), inputs)){
				return recipe;
			}
		}
		return null;
	}
	
	/*public static boolean doInputsMatch(ArrayList<ItemStack> inputRecipe, ArrayList<ItemStack> inputActual) {
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
	}*/
}
