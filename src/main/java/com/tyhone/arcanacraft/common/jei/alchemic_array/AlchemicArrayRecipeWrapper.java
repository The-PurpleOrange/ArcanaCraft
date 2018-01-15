package com.tyhone.arcanacraft.common.jei.alchemic_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeAlchemicArray;
import com.tyhone.arcanacraft.common.util.OreStack;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class AlchemicArrayRecipeWrapper implements IRecipeWrapper{

	public final RecipeAlchemicArray recipe;
	
	public AlchemicArrayRecipeWrapper(RecipeAlchemicArray recipe){
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		
		List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
		for(Object input : this.recipe.getInputs()){
			if(input instanceof ItemStack){
				inputs.add(Arrays.asList((ItemStack) input));
				
				/*List<ItemStack> subInputs = new ArrayList<>();
				subInputs.add((ItemStack) input);
				inputs.add(subInputs);*/
			}
			else if(input instanceof OreStack){
				inputs.add(OreStack.getOreDictionaryEntriesForOreStack((OreStack) input));
			}
		}
		
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
