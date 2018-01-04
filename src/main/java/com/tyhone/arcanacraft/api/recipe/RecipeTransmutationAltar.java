package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.util.ItemStackUtil;

import net.minecraft.item.ItemStack;

public class RecipeTransmutationAltar {
	
	ItemStack output;
	ArrayList<ItemStack> inputs;
	
	public RecipeTransmutationAltar(ItemStack output, ItemStack[] inputs){
		
		if(inputs == null || inputs.length>4){
			Arcanacraft.logger.log(Level.ERROR ,"Invalid transmutation recipe added for: " + output);
		}
		
		this.output = output;

		this.inputs = new ArrayList<ItemStack>(Arrays.asList(inputs));
	}

	public ArrayList<ItemStack> getInputs() {
		return new ArrayList(inputs);
	}

	public ItemStack getOutput() {
		return this.output;
	}
	
	public static RecipeTransmutationAltar getRecipe(ArrayList<ItemStack> inputs){
		for(RecipeTransmutationAltar recipe : ArcanacraftCraftingManager.getTransmutationAltarRecipes()){
			if(doInputsMatch(recipe.getInputs(), inputs)){
				return recipe;
			}
		}
		return null;
	}
	
	public static boolean doInputsMatch(ArrayList<ItemStack> inputRecipe, ArrayList<ItemStack> inputActual) {
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
	}
}
