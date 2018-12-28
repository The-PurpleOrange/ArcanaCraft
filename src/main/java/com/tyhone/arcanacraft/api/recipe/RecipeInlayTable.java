package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.common.util.RecipeUtil;

import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

public class RecipeInlayTable {
	
	ItemStack output;
	ArrayList<Object> inputs;
	
	public RecipeInlayTable(ItemStack output, ArrayList<Object> inputs){
		
		this.output = output;
		this.inputs = (ArrayList<Object>) inputs.clone();
	}

	public List<ItemStack> getInputs() {
		return new ArrayList(inputs);
	}

	public ItemStack getOutput() {
		return this.output;
	}
	
	public static RecipeInlayTable getRecipe(ItemStack[] stackArray){
		for(RecipeInlayTable recipe : ArcanacraftCraftingManager.getInlayTableRecipes()){
			ArrayList<ItemStack> al = new ArrayList<ItemStack>();
			for(ItemStack stack : stackArray) {
				if(!stack.isEmpty()) {
					al.add(stack);
				}
			}
			if(RecipeUtil.doInputsMatch(recipe.inputs, al)){
				return recipe;
			}
		}
		return null;
	}
}
