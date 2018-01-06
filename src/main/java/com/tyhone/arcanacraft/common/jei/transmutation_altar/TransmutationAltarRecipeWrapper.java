package com.tyhone.arcanacraft.common.jei.transmutation_altar;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeTransmutationAltar;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class TransmutationAltarRecipeWrapper implements IRecipeWrapper{

	public final RecipeTransmutationAltar recipe;
	
	public TransmutationAltarRecipeWrapper(RecipeTransmutationAltar recipe){
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		for(ItemStack input : this.recipe.getInputs()){
			inputs.add(input);
		}
		
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
