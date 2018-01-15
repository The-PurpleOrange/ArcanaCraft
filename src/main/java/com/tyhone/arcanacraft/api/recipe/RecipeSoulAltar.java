package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.util.RecipeUtil;

import net.minecraft.item.ItemStack;

public class RecipeSoulAltar {
	
	ItemStack output;
	Object infusionItem;
	ArrayList<Object> inputs;
	
	public RecipeSoulAltar(ItemStack output, Object infusionItem, ArrayList<Object> inputs){
		
		this.output = output;
		this.infusionItem = infusionItem;
		this.inputs = (ArrayList<Object>) inputs.clone();
	}

	public List<ItemStack> getInputs() {
		return new ArrayList(inputs);
	}

	public ItemStack getOutput() {
		return this.output;
	}

	public Object getInfusionItem() {
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
