package com.tyhone.arcanacraft.api.recipe;

import com.tyhone.arcanacraft.api.util.ItemStackUtil;

import net.minecraft.item.ItemStack;

public class RecipeHammer {

	ItemStack output;
	ItemStack input;
	
	public RecipeHammer(ItemStack output, ItemStack input){
		this.output = output;
		this.input = input;
	}
	
	public ItemStack getInput(){
		return this.input;
	}
	
	public ItemStack getOutput(){
		return this.output;
	}
	
	public static RecipeHammer getRecipe(ItemStack input){
		for(RecipeHammer recipe : ArcanacraftCraftingManager.hanmmerRecipes){
			if(ItemStackUtil.simpleAreStacksEqual(recipe.input, input)){
				return recipe;
			}
		}
		return null;
	}
}
