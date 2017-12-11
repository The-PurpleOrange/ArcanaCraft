package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ArcanacraftCraftingManager {

	public static List<RecipeDeconstructionTable> decontructionTableRecipes = new ArrayList<RecipeDeconstructionTable>();
	public static List<RecipeHammer> hanmmerRecipes = new ArrayList<RecipeHammer>();
	public static List<RecipeInfusionAltar> infusionAltarRecipes = new ArrayList<RecipeInfusionAltar>();
	public static List<RecipeSoulAltar> soulAltarRecipes = new ArrayList<RecipeSoulAltar>();
	
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
	
	//HAMMER RECIPES
	public static RecipeHammer registerHammerRecipe(ItemStack itemOutput, ItemStack itemInputs) {
		RecipeHammer recipe = new RecipeHammer(itemOutput, itemInputs);
		hanmmerRecipes.add(recipe);
		return recipe;
	}
	public static List<RecipeHammer> getHammerRecipes(){
		return hanmmerRecipes;
	}

	//INFUSION RECIPES
	public static RecipeInfusionAltar registerInfusionRecipes(ItemStack output, ItemStack itemInfusionItem, ItemStack ... inputs) {
		RecipeInfusionAltar recipe = new RecipeInfusionAltar(output, itemInfusionItem, inputs);
		infusionAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeInfusionAltar> getInfusionAltarRecipes(){
		return infusionAltarRecipes;
	}
	
	//SOUL RECIPES
	public static RecipeSoulAltar registerSoulInfusionRecipes(ItemStack output, ItemStack itemInfusionItem, ItemStack ... inputs) {
		RecipeSoulAltar recipe = new RecipeSoulAltar(output, itemInfusionItem, inputs);
		soulAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeSoulAltar> getSoulInfusionAltarRecipes(){
		return soulAltarRecipes;
	}
}
