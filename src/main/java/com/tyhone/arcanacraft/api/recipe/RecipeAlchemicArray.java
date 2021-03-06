package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.RecipeUtil;

import net.minecraft.item.ItemStack;

public class RecipeAlchemicArray {
	
	ItemStack output;
	ArrayList<Object> inputs;
	
	public RecipeAlchemicArray(ItemStack output, ArrayList<Object> inputs){
		
		if(inputs == null){
			Arcanacraft.logger.error("Invalid Alchemic Array recipe added for: " + output);
		}
		else {
		
			this.output = output;
	
			this.inputs = (ArrayList<Object>) inputs.clone();
		}
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
