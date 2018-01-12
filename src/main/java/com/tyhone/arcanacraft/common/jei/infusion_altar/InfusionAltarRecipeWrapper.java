package com.tyhone.arcanacraft.common.jei.infusion_altar;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeInfusionAltar;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class InfusionAltarRecipeWrapper implements IRecipeWrapper{

	public final RecipeInfusionAltar recipe;
	
	public InfusionAltarRecipeWrapper(RecipeInfusionAltar recipe){
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		inputs.add(this.recipe.getInfusionItem());
		for(ItemStack input : this.recipe.getInputs()){
			inputs.add(input);
		}

		List<TinktureStack> tInputs = new ArrayList<TinktureStack>();
		for(TinktureStack tInput : this.recipe.getTInputs()){
			tInputs.add(tInput);
		}
		
		ingredients.setInputs(TinktureStack.class, tInputs);
		
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
