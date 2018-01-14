package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.util.RecipeUtil;

import net.minecraft.item.ItemStack;

public class RecipeTransmutationAltar {
	
	ItemStack output;
	ArrayList<ItemStack> inputs;
	
	public RecipeTransmutationAltar(ItemStack output, ArrayList<ItemStack> inputs){
		
		this.output = output;

		this.inputs = (ArrayList<ItemStack>) inputs.clone();
	}

	public ArrayList<ItemStack> getInputs() {
		return new ArrayList(inputs);
	}

	public ItemStack getOutput() {
		return this.output;
	}
	
	public static RecipeTransmutationAltar getRecipe(ArrayList<ItemStack> inputs){
		for(RecipeTransmutationAltar recipe : ArcanacraftCraftingManager.getTransmutationAltarRecipes()){
			if(RecipeUtil.doInputsMatch(recipe.getInputs(), inputs)){
				return recipe;
			}
		}
		return null;
	}
	
	/*public static boolean doInputsMatch(ArrayList<ItemStack> inputRecipe, ArrayList<ItemStack> inputActual) {
		ArrayList<ItemStack> inputsRequired = (ArrayList<ItemStack>) new ArrayList(inputRecipe).clone();

		
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
	}*/
}
