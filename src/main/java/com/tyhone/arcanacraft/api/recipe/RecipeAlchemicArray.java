package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.util.RecipeUtil;

import net.minecraft.item.ItemStack;

public class RecipeAlchemicArray {
	
	ItemStack output;
	ArrayList<Object> inputs;
	
	public RecipeAlchemicArray(ItemStack output, ArrayList<Object> inputs){
		
		if(inputs == null){
			Arcanacraft.logger.error("Invalid Alchemic Array recipe added for: " + output);
		}
		
		this.output = output;

		this.inputs = (ArrayList<Object>) inputs.clone();
	}

	public ArrayList<Object> getInputs() {
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
}
