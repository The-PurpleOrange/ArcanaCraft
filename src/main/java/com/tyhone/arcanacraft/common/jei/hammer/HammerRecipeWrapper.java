package com.tyhone.arcanacraft.common.jei.hammer;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeHammer;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class HammerRecipeWrapper implements IRecipeWrapper{

	public final RecipeHammer recipe;
	
	public HammerRecipeWrapper(RecipeHammer recipe){
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, this.recipe.getInput());
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
