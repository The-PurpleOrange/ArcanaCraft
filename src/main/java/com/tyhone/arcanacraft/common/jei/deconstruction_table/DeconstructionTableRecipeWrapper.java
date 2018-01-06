package com.tyhone.arcanacraft.common.jei.deconstruction_table;

import java.util.Arrays;

import com.tyhone.arcanacraft.api.recipe.RecipeDeconstructionTable;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class DeconstructionTableRecipeWrapper implements IRecipeWrapper{

	public final RecipeDeconstructionTable recipe;
	
	public DeconstructionTableRecipeWrapper(RecipeDeconstructionTable recipe){
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		if(this.recipe.hasLens()){
			ingredients.setInputs(ItemStack.class, Arrays.asList(this.recipe.getInput(), this.recipe.getLens()));
		}else{
			ingredients.setInput(ItemStack.class, this.recipe.getInput());
		};
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
