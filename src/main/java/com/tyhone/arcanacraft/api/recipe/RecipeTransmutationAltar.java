package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.RecipeUtil;

import net.minecraft.item.ItemStack;

public class RecipeTransmutationAltar {
	
	ItemStack output;
	ArrayList<Object> inputs;
	
	public RecipeTransmutationAltar(ItemStack output, ArrayList<Object> inputs){
		
		this.output = output;
		this.inputs = (ArrayList<Object>) inputs.clone();
	}

	public ArrayList<Object> getInputs() {
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
}
