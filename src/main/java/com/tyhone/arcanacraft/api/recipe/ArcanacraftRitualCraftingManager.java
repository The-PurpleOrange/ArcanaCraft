package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class ArcanacraftRitualCraftingManager {

	private static List<RecipeRitualCircle> ritualCircleRecipe = new ArrayList<RecipeRitualCircle>();
	//private static Map<String, RecipeRitualCircle> activationCircleRecipes = new HashMap<String, RecipeRitualCircle>();
	

	//ACTIVATION CIRCLE RECIPES
	public static RecipeRitualCircle registerRitualCircle(String unlocalizedName, ItemStack[] itemInputs, Object[] blockInputs) {
		RecipeRitualCircle recipe = new RecipeRitualCircle(unlocalizedName, itemInputs, blockInputs);
		ritualCircleRecipe.add(recipe);
		//activationCircleRecipes.put(unlocalizedName, recipe);
		return recipe;
	}
	/*public static RecipeRitualCircle registerRitualCircle(String unlocalizedName, Object[] blockInputs) {
		RecipeRitualCircle recipe = registerRitualCircle(unlocalizedName, null, blockInputs);
		return recipe;
	}*/
	public static List<RecipeRitualCircle> getRitualCircleRecipes(){
		return ritualCircleRecipe;
	}
}
