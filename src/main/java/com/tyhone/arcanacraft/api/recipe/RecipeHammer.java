package com.tyhone.arcanacraft.api.recipe;

import com.tyhone.arcanacraft.api.util.ItemStackUtil;

import net.minecraft.item.ItemStack;

public class RecipeHammer {

	ItemStack output;
	Object input;
	
	public RecipeHammer(ItemStack output, Object input){
		this.output = output;
		this.input = input;
	}
	
	public Object getInput(){
		return this.input;
	}
	
	public ItemStack getOutput(){
		return this.output;
	}
	
	public static RecipeHammer getRecipe(ItemStack input){
		for(RecipeHammer recipe : ArcanacraftCraftingManager.getHammerRecipes()){
			if(ItemStackUtil.simpleAreStacksEqual(recipe.input, input)){
				return recipe;
			}
		}
		return null;
	}
}
