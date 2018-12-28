package com.tyhone.arcanacraft.common.jei.inlay_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeInlayTable;
import com.tyhone.arcanacraft.common.util.NBTItemStack;
import com.tyhone.arcanacraft.common.util.OreStack;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class InlayTableRecipeWrapper implements IRecipeWrapper{

	public final RecipeInlayTable recipe;
	
	public InlayTableRecipeWrapper(RecipeInlayTable recipe){
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		
		
		List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
		for(Object input : this.recipe.getInputs()){
			if(input instanceof ItemStack){
				inputs.add(Arrays.asList((ItemStack) input));
			}
			else if(input instanceof OreStack){
				inputs.add(OreStack.getOreDictionaryEntriesForOreStack((OreStack) input));
			}
			else if(input instanceof NBTItemStack){
				ItemStack stack = ((NBTItemStack) input).getStack();
				stack.setTagCompound(((NBTItemStack) input).getNBT());
				inputs.add(Arrays.asList(stack));
			}
		}
		
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
