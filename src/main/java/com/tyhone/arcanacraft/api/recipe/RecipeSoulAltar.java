package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.util.RecipeUtil;

import net.minecraft.item.ItemStack;

public class RecipeSoulAltar {
	
	ItemStack output;
	ItemStack infusionItem;
	ArrayList<ItemStack> inputs;
	
	public RecipeSoulAltar(ItemStack output, ItemStack infusionItem, ArrayList<ItemStack> inputs){
		
		this.output = output;
		this.infusionItem = infusionItem;
		
		ArrayList<ItemStack> inputsToSet = new ArrayList();
		for(ItemStack input : inputs) {
			inputsToSet.add(input);
		}

		this.inputs = inputsToSet;
	}

	public List<ItemStack> getInputs() {
		return new ArrayList(inputs);
	}

	public ItemStack getOutput() {
		return this.output;
	}

	public ItemStack getInfusionItem() {
		return this.infusionItem;
	}
	
	public static RecipeSoulAltar getRecipe(ItemStack infusionItem, ArrayList<ItemStack> inputs){
		for(RecipeSoulAltar recipe : ArcanacraftCraftingManager.getSoulAltarRecipes()){
			if(ItemStackUtil.simpleAreStackSizeEqual(recipe.infusionItem, infusionItem)){
				if(RecipeUtil.doInputsMatch(recipe.inputs, inputs)){
					return recipe;
				}
			}
		}
		return null;
	}
}
