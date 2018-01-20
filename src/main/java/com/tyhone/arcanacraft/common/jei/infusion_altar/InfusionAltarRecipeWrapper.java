package com.tyhone.arcanacraft.common.jei.infusion_altar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeInfusionAltar;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.util.NBTItemStack;
import com.tyhone.arcanacraft.common.util.OreStack;

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
		
		
		//ItemStack Inputs
		List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
		Object infusionItem = this.recipe.getInfusionItem();
		if(infusionItem instanceof ItemStack){
			inputs.add(Arrays.asList((ItemStack) infusionItem));
		}
		else if(infusionItem instanceof OreStack){
			inputs.add(OreStack.getOreDictionaryEntriesForOreStack((OreStack) infusionItem));
		}
		
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

		//Tinkture Inputs
		List<TinktureStack> tInputs = new ArrayList<TinktureStack>();
		for(TinktureStack tInput : this.recipe.getTInputs()){
			tInputs.add(tInput);
		}

		ingredients.setInputs(TinktureStack.class, tInputs);
		
		//Outputs
		ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
	}

}
