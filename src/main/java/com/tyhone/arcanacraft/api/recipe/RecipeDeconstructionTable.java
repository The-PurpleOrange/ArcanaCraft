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
	
	public ItemStack getLens(){
		return this.lens;
	}
	
	public ItemStack getInputs(){
		return this.input;
	}
	
	public ItemStack getOutputs(){
		return this.output;
	}
	
	public int getDeconstrutionTime(){
		return this.deconstructionTime;
	}

	public boolean isCorrectLens(ItemStack lens){
		return ItemStackUtil.simpleAreStackSizeEqual(this.lens, lens);
	}
	
	/*public boolean matches(ItemStack item, ItemStack lens){
		boolean lensFlag = false;
		if(lens == null && this.lens == null){
			lensFlag = true;
		}
		else if(lens == null || this.lens == null){
			return false;
		}
		else{
			lensFlag = isCorrectLens(lens);
		}
	
		return (ItemStackUtil.simpleAreStackSizeEqual(this.input, item) && lensFlag);
	}*/	
	
	public static RecipeDeconstructionTable getRecipe(ItemStack input, ItemStack lens){
		for(RecipeDeconstructionTable recipe : ArcanacraftCraftingManager.decontructionTableRecipes){
			if(ItemStackUtil.simpleAreStackSizeEqual(recipe.input, input)){
				if(ItemStackUtil.simpleAreStackSizeEqual(recipe.lens, lens)){
					return recipe;
				}
			}
		}
		return null;
	}
	
	/*public static ItemStack getDeconstructResult(ItemStack input){
		RecipeDeconstructionTable recipe = getRecipe(input);
		if(recipe != null){
			return recipe.output;
		}
        return ItemStack.EMPTY;
	}*/
}
