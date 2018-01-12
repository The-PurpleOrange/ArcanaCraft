package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;

import net.minecraft.item.ItemStack;

public class ArcanacraftCraftingManager {

	private static List<RecipeDeconstructionTable> decontructionTableRecipes = new ArrayList<RecipeDeconstructionTable>();
	private static List<RecipeHammer> hanmmerRecipes = new ArrayList<RecipeHammer>();
	private static List<RecipeAlchemicArray> alchemicalArrayRecipes = new ArrayList<RecipeAlchemicArray>();
	private static List<RecipeTransmutationAltar> transmutationAltarRecipes = new ArrayList<RecipeTransmutationAltar>();
	private static List<RecipeInfusionAltar> infusionAltarRecipes = new ArrayList<RecipeInfusionAltar>();
	private static List<RecipeSoulAltar> soulAltarRecipes = new ArrayList<RecipeSoulAltar>();
	
	//DECONSTRUCTOR RECIPES
	public static RecipeDeconstructionTable registerLensDeconstructionRecipe(ItemStack lens, ItemStack itemOutput, ItemStack itemInputs, double deconstructTime) {
		lens.setCount(1);
		itemInputs.setCount(1);
		RecipeDeconstructionTable recipe = new RecipeDeconstructionTable(lens, itemOutput, itemInputs, (int) Math.round(deconstructTime));
		decontructionTableRecipes.add(recipe);
		return recipe;
	}
	public static RecipeDeconstructionTable registerLensDeconstructionRecipe(ItemStack lens, ItemStack itemOutput, ItemStack itemInputs) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(lens, itemOutput, itemInputs, 120);
		return recipe;
	}
	public static RecipeDeconstructionTable registerDeconstructionRecipe(ItemStack itemOutput, ItemStack itemInputs, double deconstructTime) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(ItemStack.EMPTY, itemOutput, itemInputs, deconstructTime);
		return recipe;
	}
	public static RecipeDeconstructionTable registerDeconstructionRecipe(ItemStack itemOutput, ItemStack itemInputs) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(ItemStack.EMPTY, itemOutput, itemInputs, 120);
		return recipe;
	}
	public static List<RecipeDeconstructionTable> getDeconstructionRecipes(){
		return decontructionTableRecipes;
	}
	
	//HAMMER RECIPES
	public static RecipeHammer registerHammerRecipe(ItemStack itemOutput, ItemStack itemInputs) {
		itemInputs.setCount(1);
		RecipeHammer recipe = new RecipeHammer(itemOutput, itemInputs);
		hanmmerRecipes.add(recipe);
		return recipe;
	}
	public static List<RecipeHammer> getHammerRecipes(){
		return hanmmerRecipes;
	}

	//ALCHEMICAL ARRAY RECIPES
	public static RecipeAlchemicArray registerAlchemicArrayRecipe(ItemStack output, ItemStack ... inputs) {
		RecipeAlchemicArray recipe = new RecipeAlchemicArray(output, inputs);
		alchemicalArrayRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeAlchemicArray> getAlchemicArrayRecipes(){
		return alchemicalArrayRecipes;
	}

	//TRANSMUTATION RECIPES
	public static RecipeTransmutationAltar registerTransmutationRecipes(ItemStack output, ItemStack ... inputs) {
		if(inputs == null || inputs.length>4){
			Arcanacraft.logger.error("Invalid transmutation recipe added for: " + output);
			return null;
		}
		for(ItemStack item : inputs){
			item.setCount(1);
		}
		RecipeTransmutationAltar recipe = new RecipeTransmutationAltar(output, inputs);
		transmutationAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeTransmutationAltar> getTransmutationAltarRecipes(){
		return transmutationAltarRecipes;
	}

	//INFUSION RECIPES
	public static RecipeInfusionAltar registerInfusionRecipe(ItemStack output, ItemStack itemInfusionItem, ItemStack[] inputs, TinktureStack[] tInputs) {
		itemInfusionItem.setCount(1);
		for(ItemStack item : inputs){
			item.setCount(1);
		}
		RecipeInfusionAltar recipe = new RecipeInfusionAltar(output, itemInfusionItem, inputs, tInputs);
		infusionAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeInfusionAltar> getInfusionAltarRecipes(){
		return infusionAltarRecipes;
	}
	
	//SOUL RECIPES
	public static RecipeSoulAltar registerSoulInfusionRecipe(ItemStack output, ItemStack itemInfusionItem, ItemStack ... inputs) {
		itemInfusionItem.setCount(1);
		for(ItemStack item : inputs){
			item.setCount(1);
		}
		RecipeSoulAltar recipe = new RecipeSoulAltar(output, itemInfusionItem, inputs);
		soulAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeSoulAltar> getSoulAltarRecipes(){
		return soulAltarRecipes;
	}
}
