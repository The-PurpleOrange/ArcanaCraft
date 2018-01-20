package com.tyhone.arcanacraft.common.jei.hammer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeHammer;
import com.tyhone.arcanacraft.common.util.NBTItemStack;
import com.tyhone.arcanacraft.common.util.OreStack;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HammerRecipeWrapper implements IRecipeWrapper{

	public final RecipeHammer recipe;
	
	public HammerRecipeWrapper(RecipeHammer recipe){
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		Object input = this.recipe.getInput();
		if(input instanceof ItemStack){
			inputs.add((ItemStack) input);
		}
		else if(input instanceof OreStack){
			inputs = (OreStack.getOreDictionaryEntriesForOreStack((OreStack) input));
		}
		else if(input instanceof NBTItemStack){
			ItemStack stack = ((NBTItemStack) input).getStack();
			stack.setTagCompound(((NBTItemStack) input).getNBT());
			inputs.add(stack);
		}
		
		//ingredients.setInput(ItemStack.class, this.recipe.getInput());
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
