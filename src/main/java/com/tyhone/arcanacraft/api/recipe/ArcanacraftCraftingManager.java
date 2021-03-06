package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.util.OreStack;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.item.ItemStack;

public class ArcanacraftCraftingManager {

	private static List<RecipeDeconstructionTable> decontructionTableRecipes = new ArrayList<RecipeDeconstructionTable>();
	private static List<RecipeHammer> hanmmerRecipes = new ArrayList<RecipeHammer>();
	private static List<RecipeAlchemicArray> alchemicalArrayRecipes = new ArrayList<RecipeAlchemicArray>();
	private static List<RecipeTransmutationAltar> transmutationAltarRecipes = new ArrayList<RecipeTransmutationAltar>();
	private static List<RecipeInfusionAltar> infusionAltarRecipes = new ArrayList<RecipeInfusionAltar>();
	private static List<RecipeSoulAltar> soulAltarRecipes = new ArrayList<RecipeSoulAltar>();
	private static List<RecipeAlembic> alembicRecipes = new ArrayList<RecipeAlembic>();
	private static List<RecipeInlayTable> inlayTableRecipes = new ArrayList<RecipeInlayTable>();
	
	//DECONSTRUCTOR RECIPES
	public static RecipeDeconstructionTable registerLensDeconstructionRecipe(ItemStack lens, Object objectOutput, Object objectInput, double deconstructTime) {
		lens.setCount(1);
		Object input = WildStack.objectToItemStackOrOreStack(objectInput);
		//Object output = WildStack.objectToItemStackOrOreStack(objectOutput);
		
		ItemStack itemOutput = ItemStack.EMPTY;
		if(objectOutput instanceof OreStack) {
			itemOutput = OreStack.getOreDictionaryEntryForOreStack((OreStack) objectOutput);
		}
		else if(objectOutput instanceof ItemStack) {
			itemOutput = (ItemStack) objectOutput;
		}
		
		if(input==null){
			Arcanacraft.logger.error("Error register Recipe for Deconstruction Table: " + input.toString() + ":" + itemOutput.toString());
			return null;
		}
		
		RecipeDeconstructionTable recipe = new RecipeDeconstructionTable(lens, itemOutput, input, (int) Math.round(deconstructTime));
		decontructionTableRecipes.add(recipe);
		return recipe;
	}
	public static RecipeDeconstructionTable registerLensDeconstructionRecipe(ItemStack lens, Object itemOutput, Object itemInputs) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(lens, itemOutput, itemInputs, 120);
		return recipe;
	}
	public static RecipeDeconstructionTable registerDeconstructionRecipe(Object itemOutput, Object itemInputs, double deconstructTime) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(ItemStack.EMPTY, itemOutput, itemInputs, deconstructTime);
		return recipe;
	}
	public static RecipeDeconstructionTable registerDeconstructionRecipe(Object itemOutput, Object itemInputs) {
		RecipeDeconstructionTable recipe = registerLensDeconstructionRecipe(ItemStack.EMPTY, itemOutput, itemInputs, 120);
		return recipe;
	}
	public static List<RecipeDeconstructionTable> getDeconstructionRecipes(){
		return decontructionTableRecipes;
	}
	
	//HAMMER RECIPES
	public static RecipeHammer registerHammerRecipe(ItemStack itemOutput, Object objectInput) {
		
		Object input = WildStack.objectToItemStackOrOreStack(objectInput);
		if(input==null){
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
		ArrayList<Object> inputs = WildStack.objectListToItemStackOrOreStack(inputObjects);
		if(inputs.size()==0){
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
			Arcanacraft.logger.error("Error register Recipe for Transmutation Altar: " + output.getDisplayName());
			return null;
		}
		
		ArrayList<Object> inputs = WildStack.objectListToItemStackOrOreStack(inputObjects);
		if(inputs.size()==0){
			Arcanacraft.logger.error("Error register Recipe for Transmutation Altar: " + output.getDisplayName());
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
	public static RecipeInfusionAltar registerInfusionRecipe(ItemStack output, Object infusionObject, Object[] inputObjects, TinktureStack[] tInputs) {
		if(inputObjects == null || inputObjects.length>8){
			Arcanacraft.logger.error("Error register Recipe for Infusion Altar: " + output.getDisplayName());
			return null;
		}

		ArrayList<Object> inputs = WildStack.objectListToItemStackOrOreStack(inputObjects);
		if(inputs.size()==0){
			Arcanacraft.logger.error("Error register Recipe for Infusion Altar: " + output.getDisplayName());
			return null;
		}
		
		if(infusionObject instanceof ItemStack){
			((ItemStack) infusionObject).setCount(1);
			
		}
		else if(infusionObject instanceof OreStack){
			((OreStack) infusionObject).setCount(1);
			
		}
		
		RecipeInfusionAltar recipe = new RecipeInfusionAltar(output, infusionObject, inputs, tInputs);
		infusionAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeInfusionAltar> getInfusionAltarRecipes(){
		return infusionAltarRecipes;
	}
	
	//SOUL INFUSION RECIPES
	public static RecipeSoulAltar registerSoulInfusionRecipe(ItemStack output, Object infusionObject, Object ... inputObjects) {
		if(inputObjects == null || inputObjects.length>5){
			Arcanacraft.logger.error("Error register Recipe for Soul Infusion recipe: " + output.getDisplayName());
			return null;
		}

		ArrayList<Object> inputs = WildStack.objectListToItemStackOrOreStack(inputObjects);
		if(inputs.size()==0){
			Arcanacraft.logger.error("Error register Recipe for Infusion Altar: " + output.getDisplayName());
			return null;
		}
		
		if(infusionObject instanceof ItemStack){
			((ItemStack) infusionObject).setCount(1);
			
		}
		else if(infusionObject instanceof OreStack){
			((OreStack) infusionObject).setCount(1);
			
		}
		
		RecipeSoulAltar recipe = new RecipeSoulAltar(output, infusionObject, inputs);
		soulAltarRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeSoulAltar> getSoulAltarRecipes(){
		return soulAltarRecipes;
	}

	//ALEMBIC RECIPES
	public static RecipeAlembic registerAlembicRecipe(Object output, @Nullable Object bottomObject, Object object1, Object object2) {
		Object out, bo = null, obj1, obj2 = null;
		
		out = WildStack.objectToAnything(output);
		
		if(bottomObject != null){
			bo = WildStack.objectToAnything(bottomObject);
		}
		
		obj1 = WildStack.objectToAnything(object1);
		
		if(object2 != null){
			obj2 = WildStack.objectToAnything(object2);
		}
		
		RecipeAlembic recipe = new RecipeAlembic(out, bo, obj1, obj2);
		alembicRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeAlembic> getAlembicRecipes(){
		return alembicRecipes;
	}
	
	//Inlay Table RECIPES
	public static RecipeInlayTable registerInlayTableRecipe(ItemStack output, Object ... inputObjects) {
		if(inputObjects == null || inputObjects.length>8){
			Arcanacraft.logger.error("Error register Recipe for Inlay Table: " + output.getDisplayName());
			return null;
		}

		ArrayList<Object> inputs = WildStack.objectListToItemStackOrOreStack(inputObjects);
		if(inputs.size()==0){
			Arcanacraft.logger.error("Error register Recipe for Inlay Table: " + output.getDisplayName());
			return null;
		}
		
		RecipeInlayTable recipe = new RecipeInlayTable(output, inputs);
		inlayTableRecipes.add(recipe);
		return recipe;
	}
	
	public static List<RecipeInlayTable> getInlayTableRecipes(){
		return inlayTableRecipes;
	}
}
