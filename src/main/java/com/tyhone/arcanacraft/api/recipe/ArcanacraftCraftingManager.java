package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ArcanacraftCraftingManager {

	public static List<RecipeDeconstructionTable> decontructionTableRecipes = new ArrayList<RecipeDeconstructionTable>();
	
	//DECONSTRUCTOR RECIPES
	public static RecipeDeconstructionTable registerLensDeconstructionRecipe(ItemStack lens, ItemStack itemOutput, ItemStack itemInputs, double deconstructTime) {
		RecipeDeconstructionTable recipe = new RecipeDeconstructionTable(lens, itemOutput, itemInputs, (int) Math.round(deconstructTime));
		decontructionTableRecipes.add(recipe);
		return recipe;
	}
	public static RecipeDeconstructionTable registerLensDeconstructionRecipe(ItemStack lens, ItemStack itemOutput, ItemStack itemInputs) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(lens, itemOutput, itemInputs, 60);
		return recipe;
	}
	public static RecipeDeconstructionTable registerDeconstructionRecipe(ItemStack itemOutput, ItemStack itemInputs, double deconstructTime) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(ItemStack.EMPTY, itemOutput, itemInputs, deconstructTime);
		return recipe;
	}
	public static RecipeDeconstructionTable registerDeconstructionRecipe(ItemStack itemOutput, ItemStack itemInputs) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(ItemStack.EMPTY, itemOutput, itemInputs, 60);
		return recipe;
	}
	public static List<RecipeDeconstructionTable> getDeconstructionRecipes(){
		return decontructionTableRecipes;
	}
	
}
