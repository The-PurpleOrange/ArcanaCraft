package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArcanacraftCraftingManager {

	private static List<RecipeDeconstructionTable> decontructionTableRecipes = new ArrayList<RecipeDeconstructionTable>();
	private static List<RecipeHammer> hanmmerRecipes = new ArrayList<RecipeHammer>();
	private static List<RecipeAlchemicArray> alchemicalArrayRecipes = new ArrayList<RecipeAlchemicArray>();
	private static List<RecipeTransmutationAltar> transmutationAltarRecipes = new ArrayList<RecipeTransmutationAltar>();
	private static List<RecipeInfusionAltar> infusionAltarRecipes = new ArrayList<RecipeInfusionAltar>();
	private static List<RecipeSoulAltar> soulAltarRecipes = new ArrayList<RecipeSoulAltar>();
	
	//DECONSTRUCTOR RECIPES
	public static RecipeDeconstructionTable registerLensDeconstructionRecipe(ItemStack lens, ItemStack itemOutput, Object objectInput, double deconstructTime) {
		lens.setCount(1);
		ItemStack input = WildStack.ObjectToInput(objectInput, 1);
		if(input.isEmpty()){
			Arcanacraft.logger.error("Error register Recipe for Deconstruction Table: " + itemOutput.getDisplayName());
			return null;
		}
		
		RecipeDeconstructionTable recipe = new RecipeDeconstructionTable(lens, itemOutput, input, (int) Math.round(deconstructTime));
		decontructionTableRecipes.add(recipe);
		return recipe;
	}
	public static RecipeDeconstructionTable registerLensDeconstructionRecipe(ItemStack lens, ItemStack itemOutput, Object itemInputs) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(lens, itemOutput, itemInputs, 120);
		return recipe;
	}
	public static RecipeDeconstructionTable registerDeconstructionRecipe(ItemStack itemOutput, Object itemInputs, double deconstructTime) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(ItemStack.EMPTY, itemOutput, itemInputs, deconstructTime);
		return recipe;
	}
	public static RecipeDeconstructionTable registerDeconstructionRecipe(ItemStack itemOutput, Object itemInputs) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(ItemStack.EMPTY, itemOutput, itemInputs, 120);
		return recipe;
	}
	public static List<RecipeDeconstructionTable> getDeconstructionRecipes(){
		return decontructionTableRecipes;
	}
	
	//HAMMER RECIPES
	public static RecipeHammer registerHammerRecipe(ItemStack itemOutput, Object objectInput) {
		ItemStack input = WildStack.ObjectToInput(objectInput);
		if(input.isEmpty()){
			Arcanacraft.logger.error("Error register Recipe for Hammer: " + itemOutput.getDisplayName());
			return null;
		}
		
		RecipeHammer recipe = new RecipeHammer(itemOutput, input);
		hanmmerRecipes.add(recipe);
		return recipe;
	}
	public static List<RecipeHammer> getHammerRecipes(){
		return hanmmerRecipes;
	}

	//ALCHEMICAL ARRAY RECIPES
	public static RecipeAlchemicArray registerAlchemicArrayRecipe(ItemStack output, Object ... inputObjects) {
		ArrayList<ItemStack> inputs = WildStack.InitObjectListToItemStackList(inputObjects);
		if(inputs==null){
			Arcanacraft.logger.error("Error register Recipe for Alchemic Array: " + output.getDisplayName());
			return null;
		}
		
		RecipeAlchemicArray recipe = new RecipeAlchemicArray(output, inputs);
		alchemicalArrayRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeAlchemicArray> getAlchemicArrayRecipes(){
		return alchemicalArrayRecipes;
	}

	//TRANSMUTATION RECIPES
	public static RecipeTransmutationAltar registerTransmutationRecipes(ItemStack output, Object ... inputObjects) {
		if(inputObjects == null || inputObjects.length>4){
			Arcanacraft.logger.error("Error register Recipe for Transmutation recipe: " + output);
			return null;
		}
		
		ArrayList<ItemStack> inputs = WildStack.InitObjectListToItemStackList(inputObjects, 1);
		if(inputs==null){
			Arcanacraft.logger.error("Error register Recipe for Alchemic Array: " + output.getDisplayName());
			return null;
		}
		
		RecipeTransmutationAltar recipe = new RecipeTransmutationAltar(output, inputs);
		transmutationAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeTransmutationAltar> getTransmutationAltarRecipes(){
		return transmutationAltarRecipes;
	}

	//INFUSION RECIPES
	public static RecipeInfusionAltar registerInfusionRecipe(ItemStack output, ItemStack itemInfusionItem, Object[] inputObjects, TinktureStack[] tInputs) {
		if(inputObjects == null || inputObjects.length>8){
			Arcanacraft.logger.error("Error register Recipe for Infusion recipe: " + output);
			return null;
		}
		
		ArrayList<ItemStack> inputs = WildStack.InitObjectListToItemStackList(inputObjects, 1);
		if(inputs==null){
			Arcanacraft.logger.error("Error register Recipe for Alchemic Array: " + output.getDisplayName());
			return null;
		}
		
		itemInfusionItem.setCount(1);
		RecipeInfusionAltar recipe = new RecipeInfusionAltar(output, itemInfusionItem, inputs, tInputs);
		infusionAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeInfusionAltar> getInfusionAltarRecipes(){
		return infusionAltarRecipes;
	}
	
	//SOUL RECIPES
	public static RecipeSoulAltar registerSoulInfusionRecipe(ItemStack output, ItemStack itemInfusionItem, Object ... inputObjects) {
		if(inputObjects == null || inputObjects.length>5){
			Arcanacraft.logger.error("Error register Recipe for Soul Infusion recipe: " + output);
			return null;
		}
		
		ArrayList<ItemStack> inputs = WildStack.InitObjectListToItemStackList(inputObjects, 1);
		if(inputs==null){
			Arcanacraft.logger.error("Error register Recipe for Alchemic Array: " + output.getDisplayName());
			return null;
		}
		
		itemInfusionItem.setCount(1);
		RecipeSoulAltar recipe = new RecipeSoulAltar(output, itemInfusionItem, inputs);
		soulAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeSoulAltar> getSoulAltarRecipes(){
		return soulAltarRecipes;
	}
}
