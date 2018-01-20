package com.tyhone.arcanacraft.common.jei.deconstruction_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeDeconstructionTable;
import com.tyhone.arcanacraft.common.util.NBTItemStack;
import com.tyhone.arcanacraft.common.util.OreStack;

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
		
		List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
		Object input = this.recipe.getInput();
		if(input instanceof ItemStack){
			inputs.add((Arrays.asList((ItemStack) input)));
		}
		else if(input instanceof OreStack){
			inputs.add(OreStack.getOreDictionaryEntriesForOreStack((OreStack) input));
		}
		else if(input instanceof NBTItemStack){
			ItemStack stack = ((NBTItemStack) input).getStack();
			stack.setTagCompound(((NBTItemStack) input).getNBT());
			inputs.add(Arrays.asList(stack));
		}
		
		if(this.recipe.hasLens()){
			inputs.add(Arrays.asList(this.recipe.getLens()));
		};
		
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
