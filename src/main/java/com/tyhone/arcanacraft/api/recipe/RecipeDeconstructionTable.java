package com.tyhone.arcanacraft.api.recipe;

import com.tyhone.arcanacraft.api.util.ItemStackUtil;

import net.minecraft.item.ItemStack;

public class RecipeDeconstructionTable {

	private final ItemStack lens;
	private final ItemStack output;
	private final Object input;
	private final int deconstructionTime;
	
	public RecipeDeconstructionTable(ItemStack lens, ItemStack output, Object input, int deconstructionTime){
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
	
	public Object getInput(){
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
			if(ItemStackUtil.simpleAreStackSizeEqual(recipe.getInput(), input)){
				if(ItemStackUtil.simpleAreStackSizeEqual(recipe.getLens(), lens)){
					return recipe;
				}
			}
		}
		return null;
	}
}
