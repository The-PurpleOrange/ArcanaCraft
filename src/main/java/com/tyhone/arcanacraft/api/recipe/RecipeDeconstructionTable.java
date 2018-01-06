package com.tyhone.arcanacraft.api.recipe;

import com.tyhone.arcanacraft.api.util.ItemStackUtil;

import net.minecraft.item.ItemStack;

public class RecipeDeconstructionTable {

	ItemStack lens;
	ItemStack output;
	ItemStack input;
	int deconstructionTime;
	
	public RecipeDeconstructionTable(ItemStack lens, ItemStack output, ItemStack input, int deconstructionTime){
		this.lens = lens;
		this.output = output;
		this.input = input;
		this.deconstructionTime = deconstructionTime;
	}
	
	public boolean hasLens(){
		if(this.lens!=null){
			return true;
		}
		return false;
	}
	
	public ItemStack getLens(){
		return this.lens;
	}
	
	public ItemStack getInput(){
		return this.input;
	}
	
	public ItemStack getOutput(){
		return this.output;
	}
	
	public int getDeconstrutionTime(){
		return this.deconstructionTime;
	}

	public boolean isCorrectLens(ItemStack lens){
		return ItemStackUtil.simpleAreStackSizeEqual(this.lens, lens);
	}
	
	public static RecipeDeconstructionTable getRecipe(ItemStack input, ItemStack lens){
		for(RecipeDeconstructionTable recipe : ArcanacraftCraftingManager.getDeconstructionRecipes()){
			if(ItemStackUtil.simpleAreStackSizeEqual(recipe.input, input)){
				if(ItemStackUtil.simpleAreStackSizeEqual(recipe.lens, lens)){
					return recipe;
				}
			}
		}
		return null;
	}
}
